package com.genesiis.campus.command;

//20161209 JH c39-add-course-provider CmdListCourseProviderRegisterPage.java created
//20161220 JH c39-add-course-provider execute method coding: added switch case
//20161229 JH c39-add-course-provider added new switch case LIST_COUESE_PROVIDER_TYPES 
//20161230 JH c39-add-course-provider case LIST_PROVIDER_REGISTRATION_PAGE modified 
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170203 JH c39-add-course-provider mx modification: removed unwanted Logger import

import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.CourseProviderTypeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
			case LIST_PROVIDER_REGISTRATION_PAGE:
				ICrud country2Dao = new Country2DAO();
				countryCollection = country2Dao.getAll();
				helper.setAttribute("countryArrayList", countryCollection);
				ICrud providerTypeDAO = new CourseProviderTypeDAO();

				//get course provider types
				providerTypeCollection = providerTypeDAO.getAll();
				
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

				ICrud townDao = new TownDAO();
				int countryId = Integer.parseInt(helper.getParameter("country"));
				townCollection = townDao.findById(countryId);
				helper.setAttribute("townArrayList", townCollection);
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
