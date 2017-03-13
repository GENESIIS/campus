package com.genesiis.campus.entity;

//20170311 CW C147 TutorEmailVerificationDAO class created
//20170312 CW C147 modified findById method & removed some commented lines
//20170313 CW C147 modified update method & removed comment on executeUpdate method calling

import com.genesiis.campus.command.CmdTutorEmailVerification;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 * This class is used to manage the data related to tutor email verification
 * Further this class implements ICrud interface
 * @author CHINTHAKA
 */
public class TutorEmailVerificationDAO implements ICrud {
	static Logger log = Logger.getLogger(TutorEmailVerificationDAO.class.getName());

	/**
	 * updates the HASHCODE, MODON, HASHGENTIME of the tutor in the database
	 * @author CHINTHAKA
	 * @param Tutor Object
	 * @return 1 if successful & 0 if unsuccessful update
	 * @throws SQLException, Exception 
	 */
	@Override
	public int update(Object object) throws SQLException, Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.TUTOR SET HASHCODE=?, MODON=?, HASHGENTIME=?  WHERE CODE=? ";
		PreparedStatement ps = null;
		int rowInserted = -1;
		try {
			Tutor tutor = (Tutor) object;
			conn = ConnectionManager.getConnection();
			//modification date 
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date modDate = new java.sql.Date(utilDate.getTime());
			//Hashcode generated timestamp
			long time = System.currentTimeMillis();
			java.sql.Timestamp hashGenDate = new java.sql.Timestamp(time);
			
			ps = conn.prepareStatement(query);
			ps.setString(1, tutor.getHashCode());
			ps.setDate(2, modDate);
			ps.setTimestamp(3, hashGenDate);
			ps.setInt(4, tutor.getCode());
			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}
		} catch (SQLException sqle) {
			log.error("update(): SQLexception" + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("update(): Exception" + ex.toString());
			throw ex;
		} finally {
			DaoHelper.cleanup(conn, ps, null);
		}
		
		return rowInserted;
	}

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * to get the relevant details to send the hashcode to the tutor
	 * @author CHINTHAKA
	 * @param Tutor Object
	 * @return Collection<Collection<String>> 
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public Collection<Collection<String>> findById(Object data)
			throws SQLException, Exception {

		Collection<Collection<String>> emailCollection = new ArrayList<Collection<String>>();

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "SELECT USERNAME, FIRSTNAME, LASTNAME, EMAIL, CODE FROM CAMPUS.TUTOR WHERE EMAIL =? AND ISAPPROVED = ? ";
		try {
			final Tutor tutor = (Tutor) data;
			ArrayList<String> singleTutor = new ArrayList<String>();
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, tutor.getEmailAddress());
			preparedStatement.setString(2, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				singleTutor.add(rs.getString("FIRSTNAME"));
				singleTutor.add(rs.getString("LASTNAME"));
				singleTutor.add(rs.getString("EMAIL"));
				singleTutor.add(rs.getString("USERNAME"));
				singleTutor.add(rs.getString("CODE"));
				emailCollection.add(singleTutor);
			}
		} catch (SQLException sqle) {
			log.error("findById(Object data): SQLException "+ sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("findById(Object data): Exception "+ ex.toString());
			throw ex;
		} finally {
			DaoHelper.cleanup(conn, preparedStatement, rs);
		}
		return emailCollection;
	}

	
	/**
	 * to get the relevant details to verify the tutor entered hashcode
	 * @author CHINTHAKA
	 * @param Tutor Object
	 * @return Collection<Collection<String>> 
	 * @throws SQLException
	 * @throws Exception
	 */
	public Collection<Collection<String>> verifyHashCode(Object data)
			throws SQLException, Exception {
		Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();

		String query = "SELECT USERNAME, FIRSTNAME, LASTNAME, EMAIL, CODE, HASHGENTIME, DATEDIFF(MINUTE ,HASHGENTIME , SYSDATETIME()) AS MinuteDiff FROM CAMPUS.TUTOR WHERE EMAIL =? AND HASHCODE =? AND ISACTIVE = ?";
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int minitDiff = 0;
		try {
			final Tutor tutor = (Tutor) data;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, tutor.getEmailAddress());
			preparedStatement.setString(2, tutor.getHashCode());
			preparedStatement.setString(3, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
					ArrayList<String> singleTutor = new ArrayList<String>();
				//minitDiff = rs.getInt("MinuteDiff");
				//if(minitDiff <= 30){
					singleTutor = new ArrayList<String>();
					//singleStudentCollection = singleStudent;

					singleTutor.add(rs.getString("FIRSTNAME"));
					singleTutor.add(rs.getString("LASTNAME"));
					singleTutor.add(rs.getString("EMAIL"));
					singleTutor.add(rs.getString("USERNAME"));
					singleTutor.add(rs.getString("CODE"));
					dataCollection.add(singleTutor);
				/*}else{
					message = SystemMessage.VERIFICATION_CODEEXPIRED.message();
					singleStudent = new ArrayList<String>();
					singleStudentCollection = singleStudent;
					singleStudent.add(message);
				}*/
			} /*else {
				message = SystemMessage.INVALID_HASHCODE.message();
				singleStudent = new ArrayList<String>();
				singleStudentCollection = singleStudent;
				singleStudent.add(message);
			}*/
			
		} catch (SQLException sqle) {
			log.error("verifyHashCode(Object data):  SQLException"
					+ sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("verifyHashCode(Object data):  Exception"
					+ e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, preparedStatement, rs);
		}
		return dataCollection;
	}
	
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
