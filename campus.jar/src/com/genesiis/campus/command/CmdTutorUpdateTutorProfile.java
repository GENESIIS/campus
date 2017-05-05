package com.genesiis.campus.command;

//20170227 CW c37-tutor-update-tutor-profile-cw INIT CmdTutorUpdateTutorProfile.java
//20170227 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables method to add validations for empty values
//20170228 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables method to work with password correctly
//20170228 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables method to fix password errors
//20170301 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables to fix some errors caused by password
//20170301 CW c37-tutor-update-tutor-profile-cw modified passwordOld to passwordFromDb
//20170301 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables password validations
//20170301 CW c37-tutor-update-tutor-profile-cw modified execute method & moved message declaration inside the method, modified a message & modified setCompareVariables method to add null value check for the mandatory fields
//20170301 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables method & add password encryption to new password & removed un required commented lines 
//20170302 CW c37-tutor-update-tutor-profile-cw modified fillTutorCollection method to use Validator.isEmptyOrHavingSpace()
//20170305 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables method & add testing messages until password errors fixed
//20170306 CW c37-tutor-update-tutor-profile-cw modified setCompareVariables removed testing messages.
//20170306 CW c37-tutor-update-tutor-profile-cw modified execute method & change variable declarations to optimize the code
//20170306 CW c37-tutor-update-tutor-profile-cw add isValidUserAndEmailBeforeAddTutor() & validateUsernameEmailFields() methods from c36-add-tutor-information
				// Modified some comments
//20170307 CW c37-tutor-update-tutor-profile-cw modified execute method comment, set tutor.setIsApproved & tutor.setTutorStatus values before update
				//modified isValidUserAndEmailBeforeAddTutor() method to validate email if email field was updated.
				//changed method setCompareVariables name into setCompareVariablesBeforeTutorUpdateTutor.
//20170406 CW c37-tutor-update-tutor-profile-cw modified setCompareVariablesBeforeTutorUpdateTutor method & add validations for newPassword
//20170407 CW c37-tutor-update-tutor-profile-cw modified fillTutorCollection method & remove filling password into tutorCollection
//20170407 CW c37-tutor-update-tutor-profile-cw trim the password fields in  setCompareVariablesBeforeTutorUpdateTutor method
//20170407 CW c37-tutor-update-tutor-profile-cw remove un wanted log messages from execute, setCompareVariablesBeforeTutorUpdateTutor methods.
				//modified log messages from isValidUserAndEmailBeforeAddTutor, validateUsernameEmailFields methods
//20170505 CW c37-tutor-update-tutor-profile-cw modify the way updated variable initialized, assign SystemMessage.INCORRECTDATA.message() to the message 
				//variable initially & modify the code accordingly, removed some un required empty checks in setCompareVariablesBeforeTutorUpdateTutor method

import com.genesiis.campus.entity.CountryDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TownDAO;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
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
 * this class manages all the tutor details update done by the tutor himself, 
 * further it implements ICommand interface
 * @author CW
 *
 */
public class CmdTutorUpdateTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdTutorUpdateTutorProfile.class.getName());
	
	/**
	 * @author Chinthaka
	 * @param helepr IDataHelper object of Object type 
	 * @param view IView object of object type
	 * @return View object to servlet
	 */

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = SystemMessage.INCORRECTDATA.message();

		try {
			final Validator validator = new Validator();
				
			final Tutor tutor = new Tutor();	
			Collection<Collection<String>> tutorViewCollection = null;
			boolean updated = setCompareVariablesBeforeTutorUpdateTutor(helper,tutor); // returns true if updated
			
			if(updated){
				if (validator.validateTutorFields(helper) && isValidUserAndEmailBeforeAddTutor(helper)) {	
					
					final TutorDAO tutorDAO = new TutorDAO();
					int result = 0;

					UserTypeDAO typeOfUser = new UserTypeDAO();	
					tutor.setUsertype(typeOfUser.getCode(UserType.TUTOR_ROLE.name()));
					
					tutor.setIsApproved(false);	
					tutor.setTutorStatus(ApplicationStatus.PENDING.getStatusValue());
					
					result = tutorDAO.update(tutor);	
					
					if (result > 0) {
						tutorViewCollection = tutorDAO.findById(tutor);
						message = SystemMessage.UPDATED.message();
					}
				}else{
					tutorViewCollection = new ArrayList<Collection<String>>();
					fillTutorCollection(tutorViewCollection, tutor);
				}
			}else{
				tutorViewCollection = new ArrayList<Collection<String>>();
				fillTutorCollection(tutorViewCollection, tutor);
				message = SystemMessage.NOMODIFICATIONS.message();
			}
			
			view.setCollection(tutorViewCollection);
			
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
		}
		return view;
	}

	/**
	 * setCompareVariablesBeforeTutorUpdateTutor() method initialises all the instance variable	 * 
	 * @author CM, CW	 * 
	 * @param helper IDataHelper, Tutor
	 * @return Returns true if updated
	 */

	public boolean setCompareVariablesBeforeTutorUpdateTutor(IDataHelper helper, Tutor tutor) throws Exception {
			boolean updated = false;
		try {
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("tutorCode")))){
				tutor.setCode(Integer.parseInt(helper.getParameter("tutorCode").toString()));
			}
			tutor.setUsername(helper.getParameter("usernameOld"));

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("oldPassword")))){
				// need to change the password
				if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("newPassword")))){
					
					Encryptable passwordEncryptor = new TripleDesEncryptor((helper.getParameter("newPassword")).trim());					
					String encryptedNewPassword = passwordEncryptor.encryptSensitiveDataToString().trim();					
					tutor.setPassword(encryptedNewPassword);
					updated = true;				
				}
			}else{
				// no need to change the password
				tutor.setPassword("");
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("firstname")))){
				if(!((helper.getParameter("firstname")).equals(helper.getParameter("firstnameOld").toString()))){				
					tutor.setFirstName(helper.getParameter("firstname"));
					updated = true;
				}else{
					tutor.setFirstName(helper.getParameter("firstnameOld").toString());
				}
			}else{
				updated = true;
			}
			
 			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("middlename")))){
				if(!((helper.getParameter("middlename")).equals(helper.getParameter("middlenameOld").toString()))){	
					tutor.setMiddleName(helper.getParameter("middlename"));	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getParameter("middlenameOld").toString());	
				}
			}else{
				tutor.setMiddleName("-");
				if(Validator.isNotEmpty(helper.getParameter("middlenameOld").toString())){
					updated = true;
				}
			}
			
 			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("lastname")))){
				if(!((helper.getParameter("lastname")).equals(helper.getParameter("lastnameOld").toString()))){	
					tutor.setLastName(helper.getParameter("lastname"));			
					updated = true;
				}else{
					tutor.setLastName(helper.getParameter("lastnameOld").toString());
				}
			}else{
				updated = true;
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("gender"))) && !((helper.getParameter("gender")).equals(helper.getParameter("genderOld").toString()))){		
				tutor.setGender(helper.getParameter("gender"));
				updated = true;
			}else{		
				tutor.setGender(helper.getParameter("genderOld").toString());
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("email")))){
				if(!((helper.getParameter("email")).equals(helper.getParameter("emailOld").toString()))){				
					tutor.setEmailAddress(helper.getParameter("email"));
					updated = true;
				}else{
					tutor.setEmailAddress(helper.getParameter("emailOld").toString());
				}			
			}else{
				updated = true;
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("countryDetails"))) && Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(helper.getParameter("landphonecountrycodeOld").toString()))){	
					tutor.setLandCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setLandCountryCode(helper.getParameter("landphonecountrycodeOld").toString());
				}
			}else{
				tutor.setLandCountryCode(helper.getParameter("landphonecountrycodeOld").toString());
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("landAreaCode")))){
				if(!((helper.getParameter("landAreaCode")).equals(helper.getParameter("landphoneareacodeOld").toString()))){	
					tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
					updated = true;
				}else{			
					tutor.setLandAreaCode(helper.getParameter("landphoneareacodeOld").toString());
				}		
			}else{
				updated = true;
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("landNumber")))){
				if(!((helper.getParameter("landNumber")).equals(helper.getParameter("landphonenumberOld").toString()))){	
					tutor.setLandNumber(helper.getParameter("landNumber"));
					updated = true;
				}else{			
					tutor.setLandNumber(helper.getParameter("landphonenumberOld").toString());
				}	
			}else{
				updated = true;
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("countryDetails"))) && Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(helper.getParameter("mobilephonecountrycodeOld").toString()))){	
					tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setMobileCountryCode(helper.getParameter("mobilephonecountrycodeOld").toString());
				}
			}else{
				tutor.setMobileCountryCode(helper.getParameter("mobilephonecountrycodeOld").toString());
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("mobileNetworkCode")))){
				if(!((helper.getParameter("mobileNetworkCode")).equals(helper.getParameter("mobilephonenetworkcodeOld").toString()))){
					tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));		
					updated = true;
				}else{		
					tutor.setMobileNetworkCode(helper.getParameter("mobilephonenetworkcodeOld").toString());
				}
			}else{
				updated = true;
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("mobileNumber")))){
				if(!((helper.getParameter("mobileNumber")).equals(helper.getParameter("mobilephonenumberOld").toString()))){	
					tutor.setMobileNumber(helper.getParameter("mobileNumber"));			
					updated = true;
				}else{
					tutor.setMobileNumber(helper.getParameter("mobilephonenumberOld").toString());
				}
			}else{
				updated = true;
			}				
						
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("aboutMe")))){
				if(!((helper.getParameter("aboutMe")).equals(helper.getParameter("descriptionOld").toString()))){	
					tutor.setDescription(helper.getParameter("aboutMe"));	
					updated = true;		
				}else{
					tutor.setDescription(helper.getParameter("descriptionOld").toString());	
				}
			}else{
				tutor.setDescription("-");
				if(Validator.isNotEmpty(helper.getParameter("descriptionOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("experience")))){
				if(!((helper.getParameter("experience")).equals(helper.getParameter("experienceOld").toString()))){	
					tutor.setExperience(helper.getParameter("experience"));	
					updated = true;		
				}else{
					tutor.setExperience(helper.getParameter("experienceOld").toString());	
				}
			}else{
				tutor.setExperience("-");
				if(Validator.isNotEmpty(helper.getParameter("experienceOld").toString())){
					updated = true;
				}
			}			

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("weblink")))){
				if(!((helper.getParameter("weblink")).equals(helper.getParameter("weblinkOld").toString()))){	
					tutor.setWebLink(helper.getParameter("weblink"));	
					updated = true;		
				}else{
					tutor.setWebLink(helper.getParameter("weblinkOld").toString());	
				}
			}else{
				tutor.setWebLink("-");
				if(Validator.isNotEmpty(helper.getParameter("weblinkOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("facebook")))){
				if(!((helper.getParameter("facebook")).equals(helper.getParameter("facebookurlOld").toString()))){	
					tutor.setFacebookLink(helper.getParameter("facebook"));	
					updated = true;		
				}else{
					tutor.setFacebookLink(helper.getParameter("facebookurlOld").toString());	
				}
			}else{
				tutor.setFacebookLink("-");
				if(Validator.isNotEmpty(helper.getParameter("facebookurlOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("twitter")))){
				if(!((helper.getParameter("twitter")).equals(helper.getParameter("twitterurlOld").toString()))){	
					tutor.setTwitterNumber(helper.getParameter("twitter"));	
					updated = true;		
				}else{
					tutor.setTwitterNumber(helper.getParameter("twitterurlOld").toString());	
				}
			}else{
					tutor.setTwitterNumber("-");
				if(Validator.isNotEmpty(helper.getParameter("twitterurlOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("myspace")))){
				if(!((helper.getParameter("myspace")).equals(helper.getParameter("myspaceurlOld").toString()))){	
					tutor.setMySpaceId(helper.getParameter("myspace"));	
					updated = true;		
				}else{
					tutor.setMySpaceId(helper.getParameter("myspaceurlOld").toString());	
				}
			}else{
				tutor.setMySpaceId("-");
				if(Validator.isNotEmpty(helper.getParameter("myspaceurlOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("linkedin")))){
				if(!((helper.getParameter("linkedin")).equals(helper.getParameter("linkedinurlOld").toString()))){	
					tutor.setLinkedInLink(helper.getParameter("linkedin"));	
					updated = true;		
				}else{
					tutor.setLinkedInLink(helper.getParameter("linkedinurlOld").toString());	
				}
			}else{
				tutor.setLinkedInLink("-");
				if(Validator.isNotEmpty(helper.getParameter("linkedinurlOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("instagram")))){
				if(!((helper.getParameter("instagram")).equals(helper.getParameter("instagramurlOld").toString()))){	
					tutor.setInstagramId(helper.getParameter("instagram"));	
					updated = true;		
				}else{
					tutor.setInstagramId(helper.getParameter("instagramurlOld").toString());	
				}
			}else{
				tutor.setInstagramId("-");
				if(Validator.isNotEmpty(helper.getParameter("instagramurlOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("viber")))){
				if(!((helper.getParameter("viber")).equals(helper.getParameter("vibernumberOld").toString()))){	
					tutor.setViberNumber(helper.getParameter("viber"));	
					updated = true;		
				}else{
					tutor.setViberNumber(helper.getParameter("vibernumberOld").toString());	
				}
			}else{
					tutor.setViberNumber("-");
				if(Validator.isNotEmpty(helper.getParameter("vibernumberOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("whatsapp")))){
				if(!((helper.getParameter("whatsapp")).equals(helper.getParameter("whatsappnumberOld").toString()))){	
					tutor.setWhatsAppId(helper.getParameter("whatsapp"));	
					updated = true;		
				}else{
					tutor.setWhatsAppId(helper.getParameter("whatsappnumberOld").toString());	
				}
			}else{
					tutor.setWhatsAppId("-");
				if(Validator.isNotEmpty(helper.getParameter("whatsappnumberOld").toString())){
					updated = true;
				}
			}

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("address1")))){
				if(!((helper.getParameter("address1")).equals(helper.getParameter("address1Old").toString()))){				
					tutor.setAddressLine1(helper.getParameter("address1"));
					updated = true;
				}else{
					tutor.setAddressLine1(helper.getParameter("address1Old").toString());
				}
			}else{
				updated = true;
			}
			

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("address2")))){
				if(!((helper.getParameter("address2")).equals(helper.getParameter("address2Old").toString()))){	
					tutor.setAddressLine2(helper.getParameter("address2"));	
					updated = true;		
				}else{
					tutor.setAddressLine2(helper.getParameter("address2Old").toString());	
				}
			}else{
				tutor.setAddressLine2("-");
				if(Validator.isNotEmpty(helper.getParameter("address2Old").toString())){
					updated = true;
				}
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("address3")))){
				if(!((helper.getParameter("address3")).equals(helper.getParameter("address3Old").toString()))){	
					tutor.setAddressLine3(helper.getParameter("address3"));	
					updated = true;		
				}else{
					tutor.setAddressLine3(helper.getParameter("address3Old").toString());	
				}
			}else{
				tutor.setAddressLine3("-");
				if(Validator.isNotEmpty(helper.getParameter("address3Old").toString())){
					updated = true;
				}
			}
			
				tutor.setIsApproved(true);			

			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("townDetails")))){
				if(Double.parseDouble(helper.getParameter("townDetails")) != 0){
					if(!((helper.getParameter("townDetails")).equals(helper.getParameter("towncodeOld").toString()))){	
						tutor.setTown(helper.getParameter("townDetails"));
						updated = true;
					}else{
						tutor.setTown(helper.getParameter("towncodeOld").toString());
					}
				}else{
					tutor.setTown(helper.getParameter("towncodeOld").toString());
				}
			}else{
				updated = true;
			}
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("newtutorStatus"))) && 
					!(Integer.parseInt(helper.getParameter("newtutorStatus")) == (Integer.parseInt(helper.getParameter("tutorstatusOld").toString())))){	
				tutor.setTutorStatus(Integer.parseInt(helper.getParameter("newtutorStatus")));
				updated = true;
			}else{
				tutor.setTutorStatus(Integer.parseInt(helper.getParameter("tutorstatusOld").toString()));
			}
			
			if(updated){
				tutor.setModBy("chinthaka");
			}
			
		} catch (Exception e) {
			log.error("setCompareVariablesBeforeTutorUpdateTutor() : Exception" + e.toString());
			throw e;
		}
		
		return updated;
	}
	
	/**
	 * fillTutorCollection() method assign all the tutor details into a collection
	 * 
	 * @author CW
	 * 
	 * @param tutorCollection, tutor
	 */
	private void fillTutorCollection(Collection<Collection<String>> tutorViewCollection, Tutor tutor) throws SQLException, Exception{

		Collection<String> tutorCollection= new ArrayList<String>();

		tutorCollection.add(Integer.toString(tutor.getCode()));	
		tutorCollection.add(tutor.getUsername());	
		tutorCollection.add(tutor.getFirstName());
		tutorCollection.add(tutor.getMiddleName());
		tutorCollection.add(tutor.getLastName());
		tutorCollection.add(tutor.getGender());
		tutorCollection.add(tutor.getEmailAddress());
		tutorCollection.add(tutor.getLandCountryCode());
		tutorCollection.add(tutor.getLandAreaCode());
		tutorCollection.add(tutor.getLandNumber());
		tutorCollection.add(tutor.getMobileCountryCode());
		tutorCollection.add(tutor.getMobileNetworkCode());
		tutorCollection.add(tutor.getMobileNumber());		
		tutorCollection.add(tutor.getDescription());
		tutorCollection.add(tutor.getExperience());
		tutorCollection.add(tutor.getWebLink());
		tutorCollection.add(tutor.getFacebookLink());
		tutorCollection.add(tutor.getTwitterNumber());
		tutorCollection.add(tutor.getMySpaceId());
		tutorCollection.add(tutor.getLinkedInLink());
		tutorCollection.add(tutor.getInstagramId());
		tutorCollection.add(tutor.getViberNumber());
		tutorCollection.add(tutor.getWhatsAppId());
		tutorCollection.add(tutor.getAddressLine1());
		tutorCollection.add(tutor.getAddressLine2());
		tutorCollection.add(tutor.getAddressLine3());
		
		CountryDAO country = new CountryDAO();
		TownDAO town = new TownDAO();
		
		try{
			int townAddCount = 0;
			if(!(Validator.isEmptyOrHavingSpace(tutor.getMobileCountryCode()))){
				Collection<Collection<String>> townCollection = town.findById(Integer.parseInt(tutor.getMobileCountryCode()));
				
				for(Collection<String> townList : townCollection){
					if (townList.toArray()[0].toString().equals(tutor.getTown())){
						tutorCollection.add(townList.toArray()[1].toString());
						tutorCollection.add(townList.toArray()[0].toString());
						townAddCount++;
					}
				}
			}
			
			if(townAddCount == 0){
				tutorCollection.add(" ");
				tutorCollection.add(" ");
				tutorCollection.add(Integer.toString(tutor.getUsertype()));
			}else{
				tutorCollection.add(Integer.toString(tutor.getUsertype()));
			}
			
			int countryAddCount = 0; 
			if(!(Validator.isEmptyOrHavingSpace(tutor.getMobileCountryCode()))){
				Collection<Collection<String>> countryCollection = country.findById(Integer.parseInt(tutor.getMobileCountryCode()));
				if(!(countryCollection.isEmpty())){
					for(Collection<String> countryList : countryCollection){
						tutorCollection.add(countryList.toArray()[1].toString());	
						countryAddCount++;			
					}					
				}
			}
			if(countryAddCount == 0){
				tutorCollection.add(" ");
			}
						
			if(tutor.getIsApproved() == true){
				tutorCollection.add("1");
			}else{
				tutorCollection.add("0");
			}
			
			tutorCollection.add(Integer.toString(tutor.getTutorStatus()));

		}  catch (SQLException sqle){
			log.error("fillTutorCollection(): SQLException "+ sqle.toString());
			throw sqle;
		}  catch (Exception exception) {
			log.error("fillTutorCollection() : Exception" + exception.toString());
			throw exception;
		}
		
		tutorViewCollection.add(tutorCollection);
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
			
			if(!(Validator.isEmptyOrHavingSpace(helper.getParameter("email"))) && !((helper.getParameter("email")).equals(helper.getParameter("emailOld").toString()))){
				// if email address updated
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
			}
			
		} catch (SQLException sqlException) {
			log.error("isValidUserAndEmailBeforeAddTutor(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("isValidUserAndEmailBeforeAddTutor(): Exception " + e.toString());
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
			log.error("validateUsernameEmailFields(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.error("validateUsernameEmailFields(): Exception " + e.toString());
			throw e;
		}
		return validStatus;
	}
	
}
