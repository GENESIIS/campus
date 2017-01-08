package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view created TownICrud.java
//20170108 c6-list-available-institutes-on-the-view Declaring getAllDistricts() methods

import java.sql.SQLException;
import java.util.Collection;

/**The class  {@code TownICrud} is a form of Interface class.
 * The Interface {@code TownICrud} has precise control over district, town dao level manipulations. 
 *  @author dumani DJ   
 */
public interface TownICrud extends ICrud {
	public Collection<Collection<String>> getAllDistricts() throws SQLException,Exception;
}
