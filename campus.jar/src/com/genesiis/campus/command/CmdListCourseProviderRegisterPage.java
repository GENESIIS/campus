package com.genesiis.campus.command;

//20161209 JH c39-add-course-provider CmdListCourseProviderRegisterPage.java created
//20161220 JH c39-add-course-provider execute method coding: added switch case
//20161229 JH c39-add-course-provider added new switch case LIST_COUESE_PROVIDER_TYPES 
//20161230 JH c39-add-course-provider case LIST_PROVIDER_REGISTRATION_PAGE modified 
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170203 JH c39-add-course-provider mx modification: removed unwanted Logger import
//20170221 JH c141-add-course-provider-issue-improvements added doc comments
//20170402 JH c141-ui-integration-for-add-course-provider case DISPLAY_TOWN_DATA: validate country id before parsing
//20170403 JH c141-ui-integration-for-add-course-provider fixed validation error in case DISPLAY_TOWN_DATA
//20170503 JH c141-ui-integration-for-add-course-provider added doc comments 

import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.CourseProviderTypeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * CmdListCourseProviderRegisterPage used to load data to the course provider
 * registration page. This will load the page on load data as well as the data
 * required on form changes like town list for a selected country
 * @author JH
 *
 */
public class CmdListCourseProviderRegisterPage implements ICommand {
	static Logger log = Logger
			.getLogger(CmdListCourseProviderRegisterPage.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		Collection<Collection<String>> requestData = new ArrayList<Collection<String>>();
		Collection<Collection<String>> countryCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> townCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> providerTypeCollection = new ArrayList<Collection<String>>();
		try {
			Operation op = Operation.getOperation(helper.getCommandCode());
			switch (op) {
			/*  
			 * Here it loads the data required to crate the initial course provider registration page.
			 * Country list, course provider type list, account list will  be collected
			 */
			case LIST_PROVIDER_REGISTRATION_PAGE:
				
				//get country list
				ICrud country2Dao = new Country2DAO();
				countryCollection = country2Dao.getAll();
				helper.setAttribute("countryArrayList", countryCollection);
				
				
				//get course provider types
				ICrud providerTypeDAO = new CourseProviderTypeDAO();
				providerTypeCollection = providerTypeDAO.getAll();
				
				
				/*
				 * course provider account has only Active and Inactive status. 
				 * Those two enum values are collected and put to an array
				 */
				ArrayList<String> activeAccountStatus = new ArrayList<String>();
				ArrayList<String> inactiveAccountStatus = new ArrayList<String>();
				
				ArrayList<ArrayList<String>> accountStatusList = new ArrayList<ArrayList<String>>();
				
				activeAccountStatus.add(String.valueOf(ApplicationStatus.ACTIVE.getStatusValue()));
				activeAccountStatus.add(ApplicationStatus.ACTIVE.name());
				inactiveAccountStatus.add(String.valueOf(ApplicationStatus.INACTIVE.getStatusValue()));
				inactiveAccountStatus.add(ApplicationStatus.INACTIVE.name());
				
				accountStatusList.add(activeAccountStatus);
				accountStatusList.add(inactiveAccountStatus);
				
				helper.setAttribute("accountStatusList", accountStatusList);
				helper.setAttribute("providerTypes", providerTypeCollection);
				break;

			case DISPLAY_TOWN_DATA:
				
				/*
				 * DISPLAY_TOWN_DATA case queries the database to get the towns 
				 * of a particular country
				 */

				ICrud townDao = new TownDAO();
				String country = helper.getParameter("country");
				if(!Validator.isEmptyString(country)){
					int countryId = Integer.parseInt(country);
					townCollection = townDao.findById(countryId);
					helper.setAttribute("townArrayList", townCollection);			
				}

				break;
			default:
				Collection<String> signUpdata = new ArrayList<String>();
				signUpdata.add(null);
				countryCollection.add(signUpdata);

				break;
			}
			helper.setAttribute("requestData", requestData);
		} catch (SQLException sqlException) {
			log.error("execute: SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception) {
			log.error("execute: Exception " + exception.toString());
			throw exception;
		}

		view.setCollection(requestData);

		return view;
	}

}
