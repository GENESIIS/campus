package com.genesiis.campus.entity;
//20161124 PN c26-add-student-details: INIT SchoolEducationDAO.java class.
//20161125 PN c26-add-student-details: implemented findByIdMethod().

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.genesiis.campus.command.CmdGetSchoolEducationData;
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
				singleEducationList.add(rs.getString("COUNTRY"));
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
			log.info("findById(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById(): E " + e.toString());
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
