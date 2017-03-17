package com.genesiis.campus.command;

//20170314 CW c148-tutor-verify-hashcode-reset-password-cw CmdTutorPasswordChange class created.
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw comment changed & modified execute method to encrypt & update the password
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw add comments to execute method
//20170316 CW c149-tutor-email-confirmation-for-password-change-cw create fillTutorRequiredFields method & change execute method to send email after password change
							// Add method comments to execute & fillTutorRequiredFields methods, add systemMessage method
//20170317 CW c149-tutor-email-confirmation-for-password-change-cw modify variable declarations to improve code performance & add comments to improve readability

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpTutorDAO;
import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.mail.GenerateEmail;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is used to update the tutor table using the new password entered
 * further it implements ICommand interface
 * @author chinthaka
 */
public class CmdTutorPasswordChange implements ICommand {
	static Logger log = Logger.getLogger(CmdTutorPasswordChange.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";
		String pageURL = "/index.jsp";
		try {
			Tutor tutor = new Tutor();
			
			if(helper.getParameter("code") != null){
				tutor.setCode(Integer.parseInt(helper.getParameter("code")));
			}			
			
			//password encryption 
			Encryptable passwordEncryptor = new TripleDesEncryptor(helper.getParameter("password").trim());
			String encryptPassword = passwordEncryptor.encryptSensitiveDataToString().trim();
			tutor.setPassword(encryptPassword);
			
			//updating the tutor table with the new password
			ICrud passwordRest = new SigningUpTutorDAO();
			int result = passwordRest.update(tutor);
			if (result > 0) {
				// if password updated successfully showing the login screen to login
				message = SystemMessage.PASSWORD_SUCCESS.message();
				pageURL = "/index.jsp?showLogin=true";
				
				// Filling the tutor with the required date to send the email of confirmation
				fillTutorRequiredFields(tutor);

				int status = -3;
				// Sending the tutor password reset confirmation email
				status = new GenerateEmail().sendTutorPasswordChangeConformationEmail(tutor.getFirstName(), 
						tutor.getLastName()	, tutor.getEmailAddress(), tutor.getUsername());
				message = systemMessage(status);
			} else {
				message = SystemMessage.PASSWORD_UNSUCCESS.message();
			}
		} catch (SQLException sqle) {
			log.error("execute(): SQLException " + sqle.toString());
			throw sqle;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}

		helper.setAttribute("message", message);
		helper.setAttribute("pageURL", pageURL);
		return view;
	}
	
	/**
	 * This method used to get the tutor details from database & fill the required fields in tutor to send the email
	 * @author chinthaka
	 * @param tutor
	 * @throws SQLException
	 * @throws Exception
	 */
	private void fillTutorRequiredFields(Tutor tutor) throws SQLException, Exception{

		try {
			ICrud tutorDaoObj = new TutorDAO();
			Collection<Collection<String>> allTutorDetailList = new ArrayList<Collection<String>>();
			allTutorDetailList = tutorDaoObj.findById(tutor);
			
			for (Collection<String> tutorCollection : allTutorDetailList) {
				if(tutorCollection != null && !tutorCollection.isEmpty()){
					//Filling the tutor details with the data found
					Object[] array = tutorCollection.toArray();
					tutor.setFirstName((String) array[2]);	// First Name
					tutor.setLastName((String) array[4]);	// Last name
					tutor.setEmailAddress((String) array[6]);	// Email Address
					tutor.setUsername((String) array[1]);	// username	
				}
			}
		} catch (SQLException sqle) {
			log.error("fillTutorRequiredFields(): SQLException " + sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("fillTutorRequiredFields():Exception " + exp.toString());
			throw exp;
		}
		
	}
	
	/*
	 * systemMessage() handles the system Messages according to the state of the
	 * status passed in
	 * 
	 * @return String the message
	 * 
	 * @param status 3 request submitted successfully.
	 * 
	 * @param status -3 request submission fails.
	 */
	private String systemMessage(int status) {
		String message = SystemMessage.UNKNOWN.message();
		switch (status) {
		case 3:
			message = SystemMessage.MAIL_SUCCESS.message();
			break;
		case -3:
			message = SystemMessage.MAIL_UNSUCCESS.message();
			break;
		default:
			break;
		}
		return message;
	}
}



