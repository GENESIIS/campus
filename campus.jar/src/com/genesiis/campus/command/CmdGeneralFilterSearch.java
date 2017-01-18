package com.genesiis.campus.command;

//DJ 20170112 c123-general-filter-search-course-provider-MP-dj created CmdGeneralFilterSearch.java
//DJ 20170117 c123-general-filter-search-course-provider-MP-dj Enhance execute() to support provider general filter search via landing page.

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Set;

/**
* The class {@code CmdGeneralFilterSearch} is a form of Command class.It is created for the purpose of handling commands of 
* general filter search via landing page. 
* @author dumani DJ
*
*/
public class CmdGeneralFilterSearch implements ICommand{
	
	static Logger log = Logger.getLogger(CmdGeneralFilterSearch.class.getName());
	
	final static String TYPE_CPROVIDER="CPROVIDER";
	final static String TYPE_PROGRAMME="PROGRAMME";
	
	/**	 * 
	 * @author dumani DJ
	 * @param helper IDataHelper Object
	 * @param view IView Object
	 * @return General filter search command class information.
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try {
			final CourseProviderICrud courseProviderICrud = new CourseProviderDAOImpl();
			final StringBuilder keyWordBuilder = new StringBuilder();
			String selectedTypeString = helper.getParameter("selectedType");
			String keyWordString = helper.getParameter("keyWordString");
			if (UtilityHelper.isNotEmpty(keyWordString)) {
				// Do wild card search on key word
				keyWordBuilder.append("%").append(keyWordString).append("%");				
				if (selectedTypeString.equalsIgnoreCase(TYPE_CPROVIDER)) {
					final Set<Integer> cpCodeSet = courseProviderICrud.wildCardSearchOnCourseProvider(keyWordBuilder.toString());
					helper.setAttribute("codeList", cpCodeSet);
				}
			}

		} catch (Exception exception) {
			log.error("execute() :Exception  " + exception);
			throw exception;
		}
		return view;
	}

}
