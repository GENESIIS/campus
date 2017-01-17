package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view Initiated ProgrammeICrud.java
//20170108 c6-list-available-institutes-on-the-view Declaring new methods

import com.genesiis.campus.entity.model.ProgrammeSearchDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

/**The class  {@code ProgrammeICrud} is a form of Interface class.
 * The Interface {@code ProgrammeICrud} has precise control over programme dao level manipulations. 
 *  @author dumani DJ   
 */
public interface ProgrammeICrud extends ICrud {
	
	//TODO: Declared for presentation purpose and for Future benefits.
	/*public Collection<Collection<String>> getAllMajors()throws SQLException,Exception;	
	
	public Collection<Collection<String>> getAllLevels()throws SQLException,Exception;
	
	public Collection<Collection<String>> getAllSemesters()throws SQLException,Exception;
	
	public Collection<Collection<String>> getAllProgrammeInterests()throws SQLException,Exception;*/
	
	public Collection<Collection<String>> getAllCategories()throws SQLException,Exception;
	
	public Collection<Collection<String>> findMajorsByMajorCodes(Set<Integer> majorCodeSet)throws SQLException,Exception;
	
	public Collection<Collection<String>> findLevelsByLevelCodes(Set<Integer> levelCodeSet)throws SQLException,Exception;	
	
	public Collection<Collection<String>> getProgrammesForReport(ProgrammeSearchDTO programmeSearchDTO)throws SQLException,Exception;	

}
