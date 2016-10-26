package com.genesiis.campus.command;

//20161025 JH c7-list-higher-education-courses command class CmdListHigherEducationCourses.java created
//20161025 JH c7-list-higher-education-courses execute method created and coding
//20161025 JH c7-list-higher-education-courses execute method coding 
//20161026 JH c7-list-higher-education-courses CmdListHigherEducationCourses modified
//20161026 JH c7-higher-education-landing-page class renamed as CmdListHigherEducationProgrammes.java

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.HigherEducationProgrammeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListHigherEducationProgrammes implements ICommand {
	static org.apache.log4j.Logger log = Logger.getLogger(CmdListHigherEducationProgrammes.class.getName());
	private IView programmeData;
	
	public CmdListHigherEducationProgrammes(){
	}
	
	/**
	 * used to list higher education courses
	 * @param IDataHelper 
	 * @param IView
	 * @return Iview
	 * @author JH
	 */
	
	@Override
	public IView execute(IDataHelper helper, IView iview) throws SQLException,
			Exception {
		
		final HigherEducationProgrammeDAO higherEducationProgrammeDAO = new HigherEducationProgrammeDAO();
		final Programme programme = new Programme();
		Collection<Collection<String>> programmes = null;
		
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String category = helper.getParameter("categoryId");
			
			programme.setActive(true);
			programme.setExpiryDate(date);
			programme.setCategory(Integer.parseInt(category));
			
			programmes = higherEducationProgrammeDAO.findById(programme);
			iview.setCollection(programmes);
		
			final CourseProvider courseProvider = new CourseProvider();.
			courseProvider.setCourseProviderType(Integer.parseInt(category));
			courseProvider.setCourseProviderStatus(1);
			
			final CourseProviderDAO courseProviderDAO = new CourseProviderDAO();
			final Collection<Collection<String>> courseProviders = courseProviderDAO.findById(courseProvider);
		
		}catch(Exception exception){
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		
		return iview;
	}
}
