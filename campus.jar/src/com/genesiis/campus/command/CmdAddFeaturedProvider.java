package com.genesiis.campus.command;

//20161122 JH c39-add-course-provider CmdAddFeaturedProvider.java command class created
//20161122 JH c39-add-course-provider implemented ICommand class

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdAddFeaturedProvider implements ICommand{
	static Logger log = Logger.getLogger(CmdAddFeaturedProvider.class.getName());
	
	private IView courseProviderData;

	public CmdAddFeaturedProvider(IView courseProviderData) {
		this.courseProviderData = courseProviderData;
	}

	public CmdAddFeaturedProvider() {

	}
	
	/**
	 * execute method used to handle the request related to featured course provider 
	 * registration. 
	 * @author JH
	 * @param IDataHelper
	 * @param IView
	 * @return IView Objects
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		ICrud CourseProviderDAO = new CourseProviderDAO();
		Collection<Collection<String>> categoryCollection = null;
		
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		final CourseProvider courseProvider = new CourseProvider();
		
		try{
			
			categoryCollection = CourseProviderDAO.add(courseProvider);
			
			
			
		}catch(Exception exception){
			log.error("execute() : " + exception.toString());
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		
		
		return null;
	}

}
