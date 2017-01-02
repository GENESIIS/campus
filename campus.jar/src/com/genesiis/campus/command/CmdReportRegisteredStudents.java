package com.genesiis.campus.command;

//DJ 20161228 c53-report-registered-students-MP-dj created CmdReportRegisteredStudents.java

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ReportStudentDAO;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.entity.model.StudentSearchDTO;
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


public class CmdReportRegisteredStudents implements ICommand {
	
	static Logger log = Logger.getLogger(CmdReportCoursesByCourseProvider.class.getName());
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
			case SEARCH_VIEW_REGISTERED_STUDENTS:
				final Collection<Collection<String>> providerList = new CourseProviderDAO().getAll();
				iView.setCollection(providerList);
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
	 * @author DJ
	 * @param helper
	 * @throws Exception
	 */
	private void generateReportResults(IDataHelper helper)
			throws Exception {
		String startDateString = helper.getParameter("startDate");
		String endDateString = helper.getParameter("endDate");
		String studentStatus = helper.getParameter("studentStatus");
		final StudentSearchDTO student = new StudentSearchDTO();
		try {
			student.setStatus(ApplicationStatus.getApplicationStatus(studentStatus));
			student.setAccountType(StudentAccountType.REGISTERED.getAccountTypeValue());
			try {
				final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				if (UtilityHelper.isNotEmpty(startDateString)) {
					student.setFromDate(df.parse((startDateString)));
				}
				if (UtilityHelper.isNotEmpty(endDateString)) {
					student.setToDate(df.parse((endDateString)));
				}		

			} catch (ParseException parseException) {
				log.error("generateReportResults() : ParseException "
						+ parseException.toString());
				throw parseException;
			}
			final Collection<Collection<String>> registeredStudentList = new ReportStudentDAO().findById(student);
			helper.setAttribute("registeredStudentList", registeredStudentList);
		} catch (Exception exception) {
			log.error("generateReportResults() : Exception " + exception.toString());
			throw exception;
		}

	}

}
