package com.genesiis.campus.entity;

// 20161024 DN c10-contacting-us-page created the initial version of ICrud.java
//20161026 Dn c10-contacting-us-page Collection<Collection<String>> findById(Object object, Connection conn) created

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface ICrud {
	public int add(Object object) throws SQLException, Exception;

	public int update(Object object) throws SQLException, Exception;

	public int delete(Object object) throws SQLException, Exception;

	public Collection<Collection<String>> findById(Object code) throws SQLException,
			Exception;

	public Collection<Collection<String>> getAll() throws SQLException, Exception;

	int add(Object object, Connection conn) throws SQLException, Exception;

	int update(Object object, Connection conn) throws SQLException, Exception;

	int delete(Object object, Connection conn) throws SQLException, Exception;
	
	public Collection<Collection<String>> findById(Object object, Connection conn) throws SQLException, Exception;

}