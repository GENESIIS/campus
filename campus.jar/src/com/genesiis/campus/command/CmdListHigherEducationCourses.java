package com.genesiis.campus.command;


import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdListHECourses implements ICommand {
	static org.apache.log4j.Logger log = Logger.getLogger(CmdListHECourses.class.getName());
	private IView programmeData;
	
	public CmdListHECourses(IView programmeData){
		this.programmeData = programmeData;
	}
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
