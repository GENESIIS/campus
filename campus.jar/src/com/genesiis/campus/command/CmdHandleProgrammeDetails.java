package com.genesiis.campus.command;

//20170202 DJ c138-add-basic-programme-MP-dj Initiate CmdListProgrammeDetails.java
//20170202 DJ c138-add-basic-programme-MP-dj Initiate Dao call for master data view
//20170207 DJ c138-add-basic-programme-MP-dj Renamed as CmdHandleProgrammeDetails.java

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.ProgrammeDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.FormValidator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

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
				final ProgrammeDTO programmeDTO=new ProgrammeDTO();
				FormValidator.validateProgrammeDetails(helper);
				log.info("execute() ->>>>>>>>>>>>>>>>>>> ADD_PROGRAMME_DETAILS " );	
				//int value = programmeDAO.addProgrammeDetails(programmeDTO);
			}	
			
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}		
		return iView;
	}

}
