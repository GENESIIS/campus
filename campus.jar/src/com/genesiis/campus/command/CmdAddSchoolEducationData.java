package com.genesiis.campus.command;
//20161124 PN c26-add-student-details: INIT CmdAddSchoolEducationData.java class.
//20161126 PN c26-add-student-details: implemented execute() method by providing backend validations.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.entity.SchoolEducationDAO;
import com.genesiis.campus.entity.model.SchoolEducation;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.log4j.Logger;

public class CmdAddSchoolEducationData implements ICommand {
	static Logger log = Logger.getLogger(CmdAddSchoolEducationData.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;

		// Predefined date format.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		SchoolEducation data = new SchoolEducation();
		ICrud educationDao = new SchoolEducationDAO();
		ICrud country2dao = new Country2DAO();
		Collection<Collection<String>> educationCollection = new ArrayList<Collection<String>>();
		ArrayList<String> educationData = new ArrayList<>();
		String message = "";
		
		try {
			data = gson.fromJson(helper.getParameter("jsonData"), SchoolEducation.class);
			data.setStudent(StudentCode);
			data.setCrtBy("USER");
			data.setModBy("USER");

			// Set incoming data to view collection..
			educationData.add(Integer.toString(data.getSchoolGrade()));
			educationData.add(Integer.toString(data.getMajor()));
			educationData.add(Integer.toString(data.getResult()));
			educationData.add(Integer.toString(data.getMedium()));
			educationData.add(data.getIndexNo());
			educationData.add(data.getSchoolName());
			educationData.add(String.valueOf(data.getAchievedOn()));
			educationData.add(Integer.toString(data.getCountry()));
			educationData.add(data.getDescription());
			educationCollection.add(educationData);
			view.setCollection(educationCollection);
			
			// Validate incoming data and set it into a HashMap.
			Map<String, Boolean> map = Validator.validateSchoolEduData(data);
			// Check if the given data is valid.
			boolean isValid = true;
			for (String text : map.keySet()) {
				if (!map.get(text)) {
					isValid = false;
					helper.setAttribute("schoolEduDataErrors", map);
					message = SystemMessage.INVALID_INFORMATION.message();
					break;
				}
			}

			//Only if data is valid DAO method will fire
			if (isValid) {
				Connection connection = ConnectionManager.getConnection();
				// Commit false till the updations/additions successfully
				// completed.
				connection.setAutoCommit(false);
				int rowId = educationDao.update(data, connection);
				message = SystemMessage.UPDATED.message();
				if (rowId == 0) {
					rowId = educationDao.add(data, connection);
					message = SystemMessage.ADDED.message();
				}
				// Commit if all the updations/additions successfully completed.
				connection.commit();
			}
		} catch (SQLException sqle) {
			message = SystemMessage.ERROR.message();
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			message = SystemMessage.ERROR.message();
			log.error("execute() : e" + e.toString());
			throw e;
		}
		helper.setAttribute("saveChangesStatus", message);
		return view;
	}

}
