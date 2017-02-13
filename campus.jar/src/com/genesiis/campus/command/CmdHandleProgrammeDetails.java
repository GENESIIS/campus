package com.genesiis.campus.command;

//20170202 DJ c138-add-basic-programme-MP-dj Initiate CmdListProgrammeDetails.java
//20170202 DJ c138-add-basic-programme-MP-dj Initiate Dao call for master data view
//20170207 DJ c138-add-basic-programme-MP-dj Renamed as CmdHandleProgrammeDetails.java
//20170213 DJ c138-add-basic-programme-MP-dj Implemented populateFormData() to populate input data

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.util.IDataHelper;
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
 * The class {@code CmdListProgrammeDetails} is a form of Command class.It is created for the purpose of handling commands of
 * add programme details. 
 * @author dumani DJ
 *
*/
public class CmdHandleProgrammeDetails  implements ICommand {
	static Logger log = Logger.getLogger(CmdHandleProgrammeDetails.class.getName());
	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return 
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		final CourseProviderICrud providerDAO = new CourseProviderDAOImpl();	
		final ProgrammeICrud programmeDAO = new ProgrammeDAOImpl();	
		String ccoString = helper.getParameter("CCO");
		try {				
			
			if("LIST_PROGRAMME_ADD_VIEW".equalsIgnoreCase(ccoString)){				
			
				final Collection<Collection<String>> courseProviderSearchResults = providerDAO.getLightAllCourseProviders();
				iView.setCollection(courseProviderSearchResults);
				
				final Collection<Collection<String>> allCategories= programmeDAO.getAllCategories();
				helper.setAttribute("allCategories",allCategories);
				
				final Collection<Collection<String>> allMajors= programmeDAO.getAllMajors();
				helper.setAttribute("allMajors",allMajors);
				
				final Collection<Collection<String>> allLevels= programmeDAO.getAllLevels();
				helper.setAttribute("allLevels",allLevels);
				
				final Collection<Collection<String>> allClassTypes= programmeDAO.getAllClassTypes();
				helper.setAttribute("allClassTypes",allClassTypes);		
				
			}else if("ADD_PROGRAMME_DETAILS".equalsIgnoreCase(ccoString)){				
				boolean isOkToSave=FormValidator.validateProgrammeDetails(helper);
				log.info("execute() ->>>>>>>>>>>>>>>>>>> ADD_PROGRAMME_DETAILS " );	
				//if(isOkToSave){
					final ProgrammeDTO programmeDTO=new ProgrammeDTO();
					populateFormData(programmeDTO,helper);
				    int value = programmeDAO.addProgrammeDetails(programmeDTO);
				//}
			}	
			
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}		
		return iView;
	}
	
	
	/**
	 * @author DJ
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

			//programmeDTO.setProgrammeStatus(Integer.parseInt(helper	.getParameter("courseStatus")));
			programmeDTO.setCounselerName(helper.getParameter("counselorName"));
			programmeDTO.setCounselerPhone(helper.getParameter("counselorTel"));
			programmeDTO.setEmail(helper.getParameter("counselorEmail"));
			//TODO:Temparory data for EntryRequiremtns
			programmeDTO.setEntryRequiremtns("entry requirements");
			programmeDTO.setCrtOn(new Date());
			programmeDTO.setCrtBy("DJ");			
			programmeDTO.setModOn(new Date());
			programmeDTO.setModBy("DJ");

		} catch (ParseException parseException) {
			log.error("populateFormData() : ParseException "
					+ parseException.toString());
			throw parseException;
		}

	}

}
