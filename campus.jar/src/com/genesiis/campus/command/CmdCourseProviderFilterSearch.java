package com.genesiis.campus.command;
//DJ 20161117 c17-provider-criteria-based-filter-search created CmdCourseProviderFilterSearch.java

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdCourseProviderFilterSearch implements ICommand  {
	
	static Logger log = Logger.getLogger(CmdListMoreCourseProviders.class.getName());
	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return course provider filter search
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		try {
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			
			final Collection<Collection<String>> courseProviderSearchResults = providerDAO.findFilterdCourseProviders(providerSearchDTO);
			iView.setCollection(courseProviderSearchResults);
			
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}		
		
		return iView;
	}

}
