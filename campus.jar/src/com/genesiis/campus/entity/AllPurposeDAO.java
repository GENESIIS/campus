package com.genesiis.campus.entity;

/*
 * 20170425 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class AllPurposeDAO has been implemented.
 */


import com.genesiis.campus.command.CmdListPrereqSignUpWithoutThirdParty;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * The Class AllPurposeDAO. 
 * This Class is used to create adaptor classes that are <br>
 * sub classes of class AllPurposeDAO and there is no necessity <br>
 * for retaining the created class for long time in situations where it is required. <br>
 * Users must override the required method(s) of the class. It is recommend that, <br>
 * if the requirement is to access the Database and the design is not paramount important <br>
 * and the adaptor class is only serving as transient object, then the class can be used in
 * an adaptor implementation (As an anonymous class).
 * @author dushantha DN.
 * 
 */
public class AllPurposeDAO implements ICrud {
	private static Logger log = Logger.getLogger(AllPurposeDAO.class.getName());

	/** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#add(java.lang.Object)
	 */
	@Override
	public int add(Object object) throws SQLException, Exception {
		
		return 0;
	}

	/*** 
	 * @see com.genesiis.campus.entity.ICrud#update(java.lang.Object)
	 */
	@Override
	public int update(Object object) throws SQLException, Exception {
		
		return 0;
	}

	/*** 
	 * @see com.genesiis.campus.entity.ICrud#delete(java.lang.Object)
	 */
	@Override
	public int delete(Object object) throws SQLException, Exception {
		
		return 0;
	}

	/*** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#findById(java.lang.Object)
	 */
	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		
		return null;
	}

	/** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#getAll()
	 */
	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
				return null;
	}

	/** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#add(java.lang.Object, java.sql.Connection)
	 */
	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		
		return 0;
	}

	/** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#update(java.lang.Object, java.sql.Connection)
	 */
	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		
		return 0;
	}

	/** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#delete(java.lang.Object, java.sql.Connection)
	 */
	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		
		return 0;
	}

	/** (non-Javadoc)
	 * @see com.genesiis.campus.entity.ICrud#findById(java.lang.Object, java.sql.Connection)
	 */
	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		
		return null;
	}

}
