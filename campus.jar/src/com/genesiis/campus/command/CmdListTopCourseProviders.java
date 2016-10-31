package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListTopCourseProviders.java

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListTopCourseProviders implements ICommand   {

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
			iview.setCollection(institutes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
