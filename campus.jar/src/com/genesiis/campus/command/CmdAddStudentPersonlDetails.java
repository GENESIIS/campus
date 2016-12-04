package com.genesiis.campus.command;

//20161204 PN c26-add-student-details: INIT CmdAddStudentPersonlDetails.java class.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.entity.model.ProfessionalExperience;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CmdAddStudentPersonlDetails implements ICommand{
	static Logger log = Logger.getLogger(CmdAddStudentPersonlDetails.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;

		// Predefined date format.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
		Student data = new Student();
		ICrud studentDao = new StudentDAO();
		Collection<Collection<String>> studentDataCollection = new ArrayList<Collection<String>>();
		ArrayList<String> studentData = new ArrayList<>();
		String message = "";
		
		
		
		try {
			data = gson.fromJson(helper.getParameter("jsonData"), Student.class);
			data.setCode(StudentCode);
			data.setCrtBy("USER");
			data.setModBy("USER");
//		} catch (SQLException sqle) {
//			message = SystemMessage.ERROR.message();
//			log.info("execute() : sqle" + sqle.toString());
//			throw sqle;
		}catch (Exception e) {
			message = SystemMessage.ERROR.message();
			log.info("execute() : e" + e.toString());
			throw e;
		}
				
		return null;
	}

}
