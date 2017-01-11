package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view created DistrictICrud.java
//20170108 c6-list-available-institutes-on-the-view Declaring getAllDistricts() methods

import java.sql.SQLException;
import java.util.Collection;

/**The class  {@code DistrictICrud} is a form of Interface class.
* The Interface {@code DistrictICrud} has precise control over district, town dao level manipulations.
* 
*  Interface declare methods related to District table and tables around it, join directly and meaningfully.
*  For an example; methods related Town, DSD etc. 
*  @author dumani DJ   
*/
public interface DistrictICrud extends ICrud {
	public Collection<Collection<String>> getAllDistricts() throws SQLException,Exception;
}
