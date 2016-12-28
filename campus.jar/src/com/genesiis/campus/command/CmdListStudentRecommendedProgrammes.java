package com.genesiis.campus.command;

//20161227 MM c25-student-create-dashboard-MP - Created class and implemented execute(IDataHelper, IView) method
//20161227 MM c25-student-create-dashboard-MP - Organised imports; declared Logger reference

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDashboardDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Returns the list of recommended programmes for the student indicated by the request parameter 
 * named "student" that is sent by the client 
 * 
 * @param IDataHelper Wraps an HTTPServletRequest object that contains the parameters sent by the client. 
 * @param IView Wraps a Collection<Collection<String>> that the collection of programmes selected for the student is assigned to.
 * @returns IView Contains the list of programmes fetched from the DB in Collection<Collection<String>> typed field within it. 
 * 
 * @author Miyuru
 *
 */
public class CmdListStudentRecommendedProgrammes implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListStudentRecommendedProgrammes.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		List<String> msgList = new ArrayList<String>();
		
		try {
			String studentCodeStr = "1"; // Get this from the session
			if (studentCodeStr != null && !studentCodeStr.isEmpty()) {
				if (Validator.isNumber(studentCodeStr)) {
					
					int studentCode = Integer.parseInt(studentCodeStr);
					
					Student student = new Student();
					student.setCode(studentCode);
					
					// Get profile information of Student represented by studentCode
					StudentDashboardDAO studentDashboardDao = new StudentDashboardDAO();
					Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();
					
					int indexOfCourseProviderCode = 12;
					int indexOfCourseProviderShortName = 17;
					int indexOfCourseProviderName = 18;
					Map<String, List<String>> courseProviderCodeToCourseProvierNamesMap = new LinkedHashMap<String, List<String>>();
					
					dataCollection = studentDashboardDao.findById(student);		

					for (Collection<String> prog : dataCollection) {
						int count  = 0;
						ArrayList<String> tempSingleTownDetailsList = null;
						String courseProviderCode = null;
						String townCode = null;
						String majorOrLevelCode = null;
						for (String field : prog) {
							if (count == indexOfCourseProviderCode) {
								courseProviderCode = field;								
								List<String> providerRecord = courseProviderCodeToCourseProvierNamesMap.get(field);
								if (providerRecord == null) {
									providerRecord = prog;
									progCodeToProgrammeMap.put(field, prog);
								}
							}

							if (count == indexOfTownCode) {
								townCode = field;						
							}

							if (count == indexOfTownName) {
								String currentTownName = field;
								if ((townCode != null && !townCode.isEmpty()) && (field != null && !field.isEmpty())) {
									ArrayList<List<String>> townList = programmeCodeToTownListMap.get(courseProviderCode);
									if (townList == null) {
										townList = new ArrayList<List<String>>();
										programmeCodeToTownListMap.put(courseProviderCode, townList);
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
					
					
											
					view.setCollection(dataCollection);
				} else {
					msgList.add("The value provided for student parameter is invalid!");
				}// end of if (Validator.isNumber(studentCodeStr))	
			} else {
				msgList.add("No or empty value has been provided for student parameter!");
			}// end of if (studentCodeStr != null && !studentCodeStr.isEmpty())		
			
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
		
		return  view;
	}

}
