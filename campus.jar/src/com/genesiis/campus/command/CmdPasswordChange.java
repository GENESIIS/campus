package com.genesiis.campus.command;
//20170214 AS CAM-130 for Password change to created CmdPasswordChange command class.
import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpStudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.google.gson.Gson;

import org.apache.log4j.Logger;
public class CmdPasswordChange implements ICommand{
	static Logger log = Logger.getLogger(CmdPasswordChange.class.getName());
	private Student data;
	private Collection<Collection<String>> dataCollection = null;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String gsonData = helper.getParameter("jsonData");
		data = getStudentdetails(gsonData);
		log.info("swwerwerwerwerwerwervwfv werwer");
		ICrud passwordRest = new SigningUpStudentDAO();
		int result = passwordRest.update(data);
		
		
		
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
