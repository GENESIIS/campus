package com.genesiis.campus.command;

//20170328 CW c157-add-tutor-employment-details-cw Init CmdViewTutorSelectedEmploymentDetails Class
//20170328 CW c157-add-tutor-employment-details-cw modified allFeaturedCourseProviderList to allSelectedFeaturedCourseProviderList

import com.genesiis.campus.entity.FeaturedCourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdViewTutorSelectedEmploymentDetails implements ICommand {
	
	static Logger log = Logger.getLogger(CmdAddTutorEmploymentDetails.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		try {			
			String tutorCode = helper.getParameter("tutorCode");
			
			if(Validator.isNotEmpty(tutorCode)){
				Collection<Collection<String>> allSelectedFeaturedCourseProviderList = new ArrayList<Collection<String>>();
				allSelectedFeaturedCourseProviderList = FeaturedCourseProviderDAO.getTutorSelectedFCP(tutorCode);
				
				view.setCollection(allSelectedFeaturedCourseProviderList);			
			}
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		}
		return view;
	}
}
