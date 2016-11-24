package com.genesiis.campus.command;
//DJ 20161124 c17-provider-criteria-based-filter-search created CmdListCategoryTypes.java

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.CourseProviderTypeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.LevelDAO;
import com.genesiis.campus.entity.MajorDAO;
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
