package com.genesiis.campus.entity;

//20170221 DJ c145-add-enhanced-programme created DistrictICrud.java
//20170221 DJ c145-add-enhanced-programme Add method getCourseProviderTown(int providerCode).

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

	public Collection<Collection<String>> getCourseProviderTown(int providerCode)throws SQLException,Exception;
	
}
