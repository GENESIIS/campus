package com.genesiis.campus.command;

//20161121 CM c36-add-tutor-information INIT CmdAddTutorProfile.java
//20161121 CM c36-add-tutor-information Modified execute()method. 
//20161216 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary variable declarations. 
//20161221 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary code repetitions. 
//20170110 CW c36-add-tutor-details Modified setVariables() method - add tutor crtBy & modBy using setter methods  
//20170110 CW c36-add-tutor-details Modified execute() method - changed the way of calling the findById() method
//20170116 CW c36-add-tutor-details add fillTutorCollection(), fillTutorDummyCollection() methodS to fill a Collection with data

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
			Collection<Collection<String>> tutorCollection= new ArrayList<Collection<String>>();
			
		try {
			String dfd = helper.getParameter("firstName");
			if (helper.getParameter("firstName") != null){
				message = validator.validateTutorFields(helper);
				setVariables(helper,tutor);
				if (message.equalsIgnoreCase("True")) {								
	
					UserTypeDAO typeOfUser = new UserTypeDAO();
					Collection<Collection<String>> userTypeCollection= new ArrayList<Collection<String>>();
					
					fillTutorCollection(tutorCollection, tutor);
					
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
	
					} else {
						
						message = SystemMessage.ERROR.message();
					}
					
				}
			}else{
				fillTutorCollection(tutorCollection, tutor);
			}
		}  catch (SQLException sqle){
			log.error("execute(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
			view.setCollection(tutorCollection);
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
				//tutor.setMiddleName("-");
			} else {
				tutor.setMiddleName(helper.getParameter("middlename"));
			}
			
			tutor.setLastName(helper.getParameter("lastname"));
			tutor.setGender(helper.getParameter("gender"));
			tutor.setEmailAddress(helper.getParameter("email"));
			tutor.setLandCountryCode(helper.getParameter("countryDetails"));
			tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
			tutor.setLandNumber(helper.getParameter("landNumber"));
			tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
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
				//tutor.setAddressLine2("-");
			} else {
				tutor.setAddressLine2(helper.getParameter("address2"));
			}

			if (helper.getParameter("address3").equals("")) {
				//tutor.setAddressLine3("-");
			} else {
				tutor.setAddressLine3(helper.getParameter("address3"));
			}
			
			tutor.setTown(helper.getParameter("townDetails"));
			
			tutor.setCrtBy("chathuri");
			tutor.setModBy("chathuri");

		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
	}
	

	/*
	 * fillTutorDummyCollection() method assign dummy value into a collection
	 * 
	 * @author CW
	 * 
	 * @param tutorCollection, tutor
	 */
	private void fillTutorDummyCollection(Collection<Collection<String>> tutorCollection, Tutor tutor) throws SQLException, Exception{
		ArrayList<String> tutorData = new ArrayList<>();
		
		tutorData.add("");
		tutorData.add("sdf");
		tutorCollection.add(tutorData);
		
	}
	
	/*
	 * fillTutorCollection() method assign all the tutor details into a collection
	 * 
	 * @author CW
	 * 
	 * @param tutorCollection, tutor
	 */
	private void fillTutorCollection(Collection<Collection<String>> tutorCollection, Tutor tutor) throws SQLException, Exception{
		ArrayList<String> tutorData = new ArrayList<>();
		
		tutorData.add(tutor.getFirstName());
		tutorData.add(tutor.getMiddleName());
		tutorData.add(tutor.getLastName());
		tutorData.add(tutor.getGender());
		tutorData.add(tutor.getExperience());
		tutorData.add(tutor.getDescription());
		
		CountryDAO country = new CountryDAO();
		
		try{
			Collection<Collection<String>> countryCollection = new ArrayList<Collection<String>>();
			countryCollection = country.findById(Integer.parseInt(tutor.getMobileCountryCode()));
			
			for(Collection<String> countryList : countryCollection){
				tutorData.add(countryList.toArray()[1].toString());				
			}

		}  catch (SQLException sqle){
			log.error("fillTutorCollection(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("fillTutorCollection() : Exception" + exception.toString());
			throw exception;
		}
		
		TownDAO town = new TownDAO();
		try{
			Collection<Collection<String>> townCollection = new ArrayList<Collection<String>>();
			int x = Integer.parseInt(tutor.getMobileCountryCode());
			System.out.println(x);
			townCollection = town.findById(Integer.parseInt(tutor.getMobileCountryCode()));
			
			for(Collection<String> townList : townCollection){
				if (townList.toArray()[0].toString().equals(tutor.getTown())){
					tutorData.add(townList.toArray()[1].toString());		
				}
			}

		}  catch (SQLException sqle){
			log.error("fillTutorCollection(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("fillTutorCollection() : Exception" + exception.toString());
			throw exception;
		}
		
		
		tutorData.add(tutor.getMobileCountryCode());
		tutorData.add(tutor.getMobileNetworkCode());
		tutorData.add(tutor.getMobileNumber());
		tutorData.add(tutor.getLandCountryCode());
		tutorData.add(tutor.getLandAreaCode());
		tutorData.add(tutor.getLandNumber());
		tutorData.add(tutor.getAddressLine1());
		tutorData.add(tutor.getAddressLine2());
		tutorData.add(tutor.getAddressLine3());
		tutorData.add(tutor.getWebLink());
		tutorData.add(tutor.getFacebookLink());
		tutorData.add(tutor.getLinkedInLink());
		tutorData.add(tutor.getTwitterNumber());
		tutorData.add(tutor.getInstagramId());
		tutorData.add(tutor.getMySpaceId());
		tutorData.add(tutor.getWhatsAppId());
		tutorData.add(tutor.getViberNumber());
		tutorData.add(tutor.getEmailAddress());
		tutorData.add(tutor.getUsername());
		
		tutorCollection.add(tutorData);
		
	}
}
