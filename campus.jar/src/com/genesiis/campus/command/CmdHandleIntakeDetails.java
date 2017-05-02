package com.genesiis.campus.command;

//20170205 DJ c145-add-enhanced-programme-MP-dj INT- CmdHandleIntakeDetails.java

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;


/**
 * The class {@code CmdHandleIntakeDetails} is a form of Command class.It is created for the purpose of handling commands of
 * intake details. 
 * @author dumani DJ
 *
*/
public class CmdHandleIntakeDetails implements ICommand{
	
	static Logger log = Logger.getLogger(CmdHandleIntakeDetails.class.getName());
	final ProgrammeICrud programmeDAO = new ProgrammeDAOImpl();

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		String ccoString = helper.getParameter("CCO");
		try {
			if ("ADD_INTAKE_DETAILS".equalsIgnoreCase(ccoString)) {
				
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return iView;
	}

}
