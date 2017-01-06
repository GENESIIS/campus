package com.genesiis.campus.command;

//DJ 20161127 c51-report-courses-by-course-provider-MP-dj created CmdReportCoursesByCourseProvider.java
//20161221 DJ c51-report-courses-by-course-provider-MP-dj Identify the selected applicationStatus

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.ProgrammeSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * The class {@code CmdReportCoursesByCourseProvider} is a form of Command class.It is  created for the purpose of handling commands of
 * courses by course provider report generation. 
 * @author dumani DJ
 *
*/
public class CmdReportCoursesByCourseProvider implements ICommand {
	static Logger log = Logger.getLogger(CmdReportCoursesByCourseProvider.class
			.getName());

	/**
	 * @author DJ
	 * @param helper -  IDataHelper object
	 * @param iView -   IView Object
	 * @return iView - Collection of strings 
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
				generateReportSearchView(helper);				
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
	
	
	/** Retrieve course providers for drop down list in search view.
	 * @author dumani DJ
	 * @param helper -IDataHelper object
	 * @throws Exception
	 */	
	private void generateReportSearchView(IDataHelper helper) throws Exception {
		// TODO: Initiate the couseProvider object for future purposes
		final CourseProvider couseProvider = new CourseProvider();
		// couseProvider.setCourseProviderStatus(ApplicationStatus.ACTIVE.getStatusValue());
		try {
			final Collection<Collection<String>> courseProviderList = new CourseProviderDAO().findById(couseProvider);
			helper.setAttribute("courseProviderList", courseProviderList);
		} catch (Exception exception) {
			log.error("generateReportSearchView() : Exception "
					+ exception.toString());
			throw exception;
		}
	}
	

	/** Identify input search parameters and retrieve particular  result set according to search criteria.
	 * @author dumani DJ
	 * @param helper -IDataHelper object
	 * @throws ParseException,Exception
	 */
	private void generateReportResults(IDataHelper helper)throws ParseException, Exception {
		String startDateString = helper.getParameter("startDate");
		String endDateString = helper.getParameter("endDate");
		String providerCodeString = helper.getParameter("cProviderCode");
		String providerStatus = helper.getParameter("providerStatus");
		String programmeStatus = helper.getParameter("courseStatus");
		int providerCode = 0;
		try {
			if (UtilityHelper.isNotEmpty(providerCodeString)) {
				if (UtilityHelper.isInteger(providerCodeString)) {
					providerCode = Integer.parseInt(providerCodeString);
				}
			}

			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// List courses by course Providers
			final ProgrammeSearchDTO programme = new ProgrammeSearchDTO();
			programme.setCourseProvider(providerCode);
			programme.setProviderStatus(ApplicationStatus.getApplicationStatus(providerStatus));
			programme.setProgrammeStatus(ApplicationStatus.getApplicationStatus(programmeStatus));
			try {
				if (UtilityHelper.isNotEmpty(startDateString)) {
					programme.setDisplayStartDate(df.parse((startDateString)));
				}
				if (UtilityHelper.isNotEmpty(endDateString)) {
					programme.setExpiryDate((Date) df.parse((endDateString)));
				}

			} catch (ParseException parseException) {
				log.error("execute() : ParseException "
						+ parseException.toString());
				throw parseException;
			}
			final Collection<Collection<String>> allProgrammeResultList = new ProgrammeDAO()
					.findById(programme);
			helper.setAttribute("allProgrammeResultList",
					allProgrammeResultList);		
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			throw exception;
		}
	}
}
