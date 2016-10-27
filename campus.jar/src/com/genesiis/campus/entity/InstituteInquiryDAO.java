package com.genesiis.campus.entity;

//20161027 CM c9-make-inquiry-for-institute INIT InstituteInquiryDAO.java
//20161027 CM c9-make-inquiry-for-institute Modified add() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.InstituteInquiry;
import com.genesiis.campus.util.ConnectionManager;

public class InstituteInquiryDAO implements ICrud {

	static Logger log = Logger.getLogger(InstituteInquiryDAO.class.getName());
	@Override
	public int add(Object object) throws SQLException, Exception {
		

			String query = "INSERT INTO [CAMPUS].[StudentCourseProviderInquiry] (STUDENTNAME, STUDENTEMAIL, TELEPHONECOUNTRYCODE, "
					+ "TELEPHONEAREACODE, TELEPHONENUM, INQUIRYTITLE, INQUIRYTEXT, INQUIRYDATE, INQUIRYTIME, ISACTIVE,CRTON,CRTBY,MODON, MODBY ) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,GETDATE(),?, GETDATE(), ?)";
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			final InstituteInquiry instituteInquiry = (InstituteInquiry) object;
			int status = -1;

			try {
				conn = ConnectionManager.getConnection();
				preparedStatement = conn.prepareStatement(query.toString());
				preparedStatement.setString(1, instituteInquiry.getStudentName());
				preparedStatement.setString(2, instituteInquiry.getStudentEmail());
				preparedStatement.setString(3, instituteInquiry.getTelephoneCountryCode());
				preparedStatement.setString(4, instituteInquiry.getTelephoneAreaCode());
				preparedStatement.setString(5, instituteInquiry.getTelNo());
				preparedStatement.setString(6, instituteInquiry.getInquiryTitle());
				preparedStatement.setString(7, instituteInquiry.getInquiryText());
				preparedStatement.setDate(8, instituteInquiry.getInquiryDate());
				preparedStatement.setDate(9, instituteInquiry.getInquiryTime());
				preparedStatement.setInt(10, instituteInquiry.getIsActive());
				preparedStatement.setString(11, instituteInquiry.getCrtBy());
				preparedStatement.setString(12, instituteInquiry.getModBy());
				status = preparedStatement.executeUpdate();

			} catch (SQLException exception) {
				log.error("add(): " + exception.toString());
				throw exception;
			} catch (Exception exception) {
				log.error("add(): " + exception.toString());
				throw exception;
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				conn.close();
			}
		

			return status;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
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
