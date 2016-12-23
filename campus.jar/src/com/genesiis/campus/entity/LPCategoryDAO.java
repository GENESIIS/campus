package com.genesiis.campus.entity;

//20161223 PN CAM-112: INIT LPCategoryDAO.java class.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

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

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION],[ISACTIVE] FROM [CAMPUS].[CATEGORY] WHERE [ISACTIVE] = 1;";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCategoryList = new ArrayList<String>();
				singleCategoryList.add(rs.getString("CODE"));
				singleCategoryList.add(rs.getString("NAME"));
				singleCategoryList.add(rs.getString("DESCRIPTION"));
				
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
		}
		return allCategoryList;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * finById method used to get category details which are active
	 * 
	 * @param code
	 * @return collection of String
	 * @author JH
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {

		final Collection<Collection<String>> allCategoryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {

			conn = ConnectionManager.getConnection();
			
			Category category = (Category) code;

			String query = "SELECT * FROM [CAMPUS].[CATEGORY] WHERE ISACTIVE = 1 AND CODE = ?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, category.getCode());

			final ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCategoryList = new ArrayList<String>();
								
				singleCategoryList.add(rs.getString("CODE"));
				singleCategoryList.add(rs.getString("NAME"));
				singleCategoryList.add(rs.getString("DESCRIPTION"));
				/**
				 * here a '.png' is added to the image string assuming that all categories will
				 * have the logo in png form.
				 */
				singleCategoryList.add( rs.getString("CODE")+ ".png");
				singleCategoryList.add(rs.getString("CATEGORYSTRING"));

				final Collection<String> singleCategoryCollection = singleCategoryList;
				allCategoryList.add(singleCategoryCollection);
			}

		} catch (SQLException sqlException) {
			log.error("findById() : SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception) {
			log.error("findById() : Exception " + exception.toString());
		}finally{
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(conn != null){
				conn.close();
			}
		}

		return allCategoryList;
	}


	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
