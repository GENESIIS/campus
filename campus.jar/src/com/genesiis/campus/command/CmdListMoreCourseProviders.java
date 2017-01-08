package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListInstitutes.java
//DJ 20161026 c6-list-available-institutes-on-the-view implementing execute() method
//DJ 20161030 c6-list-available-institutes-on-the-view identified get all institutes
//DJ 20161110 c6-list-available-institutes-on-the-view identified get all and category wise course providers
//DJ 20161123 c6-list-available-institutes-on-the-view add SystemConfig.PROVIDER_LOGO_PATH
//DJ 20170108 c6-list-available-institutes-on-the-view Adjust dao call to new icrud interfaces

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CategoryDAO;
import com.genesiis.campus.entity.dao.CourseProviderDAO;
import com.genesiis.campus.entity.dao.DistrictDAO;
import com.genesiis.campus.entity.dao.ProgrammeDAO;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

public class CmdListMoreCourseProviders implements ICommand {
	static Logger log = Logger.getLogger(CmdListMoreCourseProviders.class
			.getName());
	
	/**
	 * @author dumani DJ
	 * @param helper IDataHelper Object
	 * @param iview IView Object
	 * @return List course provides.Identify the categorycode and retrieve appropriate course providers. 
	 */
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {
				
		String contextDeployLogoPath=SystemConfig.PROVIDER_LOGO_PATH.getValue1();
		try {
			int categoryCode = 0;
			final CourseProviderSearchDTO provider = new CourseProviderSearchDTO();
			String categoryCodeString = helper.getParameter("categoryCode");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
					provider.setCategory(categoryCode);
				}				
			}
			final CourseProviderICrud  providerDAO = new CourseProviderDAO();
			if (categoryCode > 0) {
				final Collection<Collection<String>> categoryWiseCourseProviders = providerDAO.findById(provider);
				iview.setCollection(categoryWiseCourseProviders);
			} else {
				final Collection<Collection<String>> allCourseProviders = providerDAO.getAll();
				iview.setCollection(allCourseProviders);
			}
			//List Category data for the drop down		
			final Collection<Collection<String>> categoryList=new ProgrammeDAO().getAllCategories();
			helper.setAttribute("categoryList", categoryList);
			
			//List location for the drop down
			final Collection<Collection<String>> districtList=new DistrictDAO().getAllDistricts();
			helper.setAttribute("districtList", districtList);			
			
			helper.setAttribute("contextDeployLogoPath", contextDeployLogoPath);
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}
		return iview;
	}

}
