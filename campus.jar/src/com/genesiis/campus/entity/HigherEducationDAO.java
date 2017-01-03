package com.genesiis.campus.entity;

//20161215 PN CAM-28: INIT HigherEducationDAO.java class and implemented add() method.
//20161216 PN CAM-28 : add-student-details: implemented findById() method
//20161220 PN CAM-28: implemented delete(Object object, Connection conn) method.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

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
			String query = "SELECT [CODE] ,[INSTITUTE] ,[AFFINSTITUTE] ,[AWARD] ,[MAJOR] ,[COUNTRY] ,"
					+ "[COMMENCEDON] ,[COMPLETIONON] ,[STUDENTID] ,[RESULT] ,[DESCRIPTION] ,[MEDIUM] "
					+ "FROM [CAMPUS].[HIGHERDUCATION] "
					+ "WHERE [STUDENT] = ? AND ISACTIVE = 1;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			rs = stmt.executeQuery();	

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
			if (rs != null) {
				rs.close();
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
		HigherEducation data = (HigherEducation) object;		
		int studentCode = data.getCode();
		Connection connection = conn;
		PreparedStatement preparedStatement = null;
		int result = -1;

		String deleteSQL = "DELETE FROM [CAMPUS].[HIGHERDUCATION] WHERE [CODE] = ?;";

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
