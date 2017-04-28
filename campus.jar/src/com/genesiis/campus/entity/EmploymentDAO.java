package com.genesiis.campus.entity;

// 20170327 c157-add-tutor-employment-details-cw - INIT EmploymentDAO.java & Create Add method
// 20170330 CW c157-add-tutor-employment-details-cw create deleteMultiple method
// 20170331 CW c157-add-tutor-employment-details-cw add method comments into add method
// 20170404 CW c157-add-tutor-employment-details-cw modified doc comments
// 20170427 CW c159-courseprovider-accept-tutor-request-cw add update method to do a batch update in the status
// 20170427 CW c159-courseprovider-accept-tutor-request-cw completed update(Object object) method implementations
						//modify verificationstatus to confirmationStatus
// 20170428 CW c159-courseprovider-accept-tutor-request-cw modified the query in update(Object object) method

import com.genesiis.campus.command.CmdAddTutorEmploymentDetails;
import com.genesiis.campus.entity.model.Employment;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * this class used to manage all the employment details with the database. 
 * further it implements ICrud interface
 * @author CW
 */
public class EmploymentDAO implements ICrud {

	static Logger log = Logger.getLogger(EmploymentDAO.class.getName());
	
	/**
	 * Save employment details in Database
	 * 
	 * @author Chinthaka
	 * @param object : Employment object of Object type
	 * @return int number of success/fail status
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int status = -1;
		
		String query = "INSERT INTO [CAMPUS].[EMPLOYMENT] (VARIFICATIONSTATUS, CRTON, CRTBY, MODON, MODBY, TUTOR, COURSEPROVIDER )"
				+ "VALUES (?,GETDATE(),?,GETDATE(),?,?,?); ";
						
		try {			
			final Employment employmentData = (Employment) object;
			conn = ConnectionManager.getConnection();			
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, employmentData.getConfirmationStatus()); // VARIFICATIONSTATUS
			stmt.setString(2, employmentData.getCrtby()); // CRTBY
			stmt.setString(3, employmentData.getModby()); // MODBY
			stmt.setInt(4, employmentData.getTutor()); // TUTOR
			stmt.setInt(5, employmentData.getCourseprovider()); // COURSEPROVIDER
			status = stmt.executeUpdate();

		} catch (ClassCastException cce) {
			log.error("add(): ClassCastException " + cce.toString());
			throw cce;
		} catch (SQLException exception) {
			log.error("add(): SQLException " + exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("add(): Exception " + exception.toString());
			throw exception;
		} finally {
			DaoHelper.cleanup(conn, stmt, null);
		}

		return status;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		
		PreparedStatement ps = null;
		Connection con = null; 
		int totalNumOfRecordsAffected = 0;
		
		try {			
			List<Employment> employmentStatusChangeCollection = (ArrayList<Employment>) object; 
			
			String query = "UPDATE [campus].[EMPLOYMENT] SET CONFIRMATIONSTATUS = ?, MODBY = ?, MODON = GETDATE() WHERE CODE = ?";
			
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(query);
						
			for(Employment emp : employmentStatusChangeCollection) {
				
				ps.setInt(1, emp.getConfirmationStatus());
				ps.setString(2, emp.getModby());			
				//ps.setDate(3, emp.getModon());			
				ps.setInt(3, emp.getCode());
				
			    ps.addBatch();
			}			
			
			 int[] affectedRecords = ps.executeBatch();	
			 
			 for (int numOfRecordsAffected : affectedRecords) {
				 totalNumOfRecordsAffected += numOfRecordsAffected;
			 }
			
		} catch (SQLException sqle) {
			log.info("update(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.info("update(Object): Exception:" + ex.toString());
			throw ex;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}	
		
		return totalNumOfRecordsAffected;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Delete selected employment details in Database
	 * 
	 * @author CW
	 * @param String : allSelectedToRemove All the comma separated code values of selected rows in EMPLOYMENT table
	 * @return int number of success/fail status
	 */
	public int deleteMultiple(String allSelectedToRemove) throws SQLException, Exception {

		Connection conn = null;
		PreparedStatement stmt = null;
		int status = -1;
		
		String query = "DELETE FROM CAMPUS.EMPLOYMENT WHERE CODE IN (" + allSelectedToRemove + ")";
						
		try {			
			conn = ConnectionManager.getConnection();						
			stmt = conn.prepareStatement(query);
			status = stmt.executeUpdate();

		} catch (SQLException exception) {
			log.error("deleteMultiple(): SQLException " + exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("deleteMultiple(): Exception " + exception.toString());
			throw exception;
		} finally {
			DaoHelper.cleanup(conn, stmt, null);
		}

		return status;
	}

}
