package com.genesiis.campus.command;

//20170202 DJ c138-add-basic-programme-MP-dj Initiate CmdListProgrammeDetails.java
//20170202 DJ c138-add-basic-programme-MP-dj Initiate Dao call for master data view
//20170207 DJ c138-add-basic-programme-MP-dj Renamed as CmdHandleProgrammeDetails.java
//20170213 DJ c138-add-basic-programme-MP-dj Implemented populateFormData() to populate input data
//20170221 DJ c145-add-enhanced-programme Add method retrieveProviderTown(IDataHelper helper, IView iView).

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.DistrictICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.DistrictDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.FormValidator;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * The class {@code CmdHandleProgrammeDetails} is a form of Command class.It is created for the purpose of handling commands of
 * programme details. 
 * @author dumani DJ
 *
*/
public class CmdHandleProgrammeDetails  implements ICommand {
	static Logger log = Logger.getLogger(CmdHandleProgrammeDetails.class.getName());
	final ProgrammeICrud programmeDAO = new ProgrammeDAOImpl();
	/**
	 * @author DJ Dumani
	 * @param helper
	 * @param view
	 * @return view
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		
		String ccoString = helper.getParameter("CCO");
		try {
			if ("LIST_PROGRAMME_ADD_VIEW".equalsIgnoreCase(ccoString)) {
				populateProgrammeInsertionView(helper, iView);
			} else if ("LIST_COURSE_PROVIDER_TOWN".equalsIgnoreCase(ccoString)) {
				retrieveProviderTown(helper, iView);
			} else if ("ADD_PROGRAMME_DETAILS".equalsIgnoreCase(ccoString)) {
				manageProgrammeInsertion(helper, iView);
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return iView;
	}
	
	private void retrieveProviderTown(IDataHelper helper, IView iView) {
		final DistrictICrud districtDAO=new DistrictDAOImpl();
		try {
			int providerCode=0;
			final Collection<Collection<String>> allClassTypes = districtDAO.getCourseProviderTown(providerCode);
			/*helper.setAttribute("allClassTypes", allClassTypes);*/
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * Populate program insertion interface.Load master data for particular drop downs.
	 * @author DJ Dumani
	 * @param helper
	 * @param iView  	  
	 */	
	private void populateProgrammeInsertionView(IDataHelper helper, IView iView)
			throws Exception {
		final CourseProviderICrud providerDAO = new CourseProviderDAOImpl();	

		try {
			final Collection<Collection<String>> courseProviderSearchResults = providerDAO.getLightAllCourseProviders();
			iView.setCollection(courseProviderSearchResults);		

			final Collection<Collection<String>> allCategories = programmeDAO.getAllCategories();
			helper.setAttribute("allCategories", allCategories);

			final Collection<Collection<String>> allMajors = programmeDAO.getAllMajors();
			helper.setAttribute("allMajors", allMajors);

			final Collection<Collection<String>> allLevels = programmeDAO.getAllLevels();
			helper.setAttribute("allLevels", allLevels);

			final Collection<Collection<String>> allClassTypes = programmeDAO.getAllClassTypes();
			helper.setAttribute("allClassTypes", allClassTypes);			
			
		} catch (Exception exception) {
			log.error("populateProgrammeInsertionView() : Exception "
					+ exception);
			throw exception;
		}
	}
	
	
	/** Manage form data(input values) for database insertion.
	 * @author DJ dumani
	 * @param helper 	  
	 * @param iView 
	 */	
	private void manageProgrammeInsertion(IDataHelper helper, IView iView)throws Exception {
		boolean isOkToSave = FormValidator.validateProgrammeDetails(helper);
		if (isOkToSave) {
			final ProgrammeDTO programmeDTO = new ProgrammeDTO();
			populateFormData(programmeDTO, helper);
			int successStatus = programmeDAO.addProgrammeDetails(programmeDTO);
			if (successStatus > 0) {
				helper.setAttribute("successMessage", "success");
			}
		}
	}


	/** Populate programmeDTO with form data. 
	 * @author DJ dumani
	 * @param helper
	 * @param programmeDTO	  
	 */
	private void populateFormData(ProgrammeDTO programmeDTO, IDataHelper helper)
			throws Exception {

		try {			
			String categoryString = helper.getParameter("selectedCategory");
			String majorString = helper.getParameter("selectedMajor");
			String levelString = helper.getParameter("selectedLevel");
			String providerString = helper.getParameter("selectedProvider");
			String classTypeString = helper.getParameter("selectedClassType");
			String durationString = helper.getParameter("courseDuration");		
		
			if(UtilityHelper.isNotEmpty(categoryString)){
				programmeDTO.setCategory(Integer.parseInt(categoryString));
			}			
			if(UtilityHelper.isNotEmpty(majorString)){
				programmeDTO.setMajor(Integer.parseInt(majorString));
			}			
			if(UtilityHelper.isNotEmpty(levelString)){
				programmeDTO.setLevel(Integer.parseInt(categoryString));
			}
			if(UtilityHelper.isNotEmpty(providerString)){
				programmeDTO.setCourseProvider(Integer.parseInt(providerString));
			}
			if(UtilityHelper.isNotEmpty(classTypeString)){
				programmeDTO.setClassType(Integer.parseInt(classTypeString));
			}else {
				//If classtype is not selected save Default course as default classtype.
				programmeDTO.setClassType(-1);
			}
			programmeDTO.setName(helper.getParameter("courseName"));
			programmeDTO.setDescription(helper
					.getParameter("courseDescription"));
			if (UtilityHelper.isNotEmpty(durationString)) {
				programmeDTO.setDuration(Float.valueOf(durationString));
			}
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String commencementDateString = helper
					.getParameter("commencementDate");
			String expirationDate = helper.getParameter("expirationDate");
			if (UtilityHelper.isNotEmpty(commencementDateString)) {
				programmeDTO.setDisplayDatrtDate(df
						.parse((commencementDateString)));
			}
			if (UtilityHelper.isNotEmpty(expirationDate)) {
				programmeDTO.setExpirationDate(df.parse((expirationDate)));
			}
			
			programmeDTO.setProgrammeStatus(ApplicationStatus.getApplicationStatus(helper.getParameter("courseStatus")));
			programmeDTO.setCounselerName(helper.getParameter("counselorName"));
			programmeDTO.setCounselerPhone(helper.getParameter("counselorTel"));
			programmeDTO.setEmail(helper.getParameter("counselorEmail"));
			//TODO:Temparory data for EntryRequiremtns
			programmeDTO.setEntryRequiremtns("entry requirements");
			programmeDTO.setCrtOn(new Date());
			//TODO:Shloud add system user as create by.Will add in near future.
			programmeDTO.setCrtBy("admin");
			
		} catch (ParseException parseException) {
			log.error("populateFormData() : ParseException "
					+ parseException.toString());
			throw parseException;
		}catch (Exception exception) {
			log.error("populateFormData() : Exception "
					+ exception);
			throw exception;
		}

	}

}
