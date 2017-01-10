package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListTopCourseProviders.java
//DJ 20161103 c6-list-available-institutes-on-the-view Implemented execute()
//DJ 20161103 c6-list-available-institutes-on-the-view adjust the implementation to support dynamic category code 
//DJ 20161109 c6-list-available-institutes-on-the-view add topRatedCourseProviders implementation
//DJ 20161123 c6-list-available-institutes-on-the-view add SystemConfig.PROVIDER_LOGO_PATH

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdListTopCourseProviders implements ICommand   {
	
	static Logger log = Logger.getLogger(CmdListTopCourseProviders.class.getName());
	
	/**	 * 
	 * @author dumani DJ
	 * @param helper IDataHelper Object
	 * @param view IView Object
	 * @return Top rated and top viewed course providers
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {								
		String contextDeployLogoPath=SystemConfig.PROVIDER_LOGO_PATH.getValue1();
		
		try {
			int categoryCode = 0;
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			String categoryCodeString = helper.getParameter("category");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
					providerSearchDTO.setCategory(categoryCode);
					helper.setAttribute("categoryCode", categoryCode);
				}				
			}
			//Set Course provider status
			providerSearchDTO.setCourseProviderStatus(ApplicationStatus.ACTIVE.getStatusValue());
			final CourseProviderICrud providerDAO = new CourseProviderDAOImpl();
			final Collection<Collection<String>> topViewedCourseProviders = providerDAO.findTopViewedProviders(providerSearchDTO);
			final Collection<Collection<String>> topRatedCourseProviders = providerDAO.findTopRatedProviders(providerSearchDTO);
			
			iview.setCollection(topViewedCourseProviders);
			helper.setAttribute("tRCProviders", topRatedCourseProviders);
			helper.setAttribute("contextDeployLogoPath", contextDeployLogoPath);
		} catch (Exception exception) {
			log.error("execute() :Exception  " + exception);			
			throw exception;
		}
		return iview;
	}

}
