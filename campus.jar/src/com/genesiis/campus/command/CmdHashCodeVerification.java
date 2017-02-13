package com.genesiis.campus.command;
//201700209 AS C22 forgot password, CmdHashCodeVerification command class created
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentEmailVerificationDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.google.gson.Gson;

public class CmdHashCodeVerification implements ICommand{
	static Logger log = Logger.getLogger(CmdHashCodeVerification.class.getName());
	private Student data;
	private Collection<Collection<String>> dataCollection = null;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		String gsonData = helper.getParameter("jsonData");
		data = getStudentdetails(gsonData);
		StudentEmailVerificationDAO studentEmailvarification = new StudentEmailVerificationDAO();
		dataCollection = studentEmailvarification.verifyHashCode(data);
		
		
		
		return view;
	}
	
	
	/**
	 * extract data from json object and assign to Student object
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

		} catch (Exception e) {
			log.error("extractFromJason(): " + e.toString());
			throw e;
		}
		return student;
	}

}
