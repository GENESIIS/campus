package com.genesiis.campus.entity;
//20161124 PN c26-add-student-details: INIT SchoolEducationDAO.java class.
//20161125 PN c26-add-student-details: implemented findByIdMethod().
//20161126 PN c26-add-student-details: modified findByIdMethod() method by setting country name to the return collection.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.SchoolEducation;
import com.genesiis.campus.util.ConnectionManager;

public class SchoolEducationDAO implements ICrud{
	static Logger log = Logger.getLogger(SchoolEducationDAO.class.getName());

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
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
		final Collection<Collection<String>> allEducationList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE], [STUDENT], [SCHOOLGRADE], [MAJOR], [COUNTRY], [RESULT], "
					+ "[INDEXNO], [SCHOOL], [ACHIVEDON], [DESCRIPTION], [MEDIUM] "
					+ "FROM [CAMPUS].[SCHOOLEDUCATION] WHERE [STUDENT] = ? AND ISACTIVE = 1;";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentCode);
			final ResultSet rs = stmt.executeQuery();	

			while (rs.next()) {
				final ArrayList<String> singleEducationList = new ArrayList<String>();
				singleEducationList.add(rs.getString("CODE"));
				singleEducationList.add(rs.getString("STUDENT"));
				singleEducationList.add(rs.getString("SCHOOLGRADE"));
				singleEducationList.add(rs.getString("MAJOR"));
				
				//Get country name, if in a case to pass the name to dataList
				ICrud country2dao = new Country2DAO();
				String countryName = "";
				Collection<Collection<String>> country = country2dao.findById(Integer.parseInt(rs.getString("COUNTRY")));			
				for (Collection<String> collection : country) {
					Object[] cdata = collection.toArray();
					countryName = (String) cdata[1];
				}
				singleEducationList.add(countryName);
				singleEducationList.add(rs.getString("RESULT"));
				singleEducationList.add(rs.getString("INDEXNO"));
				singleEducationList.add(rs.getString("SCHOOL"));
				singleEducationList.add(rs.getString("ACHIVEDON"));
				singleEducationList.add(rs.getString("DESCRIPTION"));
				singleEducationList.add(rs.getString("MEDIUM"));

				final Collection<String> singleEducationCollection = singleEducationList;
				allEducationList.add(singleEducationCollection);
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
		return allEducationList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		SchoolEducation data = (SchoolEducation) object;
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "INSERT INTO [CAMPUS].[SCHOOLEDUCATION] ([STUDENT], [SCHOOLGRADE], [MAJOR], [COUNTRY], "
				+ "[RESULT], [INDEXNO], [SCHOOL], [ACHIVEDON], [DESCRIPTION], [CRTON], [CRTBY], [MEDIUM]) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,(getdate()), ?, ?);";
		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getStudent());
			preparedStatement.setInt(2, data.getSchoolGrade());
			preparedStatement.setInt(3, data.getMajor());
			preparedStatement.setInt(4, data.getCountry());
			preparedStatement.setInt(5, data.getResult());
			preparedStatement.setString(6, data.getIndexNo());
			preparedStatement.setString(7, data.getSchoolName());
			preparedStatement.setDate(8, data.getAchievedOn());
			preparedStatement.setString(9, data.getDescription());
			preparedStatement.setString(10, data.getCrtBy());
			preparedStatement.setInt(11, data.getMedium());
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
		SchoolEducation data = (SchoolEducation) object;
		PreparedStatement preparedStatement = null;
		Connection connection = conn;

		String query = "UPDATE [CAMPUS].[SCHOOLEDUCATION] SET "
				+ "[SCHOOLGRADE]=?, [MAJOR]=?, [COUNTRY]=?, [RESULT]=?, [INDEXNO]=?, [SCHOOL]=?, "
				+ "[ACHIVEDON]=?, [DESCRIPTION]=?, [MODON]=(getdate()), [MODBY]=?, [MEDIUM]=? "
				+ "WHERE [STUDENT]=?;";
		int result = -1;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, data.getSchoolGrade());
			preparedStatement.setInt(2, data.getMajor());
			preparedStatement.setInt(3, data.getCountry());
			preparedStatement.setInt(4, data.getResult());
			preparedStatement.setString(5, data.getIndexNo());
			preparedStatement.setString(6, data.getSchoolName());
			preparedStatement.setDate(7, data.getAchievedOn());
			preparedStatement.setString(8, data.getDescription());
			preparedStatement.setString(9, data.getModBy());
			preparedStatement.setInt(10, data.getMedium());
			preparedStatement.setInt(11, data.getStudent());
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
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
