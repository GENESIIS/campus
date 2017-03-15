package com.genesiis.campus.command;

//20161121 CM c36-add-tutor-information INIT CmdAddTutorProfile.java
//20161121 CM c36-add-tutor-information Modified execute()method. 
//20161216 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary variable declarations. 
//20161221 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary code repetitions. 
//20170110 CW c36-add-tutor-details Modified setVariables() method - add tutor crtBy & modBy using setter methods  
//20170110 CW c36-add-tutor-details Modified execute() method - changed the way of calling the findById() method
//20170116 CW c36-add-tutor-details add fillTutorCollection(), fillTutorDummyCollection() methodS to fill a Collection with data
//20170116 CW c36-add-tutor-information removed fillTutorDummyCollection & modified execute(), fillTutorDummyCollection()
//20170117 CW c36-add-tutor-details removed un-wanted commented lines & clean the code & modified fillTutorCollection() method
//20170124 CW c36-add-tutor-details modified fillTutorCollection() method according to the 201701201215 DJ crev modification request.
//20170125 CW c36-add-tutor-details validateUserAndEmail() & validateAvailability() methods.
//20170126 CW c36-add-tutor-details modified execute() method.
//20170126 CW c36-add-tutor-details modified fillTutorCollection() method & check for null Array Lists
//20170130 Cw c36-add-tutor-details modified validateAvailability() method
//20170130 CW c36-add-tutor-information re-organize the import statements.
//20170131 CW c36-add-tutor-information modify execute() & validateUserAndEmail() methods
//20170202 CW c36-add-tutor-details modified validateAvailability(), validateUserAndEmail() methods
//20170206 CW c36-add-tutor-details modified execute() method.
//20170206 CW c36-add-tutor-details cleaning the code by removing commented lines.
//20170220 CW c36-add-tutor-details modified execute() for INCORRECTDATA message.
//20170221 CW c36-add-tutor-details removed validateUserAndEmail() method.
//20170221 CW c36-add-tutor-details modified execute() method to stop view entered data if successfully added into the database.
//20170222 CW c36-add-tutor-details modified execute() method to to stop message error
//20170228 CW c36-add-tutor-details removed validateAvailability() since it's not using anymore.
//20170228 CW c36-add-tutor-details add validateUsernameEmailFields(), isValidUserAndEmailBeforeAddTutor() method to validate the user entered usename & email availability in the database.
				// modified execute() to call isValidUserAndEmailBeforeAddTutor()
//20170314 CW c36-add-tutor-details modified isValidUserAndEmailBeforeAddTutor & add a call to validateEmail method
//20170315 CW c36-add-tutor-details modified isValidUserAndEmailBeforeAddTutor email validation modified


import com.genesiis.campus.entity.CountryDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;


/**
 * CmdAddTutorProfile class handles the tutor profile saving
 * when an tutor account is created.
 * @author Chinthaka CW
 */
public class CmdAddTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdAddTutorProfile.class.getName());

	/**
	 * @author Chathuri, Chinthaka
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */


	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

			String message = "True";
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			final Validator validator=new Validator();
			Collection<String> tutorCollection= new ArrayList<String>();
			
		try {
				setVariables(helper,tutor);
				fillTutorCollection(tutorCollection, tutor);
											
				if (validator.validateTutorFields(helper) && isValidUserAndEmailBeforeAddTutor(helper)) {													
	
					UserTypeDAO typeOfUser = new UserTypeDAO();					
					Collection<Collection<String>> userTypeCollection = typeOfUser.findById(UserType.TUTOR_ROLE.name());
					
					int userType = 9999;
					
					for(Collection<String> userTypeInnerCollection : userTypeCollection ){
						Object arr[] = userTypeInnerCollection.toArray();
						userType = Integer.parseInt(arr[0].toString());
					} 
					
					if (userType != 9999){
						tutor.setUsertype(userType);
					}
	
					int result = tutorDAO.add(tutor);
					if (result > 0) {
						message = SystemMessage.ADDED.message();	
					} else {						
						message = SystemMessage.ERROR.message();
					}					
				}
				
				if(!(message.equals(SystemMessage.ADDED.message()) || message.equals(SystemMessage.ERROR.message()))){
					message = SystemMessage.INCORRECTDATA.message();
				}
		}  catch (SQLException sqle){
			log.error("execute(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {			
			helper.setAttribute("message", message);
			
			if(!(message.equals(SystemMessage.ADDED.message()))){
				helper.setAttribute("tutorList", tutorCollection);
			}
		}
		return view;
	}	

	/*
	 * setVariables() method initializes all the instance variable
	 * 
	 * @author CM, CW
	 * 
	 * @param helper IDataHelper, Tutor
	 */

	public void setVariables(IDataHelper helper, Tutor tutor) {
		try {
			
			if (helper.getParameter("username").equals("")) {
				tutor.setUsername(" ");
			} else {
				tutor.setUsername(helper.getParameter("username"));
			}

			if (helper.getParameter("password").equals("")) {
				tutor.setPassword(" ");
			} else {
				tutor.setPassword(helper.getParameter("password"));
			}

			if (helper.getParameter("firstname").equals("")) {
				tutor.setFirstName(" ");
			} else {
				tutor.setFirstName(helper.getParameter("firstname"));
			}
			
			if (helper.getParameter("middlename").equals("")) {
				tutor.setMiddleName("-");
			} else {
				tutor.setMiddleName(helper.getParameter("middlename"));
			}

			if (helper.getParameter("lastname").equals("")) {
				tutor.setLastName(" ");
			} else {
				tutor.setLastName(helper.getParameter("lastname"));
			}
			
			tutor.setGender(helper.getParameter("gender"));

			if (helper.getParameter("experience").equals("")) {
				tutor.setExperience("-");
			} else {
				tutor.setExperience(helper.getParameter("experience"));
			}
			
			if (helper.getParameter("aboutMe").equals("")) {
				tutor.setDescription("-");
			} else {
				tutor.setDescription(helper.getParameter("aboutMe"));
			}
			
			if(Validator.isNotEmpty(helper.getParameter("mobileCountryCode"))){
				if(Validator.isNotEmpty(helper.getParameter("countryDetails")) && (!(helper.getParameter("countryDetails").equals("0")))){
					tutor.setLandCountryCode(helper.getParameter("countryDetails"));
					tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
				}
				else{
					tutor.setLandCountryCode(helper.getParameter("mobileCountryCode"));
					tutor.setMobileCountryCode(helper.getParameter("mobileCountryCode"));
				}
					
			}else{
				tutor.setLandCountryCode(helper.getParameter("countryDetails"));
				tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
			}
			
			if (helper.getParameter("landAreaCode").equals("")) {
				tutor.setLandAreaCode(" ");
			} else {
				tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
			}

			if (helper.getParameter("landNumber").equals("")) {
				tutor.setLandNumber(" ");
			} else {
				tutor.setLandNumber(helper.getParameter("landNumber"));
			}			

			if (helper.getParameter("mobileNetworkCode").equals("")) {
				tutor.setMobileNetworkCode(" ");
			} else {
				tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));
			}

			if (helper.getParameter("mobileNumber").equals("")) {
				tutor.setMobileNumber(" ");
			} else {
				tutor.setMobileNumber(helper.getParameter("mobileNumber"));
			}

			if (helper.getParameter("address1").equals("")) {
				tutor.setAddressLine1(" ");
			} else {
				tutor.setAddressLine1(helper.getParameter("address1"));
			}
						
			if (helper.getParameter("address2").equals("")) {
				tutor.setAddressLine2("-");
			} else {
				tutor.setAddressLine2(helper.getParameter("address2"));
			}

			if (helper.getParameter("address3").equals("")) {
				tutor.setAddressLine3("-");
			} else {
				tutor.setAddressLine3(helper.getParameter("address3"));
			}
									
			if (helper.getParameter("weblink").equals("")) {
				tutor.setWebLink("-");
			} else {
				tutor.setWebLink(helper.getParameter("weblink"));
			}

			if (helper.getParameter("facebook").equals("")) {
				tutor.setFacebookLink("-");
			} else {
				tutor.setFacebookLink(helper.getParameter("facebook"));
			}

			if (helper.getParameter("linkedin").equals("")) {
				tutor.setLinkedInLink("-");
			} else {
				tutor.setLinkedInLink(helper.getParameter("linkedin"));
			}

			if (helper.getParameter("twitter").equals("")) {
				tutor.setTwitterNumber("-");
			} else {
				tutor.setTwitterNumber(helper.getParameter("twitter"));
			}

			if (helper.getParameter("instagram").equals("")) {
				tutor.setInstagramId("-");
			} else {
				tutor.setInstagramId(helper.getParameter("instagram"));
			}

			if (helper.getParameter("myspace").equals("")) {
				tutor.setMySpaceId("-");
			} else {
				tutor.setMySpaceId(helper.getParameter("myspace"));
			}

			if (helper.getParameter("whatsapp").equals("")) {
				tutor.setWhatsAppId("0");
			} else {
				tutor.setWhatsAppId(helper.getParameter("whatsapp"));
			}
			
			if (helper.getParameter("viber").equals("")) {
				tutor.setViberNumber("0");
			} else {
				tutor.setViberNumber(helper.getParameter("viber"));
			}			
			
			tutor.setEmailAddress(helper.getParameter("email"));

			tutor.setIsApproved(false);
			
			tutor.setTutorStatus(ApplicationStatus.PENDING.getStatusValue());
			
			if(Validator.isNotEmpty(helper.getParameter("townHidden"))){
				if((Validator.isNotEmpty(helper.getParameter("townDetails"))) && (!(helper.getParameter("townDetails").equals("0")))){
					tutor.setTown(helper.getParameter("townDetails"));
				}
				else{
					tutor.setTown(helper.getParameter("townHidden"));
				}
					
			}else{
				tutor.setTown(helper.getParameter("townDetails"));
			}
									
			tutor.setCrtBy("chathuri");
			tutor.setModBy("chathuri");

		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
	}
	
	
	/*
	 * fillTutorCollection() method assign all the tutor details into a collection
	 * 
	 * @author CW
	 * 
	 * @param tutorCollection, tutor
	 */
	private void fillTutorCollection(Collection<String> tutorCollection, Tutor tutor) throws SQLException, Exception{
		
		tutorCollection.add(tutor.getFirstName());
		tutorCollection.add(tutor.getMiddleName());
		tutorCollection.add(tutor.getLastName());
		tutorCollection.add(tutor.getGender());
		tutorCollection.add(tutor.getExperience());
		tutorCollection.add(tutor.getDescription());
		
		CountryDAO country = new CountryDAO();
		TownDAO town = new TownDAO();
		
		try{

			int countryAddCount = 0, townAddCount = 0;
			if(tutor.getMobileCountryCode() != " "){
				Collection<Collection<String>> countryCollection = country.findById(Integer.parseInt(tutor.getMobileCountryCode()));
				Collection<Collection<String>> townCollection = town.findById(Integer.parseInt(tutor.getMobileCountryCode()));
				
				if(!(countryCollection.isEmpty())){
					for(Collection<String> countryList : countryCollection){
						tutorCollection.add(countryList.toArray()[1].toString());	
						countryAddCount++;			
					}
	
					for(Collection<String> townList : townCollection){
						if (townList.toArray()[0].toString().equals(tutor.getTown())){
							tutorCollection.add(townList.toArray()[1].toString());
							tutorCollection.add(townList.toArray()[0].toString());
							townAddCount++;
						}
					}
				}
			}
			if(countryAddCount == 0){
				tutorCollection.add(" ");
			}
			
			if(townAddCount == 0){
				tutorCollection.add(" ");
				tutorCollection.add(" ");
			}

		}  catch (SQLException sqle){
			log.error("fillTutorCollection(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("fillTutorCollection() : Exception" + exception.toString());
			throw exception;
		}

		tutorCollection.add(tutor.getMobileCountryCode());
		tutorCollection.add(tutor.getMobileNetworkCode());
		tutorCollection.add(tutor.getMobileNumber());
		tutorCollection.add(tutor.getLandCountryCode());
		tutorCollection.add(tutor.getLandAreaCode());
		tutorCollection.add(tutor.getLandNumber());
		tutorCollection.add(tutor.getAddressLine1());
		tutorCollection.add(tutor.getAddressLine2());
		tutorCollection.add(tutor.getAddressLine3());
		tutorCollection.add(tutor.getWebLink());
		tutorCollection.add(tutor.getFacebookLink());
		tutorCollection.add(tutor.getLinkedInLink());
		tutorCollection.add(tutor.getTwitterNumber());
		tutorCollection.add(tutor.getInstagramId());
		tutorCollection.add(tutor.getMySpaceId());
		tutorCollection.add(tutor.getWhatsAppId());
		tutorCollection.add(tutor.getViberNumber());
		tutorCollection.add(tutor.getEmailAddress());
		tutorCollection.add(tutor.getUsername());
		
	}	

	/**
	 * Validate Tutor username & email given before save tutor details to database. 
	 * @author Chinthaka
	 * @param helper
	 * @return boolean : return false if user name or email is having an error
	 * @throws Exception
	 */
	public boolean isValidUserAndEmailBeforeAddTutor(IDataHelper helper) throws SQLException, Exception{

		boolean valid = true; 
		int type = 0;
		try {		
			
			if(Validator.isEmptyOrHavingSpace(helper.getParameter("username"))){
				helper.setAttribute("usernameError", SystemMessage.EMPTYUSERNAME.message());
				valid = false;
			}

			if(Validator.isEmptyOrHavingSpace(helper.getParameter("email"))){
				helper.setAttribute("emailError", SystemMessage.EMPTYEMAIL.message());
				valid = false;
			}
			
			if (!Validator.isValidUserNameLength(helper.getParameter("username"))) {
				helper.setAttribute("usernameError", SystemMessage.USERNAME_LENGTH.message());
				valid = false;
			} 
			
			if(!Validator.validateEmail(helper.getParameter("email"))){
				helper.setAttribute("emailError", SystemMessage.EMAILERROR.message());
				valid = false;
			}
	
			type = validateUsernameEmailFields(helper.getParameter("username"), helper.getParameter("email"));
		
			if(type == 1){
				helper.setAttribute("usernameError", SystemMessage.USERNAME_EXIST.message());
				helper.setAttribute("emailError", SystemMessage.EMAIL_USED.message());
				valid = false;
			} 
			
			if(type == 2){
				helper.setAttribute("usernameError", SystemMessage.USERNAME_EXIST.message());
				valid = false;
			} 
			
			if(type == 3){
				helper.setAttribute("emailError", SystemMessage.EMAIL_USED.message());
				valid = false;
			}
			
		} catch (SQLException sqlException) {
			log.info("isValidUserAndEmailBeforeAddTutor(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("isValidUserAndEmailBeforeAddTutor(): Exception " + e.toString());
			throw e;
		} 
		return valid;
	}
	
	/**
	 * Check the email & username given with already entered username & email in the database 
	 * @author Chinthaka 
	 * @return Returns 1 if both username & email are available in the database, returns 1 if the username is available in the database, 
	 * 				returns 2 if the email is available & returns 0 if both are not used to create a tutor profile.
	 */
	public static int validateUsernameEmailFields(String username, String email) throws SQLException, Exception {		

		Collection<Collection<String>> allUsernameEmailList = new ArrayList<Collection<String>>();
		int validStatus = 0;
		
		try {
			allUsernameEmailList = TutorDAO.getListOfUsernameEmail(username, email);

			final TreeSet<Integer> treeOfData = new TreeSet<Integer>();
			
			for (Collection<String>usernameEmailList : allUsernameEmailList){
				if(usernameEmailList.toArray()[0].toString().equals(username) && usernameEmailList.toArray()[1].toString().equals(email)){
					treeOfData.add(1);
				}else if(usernameEmailList.toArray()[0].toString().equals(username)){
					treeOfData.add(2);
				}else if(usernameEmailList.toArray()[1].toString().equals(email)){
					treeOfData.add(3);
				}
			}

			if(treeOfData.contains(1) || (treeOfData.contains(2) && treeOfData.contains(3))){
				validStatus = 1;
			}else{
				if(treeOfData.contains(2) && !treeOfData.contains(3)){
					validStatus = 2;
				}
				if(!treeOfData.contains(2) && treeOfData.contains(3)){
					validStatus = 3;
				}
			}
			
		} catch (SQLException sqlException) {
			log.info("validateUsernameEmailFields(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("validateUsernameEmailFields(): Exception " + e.toString());
			throw e;
		}
		return validStatus;
	}
}
