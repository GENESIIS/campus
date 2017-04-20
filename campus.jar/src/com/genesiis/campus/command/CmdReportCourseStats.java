package com.genesiis.campus.command;

/*20170418 c54-report-course-stats-MP-dj created CmdReportCourseStats
 *20170420 c54-report-course-stats-MP-dj create:method generateCourseStatsView().
 *20170420 c54-report-course-stats-MP-dj create:method listProgrammeWiseProvider() and generateReportResults().
 * 
 * */

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.CourseProviderDAOImpl;
import com.genesiis.campus.entity.dao.DistrictDAOImpl;
import com.genesiis.campus.entity.model.CourseProviderSearchDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

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
			case LIST_PROGRAMME_WISE_COURSE_PROVIDER:
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
	
	
	private void listProgrammeWiseProvider(IDataHelper helper) {
		//final Collection<Collection<String>> programmeList=new DistrictDAOImpl().getAllDistricts();
		//helper.setAttribute("programmeList", programmeList);
		
	}
	
	private void generateReportResults(IDataHelper helper) {
		// TODO Auto-generated method stub
		
	}

}
