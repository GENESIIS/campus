package com.genesiis.campus.command;

//20161129 PN c26-add-student-details: INIT CmdAddProfessionalExpDetails.java class.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProfessionalExperienceDAO;
import com.genesiis.campus.entity.model.ProfessionalExperience;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.log4j.Logger;

public class CmdAddProfessionalExpDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdAddProfessionalExpDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;

		// Predefined date format.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		ProfessionalExperience data = new ProfessionalExperience();
		ICrud expDao = new ProfessionalExperienceDAO();
		Collection<Collection<String>> expCollection = new ArrayList<Collection<String>>();
		ArrayList<String> expData = new ArrayList<>();
		String message = "";
		Connection connection = ConnectionManager.getConnection();
		try {
			data = gson.fromJson(helper.getParameter("jsonData"), ProfessionalExperience.class);
			data.setStudent(StudentCode);
			data.setCrtBy("USER");
			data.setModBy("USER");

			// Set incoming data to view collection.
			expData.add(Integer.toString(data.getIndustry()));
			expData.add(Integer.toString(data.getJobCategoty()));
			expData.add(data.getDesignation());
			expData.add(data.getOrganization());
			expData.add(String.valueOf(data.getCommencedOn()));
			expData.add(String.valueOf(data.getCompletionOn()));
			expData.add(data.getDescription());
			expCollection.add(expData);
			view.setCollection(expCollection);

			// Validate incoming data and set it into a HashMap.
			Map<String, Boolean> map = Validator.validaProfExpData(data);
			// Check if the given data is valid.
			boolean isValid = true;
			for (String text : map.keySet()) {
				if (!map.get(text)) {
					isValid = false;
					helper.setAttribute("profExpErrors", map);
					message = SystemMessage.INVALID_INFORMATION.message();
					break;
				}
			}

			// Only if data is valid DAO method will fire
			if (isValid) {
				// Commit false till the updations/additions successfully
				// completed.
				connection.setAutoCommit(false);
				int rowId = expDao.update(data, connection);
				message = SystemMessage.UPDATED.message();
				if (rowId == 0) {
					rowId = expDao.add(data, connection);
					message = SystemMessage.ADDED.message();
				}
				// Commit if all the updations/additions successfully completed.
				connection.commit();
			}
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
		helper.setAttribute("pesaveChangesStatus", message);
		return view;
	}

}
