package com.genesiis.campus.command;

//20170202 DJ c138-add-basic-programme-MP-dj Initiate CmdListProgrammeDetails.java
//20170202 DJ c138-add-basic-programme-MP-dj Initiate Dao call for master data view

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * The class {@code CmdListProgrammeDetails} is a form of Command class.It is created for the purpose of handling commands of
 * add programme details. 
 * @author dumani DJ
 *
*/
public class CmdListProgrammeDetails  implements ICommand {
	static Logger log = Logger.getLogger(CmdListProgrammeDetails.class.getName());
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
		
		try {
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			final Map<String, String[]> searchParamCollection = helper.getParameterMap();			
			
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
			
			
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}		
		return iView;
	}

}
