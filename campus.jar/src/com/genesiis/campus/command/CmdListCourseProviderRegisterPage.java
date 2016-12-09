package com.genesiis.campus.command;

//20161209 JH c39-add-course-provider CmdListCourseProviderRegisterPage.java created

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.util.IDataHelper;

public class CmdListCourseProviderRegisterPage implements ICommand{
	static Logger log = Logger.getLogger(CmdListCourseProviderRegisterPage.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
