package com.genesiis.campus.command;

//20170420 CW c159-courseprovider-accept-tutor-request-cw INIT CmdListTutorsForCourseProviders.java

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
