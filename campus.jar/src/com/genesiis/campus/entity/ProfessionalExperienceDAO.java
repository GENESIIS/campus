package com.genesiis.campus.entity;
//20161128 PN c26-add-student-details: INIT ProfessionalExperienceDAO.java class.
//		   PN c26-add-student-details: implemented add() method.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.model.ProfessionalExperience;
import com.genesiis.campus.util.ConnectionManager;

public class ProfessionalExperienceDAO implements ICrud{

	@Override
	public int add(Object object) throws SQLException, Exception {
		ProfessionalExperience data = (ProfessionalExperience) object;
		PreparedStatement preparedStatement = null;
		Connection connection = ConnectionManager.getConnection();;

		String query = "INSERT INTO [CAMPUS].[PROFESSIONALEXPERIENCE] ([ORGANIZATION], [STUDENT], [INDUSTRY],"
				+ " [JOBCATEGORY], [DESIGNATION], [COMMENCEDON], [COMPLETIONON], [DESCRIPTION],[CRTBY]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?);";
";
		int result = -1;

		try {
			preparedStatement.setString(6, data.getOrganization());
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(3, data.getIndustry());
			preparedStatement.setInt(2, data.getJobCategoty());
			preparedStatement.setString(7, data.getDesignation());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletionOn());
			preparedStatement.setString(10, data.getDescription());
			preparedStatement.setString(10, data.getCrtBy());
			preparedStatement = connection.prepareStatement(query);
		
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			log.info("add(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.info("add(): E: " + ex.toString());
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
