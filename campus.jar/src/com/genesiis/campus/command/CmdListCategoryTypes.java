package com.genesiis.campus.command;
//DJ 20161124 c17-provider-criteria-based-filter-search created CmdListCategoryTypes.java

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.CourseProviderTypeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.LevelDAO;
import com.genesiis.campus.entity.MajorDAO;
import com.genesiis.campus.entity.model.CourseProviderResultDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdListCategoryTypes implements ICommand  {
	
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
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		//List categorywise types		
		//CourseProviderResultDTO dto=new CourseProviderResultDTO(); 
		int catCode=0;
		final List <CourseProviderResultDTO> categoryTypes=new CourseProviderDAO().getCategoryWiseTypes(catCode);
		
		final Set<Integer> cpTypeSet=new HashSet<>();
		final Set<Integer> majorSet=new HashSet<>();
		final Set<Integer> levelSet=new HashSet<>();
		
		for(CourseProviderResultDTO dto:categoryTypes){
			cpTypeSet.add(dto.getCourserProviderType());
			majorSet.add(dto.getMajor());
			levelSet.add(dto.getLevel());
		}
		
		//List course provider types for the drop down
		final Collection<Collection<String>> cpTypeList=new CourseProviderTypeDAO().findById("code");
		helper.setAttribute("cpTypeList", cpTypeList);
		
		//List Majors for the drop down
		final Collection<Collection<String>> majorList=new MajorDAO().findById("code");
		helper.setAttribute("majorList", majorList);
		
		//List Levels for the drop down			
		final Collection<Collection<String>> levelList=new LevelDAO().findById("code");
		helper.setAttribute("levelList", levelList);
		return null;
	}

}
