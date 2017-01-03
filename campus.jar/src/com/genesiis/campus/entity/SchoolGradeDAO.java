package com.genesiis.campus.entity;
//20161125 PN c26-add-student-details: INIT the class and getAll() method implemented.
//			  c26-add-student-details: changed getAll() method SQL query.
//20160103 PN CAM-28: added JDBC property closing statements to the finally block.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.util.ConnectionManager;
import org.apache.log4j.Logger;

public class SchoolGradeDAO implements ICrud{
	static Logger log = Logger.getLogger(SchoolGradeDAO.class.getName());
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allGradeList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[LEVEL],[TITLE] FROM [CAMPUS].[SCHOOLGRADE] WHERE [LEVEL] = 17 AND [ISACTIVE]=1;";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				final ArrayList<String> singleGradeList = new ArrayList<String>();
				singleGradeList.add(rs.getString("CODE"));
				singleGradeList.add(rs.getString("TITLE"));
				singleGradeList.add(rs.getString("LEVEL"));

				final Collection<String> singleGradeCollection = singleGradeList;
				allGradeList.add(singleGradeCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): E " + e.toString());
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
		return allGradeList;
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
