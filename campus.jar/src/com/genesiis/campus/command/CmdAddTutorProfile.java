package com.genesiis.campus.command;

//20161121 CM c36-add-tutor-information INIT CmdAddTutorProfile.java
//20161121 CM c36-add-tutor-information Modified execute()method. 
//20161216 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary variable declarations. 
//20161221 CW c36-add-tutor-details Modified execute() & setVariables() methods - removed unnecessary code repetitions. 
//20170105 CW c98-send-email-at-tutor-signup Modified execute() add email sending at tutor signup

import java.sql.SQLException;
import java.util.ArrayList;
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
			message = validator.validateTutorFields(helper);
			if (message.equalsIgnoreCase("True")) {
								
				setVariables(helper,tutor);

				UserTypeDAO typeOfUser = new UserTypeDAO();

				tutor.setUsertype(typeOfUser.getCode(UserType.TUTOR_ROLE.name()));   

					int result = tutorDAO.add(tutor);
					if (result > 0) {
						message = SystemMessage.ADDED.message();
						//ICommand emailSignUp = new CmdGenerateEmailSinUp();
						ICommand emailSignUp = new CmdGenerateEmailTutorSinUp();
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
				tutor.setViberNumber("-");
			} else {
				tutor.setViberNumber(helper.getParameter("viber"));
			}

			if (helper.getParameter("whatsapp").equals("")) {
				tutor.setWhatsAppId("-");
			} else {
				tutor.setWhatsAppId(helper.getParameter("whatsapp"));
			}
			
			tutor.setIsApproved(false);
			
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
			
			tutor.setTown(helper.getParameter("townDetails"));

		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
	}
}
