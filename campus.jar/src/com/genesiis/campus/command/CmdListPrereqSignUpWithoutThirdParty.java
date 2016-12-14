package com.genesiis.campus.command;
//20161205 DN C18-student-signup-without-using-third-party-application-dn created the class 
// CmdListPrereqSignUpWithoutThirdParty.java
//20161206 DN C18-student-signup-without-using-third-party-application-dn refactor method to include switch statement
//20161207 DN C18: modified execute() to include view.setCollection(preRequisteCollnWrapper) call from one place
//20161209 DN C18: code refactor execute() to add usertype logic
//20161214 DN CAMP:18 setTempdata(int USERTYPE) temporary method created to by pass the USERTYPE table data issue

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.RowStudentForJason;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.entity.Country2DAO;
import com.genesiis.campus.entity.model.Town;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

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
					//helper.setAttribute("userType", userTypeDao.findById(UserType.STUDENT.getUserType()));
					helper.setAttribute("userType",setTempdata(3));
					break;
				case DISPLAY_TOWN_DATA :
					
					ICrud townDao = new TownDAO();
					preRequisteCollnWrapper = townDao.findById(Integer.parseInt(helper.getParameter("country")));
					
					break;
				default:
					Collection<String> signUpdata = new ArrayList<String>();
					signUpdata.add(null);
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
	
	
	
//	/**
//	 * extractDumyObjectFrom helps extract the json data to a
//	 * row object with the same field name similar to the fields json data 
//	 * contains
//	 * @param gsonData
//	 * @param infaltingtype Class<?> the class type that the gson data to be inflated in to
//	 * @return
//	 */
//
//	public Object extractDumyObjectFrom(String gsonData,Class<?> infaltingtype) {
//		Gson gson = new Gson();
//		Object rowObj = null;
//		try {
//			rowObj = gson.fromJson(gsonData,
//					infaltingtype);
//
//		} catch (Exception exception) {
//			log.error("extractDumyObjectFrom(): " + exception.toString());
//			throw exception;
//		}
//		return rowObj;
//	}
	
	
	
	private Collection<Collection<String>> setTempdata(int USERTYPE){
		Collection<Collection<String>> allTownList = new ArrayList<Collection<String>>();
		Collection<String> inner = new ArrayList<String>();
		inner.add(String.valueOf(USERTYPE));
		inner.add(String.valueOf(4));
		allTownList.add(inner);
		return allTownList;
		
	}
	

}
