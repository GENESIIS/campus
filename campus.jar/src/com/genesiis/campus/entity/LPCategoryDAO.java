package com.genesiis.campus.entity;

//20161223 PN CAM-112: INIT LPCategoryDAO.java class.
//20161227 PN CAM-112: Modified getAll() method implementation.
//20170102 PN CAM-112: added ResultSet close statement into finally blocks in DAO methods.]

import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class LPCategoryDAO implements ICrud{
static Logger log = Logger.getLogger(CategoryDAO.class.getName());
	
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

	/**
	 * @author pabodha
	 * @return Collection<Collection<String>>: contains all the available
	 *         categories in DB.
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		final Collection<Collection<String>> allCategoryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION],[ISACTIVE] FROM [CAMPUS].[CATEGORY] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCategoryList = new ArrayList<String>();
				singleCategoryList.add(rs.getString("CODE"));
				singleCategoryList.add(rs.getString("NAME"));
				singleCategoryList.add(Validator.getSubDescription(rs.getString("DESCRIPTION")).replaceAll(",", "##"));
				
				final Collection<String> singleCategoryCollection = singleCategoryList;
				allCategoryList.add(singleCategoryCollection);
			}
		} catch (SQLException sqlException) {
			log.error("getAll(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("getAll(): Exception " + e.toString());
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
		return allCategoryList;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
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

	@Override
	public Collection<Collection<String>> findById(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
