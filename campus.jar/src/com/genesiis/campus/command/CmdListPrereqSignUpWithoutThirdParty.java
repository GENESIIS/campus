package com.genesiis.campus.command;
//20161205 DN C18-student-signup-without-using-third-party-application-dn created the class 
// CmdListPrereqSignUpWithoutThirdParty.java


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.entity.Country2DAO;

import org.apache.log4j.Logger;

public class CmdListPrereqSignUpWithoutThirdParty implements ICommand {

	static Logger log = Logger.getLogger(CmdListPrereqSignUpWithoutThirdParty.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		Collection<Collection<String>> preRequisteCollnWrapper = new ArrayList<Collection<String>>();
		ICrud country2Dao = new Country2DAO();
		try{
			preRequisteCollnWrapper = country2Dao.getAll();
			view.setCollection(preRequisteCollnWrapper);
			
		} catch(SQLException sqle) {
			log.error("execute: SQLException "+sqle.toString());
			throw sqle;
		} catch(Exception exp) {
			log.error("execute: Exception "+exp.toString());
			throw exp;
		}
		return view;
	}

}
