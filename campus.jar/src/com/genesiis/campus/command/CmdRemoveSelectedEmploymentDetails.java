package com.genesiis.campus.command;

//20170329 CW c157-add-tutor-employment-details-cw INIT CmdRemoveSelectedEmploymentDetails.java

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;

/**
 * this class will remove all the tutor selected course provider details which were added by the tutor earlier, 
 * further it implements ICommand interface
 * @author CW
 *
 */
public class CmdRemoveSelectedEmploymentDetails implements ICommand  {

	static Logger log = Logger.getLogger(CmdRemoveSelectedEmploymentDetails.class.getName());
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
			String employerCode = helper.getParameter("employerCode");
			
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
