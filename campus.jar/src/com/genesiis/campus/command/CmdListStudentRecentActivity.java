package com.genesiis.campus.command;

//20161229 MM c25-student-create-dashboard-MP - INIT - Initialised file and implemented execute() method
//20170101 MM c25-student-create-dashboard-MP - Added code in execute(IDataHelper) to process the fetched list of student activities

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentActivitiyDAO;
import com.genesiis.campus.entity.StudentDashboardDAO;
import com.genesiis.campus.entity.model.RecommendedProgrammesSearchDTO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CmdListStudentRecentActivity implements ICommand {

	static Logger Log = Logger.getLogger(CmdListStudentRecentActivity.class.getName());

	/**
	 * Returns a list of recent activities for the student indicated by the request parameter 
	 * named "student" that is sent by the client 
	 * 
	 * @param helper An object that is an IDataHelper that wraps an HTTPServletRequest object that contains the parameters sent by the client. 
	 * @param view An object that is an IView that wraps a Collection<Collection<String>> that the collection of data on activities carried out by the student.
	 * @returns an IView that contains the list of activities fetched from the DB in the Collection typed field within it. 
	 * 
	 * @author Miyuru
	 *
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		List<String> msgList = new ArrayList<String>();
		
		try {
			String studentCodeStr = "1"; // Get this from the session
//			HttpSession session = helper.getSession(false);
//			studentCodeStr = (String) session.getAttribute("userCode");
			
			if (studentCodeStr != null && !studentCodeStr.isEmpty()) {
				if (Validator.isNumber(studentCodeStr)) {
					
					int studentCode = Integer.parseInt(studentCodeStr);
					
					Student student = new Student();
					student.setCode(studentCode);

					StudentActivitiyDAO studentActivityDao = new StudentActivitiyDAO();
					Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();
					List<String> courseProviderCodeToCourseProviderNamesMap;
								
					dataCollection = studentActivityDao.findById(student);											

					for (Collection<String> prog : dataCollection) {
						int index  = 0;				
						int indexOfCourseProviderCode;
						int indexOfCourseProviderShortName;
						int indexOfCourseProviderName;
						ArrayList<String> tempSingleTownDetailsList = null;
						int courseProviderCode = 0;
						String courseProviderShortName = null;
						String courseProviderName = null;
						int tableIndicatorIndex = 0;
						String tableIndicator = "";
						String majorOrLevelCode = null;
						
						for (String field : prog) {		
							
							if (index == tableIndicatorIndex) {
								tableIndicator = field;	
								index++;	
								continue;
							}							
									
							if (index == indexOfCourseProviderCode) {
								courseProviderCode = Integer.parseInt(field);	
								index++;	
								continue;
							}
							
							if (index == indexOfCourseProviderShortName) {
								courseProviderShortName = field;	
								index++;	
								continue;
							}

							if (index == indexOfCourseProviderName) {
								courseProviderName = field;	
								index++;	
								continue;
							}
							
							index++;	
						}	
						
						LinkedList<String> providerRecord = null; //courseProviderCodeToCourseProviderNamesMap.get(courseProviderCode);
						if (providerRecord == null) {
							providerRecord = new LinkedList<String>();							
//							providerRecord.add(courseProviderCode);
							providerRecord.add(courseProviderShortName);
							providerRecord.add(courseProviderName);							
//							courseProviderCodeToCourseProviderNamesMap.put(String.valueOf(courseProviderCode), providerRecord);
						}									
					}						
					
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
