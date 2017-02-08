package com.genesiis.campus.command;

//20170207 CW c38-view-update-tutor-profile- modified execute(), setVariables() methods
//20170208 CW c38-view-update-tutor-profile- modified setVariables() method.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

//import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.validation.Validator;

public class CmdUpdateTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdUpdateTutorProfile.class.getName());

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

		try {
			final Validator validator = new Validator();
			message = validator.validateTutorFields(helper);
			if (message.equalsIgnoreCase("True")) {
				
				final Tutor tutor = new Tutor();
				final TutorDAO tutorDAO = new TutorDAO();
				int result = 0;
				
				boolean updated = false;				
				updated = setVariables(helper,tutor); // returns true if updated
				
				if( updated)
				{
					UserTypeDAO typeOfUser = new UserTypeDAO();	
					tutor.setUsertype(typeOfUser.getCode(UserType.TUTOR_ROLE.name()));
					result = tutorDAO.update(tutor);
				}
				
				if (result > 0) {
					message = SystemMessage.UPDATED.message();
					Collection<Collection<String>> tutorViewCollection = new ArrayList<Collection<String>>();
					tutorViewCollection = tutorDAO.findById(tutor);
					view.setCollection(tutorViewCollection);		

				} else {
					message = SystemMessage.ERROR.message();
				}
				
			}
		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);
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

	public boolean setVariables(IDataHelper helper, Tutor tutor) {
			boolean updated = false;
			//HttpSession session = helper.getSession(false);
		try {


System.out.println("helper.getParameter(codeOld)) 	= " +helper.getParameter("codeOld"));
System.out.println("helper.getParameter(usernameOld)) 	= " +helper.getParameter("usernameOld"));
System.out.println("helper.getParameter(passwordOld)) 	= " +helper.getParameter("passwordOld"));
System.out.println("helper.getParameter(firstnameOld)) 	= " +helper.getParameter("firstnameOld"));
System.out.println("helper.getParameter(middlenameOld)) 	= " +helper.getParameter("middlenameOld"));
System.out.println("helper.getParameter(lastnameOld)) 	= " +helper.getParameter("lastnameOld"));
System.out.println("helper.getParameter(genderOld)) 	= " +helper.getParameter("genderOld"));
System.out.println("helper.getParameter(emailOld)) 	= " +helper.getParameter("emailOld"));
System.out.println("helper.getParameter(landphonecountrycodeOld)) 	= " +helper.getParameter("landphonecountrycodeOld"));
System.out.println("helper.getParameter(landphoneareacodeOld)) 	= " +helper.getParameter("landphoneareacodeOld"));
System.out.println("helper.getParameter(landphonenumberOld)) 	= " +helper.getParameter("landphonenumberOld"));
System.out.println("helper.getParameter(mobilephonecountrycodeOld)) 	= " +helper.getParameter("mobilephonecountrycodeOld"));
System.out.println("helper.getParameter(mobilephonenetworkcodeOld)) 	= " +helper.getParameter("mobilephonenetworkcodeOld"));
System.out.println("helper.getParameter(mobilephonenumberOld)) 	= " +helper.getParameter("mobilephonenumberOld"));
System.out.println("helper.getParameter(descriptionOld)) 	= " +helper.getParameter("descriptionOld"));
System.out.println("helper.getParameter(experienceOld)) 	= " +helper.getParameter("experienceOld"));
System.out.println("helper.getParameter(weblinkOld)) 	= " +helper.getParameter("weblinkOld"));
System.out.println("helper.getParameter(facebookurlOld)) 	= " +helper.getParameter("facebookurlOld"));
System.out.println("helper.getParameter(twitterurlOld)) 	= " +helper.getParameter("twitterurlOld"));
System.out.println("helper.getParameter(myspaceurlOld)) 	= " +helper.getParameter("myspaceurlOld"));
System.out.println("helper.getParameter(linkedinurlOld)) 	= " +helper.getParameter("linkedinurlOld"));
System.out.println("helper.getParameter(instagramurlOld)) 	= " +helper.getParameter("instagramurlOld"));
System.out.println("helper.getParameter(vibernumberOld)) 	= " +helper.getParameter("vibernumberOld"));
System.out.println("helper.getParameter(whatsappnumberOld)) 	= " +helper.getParameter("whatsappnumberOld"));
System.out.println("helper.getParameter(address1Old)) 	= " +helper.getParameter("address1Old"));
System.out.println("helper.getParameter(address2Old)) 	= " +helper.getParameter("address2Old"));
System.out.println("helper.getParameter(address3Old)) 	= " +helper.getParameter("address3Old"));
System.out.println("helper.getParameter(townOld)) 	= " +helper.getParameter("townOld"));
System.out.println("helper.getParameter(towncodeOld)) 	= " +helper.getParameter("towncodeOld"));
System.out.println("helper.getParameter(usertypeOld)) 	= " +helper.getParameter("usertypeOld"));
System.out.println("helper.getParameter(countrynameOld)) 	= " +helper.getParameter("countrynameOld"));
System.out.println("helper.getParameter(isapprovedOld)) 	= " +helper.getParameter("isapprovedOld"));
System.out.println("helper.getParameter(tutorstatusOld)) 	= " +helper.getParameter("tutorstatusOld"));
			
			tutor.setCode(Integer.parseInt(helper.getParameter("codeOld").toString()));
			
			tutor.setUsername(helper.getParameter("usernameOld"));
			
			if(!((helper.getParameter("password")).equals(helper.getParameter("passwordOld").toString()))){				
				tutor.setPassword(helper.getParameter("password"));
				updated = true;
			}else{
				tutor.setPassword(helper.getParameter("passwordOld").toString());
			}
			
			if(!((helper.getParameter("firstname")).equals(helper.getParameter("firstnameOld").toString()))){				
				tutor.setFirstName(helper.getParameter("firstname"));
				updated = true;
			}else{
				tutor.setFirstName(helper.getParameter("firstnameOld").toString());
			}
			
 			if(Validator.isNotEmpty(helper.getParameter("middlename"))){
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
			
			if(!((helper.getParameter("lastname")).equals(helper.getParameter("lastnameOld").toString()))){	
				tutor.setLastName(helper.getParameter("lastname"));			
				updated = true;
			}else{
				tutor.setLastName(helper.getParameter("lastnameOld").toString());
			}
			
			if(!((helper.getParameter("gender")).equals(helper.getParameter("genderOld").toString()))){		
				tutor.setGender(helper.getParameter("gender"));
				updated = true;
			}else{		
				tutor.setGender(helper.getParameter("genderOld").toString());
			}

			if(!((helper.getParameter("email")).equals(helper.getParameter("emailOld").toString()))){				
				tutor.setEmailAddress(helper.getParameter("email"));
				updated = true;
			}else{
				tutor.setEmailAddress(helper.getParameter("emailOld").toString());
			}			
			
			if(Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(helper.getParameter("landphonecountrycodeOld").toString()))){	
					tutor.setLandCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setLandCountryCode(helper.getParameter("landphonecountrycodeOld").toString());
				}
			}else{
				tutor.setLandCountryCode(helper.getParameter("landphonecountrycodeOld").toString());
			}
			
			if(!((helper.getParameter("landAreaCode")).equals(helper.getParameter("landphoneareacodeOld").toString()))){	
				tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
				updated = true;
			}else{			
				tutor.setLandAreaCode(helper.getParameter("landphoneareacodeOld").toString());
			}
			
			if(!((helper.getParameter("landNumber")).equals(helper.getParameter("landphonenumberOld").toString()))){	
				tutor.setLandNumber(helper.getParameter("landNumber"));
				updated = true;
			}else{			
				tutor.setLandNumber(helper.getParameter("landphonenumberOld").toString());
			}

			if(Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(helper.getParameter("mobilephonecountrycodeOld").toString()))){	
					tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setMobileCountryCode(helper.getParameter("mobilephonecountrycodeOld").toString());
				}
			}else{
				tutor.setMobileCountryCode(helper.getParameter("mobilephonecountrycodeOld").toString());
			}


			if(!((helper.getParameter("mobileNetworkCode")).equals(helper.getParameter("mobilephonenetworkcodeOld").toString()))){
				tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));		
				updated = true;
			}else{		
				tutor.setMobileNetworkCode(helper.getParameter("mobilephonenetworkcodeOld").toString());
			}

			if(!((helper.getParameter("mobileNumber")).equals(helper.getParameter("mobilephonenumberOld").toString()))){	
				tutor.setMobileNumber(helper.getParameter("mobileNumber"));			
				updated = true;
			}else{
				tutor.setMobileNumber(helper.getParameter("mobilephonenumberOld").toString());
			}
			
			if(Validator.isNotEmpty(helper.getParameter("aboutMe"))){
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

			if(Validator.isNotEmpty(helper.getParameter("experience"))){
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

			if(Validator.isNotEmpty(helper.getParameter("weblink"))){
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

			if(Validator.isNotEmpty(helper.getParameter("facebook"))){
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

			if(Validator.isNotEmpty(helper.getParameter("twitter"))){
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

			if(Validator.isNotEmpty(helper.getParameter("myspace"))){
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

			if(Validator.isNotEmpty(helper.getParameter("linkedin"))){
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

			if(Validator.isNotEmpty(helper.getParameter("instagram"))){
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

			if(Validator.isNotEmpty(helper.getParameter("viber"))){
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

			if(Validator.isNotEmpty(helper.getParameter("whatsapp"))){
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

			if(!((helper.getParameter("address1")).equals(helper.getParameter("address1Old").toString()))){				
				tutor.setAddressLine1(helper.getParameter("address1"));
				updated = true;
			}else{
				tutor.setAddressLine1(helper.getParameter("address1Old").toString());
			}

			if(Validator.isNotEmpty(helper.getParameter("address2"))){
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
			
			if(Validator.isNotEmpty(helper.getParameter("address3"))){
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
			
			int approved = Integer.parseInt(helper.getParameter("isApproved"));
			if (approved == 1){
				tutor.setIsApproved(true);
				updated = true;
			}

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
			
			if(!(Integer.parseInt(helper.getParameter("newtutorStatus")) == (Integer.parseInt(helper.getParameter("tutorstatusOld").toString())))){	
				tutor.setTutorStatus(Integer.parseInt(helper.getParameter("newtutorStatus")));
				updated = true;
			}else{
				tutor.setTutorStatus(Integer.parseInt(helper.getParameter("tutorstatusOld").toString()));
			}
			
		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
		
		return updated;
	}

}
