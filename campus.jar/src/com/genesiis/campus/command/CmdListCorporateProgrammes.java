package com.genesiis.campus.command;

//20161026 MM c5-corporate-training-landing-page INIT CmdListCorporateProgrammes.java
//20161027 MM c5-corporate-training-landing-page INIT Modified execute() method to include 
// 				fetching of CourseProviders

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CorporateProgrammeDAO;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.Programme;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdListCorporateProgrammes implements ICommand {
	
	static Logger log = Logger.getLogger(CmdListCorporateProgrammes.class.getName());

	final ICrud programmeDao = new CorporateProgrammeDAO();	
	final ICrud courseProviderDao = new CourseProviderDAO();	
		
	public CmdListCorporateProgrammes() {
		
	}

	@Override 
	public IView execute(IDataHelper helper, IView iview) throws Exception {
		
		String message = "";
		Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();
		Collection<Collection<String>> courseProviderCollection = new ArrayList<Collection<String>>();
		List<String> msgList = new ArrayList<String>();
		int categoryCode = -1;
		try {
			if (helper.getParameter("category") == null) {
				log.error("The provided value for category is null!");
				msgList.add("The provided value for category is null!");
				throw new IllegalArgumentException("The provided value for category is null!");
			} 
			
			categoryCode = Integer.parseInt(helper.getParameter("category"));
			
			Programme programme = new Programme();
			programme.setCategory(categoryCode);
			
			programmeCollection = programmeDao.findById(programme);
			
			CourseProvider courseProvider = new CourseProvider();
			courseProvider.setCourseProviderType(1);
			
			courseProviderCollection = courseProviderDao.findById(courseProvider);
			
		} catch (NumberFormatException nfe) {
			log.info("execute(IDataHelper, IView) : NumberFormatException " + nfe.toString());
			msgList.add(SystemMessage.ERROR.message());
			msgList.add(nfe.getMessage());	
			throw nfe;
		} catch (IllegalArgumentException iae) {
			log.info("execute(IDataHelper, IView) : IllegalArgumentException " + iae.toString());
			msgList.add(SystemMessage.ERROR.message());
			msgList.add(iae.getMessage());	
		}  catch (Exception e) {
			Log.info("execute(IDataHelper, IView) : Exception " + e.toString());
			msgList.add(SystemMessage.ERROR.message());
			msgList.add("Unknown error occured while fetching records to display");
			throw e;
		}
		
		return  iview;
	}
	
}
