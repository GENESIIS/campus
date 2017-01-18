package com.genesiis.campus.command;

//20161026 DN c10-contacting-us-page created CmdGenerateEmail.java

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdGenerateEmail implements ICommand {
	static Logger log = Logger.getLogger(CmdGenerateEmail.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		//getting the admin relateddata e.g emailadress
		// composing the email and sending will be executed
		
		
		return null;
	}

}
