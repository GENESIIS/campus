package com.genesiis.campus.command;

//20161207 PN c26-add-student-details INIT CmdAddStudentInterestDetails.java. Implemented execute() method.
//		   PN c26-add-student-details: modified execute() method by adding status messages.
//20161228 CAM-26: removed unwanted logger statements. added helper.getParameter() code lines into try block.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentInterestDAO;
import com.genesiis.campus.entity.model.StudentInterest;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

public class CmdAddStudentInterestDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdAddStudentInterestDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// This needs to be assign from the session.
		int StudentCode = 1;
		String message = "";

		ICrud interestDao = new StudentInterestDAO();
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			String[] oldStudentInterest = helper.getParameter("oldStudentInterest").split(",");
			String[] newStudentInterest = helper.getParameter("newStudentInterest").split(",");
			
			// Commit false till the updations/additions successfully
			// completed.
			connection.setAutoCommit(false);

			if (oldStudentInterest.length > newStudentInterest.length) {
				List diff = Validator.subtract(Arrays.asList(oldStudentInterest), Arrays.asList(newStudentInterest));
				for (Object object : diff) {
					StudentInterest interest = new StudentInterest();
					interest.setStudent(StudentCode);
					interest.setInterest(Integer.parseInt(object.toString()));
					interestDao.delete(interest, connection);
				}
			} else if (oldStudentInterest.length < newStudentInterest.length) {
				List diff = Validator.subtract(Arrays.asList(newStudentInterest), Arrays.asList(oldStudentInterest));
				for (Object object : diff) {
					StudentInterest interest = new StudentInterest();
					interest.setStudent(StudentCode);
					interest.setInterest(Integer.parseInt(object.toString()));
					interest.setCrtBy("USER");
					interestDao.add(interest, connection);
				}
			} else {
				List diff = Validator.subtract(Arrays.asList(oldStudentInterest), Arrays.asList(newStudentInterest));
				for (Object object : diff) {
					StudentInterest interest = new StudentInterest();
					interest.setStudent(StudentCode);
					interest.setInterest(Integer.parseInt(object.toString()));
					interestDao.delete(interest, connection);
				}

				List diff1 = Validator.subtract(Arrays.asList(newStudentInterest), Arrays.asList(oldStudentInterest));
				for (Object object : diff1) {
					StudentInterest interest = new StudentInterest();
					interest.setStudent(StudentCode);
					interest.setInterest(Integer.parseInt(object.toString()));
					interest.setCrtBy("USER");
					interestDao.add(interest, connection);
				}
			}
			message = SystemMessage.SUCCESS.message();
			// Commit if all the updations/additions successfully completed.
			connection.commit();

			Collection<Collection<String>> studentInterestCollection = interestDao.findById(StudentCode);
			view.setCollection(studentInterestCollection);
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
		helper.setAttribute("InterestChangesStatus", message);
		return view;
	}
}