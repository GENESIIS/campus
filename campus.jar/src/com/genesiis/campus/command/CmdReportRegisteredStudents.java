package com.genesiis.campus.command;

//DJ 20161228 c53-report-registered-students-MP-dj created CmdReportRegisteredStudents.java

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;


public class CmdReportRegisteredStudents implements ICommand {
	
	static Logger log = Logger.getLogger(CmdReportCoursesByCourseProvider.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
