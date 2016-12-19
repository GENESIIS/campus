package com.genesiis.campus.command;

//DJ 20161127 c51-report-courses-by-course-provider-MP-dj created CmdReportGeneration.java


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
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UtilityHelper;

import org.apache.log4j.Logger;

public class CmdReportGeneration  implements ICommand{
	static Logger log = Logger.getLogger(CmdReportGeneration.class.getName());
	
	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return 
	 * @throws Exception
	 */

	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		final CourseProviderDAO providerDAO = new CourseProviderDAO();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		
		try {
			String commandString = helper.getParameter("CCO");
			
			if(commandString!=null && commandString.equalsIgnoreCase(Operation.SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER.getCommandString())){
				final Collection<Collection<String>> providerList=new CourseProviderDAO().getAll();
				iView.setCollection(providerList);				
			}else if(commandString!=null && commandString.equalsIgnoreCase(Operation.REPORT_COURSES_BY_COURSE_PROVIDER.getCommandString())){
				String startDateString = helper.getParameter("startDate");
				String endDateString = helper.getParameter("endDate");
				String providerCodeString = helper.getParameter("cProviderCode");
				int providerCode=0; 
				if (UtilityHelper.isNotEmpty(providerCodeString)) {
					if (UtilityHelper.isInteger(providerCodeString)) {
						providerCode = Integer.parseInt(providerCodeString);
					}
				}
				
				final DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 		
				
				if(providerCode>0){
					//List courses by course Providers
					//param:cpcode,date range
					final Programme programme=new Programme();
					programme.setCourseProvider(providerCode);
					programme.setProgrammeStatus(ApplicationStatus.ACTIVE.getStatusValue());
					try {
						if(UtilityHelper.isNotEmpty(startDateString)){
							programme.setDisplayStartDate(df.parse((startDateString)));
						}
						if(UtilityHelper.isNotEmpty(startDateString)){
						   programme.setExpiryDate((Date)df.parse((endDateString)));
						}
						
					} catch (ParseException parseException) {
						log.error("execute() : ParseException " + parseException.toString());
						systemMessage = SystemMessage.ERROR;
						throw parseException;
					}
					
					
					final Collection<Collection<String>> coursesList=new ProgrammeDAO().findById(programme);
					helper.setAttribute("coursesList", coursesList);
				}				
			}			
		}catch (Exception exception) {
			log.error("execute() : Exception " + exception.toString());
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}	
		return iView;
	}

}
