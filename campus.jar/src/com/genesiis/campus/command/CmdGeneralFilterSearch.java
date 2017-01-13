package com.genesiis.campus.command;

//DJ 20170112 c123-general-filter-search-course-provider-MP-dj created CmdGeneralFilterSearch.java

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.util.IDataHelper;
import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.util.List;


public class CmdGeneralFilterSearch implements ICommand{
	
	static Logger log = Logger.getLogger(CmdGeneralFilterSearch.class.getName());
	
	/**	 * 
	 * @author dumani DJ
	 * @param helper IDataHelper Object
	 * @param view IView Object
	 * @return General filter search information.
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try {
			CourseProviderICrud courseProviderICrud=new CourseProviderDAOImpl();
			//TODO:Identify radio button click-Course providers
			String keyWord="icbt";			
			
			//TODO:For testing purpose			
			//Do wild card search on key word
			List result=courseProviderICrud.wildCardSearchOnCourseProvider();
			
			//Find particular course providers
			//find Course providers
			
		} catch (Exception exception) {
			log.error("execute() :Exception  " + exception);			
			throw exception;
		}
		
		return view;
	}

}
