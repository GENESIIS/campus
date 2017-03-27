package com.genesiis.campus.command;

//20170327 CW c157-add-tutor-employment-details-cw INIT CmdViewTutorEmploymentDetails.java & Create execute method

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
			Collection<Collection<String>> tutorEmploymentViewCollection = new ArrayList<Collection<String>>();
			Collection<String> singleTutorEmploymentViewCollection = new ArrayList<String>();
			
			if(helper.getParameter("tutorCode") != null){
				singleTutorEmploymentViewCollection.add(helper.getParameter("tutorCode"));
				tutorEmploymentViewCollection.add(singleTutorEmploymentViewCollection);
			}
			
			view.setCollection(tutorEmploymentViewCollection);			
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}

}
