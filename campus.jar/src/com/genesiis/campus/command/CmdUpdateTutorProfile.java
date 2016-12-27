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
	private String message = "True";
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// TODO Auto-generated method stub
		System.out.println("........@ CmdUpdateTutorProfile test..............");

		try {
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			Collection<String> tutorCollection= new ArrayList<String>();
			final Validator validator=new Validator();
			message = validator.validateTutorFields(helper);
			if (message.equalsIgnoreCase("True")) {

				setVariables(helper,tutorCollection);
				
				

				//helper.getSession(false).getAttribute("name");

				
				System.out.println("Code = " + helper.getSession(false).getAttribute("name"));
				//System.out.println("username = " + helper.getAttribute("username"));
				
				tutor.setUsername(tutorCollection.toArray()[0].toString());
				tutor.setPassword(tutorCollection.toArray()[1].toString());
				tutor.setFirstName(tutorCollection.toArray()[2].toString());
				tutor.setMiddleName(tutorCollection.toArray()[3].toString());
				tutor.setLastName(tutorCollection.toArray()[4].toString());
				tutor.setGender(tutorCollection.toArray()[5].toString());
				tutor.setEmailAddress(tutorCollection.toArray()[6].toString());
				tutor.setLandCountryCode(tutorCollection.toArray()[7].toString());
				tutor.setLandAreaCode(tutorCollection.toArray()[8].toString());
				tutor.setLandNumber(tutorCollection.toArray()[9].toString());
				tutor.setMobileCountryCode(tutorCollection.toArray()[10].toString());
				tutor.setMobileNetworkCode(tutorCollection.toArray()[11].toString());
				tutor.setMobileNumber(tutorCollection.toArray()[12].toString());
				tutor.setDescription(tutorCollection.toArray()[13].toString());
				tutor.setExperience(tutorCollection.toArray()[14].toString());
				tutor.setWebLink(tutorCollection.toArray()[15].toString());
				tutor.setFacebookLink(tutorCollection.toArray()[16].toString());
				tutor.setTwitterNumber(tutorCollection.toArray()[17].toString());
				tutor.setMySpaceId(tutorCollection.toArray()[18].toString());
				tutor.setLinkedInLink(tutorCollection.toArray()[19].toString());
				tutor.setInstagramId(tutorCollection.toArray()[20].toString());
				tutor.setViberNumber(tutorCollection.toArray()[21].toString());
				tutor.setWhatsAppId(tutorCollection.toArray()[22].toString());
				tutor.setAddressLine1(tutorCollection.toArray()[23].toString());
				tutor.setAddressLine2(tutorCollection.toArray()[24].toString());
				tutor.setAddressLine3(tutorCollection.toArray()[25].toString());
				tutor.setTown(tutorCollection.toArray()[26].toString());
			//	tutor.setIsApproved(0);

				
				UserTypeDAO typeOfUser = new UserTypeDAO();

				tutor.setUsertype(typeOfUser.getCode(UserType.TUTOR_ROLE.name()));   

					int result = tutorDAO.update(tutor);
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
	 * setEnvironment() method initializes all the instance variable
	 * 
	 * @author CM
	 * 
	 * @param helper IDataHelper
	 */

//	public void setVariables(IDataHelper helper, Collection<Collection<String>> tutorCollection)
	public void setVariables(IDataHelper helper, Collection<String> tutorCollection) {
		try {
			
			tutorCollection.add(helper.getParameter("username"));
			tutorCollection.add(helper.getParameter("password"));
			tutorCollection.add(helper.getParameter("firstname"));
			
			if (helper.getParameter("middlename").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("middlename"));
			}
			
			tutorCollection.add(helper.getParameter("lastname"));
			tutorCollection.add(helper.getParameter("gender"));
			tutorCollection.add(helper.getParameter("email"));
			tutorCollection.add(helper.getParameter("countryDetails"));
			tutorCollection.add(helper.getParameter("landAreaCode"));
			tutorCollection.add(helper.getParameter("landNumber"));
			tutorCollection.add(helper.getParameter("countryDetails"));
			tutorCollection.add(helper.getParameter("mobileNetworkCode"));
			tutorCollection.add(helper.getParameter("mobileNumber"));
			
			if (helper.getParameter("aboutMe").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("aboutMe"));
			}

			if (helper.getParameter("experience").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("experience"));
			}
			
			if (helper.getParameter("weblink").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("weblink"));
			}

			if (helper.getParameter("facebook").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("facebook"));
			}

			if (helper.getParameter("twitter").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("twitter"));
			}

			if (helper.getParameter("myspace").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("myspace"));
			}

			if (helper.getParameter("linkedin").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("linkedin"));
			}

			if (helper.getParameter("instagram").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("instagram"));
			}
			
			if (helper.getParameter("viber").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("viber"));
			}

			if (helper.getParameter("whatsapp").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("whatsapp"));
			}
			
			tutorCollection.add(helper.getParameter("address1"));
			if (helper.getParameter("address2").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("address2"));
			}

			if (helper.getParameter("address3").equals("")) {
				tutorCollection.add("-");
			} else {
				tutorCollection.add(helper.getParameter("address3"));
			}
			
			tutorCollection.add(helper.getParameter("townDetails"));

		} catch (Exception e) {
			log.error("setVariables() : Exception" + e.toString());
			throw e;
		}
	}

}
