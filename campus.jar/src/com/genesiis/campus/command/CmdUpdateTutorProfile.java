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
			
			////System.out.println("Code = " + helper.getSession(false).getAttribute("code"));
			tutor.setUsername(helper.getParameter("username"));
			
			if(!((helper.getParameter("password")).equals(helper.getSession(false).getAttribute("password")))){				
				tutor.setPassword(helper.getSession(false).getAttribute("password").toString());
				updated = true;
			}else{
				tutor.setPassword(helper.getParameter("password"));
			}
			
			if(!((helper.getParameter("firstname")).equals(helper.getSession(false).getAttribute("firstname")))){				
				tutor.setFirstName(helper.getSession(false).getAttribute("firstname").toString());
				updated = true;
			}else{
				tutor.setFirstName(helper.getParameter("firstname"));
			}
			
			//tutor.setFirstName(helper.getParameter("firstname"));
			//Validator.isNotEmpty(helper.getParameter("middlename")) && Validator.isNotEmpty(helper.getSession(false).getAttribute("middlename").toString())) ||
			
			if(Validator.isNotEmpty(helper.getParameter("middlename"))){
				if(!((helper.getParameter("middlename")).equals(helper.getSession(false).getAttribute("middlename")))){	
					tutor.setMiddleName(helper.getSession(false).getAttribute("middlename").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("middlename").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("middlename").toString())){
					tutor.setMiddleName(helper.getSession(false).getAttribute("middlename").toString());
				}else {
					tutor.setMiddleName("-");
				}
				updated = true;
			}
			
		/*	if (helper.getParameter("middlename").equals("")) {
				tutor.setMiddleName("-");
			} else {
				tutor.setMiddleName(helper.getParameter("middlename"));
			}*/
			
			if(!((helper.getParameter("lastname")).equals(helper.getSession(false).getAttribute("lastname")))){				
				tutor.setLastName(helper.getSession(false).getAttribute("lastname").toString());
				updated = true;
			}else{
				tutor.setLastName(helper.getParameter("lastname"));
			}
			
			//tutor.setLastName(helper.getParameter("lastname"));
			

			if(!((helper.getParameter("gender")).equals(helper.getSession(false).getAttribute("gender")))){				
				tutor.setGender(helper.getSession(false).getAttribute("gender").toString());
				updated = true;
			}else{
				tutor.setGender(helper.getParameter("gender"));
			}
		//	tutor.setGender(helper.getParameter("gender"));

			if(!((helper.getParameter("email")).equals(helper.getSession(false).getAttribute("email")))){				
				tutor.setEmailAddress(helper.getSession(false).getAttribute("email").toString());
				updated = true;
			}else{
				tutor.setEmailAddress(helper.getParameter("email"));
			}
			//tutor.setEmailAddress(helper.getParameter("email"));

			if(!((helper.getParameter("countryDetails")).equals(helper.getSession(false).getAttribute("landphonecountrycode")))){				
				tutor.setLandCountryCode(helper.getSession(false).getAttribute("landphonecountrycode").toString());
				updated = true;
			}else{
				tutor.setLandCountryCode(helper.getParameter("pacountryDetailsssword"));
			}
			//tutor.setLandCountryCode(helper.getParameter("countryDetails"));

			if(!((helper.getParameter("landAreaCode")).equals(helper.getSession(false).getAttribute("landphoneareacode")))){				
				tutor.setLandAreaCode(helper.getSession(false).getAttribute("landphoneareacode").toString());
				updated = true;
			}else{
				tutor.setLandAreaCode(helper.getParameter("landAreaCode"));
			}
			//tutor.setLandAreaCode(helper.getParameter("landAreaCode"));

			if(!((helper.getParameter("landNumber")).equals(helper.getSession(false).getAttribute("landphonenumber")))){				
				tutor.setLandNumber(helper.getSession(false).getAttribute("landphonenumber").toString());
				updated = true;
			}else{
				tutor.setLandNumber(helper.getParameter("landNumber"));
			}
			//tutor.setLandNumber(helper.getParameter("landNumber"));

			if(!((helper.getParameter("countryDetails")).equals(helper.getSession(false).getAttribute("mobilephonecountrycode")))){				
				tutor.setMobileCountryCode(helper.getSession(false).getAttribute("mobilephonecountrycode").toString());
				updated = true;
			}else{
				tutor.setMobileCountryCode(helper.getParameter("countryDetails"));
			}
			//tutor.setMobileCountryCode(helper.getParameter("countryDetails"));

			if(!((helper.getParameter("mobileNetworkCode")).equals(helper.getSession(false).getAttribute("mobilephonenetworkcode")))){				
				tutor.setMobileNetworkCode(helper.getSession(false).getAttribute("mobilephonenetworkcode").toString());
				updated = true;
			}else{
				tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));
			}
			//tutor.setMobileNetworkCode(helper.getParameter("mobileNetworkCode"));

			if(!((helper.getParameter("mobileNumber")).equals(helper.getSession(false).getAttribute("mobilephonenumber")))){				
				tutor.setMobileNumber(helper.getSession(false).getAttribute("mobilephonenumber").toString());
				updated = true;
			}else{
				tutor.setMobileNumber(helper.getParameter("mobileNumber"));
			}
			//tutor.setMobileNumber(helper.getParameter("mobileNumber"));
			
			if(Validator.isNotEmpty(helper.getParameter("aboutMe"))){
				if(!((helper.getParameter("aboutMe")).equals(helper.getSession(false).getAttribute("description")))){	
					tutor.setDescription(helper.getSession(false).getAttribute("description").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("description").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("description").toString())){
					tutor.setDescription(helper.getSession(false).getAttribute("description").toString());
				}else {
					tutor.setDescription("-");
				}
				updated = true;
			}
		/*	
			if (helper.getParameter("aboutMe").equals("")) {
				tutor.setDescription("-");
			} else {
				tutor.setDescription(helper.getParameter("aboutMe"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("experience"))){
				if(!((helper.getParameter("experience")).equals(helper.getSession(false).getAttribute("experience")))){	
					tutor.setExperience(helper.getSession(false).getAttribute("experience").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("experience").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("experience").toString())){
					tutor.setExperience(helper.getSession(false).getAttribute("experience").toString());
				}else {
					tutor.setExperience("-");
				}
				updated = true;
			}
			
			/*if (helper.getParameter("experience").equals("")) {
				tutor.setExperience("-");
			} else {
				tutor.setExperience(helper.getParameter("experience"));
			}*/
			

			if(Validator.isNotEmpty(helper.getParameter("weblink"))){
				if(!((helper.getParameter("weblink")).equals(helper.getSession(false).getAttribute("weblink")))){	
					tutor.setWebLink(helper.getSession(false).getAttribute("weblink").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("weblink").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("weblink").toString())){
					tutor.setWebLink(helper.getSession(false).getAttribute("weblink").toString());
				}else {
					tutor.setWebLink("-");
				}
				updated = true;
			}
		/*	if (helper.getParameter("weblink").equals("")) {
				tutor.setWebLink("-");
			} else {
				tutor.setWebLink(helper.getParameter("weblink"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("facebook"))){
				if(!((helper.getParameter("facebook")).equals(helper.getSession(false).getAttribute("facebookurl")))){	
					tutor.setFacebookLink(helper.getSession(false).getAttribute("facebookurl").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("facebookurl").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("facebookurl").toString())){
					tutor.setFacebookLink(helper.getSession(false).getAttribute("facebookurl").toString());
				}else {
					tutor.setFacebookLink("-");
				}
				updated = true;
			}
		/*	if (helper.getParameter("facebook").equals("")) {
				tutor.setFacebookLink("-");
			} else {
				tutor.setFacebookLink(helper.getParameter("facebook"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("twitter"))){
				if(!((helper.getParameter("twitter")).equals(helper.getSession(false).getAttribute("twitterurl")))){	
					tutor.setTwitterNumber(helper.getSession(false).getAttribute("twitterurl").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("twitterurl").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("twitterurl").toString())){
					tutor.setTwitterNumber(helper.getSession(false).getAttribute("twitterurl").toString());
				}else {
					tutor.setTwitterNumber("-");
				}
				updated = true;
			}
			/*if (helper.getParameter("twitter").equals("")) {
				tutor.setTwitterNumber("-");
			} else {
				tutor.setTwitterNumber(helper.getParameter("twitter"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("myspace"))){
				if(!((helper.getParameter("myspace")).equals(helper.getSession(false).getAttribute("myspaceurl")))){	
					tutor.setMySpaceId(helper.getSession(false).getAttribute("myspaceurl").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("myspaceurl").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("myspaceurl").toString())){
					tutor.setMySpaceId(helper.getSession(false).getAttribute("myspaceurl").toString());
				}else {
					tutor.setMySpaceId("-");
				}
				updated = true;
			}
			
		/*	if (helper.getParameter("myspace").equals("")) {
				tutor.setMySpaceId("-");
			} else {
				tutor.setMySpaceId(helper.getParameter("myspace"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("linkedin"))){
				if(!((helper.getParameter("linkedin")).equals(helper.getSession(false).getAttribute("linkedinurl")))){	
					tutor.setLinkedInLink(helper.getSession(false).getAttribute("linkedinurl").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("linkedinurl").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("linkedinurl").toString())){
					tutor.setLinkedInLink(helper.getSession(false).getAttribute("linkedinurl").toString());
				}else {
					tutor.setLinkedInLink("-");
				}
				updated = true;
			}
		/*	if (helper.getParameter("linkedin").equals("")) {
				tutor.setLinkedInLink("-");
			} else {
				tutor.setLinkedInLink(helper.getParameter("linkedin"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("instagram"))){
				if(!((helper.getParameter("instagram")).equals(helper.getSession(false).getAttribute("instagramurl")))){	
					tutor.setInstagramId(helper.getSession(false).getAttribute("instagramurl").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("instagramurl").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("instagramurl").toString())){
					tutor.setInstagramId(helper.getSession(false).getAttribute("instagramurl").toString());
				}else {
					tutor.setInstagramId("-");
				}
				updated = true;
			}
/*			if (helper.getParameter("instagram").equals("")) {
				tutor.setInstagramId("-");
			} else {
				tutor.setInstagramId(helper.getParameter("instagram"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("viber"))){
				if(!((helper.getParameter("viber")).equals(helper.getSession(false).getAttribute("vibernumber")))){	
					tutor.setViberNumber(helper.getSession(false).getAttribute("vibernumber").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("vibernumber").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("vibernumber").toString())){
					tutor.setViberNumber(helper.getSession(false).getAttribute("vibernumber").toString());
				}else {
					tutor.setViberNumber("-");
				}
				updated = true;
			}
			
/*			if (helper.getParameter("viber").equals("")) {
				tutor.setViberNumber("-");
			} else {
				tutor.setViberNumber(helper.getParameter("viber"));
			}
*/
			if(Validator.isNotEmpty(helper.getParameter("whatsapp"))){
				if(!((helper.getParameter("whatsapp")).equals(helper.getSession(false).getAttribute("whatsappnumber")))){	
					tutor.setWhatsAppId(helper.getSession(false).getAttribute("whatsappnumber").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("whatsappnumber").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("whatsappnumber").toString())){
					tutor.setWhatsAppId(helper.getSession(false).getAttribute("whatsappnumber").toString());
				}else {
					tutor.setWhatsAppId("-");
				}
				updated = true;
			}
	/*		if (helper.getParameter("whatsapp").equals("")) {
				tutor.setWhatsAppId("-");
			} else {
				tutor.setWhatsAppId(helper.getParameter("whatsapp"));
			}*/
			
			if(!((helper.getParameter("address1")).equals(helper.getSession(false).getAttribute("address1")))){				
				tutor.setAddressLine1(helper.getSession(false).getAttribute("address1").toString());
				updated = true;
			}else{
				tutor.setAddressLine1(helper.getParameter("address1"));
			}
/*			tutor.setAddressLine1(helper.getParameter("address1"));*/

			if(Validator.isNotEmpty(helper.getParameter("address2"))){
				if(!((helper.getParameter("address2")).equals(helper.getSession(false).getAttribute("address2")))){	
					tutor.setAddressLine2(helper.getSession(false).getAttribute("address2").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("address2").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("address2").toString())){
					tutor.setAddressLine2(helper.getSession(false).getAttribute("address2").toString());
				}else {
					tutor.setAddressLine2("-");
				}
				updated = true;
			}
/*			if (helper.getParameter("address2").equals("")) {
				tutor.setAddressLine2("-");
			} else {
				tutor.setAddressLine2(helper.getParameter("address2"));
			}*/

			if(Validator.isNotEmpty(helper.getParameter("address3"))){
				if(!((helper.getParameter("address3")).equals(helper.getSession(false).getAttribute("address3")))){	
					tutor.setAddressLine3(helper.getSession(false).getAttribute("address3").toString());	
					updated = true;		
				}else{
					tutor.setMiddleName(helper.getSession(false).getAttribute("address3").toString());	
				}
			}else{
				if(Validator.isNotEmpty(helper.getSession(false).getAttribute("address3").toString())){
					tutor.setAddressLine3(helper.getSession(false).getAttribute("address3").toString());
				}else {
					tutor.setAddressLine3("-");
				}
				updated = true;
			}
/*			if (helper.getParameter("address3").equals("")) {
				tutor.setAddressLine3("-");
			} else {
				tutor.setAddressLine3(helper.getParameter("address3"));
			}*/

			if(!((helper.getParameter("townDetails")).equals(helper.getSession(false).getAttribute("town")))){	
				System.out.println(" helper town = " + helper.getSession(false).getAttribute("town"));
				System.out.println(" helper town code = " + helper.getSession(false).getAttribute("towncode"));
				System.out.println(" helper town code string= " + helper.getSession(false).getAttribute("towncode").toString());
				tutor.setTown(helper.getSession(false).getAttribute("towncode").toString());
				updated = true;
			}else{
				tutor.setTown(helper.getParameter("townDetails"));
			}
			//tutor.setTown(helper.getParameter("townDetails"));

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
