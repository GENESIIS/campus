package com.genesiis.campus.command;

//20161121 CM c36-add-tutor-information INIT CmdAddTutorProfile.java
//20161121 CM c36-add-tutor-information Modified execute()method. 
//20161216 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary variable declarations. 
//20161221 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary code repetitions. 
//20170105 CW c98-send-email-at-tutor-signup Modified execute() add email sending at tutor signup
//20170110 CW c36-add-tutor-details Modified setVariables() method - add tutor crtBy & modBy using setter methods  
//20170110 CW c36-add-tutor-details Modified execute() method - changed the way of calling the findById() method
//20170116 CW c36-add-tutor-details add fillTutorCollection(), fillTutorDummyCollection() methodS to fill a Collection with data
//20170116 CW c36-add-tutor-information removed fillTutorDummyCollection & modified execute(), fillTutorDummyCollection()
//20170117 CW c36-add-tutor-details removed un-wanted commented lines & clean the code & modified fillTutorCollection() method
//20170123 CW c125-un-formatted-email-sending-tutor-signup-removing un-wanted commented lines
//20170124 CW c36-add-tutor-details modified fillTutorCollection() method according to the 201701201215 DJ crev modification request.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

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
				message = validator.validateTutorFields(helper);
				setVariables(helper,tutor);
				fillTutorCollection(tutorCollection, tutor);
				
				if (message.equalsIgnoreCase("True")) {								
	
					UserTypeDAO typeOfUser = new UserTypeDAO();
					Collection<Collection<String>> userTypeCollection= new ArrayList<Collection<String>>();					
					
					userTypeCollection = typeOfUser.findById(UserType.TUTOR_ROLE.name());
					
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
						ICommand emailSignUp = new CmdGenerateEmailTutorSignUp();
						emailSignUp.execute(helper, view); //send email
					} else {
						
						message = SystemMessage.ERROR.message();
					}
				
			}
		}  catch (SQLException sqle){
			log.error("execute(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
			helper.setAttribute("tutorList", tutorCollection);
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
			Collection<Collection<String>> countryCollection = new ArrayList<Collection<String>>();
			countryCollection = country.findById(Integer.parseInt(tutor.getMobileCountryCode()));
			for(Collection<String> countryList : countryCollection){
				tutorCollection.add(countryList.toArray()[1].toString());				
			}

			Collection<Collection<String>> townCollection = new ArrayList<Collection<String>>();
			int addCount = 0;
			townCollection = town.findById(Integer.parseInt(tutor.getMobileCountryCode()));
			
			for(Collection<String> townList : townCollection){
				if (townList.toArray()[0].toString().equals(tutor.getTown())){
					tutorCollection.add(townList.toArray()[1].toString());
					tutorCollection.add(townList.toArray()[0].toString());
					addCount++;
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
