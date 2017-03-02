package com.genesiis.campus.entity;
/**
 * 20170209 DN c131-admin-manage-banner-upload-banner-image-dn cretaed the initial class stub
 * 				of BannerSlotDAO.java
 * 				the method findById(Object code) has been overriden in oder to get the banner slots.
 * 20170302 DN c131-admin-manage-banner-upload-banner-image-dn changed the sql query in  findById(Object code) 
 * 				to include group by clause and the filter conditions.
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
 * Class BannerSlotDAO acts as a layer between the database and the Command
 * class in communicating and facilitating executing queries on data base entities
 * related to Banner slots.
 * @author dushantha
 *
 */

public class BannerSlotDAO implements ICrud {

	static final Logger log = Logger.getLogger(BannerSlotDAO.class.getName());
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
	 * method accept an object type variable and returns Collection<Collection<String>>
	 * of banner slots attached to the page of which the page code is passed to the method
	 * as the argument
	 * @author dushantha DN
	 * @param Object code
	 * 					This is the unique identification code of a page
	 * 					of which the allocated banner slots have to be
	 * 					retrieved out of the data base 
	 * @return Collection<Collection<String>>
	 * 					Outer Collection acts as a wrapper to the inner Collection<String>
	 * 					Each inner collection represents a data in a tuple.	
	 * @throws SQLException
	 * @throws Exception - NumberFormatException sub type can be thrown if the object
	 * 						code can not be converted to a valid number.
	 */
	
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement prep = null;
		ResultSet resultSlot = null;
		try{
			int pageCode =Integer.parseInt((String)code);
			StringBuilder bannerSlotSql = new StringBuilder("SELECT PSLT.NAME FROM [CAMPUS].[PAGESLOT]  PSLT ");
			bannerSlotSql.append(" INNER JOIN [CAMPUS].[PAGE] PGE ");
			bannerSlotSql.append("ON PGE.CODE=PSLT.PAGE ");
			bannerSlotSql.append(" WHERE PGE.ISACTIVE = 1 AND PSLT.[ISACTIVE] =1 AND PSLT.PAGE=? ");
			bannerSlotSql.append(" GROUP BY PSLT.NAME");
			con = ConnectionManager.getConnection();
			prep = con.prepareStatement(bannerSlotSql.toString());
			prep.setInt(1, pageCode);
			resultSlot= prep.executeQuery();
			Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
			
			while(resultSlot.next()){
				ArrayList<String> bannerSlots = new ArrayList<String>();
				//bannerSlots.add(resultSlot.getString("CODE"));
				bannerSlots.add("1"); //ADDING A DUMMY RECORD TO THE COLLECTION
				bannerSlots.add(resultSlot.getString("NAME"));
				outerWrapper.add(bannerSlots);
				
			}
		return outerWrapper;
		
		}catch (SQLException sqle) {
			log.error("findById(Object code) :SQLException "+sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("findById(Object code) :Exception"+ exp.toString());
			throw exp;			
		}finally{
			DaoHelper.cleanup(con, prep, resultSlot);
		}
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
