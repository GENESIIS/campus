package com.genesiis.campus.command;

//20161025 JH c7-list-higher-education-courses command class CmdListInstitutes.java created
//20161025 JH c7-list-higher-education-courses execute method modified

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdListInstitutes implements ICommand{

	static org.apache.log4j.Logger log = Logger.getLogger(CmdListInstitutes.class.getName());	
	
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
