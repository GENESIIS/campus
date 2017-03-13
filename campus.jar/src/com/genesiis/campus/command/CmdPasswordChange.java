package com.genesiis.campus.command;

//20170214 AS CAM-22 for Password change to created CmdPasswordChange command class.
//20170221 AS CAM-22 execute() method body implemented a try-catch block
//20170223 AS CAM-22 message and page string added inside try-catch block 
//20170224 AS C22 Changed imports oder. 


import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpStudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdPasswordChange implements ICommand {
	static Logger log = Logger.getLogger(CmdPasswordChange.class.getName());
	private Student data;
	private Collection<Collection<String>> dataCollection = null;

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";
		String pageURL = "/index.jsp";
		try {
			String gsonData = helper.getParameter("jsonData");
			data = getStudentdetails(gsonData);

			ICrud passwordRest = new SigningUpStudentDAO();
			int result = passwordRest.update(data);
			if (result > 0) {
				message = SystemMessage.PASSWORD_SUCCESS.message();
				pageURL = "/index.jsp?showLogin=true";
			} else {
				message = SystemMessage.PASSWORD_UNSUCCESS.message();
			}
		} catch (SQLException sexp) {
			log.error("execute(): SQLException " + sexp.toString());
			throw sexp;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
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
