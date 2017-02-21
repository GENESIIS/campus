package com.genesiis.campus.command;

//20161208 JH c39-add-course-provider CmdAddCourseProviderAccount.java created
//20161209 JH c39-add-course-provider class name renamed to CmdCourseProviderAccountValidate
//20161222 JH c39-add-course-provider code modifications for Username validation 
//20161223 JH c39-add-course-provider code modified
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170221 JH c141-add-course-provider-issue-improvements modified to access validator class methods in static way  

import com.genesiis.campus.entity.CourseProviderPrefixDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderUsernameDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class CmdCourseProviderAccountValidate implements ICommand {
	static Logger log = Logger
			.getLogger(CmdCourseProviderAccountValidate.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		final String action;
		SystemMessage message = null;
		int validationFlag = 0;

		try {
			if (!Validator.isEmptyString(helper.getParameter("action"))) {

				action = helper.getParameter("action");

				if (action
						.equalsIgnoreCase("COURSE_PROVIDER_USERNAME_VALIDATION")) {

					ICrud usernameDAO = new CourseProviderUsernameDAO();

					if (!Validator.isEmptyString(helper.getParameter("username"))
							|| !Validator.isEmptyString(helper.getParameter("email"))) {

						String username = helper.getParameter("username");
						final CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
						courseProviderAccount.setUsername(username);
						Collection<Collection<String>> usernameCollection = new ArrayList<Collection<String>>();

						usernameCollection = usernameDAO
								.findById(courseProviderAccount);
						if (usernameCollection.size() != 0) {
							message = SystemMessage.USERNAME_INVALID;
							validationFlag = 0;

						} else {
							message = SystemMessage.USERNAME_VALID;
							validationFlag = 1;
						}
					} else {
						message = SystemMessage.EMPTY_USERNAME;
						validationFlag = 0;
					}
				} else if (action
						.equalsIgnoreCase("COURSE_PROVIDER_PREFIX_VALIDATION")) {

					ICrud prefixDAO = new CourseProviderPrefixDAO();

					if (!Validator.isEmptyString(helper.getParameter("prefix"))) {
						String prefix = helper.getParameter("prefix");

						final CourseProvider courseProvider = new CourseProvider();
						courseProvider.setUniquePrefix(prefix);
						Collection<Collection<String>> prefixCollection = prefixDAO
								.findById(courseProvider);
						if (prefixCollection.size() != 0) {
							message = SystemMessage.PREFIX_INVALID;
							validationFlag = 0;

						} else if (prefixCollection.size() == 0) {
							message = SystemMessage.PREFIX_VALID;
							validationFlag = 1;
						}

					} else {
						message = SystemMessage.EMPTY_FIELD;
						validationFlag = 0;
					}
				}

			} else {
				validationFlag = 0;
			}

			helper.setAttribute("validationFlag", validationFlag);

		} catch (SQLException exception) {
			message = SystemMessage.ERROR;
			log.error("execute method SQLException" + exception.toString());
			throw exception;
		} catch (Exception exception) {
			message = SystemMessage.ERROR;
			log.error("execute method Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("userMessage", message.message());
		}

		return view;
	}
}
