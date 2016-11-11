package com.genesiis.campus.command;

//20161026 MM c5-corporate-training-landing-page INIT CmdListCorporateProgrammes.java
//20161027 MM c5-corporate-training-landing-page Modified execute() method to include 
// 				fetching of CourseProviders
//20161027 MM c5-corporate-training-landing-page Modified execute() method to re-use 
//				Programme object to pass argument to findById() method of CourseProviderDAO
//20161104 MM c5-corporate-training-landing-page Added code to support sending of levels or 
//				majors based on the category of the programme requested
//20161109 MM c5-corporate-training-landing-page-MP Changed organisation of major/level list
//				data to be in a List<List<String>>
//20161110 MM c5-corporate-training-landing-page-MP Modified code in the looping structure in 
// 				order to better structure the town list sent for each programme
//20161111 MM c5-corporate-training-landing-page-MP Modified code to remove tasks of paging 
//				the fetched result set. This is now the job of front-end code.  

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CategoryProgrammeDAO;
import com.genesiis.campus.entity.CategoryCourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListCategoryProgrammes implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListCategoryProgrammes.class.getName());

	final ICrud programmeDao = new CategoryProgrammeDAO();	
	final ICrud courseProviderDao = new CategoryCourseProviderDAO();	
		
	public CmdListCategoryProgrammes() {
		
	}

	@Override 
	public IView execute(IDataHelper helper, IView iview) throws Exception {
		
		String message = "";
		Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();
		List<String> msgList = new ArrayList<String>();
		int categoryCode = -1;
		int pageNum = -1;
		int numOfResultsPerPage = 20; // This value will be needed to be eventually fetched from DB
		String contextDeployLogoPath = "/education/provider/logo/";
		String contextDeployCourseLogoPath = "/course/";
		try {
			if (helper.getParameter("category") == null) {
				Log.error("The provided value for category is null!");
				msgList.add("The provided value for category is null!");
				throw new IllegalArgumentException("The provided value for category is null!");
			} 
			
			categoryCode = Integer.parseInt(helper.getParameter("category"));		
			
			Programme programme = new Programme();
			programme.setCategory(categoryCode);
			
			// Get programmes that belong to the same category as categoryCode
			programmeCollection = programmeDao.findById(programme);
			
			Map<String, ArrayList<List<String>>> programmeCodeToTownListMap = 
					new LinkedHashMap<String, ArrayList<List<String>>>();
			
			Map<String, List<String>> levelOrMajorCodeToLevelOrMajorDetailsMap = new LinkedHashMap<String, List<String>>();
			List<String> tempLevelOrMajorDetailsList = null;
			
			Map<String, Collection<String>> progCodeToProgrammeMap = new LinkedHashMap<String, Collection<String>>();
			int indexOfMajorOrLevelCode = categoryCode == 3 ? 12 : 14; // Value 3 here (for "Corporate Training category) must not be hard-coded, 
			// but there should be a mechanism to identify if a category returned from DB is of type "Corporate Training"
			
			int indexOfMajorOrLevelName = categoryCode == 3 ? 23 : 24; // Value 3 here (for "Corporate Training category) must not be hard-coded, 
			// but there should be a mechanism to identify if a category returned from DB is of type "Corporate Training"

			for (Collection<String> prog : programmeCollection) {
				int count  = 0;
				ArrayList<String> tempSingleTownDetailsList = null;
				String programmeCode = null;
				String townCode = null;
				String majorOrLevelCode = null;
				for (String field : prog) {
					if (count == 0) {
						programmeCode = field;						
						
						Collection<String> programmeRecord = progCodeToProgrammeMap.get(field);
						if (programmeRecord == null) {
							programmeRecord = prog;
							progCodeToProgrammeMap.put(field, prog);
						}
					}

					if (count == 20) {
						townCode = field;						
					}

					if (count == 21) {
						String currentTownName = field;
						if ((townCode != null && !townCode.isEmpty()) && (field != null && !field.isEmpty())) {
							ArrayList<List<String>> townList = programmeCodeToTownListMap.get(programmeCode);
							if (townList == null) {
								townList = new ArrayList<List<String>>();
								programmeCodeToTownListMap.put(programmeCode, townList);
							}
							if (townList.size() != 0) {
								boolean isTownAlreadyAdded = false; 
								for (List<String> singleTownDetailsList : townList) {
									if (singleTownDetailsList.get(0).equals(townCode)) {
										isTownAlreadyAdded = true;
									}
								}
								if (!isTownAlreadyAdded) {
									tempSingleTownDetailsList = new ArrayList<String>();
									tempSingleTownDetailsList.add(townCode);
									tempSingleTownDetailsList.add(currentTownName);
									townList.add(tempSingleTownDetailsList);
								}
							} else {
								tempSingleTownDetailsList = new ArrayList<String>();
								tempSingleTownDetailsList.add(townCode);
								tempSingleTownDetailsList.add(currentTownName);
								townList.add(tempSingleTownDetailsList);
							}							
						}					
					}

					if (count == indexOfMajorOrLevelCode) {
						majorOrLevelCode = field;					
					}

					if (count == indexOfMajorOrLevelName) {
						List<String> levelOrMajorList = levelOrMajorCodeToLevelOrMajorDetailsMap.get(majorOrLevelCode);
						if (levelOrMajorList == null) {
							levelOrMajorList = new ArrayList<String>();
							levelOrMajorCodeToLevelOrMajorDetailsMap.put(majorOrLevelCode, levelOrMajorList);
						}
						
						if (levelOrMajorList.size() == 0) {
							levelOrMajorList.add(majorOrLevelCode);
							levelOrMajorList.add(field);
						}
					}	
					
					count++;
				}
			}
			
			programmeCollection = progCodeToProgrammeMap.values();
			
			iview.setCollection(programmeCollection);
			helper.setAttribute("contextDeployLogoPath", contextDeployLogoPath);
			helper.setAttribute("contextDeployCourseLogoPath", contextDeployCourseLogoPath);
			helper.setAttribute("numOfResultsPerPage", numOfResultsPerPage);
			helper.setAttribute("levelOrMajorCollection", levelOrMajorCodeToLevelOrMajorDetailsMap.values());
			helper.setAttribute("programmeCodeToTownListMap", programmeCodeToTownListMap);
			
		} catch (NumberFormatException nfe) {
			Log.info("execute(IDataHelper, IView) : NumberFormatException " + nfe.toString());
			msgList.add(SystemMessage.ERROR.message());
			msgList.add(nfe.getMessage());	
			throw nfe;
		} catch (IllegalArgumentException iae) {
			Log.info("execute(IDataHelper, IView) : IllegalArgumentException " + iae.toString());
			msgList.add(SystemMessage.ERROR.message());
			msgList.add(iae.getMessage());	
		}  catch (Exception e) {
			Log.info("execute(IDataHelper, IView) : Exception " + e.toString());
			msgList.add(SystemMessage.ERROR.message());
			msgList.add("Unknown error occured while fetching records to display");
			throw e;
		}
		
		return  iview;
	}
	
}
