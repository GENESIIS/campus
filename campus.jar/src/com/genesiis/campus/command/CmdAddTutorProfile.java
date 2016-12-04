package com.genesiis.campus.command;

//20161121 CM c36-add-tutor-information INIT CmdAddTutorProfile.java
//20161121 CM c36-add-tutor-information Modified execute()method. 

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.UserTypeDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.validation.Validator;

public class CmdAddTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdAddTutorProfile.class.getName());

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String experience;
	private String description;
	private String countryCode;
	private String mobileCountryCode;
	private String mobileNetworkCode;
	private String mobileNumber;
	private String landCountryCode;
	private String landAreaCode;
	private String landNumber;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String webLink;
	private String facebook;
	private String linkedIn;
	private String twitter;
	private String instagram;
	private String MySpace;
	private String whatsApp;
	private String viber;
	private String email;
	//private String imagePath;
	private String username;
	private String password;
	private String town;
	private int usertype;
	private int isActive;
	private String message = "True";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		try {
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			final Validator validator=new Validator();
			message = validator.validateTutorFields(helper);
			if (message.equalsIgnoreCase("True")) {

				setVariables(helper);

				tutor.setUsername(username);
				tutor.setPassword(password);
				tutor.setFirstName(firstName);
				tutor.setMiddleName(middleName);
				tutor.setLastName(lastName);
				tutor.setGender(gender);
				tutor.setEmailAddress(email);
				tutor.setLandCountryCode(landCountryCode);
				tutor.setLandAreaCode("0");
				tutor.setLandNumber(landNumber);
				tutor.setMobileCountryCode(mobileCountryCode);
				tutor.setMobileNetworkCode("0");
				tutor.setMobileNumber(mobileNumber);
				tutor.setDescription(description);
				tutor.setExperience(experience);
				tutor.setWebLink(webLink);
				tutor.setFacebookLink(facebook);
				tutor.setTwitterNumber(twitter);
				tutor.setMySpaceId(MySpace);
				tutor.setLinkedInLink(linkedIn);
				tutor.setInstagramId(instagram);
				tutor.setViberNumber(viber);
				tutor.setWhatsAppId(whatsApp);
				tutor.setAddressLine1(addressLine1);
				tutor.setAddressLine2(addressLine2);
				tutor.setAddressLine3(addressLine3);
				//tutor.setImagePath("-");
				tutor.setTown(town);
				
				UserTypeDAO typeOfUser = new UserTypeDAO();

				tutor.setUsertype(typeOfUser.getCode(UserType.TUTOR_ROLE.name()));   

					int result = tutorDAO.add(tutor);
					if (result > 0) {
						message = SystemMessage.ADDED.message();

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
	 * setEnvironment() method initializes all the instance variable
	 * 
	 * @author CM
	 * 
	 * @param helper IDataHelper
	 */
	public void setVariables(IDataHelper helper) {
		try {
			username = helper.getParameter("username");
			password = helper.getParameter("password");
			firstName = helper.getParameter("firstname");
			lastName = helper.getParameter("lastname");
			gender = helper.getParameter("gender");
			email = helper.getParameter("email");
			landCountryCode = helper.getParameter("countryDetails");
			landNumber = helper.getParameter("landNumber");
			mobileCountryCode = helper.getParameter("countryDetails");
			mobileNumber = helper.getParameter("mobileNumber");
			addressLine1 = helper.getParameter("address1");
			town = helper.getParameter("townDetails");
			String a = helper.getParameter("aboutMe");
			if (helper.getParameter("middlename").equals("")) {
				middleName = "-";
			} else {
				middleName = helper.getParameter("middlename");
			}

			if (helper.getParameter("aboutMe").equals("")) {
				description = "-";
			} else {
				description = helper.getParameter("aboutMe");
			}

			if (helper.getParameter("experience").equals("")) {
				experience = "-";
			} else {
				experience = helper.getParameter("experience");
			}

			if (helper.getParameter("weblink").equals("")) {
				webLink = "-";
			} else {
				webLink = helper.getParameter("weblink");
			}

			if (helper.getParameter("facebook").equals("")) {
				facebook = "-";
			} else {
				facebook = helper.getParameter("facebook");
			}

			if (helper.getParameter("twitter").equals("")) {
				twitter = "-";
			} else {
				twitter = helper.getParameter("twitter");
			}

			if (helper.getParameter("myspace").equals("")) {
				MySpace = "-";
			} else {
				MySpace = helper.getParameter("myspace");
			}

			if (helper.getParameter("linkedin").equals("")) {
				linkedIn = "-";
			} else {
				linkedIn = helper.getParameter("linkedin");
			}

			if (helper.getParameter("instagram").equals("")) {
				instagram = "-";
			} else {
				instagram = helper.getParameter("instagram");
			}

			if (helper.getParameter("whatsapp").equals("")) {
				whatsApp = "-";
			} else {
				whatsApp = helper.getParameter("whatsapp");
			}

			if (helper.getParameter("viber").equals("")) {
				viber = "-";
			} else {
				viber = helper.getParameter("viber");
			}

			if (helper.getParameter("address2").equals("")) {
				addressLine2 = "-";
			} else {
				addressLine2 = helper.getParameter("address2");
			}

			if (helper.getParameter("address3").equals("")) {
				addressLine3 = "-";
			} else {
				addressLine3 = helper.getParameter("address3");
			}
		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
	}
}
