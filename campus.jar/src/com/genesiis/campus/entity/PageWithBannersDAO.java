package com.genesiis.campus.entity;
/*
 * 20170208 DN c131-admin-manage-banner-upload-banner-image-dn created the initial class stub
 * 
 */

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * PageWithBannersDAO class facilitates bringing DAO level operation a reality
 * It establishes connection directly with the database PAGE table and perform
 * CRUD operations.
 * It implements ICrud interface. 
 * 
 * @author dushantha DN
 *
 */
public class PageWithBannersDAO implements ICrud {

	static Logger log = Logger.getLogger(FeaturedCourseProviderDAO.class
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

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * getAll() method bears the responsibility of extracting all the banner Pages
	 * who are featured and status been 1
	 * 
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		
		// Extract all the course providers who are featured providers and status is one
		StringBuilder bannerPagesSQL =new StringBuilder("SELECT [CODE],[NAME]  FROM [xeno-7].[CAMPUS].[PAGE] WHERE [ISACTIVE]=1");
		String allBannerPagesSQL = bannerPagesSQL.toString();
		Connection bannerPagesConnection =null;
		PreparedStatement preparedStatement = null;
		ResultSet bannerPagesResultSet = null;
		
		try{
			bannerPagesConnection =  ConnectionManager.getConnection();
			preparedStatement =bannerPagesConnection.prepareStatement(allBannerPagesSQL);
			bannerPagesResultSet = preparedStatement.executeQuery();
			final Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
			
			while(bannerPagesResultSet.next()){
				final ArrayList<String>bannerPageRecord = new ArrayList<String>();
				bannerPageRecord.add(bannerPagesResultSet.getString("CODE"));
				bannerPageRecord.add(bannerPagesResultSet.getString("SHORTNAME"));
				outerWrapper.add(bannerPageRecord);
			}
			
			return outerWrapper;
			
		}catch (SQLException sqle){
			log.error("getAll() :SQLException"+sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("getAll() :Exception"+exp.toString());
			throw exp;
		} finally{
			DaoHelper.cleanup(bannerPagesConnection, preparedStatement, bannerPagesResultSet);
		}
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

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
