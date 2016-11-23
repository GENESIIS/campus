package com.genesiis.campus.command;
//DJ 20161117 c17-provider-criteria-based-filter-search created CmdCourseProviderFilterSearch.java
//DJ 20161117 c17-provider-criteria-based-filter-search Implement execute() method

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdCourseProviderFilterSearch implements ICommand  {
	
	static Logger log = Logger.getLogger(CmdCourseProviderFilterSearch.class.getName());
	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return course provider filter search result collection
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		try {
			
			Map<String, String[]> bandInfo = helper.getParameterMap();
			//bandInfo.get("CCO")
			//bandInfo.get("searchData")
			
			 String selectAllMap=Arrays.asList(bandInfo.get("selectAllMap")).get(0);
			 String mainAreasMap=Arrays.asList(bandInfo.get("mainAreasMap")).get(0);


			
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			int categoryCode = 0;
			//Set input filter values to providerSearchDTO			
			String categoryCodeString = helper.getParameter("categoryCode");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
					providerSearchDTO.setCategory(categoryCode);
				}				
			}
			
			String courseCountString = helper.getParameter("courseCount");
			if (UtilityHelper.isNotEmpty(courseCountString)) {
				if (UtilityHelper.isInteger(courseCountString) && Integer.parseInt(courseCountString)>0) {					
					providerSearchDTO.setGetAll(true);
				}				
			}
			
			
			
			//TODO:set filter values
			//isgetAll == true--> set all the param to 0
			/*providerSearchDTO.setCourserProviderType(courserProviderType);
			providerSearchDTO.setMajor(major);
			providerSearchDTO.setLevel(level);
			providerSearchDTO.setDistrict(district);*/
			
			
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
