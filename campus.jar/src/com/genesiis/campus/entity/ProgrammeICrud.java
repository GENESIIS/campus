package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view Initiated ProgrammeICrud.java

/**The class  {@code ProgrammeICrud} is a form of Interface class.
 * The Interface {@code ProgrammeICrud} has precise control over programme dao level manipulations. 
 *  @author dumani DJ   
 */
public interface ProgrammeICrud extends ICrud {
	
	getAllMajors();
	getAllCategories();
	getAllLevels();
	getAllSemesters();
	getAllProgrammeInterests;
	

}
