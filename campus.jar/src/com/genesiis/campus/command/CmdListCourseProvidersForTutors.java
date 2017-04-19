package com.genesiis.campus.command;

//20170323 CW c157-add-tutor-employment-details-cw INIT CmdListCourseProvidersForTutors.java
//20170326 CW c157-add-tutor-employment-details-cw create execute method
//20170329 CW c157-add-tutor-employment-details-cw edit the place of the comments
//20170329 CW c157-add-tutor-employment-details-cw modified execute method to use getFCPListForTutorToSelect method instead of getAll method
				// add validations to execute method
//20170403 CW c157-add-tutor-employment-details-cw removed un used import statements
//20170404 CW c157-add-tutor-employment-details-cw removed un used import statements
//20170404 CW c157-add-tutor-employment-details-cw add doc comments
//20170418 CW c158-send-email-tutor-employment-confirmation-cw modified getFCPListForTutorToSelect method call to pass Account type
//20170419 CW c158-send-email-tutor-employment-confirmation-cw modified execute method & remove un necessary variable declarations & clean the code

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.AccountType;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class lists all the Course Providers details which is not already added by the tutor required by the Tutors to add employment details, 
 * further it implements ICommand interface
 * @author CW
 *
 */
public class CmdListCourseProvidersForTutors implements ICommand  {

	static Logger log = Logger.getLogger(CmdListCourseProvidersForTutors.class.getName());
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
			
			String tutorCode = helper.getParameter("tutorCode");
			if(Validator.isNotEmpty(tutorCode)){
				
				final FeaturedCourseProviderDAO featuredCourseProviders = new FeaturedCourseProviderDAO();				
				Collection<Collection<String>> allFeaturedCourseProviderList = featuredCourseProviders.getFCPListForTutorToSelect(tutorCode, 
						AccountType.getAccountType("FEATURED_COURSE_PROVIDER"));
				
				view.setCollection(allFeaturedCourseProviderList);		
			}else{
				message = "Tutor Code is empty. Cannot view employers list ...";
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
