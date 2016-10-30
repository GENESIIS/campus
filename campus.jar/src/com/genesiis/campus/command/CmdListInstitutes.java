package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListInstitutes.java
//DJ 20161026 c6-list-available-institutes-on-the-view implementing execute() method

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteProviderDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdListInstitutes implements ICommand  {
	static Logger log = Logger.getLogger(CmdListInstitutes.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {
		final InstituteProviderDAO providerDAO=new InstituteProviderDAO();		
		SystemMessage systemMessage = SystemMessage.UNKNOWN;		
		try {
			int categoryCode =0;
			final CourseProvider institute=new CourseProvider();
			if (UtilityHelper.isNotEmpty( helper.getParameter("category"))) {
				categoryCode = Integer.parseInt(helper.getParameter("category"));
				institute.setCategory(categoryCode);
			}
			final Collection<Collection<String>> institutes=providerDAO.findById(institute);
			iview.setCollection(institutes);
			//helper.setAttribute("courseProviders", institutes);
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return iview;
	}

}
