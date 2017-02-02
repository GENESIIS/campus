package com.genesiis.campus.entity;
//20161026 Dn c10-contacting-us-page created the initial version of SystemConfigDAO.java
//20161026 Dn c10-contacting-us-page findById(Object object,Connection conn) created
//20170109 DN CAM47 refactor the method findById(Object object,Connection con) to include finally block
//20170102 PN CAM-112: added ResultSet close statement into finally blocks in DAO methods.

import com.genesiis.campus.entity.model.SystemConfiguration;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class SystemConfigDAO implements ICrud {
	
	static Logger log = Logger.getLogger(SystemConfigDAO.class.getName());

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
	 * Returns the record in SYSTEMCONFIG table, whose value for SYSTEMCONFIGCODE column matches 
	 * the String accepted as the parameter to the method.	 * 
	 * 
	 * @param code An Object, whose actual value is expected to be of type String, that the 
	 * record to be selected must contain as the value for its SYSTEMCONFIGCODE column
	 * 
	 * @return Collection<Collection<String>> A Collection wrapping the values fetched from DB. 
	 * Although Collection of Collection<String> is returned, there will almost always actually 
	 * be data of only one record. 
	 *  
	 */
	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		final Collection<Collection<String>> valueCollection = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String systemConfigCode = (String) code;
			
			String query = "SELECT DESCRIPTION, VALUE1, VALUE2, VALUE3, SORTKEY "
					+ "FROM [CAMPUS].[SYSTEMCONFIG] "
					+ "WHERE SYSTEMCONFIGCODE = ?";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, systemConfigCode);
			ResultSet rs = ps.executeQuery();			

			while (rs.next()) {
				final ArrayList<String> singleRecord = new ArrayList<String>();
				singleRecord.add(systemConfigCode); //0
				singleRecord.add(rs.getString("DESCRIPTION")); //1
				singleRecord.add(rs.getString("VALUE1")); //2
				singleRecord.add(rs.getString("VALUE2")); //3
				singleRecord.add(rs.getString("VALUE3")); //4
				singleRecord.add(rs.getString("SORTKEY")); //5
				final Collection<String> singleRecordColl = singleRecord;
				valueCollection.add(singleRecordColl);
			}
			
		} catch (SQLException sqle) {
			log.error("findById(Object): SQLException " + sqle.toString());
			throw sqle;

		} catch (Exception e) {
			log.error("findById(Object): Exception " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return valueCollection;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		final Collection<Collection<String>> allConfigList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionManager.getConnection();
			String query = "SELECT [CODE],[SYSTEMCONFIGCODE],[DESCRIPTION],[VALUE1],[VALUE2],[VALUE3],[SORTKEY] FROM [CAMPUS].[SYSTEMCONFIG];";

			stmt = conn.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				final ArrayList<String> singleConfigList = new ArrayList<String>();
				singleConfigList.add(rs.getString("CODE"));
				singleConfigList.add(rs.getString("SYSTEMCONFIGCODE"));
				singleConfigList.add(rs.getString("DESCRIPTION"));
				singleConfigList.add(rs.getString("VALUE1"));
				singleConfigList.add(rs.getString("VALUE2"));
				singleConfigList.add(rs.getString("VALUE3"));
				singleConfigList.add(rs.getString("SORTKEY"));

				final Collection<String> singleConfigCollection = singleConfigList;
				allConfigList.add(singleConfigList);
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
		return allConfigList;
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
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		ResultSet resultSet = null;
		PreparedStatement prstmtFind = null;
		String[]  sysConfigCode = (String[])object;		
		
		StringBuilder queryBuilder = new StringBuilder("SELECT [CODE] ,[SYSTEMCONFIGCODE] ,[DESCRIPTION] ,[VALUE1] ");
		queryBuilder.append(" ,[VALUE2] ,[VALUE3] ,[SORTKEY] ,[CRTON] ,[CRTBY] ");
		queryBuilder.append(",[MODON] ,[MODBY] ");
		queryBuilder.append("FROM [CAMPUS].[SYSTEMCONFIG] ");
		queryBuilder.append(" WHERE [SYSTEMCONFIGCODE] IN ( ");
		
		for (int counter = 0; counter < sysConfigCode.length; counter++) {
			if (counter < sysConfigCode.length - 1) {
				queryBuilder.append("'"+sysConfigCode[counter] +"'"+ ", ");
			} else {
				queryBuilder.append("'"+sysConfigCode[counter] + "'"+" );");
			}
		}
		
		
		String findSql =queryBuilder.toString();		
		final Collection<Collection<String>> outerCollection = new ArrayList<Collection<String>>();
		try{
			 prstmtFind = conn.prepareStatement(findSql);
			 resultSet = prstmtFind.executeQuery();
			
			while(resultSet.next()){
				final ArrayList<String> systemConfigurations = new ArrayList<String>();				
				
				systemConfigurations.add(resultSet.getString("VALUE1")); // only the Email needed   
				systemConfigurations.add(resultSet.getString("VALUE2"));
				systemConfigurations.add(resultSet.getString("VALUE3"));
				outerCollection.add(systemConfigurations);
				
			}						
		} catch (SQLTimeoutException sqlto) {
			log.error("findById(Object object,Connection conn):SQLTimeoutException :" +sqlto.toString());
			throw sqlto;
		} catch (SQLException sqle){
			log.error("findById(Object object,Connection conn):SQLException :" +sqle.toString());
			throw sqle;	
		} finally{
			
			DaoHelper.cleanup(null, prstmtFind, resultSet); // connection will be closed from where it has been created
			if (prstmtFind != null) {
				prstmtFind.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} 
		return outerCollection;
	}
	
}
