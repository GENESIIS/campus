package com.genesiis.campus.command;

//20161025 JH c7-list-higher-education-courses command class CmdListInstitutes.java created
//20161025 JH c7-list-higher-education-courses execute method modified
//20161026 JH c7-higher-education-landing-page CmdListInstitutes.java class renamed as CmdListCourseProviders.java

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdListCourseProviders implements ICommand{

	static org.apache.log4j.Logger log = Logger.getLogger(CmdListCourseProviders.class.getName());	
	
	/**
	 * used to list institutes based on the given criteria
	 * @param IDataHelper
	 * @param IView
	 * @return IView
	 * @author JH
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		
		
		return null;
	}

}
