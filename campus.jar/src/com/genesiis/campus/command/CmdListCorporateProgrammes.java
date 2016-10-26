package com.genesiis.campus.command;

//20161026 MM c5-corporate-training-landing-page INIT CmdListCorporateProgrammes.java

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CorporateProgrammeDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdListCorporateProgrammes implements ICommand {
	
	static Logger log = Logger.getLogger(CmdListCorporateProgrammes.class.getName());

	final ICrud dao = new CorporateProgrammeDAO();	
		
	public CmdListCorporateProgrammes() {
		
	}

	@Override 
	public IView execute(IDataHelper helper, IView iview) throws Exception {
		
		String message = "";
		Collection<Collection<String>> programmeCollection = new ArrayList<Collection<String>>();
		List<String> msgList = new ArrayList<String>();
		
		try {
			if (helper.getParameter("category") == null) {
				log.error("The provided value for category is null!");
				throw new IllegalArgumentException("The provided value for category is null!");
				msgList.add("The provided value for category is null!");
			} 
			
			
		} catch (IllegalArgumentException iae) {
			
		} 
		
		return  iview;
	}
	
}
