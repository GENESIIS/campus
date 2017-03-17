package com.genesiis.campus.util.mail;

//20170307 CW c147-tutor-reset-password-cw Class copied from c103-send-email-tutor-status-change-cw.
				//add sendTutorResetPasswordVerificationEmail() method
//20170312 CW c147-tutor-reset-password-cw modified sendTutorResetPasswordVerificationEmail method return type to int
				//modified sendMail method to sent the tutor reset password email using tutor details
//20170313 CW c147-tutor-reset-password-cw modified sendTutorResetPasswordVerificationEmail method comment
				// Removed systemMessage(int status) method
//20170316 CW c149-tutor-email-confirmation-for-password-change-cw sendTutorPasswordChangeConformationEmail method created 
				// modified sendMail(IEmailComposer tutorUpdateEmailComposer) parameter to emailComposer
				// modified sendTutorResetPasswordVerificationEmail method error logs
//20170316 CW c149-tutor-email-confirmation-for-password-change-cw removed unwanted commented lines

import com.genesiis.campus.util.TutorPasswordChangeConfirmationEmailComposer;
import com.genesiis.campus.util.TutorPasswordResetEmailComposer;
import com.genesiis.campus.validation.SystemEmail;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import javax.mail.MessagingException;

/**
 * GenerateEmail class handles the sending email at various situations
 * Any developer who use this class need to create a method relevant to the email requirement.
 * @author Chinthaka CW
 */
public class GenerateEmail {
static Logger log = Logger.getLogger(GenerateEmail.class.getName());
	
	/*
	 * sendTutorResetPasswordVerificationEmail() method handles the email sending at the time of tutor reset password.
	 * @author CW
	 * @return int
	 * @throws IllegalArgumentException & Exception in any case email sending fails
	 */
	public int sendTutorResetPasswordVerificationEmail(String firstname, String lastname, String emailAddress, String username, String hashCode) 
			throws IllegalArgumentException, Exception {
		int status;
		try {
			IEmailComposer tutorResetPasswordEmailComposer = new TutorPasswordResetEmailComposer(hashCode);
			
			String recieversName = firstname.concat(" " + lastname);
			
			//Used the same email address send to the method as the senders email address & receivers email address. 
			//The senders email address will overridden later from the email address in campus.xml file
			tutorResetPasswordEmailComposer.setEnvironment(recieversName, emailAddress,
					tutorResetPasswordEmailComposer.composeSingleEmailList(emailAddress),
					SystemEmail.SEND_EMAIL_TUTOR_RESET_PASSWORD_BODY1.getSubject(),
					SystemMessage.SUCCESSFULL_CREATION.message());
	
			tutorResetPasswordEmailComposer.formatEmailInstance(username);
			status = this.sendMail(tutorResetPasswordEmailComposer);

	} catch (IllegalArgumentException ilexp) {
		log.error("sendTutorResetPasswordVerificationEmail(): IllegalArgumentException" + ilexp.toString());
		throw ilexp;
	} catch (Exception exp) {
		log.error("sendTutorResetPasswordVerificationEmail():Exception " + exp.toString());
		throw exp;
	}
	return status;	
	}
	
	/*
	 * sendTutorPasswordChangeConformationEmail() method handles the email sending at the time of tutor password change confirmation.
	 * @author CW
	 * @return int
	 * @throws IllegalArgumentException & Exception in any case email sending fails
	 */
	public int sendTutorPasswordChangeConformationEmail(String firstname, String lastname, String emailAddress, String username) 
			throws IllegalArgumentException, Exception {
		int status;
		try {
			IEmailComposer tutorPasswordChangeEmailComposer = new TutorPasswordChangeConfirmationEmailComposer();
			
			String recieversName = firstname.concat(" " + lastname);
			
			//Used the same email address send to the method as the senders email address & receivers email address. 
			//The senders email address will overridden later from the email address in campus.xml file
			tutorPasswordChangeEmailComposer.setEnvironment(recieversName, emailAddress,
					tutorPasswordChangeEmailComposer.composeSingleEmailList(emailAddress),
					SystemEmail.SEND_EMAIL_TUTOR_RESET_PASSWORD_BODY1.getSubject(),
					SystemMessage.SUCCESSFULL_CREATION.message());
	
			tutorPasswordChangeEmailComposer.formatEmailInstance(username);
			status = this.sendMail(tutorPasswordChangeEmailComposer);

	} catch (IllegalArgumentException ilexp) {
		log.error("sendTutorPasswordChangeConformationEmail(): IllegalArgumentException" + ilexp.toString());
		throw ilexp;
	} catch (Exception exp) {
		log.error("sendTutorPasswordChangeConformationEmail():Exception " + exp.toString());
		throw exp;
	}
	return status;	
	}
	
	/*
	 * sendMail() method intended to dispense the email
	 * @author DN
	 * @return int -3 fail sending email 3 sent email successfully 
	 * @throws MessagingException in any case dispensing email fails
	 */
	private int sendMail(IEmailComposer emailComposer)  {
		int MAIL_SENT_STATUS=3;
		try{ 
			if(emailComposer.getGeneralEmail()== null){
				Exception exp = new Exception("IEmail is not created ");
				log.error("sendMail(): Exception"+exp.toString());
				throw exp;
			}
				
			emailComposer.setEmailDispenser(new EmailDispenser(emailComposer.getGeneralEmail()));
			emailComposer.getEmailDispenser().emailDispense();
		} catch (IllegalArgumentException illearg){
			log.error("sendMail():IllegalArgumentException "+illearg.toString());
			MAIL_SENT_STATUS= -3;
		} catch (MessagingException msexp) {
			log.error("sendMail():MessagingException "+msexp.toString());
			MAIL_SENT_STATUS= -3;
		} catch (NullPointerException msexp) {
			log.error("sendMail():NullPointerException "+msexp.toString());
			MAIL_SENT_STATUS= -3;
		} catch(Exception exp){
			MAIL_SENT_STATUS= -3;
		}
		finally{
			return MAIL_SENT_STATUS;
		}		
	}
}
