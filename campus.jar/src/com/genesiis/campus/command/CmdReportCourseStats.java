package com.genesiis.campus.command;

/*20170418 c54-report-course-stats-MP-dj created CmdReportCourseStats*/

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.dao.DistrictDAOImpl;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

import java.sql.SQLException;
import java.util.Collection;

/**The class {@code CmdReportCourseStats} is a form of command handling class.Created for the purpose of handling commands of course 
 * stats report generation. 
 * @author dumani DJ   
 */
public class CmdReportCourseStats implements ICommand{

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		try {			
			String cco = helper.getParameter("CCO");
			
			switch (Operation.getOperation(cco)) {
			case REPORT_COURSE_STATS:				
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

}
