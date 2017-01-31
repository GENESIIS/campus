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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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

	private String message = "True";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			final Validator validator=new Validator();
			Collection<String> tutorCollection= new ArrayList<String>();
			
		try {
				setVariables(helper,tutor);
				fillTutorCollection(tutorCollection, tutor);
				
				message = validateUserAndEmail(helper);
				message = validator.validateTutorFields(helper);
				
				if (message.equalsIgnoreCase("True")) {													
	
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
		}  catch (SQLException sqle){
			log.error("execute(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			if(message.equalsIgnoreCase("false")){
				message = "Something wrong in the data you have entered...";
			}			
			
			helper.setAttribute("message", message);
			helper.setAttribute("tutorList", tutorCollection);
		}
		return view;
	}	
	
	/**
	 * Validate Tutor username & email given. 
	 * @author Chinthaka
	 * @param helper
	 * @return String
	 * @throws Exception
	 */
	public String validateUserAndEmail(IDataHelper helper) throws SQLException, Exception{

		String message = "True"; 
		try {		

			if (!(Validator.isNotEmpty(helper.getParameter("username")))){
				helper.setAttribute("usernameError", SystemMessage.EMPTYUSERNAME.message());
				message = "False";
			}
			
			if (!(Validator.isNotEmpty(helper.getParameter("email")))){
				helper.setAttribute("emailError", SystemMessage.EMPTYEMAIL.message());
				message = "False";
			}
			
			if (!Validator.isValidUserNameLength(helper.getParameter("username"))) {
				helper.setAttribute("usernameError", SystemMessage.USERNAME_LENGTH.message());
				message = "False";
			} 
			
		} catch (SQLException sqlException) {
			log.info("validateUserAndEmail(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("validateUserAndEmail(): Exception " + e.toString());
			throw e;
		} 
		return message;
	}
	
	/**
	 * Validate Tutor username & email for availability.
	 * @author Chinthaka
	 * @param helper
	 * @return String
	 * @throws Exception
	 */
	public String validateAvailability(IDataHelper helper) throws SQLException, Exception {

		String message = "True"; 
		int type = 0;
		try {		
				type = TutorDAO.validateUsernameEmailFields(helper.getParameter("username"), helper.getParameter("email"));
				
				if(type == 1){
					helper.setAttribute("usernameError", SystemMessage.USERNAME_EXIST.message());
					//message = SystemMessage.USERNAME_EXIST.message();
				} else if(type == 2){
					helper.setAttribute("emailError", SystemMessage.EMAIL_USED.message());
					//message = SystemMessage.EMAIL_USED.message();
				}
		} catch (SQLException sqlException) {
			log.info("validateAvailability(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("validateAvailability(): Exception " + e.toString());
			throw e;
		} 
		return message;
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
			tutor.setUsername(helper.getParameter("username"));
			tutor.setPassword(helper.getParameter("password"));
			tutor.setFirstName(helper.getParameter("firstname"));
			
			if (helper.getParameter("middlename").equals("")) {
				tutor.setMiddleName("-");
			} else {
				tutor.setMiddleName(helper.getParameter("middlename"));
			}
			
			tutor.setLastName(helper.getParameter("lastname"));
			tutor.setGender(helper.getParameter("gender"));
			tutor.setEmailAddress(helper.getParameter("email"));
			
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
			
			tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
			tutor.setLandNumber(helper.getParameter("landNumber"));
			tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));
			tutor.setMobileNumber(helper.getParameter("mobileNumber"));
			
			if (helper.getParameter("aboutMe").equals("")) {
				tutor.setDescription("-");
			} else {
				tutor.setDescription(helper.getParameter("aboutMe"));
			}

			if (helper.getParameter("experience").equals("")) {
				tutor.setExperience("-");
			} else {
				tutor.setExperience(helper.getParameter("experience"));
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

			if (helper.getParameter("twitter").equals("")) {
				tutor.setTwitterNumber("-");
			} else {
				tutor.setTwitterNumber(helper.getParameter("twitter"));
			}

			if (helper.getParameter("myspace").equals("")) {
				tutor.setMySpaceId("-");
			} else {
				tutor.setMySpaceId(helper.getParameter("myspace"));
			}

			if (helper.getParameter("linkedin").equals("")) {
				tutor.setLinkedInLink("-");
			} else {
				tutor.setLinkedInLink(helper.getParameter("linkedin"));
			}

			if (helper.getParameter("instagram").equals("")) {
				tutor.setInstagramId("-");
			} else {
				tutor.setInstagramId(helper.getParameter("instagram"));
			}
			
			if (helper.getParameter("viber").equals("")) {
				tutor.setViberNumber("0");
			} else {
				tutor.setViberNumber(helper.getParameter("viber"));
			}

			if (helper.getParameter("whatsapp").equals("")) {
				tutor.setWhatsAppId("0");
			} else {
				tutor.setWhatsAppId(helper.getParameter("whatsapp"));
			}

			tutor.setIsApproved(false);
			
			tutor.setTutorStatus(ApplicationStatus.PENDING.getStatusValue());
			
			tutor.setAddressLine1(helper.getParameter("address1"));
			
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
			Collection<Collection<String>> countryCollection = country.findById(Integer.parseInt(tutor.getMobileCountryCode()));
			if(!(countryCollection.isEmpty())){
				for(Collection<String> countryList : countryCollection){
					tutorCollection.add(countryList.toArray()[1].toString());				
				}
			}

			int addCount = 0;
			Collection<Collection<String>> townCollection = town.findById(Integer.parseInt(tutor.getMobileCountryCode()));
			
			if(!(countryCollection.isEmpty())){
				for(Collection<String> townList : townCollection){
					if (townList.toArray()[0].toString().equals(tutor.getTown())){
						tutorCollection.add(townList.toArray()[1].toString());
						tutorCollection.add(townList.toArray()[0].toString());
						addCount++;
					}
				}
			}
			if(addCount == 0){
				tutorCollection.add("0");
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
}
