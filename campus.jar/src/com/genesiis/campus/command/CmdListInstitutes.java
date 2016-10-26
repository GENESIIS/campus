package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListInstitutes.java
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteProviderDAO;
import com.genesiis.campus.entity.model.Institutes;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdListInstitutes implements ICommand  {
	static Logger log = Logger.getLogger(CmdListInstitutes.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		final InstituteProviderDAO providerDAO=new InstituteProviderDAO();		
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		final Collection<Collection<String>> institutes =new ArrayList<Collection<String>>();
		try {
			final Institutes institute=new Institutes();
			providerDAO.findById(institute);
			
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return null;
	}

}
