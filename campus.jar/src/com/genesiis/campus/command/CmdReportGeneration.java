package com.genesiis.campus.command;

//DJ 20161127 c51-report-courses-by-course-provider-MP-dj created CmdReportGeneration.java

import java.sql.SQLException;
import java.util.Collection;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.ProgrammeDAO;
import com.genesiis.campus.util.IDataHelper;
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
			String providerCodeString = helper.getParameter("providerCode");
			int providerCode=0; 
			if (UtilityHelper.isNotEmpty(providerCodeString)) {
				if (UtilityHelper.isInteger(providerCodeString)) {
					providerCode = Integer.parseInt(providerCodeString);
				}
			}
			if(providerCode>0){
				//List courses by course Providers
				//param:cpcode,date range
				final Collection<Collection<String>> coursesList=new ProgrammeDAO().findById(providerCode);
				
			}
			final Collection<Collection<String>> providerList=new CourseProviderDAO().getAll();
			iView.setCollection(providerList);
			
		}catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}	
		return iView;
	}

}
