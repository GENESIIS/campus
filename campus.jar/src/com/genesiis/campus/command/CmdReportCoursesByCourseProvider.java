package com.genesiis.campus.command;

//DJ 20161127 c51-report-courses-by-course-provider-MP-dj created CmdReportCoursesByCourseProvider.java
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Identify the selected applicationStatus

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdReportCoursesByCourseProvider implements ICommand {
	static Logger log = Logger.getLogger(CmdReportCoursesByCourseProvider.class
			.getName());

	/**
	 * @author DJ
	 * @param helper
	 * @param iView
	 * @return iView
	 * @throws Exception
	 */

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		    
		try {
			iView=new View();
			String cco = helper.getParameter("CCO");
			
			switch (Operation.getOperation(cco)) {
			case SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER:
				final CourseProvider couseProvider=new CourseProvider();
				couseProvider.setCourseProviderStatus(ApplicationStatus.ACTIVE.getStatusValue());
				final Collection<Collection<String>> activeProviderList = new CourseProviderDAO().findById(couseProvider);				
				iView.setCollection(activeProviderList);
				break;
			case REPORT_COURSES_BY_COURSE_PROVIDER:
				generateReportResults(helper);
				break;
			default:
				break;
			}
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());			
			throw exception;
		}
		return iView;
	}
	
	/** Identify input search parameters and retrieve particular  result set according to search criteria.
	 * @author DJ
	 * @param helper
	 * @throws Exception
	 */
	private void generateReportResults(IDataHelper helper)throws  Exception {
		String startDateString = helper.getParameter("startDate");
		String endDateString = helper.getParameter("endDate");
		String providerCodeString = helper.getParameter("cProviderCode");
		String programmeStatus = helper.getParameter("statusValue");
		int providerCode = 0;
		try {
			if (UtilityHelper.isNotEmpty(providerCodeString)) {
				if (UtilityHelper.isInteger(providerCodeString)) {
					providerCode = Integer.parseInt(providerCodeString);
				}
			}

			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			if (providerCode > 0) {
				// List courses by course Providers
				// param:cpcode,date range
				final Programme programme = new Programme();
				programme.setCourseProvider(providerCode);
				programme.setProgrammeStatus(ApplicationStatus.getApplicationStatus(programmeStatus));
				try {
					if (UtilityHelper.isNotEmpty(startDateString)) {
						programme.setDisplayStartDate(df.parse((startDateString)));
					}
					if (UtilityHelper.isNotEmpty(endDateString)) {
						programme.setExpiryDate((Date) df.parse((endDateString)));
					}

				} catch (ParseException parseException) {
					log.error("execute() : ParseException "	+ parseException.toString());
					throw parseException;
				}
				final Collection<Collection<String>> coursesList = new ProgrammeDAO().findById(programme);
				helper.setAttribute("coursesResultList", coursesList);
			}
		} catch (Exception exception) {
		log.error("execute() : Exception " + exception.toString());
		throw exception;
		}
	}
}
