package com.genesiis.campus.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.net.ssl.HttpsURLConnection;

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
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			final Validator validator=new Validator();
			int result = 0;
			message = validator.validateTutorFields(helper);
			if (message.equalsIgnoreCase("True")) {
				
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
		try {
			tutor.setUsername(helper.getParameter("username"));
			
			if(!((helper.getParameter("password")).equals(helper.getSession(false).getAttribute("password").toString()))){				
				tutor.setPassword(helper.getParameter("password"));
				updated = true;
			}else{
				tutor.setPassword(helper.getSession(false).getAttribute("password").toString());
			}
			
			if(!((helper.getParameter("firstname")).equals(helper.getSession(false).getAttribute("firstname").toString()))){				
				tutor.setFirstName(helper.getParameter("firstname"));
				updated = true;
			}else{
				tutor.setFirstName(helper.getSession(false).getAttribute("firstname").toString());
			}
			
 			if(Validator.isNotEmpty(helper.getParameter("middlename"))){
				if(!((helper.getParameter("middlename")).equals(helper.getSession(false).getAttribute("middlename").toString()))){	
					tutor.setMiddleName(helper.getParameter("middlename"));	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("middlename").toString());	
				}
			}else{
				tutor.setMiddleName("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("middlename").toString())){
					updated = true;
				}
			}
			
			if(!((helper.getParameter("lastname")).equals(helper.getSession(false).getAttribute("lastname").toString()))){	
				tutor.setLastName(helper.getParameter("lastname"));			
				updated = true;
			}else{
				tutor.setLastName(helper.getSession(false).getAttribute("lastname").toString());
			}
			
			if(!((helper.getParameter("gender")).equals(helper.getSession(false).getAttribute("gender").toString()))){		
				tutor.setGender(helper.getParameter("gender"));
				updated = true;
			}else{		
				tutor.setGender(helper.getSession(false).getAttribute("gender").toString());
			}

			if(!((helper.getParameter("email")).equals(helper.getSession(false).getAttribute("email").toString()))){				
				tutor.setEmailAddress(helper.getParameter("email"));
				updated = true;
			}else{
				tutor.setEmailAddress(helper.getSession(false).getAttribute("email").toString());
			}			
			
			if(Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(helper.getSession(false).getAttribute("landphonecountrycode").toString()))){	
					tutor.setLandCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setLandCountryCode(helper.getSession(false).getAttribute("landphonecountrycode").toString());
				}
			}else{
				tutor.setLandCountryCode(helper.getSession(false).getAttribute("landphonecountrycode").toString());
			}
			
			if(!((helper.getParameter("landAreaCode")).equals(helper.getSession(false).getAttribute("landphoneareacode").toString()))){	
				tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
				updated = true;
			}else{			
				tutor.setLandAreaCode(helper.getSession(false).getAttribute("landphoneareacode").toString());
			}
			
			if(!((helper.getParameter("landNumber")).equals(helper.getSession(false).getAttribute("landphonenumber").toString()))){	
				tutor.setLandNumber(helper.getParameter("landNumber"));
				updated = true;
			}else{			
				tutor.setLandNumber(helper.getSession(false).getAttribute("landphonenumber").toString());
			}

			if(Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(helper.getSession(false).getAttribute("mobilephonecountrycode").toString()))){	
					tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setMobileCountryCode(helper.getSession(false).getAttribute("mobilephonecountrycode").toString());
				}
			}else{
				tutor.setMobileCountryCode(helper.getSession(false).getAttribute("mobilephonecountrycode").toString());
			}


			if(!((helper.getParameter("mobileNetworkCode")).equals(helper.getSession(false).getAttribute("mobilephonenetworkcode").toString()))){
				tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));		
				updated = true;
			}else{		
				tutor.setMobileNetworkCode(helper.getSession(false).getAttribute("mobilephonenetworkcode").toString());
			}

			if(!((helper.getParameter("mobileNumber")).equals(helper.getSession(false).getAttribute("mobilephonenumber").toString()))){	
				tutor.setMobileNumber(helper.getParameter("mobileNumber"));			
				updated = true;
			}else{
				tutor.setMobileNumber(helper.getSession(false).getAttribute("mobilephonenumber").toString());
			}
			
			if(Validator.isNotEmpty(helper.getParameter("aboutMe"))){
				if(!((helper.getParameter("aboutMe")).equals(helper.getSession(false).getAttribute("description").toString()))){	
					tutor.setDescription(helper.getParameter("aboutMe"));	
					updated = true;		
				}else{
					tutor.setDescription(helper.getSession(false).getAttribute("description").toString());	
				}
			}else{
				tutor.setDescription("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("description").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("experience"))){
				if(!((helper.getParameter("experience")).equals(helper.getSession(false).getAttribute("experience").toString()))){	
					tutor.setExperience(helper.getParameter("experience"));	
					updated = true;		
				}else{
					tutor.setExperience(helper.getSession(false).getAttribute("experience").toString());	
				}
			}else{
				tutor.setExperience("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("experience").toString())){
					updated = true;
				}
			}			

			if(Validator.isNotEmpty(helper.getParameter("weblink"))){
				if(!((helper.getParameter("weblink")).equals(helper.getSession(false).getAttribute("weblink").toString()))){	
					tutor.setWebLink(helper.getParameter("weblink"));	
					updated = true;		
				}else{
					tutor.setWebLink(helper.getSession(false).getAttribute("weblink").toString());	
				}
			}else{
				tutor.setWebLink("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("weblink").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("facebook"))){
				if(!((helper.getParameter("facebook")).equals(helper.getSession(false).getAttribute("facebookurl").toString()))){	
					tutor.setFacebookLink(helper.getParameter("facebook"));	
					updated = true;		
				}else{
					tutor.setFacebookLink(helper.getSession(false).getAttribute("facebookurl").toString());	
				}
			}else{
				tutor.setFacebookLink("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("facebookurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("twitter"))){
				if(!((helper.getParameter("twitter")).equals(helper.getSession(false).getAttribute("twitterurl").toString()))){	
					tutor.setTwitterNumber(helper.getParameter("twitter"));	
					updated = true;		
				}else{
					tutor.setTwitterNumber(helper.getSession(false).getAttribute("twitterurl").toString());	
				}
			}else{
					tutor.setTwitterNumber("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("twitterurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("myspace"))){
				if(!((helper.getParameter("myspace")).equals(helper.getSession(false).getAttribute("myspaceurl").toString()))){	
					tutor.setMySpaceId(helper.getParameter("myspace"));	
					updated = true;		
				}else{
					tutor.setMySpaceId(helper.getSession(false).getAttribute("myspaceurl").toString());	
				}
			}else{
				tutor.setMySpaceId("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("myspaceurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("linkedin"))){
				if(!((helper.getParameter("linkedin")).equals(helper.getSession(false).getAttribute("linkedinurl").toString()))){	
					tutor.setLinkedInLink(helper.getParameter("linkedin"));	
					updated = true;		
				}else{
					tutor.setLinkedInLink(helper.getSession(false).getAttribute("linkedinurl").toString());	
				}
			}else{
				tutor.setLinkedInLink("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("linkedinurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("instagram"))){
				if(!((helper.getParameter("instagram")).equals(helper.getSession(false).getAttribute("instagramurl").toString()))){	
					tutor.setInstagramId(helper.getParameter("instagram"));	
					updated = true;		
				}else{
					tutor.setInstagramId(helper.getSession(false).getAttribute("instagramurl").toString());	
				}
			}else{
				tutor.setInstagramId("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("instagramurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("viber"))){
				if(!((helper.getParameter("viber")).equals(helper.getSession(false).getAttribute("vibernumber").toString()))){	
					tutor.setViberNumber(helper.getParameter("viber"));	
					updated = true;		
				}else{
					tutor.setViberNumber(helper.getSession(false).getAttribute("vibernumber").toString());	
				}
			}else{
					tutor.setViberNumber("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("vibernumber").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("whatsapp"))){
				if(!((helper.getParameter("whatsapp")).equals(helper.getSession(false).getAttribute("whatsappnumber").toString()))){	
					tutor.setWhatsAppId(helper.getParameter("whatsapp"));	
					updated = true;		
				}else{
					tutor.setWhatsAppId(helper.getSession(false).getAttribute("whatsappnumber").toString());	
				}
			}else{
					tutor.setWhatsAppId("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("whatsappnumber").toString())){
					updated = true;
				}
			}

			if(!((helper.getParameter("address1")).equals(helper.getSession(false).getAttribute("address1").toString()))){				
				tutor.setAddressLine1(helper.getParameter("address1"));
				updated = true;
			}else{
				tutor.setAddressLine1(helper.getSession(false).getAttribute("address1").toString());
			}

			if(Validator.isNotEmpty(helper.getParameter("address2"))){
				if(!((helper.getParameter("address2")).equals(helper.getSession(false).getAttribute("address2").toString()))){	
					tutor.setAddressLine2(helper.getParameter("address2"));	
					updated = true;		
				}else{
					tutor.setAddressLine2(helper.getSession(false).getAttribute("address2").toString());	
				}
			}else{
				tutor.setAddressLine2("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("address2").toString())){
					updated = true;
				}
			}

			System.out.println(" 0.7 "); //ccw
			
			if(Validator.isNotEmpty(helper.getParameter("address3"))){
				if(!((helper.getParameter("address3")).equals(helper.getSession(false).getAttribute("address3").toString()))){	
					tutor.setAddressLine3(helper.getParameter("address3"));	
					updated = true;		
				}else{
					tutor.setAddressLine3(helper.getSession(false).getAttribute("address3").toString());	
				}
			}else{
				tutor.setAddressLine3("-");
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("address3").toString())){
					updated = true;
				}
			}

			System.out.println(" 0.7 "); //ccw
			
			if (Validator.isNotEmpty(helper.getParameter("isApproved"))){
				int a = Integer.parseInt(helper.getParameter("isApproved"));
				System.out.println(" 1 "); //ccw
				if (a == 1){
					tutor.setIsApproved(true);
				}
			}

			System.out.println(" 0.8 "); //ccw
			if(Double.parseDouble(helper.getParameter("townDetails")) != 0){
				if(!((helper.getParameter("townDetails")).equals(helper.getSession(false).getAttribute("towncode").toString()))){	
					tutor.setTown(helper.getParameter("townDetails"));
					updated = true;
				}else{
					tutor.setTown(helper.getSession(false).getAttribute("towncode").toString());
				}
			}else{
				tutor.setTown(helper.getSession(false).getAttribute("towncode").toString());
			}

			System.out.println(" 0.9 "); //ccw
			System.out.println(" 2 = "+ helper.getParameter("newtutorStatus")); //ccw
			System.out.println(" 2.1 = "+ helper.getSession(false).getAttribute("tutorstatus")); //ccw
			if(!(Integer.parseInt(helper.getParameter("newtutorStatus")) == (Integer.parseInt(helper.getSession(false).getAttribute("tutorstatus").toString())))){	
				tutor.setTutorStatus(Integer.parseInt(helper.getParameter("newtutorStatus")));
				updated = true;
			}else{
				tutor.setTutorStatus(Integer.parseInt(helper.getSession(false).getAttribute("tutorstatus").toString()));
			}
			

			System.out.println(" 3 "); //ccw
		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
		
		return updated;
	}

}
