package com.genesiis.campus.command;
//20161123 AS C19-student-login-without-using-third-party-application-test-as CmdStudentLogin class created.
import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdStudentLogin implements ICommand{

	static Logger log = Logger.getLogger(CmdStudentLogin.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		log.info(helper);

		return null;
	}

}
