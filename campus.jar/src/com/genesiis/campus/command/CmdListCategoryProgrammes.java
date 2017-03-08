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
//20161116 MM c5-corporate-training-landing-page-MP Made modifications to change exception 
//				throwing when invalid parameters are encountered
//20161116 MM c5-corporate-training-landing-page-MP Overhauled execute() method implementation 
// 				to change validation and exception handling mechanism
//20161116 MM c5-corporate-training-landing-page-MP Changed the way course provider logo path 
// 				is fetched; was via querying SystemConfig table; now via SystemConfig enum
//20161124 MM c5-corporate-training-landing-page-MP Added detailed comment about the use of 
// 				EducationCategory.CORPORATE_TRAINING enum constant as requested in Code Review
//20161126 MM c5-corporate-training-landing-page-MP Modified code that accesses the programme-
//				data collection returned from the DAO class to suit the omission of 
//				logoImagePath column there from the extracted column values. Also made other 
//				code that accesses specific indexes of the result-set more maintainable. Added
//				code to send default-course-provider details to front-end so the default-
//				provider image may be shown when accountType of a provider is one-off.

import com.genesiis.campus.entity.CategoryProgrammeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.EducationCategory;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

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
		
		List<String> msgList = new ArrayList<String>();
		
		try {
			String categoryStr = helper.getParameter("category");
			if (categoryStr != null && !categoryStr.isEmpty()) {
				if (Validator.isNumber(categoryStr)) {
					
					int categoryCode = Integer.parseInt(categoryStr);
					
					String categoryIdentifierString = helper.getParameter("categoryIdentifierString");		
					if (categoryIdentifierString != null && !categoryIdentifierString.isEmpty()) {
					
						boolean isValidCategoryString = false;
						EducationCategory educationCategory = null;
						for (EducationCategory categoryConstant : EducationCategory.values()) {
							if (categoryConstant.name().equals(categoryIdentifierString)) {
								isValidCategoryString = true;
								educationCategory = EducationCategory.valueOf(categoryIdentifierString);
								break;
							}
						}
						
						if (isValidCategoryString) {							

							Programme programme = new Programme();
							programme.setCategory(categoryCode);
							
							// Get programmes that belong to the same category as categoryCode
							Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();
							programmeCollection = programmeDao.findById(programme);	
							
							Map<String, ArrayList<List<String>>> programmeCodeToTownListMap = 
									new LinkedHashMap<String, ArrayList<List<String>>>();
							
							Map<String, List<String>> levelOrMajorCodeToLevelOrMajorDetailsMap = new LinkedHashMap<String, List<String>>();
							List<String> tempLevelOrMajorDetailsList = null;
							
							Map<String, Collection<String>> progCodeToProgrammeMap = new LinkedHashMap<String, Collection<String>>();
							
							int indexOfMajorOrLevelCode = -1; 
							int indexOfMajorOrLevelName = -1;
							int indexOfProgrammeCode = 0;
							int indexOfTownCode = 19;
							int indexOfTownName = 20;
							int indexOfAccountType = 24;
							String filterType = "";							
						
							// EducationCategory enum contains constants that map to records in the Category table. 
							// E.g. CORPORATE_TRAINING constant of type EducationCategory has a related record in 
							// Category table whose value for CATEGORYSTRING column will be CORPORATE_TRAINING.
							
							// Code below exemplifies the use and purpose of EducationCategory. Certain things must  
							// happen only if the category that concerns this user request is CORPORATE_TRAINING.
							// We see if the EducationCategory enum constant generated by using the categoryIdentifierString 
							// that the client sent equals the CORPORATE_TRAINING constant of type EducationCategory.
							if (EducationCategory.CORPORATE_TRAINING.equals(educationCategory)) {
								// If is CORPORATE_TRAINING consider Major data
								indexOfMajorOrLevelCode = 12; // in the result set, MAJOR (code) will be at index 12 
								indexOfMajorOrLevelName = 22; // in the result set, MAJORNAME will be at index 23
								filterType = "Major";
							} else {
								// else, consider Level data
								// Consider Level data
								indexOfMajorOrLevelCode = 14; // in the result set, LEVEL (code) will be at index 14 
								indexOfMajorOrLevelName = 23; // in the result set, LEVELNAME will be at index 24
								filterType = "Level";
							}

							for (Collection<String> prog : programmeCollection) {
								int count  = 0;
								ArrayList<String> tempSingleTownDetailsList = null;
								String programmeCode = null;
								String townCode = null;
								String majorOrLevelCode = null;
								for (String field : prog) {
									if (count == indexOfProgrammeCode) {
										programmeCode = field;						
										
										Collection<String> programmeRecord = progCodeToProgrammeMap.get(field);
										if (programmeRecord == null) {
											programmeRecord = prog;
											progCodeToProgrammeMap.put(field, prog);
										}
									}

									if (count == indexOfTownCode) {
										townCode = field;						
									}

									if (count == indexOfTownName) {
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
							} // end of for (Collection<String> prog : programmeCollection)
							
							programmeCollection = progCodeToProgrammeMap.values();			
							
							// Get course provider logo path from SystemConfig enum
							String courseProviderLogoPath = SystemConfig.PROVIDER_LOGO_PATH.getValue1();

							int numOfResultsPerPage = 20; // This value will need to be eventually fetched from DB
							int defaultCourseProviderRecordCode = -1; // Hard-coded
							Map<String, String> oneOffProviderDetails = new HashMap<String, String>();
							oneOffProviderDetails.put("name", AccountType.ONE_OFF_COURSE_PROVIDER.name());
							oneOffProviderDetails.put("code", String.valueOf(defaultCourseProviderRecordCode));
							
							iview.setCollection(programmeCollection);
							helper.setAttribute("courseProviderLogoPath", courseProviderLogoPath);
							helper.setAttribute("numOfResultsPerPage", numOfResultsPerPage);
							helper.setAttribute("levelOrMajorCollection", levelOrMajorCodeToLevelOrMajorDetailsMap.values());
							helper.setAttribute("programmeCodeToTownListMap", programmeCodeToTownListMap);
							helper.setAttribute("filterType", filterType);
							helper.setAttribute("oneOffProviderDetails", oneOffProviderDetails);
							
						} else {
							msgList.add("The provided value for categoryIdentifierString is not valid!");
						}// end of if (isValidCategoryString)
					} else {
						msgList.add("Either no or empty value has been provided for categoryIdentifierString parameter!");						
					}// end of if (categoryIdentifierString != null && !categoryIdentifierString.isEmpty())					
				} else {
					msgList.add("The value provided for category is invalid!");
				}// end of if (Validator.isNumber(categoryStr))	
			} else {
				msgList.add("No or empty value has been provided for category parameter!");
			}// end of if (categoryStr != null && !categoryStr.isEmpty())		
			
		} catch (NumberFormatException nfe) {
			Log.info("execute(IDataHelper, IView) : NumberFormatException " + nfe.toString());
			throw nfe;
		} catch (IllegalArgumentException iae) {
			Log.info("execute(IDataHelper, IView) : IllegalArgumentException " + iae.toString());
			throw iae;
		}  catch (Exception e) {
			Log.info("execute(IDataHelper, IView) : Exception " + e.toString());
			throw e;
		} finally {
			helper.setAttribute("messages", msgList);
		}
		
		return  iview;
	}
	
}
