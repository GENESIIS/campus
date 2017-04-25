package com.genesiis.campus.command;

/*20170418 DJ c54-report-course-stats-MP-dj created CmdReportCourseStats
 *20170420 DJ c54-report-course-stats-MP-dj create:method generateCourseStatsView().
 *20170420 DJ c54-report-course-stats-MP-dj create:method listProgrammeWiseProvider() and generateReportResults().
 *20170421 DJ c54-report-course-stats-MP-dj create:implement listProgrammeWiseProvider().
 *20170421 DJ c54-report-course-stats-MP-dj create:implement generateReportResults().
 *20170421 DJ c54-report-course-stats-MP-dj Identify the input fields in generateReportResults.
 *20170424 DJ c54-report-course-stats-MP-dj refactored code in method generateReportResults().
 * */

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeICrud;
import com.genesiis.campus.entity.dao.AdminReportDAOImpl;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.DistrictDAOImpl;
import com.genesiis.campus.entity.dao.ProgrammeDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.entity.model.CourseStatSearchDTO;
import com.genesiis.campus.entity.model.CourseStatSearchResultDTO;
import com.genesiis.campus.entity.model.ProgrammeSearchDTO;
import com.genesiis.campus.entity.model.StudentSearchResultDTO;
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
import java.util.List;

/**The class {@code CmdReportCourseStats} is a form of command handling class.Created for the purpose of handling commands of course 
 * stats report generation. 
 * @author dumani DJ   
 */
public class CmdReportCourseStats implements ICommand{
	static Logger log = Logger.getLogger(CmdReportCourseStats.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		try {			
			String cco = helper.getParameter("CCO");			
			switch (Operation.getOperation(cco)) {
			case SEARCH_VIEW_COURSE_STATS:
				generateCourseStatsView(helper);
				break;
			case LIST_COURSE_PROVIDER_WISE_PROGRAMME:
				listProgrammeWiseProvider(helper);				
				break;
			case REPORT_COURSE_STATS:	
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
	private void generateCourseStatsView(IDataHelper helper)throws Exception {
		try {
			final CourseProviderSearchDTO providerSearchDTO = new CourseProviderSearchDTO();
			providerSearchDTO.setCourseProviderStatus(ApplicationStatus.ACTIVE.getStatusValue());
			final Collection<Collection<String>> courseProviderList = new CourseProviderDAOImpl().getLightAllCourseProviders(providerSearchDTO);
			helper.setAttribute("courseProviderList", courseProviderList);
		} catch (Exception exception) {
			log.error("generateCourseStatsView() : Exception " + exception);
			throw exception;
		}
	}
	
	/** Retrieve programmes of selected course provider.
	 * @author dumani DJ
	 * @param helper -IDataHelper object
	 * @throws Exception
	 */
	private void listProgrammeWiseProvider(IDataHelper helper)throws Exception {				
		try {
			final ProgrammeICrud programmeDAO=new ProgrammeDAOImpl();
			final ProgrammeSearchDTO searchDTO=new ProgrammeSearchDTO();
			
			String providerCodeString = helper.getParameter("providerCode");
			int providerCode = 0;
			if (UtilityHelper.isNotEmpty(providerCodeString)) {
				if (UtilityHelper.isInteger(providerCodeString)) {
					providerCode = Integer.parseInt(providerCodeString);
				}
			}
			searchDTO.setCourseProvider(providerCode);
			searchDTO.setProgrammeStatus(ApplicationStatus.ACTIVE.getStatusValue());
			
			final Collection<Collection<String>> programmeList = programmeDAO.getLightProgrammes(searchDTO);
			helper.setAttribute("programmeList", programmeList);
			
		} catch (Exception exception) {
			log.error("listProgrammeWiseProvider() : Exception " + exception);
			throw exception;
		}		
	}
	
	
	/** Identify input search parameters and retrieve particular result set according to search criteria.
	 * @author dumani DJ
	 * @param helper IDataHelper object
	 * @throws  Exception
	 */
	private void generateReportResults(IDataHelper helper)throws Exception {
		try {
			String providerCodeString = helper.getParameter("providerCode");
			String programmeCodeString = helper.getParameter("programmeCode");
			String startDateString = helper.getParameter("startDate");
			String endDateString = helper.getParameter("endDate");

			final CourseStatSearchDTO searchDTO = new CourseStatSearchDTO();
			//int providerCode = 0;
			if (UtilityHelper.isNotEmpty(providerCodeString)) {
				if (UtilityHelper.isInteger(providerCodeString)) {
					int providerCode = Integer.parseInt(providerCodeString);
					searchDTO.setProviderCode(providerCode);
				}
			}
			if (UtilityHelper.isNotEmpty(programmeCodeString)) {
				if (UtilityHelper.isInteger(programmeCodeString)) {
					int programmeCode = Integer.parseInt(programmeCodeString);
					searchDTO.setProgrammeCode(programmeCode);
				}
			}			
			
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			if (UtilityHelper.isNotEmpty(startDateString)) {
				searchDTO.setFromDate(df.parse((startDateString)));
			} else {
				
			}
			if (UtilityHelper.isNotEmpty(endDateString)) {
				searchDTO.setToDate(df.parse((endDateString)));
			} else {
				
			}				

			final List<CourseStatSearchResultDTO> courseStatList = new AdminReportDAOImpl().getProgrammeStatsReport(searchDTO);

			helper.setAttribute("courseStatList", courseStatList);
			
		} catch (Exception exception) {
			log.error("generateReportResults() : Exception " + exception);
			throw exception;
		}
	}

}
