package com.genesiis.campus.command;

//20161123 AS C19-student-login-without-using-third-party-application-test-as CmdStudentLogin class created.
//20161128 AS C19-student-login-without-using-third-party-application-test-as extractFromJason 
import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.StudentLoginDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.LoginValidator;
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
		String message = "Unsuccessfull";

		String gsonData = helper.getParameter("jsonData");
		data = getStudentdetails(gsonData);

		log.info("testing Data ................ : EMAIL " + data.getUserKey() +"  Password  : "+data.getPassword());

	String validateResult = LoginValidator.validateLogin(data);
	//	log.info(validateResult);
		
		
		if (validateResult.equalsIgnoreCase("True")) {
		//	log.info(validateResult);
			data = LoginValidator.dataSeparator(data);
			log.info("Student user key: " +data.getUserKey());
			log.info("Student Assigen Email : " +data.getEmail());
			log.info("Student username : " +data.getUsername());
			final StudentLoginDAO loginDAO = new StudentLoginDAO();
			 dataCollection = loginDAO.findById(data);
		}

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
