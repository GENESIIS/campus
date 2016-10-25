package com.genesiis.campus.command;


import java.sql.SQLException;

//20161025 JH c7-list-higher-education-courses command class CmdListHigherEducationCourses.java created
//20161025 JH c7-list-higher-education-courses execute method created and coding

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdListHigherEducationCourses implements ICommand {
	static org.apache.log4j.Logger log = Logger.getLogger(CmdListHigherEducationCourses.class.getName());
	private IView programmeData;
	
	public CmdListHigherEducationCourses(IView programmeData){
		this.programmeData = programmeData;
	}
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
