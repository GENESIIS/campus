package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListInstitutes.java
//DJ 20161026 c6-list-available-institutes-on-the-view implementing execute() method
//DJ 20161030 c6-list-available-institutes-on-the-view identified get all institutes
//DJ 20161110 c6-list-available-institutes-on-the-view identified get all and category wise course providers
//DJ 20161123 c6-list-available-institutes-on-the-view add SystemConfig.PROVIDER_LOGO_PATH
//DJ 20170108 c6-list-available-institutes-on-the-view Adjust dao call to new icrud interfaces
//DJ 20170116 c123-general-filter-search-course-provider-MP-dj created Enhanced execute() method with course provider code list.

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.DistrictDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The class {@code CmdListMoreCourseProviders} is a form of Command class.It is created for the purpose of handling commands of
 * list more course providers. 
 * @author dumani DJ
 *
*/
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
				
		String courseProviderLogoPath=SystemConfig.PROVIDER_LOGO_PATH.getValue1();
		try {
			int categoryCode = 0;
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			String categoryCodeString = helper.getParameter("categoryCode");			
			String [] cpCodeList=helper.getParameterValues("cpCodeList[]");
			String generalSearchFlag=helper.getParameter("generalSearchFlag");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
					providerSearchDTO.setCategory(categoryCode);
				}				
			}
			final List<Integer> courseProviderCodeList = new ArrayList<Integer>();
			if (cpCodeList != null && cpCodeList.length > 0) {			
				for (String code : cpCodeList) {
					courseProviderCodeList.add(Integer.valueOf(code));
				}
				providerSearchDTO.setCourseProviderCodeList(courseProviderCodeList);				
			}
			providerSearchDTO.setCourseProviderStatus(ApplicationStatus.ACTIVE.getStatusValue());
			
			final CourseProviderICrud  providerDAO = new CourseProviderDAOImpl();
			Collection<Collection<String>> allCourseProviders = new ArrayList<Collection<String>>();
			if(!(providerSearchDTO.getCourseProviderCodeList()==null && generalSearchFlag.equalsIgnoreCase("TRUE"))){
			   allCourseProviders=providerDAO.getLightAllCourseProviders(providerSearchDTO);			   
			}	
			iview.setCollection(allCourseProviders);

			//List Category data for the drop down		
			final Collection<Collection<String>> categoryList=new ProgrammeDAOImpl().getAllCategories();
			helper.setAttribute("categoryList", categoryList);
			
			//List location for the drop down
			final Collection<Collection<String>> districtList=new DistrictDAOImpl().getAllDistricts();
			helper.setAttribute("districtList", districtList);			
			
			helper.setAttribute("courseProviderLogoPath", courseProviderLogoPath);
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}
		return iview;
	}

}