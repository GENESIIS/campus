package com.genesiis.campus.command;

//20161123 AS C19-student-login-without-using-third-party-application-test-as CmdStudentLogin class created.
//20161128 AS C19-student-login-without-using-third-party-application-test-as extractFromJason 
//20170227 AS C22-checking Session null condition removed
import com.genesiis.campus.entity.ICrud;
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

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CmdStudentLogin implements ICommand {

	static Logger log = Logger.getLogger(CmdStudentLogin.class.getName());

	private Student data;
	private Collection<Collection<String>> dataCollection = null;
	// HttpSession session;
	String pageURL;
	String message;

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try {


				int attempts = 0;
				pageURL = "/dist/partials/login.jsp";
				message = SystemMessage.LOGINUNSUCCESSFULL.message();

				String gsonData = helper.getParameter("jsonData");
				data = getStudentdetails(gsonData);

				String validateResult = LoginValidator.validateLogin(data);

				boolean rememberMe = data.isRemember();

				if (validateResult.equalsIgnoreCase("True")) {
					data = LoginValidator.dataSeparator(data);
					
					ICrud loginDAO = new StudentLoginDAO();
					dataCollection = loginDAO.findById(data);

					for (Collection<String> collection : dataCollection) {
						Object[] array = collection.toArray();
						message = (String) array[0];

					}

					if (message.equalsIgnoreCase(SystemMessage.VALIDUSER
							.message())) {

						if (rememberMe == true) {
							helper.setAttribute("student", data);
							CookieHandler.addCookie(helper.getResponse(),
									"userIdendificationKey", data.getUserKey(),
									2592000);
						}

						if (data.getLastLoggedInSessionid()
								.equalsIgnoreCase("")) {
							pageURL = "/dist/partials/student/ManageStudentDetails.jsp";
						} else {
							pageURL = "/dist/partials/student/student-dashboard.jsp";
						}

					HttpSession	session = helper.getSession(true);
						String sessionID = session.getId();
						log.info("JSESSIONID = "+sessionID);
						data.setLastLoggedInSessionid(sessionID);
						session.setAttribute("currentSessionUser",
								data.getUsername());
						session.setAttribute("user", data.getFirstName());
						session.setAttribute("userCode", data.getCode());
						session.setAttribute("currentUserData", dataCollection);
						setStudentLoginDetails(data, helper);
						int status = StudentLoginDAO.loginDataUpdate(data);

						if (status > 0) {
							message = SystemMessage.VALIDUSER.message();
						} else {

						}
					} else {
						// login attempts handle in here
						// after 3 attempts session will blocked user
					}

				} else {
					message = SystemMessage.LOGINUNSUCCESSFULL.message();

				}
		
			helper.setAttribute("message", message);
			helper.setAttribute("pageURL", pageURL);
			view.setCollection(dataCollection);

		} catch (SQLException e) {
			log.error("execute(IDataHelper helper, IView view):  SQLException"
					+ e.toString());
			throw e;
		} catch (Exception e) {
			log.error("execute(IDataHelper helper, IView view):  Exception"
					+ e.toString());
			throw e;
		}
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

		try {

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
		} catch (Exception e) {
			log.error("setStudentLoginDetails(Student object, IDataHelper helper):  Exception"
					+ e.toString());
			throw e;
		}
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
