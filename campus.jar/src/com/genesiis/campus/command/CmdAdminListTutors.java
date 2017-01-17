package com.genesiis.campus.command;

//20170117 JH c133-admin-list-tutors CmdAdminListTutors.java created

import java.sql.SQLException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdAdminListTutors implements ICommand{
	
	static com.sun.media.jfxmedia.logging.Logger log = Logger.getLogger(CmdAdminListTutors.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();
		
try{

	
	
	
}catch(SQLException sqlException){
	log.error("execute(IDataHelper, IView) : SQLException" + sqlException.toString());
	throw sqlException;
}catch(Exception exception){
	log.error("execute(IDataHelper, IView) : Exception" + exception.toString());
	throw exception;
}
		
		return null;
	}

}
