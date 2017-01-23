package com.genesiis.campus.command;

//20170105 CW c98-send-email-at-tutor-signup-cw Created the CmdGenerateEmailTutorSinUp.java class to send email at tutor signup.
//20170119 CW c125-un-formatted-email-sending-tutor-signup-Add codes from CAM-18 to send dummy email - cw

import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.RowStudentForJason;
import com.genesiis.campus.util.SignUpEmailComposer;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.SystemMail;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdGenerateEmailTutorSinUp implements ICommand {

	static Logger log = Logger.getLogger(CmdAddTutorProfile.class.getName());
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {
		try {
			IEmailComposer signUpEmailComposer = new SignUpEmailComposer();
			int status;
//			String message = "";
//			String gsonData = helper.getParameter("jsonData");
//			this.setPartialStudent((RowStudentForJason) extractDumyObjectFrom(gsonData));
//			String recieversName = partialStudent.getFirstName().concat(" " + partialStudent.getLastName());
			
			String recieversName = helper.getParameter("firstname").concat(" " + helper.getParameter("lastname"));
			String sendersEmailAddress = helper.getParameter("email");
			String recieversEmailAddreses = helper.getParameter("email");			
			
			signUpEmailComposer.setEnvironment(recieversName, sendersEmailAddress,
					signUpEmailComposer.composeSingleEmailList(recieversEmailAddreses),
					SystemMail.SEND_EMAIL_TUTOR_SIGNUP_BODY1.getSubject(),
					SystemMessage.SUCCESSFULL_CREATTION.message());

			signUpEmailComposer.setGeneralEmail(signUpEmailComposer.formatEmailInstance(getMailContent()));
			status = this.sendMail(signUpEmailComposer);
			helper.setAttribute("message", composeOutStatusMessageToClient(status));

		/*} catch (SQLException sexp) {
			log.error("execute(): SQLException " + sexp.toString());
			throw sexp;*/
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
	 * addSpecificContentToOriginalMailBody() formats the original details with
	 * users credentials e.g email, contact number, full name
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private String getMailContent(){
		StringBuilder result = new StringBuilder();		
		result.append(System.getProperty("line.separator"));
		result.append(SystemMail.SEND_EMAIL_TUTOR_SIGNUP_BODY1.getTitle());
		result.append(System.getProperty("line.separator"));
		result.append(SystemMail.SEND_EMAIL_TUTOR_SIGNUP_BODY1.getMailBody());
		result.append(System.getProperty("line.separator"));
		result.append(SystemMail.SEND_EMAIL_TUTOR_SIGNUP_BODY2.getMailBody());
		result.append(System.getProperty("line.separator"));
		result.append(SystemMail.SEND_EMAIL_TUTOR_SIGNUP_BODY3.getMailBody());
		result.append(System.getProperty("line.separator"));
		result.append(SystemMessage.SUPERADMIN_NAME.message());
		return result.toString();
		
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
/*

/**
 * setEnvironment() sets the required fields from the client inputs
 * @param recieversName
 * @param sedersEmailAddress
 * @param recieversEmailAddreses
 * @param mailSubject
 * @param emailBodyText
 * @param restoftheParameters it's a variable argument , it can be omitted as well.
 * if it's set it's users responsibility to set the fields and pass the
 * correct order of the parameters to be set.
 //
void setEnvironment(String recieversName,
		String sedersEmailAddress,
		ArrayList<String> recieversEmailAddreses, 
		String mailSubject,
		String emailBodyText,String ... restoftheParameters);
*/