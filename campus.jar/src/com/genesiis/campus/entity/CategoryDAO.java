package com.genesiis.campus.entity;

//20161028 PN c11-criteria-based-filter-search implemented getAll() method for retrieve existing details
//20161103 JH c7-higher-education-landing-page findById method code modified
//20161104 JH c7-higher-education-landing-page findById method code modified : remove unwanted loggers
//20161115 JH c7-higher-education-landing-page findById method code modified : set enum class values
//20161115 JH c7-higher-education-landing-page getAll() method : added method comments

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.util.ConnectionManager;

import org.apache.log4j.Logger;

public class CategoryDAO implements ICrud {
	static org.apache.log4j.Logger log = Logger.getLogger(CategoryDAO.class
			.getName());

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

		Category category = (Category) code;

		try {

			conn = ConnectionManager.getConnection();

			String query = "SELECT * FROM [CAMPUS].[CATEGORY] WHERE ISACTIVE = 1 AND CODE = ?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, category.getCode());

			final ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleCategoryList = new ArrayList<String>();
				
				
				/**
				 * this section of the code used to get the enum value of the category, 
				 * which is used to identify the category. The enum value is stored in the
				 * database in future. But to support the current code following switch case is used
				 * to generate the enum value for the category.
				 */
				int categoryId = Integer.parseInt(rs.getString("CODE"));
				String categoryIdentifierString = null;
				switch(categoryId){
				case 1: categoryIdentifierString = "PRE_EDUCATION"; break;
				case 2: categoryIdentifierString = "SCHOOL_EDUCATION"; break;
				case 3: categoryIdentifierString = "HIGHER_EDUCATION"; break;
				case 4: categoryIdentifierString = "CORPORATE_TRAINING"; break;
				case 5: categoryIdentifierString = "VOCATIONAL_TRAINING"; break;
				case 6: categoryIdentifierString = "TALENT_AND_SKILL"; break;
				default: categoryIdentifierString = "DEFAULT_CATEGORY"; break;
				}
								
				singleCategoryList.add(rs.getString("CODE"));
				singleCategoryList.add(rs.getString("NAME"));
				singleCategoryList.add(rs.getString("DESCRIPTION"));
				singleCategoryList.add(rs.getString("IMAGE"));
				singleCategoryList.add(rs.getString("CRTON"));
				singleCategoryList.add(rs.getString("CRTBY"));
				singleCategoryList.add(rs.getString("MODON"));
				singleCategoryList.add(rs.getString("MODBY"));
				singleCategoryList.add(categoryIdentifierString);

				final Collection<String> singleCategoryCollection = singleCategoryList;
				allCategoryList.add(singleCategoryCollection);
			}

		} catch (SQLException sqlException) {
			log.error("findById() : SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception) {
			log.error("findById() : Exception " + exception.toString());
		}

		return allCategoryList;
	}

	/**
	 * getAll method used to get details of all categories.
	 * @author PN
	 * @return collection of String
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		final Collection<Collection<String>> allCategoryList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[NAME],[DESCRIPTION],[IMAGE],[ISACTIVE] FROM [CAMPUS].[CATEGORY] WHERE [ISACTIVE] = 1;";

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

}