package com.genesiis.campus.command;

//20161208 JH c39-add-course-provider CmdAddCourseProviderAccount.java created

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.UsernameDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdAddCourseProviderAccount implements ICommand{
	static org.apache.log4j.Logger log = Logger.getLogger(CmdAddCourseProviderAccount.class.getName());
	
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {


	String action = 	helper.getParameter("action");
	
	if(action.equalsIgnoreCase("USERNAME_VALIDATION")){//used when validating a username
		ICrud usernameDAO = new UsernameDAO();
		String username = helper.getParameter("username");
		
		  usernameDAO.findById(username);

		
	}
	else if(action.equalsIgnoreCase("PREFIX_VALIDATION")){//used when validating prefix
		
	}
		return view;
	}

}
