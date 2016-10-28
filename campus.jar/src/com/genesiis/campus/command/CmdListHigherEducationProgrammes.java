package com.genesiis.campus.command;

//20161025 JH c7-list-higher-education-courses command class CmdListHigherEducationCourses.java created
//20161025 JH c7-list-higher-education-courses execute method created and coding
//20161025 JH c7-list-higher-education-courses execute method coding 
//20161026 JH c7-list-higher-education-courses CmdListHigherEducationCourses modified
//20161026 JH c7-higher-education-landing-page class renamed as CmdListHigherEducationProgrammes.java
//20161027 JH c7-higher-education-landing-page execute method coding

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CourseProviderProgrammeDAO;
import com.genesiis.campus.entity.HigherEducationProgrammeDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListHigherEducationProgrammes implements ICommand {
	static org.apache.log4j.Logger log = Logger
			.getLogger(CmdListHigherEducationProgrammes.class.getName());
	private IView programmeData;

	public CmdListHigherEducationProgrammes() {
	}

	/**
	 * used to list higher education courses
	 * 
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
		
		final String category = helper.getParameter("categoryId");
		

		try {

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());



			programme.setActive(true);
			programme.setExpiryDate(sqlDate);
			programme.setCategory(Integer.parseInt(category));
			programme.setProgrammeStatus(1);

			programmes = higherEducationProgrammeDAO.findById(programme);
			iview.setCollection(programmes);

		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		} finally {
			//get course providers
			
			CourseProviderProgrammeDAO courseProviderDAO =  new CourseProviderProgrammeDAO();
	
			
			CourseProvider courseProvider = new CourseProvider();
			courseProvider.setCourseProviderType(Integer.parseInt(category));
			courseProvider.setCourseProviderStatus(1);

			final Collection<Collection<String>> courseProviders  = courseProviderDAO
					.findById(courseProvider);
			helper.setAttribute("week", courseProviders);
		}

		helper.setAttribute("message", systemMessage.message());

		return iview;
	}
}
