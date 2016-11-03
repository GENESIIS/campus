package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListTopCourseProviders.java
//DJ 20161103 c6-list-available-institutes-on-the-view Implemented execute()
//DJ 20161103 c6-list-available-institutes-on-the-view adjust the implementation to support dynamic category code  

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdListTopCourseProviders implements ICommand   {
	
	static Logger log = Logger.getLogger(CmdListTopCourseProviders.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;				
		try {
			int categoryCode = 0;
			final CourseProvider provider = new CourseProvider();
			String categoryCodeString = helper.getParameter("category");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
					provider.setCategory(categoryCode);
				}
				provider.setGetAll(false);
			} else {
				// Setting the flag for retrieve all the institutes
				provider.setGetAll(true);
			}			
			//final Collection<Collection<String>> institutes = providerDAO.findById(provider);			
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
