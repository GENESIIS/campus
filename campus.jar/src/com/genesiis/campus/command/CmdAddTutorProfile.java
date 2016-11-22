package com.genesiis.campus.command;

//20161121 CM c36-add-tutor-information INIT CmdAddTutorProfile.java
//20161121 CM c36-add-tutor-information Modified execute()method. 

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

public class CmdAddTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdAddTutorProfile.class.getName());

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";
		try {
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();

			String username = helper.getParameter("username");
			String password = helper.getParameter("password");
			String firstName = helper.getParameter("firstname");
			String middleName = helper.getParameter("middlename");
			String lastName = helper.getParameter("lastname");
			String gender = helper.getParameter("gender");
			String email = helper.getParameter("email");
			// String imagepath=helper.getParameter("imagepath");
//			String landPhoneCoutnryCode = helper
//					.getParameter("landphoneCountryCode");
//			String landPhoneAreaCode = helper.getParameter("landphoneAreaCode");
			String landPhoneNumber = helper.getParameter("landNumber");
//			String mobileCountryCode = helper.getParameter("mobileCountryCode");
//			String mobileNetworkCode = helper.getParameter("mobileAreaCode");
			String mobileNumber = helper.getParameter("mobileNumber");
			String abouMe = helper.getParameter("aboutMe");
			String experience = helper.getParameter("experience");
			String weblink = helper.getParameter("weblink");
			String facebook = helper.getParameter("facebook");
			String twitter = helper.getParameter("twitter");
			String myspace = helper.getParameter("myspace");
			String linkedIn = helper.getParameter("linkedin");
			String instagram = helper.getParameter("instagram");
 			String viber = helper.getParameter("viber");
			String whatsapp = helper.getParameter("whatsapp");
			String adreess1 = helper.getParameter("address1");
			String adreess2 = helper.getParameter("address2");
			String adreess3 = helper.getParameter("address3");
			String town=helper.getParameter("townDetails");
		
			tutor.setUsername(username);
			tutor.setPassword(password);
			tutor.setFirstName(firstName);
			tutor.setMiddleName(middleName);
			tutor.setLastName(lastName);
			tutor.setGender(gender);
			tutor.setEmail(email);
			tutor.setLandCountryCode("0");
			tutor.setLandAreaCode("0");
			tutor.setLandNumber(landPhoneNumber);
			tutor.setMobileCountryCode("0");
			tutor.setMobileNetworkCode("0");
			tutor.setMobileNumber(mobileNumber);
			tutor.setDescription(abouMe);
			tutor.setExperience(experience);
			tutor.setWebLink(weblink);
			tutor.setFacebook(facebook);
			tutor.setTwitter(twitter);
			tutor.setMySpace(myspace);
			tutor.setLinkedIn(linkedIn);
			tutor.setInstagram(instagram);
			tutor.setViber(viber);
			tutor.setWhatsApp(whatsapp);
			tutor.setAddressLine1(adreess1);
			tutor.setAddressLine2(adreess2);
			tutor.setAddressLine3(adreess3);
			tutor.setImagePath("-");
			tutor.setTown(town);
			tutor.setUsertype(1);
			
			
			int result = tutorDAO.add(tutor);
			if (result > 0) {
				message = SystemMessage.ADDED.message();

			}else{
				message=SystemMessage.ERROR.message();
			}
		} catch (Exception exception) {
			log.error("execute() : " + exception);
			throw exception;
		} finally {
			helper.setAttribute("message", message);

		}
		return view;
	}

	
	
}
