package com.genesiis.campus.command;

//20161025 JH c7-list-higher-education-courses command class CmdListHigherEducationCourses.java created
//20161025 JH c7-list-higher-education-courses execute method created and coding
//20161025 JH c7-list-higher-education-courses execute method coding 
//20161026 JH c7-list-higher-education-courses CmdListHigherEducationCourses modified
//20161026 JH c7-higher-education-landing-page class renamed as CmdListHigherEducationProgrammes.java
//20161027 JH c7-higher-education-landing-page execute method coding
//20161027 JH c7-higher-education-landing-page code modifications to list 20 course providers randomly

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CourseProviderHigherEducationProgrammeDAO;
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

		programme.setActive(true);
		programme.setProgrammeStatus(1);
		programme.setCategory(Integer.parseInt(category));

		try {

			//list higher education courses
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			programme.setExpiryDate(sqlDate);

			programmes = higherEducationProgrammeDAO.findById(programme);
			iview.setCollection(programmes);
			
			
			//list top 5 course providers according to their programme stat rate
			/**
			 * current date is back dated by an year when selecting the
			 * programmes to get programme stat. Only resent statistics are
			 * considered when selecting the featured institutes.
			 */
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, -1); // to get previous year 
			Date lastYear = cal.getTime();

			java.sql.Date expiryDate = new java.sql.Date(lastYear.getTime());

			log.info("sqldate " + expiryDate);

			CourseProviderHigherEducationProgrammeDAO courseProviderDAO = new CourseProviderHigherEducationProgrammeDAO();
			programme.setExpiryDate(expiryDate);
			programme.setLevel(1);//this level=1 is just to identify to get course 
			//providers with higher program stats
			final Collection<Collection<String>> featuredCourseProviders = courseProviderDAO
					.findById(programme);
			helper.setAttribute("featuredInstitutes", featuredCourseProviders);

			
			//list 20 course providers randomly
			programme.setLevel(0);
			final Collection<Collection<String>> courseProviders = courseProviderDAO
					.findById(programme);
			helper.setAttribute("institutes", courseProviders);

			
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		} 

		helper.setAttribute("message", systemMessage.message());

		return iview;
	}
}
