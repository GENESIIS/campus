package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view created CourseProviderICrud.java

import com.genesiis.campus.entity.model.CourseProviderResultDTO;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface CourseProviderICrud extends ICrud{
		
	Collection<Collection<String>> findTopViewedProviders(CourseProviderSearchDTO provider) throws SQLException,Exception;
	
	Collection<Collection<String>> findTopRatedProviders(CourseProviderSearchDTO provider) throws SQLException,Exception;
	
	Collection<Collection<String>> findFilterdCourseProviders(CourseProviderSearchDTO provider) throws SQLException,Exception;
	
	List<CourseProviderResultDTO> getCategoryWiseTypes(Integer categoryCode)throws SQLException,Exception;

}
