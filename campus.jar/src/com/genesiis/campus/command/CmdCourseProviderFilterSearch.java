package com.genesiis.campus.command;
//DJ 20161117 c17-provider-criteria-based-filter-search created CmdCourseProviderFilterSearch.java
//DJ 20161117 c17-provider-criteria-based-filter-search Implement execute() method
//DJ 20161124 c17-provider-criteria-based-filter-search retrieved input select values to command class
//DJ 20161216 c17-provider-criteria-based-filter-search Implement getSelectedCodeList() method

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAO;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
		final CourseProviderICrud providerDAO = new CourseProviderDAO();			
		try {
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			final Map<String, String[]> searchParamCollection = helper.getParameterMap();			
			
			if (searchParamCollection!=null && !searchParamCollection.isEmpty()) {
				populateFilterSearch(providerSearchDTO,searchParamCollection);
				final Collection<Collection<String>> courseProviderSearchResults = providerDAO.findFilterdCourseProviders(providerSearchDTO);
				iView.setCollection(courseProviderSearchResults);
			}			
			
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}		
		return iView;
	}
	
	/**
	 * @author DJ
	 * @param helper.getParameterMap()
	 * @param providerSearchDTO
	 * @return Populating filter search parameter collection 
	 */	
	private void populateFilterSearch(final CourseProviderSearchDTO providerSearchDTO,final Map<String, String[]> searchParamCollection) {
       //TODO:Kept it for future purpose
		/*String[] cpTypeAll = searchParamCollection.get("mainAreasMap[cpTypeAll]");
		String[] majorAll = searchParamCollection.get("mainAreasMap[majorAll]");
		String[] levelAll = searchParamCollection.get("mainAreasMap[levelAll]");*/
		String[] catCodeArray = searchParamCollection.get("categoryCodes[]");
		String[] districtCode = searchParamCollection.get("districtCode");
		String[] cpTypeArray = searchParamCollection.get("cpTypeCodes[]");
		String[] majorArray = searchParamCollection.get("majorCodes[]");
		String[] levelArray = searchParamCollection.get("levelCodes[]");

		if (catCodeArray != null && catCodeArray.length > 0) {
			final List<Integer> codeList = new ArrayList<Integer>();
			for (String code : catCodeArray) {
				codeList.add(Integer.parseInt(code));
			}
			// Assume one category can be selected at once from gui
			providerSearchDTO.setCategoryList(codeList);
		}
		if (districtCode != null && districtCode.length > 0
				&& !districtCode[0].isEmpty()) {
			int district = Integer.parseInt(districtCode[0]);
			providerSearchDTO.setDistrict(district);
		}
		providerSearchDTO.setCpTypeList(getSelectedCodeList(cpTypeArray));
		providerSearchDTO.setMajorList(getSelectedCodeList(majorArray));
		providerSearchDTO.setLevelList(getSelectedCodeList(levelArray));
	}

	/**
	 * @author DJ
	 * @param selectedCodesArray 
	 * @return Get  list of integer codes form string array 
	 */	
	private List<Integer> getSelectedCodeList(String[] selectedCodesArray) {
		final List<Integer> codeList = new ArrayList<Integer>();
		if (selectedCodesArray != null && selectedCodesArray.length > 0) {			
			for (String code : selectedCodesArray) {
				codeList.add(Integer.valueOf(code));
			}			
		}
		return codeList;
	}
}
