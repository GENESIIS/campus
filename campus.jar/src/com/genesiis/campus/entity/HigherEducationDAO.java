package com.genesiis.campus.entity;

//20161215 PN CAM-28: INIT HigherEducationDAO.java class and implemented add() method.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdAddHigherEducationData;
import com.genesiis.campus.entity.model.HigherEducation;
import com.genesiis.campus.util.ConnectionManager;

public class HigherEducationDAO implements ICrud{
	static Logger log = Logger.getLogger(HigherEducationDAO.class.getName());
	@Override
	public int add(Object object) throws SQLException, Exception {
		HigherEducation data = (HigherEducation) object;
		PreparedStatement preparedStatement = null;
		Connection connection = ConnectionManager.getConnection();

		String query = "INSERT INTO [CAMPUS].[HIGHERDUCATION] ([INSTITUTE] ,[AFFINSTITUTE] ,[STUDENT] ,[LEVEL] ,[AWARD] ,"
				+ "[MAJOR] ,[COUNTRY] ,[COMMENCEDON] ,[COMPLETIONON] ,[STUDENTID] ,[RESULT] ,[DESCRIPTION] ,[MEDIUM] ,[CRTON] ,[CRTBY]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,getDate(),?);";

		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getInstitute());
			preparedStatement.setString(2, data.getAffiliatedInstitute());
			preparedStatement.setInt(3, data.getStudent());
			preparedStatement.setInt(4, data.getLevel());
			preparedStatement.setInt(5, data.getAward());
			preparedStatement.setInt(6, data.getMajor());
			preparedStatement.setInt(7, data.getCountry());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletedOn());
			preparedStatement.setString(10, data.getStudentId());
			preparedStatement.setString(11, data.getResult());
			preparedStatement.setString(12, data.getDescription());
			preparedStatement.setInt(13, data.getMedium());
			preparedStatement.setString(14, data.getCrtBy());
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			log.error("add(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("add(): E: " + ex.toString());
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return result;
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

}
