package com.genesiis.campus.entity;

//20161129 CW c36-add-tutor-details Created the Class. 

//20161221 CW c36-add-tutor-details Modified getAll() method.
//20170110 CW c36-add-tutor-details add findById() method from c18 - student : signup : without using third party application & removed getCode() method.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

public class UserTypeDAO implements ICrud {

	static Logger log = Logger.getLogger(TownDAO.class.getName());

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

	/*
	 * @author DN - taken from c18 - student : signup : without using third
	 * party application UserTypeDAO class
	 * 
	 * @see com.genesiis.campus.entity.ICrud#findById(java.lang.Object)
	 */
	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		Connection userTypeConnection = null;
		PreparedStatement prepaire = null;
		ResultSet userCode = null;
		String userTypeString = (String) code;
		try {
			Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
			userTypeConnection = ConnectionManager.getConnection();

			StringBuilder getUserTypeSQL = new StringBuilder("SELECT * FROM [CAMPUS].[USERTYPE] ");
			getUserTypeSQL.append(" WHERE USERTYPESTRING = ? AND ISACTIVE=1 ; ");
			prepaire = userTypeConnection.prepareStatement(getUserTypeSQL.toString());
			prepaire.setString(1, userTypeString);
			userCode = prepaire.executeQuery();

			while (userCode.next()) {
				final Collection<String> singleUSerTypeList = new ArrayList<String>();
				singleUSerTypeList.add(userCode.getString("CODE"));
				singleUSerTypeList.add(userCode.getString("USERTYPESTRING"));
				outerWrapper.add(singleUSerTypeList);
			}
			return outerWrapper;
		} catch (SQLException sqle) {
			log.error("findById(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("findById(): Wxcepption" + exp.toString());
			throw exp;
		} finally {
			try {
				DaoHelper.cleanup(userTypeConnection, prepaire, userCode);
			} catch (SQLException sqle) {
				log.error("findById(): SQLException in finally block " + sqle.toString());
				throw sqle;
			}
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
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
}
