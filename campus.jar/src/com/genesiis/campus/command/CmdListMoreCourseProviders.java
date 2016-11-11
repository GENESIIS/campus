package com.genesiis.campus.command;

//DJ 20161026 c6-list-available-institutes-on-the-view created CmdListInstitutes.java
//DJ 20161026 c6-list-available-institutes-on-the-view implementing execute() method
//DJ 20161030 c6-list-available-institutes-on-the-view identified get all institutes
//DJ 20161110 c6-list-available-institutes-on-the-view identified get all and category wise course providers

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.CategoryDAO;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdListMoreCourseProviders implements ICommand {
	static Logger log = Logger.getLogger(CmdListMoreCourseProviders.class
			.getName());

	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		String contextDeployLogoPath = "education/provider/logo/";
		try {
			int categoryCode = 0;
			final CourseProvider provider = new CourseProvider();
			String categoryCodeString = helper.getParameter("category");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
					provider.setCategory(categoryCode);
				}				
			}
			if (categoryCode > 0) {
				final Collection<Collection<String>> categoryWiseCourseProviders = providerDAO.findById(provider);
				iview.setCollection(categoryWiseCourseProviders);
			} else {
				final Collection<Collection<String>> allCourseProviders = providerDAO.getAll();
				iview.setCollection(allCourseProviders);
			}
			//List Category data for the drop down
			final CategoryDAO categoryDAO=new CategoryDAO();
			final Collection<Collection<String>> categoryList=new CategoryDAO().getAll();
			//List Course provider types for the drop down
			//List location for the drop down
			//List Majors for the drop down
			//List Levels for the drop down 
			helper.setAttribute("contextDeployLogoPath", contextDeployLogoPath);
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return iview;
	}

}
