package com.genesiis.campus.command;

//20170207 CW c38-view-update-tutor-profile- modified execute(), setVariables() methods

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

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
			HttpSession session = helper.getSession(false);
		try {
			
			tutor.setCode(Integer.parseInt(session.getAttribute("code").toString()));
			
			tutor.setUsername(helper.getParameter("username"));
			
			if(!((helper.getParameter("password")).equals(session.getAttribute("password").toString()))){				
				tutor.setPassword(helper.getParameter("password"));
				updated = true;
			}else{
				tutor.setPassword(session.getAttribute("password").toString());
			}
			
			if(!((helper.getParameter("firstname")).equals(session.getAttribute("firstname").toString()))){				
				tutor.setFirstName(helper.getParameter("firstname"));
				updated = true;
			}else{
				tutor.setFirstName(session.getAttribute("firstname").toString());
			}
			
 			if(Validator.isNotEmpty(helper.getParameter("middlename"))){
				if(!((helper.getParameter("middlename")).equals(session.getAttribute("middlename").toString()))){	
					tutor.setMiddleName(helper.getParameter("middlename"));	
					updated = true;		
				}else{
					tutor.setMiddleName(session.getAttribute("middlename").toString());	
				}
			}else{
				tutor.setMiddleName("-");
				if(Validator.isNotEmpty(session.getAttribute("middlename").toString())){
					updated = true;
				}
			}
			
			if(!((helper.getParameter("lastname")).equals(session.getAttribute("lastname").toString()))){	
				tutor.setLastName(helper.getParameter("lastname"));			
				updated = true;
			}else{
				tutor.setLastName(session.getAttribute("lastname").toString());
			}
			
			if(!((helper.getParameter("gender")).equals(session.getAttribute("gender").toString()))){		
				tutor.setGender(helper.getParameter("gender"));
				updated = true;
			}else{		
				tutor.setGender(session.getAttribute("gender").toString());
			}

			if(!((helper.getParameter("email")).equals(session.getAttribute("email").toString()))){				
				tutor.setEmailAddress(helper.getParameter("email"));
				updated = true;
			}else{
				tutor.setEmailAddress(session.getAttribute("email").toString());
			}			
			
			if(Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(session.getAttribute("landphonecountrycode").toString()))){	
					tutor.setLandCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setLandCountryCode(session.getAttribute("landphonecountrycode").toString());
				}
			}else{
				tutor.setLandCountryCode(session.getAttribute("landphonecountrycode").toString());
			}
			
			if(!((helper.getParameter("landAreaCode")).equals(session.getAttribute("landphoneareacode").toString()))){	
				tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
				updated = true;
			}else{			
				tutor.setLandAreaCode(session.getAttribute("landphoneareacode").toString());
			}
			
			if(!((helper.getParameter("landNumber")).equals(session.getAttribute("landphonenumber").toString()))){	
				tutor.setLandNumber(helper.getParameter("landNumber"));
				updated = true;
			}else{			
				tutor.setLandNumber(session.getAttribute("landphonenumber").toString());
			}

			if(Integer.parseInt(helper.getParameter("countryDetails")) != 0){
				if(!((helper.getParameter("countryDetails")).equals(session.getAttribute("mobilephonecountrycode").toString()))){	
					tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
					updated = true;
				}else{
					tutor.setMobileCountryCode(session.getAttribute("mobilephonecountrycode").toString());
				}
			}else{
				tutor.setMobileCountryCode(session.getAttribute("mobilephonecountrycode").toString());
			}


			if(!((helper.getParameter("mobileNetworkCode")).equals(session.getAttribute("mobilephonenetworkcode").toString()))){
				tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));		
				updated = true;
			}else{		
				tutor.setMobileNetworkCode(session.getAttribute("mobilephonenetworkcode").toString());
			}

			if(!((helper.getParameter("mobileNumber")).equals(session.getAttribute("mobilephonenumber").toString()))){	
				tutor.setMobileNumber(helper.getParameter("mobileNumber"));			
				updated = true;
			}else{
				tutor.setMobileNumber(session.getAttribute("mobilephonenumber").toString());
			}
			
			if(Validator.isNotEmpty(helper.getParameter("aboutMe"))){
				if(!((helper.getParameter("aboutMe")).equals(session.getAttribute("description").toString()))){	
					tutor.setDescription(helper.getParameter("aboutMe"));	
					updated = true;		
				}else{
					tutor.setDescription(session.getAttribute("description").toString());	
				}
			}else{
				tutor.setDescription("-");
				if(Validator.isNotEmpty(session.getAttribute("description").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("experience"))){
				if(!((helper.getParameter("experience")).equals(session.getAttribute("experience").toString()))){	
					tutor.setExperience(helper.getParameter("experience"));	
					updated = true;		
				}else{
					tutor.setExperience(session.getAttribute("experience").toString());	
				}
			}else{
				tutor.setExperience("-");
				if(Validator.isNotEmpty(session.getAttribute("experience").toString())){
					updated = true;
				}
			}			

			if(Validator.isNotEmpty(helper.getParameter("weblink"))){
				if(!((helper.getParameter("weblink")).equals(session.getAttribute("weblink").toString()))){	
					tutor.setWebLink(helper.getParameter("weblink"));	
					updated = true;		
				}else{
					tutor.setWebLink(session.getAttribute("weblink").toString());	
				}
			}else{
				tutor.setWebLink("-");
				if(Validator.isNotEmpty(session.getAttribute("weblink").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("facebook"))){
				if(!((helper.getParameter("facebook")).equals(session.getAttribute("facebookurl").toString()))){	
					tutor.setFacebookLink(helper.getParameter("facebook"));	
					updated = true;		
				}else{
					tutor.setFacebookLink(session.getAttribute("facebookurl").toString());	
				}
			}else{
				tutor.setFacebookLink("-");
				if(Validator.isNotEmpty(session.getAttribute("facebookurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("twitter"))){
				if(!((helper.getParameter("twitter")).equals(session.getAttribute("twitterurl").toString()))){	
					tutor.setTwitterNumber(helper.getParameter("twitter"));	
					updated = true;		
				}else{
					tutor.setTwitterNumber(session.getAttribute("twitterurl").toString());	
				}
			}else{
					tutor.setTwitterNumber("-");
				if(Validator.isNotEmpty(session.getAttribute("twitterurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("myspace"))){
				if(!((helper.getParameter("myspace")).equals(session.getAttribute("myspaceurl").toString()))){	
					tutor.setMySpaceId(helper.getParameter("myspace"));	
					updated = true;		
				}else{
					tutor.setMySpaceId(session.getAttribute("myspaceurl").toString());	
				}
			}else{
				tutor.setMySpaceId("-");
				if(Validator.isNotEmpty(session.getAttribute("myspaceurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("linkedin"))){
				if(!((helper.getParameter("linkedin")).equals(session.getAttribute("linkedinurl").toString()))){	
					tutor.setLinkedInLink(helper.getParameter("linkedin"));	
					updated = true;		
				}else{
					tutor.setLinkedInLink(session.getAttribute("linkedinurl").toString());	
				}
			}else{
				tutor.setLinkedInLink("-");
				if(Validator.isNotEmpty(session.getAttribute("linkedinurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("instagram"))){
				if(!((helper.getParameter("instagram")).equals(session.getAttribute("instagramurl").toString()))){	
					tutor.setInstagramId(helper.getParameter("instagram"));	
					updated = true;		
				}else{
					tutor.setInstagramId(session.getAttribute("instagramurl").toString());	
				}
			}else{
				tutor.setInstagramId("-");
				if(Validator.isNotEmpty(session.getAttribute("instagramurl").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("viber"))){
				if(!((helper.getParameter("viber")).equals(session.getAttribute("vibernumber").toString()))){	
					tutor.setViberNumber(helper.getParameter("viber"));	
					updated = true;		
				}else{
					tutor.setViberNumber(session.getAttribute("vibernumber").toString());	
				}
			}else{
					tutor.setViberNumber("-");
				if(Validator.isNotEmpty(session.getAttribute("vibernumber").toString())){
					updated = true;
				}
			}

			if(Validator.isNotEmpty(helper.getParameter("whatsapp"))){
				if(!((helper.getParameter("whatsapp")).equals(session.getAttribute("whatsappnumber").toString()))){	
					tutor.setWhatsAppId(helper.getParameter("whatsapp"));	
					updated = true;		
				}else{
					tutor.setWhatsAppId(session.getAttribute("whatsappnumber").toString());	
				}
			}else{
					tutor.setWhatsAppId("-");
				if(Validator.isNotEmpty(session.getAttribute("whatsappnumber").toString())){
					updated = true;
				}
			}

			if(!((helper.getParameter("address1")).equals(session.getAttribute("address1").toString()))){				
				tutor.setAddressLine1(helper.getParameter("address1"));
				updated = true;
			}else{
				tutor.setAddressLine1(session.getAttribute("address1").toString());
			}

			if(Validator.isNotEmpty(helper.getParameter("address2"))){
				if(!((helper.getParameter("address2")).equals(session.getAttribute("address2").toString()))){	
					tutor.setAddressLine2(helper.getParameter("address2"));	
					updated = true;		
				}else{
					tutor.setAddressLine2(session.getAttribute("address2").toString());	
				}
			}else{
				tutor.setAddressLine2("-");
				if(Validator.isNotEmpty(session.getAttribute("address2").toString())){
					updated = true;
				}
			}
			
			if(Validator.isNotEmpty(helper.getParameter("address3"))){
				if(!((helper.getParameter("address3")).equals(session.getAttribute("address3").toString()))){	
					tutor.setAddressLine3(helper.getParameter("address3"));	
					updated = true;		
				}else{
					tutor.setAddressLine3(session.getAttribute("address3").toString());	
				}
			}else{
				tutor.setAddressLine3("-");
				if(Validator.isNotEmpty(session.getAttribute("address3").toString())){
					updated = true;
				}
			}
			
			int approved = Integer.parseInt(helper.getParameter("isApproved"));
			if (approved == 1){
				tutor.setIsApproved(true);
				updated = true;
			}

			if(Double.parseDouble(helper.getParameter("townDetails")) != 0){
				if(!((helper.getParameter("townDetails")).equals(session.getAttribute("towncode").toString()))){	
					tutor.setTown(helper.getParameter("townDetails"));
					updated = true;
				}else{
					tutor.setTown(session.getAttribute("towncode").toString());
				}
			}else{
				tutor.setTown(session.getAttribute("towncode").toString());
			}
			
			if(!(Integer.parseInt(helper.getParameter("newtutorStatus")) == (Integer.parseInt(session.getAttribute("tutorstatus").toString())))){	
				tutor.setTutorStatus(Integer.parseInt(helper.getParameter("newtutorStatus")));
				updated = true;
			}else{
				tutor.setTutorStatus(Integer.parseInt(session.getAttribute("tutorstatus").toString()));
			}
			
		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
		
		return updated;
	}

}
