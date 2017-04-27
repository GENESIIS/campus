package com.genesiis.campus.command;

//20170202 DJ c145-add-enhanced-programme-MP-dj Initiate CmdHandleSemesterDetails.java

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * The class {@code CmdHandleSemesterDetails} is a form of Command class.It is created for the purpose of handling commands of
 * semester details. 
 * @author dumani DJ
 *
*/
public class CmdHandleSemesterDetails implements ICommand{
	static Logger log = Logger.getLogger(CmdHandleSemesterDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		String ccoString = helper.getParameter("CCO");
		try {
			if ("ADD_SEMESTER_DETAILS".equalsIgnoreCase(ccoString)) {
				manageSemesterInsertion(helper, iView);
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return iView;
	}

	private void manageSemesterInsertion(IDataHelper helper, IView iView) {
		// TODO Auto-generated method stub
		
	}

}
