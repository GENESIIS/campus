package com.genesiis.campus.entity.dao;
//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj created DistrictDAO.java
//DJ 20161115 c17-provider-criteria-based-filter-search-MP-dj Implement getAll()
//DJ 20170108 c6-list-available-institutes-on-the-view Implement getAllDistricts() method

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.TownICrud;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DistrictDAO  implements TownICrud{
	
	static org.apache.log4j.Logger log = Logger.getLogger(DistrictDAO.class.getName());

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
	 * Get all DistrictList details
	 * @param 
	 * @author DJ
	 * @return Collection 
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		/*Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allDistrictList=new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[PROVINCE],[NAME] FROM [CAMPUS].[DISTRICT] WHERE CODE NOT IN (-1,31);";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleDistrictList = new ArrayList<String>();
				singleDistrictList.add(rs.getString("CODE"));
				singleDistrictList.add(rs.getString("PROVINCE"));
				singleDistrictList.add(rs.getString("NAME"));

				final Collection<String> singleDistrictCollection = singleDistrictList;
				allDistrictList.add(singleDistrictCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}
		
		return allDistrictList;*/
		return null;
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
	
	/**
	 * Get all DistrictList details
	 * @param 
	 * @author dumani DJ
	 * @return Collection 
	 */
	@Override
	public Collection<Collection<String>> getAllDistricts()
			throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		final Collection<Collection<String>> allDistrictList=new ArrayList<Collection<String>>();
		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[PROVINCE],[NAME] FROM [CAMPUS].[DISTRICT] WHERE CODE NOT IN (-1,31);";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleDistrictList = new ArrayList<String>();
				singleDistrictList.add(rs.getString("CODE"));
				singleDistrictList.add(rs.getString("PROVINCE"));
				singleDistrictList.add(rs.getString("NAME"));

				final Collection<String> singleDistrictCollection = singleDistrictList;
				allDistrictList.add(singleDistrictCollection);
			}
		} catch (SQLException sqlException) {
			log.info("getAll() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("getAll() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, rs);
		}		
		return allDistrictList;
	}

}
