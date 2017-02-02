package com.genesiis.campus.command;
//201700202 AS C22 forgot password, CmdEmailVarification command class created
import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

public class CmdEmailVarification implements ICommand {
	static Logger log = Logger.getLogger(CmdEmailVarification.class.getName());
	private Student data;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String gsonData = helper.getParameter("jsonData");
		data = getStudentdetails(gsonData);

		return null;
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
		} catch (Exception e) {
			log.error("extractFromJason(): " + e.toString());
			throw e;
		}
		return student;
	}
}
