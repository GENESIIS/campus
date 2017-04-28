package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view Initiated ProgrammeICrud.java
//20170108 c6-list-available-institutes-on-the-view Declaring new methods
//20170202 DJ c138-add-basic-programme-MP-dj Initiate getAllClassTypes() method.
//20170428 DJ c145-add-enhanced-programme-MP-dj Initiated addSemesterDetails() method.
//20170428 DJ c145-add-enhanced-programme-MP-dj Initiated addModuleDetails() method.

import com.genesiis.campus.entity.model.ModuleDTO;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.entity.model.SemesterDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**The class  {@code ProgrammeICrud} is a form of Interface class.
 * The Interface {@code ProgrammeICrud} has precise control over programme dao level manipulations. 
 *  @author dumani DJ   
 */
public interface ProgrammeICrud extends ICrud {
	
	//TODO: Declared for presentation purpose and for Future benefits.
	public Collection<Collection<String>> getAllMajors()throws SQLException,Exception;	
	
	public Collection<Collection<String>> getAllLevels()throws SQLException,Exception;
	
	public Collection<Collection<String>> getAllClassTypes()throws SQLException,Exception;
	
	/*public Collection<Collection<String>> getAllSemesters()throws SQLException,Exception;
	
	public Collection<Collection<String>> getAllProgrammeInterests()throws SQLException,Exception;*/
	
	public Collection<Collection<String>> getAllCategories()throws SQLException,Exception;
	
	public Collection<Collection<String>> findMajorsByMajorCodes(Set<Integer> majorCodeSet)throws SQLException,Exception;
	
	public Collection<Collection<String>> findLevelsByLevelCodes(Set<Integer> levelCodeSet)throws SQLException,Exception;

	public int addProgrammeDetails(ProgrammeDTO programmeDTO) throws SQLException, Exception;
	
	public int[] addSemesterDetails(ArrayList<SemesterDTO> semesterList) throws SQLException, Exception;
	
	public int[] addModuleDetails(ArrayList<ModuleDTO> moduleList) throws SQLException, Exception;	

}
