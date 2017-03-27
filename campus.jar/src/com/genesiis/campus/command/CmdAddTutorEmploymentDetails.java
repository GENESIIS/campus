package com.genesiis.campus.command;

//20170327 CW c157-add-tutor-employment-details-cw INIT CmdAddTutorEmploymentDetails.java
//20170327 CW c157-add-tutor-employment-details-cw modified execute method to add employment data to the database
				// created setEmploymentDetails method
//20170327 CW c157-add-tutor-employment-details-cw modified execute method & fix data saving error

import com.genesiis.campus.entity.EmploymentDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Employment;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class used to add the tutor employment details to the database & send an email to the Course provide & Admin to approve the request. 
 * further it implements ICommand interface
 * @author CW
 */
public class CmdAddTutorEmploymentDetails implements ICommand {

	static Logger log = Logger.getLogger(CmdAddTutorEmploymentDetails.class.getName());
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
			Employment employmentDetails = new Employment();
			String tutorCode = helper.getParameter("tutorCode");
			String employerCode = helper.getParameter("employerDetails");			
			
			if(Validator.isNotEmpty(tutorCode) && Validator.isNotEmpty(employerCode) && !employerCode.equals("-1")){
				employmentDetails = setEmploymentDetails(tutorCode, employerCode);
							
				EmploymentDAO addEmployment = new EmploymentDAO();
				int status = addEmployment.add(employmentDetails);			
				
				final FeaturedCourseProviderDAO featuredCourseProviders = new FeaturedCourseProviderDAO();
				final Tutor tutor = new Tutor();
				Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
				
				allFeaturedCourseProviderList = featuredCourseProviders.getAll();
				
				view.setCollection(allFeaturedCourseProviderList);			
			}
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}
	
	
	/**
	 * This method is created to populate data into the Employment object
	 * @param tutorCode
	 * @param employerCode
	 * @return Employment object
	 */
	private Employment setEmploymentDetails(String tutorCode, String employerCode){
		
		Employment employment = new Employment();
		
		if(tutorCode != null && employerCode != null){
			employment.setVarificationstatus(ApplicationStatus.PENDING.getStatusValue());
			employment.setCrtby("chinthaka");
			employment.setModby("-");
			employment.setTutor(Integer.parseInt(tutorCode));
			employment.setCourseprovider(Integer.parseInt(employerCode));
		}
			
		return employment;
	}
	
}
