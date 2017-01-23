package com.genesiis.campus.command;

//20170123 JH c135-admin-tutor-keyword-search created CmdAdminSearchTutors.java

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

public class CmdAdminSearchTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdAdminSearchTutors.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		SystemMessage systemMessage = SystemMessage.NO_DATA;
		
		try{
			String searchKey = helper.getParameter("searchKeyWord");
			if(!Validator.isEmptyString(searchKey)){
				
			}
		}catch(Exception exception){
			log.error("execute() Exception : " + exception.toString());
			throw exception;
		}
		return null;
	}
	

}
