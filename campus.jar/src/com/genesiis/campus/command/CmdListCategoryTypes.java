package com.genesiis.campus.command;

//DJ 20161124 c17-provider-criteria-based-filter-search created CmdListCategoryTypes.java
//DJ 20161125 c17-provider-criteria-based-filter-search execute method implementation
//DJ 20170108 c6-list-available-institutes-on-the-view  Adjust dao calls to new Icrud interfaces

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderResultDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class {@code CmdListCategoryTypes} is a form of Command class.It is created for the purpose of handling commands of
 * listing category types. 
 * @author dumani DJ
 *
*/
public class CmdListCategoryTypes implements ICommand {

	static Logger log = Logger.getLogger(CmdListCategoryTypes.class.getName());

	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return Retrieve category wise course provider types, majors, levels.
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		int categoryCode = 0;
		try {
			//List categorywise types
			String categoryCodeString = helper.getParameter("categoryCode");
			if (UtilityHelper.isNotEmpty(categoryCodeString)) {
				if (UtilityHelper.isInteger(categoryCodeString)) {
					categoryCode = Integer.parseInt(categoryCodeString);
				}
			}
			if (categoryCode > 0) {
				final CourseProviderICrud courseProviderDAO=new CourseProviderDAOImpl();
				final List<CourseProviderResultDTO> categoryTypes =courseProviderDAO.getCategoryWiseTypes(categoryCode);

				final Set<Integer> cpTypeCodeSet = new HashSet<Integer>();
				final Set<Integer> majorCodeSet = new HashSet<Integer>();
				final Set<Integer> levelCodeSet = new HashSet<Integer>();

				for (final CourseProviderResultDTO dto : categoryTypes) {
					cpTypeCodeSet.add(dto.getCourserProviderType());
					majorCodeSet.add(dto.getMajor());
					levelCodeSet.add(dto.getLevel());
				}			
				// List course provider types for the drop down
				if (cpTypeCodeSet != null && !cpTypeCodeSet.isEmpty()) {
					final Collection<Collection<String>> cpTypeList = courseProviderDAO.findCPTypesByCPTypeCodes(cpTypeCodeSet);
					helper.setAttribute("cpTypeList", cpTypeList);
				}
				final ProgrammeICrud programmeDAO=new ProgrammeDAOImpl();
				// List Majors for the drop down
				if (majorCodeSet != null && !majorCodeSet.isEmpty()) {
					final Collection<Collection<String>> majorList =programmeDAO.findMajorsByMajorCodes(majorCodeSet);
					helper.setAttribute("majorList", majorList);
				}

				// List Levels for the drop down
				if (levelCodeSet != null && !levelCodeSet.isEmpty()) {
					final Collection<Collection<String>> levelList = programmeDAO.findLevelsByLevelCodes(levelCodeSet);
					helper.setAttribute("levelList", levelList);
				}
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			throw exception;
		}
		return view;
	}

}
