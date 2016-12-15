package com.genesiis.campus.entity;
//20161128 PN c26-add-student-details: INIT ProfessionalExperienceDAO.java class.
//		   PN c26-add-student-details: implemented add() method.
//20161129 PN c26-add-student-details: modified SQL query inside add() method.
//20161208 PN CAM-26 : add-student-details: implemented add(object,Connection) method
//20161215 PN CAM-28 : add-student-details: implemented findById() method

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.command.CmdAddProfessionalExpDetails;
import org.apache.log4j.Logger;
import com.genesiis.campus.entity.model.ProfessionalExperience;
import com.genesiis.campus.util.ConnectionManager;

public class ProfessionalExperienceDAO implements ICrud{
	static Logger log = Logger.getLogger(ProfessionalExperienceDAO.class.getName());
	
	@Override
	public int add(Object object) throws SQLException, Exception {
		ProfessionalExperience data = (ProfessionalExperience) object;
		PreparedStatement preparedStatement = null;
		Connection connection = ConnectionManager.getConnection();;

		String query = "INSERT INTO [CAMPUS].[PROFESSIONALEXPERIENCE] ([ORGANIZATION], [STUDENT], [INDUSTRY],"
				+ " [JOBCATEGORY], [DESIGNATION], [COMMENCEDON], [COMPLETIONON], [DESCRIPTION],[CRTBY]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?);";

		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(6, data.getOrganization());
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(3, data.getIndustry());
			preparedStatement.setInt(2, data.getJobCategoty());
			preparedStatement.setString(7, data.getDesignation());
			preparedStatement.setDate(8, data.getCommencedOn());
			preparedStatement.setDate(9, data.getCompletionOn());
			preparedStatement.setString(10, data.getDescription());
			preparedStatement.setString(10, data.getCrtBy());
			
		
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
		int studentCode = (Integer) code;
		final Collection<Collection<String>> allhigherEduList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE] ,[INSTITUTE] ,[AFFINSTITUTE] ,[AWARD] ,[MAJOR] ,[COUNTRY] ,"
					+ "[COMMENCEDON] ,[COMPLETIONON] ,[STUDENTID] ,[RESULT] ,[DESCRIPTION] ,[MEDIUM] "
					+ "FROM [CAMPUS].[HIGHERDUCATION] "
					+ "WHERE [STUDENT] = ? AND ISACTIVE = 1;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			final ResultSet rs = stmt.executeQuery();	

			while (rs.next()) {
				final ArrayList<String> singlehigherEduList = new ArrayList<String>();
				singlehigherEduList.add(rs.getString("CODE"));
				singlehigherEduList.add(rs.getString("INSTITUTE"));
				singlehigherEduList.add(rs.getString("AFFINSTITUTE"));
				singlehigherEduList.add(rs.getString("AWARD"));
				singlehigherEduList.add(rs.getString("MAJOR"));
				
				//Get country name, if in a case to pass the name to dataList
				ICrud country2dao = new Country2DAO();
				String countryName = "";
				Collection<Collection<String>> country = country2dao.findById(Integer.parseInt(rs.getString("COUNTRY")));			
				for (Collection<String> collection : country) {
					Object[] cdata = collection.toArray();
					countryName = (String) cdata[1];
				}
				singlehigherEduList.add(countryName);
				singlehigherEduList.add(rs.getString("COMMENCEDON"));
				singlehigherEduList.add(rs.getString("COMPLETIONON"));
				singlehigherEduList.add(rs.getString("STUDENTID"));
				singlehigherEduList.add(rs.getString("RESULT"));
				singlehigherEduList.add(rs.getString("DESCRIPTION"));
				singlehigherEduList.add(rs.getString("MEDIUM"));

				final Collection<String> singlehigherEduCollection = singlehigherEduList;
				allhigherEduList.add(singlehigherEduCollection);
			}
		} catch (SQLException sqlException) {
			log.error("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("findById(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allhigherEduList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		ProfessionalExperience data = (ProfessionalExperience) object;
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "INSERT INTO [CAMPUS].[PROFESSIONALEXPERIENCE] ([ORGANIZATION], [STUDENT], [INDUSTRY],"
				+ " [JOBCATEGORY], [DESIGNATION], [COMMENCEDON], [COMPLETIONON], [DESCRIPTION],[CRTBY]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?);";

		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data.getOrganization());
			preparedStatement.setInt(2, data.getStudent());
			preparedStatement.setInt(3, data.getIndustry());
			preparedStatement.setInt(4, data.getJobCategoty());
			preparedStatement.setString(5, data.getDesignation());
			preparedStatement.setDate(6, data.getCommencedOn());
			preparedStatement.setDate(7, data.getCompletionOn());
			preparedStatement.setString(8, data.getDescription());
			preparedStatement.setString(9, data.getCrtBy());
			
		
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
