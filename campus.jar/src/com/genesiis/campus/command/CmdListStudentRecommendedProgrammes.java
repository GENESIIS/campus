package com.genesiis.campus.command;

//20161227 MM c25-student-create-dashboard-MP - Created class and implemented execute(IDataHelper, IView) method
//20161227 MM c25-student-create-dashboard-MP - Organised imports; declared Logger reference
//20161228 MM c25-student-create-dashboard-MP - Added code to retrieve institutes from the same result set that
//				was returned as the recommended programmes
//20161229 MM c25-student-create-dashboard-MP - Modified code in execute(IDataHelper) so the number of programmes
//				to fetch is specified from the command class and sent to DAO method; also modified code so it uses 
//				the new RecommendedProgrammesSearchDTO instead of a Student object to send parameters to the DAO method 

import com.genesiis.campus.entity.IView;
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

public class CmdListStudentRecommendedProgrammes implements ICommand {
	
	static Logger Log = Logger.getLogger(CmdListStudentRecommendedProgrammes.class.getName());

	/**
	 * Returns the list of recommended programmes for the student indicated by the request parameter 
	 * named "student" that is sent by the client 
	 * 
	 * @param helper An object that is an IDataHelper that wraps an HTTPServletRequest object that contains the parameters sent by the client. 
	 * @param view An object that is an IView that wraps a Collection<Collection<String>> that the collection of programmes selected for the student is assigned to.
	 * @returns an IView that contains the list of programmes fetched from the DB in the Collection typed field within it. 
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
			if (studentCodeStr != null && !studentCodeStr.isEmpty()) {
				if (Validator.isNumber(studentCodeStr)) {
					
					int studentCode = Integer.parseInt(studentCodeStr);
					int numberOfRecommendedProgrammesToShow = 10; // This can be fetched from the SystemConfig DB table in the future
					
					RecommendedProgrammesSearchDTO recommendedProgrammesSearchDto = new RecommendedProgrammesSearchDTO();
					recommendedProgrammesSearchDto.setStudent(studentCode);
					recommendedProgrammesSearchDto.setNumberOfProgrammes(numberOfRecommendedProgrammesToShow);

					StudentDashboardDAO studentDashboardDao = new StudentDashboardDAO();
					Collection<Collection<String>> dataCollection = new ArrayList<Collection<String>>();
					
					int indexOfCourseProviderCode = 12;
					int indexOfCourseProviderShortName = 17;
					int indexOfCourseProviderName = 18;
					Map<String, List<String>> courseProviderCodeToCourseProviderNamesMap = new LinkedHashMap<String, List<String>>();
					
					dataCollection = studentDashboardDao.findById(recommendedProgrammesSearchDto);		

					for (Collection<String> prog : dataCollection) {
						int index  = 0;
						ArrayList<String> tempSingleTownDetailsList = null;
						String courseProviderCode = null;
						String courseProviderShortName = null;
						String courseProviderName = null;
						String townCode = null;
						String majorOrLevelCode = null;
						
						for (String field : prog) {		
													
							if (index == indexOfCourseProviderCode) {
								courseProviderCode = field;	
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
						
						List<String> providerRecord = courseProviderCodeToCourseProviderNamesMap.get(courseProviderCode);
						if (providerRecord == null) {
							providerRecord = new LinkedList<String>();							
							providerRecord.add(courseProviderCode);
							providerRecord.add(courseProviderShortName);
							providerRecord.add(courseProviderName);							
							courseProviderCodeToCourseProviderNamesMap.put(courseProviderCode, providerRecord);
						}									
					}					
											
					view.setCollection(dataCollection);
					helper.setAttribute("recommendedInstituteList", courseProviderCodeToCourseProviderNamesMap.values());
					
					String courseProviderLogoPath = SystemConfig.PROVIDER_LOGO_PATH.getValue1();
					helper.setAttribute("courseProviderLogoPath", courseProviderLogoPath);
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
