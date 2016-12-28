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
			System.out.println("username = " + helper.getParameter("username"));
			tutor.setUsername(helper.getParameter("username"));
			

			System.out.println("");
			System.out.println("");
			System.out.println("password = " + helper.getParameter("password"));
			System.out.println("password1 = " + helper.getSession(false).getAttribute("password"));
			
			if(!((helper.getParameter("password")).equals(helper.getSession(false).getAttribute("password").toString()))){				
				tutor.setPassword(helper.getParameter("password"));
				updated = true;
			}else{
				tutor.setPassword(helper.getSession(false).getAttribute("password").toString());
			}
			

			System.out.println("");
			System.out.println("");
			System.out.println("firstname = " + helper.getParameter("firstname"));
			System.out.println("firstname = " + helper.getSession(false).getAttribute("firstname"));
			
			if(!((helper.getParameter("firstname")).equals(helper.getSession(false).getAttribute("firstname").toString()))){				
				tutor.setFirstName(helper.getParameter("firstname"));
				updated = true;
			}else{
				tutor.setFirstName(helper.getSession(false).getAttribute("firstname").toString());
			}
						
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("middlename = " + helper.getParameter("middlename"));
			System.out.println("middlename = " + helper.getSession(false).getAttribute("middlename"));
			
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
			
 			
 			

			System.out.println("");
			System.out.println("");
			System.out.println("lastname = " + helper.getParameter("lastname"));
			System.out.println("lastname = " + helper.getSession(false).getAttribute("lastname"));
			
			if(!((helper.getParameter("lastname")).equals(helper.getSession(false).getAttribute("lastname").toString()))){	
				tutor.setLastName(helper.getParameter("lastname"));			
				updated = true;
			}else{
				tutor.setLastName(helper.getSession(false).getAttribute("lastname").toString());
			}
			

			System.out.println("");
			System.out.println("");
			System.out.println("gender = " + helper.getParameter("gender"));
			System.out.println("gender = " + helper.getSession(false).getAttribute("gender"));
			
			if(!((helper.getParameter("gender")).equals(helper.getSession(false).getAttribute("gender").toString()))){		
				tutor.setGender(helper.getParameter("gender"));
				updated = true;
			}else{		
				tutor.setGender(helper.getSession(false).getAttribute("gender").toString());
			}
			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("email = " + helper.getParameter("email"));
			System.out.println("email = " + helper.getSession(false).getAttribute("email"));
			
			if(!((helper.getParameter("email")).equals(helper.getSession(false).getAttribute("email").toString()))){				
				tutor.setEmailAddress(helper.getParameter("email"));
				updated = true;
			}else{
				tutor.setEmailAddress(helper.getSession(false).getAttribute("email").toString());
			}


			System.out.println("");
			System.out.println("");
			System.out.println("countryDetails = " + helper.getParameter("countryDetails"));
			System.out.println("landphonecountrycode = " + helper.getSession(false).getAttribute("landphonecountrycode"));
			
			if(!((helper.getParameter("countryDetails")).equals(helper.getSession(false).getAttribute("landphonecountrycode").toString()))){	
				tutor.setLandCountryCode(helper.getParameter("countryDetails"));			
				updated = true;
			}else{
				tutor.setLandCountryCode(helper.getSession(false).getAttribute("landphonecountrycode").toString());
			}

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("landAreaCode = " + helper.getParameter("landAreaCode"));
			System.out.println("landphoneareacode = " + helper.getSession(false).getAttribute("landphoneareacode"));
			
			if(!((helper.getParameter("landAreaCode")).equals(helper.getSession(false).getAttribute("landphoneareacode").toString()))){	
				tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
				updated = true;
			}else{			
				tutor.setLandAreaCode(helper.getSession(false).getAttribute("landphoneareacode").toString());
			}

			

			System.out.println("");
			System.out.println("");
			System.out.println("landNumber = " + helper.getParameter("landNumber"));
			System.out.println("landphonenumber = " + helper.getSession(false).getAttribute("landphonenumber"));
			
			if(!((helper.getParameter("landNumber")).equals(helper.getSession(false).getAttribute("landphonenumber").toString()))){	
				tutor.setLandNumber(helper.getParameter("landNumber"));
				updated = true;
			}else{			
				tutor.setLandNumber(helper.getSession(false).getAttribute("landphonenumber").toString());
			}

			

			System.out.println("");
			System.out.println("");
			System.out.println("countryDetails = " + helper.getParameter("countryDetails"));
			System.out.println("mobilephonecountrycode" + helper.getSession(false).getAttribute("mobilephonecountrycode"));
			
			if(!((helper.getParameter("countryDetails")).equals(helper.getSession(false).getAttribute("mobilephonecountrycode").toString()))){		
				tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
				updated = true;
			}else{		
				tutor.setMobileCountryCode(helper.getSession(false).getAttribute("mobilephonecountrycode").toString());
			}

			

			System.out.println("");
			System.out.println("");
			System.out.println("mobileNetworkCode = " + helper.getParameter("mobileNetworkCode"));
			System.out.println("mobilephonenetworkcode = " + helper.getSession(false).getAttribute("mobilephonenetworkcode"));
			
			if(!((helper.getParameter("mobileNetworkCode")).equals(helper.getSession(false).getAttribute("mobilephonenetworkcode").toString()))){
				tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));		
				updated = true;
			}else{		
				tutor.setMobileNetworkCode(helper.getSession(false).getAttribute("mobilephonenetworkcode").toString());
			}

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("mobileNumber = " + helper.getParameter("mobileNumber"));
			System.out.println("mobilephonenumber = " + helper.getSession(false).getAttribute("mobilephonenumber"));
			
			if(!((helper.getParameter("mobileNumber")).equals(helper.getSession(false).getAttribute("mobilephonenumber").toString()))){	
				tutor.setMobileNumber(helper.getParameter("mobileNumber"));			
				updated = true;
			}else{
				tutor.setMobileNumber(helper.getSession(false).getAttribute("mobilephonenumber").toString());
			}
			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("aboutMe = " + helper.getParameter("aboutMe"));
			System.out.println("description = " + helper.getSession(false).getAttribute("description"));
			
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

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("experience = " + helper.getParameter("experience"));
			System.out.println("experience = " + helper.getSession(false).getAttribute("experience"));
			
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

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("weblink = " + helper.getParameter("weblink"));
			System.out.println("weblink = " + helper.getSession(false).getAttribute("weblink"));
			
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

			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("facebook = " + helper.getParameter("facebook"));
			System.out.println("facebookurl = " + helper.getSession(false).getAttribute("facebookurl"));
			
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

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("twitter = " + helper.getParameter("twitter"));
			System.out.println("twitterurl = " + helper.getSession(false).getAttribute("twitterurl"));
			
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

			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("myspace = " + helper.getParameter("myspace"));
			System.out.println("myspaceurl = " + helper.getSession(false).getAttribute("myspaceurl"));
			
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
			
			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("linkedin = " + helper.getParameter("linkedin"));
			System.out.println("linkedinurl = " + helper.getSession(false).getAttribute("linkedinurl"));
			
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

			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("instagram = " + helper.getParameter("instagram"));
			System.out.println("instagramurl = " + helper.getSession(false).getAttribute("instagramurl"));
			
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

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("viber = " + helper.getParameter("viber"));
			System.out.println("vibernumber = " + helper.getSession(false).getAttribute("vibernumber"));
			
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
			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("whatsapp = " + helper.getParameter("whatsapp"));
			System.out.println("whatsappnumber = " + helper.getSession(false).getAttribute("whatsappnumber"));
			
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

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("address1 = " + helper.getParameter("address1"));
			System.out.println("address1 = " + helper.getSession(false).getAttribute("address1"));
			
			if(!((helper.getParameter("address1")).equals(helper.getSession(false).getAttribute("address1").toString()))){				
				tutor.setAddressLine1(helper.getParameter("address1"));
				updated = true;
			}else{
				tutor.setAddressLine1(helper.getSession(false).getAttribute("address1").toString());
			}

			
			
			

			System.out.println("");
			System.out.println("");
			System.out.println("address2 = " + helper.getParameter("address2"));
			System.out.println("address2 = " + helper.getSession(false).getAttribute("address2"));
			
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

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("address3 = " + helper.getParameter("address3"));
			System.out.println("address3 = " + helper.getSession(false).getAttribute("address3"));
			
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
			}//done

			
			

			System.out.println("");
			System.out.println("");
			System.out.println("townDetails = " + helper.getParameter("townDetails")); //4454031454
			System.out.println("town = " + helper.getSession(false).getAttribute("town").toString()); //Aiyamperumal
			System.out.println("towncode = " + helper.getSession(false).getAttribute("towncode").toString());
			
			if(helper.getParameter("townDetails") == "0"){
				if(!((helper.getParameter("townDetails")).equals(helper.getSession(false).getAttribute("towncode").toString()))){	
					tutor.setTown(helper.getParameter("townDetails"));
					updated = true;
				}else{
					tutor.setTown(helper.getSession(false).getAttribute("towncode").toString());
				}
			}else{
				tutor.setTown(helper.getSession(false).getAttribute("towncode").toString());
			}

		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
		
		return updated;
	}
	
	/*
	public void checkForUpdates(IDataHelper helper, Collection<String> tutorCollection){*/

		//System.out.println("Code = " + helper.getSession(false).getAttribute("code"));
		/*
		tutor.setUsername			(tutorCollection.toArray()[0].toString());   
		tutor.setPassword			(tutorCollection.toArray()[1].toString());
		tutor.setFirstName			(tutorCollection.toArray()[2].toString());
		tutor.setMiddleName			(tutorCollection.toArray()[3].toString());
		tutor.setLastName			(tutorCollection.toArray()[4].toString());
		tutor.setGender				(tutorCollection.toArray()[5].toString());
		tutor.setEmailAddress		(tutorCollection.toArray()[6].toString());
		tutor.setLandCountryCode	(tutorCollection.toArray()[7].toString());
		tutor.setLandAreaCode		(tutorCollection.toArray()[8].toString());
		tutor.setLandNumber			(tutorCollection.toArray()[9].toString());
		tutor.setMobileCountryCode	(tutorCollection.toArray()[10].toString());
		tutor.setMobileNetworkCode	(tutorCollection.toArray()[11].toString());
		tutor.setMobileNumber		(tutorCollection.toArray()[12].toString());
		tutor.setDescription		(tutorCollection.toArray()[13].toString());
		tutor.setExperience			(tutorCollection.toArray()[14].toString());
		tutor.setWebLink			(tutorCollection.toArray()[15].toString());
		tutor.setFacebookLink		(tutorCollection.toArray()[16].toString());
		tutor.setTwitterNumber		(tutorCollection.toArray()[17].toString());
		tutor.setMySpaceId			(tutorCollection.toArray()[18].toString());
		tutor.setLinkedInLink		(tutorCollection.toArray()[19].toString());
		tutor.setInstagramId		(tutorCollection.toArray()[20].toString());
		tutor.setViberNumber		(tutorCollection.toArray()[21].toString());
		tutor.setWhatsAppId			(tutorCollection.toArray()[22].toString());
		tutor.setAddressLine1		(tutorCollection.toArray()[23].toString());
		tutor.setAddressLine2		(tutorCollection.toArray()[24].toString());
		tutor.setAddressLine3		(tutorCollection.toArray()[25].toString());
		tutor.setTown				(tutorCollection.toArray()[26].toString());
		*/
	/*	
		if((tutorCollection.toArray()[0].toString()) != (helper.getSession(false).getAttribute("username"))){
			tutorCollection.get  =	 helper.getSession(false).getAttribute("username");
		}*/
		
/*		IF (tutorCollection.toArray()[1].toString()  !=	 helper.getSession(false).getAttribute("password"))					{
			tutorCollection.toArray()[1].toString()  =	 helper.getSession(false).getAttribute("password");
			}				
		IF (tutorCollection.toArray()[2].toString()  !=	 helper.getSession(false).getAttribute("firstname"))				{
			tutorCollection.toArray()[2].toString()  =	 helper.getSession(false).getAttribute("firstname");
			}				
		IF (tutorCollection.toArray()[3].toString()  !=	 helper.getSession(false).getAttribute("middlename"))				{
			tutorCollection.toArray()[3].toString()  =	 helper.getSession(false).getAttribute("middlename");}			
		IF (tutorCollection.toArray()[4].toString()  !=	 helper.getSession(false).getAttribute("lastname"))					{
			tutorCollection.toArray()[4].toString()  =	 helper.getSession(false).getAttribute("lastname");}			
		IF (tutorCollection.toArray()[5].toString()  !=	 helper.getSession(false).getAttribute("gender"))					{
			tutorCollection.toArray()[5].toString()  =	 helper.getSession(false).getAttribute("gender");}			
		IF (tutorCollection.toArray()[6].toString()  !=	 helper.getSession(false).getAttribute("email"))					{
			tutorCollection.toArray()[6].toString()  =	 helper.getSession(false).getAttribute("email");}			
		IF (tutorCollection.toArray()[7].toString()  !=	 helper.getSession(false).getAttribute("landphonecountrycode"))		{
			tutorCollection.toArray()[7].toString()  =	 helper.getSession(false).getAttribute("landphonecountrycode");}   
		IF (tutorCollection.toArray()[8].toString()  !=	 helper.getSession(false).getAttribute("landphoneareacode"))		{
			tutorCollection.toArray()[8].toString()  =	 helper.getSession(false).getAttribute("landphoneareacode");}	
		IF (tutorCollection.toArray()[9].toString()  !=	 helper.getSession(false).getAttribute("landphonenumber"))			{
			tutorCollection.toArray()[9].toString()  =	 helper.getSession(false).getAttribute("landphonenumber");}	
		IF (tutorCollection.toArray()[10].toString()  !=	 helper.getSession(false).getAttribute("mobilephonecountrycode")){
			tutorCollection.toArray()[10].toString()  =	 helper.getSession(false).getAttribute("mobilephonecountrycode");}
		IF (tutorCollection.toArray()[11].toString()  !=	 helper.getSession(false).getAttribute("mobilephonenetworkcode")){
			tutorCollection.toArray()[11].toString()  =	 helper.getSession(false).getAttribute("mobilephonenetworkcode");}
		IF (tutorCollection.toArray()[12].toString()  !=	 helper.getSession(false).getAttribute("mobilephonenumber"))	{
			tutorCollection.toArray()[12].toString()  =	 helper.getSession(false).getAttribute("mobilephonenumber");}  		
		IF (tutorCollection.toArray()[13].toString()  !=	 helper.getSession(false).getAttribute("description"))			{
			tutorCollection.toArray()[13].toString()  =	 helper.getSession(false).getAttribute("description");}		
		IF (tutorCollection.toArray()[14].toString()  !=	 helper.getSession(false).getAttribute("experience"))			{
			tutorCollection.toArray()[14].toString()  =	 helper.getSession(false).getAttribute("experience");}		
		IF (tutorCollection.toArray()[15].toString()  !=	 helper.getSession(false).getAttribute("weblink"))				{
			tutorCollection.toArray()[15].toString()  =	 helper.getSession(false).getAttribute("weblink");}		
		IF (tutorCollection.toArray()[16].toString()  !=	 helper.getSession(false).getAttribute("facebookurl"))			{
			tutorCollection.toArray()[16].toString()  =	 helper.getSession(false).getAttribute("facebookurl");}		
		IF (tutorCollection.toArray()[17].toString()  !=	 helper.getSession(false).getAttribute("twitterurl"))			{
			tutorCollection.toArray()[17].toString()  =	 helper.getSession(false).getAttribute("twitterurl");}		
		IF (tutorCollection.toArray()[18].toString()  !=	 helper.getSession(false).getAttribute("myspaceurl"))			{
			tutorCollection.toArray()[18].toString()  =	 helper.getSession(false).getAttribute("myspaceurl");}		
		IF (tutorCollection.toArray()[19].toString()  !=	 helper.getSession(false).getAttribute("linkedinurl"))			{
			tutorCollection.toArray()[19].toString()  =	 helper.getSession(false).getAttribute("linkedinurl");}		
		IF (tutorCollection.toArray()[20].toString()  !=	 helper.getSession(false).getAttribute("instagramurl"))			{
			tutorCollection.toArray()[20].toString()  =	 helper.getSession(false).getAttribute("instagramurl");}		
		IF (tutorCollection.toArray()[21].toString()  !=	 helper.getSession(false).getAttribute("vibernumber)")			{
			tutorCollection.toArray()[21].toString()  =	 helper.getSession(false).getAttribute("vibernumber");}		
		IF (tutorCollection.toArray()[22].toString()  !=	 helper.getSession(false).getAttribute("whatsappnumber"))		{
			tutorCollection.toArray()[22].toString()  =	 helper.getSession(false).getAttribute("whatsappnumber");}		
		IF (tutorCollection.toArray()[23].toString()  !=	 helper.getSession(false).getAttribute("address1"))				{
			tutorCollection.toArray()[23].toString()  =	 helper.getSession(false).getAttribute("address1");}		
		IF (tutorCollection.toArray()[24].toString()  !=	 helper.getSession(false).getAttribute("address2"))				{
			tutorCollection.toArray()[24].toString()  =	 helper.getSession(false).getAttribute("address2");}		
		IF (tutorCollection.toArray()[25].toString()  !=	 helper.getSession(false).getAttribute("address3"))				{
			tutorCollection.toArray()[25].toString()  =	 helper.getSession(false).getAttribute("address3");}		
		IF (tutorCollection.toArray()[26].toString()  !=	 helper.getSession(false).getAttribute("town"))					{
			tutorCollection.toArray()[26].toString()  =	 helper.getSession(false).getAttribute("town");}
		
		*/
		
	//}
	

}
