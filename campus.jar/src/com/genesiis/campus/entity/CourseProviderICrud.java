package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view created CourseProviderICrud.java
//20170108 c6-list-available-institutes-on-the-view Declaring new methods 

import com.genesiis.campus.entity.model.CourseProviderResultDTO;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**The class  {@code CourseProviderICrud} is a form of Interface class.
 * The Interface {@code CourseProviderICrud} has precise control over course provider dao level manipulations. 
 *  @author dumani DJ   
 */
public interface CourseProviderICrud extends ICrud{	
	
   //Retrieve all the fields of course provider table
	public Collection<Collection<String>> getAllCourseProviders()throws SQLException,Exception;
	
	//Retrieve all the fields of course provider table
	public Collection<Collection<String>> getAllCourseProviders(CourseProviderSearchDTO providerSearchDTO)throws SQLException,Exception;
	
	//Retrieve basic fields of course provider table
	public Collection<Collection<String>> getLightAllCourseProviders()throws SQLException,Exception;
	
	//Retrieve basic fields of course provider table
	public Collection<Collection<String>> getLightAllCourseProviders(CourseProviderSearchDTO providerSearchDTO)throws SQLException,Exception;
	
	//Retrieve a course provider details by course provider code
	public Collection<Collection<String>> getCourseProviderById(Integer courseProviderCode)throws SQLException,Exception;
	
	 //Retrieve all the fields of Course Provider Type table
	public Collection<Collection<String>> getAllCourseProviderTypes()throws SQLException,Exception;
	
	//TODO:For Future benefits
	/*public int addCourseProvider(CourseProvider courseProvider) throws SQLException, Exception;
	
	public int updateCourseProvider(CourseProvider courseProvider) throws SQLException, Exception;*/
	
	public Collection<Collection<String>> findTopViewedProviders(CourseProviderSearchDTO provider) throws SQLException,Exception;
	
	public Collection<Collection<String>> findTopRatedProviders(CourseProviderSearchDTO provider) throws SQLException,Exception;
	
	public Collection<Collection<String>> findFilterdCourseProviders(CourseProviderSearchDTO provider) throws SQLException,Exception;
	
	public List<CourseProviderResultDTO> getCategoryWiseTypes(Integer categoryCode)throws SQLException,Exception;
	
	public Collection<Collection<String>> findCPTypesByCPTypeCodes(Set<Integer> cpTypeCodeSet)throws SQLException,Exception;	
		
}
