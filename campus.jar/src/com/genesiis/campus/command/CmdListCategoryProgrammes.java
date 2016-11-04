package com.genesiis.campus.command;

//20161026 MM c5-corporate-training-landing-page INIT CmdListCorporateProgrammes.java
//20161027 MM c5-corporate-training-landing-page Modified execute() method to include 
// 				fetching of CourseProviders
//20161027 MM c5-corporate-training-landing-page Modified execute() method to re-use 
//				Programme object to pass argument to findById() method of CourseProviderDAO
//20161104 MM c5-corporate-training-landing-page Added code to support sending of levels or 
//				majors based on the category of the programme requested

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
import com.genesiis.campus.entity.CourseProviderCategoryProgrammeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListCategoryProgrammes implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListCategoryProgrammes.class.getName());

	final ICrud programmeDao = new CategoryProgrammeDAO();	
	final ICrud courseProviderDao = new CourseProviderCategoryProgrammeDAO();	
		
	public CmdListCategoryProgrammes() {
		
	}

	@Override 
	public IView execute(IDataHelper helper, IView iview) throws Exception {
		
		String message = "";
		Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> courseProviderCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> courseProvidersWithPopularCourses = new ArrayList<Collection<String>>();
		List<String> msgList = new ArrayList<String>();
		int categoryCode = -1;
		int pageNum = -1;
		int numOfProgrammesPerPage = 20;
		String contextDeployLogoPath = "/education/provider/logo/";
		String contextDeployCourseLogoPath = "/course/";
		try {
			if (helper.getParameter("category") == null) {
				Log.error("The provided value for category is null!");
				msgList.add("The provided value for category is null!");
				throw new IllegalArgumentException("The provided value for category is null!");
			} 
			
			categoryCode = Integer.parseInt(helper.getParameter("category"));
			pageNum = Integer.parseInt(helper.getParameter("pageNum"));
			
			
			Programme programme = new Programme();
			programme.setCategory(categoryCode);
			
			// Get programmes that belong to the same category as categoryCode
			programmeCollection = programmeDao.findById(programme);
			
			Map<String, ArrayList<String>> programmeCodeToTownListMap = 
					new LinkedHashMap<String, ArrayList<String>>();
			
			Map<String, String> levelOrMajorCodeToLevelOrMajorNameMap = new LinkedHashMap<String, String>();
			
			Map<String, Collection<String>> progCodeToProgrammeMap = new LinkedHashMap<String, Collection<String>>();
			int indexOfMajorOrLevelCode = categoryCode == 3 ? 12 : 14; // Value 3 here (for "Corporate Training category) must not be hard-coded, 
			// but there should be a mechanism to identify if a category returned from DB is of type "Corporate Training"
			
			int indexOfMajorOrLevelName = categoryCode == 3 ? 23 : 24; // Value 3 here (for "Corporate Training category) must not be hard-coded, 
			// but there should be a mechanism to identify if a category returned from DB is of type "Corporate Training"

			for (Collection<String> prog : programmeCollection) {
				int count  = 0;
				ArrayList<String> tempTownList = null;
				String code = null;
				String majorOrLevelCode = null;
				for (String field : prog) {
					if (count == 0) {
						code = field;
						ArrayList<String> townList = programmeCodeToTownListMap.get(field);
						if (townList == null) {
							townList = new ArrayList<String>();
							programmeCodeToTownListMap.put(field, townList);
						}
						tempTownList = townList;
						
						Collection<String> programmeRecord = progCodeToProgrammeMap.get(field);
						if (programmeRecord == null) {
							programmeRecord = prog;
							progCodeToProgrammeMap.put(field, prog);
						}
					}

					if (count == 21) {
						tempTownList = programmeCodeToTownListMap.get(code);
						tempTownList.add(field);						
					}

					if (count == indexOfMajorOrLevelCode) {
						majorOrLevelCode = field;					
					}

					if (count == indexOfMajorOrLevelName) {
						String majorName = levelOrMajorCodeToLevelOrMajorNameMap.get(majorOrLevelCode);
						if (majorName == null) {
							majorName = field;
							levelOrMajorCodeToLevelOrMajorNameMap.put(majorOrLevelCode, majorName);
						}			
					}	
					
					count++;
				}
			}
			
			programmeCollection = progCodeToProgrammeMap.values();
			int totalNumOfResults = programmeCollection.size();
			int numOfPages = (totalNumOfResults % numOfProgrammesPerPage > 0) ? 
					(totalNumOfResults / numOfProgrammesPerPage) + 1 : totalNumOfResults / numOfProgrammesPerPage;
			List<Collection<String>> programmeListForPage = new ArrayList<Collection<String>>();

			int lastProgItemNeededForPage = numOfProgrammesPerPage * pageNum;
			int firstProgItemNeededForPage = lastProgItemNeededForPage - (numOfProgrammesPerPage - 1);
			
			int count = 0;
			for (Collection<String> progColl : programmeCollection) {
				if (count >= firstProgItemNeededForPage && count <= lastProgItemNeededForPage) {
					programmeListForPage.add(progColl);
				}
			}

//			// Get course providers that offer programmes that belong to the same category as categoryCode
//			programme.setLevel(0); // level property is used here to act as a flag
//			courseProviderCollection = courseProviderDao.findById(programme);
//			
//			// Get course providers that offer programmes that belong to the same category as categoryCode 
//			// and when those programmes are the ones with the highest number of views
//			programme.setLevel(1); // level property is used here to act as a flag
//			courseProvidersWithPopularCourses = courseProviderDao.findById(programme);			
			
			iview.setCollection(programmeListForPage);
			helper.setAttribute("contextDeployLogoPath", contextDeployLogoPath);
			helper.setAttribute("contextDeployCourseLogoPath", contextDeployCourseLogoPath);
			helper.setAttribute("courseProviders", courseProviderCollection);
			helper.setAttribute("courseProvidersWithPopularCourses", courseProvidersWithPopularCourses);
			helper.setAttribute("pageNum", pageNum);
			helper.setAttribute("totalNumOfResults", totalNumOfResults);
			helper.setAttribute("numOfPages", numOfPages);
			helper.setAttribute("pageNum", pageNum);
			helper.setAttribute("programmeColl", programmeListForPage);
			helper.setAttribute("levelOrMajorNameCollection", levelOrMajorCodeToLevelOrMajorNameMap.values());
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
