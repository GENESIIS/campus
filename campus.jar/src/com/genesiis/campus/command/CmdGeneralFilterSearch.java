package com.genesiis.campus.command;

//DJ 20170112 c123-general-filter-search-course-provider-MP-dj created CmdGeneralFilterSearch.java

import com.genesiis.campus.entity.CourseProviderICrud;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class CmdGeneralFilterSearch implements ICommand{
	
	static Logger log = Logger.getLogger(CmdGeneralFilterSearch.class.getName());
	
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
			CourseProviderICrud courseProviderICrud=new CourseProviderDAOImpl();
			//TODO:Identify radio button click-Course providers
			final StringBuilder keyWordBuilder=new StringBuilder();
			String selectedTypeString=helper.getParameter("selectedType");
			String keyWordString=helper.getParameter("keyWordString");
			if(keyWordString!=null){
			keyWordBuilder.append("%").append(keyWordString).append("%");
			}
			//String keyWord="%sliit%";	
						
			
			//TODO:For testing purpose			
			//Do wild card search on key word
			final Set<Integer> cpCodeSet=courseProviderICrud.wildCardSearchOnCourseProvider(keyWordBuilder.toString());
			helper.setAttribute("codeList", cpCodeSet);
			
			//Find particular course providers
			//find Course providers
			/*final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			providerSearchDTO.setCourseProviderCodeList(new ArrayList<Integer>(cpCodeSet));
			final Collection<Collection<String>> courseProviderSearchResults = courseProviderICrud.getLightAllCourseProviders(providerSearchDTO);
			view.setCollection(courseProviderSearchResults);*/
			/*<a href="/dist/partials/viewMoreCourseProviders.jsp">Show All</a>*/
		} catch (Exception exception) {
			log.error("execute() :Exception  " + exception);			
			throw exception;
		}
		
		return view;
	}

}
