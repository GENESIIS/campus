package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListTopCourseProviders.java
//DJ 20161103 c6-list-available-institutes-on-the-view Implemented execute()

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdListTopCourseProviders implements ICommand   {
	
	static Logger log = Logger.getLogger(CmdListTopCourseProviders.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		int categoryCode = 0;		
		try {
			final CourseProvider provider = new CourseProvider();
			categoryCode=1;
			final Collection<Collection<String>> institutes = providerDAO.findById(provider);
			
			final Collection<Collection<String>> topViewedCourseProviders = providerDAO.findTopViewedProviders(provider);
			iview.setCollection(topViewedCourseProviders);
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return iview;
	}

}
