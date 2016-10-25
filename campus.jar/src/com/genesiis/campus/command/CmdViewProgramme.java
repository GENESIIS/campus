package com.genesiis.campus.command;

//20161025 CM c13-Display course details INIT CmdViewProgramme.java

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdViewProgramme implements ICommand{

	static Logger log = Logger.getLogger(CmdViewProgramme.class.getName());
	private IView programmeData;
	
	public CmdViewProgramme(IView programmeData) {
		this.programmeData=programmeData;
	}
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		return null;
	}

}
