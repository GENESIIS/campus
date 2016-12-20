package com.genesiis.campus.command;

//20161123 AS C19-student-login-without-using-third-party-application-test-as CmdStudentLogin class created.
//20161128 AS C19-student-login-without-using-third-party-application-test-as extractFromJason 
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.StudentLoginDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
import com.genesiis.campus.util.CookieHandler;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.LoginValidator;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

public class CmdStudentLogin implements ICommand {

	static Logger log = Logger.getLogger(CmdStudentLogin.class.getName());

	private Student data;
	private Collection<Collection<String>> dataCollection = null;

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = SystemMessage.LOGINUNSUCCESSFULL.message();
		String messageReturn = new String(message);
		String gsonData = helper.getParameter("jsonData");
		data = getStudentdetails(gsonData);

		String validateResult = LoginValidator.validateLogin(data);

		boolean rememberMe = data.isRemember();

		if (validateResult.equalsIgnoreCase("True")) {
			data = LoginValidator.dataSeparator(data);
			final StudentLoginDAO loginDAO = new StudentLoginDAO();
			dataCollection = loginDAO.findById(data);

			if (rememberMe == true) {
				helper.setAttribute("student", data);
				CookieHandler.addCookie(helper.getResponse(),
						"userIdendificationKey", data.getUserKey(), 2592000);

			}
			HttpSession session = helper.getSession(true);
			session.setAttribute("currentSessionUser", data.getUsername());
			session.setAttribute("user", data.getFirstName());
			session.setAttribute("userCode", data.getCode());

			setStudentLoginDetails(data, helper);
			int status = StudentLoginDAO.loginDataUpdate(data);
		} else {
			message = SystemMessage.LOGINUNSUCCESSFULL.message();

		}

		for (Collection<String> collection : dataCollection) {
			Object[] array = collection.toArray();
			message = (String) array[0];

		}

		helper.setAttribute("message", message);
		view.setCollection(dataCollection);
		helper.setRedirectPage("/dist/partials/student/student-dashboard.jsp");
		return view;
	}

	/**
	 * Student login details maintain.
	 * 
	 * @param object
	 * @param helper
	 * @return Student object
	 */

	private Student setStudentLoginDetails(Student object, IDataHelper helper) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		Date loginTime = new Date();

		java.util.Date utilDate = new java.util.Date();
		java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());

		object.setLastLoggedInDate(loginDate.toString());
		object.setLastLoggedInTime(new Timestamp(loginTime.getTime())
				.toString());

		String browser = helper.getHeader("User-Agent");
		String[] output = browser.split("/");
		object.setLastLoggedInUserAgent(output[0]);
		object.setLastLoggedInIpAddress(helper.getRemoteAddress());
		return object;
	}

	/**
	 * extract data from json object and assign to StudentProgrammeInquiry
	 * object
	 * 
	 * @author anuradha
	 * @param gsonData
	 * @return Student object
	 */

	private Student getStudentdetails(String gsonData) {
		Student student = (Student) extractFromJason(gsonData);

		return student;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		Student student = null;
		try {
			student = gson.fromJson(gsonData, Student.class);

		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return student;
	}

}
