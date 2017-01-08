package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view created StudentICrud.java

import java.sql.SQLException;
import java.util.Collection;

/**The class  {@code StudentICrud} is a form of Interface class.
 * The Interface {@code StudentICrud} has precise control over Student related dao level manipulations. 
 *  @author dumani DJ   
 */
public interface StudentICrud extends ICrud {
	
	public Collection<Collection<String>> getAllStudentInterests()throws SQLException,Exception;
	public Collection<Collection<String>> getAllStudentSkills()throws SQLException,Exception;

}
