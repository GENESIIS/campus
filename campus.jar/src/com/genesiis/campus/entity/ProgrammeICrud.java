package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view Initiated ProgrammeICrud.java
//20170108 c6-list-available-institutes-on-the-view Declaring new methods
//20170202 DJ c138-add-basic-programme-MP-dj Initiate getAllClassTypes() method.
//20170316 DJ c152-add-enhanced-programme-insertion: create method;addIntakeDetails(),addSemesterDetails(),addModuleDetails

import com.genesiis.campus.entity.model.IntakeDTO;
import com.genesiis.campus.entity.model.ModuleDTO;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.entity.model.SemesterDTO;

import java.sql.SQLException;
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
	
	public int addIntakeDetails(final IntakeDTO intakeDTO) throws SQLException, Exception;
	
	public int addSemesterDetails(final SemesterDTO semesterDTO) throws SQLException, Exception;	
	
	public int addModuleDetails(final ModuleDTO moduleDTO) throws SQLException, Exception;	

}
