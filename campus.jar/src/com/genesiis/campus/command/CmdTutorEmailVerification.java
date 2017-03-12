package com.genesiis.campus.command;

//20170307 CW c147-tutor-reset-password-cw CmdTutorEmailVerification class created.
//20170311 CW c147-tutor-reset-password-cw modified execute method to get the data from the database for given email
//20170312 CW c147-tutor-reset-password-cw add systemMessage method to send the message properly
				// modified execute method to send the email with hashcode 

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.TutorEmailVerificationDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.scrypt.crypto.HashCodeBuilder;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.mail.GenerateEmail;
import com.genesiis.campus.validation.LoginValidator;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdTutorEmailVerification implements ICommand {
	
	static Logger log = Logger.getLogger(CmdTutorEmailVerification.class.getName());

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		int status = -3;
		String message = null;
		String firstName = "";
		String lastName = "";
		String email = "";
		String uname = "";
		String code = "";
		try {
			GenerateEmail tutorResetPasswordEmail = new GenerateEmail();
			Tutor tutor = new Tutor();
			tutor.setEmailAddress(helper.getParameter("email"));
			boolean validEmail = LoginValidator.validateEmail(tutor.getEmailAddress());
			if (validEmail) {
				ICrud emailVarifyDAO = new TutorEmailVerificationDAO();
				Collection<Collection<String>> emailCollection = new ArrayList<Collection<String>>();
				emailCollection = emailVarifyDAO.findById(tutor);

				for (Collection<String> collection : emailCollection) {
					if(collection != null && !collection.isEmpty()){
						Object[] array = collection.toArray();
						tutor.setFirstName((String) array[0]);
						tutor.setLastName((String) array[1]);
						tutor.setEmailAddress((String) array[2]);
						tutor.setUsername((String) array[3]);
						
						code = (String) array[4];
						if(code != null){
							tutor.setCode(Integer.parseInt(code));
						}						
					}else{
						message = SystemMessage.INVALID_EMAIL.message();
					}
				}

				if (message == null){
					HashCodeBuilder hashBuilder = new HashCodeBuilder();
					String securedHash = hashBuilder.createHash(tutor.getFirstName(), tutor.getLastName());
					tutor.setHashCode(securedHash);

					int updateData = emailVarifyDAO.update(tutor);
					if (updateData > 0) {
						message = SystemMessage.HASHCODES.message();
						status = tutorResetPasswordEmail.sendTutorResetPasswordVerificationEmail(tutor.getFirstName(), 
								tutor.getLastName()	, tutor.getEmailAddress(), tutor.getUsername(), tutor.getHashCode());
					} else {
						message = SystemMessage.HASHCODEUNS.message();
					}
					message = systemMessage(status);
				}
			} else {
				message = SystemMessage.INVALID_EMAIL.message();
			}
		} catch (SQLException sexp) {
			log.error("execute(): SQLException " + sexp.toString());
			throw sexp;
		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}
		helper.setAttribute("message", message);
		return view;
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
