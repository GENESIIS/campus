package com.genesiis.campus.command;

//20170428 DJ c145-add-enhanced-programme-MP-dj Initiate CmdHandleModuleDetails.java

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import java.sql.SQLException;

/**
 * The class {@code CmdHandleModuleDetails} is a form of Command class.It is created for the purpose of handling commands of
 * module details. 
 * @author dumani DJ
 *
*/
public class CmdHandleModuleDetails implements ICommand {
	static Logger log = Logger.getLogger(CmdHandleModuleDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		String ccoString = helper.getParameter("CCO");
		try {
			if ("ADD_SEMESTER_DETAILS".equalsIgnoreCase(ccoString)) {
				manageModuleInsertion(helper, iView);
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return iView;
	}

	/** Manage form data(input values) for database insertion.
	 * @author DJ dumani
	 * @param helper 	  
	 * @param iView 
	 */	
	private void manageModuleInsertion(IDataHelper helper, IView iView) {
				
	}

}
