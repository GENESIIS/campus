package com.genesiis.campus.entity;
/*
 * 20170308 DN c131-admin-manage-banner-upload-banner-image-dn implemented the initial interface 
 */
import java.sql.SQLException;
import java.util.Collection;

public interface ICrudSibling extends ICrud {
	
	public Collection<Collection<String>> getAll(Object o) throws SQLException,
	Exception ;

}
