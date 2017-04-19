package com.genesiis.campus.util.mail;

//20170222 CW c103-send-email-tutor-status-change-cw Created the GenerateEmail class instead of CmdGenerateEmailAdminTutorUpdate.java class to send email
//20170222 CW c103-send-email-tutor-status-change-cw created sendAdminTutorUpdateEmail() to send email at tutor details update by Admin.
//20170224 CW c103-send-email-tutor-status-change - modified systemMessage() method to return MAIL_SUCCESS_TUTOR message
//20170406 CW c157-add-tutor-employment-details-cw add sendTutoremploymentConfirmEmail method
//20170417 CW c157-add-tutor-employment-details-cw sendTutoremploymentConfirmEmail method name changed to sendTutorEmploymentConfirmEmail
//20170418 CW c157-add-tutor-employment-details-cw modified sendTutorEmploymentConfirmEmail method to use TutorEmploymentConfirmEmailComposer class to send email
//20170418 CW c157-add-tutor-employment-details-cw modified setEnvironment method call in sendTutorEmploymentConfirmEmail method
//20170418 CW c157-add-tutor-employment-details-cw modified formatEmailInstance method call to send null String
//20170419 CW c157-add-tutor-employment-details-cw modified sendTutorEmploymentConfirmEmail method & add bccEmailCount variable
//20170419 CW c157-add-tutor-employment-details-cw modified sendTutorEmploymentConfirmEmail method to remove un wanted commented lines & changed error log method name

import com.genesiis.campus.util.TutorEmploymentConfirmEmailComposer;
import com.genesiis.campus.validation.SystemEmail;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

/**
 * GenerateEmail class handles the sending email at various situations
 * Any developer who use this class need to create a method relevant to the email requirement.
 * @author Chinthaka CW
 */
public class GenerateEmail {
	
	static Logger log = Logger.getLogger(GenerateEmail.class.getName());
	
	/*
	 * sendTutorEmploymentConfirmEmail() method handles the email sending at the time of tutor add employment details.
	 * @author CW
	 * @return String
	 * @throws IllegalArgumentException & Exception in any case email sending fails
	 */
	public String sendTutorEmploymentConfirmEmail(String nameOfTutor, ArrayList<String> receiverEmailList, String bccEmail) 
			throws IllegalArgumentException, Exception {
		int status;
		try {
			IEmailComposer tutorEmployentConfirmEmailComposer = new TutorEmploymentConfirmEmailComposer(nameOfTutor);
			String bccEmailCount = "1"; // only one email address to bcc. 
						
			//Used the same email address send to the method as the senders email address & receivers email address. 
			//The senders email address will overridden later from the email address in campus.xml file
			tutorEmployentConfirmEmailComposer.setEnvironment("Administrator", bccEmail, receiverEmailList,
											SystemEmail.SEND_EMPLOYMENT_CONFIRM_EMAIL_BODY1.getSubject(),
											SystemEmail.SEND_EMPLOYMENT_CONFIRM_EMAIL_BODY1.getMailBody(), bccEmailCount, bccEmail);
	
			tutorEmployentConfirmEmailComposer.formatEmailInstance("");
			status = this.sendMail(tutorEmployentConfirmEmailComposer);

		} catch (IllegalArgumentException ilexp) {
			log.error("sendTutorEmploymentConfirmEmail(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("sendTutorEmploymentConfirmEmail():Exception " + exp.toString());
			throw exp;
		}
		return systemMessage(status);	
	}

	/*
	 * systemMessage() handles the system Messages according to
	 * the state of the status passed in
	 * @return String the message
	 * @param status 3 request submitted successfully.
	 * @param status -3 request submission fails.
	 * 
	 */
	private String systemMessage(int status){
		String message = SystemMessage.UNKNOWN.message();
		switch(status){		
		case 3:
			message =SystemMessage.MAIL_SUCCESS_TUTOR.message();
			break;
		case -3:
			message =SystemMessage.MAIL_UNSUCCESS.message();
			break;
		default:			
			break;
		}
		return message;
	}
	
	/*
	 * sendMail() method intended to dispense the email
	 * @author DN
	 * @return int -3 fail sending email 3 sent email successfully 
	 * @throws MessagingException in any case dispensing email fails
	 */
	private int sendMail(IEmailComposer signUpEmailComposer)  {
		int MAIL_SENT_STATUS=3;
		try{ 
			if(signUpEmailComposer.getGeneralEmail()== null){
				Exception exp = new Exception("IEmail is not created ");
				log.error("sendMail(): Exception"+exp.toString());
				throw exp;
			}
				
			signUpEmailComposer.setEmailDispenser(new EmailDispenser(signUpEmailComposer.getGeneralEmail()));
			signUpEmailComposer.getEmailDispenser().emailDispense();
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