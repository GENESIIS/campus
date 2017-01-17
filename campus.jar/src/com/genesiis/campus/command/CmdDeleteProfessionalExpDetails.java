package com.genesiis.campus.command;

//20161220 PN CAM-28: INIT CmdDeleteProfessionalExpDetails.java class and implemented execute method.
//20170105 PN CAM-28: edit user information: execute() method code modified with improved connection property management.
//20170110 PN CAM-28: modified execute() method to pass view to the front end with findById() DAO method.
//20170117 PN CAM-28: dao method call moved into try block. method comments changed.

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

public class CmdDeleteProfessionalExpDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdDeleteProfessionalExpDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;

		// Predefined date format.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		ICrud expDao = new ProfessionalExperienceDAO();
		Connection connection = null;
		String message = "";
		Collection<Collection<String>> expCollection = new ArrayList<Collection<String>>();
		ArrayList<String> expData = new ArrayList<>();

		try {
			// Set connection from here to pass it to all the queries.
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			int rowCount = 0;

			String rows[] = helper.getParameterValues("rows[]");

			if ((rows != null) && (rows.length > 0)) {
				for (int i = 0; i < rows.length; i++) {
					ProfessionalExperience data = new ProfessionalExperience();
					data.setCode(Integer.parseInt(rows[i]));

					int rowId = expDao.delete(data, connection);
					if (rowId > 0) {
						rowCount++;
					}
				}

				// Commit if all the deletions successfully completed.
				connection.commit();

				if (rowCount == rows.length) {
					message = SystemMessage.DELETED.message();
				} else if (rowCount == 0) {
					message = SystemMessage.NODETAILSTODELETE.message();
				} else {
					message = (rows.length - rowCount) + " " + SystemMessage.FAILDTOUPDATE.message();
				}
			} else {
				message = SystemMessage.NODETAILSTODELETE.message();
			}
		expCollection = expDao.findById(StudentCode);
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
		view.setCollection(expCollection);
		helper.setAttribute("pesaveChangesStatus", message);
		return view;
	}

}
