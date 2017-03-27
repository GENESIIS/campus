package com.genesiis.campus.command;

//20170327 CW c157-add-tutor-employment-details-cw INIT CmdAddTutorEmploymentDetails.java

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;

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
			log.info(" tutor code = " + helper.getParameter("code"));
			log.info(" tutor employerDetails = " + helper.getParameter("employerDetails"));
			
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
