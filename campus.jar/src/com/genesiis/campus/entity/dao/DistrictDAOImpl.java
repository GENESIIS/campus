package com.genesiis.campus.entity.dao;

//20170221 DJ c145-add-enhanced-programme created DistrictDAOImpl.java

import com.genesiis.campus.entity.DistrictICrud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;



/**The class  {@code DistrictDAOImpl} is a form of DAO class.
 * The Interface {@code DistrictDAOImpl} has precise control over district and town dao level manipulations. 
 *  @author dumani DJ   
 */
public class DistrictDAOImpl  implements DistrictICrud{

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

	@Override
	public Collection<Collection<String>> getCourseProviderTown(int providerCode)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
