package com.genesiis.campus.entity;
//20170202 AS C22 StudentEmailVerificationDAO class created
//20170213 AS C22 Student Generated Hash code update in update method.
//20170224 AS C22 Changed imports oder. 
//20170307 AS C22 verifyHashCode method condition change to nested if, and expiration time modified 

import com.genesiis.campus.command.CmdEmailVarification;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class StudentEmailVerificationDAO implements ICrud {
	static Logger log = Logger.getLogger(StudentEmailVerificationDAO.class
			.getName());

	@Override
	public int update(Object object) throws SQLException, Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.STUDENT SET  HASHCODE=?, MODON=?, HASHGENTIME=?  WHERE CODE=? ";
		PreparedStatement ps = null;
		int rowInserted = -1;
		try {
			Student student = (Student) object;
			conn = ConnectionManager.getConnection();
			//modification date 
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date modDate = new java.sql.Date(utilDate.getTime());
			//Hashcode generated timestamp
			long time = System.currentTimeMillis();
			java.sql.Timestamp hashGenDate = new java.sql.Timestamp(time);
			
			ps = conn.prepareStatement(query);
			ps.setString(1, student.getHashCode());
			ps.setDate(2, modDate);
			ps.setTimestamp(3, hashGenDate);
			ps.setInt(4, student.getCode());
			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}
		} catch (SQLException e) {
			log.info("update(): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.info("update(): Exception" + ex.toString());
			throw ex;
		} finally {

			if (ps != null) {
				ps.close();
			}

			if (conn != null) {
				conn.close();
			}

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

	//email verification 
	@Override
	public Collection<Collection<String>> findById(Object data)
			throws SQLException, Exception {

		Collection<Collection<String>> emailCollection = new ArrayList<Collection<String>>();

		ArrayList<String> singleStudent = null;
		Collection<String> singleStudentCollection = null;

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "SELECT USERNAME, FIRSTNAME, LASTNAME, EMAIL, CODE FROM CAMPUS.STUDENT WHERE EMAIL =? AND ISACTIVE = ? ";
		String message = SystemMessage.INVALID_EMAIL.message();
		try {
			final Student student = (Student) data;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getEmail());
			preparedStatement.setString(2, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				singleStudent = new ArrayList<String>();
				singleStudentCollection = singleStudent;

				singleStudent.add(rs.getString("FIRSTNAME"));
				singleStudent.add(rs.getString("LASTNAME"));
				singleStudent.add(rs.getString("EMAIL"));
				singleStudent.add(rs.getString("USERNAME"));
				singleStudent.add(rs.getString("CODE"));
			} else {
				message = SystemMessage.INVALID_EMAIL.message();
				singleStudent = new ArrayList<String>();
				singleStudentCollection = singleStudent;
				singleStudent.add(message);

			}
		} catch (SQLException exception) {
			log.error("findById(Object data):  SQLexception"
					+ exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("findById(Object data):  exception"
					+ exception.toString());
			throw exception;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

		emailCollection.add(singleStudentCollection);
		return emailCollection;
	}

	
	/**
	 * to verify hash code and user entered 
	 * if verification code only valid 30 min, if it is more than 30 min, fire the error message. 
	 * @param Student Object
	 * @return Collection<Collection<String>> 
	 * @throws SQLException
	 * @throws Exception
	 */
	public Collection<Collection<String>> verifyHashCode(Object data)
			throws SQLException, Exception {
		Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();

		ArrayList<String> singleStudent = null;
		Collection<String> singleStudentCollection = null;
		String query = "SELECT USERNAME, FIRSTNAME, LASTNAME, EMAIL, CODE, HASHGENTIME, DATEDIFF(MINUTE ,HASHGENTIME , SYSDATETIME()) AS MinuteDiff FROM CAMPUS.STUDENT WHERE EMAIL =? AND HASHCODE =? AND ISACTIVE = ?";
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String message = SystemMessage.VALIDHASHCODE.message();
		int minitDiff = 0;
		try {
			final Student student = (Student) data;
			conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, student.getEmail());
			preparedStatement.setString(2, student.getHashCode());
			preparedStatement.setString(3, Integer.toString(ApplicationStatus.ACTIVE.getStatusValue()));

			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				
				minitDiff = rs.getInt("MinuteDiff");
				if(minitDiff <= 30){
					singleStudent = new ArrayList<String>();
					singleStudentCollection = singleStudent;

					singleStudent.add(rs.getString("FIRSTNAME"));
					singleStudent.add(rs.getString("LASTNAME"));
					singleStudent.add(rs.getString("EMAIL"));
					singleStudent.add(rs.getString("USERNAME"));
					singleStudent.add(rs.getString("CODE"));
					
				}else{
					message = SystemMessage.VERIFICATION_CODEEXPIRED.message();
					singleStudent = new ArrayList<String>();
					singleStudentCollection = singleStudent;
					singleStudent.add(message);
				}
			} else {
				message = SystemMessage.INVALID_HASHCODE.message();
				singleStudent = new ArrayList<String>();
				singleStudentCollection = singleStudent;
				singleStudent.add(message);
			}
			
		} catch (SQLException exception) {
			log.error("verifyHashCode(Object data):  SQLexception"
					+ exception.toString());
			throw exception;
		} catch (Exception exception) {
			log.error("verifyHashCode(Object data):  exception"
					+ exception.toString());
			throw exception;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		dataCollection.add(singleStudentCollection);
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
