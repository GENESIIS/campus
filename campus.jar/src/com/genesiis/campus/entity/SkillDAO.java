package com.genesiis.campus.entity;

//20161206 PN c26-add-student-details INIT SkillDAO.java. Implemented geAll() method.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;

public class SkillDAO implements ICrud{
	static Logger log = Logger.getLogger(SkillDAO.class.getName());
	
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
		final Collection<Collection<String>> allSkillList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION] FROM [CAMPUS].[SKILL] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleLevelList = new ArrayList<String>();
				singleLevelList.add(rs.getString("CODE"));
				singleLevelList.add(rs.getString("NAME"));
				singleLevelList.add(rs.getString("DESCRIPTION"));

				final Collection<String> singleSkillCollection = singleLevelList;
				allSkillList.add(singleSkillCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll(): SQLE " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll(): E " + e.toString());
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allSkillList;
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
