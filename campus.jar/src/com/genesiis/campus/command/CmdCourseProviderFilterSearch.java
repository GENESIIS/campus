package com.genesiis.campus.command;
//DJ 20161117 c17-provider-criteria-based-filter-search created CmdCourseProviderFilterSearch.java
//DJ 20161117 c17-provider-criteria-based-filter-search Implement execute() method
//DJ 20161124 c17-provider-criteria-based-filter-search retrieved input select values to command class

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			Map<String, String[]> bandInfo = helper.getParameterMap();
			if (!bandInfo.isEmpty()) {

				// String[] selectAllMap=bandInfo.get("selectAllMap");
				String[] cpTypeAll = bandInfo.get("mainAreasMap[cpTypeAll]");
				String[] majorAll = bandInfo.get("mainAreasMap[majorAll]");
				String[] levelAll = bandInfo.get("mainAreasMap[levelAll]");
				String[] catCodeArray = bandInfo.get("categoryCodes[]");
				String[] districtCode = bandInfo.get("districtCode");
				String[] cpTypeAyrray =bandInfo.get("cpTypeCodes[]");
				String[] majorAyrray =bandInfo.get("majorCodes[]");
				String[] levelAyrray =bandInfo.get("levelCodes[]");
				

				if (catCodeArray != null && catCodeArray.length > 0) {
					final List<Integer> codeList = new ArrayList<Integer>();
					for (String code : catCodeArray) {
						codeList.add(Integer.parseInt(code));
					}
					//Assume one category can be selected at once from gui
					providerSearchDTO.setCategory(codeList.get(0));
				}
				if (districtCode != null && districtCode.length > 0 && !districtCode[0].isEmpty()) {					
					int district = Integer.parseInt(districtCode[0]);
					providerSearchDTO.setDistrict(district);
				}
			
				
				if (cpTypeAll != null && cpTypeAll.length > 0) {
					if(cpTypeAll[0].equalsIgnoreCase("true")){						
						providerSearchDTO.setGetAllCPTypes(true);
					}
				}
				if (majorAll != null && majorAll.length > 0) {
					if(majorAll[0].equalsIgnoreCase("true")){						
						providerSearchDTO.setGetAllMajors(true);
					}
				}
				if (levelAll != null && levelAll.length > 0) {
					if(levelAll[0].equalsIgnoreCase("true")){						
						providerSearchDTO.setGetAllMajors(true);
					}
				}
				
				if (cpTypeAyrray != null && cpTypeAyrray.length > 0) {
					final List<Integer> codeList = new ArrayList<Integer>();
					for (String code : cpTypeAyrray) {
						codeList.add(Integer.parseInt(code));
					}
					providerSearchDTO.setCpTypeList(codeList);
				}
				if (majorAyrray != null && majorAyrray.length > 0) {
					final List<Integer> codeList = new ArrayList<Integer>();
					for (String code : majorAyrray) {
						codeList.add(Integer.parseInt(code));
					}
					providerSearchDTO.setMajorList(codeList);
				}
				if (levelAyrray != null && levelAyrray.length > 0) {
					final List<Integer> codeList = new ArrayList<Integer>();
					for (String code : levelAyrray) {
						codeList.add(Integer.parseInt(code));
					}
					providerSearchDTO.setLevelList(codeList);
				}
			}
			 
			//Arrays.asList(bandInfo.get("mainAreasMap[cpTypeAll]"));
		
		/*	int categoryCode = 0;
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
			}*/
						
			
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
