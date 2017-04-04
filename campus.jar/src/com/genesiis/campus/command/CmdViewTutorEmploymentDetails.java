package com.genesiis.campus.command;

//20170327 CW c157-add-tutor-employment-details-cw INIT CmdViewTutorEmploymentDetails.java & Create execute method
//20170331 CW c157-add-tutor-employment-details-cw modified execute method to get the list of details from getTutorSelectedFCP
//20170402 CW c157-add-tutor-employment-details-cw modified execute method & add validations to allFeaturedCourseProviderList
//20170403 CW c157-add-tutor-employment-details-cw modified singleTutorEmploymentViewCollection filling with tutor code
//20170403 CW c157-add-tutor-employment-details-cw re-order Import Statements
//20170404 CW c157-add-tutor-employment-details-cw create tutorCode variable 

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * this class used to view the tutor employment details. 
 * further it implements ICommand interface
 * @author CW
 */
public class CmdViewTutorEmploymentDetails implements ICommand {

	static Logger log = Logger.getLogger(CmdViewTutorProfile.class.getName());
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
			Collection<String> singleTutorEmploymentViewCollection = new ArrayList<String>();
			String tutorCode = helper.getParameter("tutorCode");
			if(tutorCode != null){
				
				Collection<Collection<String>> allFeaturedCourseProviderList = new ArrayList<Collection<String>>();
				allFeaturedCourseProviderList = FeaturedCourseProviderDAO.getTutorSelectedFCP(tutorCode);
				
				if(allFeaturedCourseProviderList == null || allFeaturedCourseProviderList.isEmpty()){
					singleTutorEmploymentViewCollection.add(tutorCode);
					allFeaturedCourseProviderList.add(singleTutorEmploymentViewCollection);
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
}
