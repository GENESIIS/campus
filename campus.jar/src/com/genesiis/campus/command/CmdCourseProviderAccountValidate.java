package com.genesiis.campus.command;

//20161208 JH c39-add-course-provider CmdAddCourseProviderAccount.java created
//20161209 JH c39-add-course-provider class name renamed to CmdCourseProviderAccountValidate
//20161222 JH c39-add-course-provider code modifications for Username validation 
//20161223 JH c39-add-course-provider code modified

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderUsernameDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.UserType;

import org.apache.log4j.Logger;

public class CmdCourseProviderAccountValidate implements ICommand {
	static org.apache.log4j.Logger log = Logger
			.getLogger(CmdCourseProviderAccountValidate.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		final String action;
		SystemMessage message = null;
		Validator validator = new Validator();

		if (!validator.isEmpty(helper.getParameter("action"))) {

			action = helper.getParameter("action");

			if (action.equalsIgnoreCase("COURSE_PROVIDER_USERNAME_VALIDATION")) {
			
				ICrud usernameDAO = new CourseProviderUsernameDAO();
				ICrud userTypeDAO = new UserTypeDAO();
				String username = helper.getParameter("username");
				String email = helper.getParameter("");
				final CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
				//courseProviderAccount.setUserType(Integer.parseInt(userType));
				courseProviderAccount.setUsername(username);
				/*
				 *have to set user type 
				 *check whether we can check for user status 
				 *add dao class 
				 */
				//courseProviderAccount.setUserType(UserType.STUDENT.getUserType);
				Collection<Collection<String>> usernameCollection = usernameDAO.findById(courseProviderAccount);
				if(usernameCollection != null){
					helper.setAttribute("users", usernameCollection);
					log.info("jsdkfkdsjfdfkdskj")	;
				}else{
					helper.setAttribute("users", usernameCollection);
				}
				
			}

			// if (action.equalsIgnoreCase("USERNAME_VALIDATION")) {// used when
			// // validating
			// // a
			// // username
			// ICrud usernameDAO = new CourseProviderUsernameDAO();
			// String username = helper.getParameter("username");
			//
			// final Collection<Collection<String>> userAccountCollectin =
			// usernameDAO
			// .findById(username);
			// if (userAccountCollectin.size() > 0) {// username exist
			// message = SystemMessage.USERNAME_INVALID;
			// } else {
			// message = SystemMessage.USERNAME_VALID;
			// }
			//
			// } else if (action.equalsIgnoreCase("PREFIX_VALIDATION")) {// used
			// // when
			// // validating
			// // prefix
			//
			// }
		} else {
			message = SystemMessage.EMPTY_USERNAME;
		}

		helper.setAttribute("message", message.message());
		return view;
	}
}
