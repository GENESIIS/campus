package com.genesiis.campus.command;

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;

//20170323 CW c157-add-tutor-employment-details-cw INIT CmdListCourseProvidersForTutors.java
//20170326 CW c157-add-tutor-employment-details-cw create execute method

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class lists all the Course Providers details required by the Tutors to add employment details, 
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
			final FeaturedCourseProviderDAO featuredCourseProviders = new FeaturedCourseProviderDAO();
			final Tutor tutor = new Tutor();
			Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
			
			allFeaturedCourseProviderList = featuredCourseProviders.getAll();
			
			view.setCollection(allFeaturedCourseProviderList);			
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}
}
