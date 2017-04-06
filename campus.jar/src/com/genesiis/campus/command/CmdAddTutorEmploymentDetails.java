package com.genesiis.campus.command;

//20170327 CW c157-add-tutor-employment-details-cw INIT CmdAddTutorEmploymentDetails.java
//20170327 CW c157-add-tutor-employment-details-cw modified execute method to add employment data to the database
				// created setEmploymentDetails method
//20170327 CW c157-add-tutor-employment-details-cw modified execute method & fix data saving error
//20170328 CW c157-add-tutor-employment-details-cw modified execute method to call getTutorSelectedFCP & get the tutor selected course provider list
//20170331 CW c157-add-tutor-employment-details-cw modified execute method & change tutorCode to tutorcodelist
//20170331 CW c157-add-tutor-employment-details-cw modified validations in execute method & add messages
//20170403 CW c157-add-tutor-employment-details-cw removed un used import statements & re-order Import Statements
//20170404 CW c157-add-tutor-employment-details-cw add doc comments
//20170404 CW c157-add-tutor-employment-details-cw add validations to allFeaturedCourseProviderList in execute method
//20170406 CW c157-add-tutor-employment-details-cw add email sending method calls into the execute method

import com.genesiis.campus.entity.EmploymentDAO;
import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Employment;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.mail.GenerateEmail;
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

	/**
	 * @author Chinthaka
	 * @param helepr IDataHelper object of Object type 
	 * @param view IView object of object type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		String message = "";
		String emailMessage = "";
		int status = 0;
		try {			
			Employment employmentDetails = new Employment();
			String tutorCode = helper.getParameter("tutorcodelist");
			String employerCode = helper.getParameter("employerDetails");			
			
			if(Validator.isNotEmpty(employerCode) && !employerCode.equals("-1")){// employer code is selected
				if(Validator.isNotEmpty(tutorCode)){
					employmentDetails = setEmploymentDetails(tutorCode, employerCode);
								
					EmploymentDAO addEmployment = new EmploymentDAO();
					status = addEmployment.add(employmentDetails);					
				}
			}else{// employer code is not selected
				message = "Please select an Employer ... ";
			}			
			
			if(status > 0){
				message = "Selected employers successfully added ...";
				GenerateEmail emailAtUpdate = new GenerateEmail();
				emailMessage = emailAtUpdate.sendAdminTutorUpdateEmail(helper.getParameter("firstname"), helper.getParameter("lastname"), 
						helper.getParameter("email"), helper.getParameter("username"), appStatus); //send email
			}
			
			if(Validator.isNotEmpty(tutorCode)){
				Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
				allFeaturedCourseProviderList = FeaturedCourseProviderDAO.getTutorSelectedFCP(tutorCode);
				
				if(allFeaturedCourseProviderList == null || allFeaturedCourseProviderList.isEmpty()){ 
					// if any employers not selected earlier then add tutor code into the view collection
					Collection<String> singleFeaturedCourseProviderList = new ArrayList<String>();
					singleFeaturedCourseProviderList.add(tutorCode);
					allFeaturedCourseProviderList.add(singleFeaturedCourseProviderList);
				}
				
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
			employment.setVarificationstatus(ApplicationStatus.PENDING.getStatusValue()); // initially employer verification status is pending
			employment.setCrtby("chinthaka");
			employment.setModby("-");
			employment.setTutor(Integer.parseInt(tutorCode));
			employment.setCourseprovider(Integer.parseInt(employerCode));
		}
			
		return employment;
	}
	
}
