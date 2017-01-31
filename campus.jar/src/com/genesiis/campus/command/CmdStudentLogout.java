package com.genesiis.campus.command;

//20170118 AS CAM-21 CmdStudentLogout command class created. 
//20170130 AS CAM-21 code review modification done. 
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentLoginDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class CmdStudentLogout implements ICommand {
	static Logger log = Logger.getLogger(CmdStudentLogout.class.getName());

	private Student loggedStudent;

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = SystemMessage.LOGOUTUNSUCCESSFULL.message();
		String pageURL = "/index.jsp";
		try {
			String gsonData = helper.getParameter("jsonData");
			loggedStudent = getStudentdetails(gsonData);

			Date loginTime = new Date();

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date loginDate = new java.sql.Date(utilDate.getTime());

			loggedStudent.setLastLoggedOutDate(loginDate.toString());
			loggedStudent.setLastLoggedOutTime(new Timestamp(loginTime
					.getTime()).toString());

			int status = StudentLoginDAO.logoutDataUpdate(loggedStudent);

			if (status > 0) {
				Cookie[] cookies = helper.getRequest().getCookies();
		    	if(cookies != null){
		    	for(Cookie cookie : cookies){
		    		if(cookie.getName().equals("JSESSIONID")){
		    			System.out.println("JSESSIONID="+cookie.getValue());
		    			break;
		    		}
		    	}
		    	}
				
				
				HttpSession curentSession = helper.getRequest().getSession(false);
				
				if(curentSession == null){
					curentSession.removeAttribute("user");
					curentSession.removeAttribute("userCode");
					curentSession.removeAttribute("currentUserData");
				curentSession.invalidate();

				message = SystemMessage.LOGOUTSUCCESSFULL.message();
				}
			} else {
				message = SystemMessage.LOGOUTUNSUCCESSFULL.message();
			}

		} catch (Exception e) {
			log.error("CmdStudentLogout():  Exception" + e.toString());
			throw e;
		}
		helper.setAttribute("message", message);
		helper.setAttribute("pageURL", pageURL);
		return view;
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
