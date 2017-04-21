package com.genesiis.campus.entity;
//20170421 DJ c54-report-course-stats-MP-dj Init:ProgrammeICrud.java

import com.genesiis.campus.entity.model.ProgrammeSearchDTO;

import java.sql.SQLException;
import java.util.Collection;


/**The class  {@code ProgrammeICrud} is a form of Interface class.
* The Interface {@code ProgrammeICrud} has precise control over programme dao level manipulations. 
*  @author dumani DJ   
*/
public interface ProgrammeICrud {
	
	public Collection<Collection<String>> getLightProgrammes(ProgrammeSearchDTO programmeSearchDTO)throws SQLException,Exception;
	
}
