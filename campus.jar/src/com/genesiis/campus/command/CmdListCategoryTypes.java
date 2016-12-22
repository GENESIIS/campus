package com.genesiis.campus.command;

//DJ 20161124 c17-provider-criteria-based-filter-search created CmdListCategoryTypes.java
//DJ 20161125 c17-provider-criteria-based-filter-search execute method implementation 

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.CourseProviderTypeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.LevelDAO;
import com.genesiis.campus.entity.MajorDAO;
import com.genesiis.campus.entity.model.CourseProviderResultDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdListCategoryTypes implements ICommand {

	static Logger log = Logger.getLogger(CmdListCategoryTypes.class.getName());

	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return Retrieve category wise course provider types, majors, levels
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		int categoryCode = 0;
		try {
		// List categorywise types		
		String categoryCodeString = helper.getParameter("categoryCode");
		if (UtilityHelper.isNotEmpty(categoryCodeString)) {
			if (UtilityHelper.isInteger(categoryCodeString)) {
				categoryCode = Integer.parseInt(categoryCodeString);
			}
		}
		if (categoryCode > 0) {

			final List<CourseProviderResultDTO> categoryTypes = new CourseProviderDAO().getCategoryWiseTypes(categoryCode);

			final Set<Integer> cpTypeCodeSet = new HashSet<>();
			final Set<Integer> majorCodeSet = new HashSet<>();
			final Set<Integer> levelCodeSet = new HashSet<>();

			for (CourseProviderResultDTO dto : categoryTypes) {
				cpTypeCodeSet.add(dto.getCourserProviderType());
				majorCodeSet.add(dto.getMajor());
				levelCodeSet.add(dto.getLevel());
			}

			// List course provider types for the drop down
			if (cpTypeCodeSet != null && !cpTypeCodeSet.isEmpty()) {
				final Collection<Collection<String>> cpTypeList = new CourseProviderTypeDAO().findCPTypesByCodes(cpTypeCodeSet);
				helper.setAttribute("cpTypeList", cpTypeList);
			}

			// List Majors for the drop down
			if (majorCodeSet != null && !majorCodeSet.isEmpty()) {
				final Collection<Collection<String>> majorList = new MajorDAO().findMajorsByMajorCodes(majorCodeSet);
				helper.setAttribute("majorList", majorList);
			}

			// List Levels for the drop down
			if (levelCodeSet != null && !levelCodeSet.isEmpty()) {
				final Collection<Collection<String>> levelList = new LevelDAO().findLevelsByLevelCodes(levelCodeSet);
				helper.setAttribute("levelList", levelList);
			}
		 }
		}catch (Exception exception) {
			log.error("execute() : Exception " + exception);			
			throw exception;
		}		
		return view;
	}

}
