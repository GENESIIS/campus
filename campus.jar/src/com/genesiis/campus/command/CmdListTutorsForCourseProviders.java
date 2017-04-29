package com.genesiis.campus.command;

//20170420 CW c159-courseprovider-accept-tutor-request-cw INIT CmdListTutorsForCourseProviders.java
//20170420 CW c159-courseprovider-accept-tutor-request-cw modified execute method to use courseProviderCode variable
//20170421 CW c159-courseprovider-accept-tutor-request-cw add tutorsForCP variable into execute method
//20170424 CW c159-courseprovider-accept-tutor-request-cw modified execute method to set values of courseProviderCode from cpCode
//20170425 CW c159-courseprovider-accept-tutor-request-cw add applicationStatusMap
//20170425 CW c159-courseprovider-accept-tutor-request-cw add applicationStatusMap errors fixing
//20170426 CW c159-courseprovider-accept-tutor-request-cw unit testing & applicationStatusMap data filling error fixing
//20170427 CW c159-courseprovider-accept-tutor-request-cw modified the values sending into applicationStatusMap
//20170429 CW c159-courseprovider-accept-tutor-request-cw set parameters into getTutorsListOfCourseprovider method

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Validator;
import com.sun.javafx.collections.MappingChange.Map;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This class returns the list of tutors for given Course Provider 
 * further it implements ICommand interface
 * @author CW
 *
 */
public class CmdListTutorsForCourseProviders implements ICommand  {

	static Logger log = Logger.getLogger(CmdListTutorsForCourseProviders.class.getName());
	private String message = "";

	/**
	 * @author Chinthaka
	 * @param helepr IDataHelper object of Object type 
	 * @param view IView object of object type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		try {
			String courseProviderCode = helper.getParameter("cpCode");
			if(Validator.isNotEmpty(courseProviderCode)){
				
				final TutorDAO tutorsForCP = new TutorDAO();							
				
				Collection<Collection<String>> allEmploymentTutorsList = tutorsForCP.getTutorsListOfCourseprovider(courseProviderCode, 
						String.valueOf(ApplicationStatus.ACTIVE.getStatusValue()), String.valueOf(ApplicationStatus.PENDING.getStatusValue()));				

				HashMap<Integer, String> applicationStatusMap = new HashMap<Integer, String>();
				
				for (ApplicationStatus statusValues : ApplicationStatus.values()) {
					int statusValueInt = ApplicationStatus.getApplicationStatus(statusValues.toString());
					applicationStatusMap.put(statusValueInt, ApplicationStatus.getApplicationStatus(statusValueInt));
				}

				helper.setAttribute("applicationStatusMap", applicationStatusMap);
				
				view.setCollection(allEmploymentTutorsList);		
			}else{
				message = "Course Provider Code is empty. Cannot view tutor's list ...";
			}
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}
}
