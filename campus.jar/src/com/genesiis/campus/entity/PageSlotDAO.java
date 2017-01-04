package com.genesiis.campus.entity;

//DJ 20161210 c52-report-banner-statistics-MP-dj  Implement  PageSlotDAO.java
//DJ 20161210 c52-report-banner-statistics-MP-dj  Implement  findById() method

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class PageSlotDAO implements ICrud {
	
	static org.apache.log4j.Logger log = Logger.getLogger(PageSlotDAO.class.getName());

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
	 * Retrieve Page Slots result set
	 * @param 
	 * @author DJ
	 * @return Collection
	 */

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		final Collection<Collection<String>> pageSlotList = new ArrayList<Collection<String>>();
		int pageCode=0;
		if(UtilityHelper.isNotEmptyObject(code)){
			 pageCode=Integer.valueOf((String) code);			
		}
        
		try {
			conn=ConnectionManager.getConnection();
			final StringBuilder sb = new StringBuilder(
					"SELECT SLOT.CODE AS PAGESLOTCODE , SLOT.NAME AS PAGESLOTNAME FROM [CAMPUS].[PAGESLOT] SLOT WHERE");
			sb.append(" SLOT.PAGE= ? AND SLOT.ISACTIVE= ?");
			stmt=conn.prepareStatement(sb.toString());
			stmt.setInt(1, pageCode);
			stmt.setInt(2, ApplicationStatus.ACTIVE.getStatusValue());
			resultSet= stmt.executeQuery();	
			while (resultSet.next()) {
				final ArrayList<String> singleSlot = new ArrayList<String>();
				singleSlot.add(resultSet.getString("PAGESLOTCODE"));
				singleSlot.add(resultSet.getString("PAGESLOTNAME"));				
				pageSlotList.add(singleSlot);
			}			

		} catch (SQLException sqlException) {
			log.info("findById() sqlException" + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("findById() Exception" + e.toString());
			throw e;
		} finally {
			DaoHelper.cleanup(conn, stmt, resultSet);
		}
		return pageSlotList;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
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

}
