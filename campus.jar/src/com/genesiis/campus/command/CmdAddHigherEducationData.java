package com.genesiis.campus.command;

//20161215 PN CAM-28: INIT CmdAddHigherEducationData.java class and implemented execute() method.
//20170105 PN CAM-28: edit user information: execute() method code modified with improved connection property management.
//20170110 PN CAM-28: modified execute() method to pass view to the front end with findById() DAO method.
//20170117 PN CAM-28: dao method call moved into try block.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.genesiis.campus.entity.HigherEducationDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.HigherEducation;
import com.genesiis.campus.entity.model.ProfessionalExperience;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

public class CmdAddHigherEducationData implements ICommand {
	static Logger log = Logger.getLogger(CmdAddHigherEducationData.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;

		// Predefined date format.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		HigherEducation data = new HigherEducation();
		ICrud educationDao = new HigherEducationDAO();
		Collection<Collection<String>> educationCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> stdHighEduCollection = new ArrayList<Collection<String>>();
		ArrayList<String> educationData = new ArrayList<>();
		String message = "";
		Connection connection = null;
		try {
			data = gson.fromJson(helper.getParameter("jsonData"), HigherEducation.class);
			data.setStudent(StudentCode);
			data.setCrtBy("USER");
			data.setModBy("USER");

			// Set incoming data to view collection.
			educationData.add(data.getInstitute());
			educationData.add(data.getAffiliatedInstitute());
			educationData.add(Integer.toString(data.getMajor()));
			educationData.add(Integer.toString(data.getAward()));
			educationData.add(data.getStudentId());
			educationData.add(data.getResult());
			educationData.add(String.valueOf(data.getCommencedOn()));
			educationData.add(String.valueOf(data.getCompletedOn()));
			educationData.add(Integer.toString(data.getMedium()));
			educationData.add(Integer.toString(data.getCountry()));
			educationData.add(data.getDescription());
			educationCollection.add(educationData);
			//view.setCollection(educationCollection);

			// Validate incoming data and set it into a HashMap.
			Map<String, Boolean> map = Validator.validaHighereducationData(data);
			// Check if the given data is valid.
			boolean isValid = true;
			for (String text : map.keySet()) {
				if (!map.get(text)) {
					isValid = false;
					helper.setAttribute("higherEduErrors", map);
					message = SystemMessage.INVALID_INFORMATION.message();
					break;
				}
			}

			// Only if data is valid DAO method will fire
			if (isValid) {
				connection = ConnectionManager.getConnection();
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
			stdHighEduCollection = educationDao.findById(StudentCode);
		} catch (SQLException sqle) {
			connection.rollback();
			message = SystemMessage.ERROR.message();
			log.error("execute() : sqle" + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			connection.rollback();
			message = SystemMessage.ERROR.message();
			log.error("execute() : e" + e.toString());
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		view.setCollection(stdHighEduCollection);
		helper.setAttribute("saveChangesHigherEduStatus", message);
		return view;
	}

}
