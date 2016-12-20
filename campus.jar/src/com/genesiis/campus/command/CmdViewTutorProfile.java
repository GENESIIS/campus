package com.genesiis.campus.command;

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

public class CmdViewTutorProfile implements ICommand {

	static Logger log = Logger.getLogger(CmdAddTutorProfile.class.getName());
	private String tutorCode = "0";
	private String message = "True";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			final TutorDAO tutorDAO = new TutorDAO();
			final Tutor tutor = new Tutor();
			Collection<Collection<String>> tutorViewCollection = new ArrayList<Collection<String>>();
			// final Validator validator=new Validator();
			tutorCode = helper.getParameter("tutorCode");// validator.validateTutorFields(helper);
			tutor.setCode(Integer.parseInt(tutorCode));
			if (tutorCode != null)
				// if (message.equalsIgnoreCase("True")) {

				tutorViewCollection = tutorDAO.findById(tutor);
			/*
			 * setVariables(helper,tutorCollection);
			 * 
			 * 
			 * tutor.setUsername(tutorCollection.toArray()[0].toString());
			 * tutor.setPassword(tutorCollection.toArray()[1].toString());
			 * tutor.setFirstName(tutorCollection.toArray()[2].toString());
			 * tutor.setMiddleName(tutorCollection.toArray()[3].toString());
			 * tutor.setLastName(tutorCollection.toArray()[4].toString());
			 * tutor.setGender(tutorCollection.toArray()[5].toString());
			 * tutor.setEmailAddress(tutorCollection.toArray()[6].toString());
			 * tutor.setLandCountryCode(tutorCollection.toArray()[7].toString())
			 * ; tutor.setLandAreaCode(tutorCollection.toArray()[8].toString());
			 * tutor.setLandNumber(tutorCollection.toArray()[9].toString());
			 * tutor.setMobileCountryCode(tutorCollection.toArray()[10].toString
			 * ());
			 * tutor.setMobileNetworkCode(tutorCollection.toArray()[11].toString
			 * ());
			 * tutor.setMobileNumber(tutorCollection.toArray()[12].toString());
			 * tutor.setDescription(tutorCollection.toArray()[13].toString());
			 * tutor.setExperience(tutorCollection.toArray()[14].toString());
			 * tutor.setWebLink(tutorCollection.toArray()[15].toString());
			 * tutor.setFacebookLink(tutorCollection.toArray()[16].toString());
			 * tutor.setTwitterNumber(tutorCollection.toArray()[17].toString());
			 * tutor.setMySpaceId(tutorCollection.toArray()[18].toString());
			 * tutor.setLinkedInLink(tutorCollection.toArray()[19].toString());
			 * tutor.setInstagramId(tutorCollection.toArray()[20].toString());
			 * tutor.setViberNumber(tutorCollection.toArray()[21].toString());
			 * tutor.setWhatsAppId(tutorCollection.toArray()[22].toString());
			 * tutor.setAddressLine1(tutorCollection.toArray()[23].toString());
			 * tutor.setAddressLine2(tutorCollection.toArray()[24].toString());
			 * tutor.setAddressLine3(tutorCollection.toArray()[25].toString());
			 * tutor.setTown(tutorCollection.toArray()[26].toString()); //
			 * tutor.setIsApproved(0);
			 * 
			 * 
			 * UserTypeDAO typeOfUser = new UserTypeDAO();
			 * 
			 * tutor.setUsertype(typeOfUser.getCode(UserType.TUTOR_ROLE.name()))
			 * ;
			 * 
			 * int result = tutorDAO.add(tutor); if (result > 0) { message =
			 * SystemMessage.ADDED.message();
			 * 
			 * } else { message = SystemMessage.ERROR.message(); }
			 * 
			 */
//			helper.setAttribute("tutors", tutorViewCollection);
			view.setCollection(tutorViewCollection);

		} catch (Exception exception) {
			log.error("execute() : Exception" + exception.toString());
			throw exception;
		} finally {
			helper.setAttribute("message", message);

		}
		return view;
	}

}
