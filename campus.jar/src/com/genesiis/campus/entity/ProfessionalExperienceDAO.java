package com.genesiis.campus.entity;
//20161128 PN c26-add-student-details: INIT ProfessionalExperienceDAO.java class.
//		   PN c26-add-student-details: implemented add() method.
//20161129 PN c26-add-student-details: modified SQL query inside add() method.
//20161208 PN CAM-26 : add-student-details: implemented add(object,Connection) method
//20161215 PN CAM-28 : add-student-details: implemented findById() method
//20161216 PN CAM-28 : re-implemented findById() method
//20161220 PN CAM-28: implemented delete(Object object, Connection conn) method.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.

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
			if (connection != null) {
				connection.close();
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
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE], [ORGANIZATION], [STUDENT], [INDUSTRY], [JOBCATEGORY], "
					+ "[DESIGNATION], [COMMENCEDON], [COMPLETIONON], [DESCRIPTION] "
					+ "FROM [CAMPUS].[PROFESSIONALEXPERIENCE] "
					+ "WHERE [STUDENT] = ? AND ISACTIVE = 1;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			rs = stmt.executeQuery();	

			while (rs.next()) {
				final ArrayList<String> singlehigherEduList = new ArrayList<String>();
				singlehigherEduList.add(rs.getString("CODE"));
				singlehigherEduList.add(rs.getString("ORGANIZATION"));
				
				//Get country name, if in a case to pass the name to dataList
				ICrud majordao = new MajorDAO();
				String jobcategoryName = "";
				String industryName = "";
					
				Collection<Collection<String>> industry = majordao.findById(Integer.parseInt(rs.getString("JOBCATEGORY")));			
				for (Collection<String> collection : industry) {
					Object[] cdata = collection.toArray();
					jobcategoryName = (String) cdata[1];
				}
				Collection<Collection<String>> jobcaegory = majordao.findById(Integer.parseInt(rs.getString("INDUSTRY")));				
				for (Collection<String> collection : industry) {
					Object[] cdata = collection.toArray();
					industryName = (String) cdata[1];
				}
				
				singlehigherEduList.add(industryName);
				singlehigherEduList.add(jobcategoryName);
				singlehigherEduList.add(rs.getString("DESIGNATION"));
				singlehigherEduList.add(rs.getString("COMMENCEDON"));
				singlehigherEduList.add(rs.getString("COMPLETIONON"));
				singlehigherEduList.add(rs.getString("DESCRIPTION"));

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
			if (rs != null) {
				rs.close();
			}
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
		ProfessionalExperience data = (ProfessionalExperience) object;		
		int studentCode = data.getCode();
		Connection connection = conn;
		PreparedStatement preparedStatement = null;
		int result = -1;

		String deleteSQL = "DELETE FROM [CAMPUS].[PROFESSIONALEXPERIENCE] WHERE [CODE] = ?;";

		try {
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, studentCode);

			// execute delete SQL stetement
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			log.error("delete(): SQLE: " + sqle.toString());
			throw sqle;
		} catch (Exception ex) {
			log.error("delete(): E: " + ex.toString());
			throw ex;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
		return result;
	}

}
