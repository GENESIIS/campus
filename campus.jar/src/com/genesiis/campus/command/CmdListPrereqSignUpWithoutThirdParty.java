package com.genesiis.campus.command;
//20161205 DN C18-student-signup-without-using-third-party-application-dn created the class 
// CmdListPrereqSignUpWithoutThirdParty.java
//20161206 DN C18-student-signup-without-using-third-party-application-dn refactor method to include switch statement
//20161207 DN C18: modified execute() to include view.setCollection(preRequisteCollnWrapper) call from one place
//20161209 DN C18: code refactor execute() to add usertype logic
//20161214 DN CAMP:18 setTempdata(int USERTYPE) temporary method created to by pass the USERTYPE table data issue
//20161229 DN CAMP18: removed unnecessary imports.
//20170503 DN c88-admin-manage-advertiser-add-new-advertiser-dn. Removed the commented code as per the crev comment. 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.UserType;

public class CmdListPrereqSignUpWithoutThirdParty implements ICommand {

	static Logger log = Logger.getLogger(CmdListPrereqSignUpWithoutThirdParty.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		Collection<Collection<String>> preRequisteCollnWrapper = new ArrayList<Collection<String>>();
		
		try{
			Operation op =Operation.getOperation(helper.getCommandCode()) ;
			switch(op){
				case DISPLAY_PREREQUISITE_DATA:
					ICrud country2Dao = new Country2DAO();
					ICrud userTypeDao = new UserTypeDAO();
					preRequisteCollnWrapper = country2Dao.getAll();
					helper.setAttribute("userType", userTypeDao.findById(UserType.STUDENT.getUserType()));
					break;
				case DISPLAY_TOWN_DATA :
					
					ICrud townDao = new TownDAO();
					preRequisteCollnWrapper = townDao.findById(Integer.parseInt(helper.getParameter("country")));
					
					break;
				default:
					Collection<String> signUpdata = new ArrayList<String>();
					preRequisteCollnWrapper.add(signUpdata);
					
					break;
			}
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