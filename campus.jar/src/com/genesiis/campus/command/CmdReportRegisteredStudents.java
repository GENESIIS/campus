package com.genesiis.campus.command;

//DJ 20161228 c53-report-registered-students-MP-dj created CmdReportRegisteredStudents.java

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.entity.ReportStudentDAO;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


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
	private void generateReportResults(IDataHelper helper) {
		String startDateString = helper.getParameter("startDate");
		String endDateString = helper.getParameter("endDate");
		final Student student = new Student();
		try {
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			/*if (UtilityHelper.isNotEmpty(startDateString)) {
				student.setDisplayStartDate(df.parse((startDateString)));
			}
			if (UtilityHelper.isNotEmpty(endDateString)) {
				student.setExpiryDate((Date) df.parse((endDateString)));
			}*/

		} catch (ParseException parseException) {
			log.error("execute() : ParseException "	+ parseException.toString());
			throw parseException;
		}
		final Collection<Collection<String>> registeredStudentList = new ReportStudentDAO().findById(student);
		helper.setAttribute("registeredStudentList", registeredStudentList);
		
	}

}
