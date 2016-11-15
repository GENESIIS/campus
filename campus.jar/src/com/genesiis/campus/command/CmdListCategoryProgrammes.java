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
//20161112 MM c5-corporate-training-landing-page-MP Modified code to expect a parameter named 
//				categoryIdentifierString from the client and to determine whether majors or 
//				levels to send (as filtering type) based on that parameter
//20161114 MM c5-corporate-training-landing-page-MP Modified code to use EducationCateogry 
// 				enum to decide whether to show Levels or Majors as filters, and to use 
// 				SystemConfig enum and SystemConfigDAO to fetch courseProviderLogoPath from 
// 				SystemConfig table
//20161115 MM c5-corporate-training-landing-page-MP Added further validation code in execute() 
//				method and set a message list to be sent to be shown to user

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
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.EducationCategory;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListCategoryProgrammes implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListCategoryProgrammes.class.getName());

	final ICrud programmeDao = new CategoryProgrammeDAO();	
	final ICrud systemConfigDao = new SystemConfigDAO();	
		
	public CmdListCategoryProgrammes() {
		
	}

	/**
	 * @author Miyuru
	 * 
	 * Returns the Programme collection, and attaches a Level/Major collection and other 
	 * related details based on that Programme collection to the  IDataHelper parameter 
	 * upon receiving a client request with a value indicating a categoryCode that the 
	 * Programmes must fall into.
	 * 
	 * @param An IDataHelper typed object wrapping a HttpServletRequest containing client 
	 * parameters.
	 * 
	 * @param An IView typed object wrapping a Collection intended to be assigned the 
	 * Programme data pay-load
	 * 
	 * @return An IView containing the collection of Programmes that match the categoryCode that is
	 *  sent by the client as a request parameter. The method, in addition to this, also sets several 
	 *  attributes to the HttpServletRequest that is wrapped inside the IDataHelper typed object that 
	 *  is passed here, such as a map that has Town details of Programmes mapped against the 
	 *  the related code of the related Programme, the "filter" type to be used to further separate 
	 *  this Programme collection (Such as Majors or Levels) etc. If the provided value for categoryCode
	 *  represents the category record that matches the name of EducationCategory.CORPORATE_TRAINING 
	 *  constant, it is the Major that is used as the filter type and the list of unique Majors of the 
	 *  Programme collection will be set as an attribute, else, it is the Level that is considered.
	 * 
	 */
	@Override 
	public IView execute(IDataHelper helper, IView iview) throws Exception {
		
		String message = "";
		Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> systemConfigRecord = new ArrayList<Collection<String>>();
		List<String> msgList = new ArrayList<String>();
		int categoryCode = -1;
		String categoryIdentifierString = "";
		int pageNum = -1;
		int numOfResultsPerPage = 20; // This value will need to be eventually fetched from DB
		String courseProviderLogoPath = "";
		
		try {
			if (helper.getParameter("category") == null) {
				Log.error("The provided value for category is null!");
				msgList.add("The provided value for category is null!");
				throw new IllegalArgumentException("The provided value for category is null!");
			} 			

			if (helper.getParameter("category").isEmpty()) {
				Log.error("The provided value for category is empty!");
				msgList.add("The provided value for category is empty!");
				throw new IllegalArgumentException("The provided value for category is empty!");
			} 	
			
			try {
				categoryCode = Integer.parseInt(helper.getParameter("category"));
			} catch (NumberFormatException nfe){
				Log.error("The provided value for categoryCode cannot be parsed into a number!");
				msgList.add("The provided value for categoryCode cannot be parsed into a number!");
				throw new IllegalArgumentException("The provided value for categoryCode is invalid!");
			}
			
			categoryIdentifierString = helper.getParameter("categoryIdentifierString");	
			
			if (categoryIdentifierString == null) {
				Log.error("The provided value for categoryIdentifierString is null!");
				msgList.add("The provided value for categoryIdentifierString is null!");
				throw new IllegalArgumentException("The provided value for categoryIdentifierString is null!");
			} 	
			
			EducationCategory category = null;		
			try {
				category = EducationCategory.valueOf(categoryIdentifierString);
			} catch (IllegalArgumentException iae) {
				// If the categoryIdentifierString does not represent the name of an existing EducationCategory constant 
				// there will be an IllegalArgumentException.	
				Log.error("The provided value for categoryIdentifierString is invalid!");
				msgList.add("The provided value for categoryIdentifierString is invalid!");
				throw new IllegalArgumentException("The provided value for categoryIdentifierString is invalid!");
			}
			
			Programme programme = new Programme();
			programme.setCategory(categoryCode);
			
			// Get programmes that belong to the same category as categoryCode
			programmeCollection = programmeDao.findById(programme);	
			
			Map<String, ArrayList<List<String>>> programmeCodeToTownListMap = 
					new LinkedHashMap<String, ArrayList<List<String>>>();
			
			Map<String, List<String>> levelOrMajorCodeToLevelOrMajorDetailsMap = new LinkedHashMap<String, List<String>>();
			List<String> tempLevelOrMajorDetailsList = null;
			
			Map<String, Collection<String>> progCodeToProgrammeMap = new LinkedHashMap<String, Collection<String>>();
			
			int indexOfMajorOrLevelCode = -1; 
			int indexOfMajorOrLevelName = -1;
			String filterType = "";
			
			// This exemplifies the use and purpose of EducationCategory. 
			// Certain things must happen only if the category is CORPORATE_TRAINING
			if (EducationCategory.CORPORATE_TRAINING.equals(category)) {
				// Consider Major data
				indexOfMajorOrLevelCode = 12;
				indexOfMajorOrLevelName = 23;
				filterType = "Major";
			} else {
				// Consider Level data
				indexOfMajorOrLevelCode = 14;
				indexOfMajorOrLevelName = 24;
				filterType = "Level";
			}

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
			
			// Get course provider logo path from SystemConfig table
			systemConfigRecord = systemConfigDao.findById(SystemConfig.PROVIDER_LOGO_PATH.name());
			
			outer:
			for (Collection<String> record : systemConfigRecord) {
				int count = 0;
				inner:
				for (String field : record) {
					if (count == 2) {
						courseProviderLogoPath = field;
						break outer;
					}
					count++;
				}
			}
			iview.setCollection(programmeCollection);
			helper.setAttribute("courseProviderLogoPath", courseProviderLogoPath);
			helper.setAttribute("numOfResultsPerPage", numOfResultsPerPage);
			helper.setAttribute("levelOrMajorCollection", levelOrMajorCodeToLevelOrMajorDetailsMap.values());
			helper.setAttribute("programmeCodeToTownListMap", programmeCodeToTownListMap);
			helper.setAttribute("filterType", filterType);
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
			msgList.add("Unknown error occured while preparing records to display");
			throw e;
		} finally {
			helper.setAttribute("messages", msgList);
		}
		
		return  iview;
	}
	
}
