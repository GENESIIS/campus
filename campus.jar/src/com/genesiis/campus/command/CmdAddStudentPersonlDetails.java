package com.genesiis.campus.command;

//20161204 PN c26-add-student-details: INIT CmdAddStudentPersonlDetails.java class.
//20161205 PN c26-add-student-details: implementing execute() method.
//20170105 PN CAM-28: edit user information: execute() method code modified with improved connection property management.
//20170117 PN CAM-28: dao method call moved into try block.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.sql.Connection;
import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CmdAddStudentPersonlDetails implements ICommand {
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
		Collection<Collection<String>> studentDetails = new ArrayList<Collection<String>>();
		ArrayList<String> studentData = new ArrayList<>();
		String message = "";
		Connection connection = null;

		try {
			data = gson.fromJson(helper.getParameter("jsonData"), Student.class);
			data.setCode(StudentCode);
			data.setCrtBy("USER");
			data.setModBy("USER");

			// Set incoming data to view collection.
			studentData.add(data.getFirstName());
			studentData.add(data.getMiddleName());
			studentData.add(data.getLastName());
			studentData.add(String.valueOf(data.getDateOfBirth()));
			studentData.add(data.getDescription());
			studentData.add(data.getMobilePhoneNo());
			studentData.add(data.getLandPhoneNo());
			studentData.add(data.getAddress1());
			studentData.add(data.getTown());
			studentData.add(data.getEmail());
			studentData.add(data.getFacebookUrl());
			studentData.add(data.getTwitterUrl());
			studentData.add(data.getLinkedInUrl());
			studentData.add(data.getInstagramUrl());
			studentData.add(data.getMySpaceUrl());
			studentData.add(data.getWhatsAppNumber());
			studentData.add(data.getViberNumber());
			studentData.add(Integer.toString(data.getGender()));
			studentData.add(data.getLandPhoneCountryCode());
			studentDataCollection.add(studentData);
			view.setCollection(studentDataCollection);

			// Validate incoming data and set it into a HashMap.
			Map<String, Boolean> map = Validator.validaPersonalData(data);
			// Check if the given data is valid.
			boolean isValid = true;
			for (String text : map.keySet()) {
				if (!map.get(text)) {
					isValid = false;
					helper.setAttribute("studentPersonalStatus", map);
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
				int rowId = studentDao.update(data, connection);
				message = SystemMessage.UPDATED.message();
				if (rowId == 0) {
					rowId = studentDao.add(data, connection);
					message = SystemMessage.ADDED.message();
				}
				// Commit if all the updations/additions successfully completed.
				connection.commit();
			}
			studentDetails = studentDao.findById(data);
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
		helper.setAttribute("studentPersonalStatus", message);
		return view;
	}

}
