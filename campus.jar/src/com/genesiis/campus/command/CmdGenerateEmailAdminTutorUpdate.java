package com.genesiis.campus.command;

//20170219 CW c103-send-email-tutor-status-change-cw Created the CmdGenerateEmailAdminTutorUpdate.java class to send email at tutor details update by Admin.
//20170220 CW c103-send-email-tutor-status-change-cw modified execute method.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.TutorUpdateEmailComposer;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.SystemEmail;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;

import javax.mail.MessagingException;

/**
 * CmdGenerateEmailAdminTutorUpdate class handles sending the email to be sent
 * at the time of tutor details update by Admin.
 * @author Chinthaka CW
 */
public class CmdGenerateEmailAdminTutorUpdate implements ICommand {

	static Logger log = Logger.getLogger(CmdGenerateEmailAdminTutorUpdate.class.getName());
	
	/*
	 * execute() method handles the email sending at the time of tutor details update by Admin.
	 * @author CW
	 * @return view
	 * @throws IllegalArgumentException & Exception in any case email sending fails
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws IllegalArgumentException, Exception {
		try {
			IEmailComposer tutorUpdateEmailComposer = new TutorUpdateEmailComposer(helper);
			int status;
			String recieversName = helper.getParameter("firstname").concat(" " + helper.getParameter("lastname"));
			String sendersEmailAddress = helper.getParameter("email"); // This will overridden later from the email address in campus.xml file
			String recieversEmailAddreses = helper.getParameter("email");			
			
			tutorUpdateEmailComposer.setEnvironment(recieversName, sendersEmailAddress,
					tutorUpdateEmailComposer.composeSingleEmailList(recieversEmailAddreses),
					SystemEmail.SEND_EMAIL_ADMIN_TUTOR_UPDATE_BODY1.getSubject(),
					SystemMessage.SUCCESSFULL_CREATTION.message());

			tutorUpdateEmailComposer.formatEmailInstance(helper.getParameter("username"));
			status = this.sendMail(tutorUpdateEmailComposer);
			helper.setAttribute("message", composeOutStatusMessageToClient(status));

		} catch (IllegalArgumentException ilexp) {
			log.error("execute(): IllegalArgumentException" + ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception " + exp.toString());
			throw exp;
		}
		return view;
	}
	
	/*
	 * composeOutStatusMessageToClient(): composes the system message
	 * according to the int parameter sent in to the method.
	 * @author dushantha DN
	 * @param status int value which maps to a numeric table
	 * that has a meaning of the successfulness or un_successfulness
	 * of the the operation. 
	 */
	private String composeOutStatusMessageToClient(int status){
		return systemMessage(status);
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
			message =SystemMessage.MAIL_SUCCESS.message();
			break;
		case -3:
			message =SystemMessage.MAIL_UNSUCCESS.message();
			break;
		default:			
			break;
		}
		return message;
	}
}