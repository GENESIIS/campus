package com.genesiis.campus.entity;

//20170420 DJ c54-report-course-stats-MP-dj Initiated CourseProviderICrud.java , getLightAllCourseProviders method.

import com.genesiis.campus.entity.model.CourseProviderSearchDTO;

import java.sql.SQLException;
import java.util.Collection;

/**The class  {@code CourseProviderICrud} is a form of Interface class.
 * The Interface {@code CourseProviderICrud} has precise control over course provider dao level manipulations.
 * 
 *  Interface declare methods related to course provider table and tables around it, join directly and meaningfully.
 *  For an example; methods related course provider types. 
 *  @author dumani DJ   
 */
public interface CourseProviderICrud extends ICrud{	
	
	//Retrieve basic fields of course provider table
	public Collection<Collection<String>> getLightAllCourseProviders(CourseProviderSearchDTO providerSearchDTO)throws SQLException,Exception;
			
}
