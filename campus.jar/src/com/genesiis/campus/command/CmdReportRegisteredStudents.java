package com.genesiis.campus.command;

//DJ 20161228 c53-report-registered-students-MP-dj created CmdReportRegisteredStudents.java
//DJ 20170104 c53-report-registered-students-MP-dj Identified command SEARCH_VIEW_REGISTERED_STUDENTS

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.entity.dao.AdminReportDAOImpl;
import com.genesiis.campus.entity.dao.DistrictDAOImpl;
import com.genesiis.campus.entity.model.StudentSearchDTO;
import com.genesiis.campus.entity.model.StudentSearchResultDTO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.StudentAccountType;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**The class {@code CmdReportRegisteredStudents} is a form of command handling class.Created for the purpose of handling commands of registered
 * student report generation. 
 * @author dumani DJ   
 */

public class CmdReportRegisteredStudents implements ICommand {
	
	static Logger log = Logger.getLogger(CmdReportRegisteredStudents.class.getName());
	/**
	 * @author dumani DJ
	 * @param helper IDataHelper object
	 * @param iView IView 
	 * @return iView Collection of strings
	 * @throws SQLException, Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		try {			
			String cco = helper.getParameter("CCO");
			
			switch (Operation.getOperation(cco)) {
			case SEARCH_VIEW_REGISTERED_STUDENTS:				
				final Collection<Collection<String>> districtList=new DistrictDAOImpl().getAllDistricts();
				helper.setAttribute("districtList", districtList);
				break;
			case REPORT_REGISTERED_STUDENTS:
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
	 * @author dumani DJ
	 * @param helper IDataHelper object
	 * @throws ParseException, Exception
	 */
	private void generateReportResults(IDataHelper helper)throws ParseException, Exception {
		String startDateString = helper.getParameter("startDate");
		String endDateString = helper.getParameter("endDate");
		String studentStatus = helper.getParameter("studentStatus");
		String districtCodeString = helper.getParameter("districtCode");
		final StudentSearchDTO studentSearchDTO = new StudentSearchDTO();
		try {
			studentSearchDTO.setStudentStatus(ApplicationStatus.getApplicationStatus(studentStatus));
			studentSearchDTO.setAccountType(StudentAccountType.REGISTERED.getAccountTypeValue());
			if (UtilityHelper.isNotEmpty(districtCodeString)) {
				if (UtilityHelper.isInteger(districtCodeString)) {
					studentSearchDTO.setDistrictCode(Integer.valueOf(districtCodeString));
				}
			}					

			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if (UtilityHelper.isNotEmpty(startDateString)) {
				studentSearchDTO.setFromDate(df.parse((startDateString)));
			}
			if (UtilityHelper.isNotEmpty(endDateString)) {
				studentSearchDTO.setToDate(df.parse((endDateString)));
			}

			//final Collection<Collection<String>> registeredStudentList = new AdminReportDAOImpl().getRegisteredStudentReport(studentSearchDTO);
			final List<StudentSearchResultDTO> registeredStudentList = new AdminReportDAOImpl().getRegisteredStudentReport(studentSearchDTO);
			helper.setAttribute("registeredStudentList", registeredStudentList);
		}  catch (ParseException parseException) {
			log.error("generateReportResults() : ParseException "+ parseException.toString());
			throw parseException;
		}catch (Exception exception) {
			log.error("generateReportResults() : Exception " + exception.toString());
			throw exception;
		}
	}

}
