package com.genesiis.campus.util.mail;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.validation.SystemMessage;

//20170207 AS CAM-22 ResetPasswordInstructionEmailDispenser created, to send Instructions via email
public class ResetPasswordInstructionEmailDispenser {

	static Logger log = Logger
			.getLogger(ResetPasswordInstructionEmailDispenser.class.getName());

	private EmailDispenser emailDispenser;
	private IEmail generalEmail;
	private Student data;
	private String emailTitle = "Reset password instructions";
	private ArrayList<String> recieversEmailAddreses;
	private String sendersEmail = "";
	private String emailBody = "Someone has requested a link to change your password. You can do this through the Link below.";
	
	
	/*
	 * formatEmailInstance() creates an IEmail with email receiver and sender
	 * addresses, host ,SMTP host, subject, mailBody bounded
	 * 
	 * @author AS
	 * 
	 * @return IEmail formatted Email out put
	 */
	private IEmail formatEmailInstance() {

		addContentToOriginalMailBody("");

		IEmail generalEmail = new GeneralMail(recieversEmailAddreses,
				sendersEmail, emailTitle, emailBody);
		return generalEmail;

	}

	/*
	 * composeSingleEmailList() adds up all the email structured in Collection
	 * of Collections to a monolithic Collection<String>
	 * 
	 * @author AS
	 * 
	 * @param outer accepts Collection<Collection<String>
	 * 
	 * @return email list as a mono ArrayList<String>
	 */

	private ArrayList<String> composeSingleEmailList(
			Collection<Collection<String>> outer) {
		ArrayList<String> monoList = new ArrayList<String>();
		for (Collection<String> emailAddressList : outer) {
			monoList.addAll(emailAddressList);
		}
		return monoList;

	}

	/*
	 * sendMail() method intended to dispense the email
	 * 
	 * @author AS
	 * 
	 * @throws MessagingException in any case dispensing email fails
	 */

	private int sendMail() {
		int MAIL_SENT_STATUS = 3;
		try {
			emailDispenser = new EmailDispenser(generalEmail);
			emailDispenser.emailDispense();

		} catch (IllegalArgumentException illearg) {
			log.error("sendMail():IllegalArgumentException "
					+ illearg.toString());
			MAIL_SENT_STATUS = -3;
		} catch (MessagingException msexp) {
			log.error("sendMail():MessagingException " + msexp.toString());
			MAIL_SENT_STATUS = -3;
		} finally {
			return MAIL_SENT_STATUS;
		}
	}

	/*
	 * addContentToOriginalMailBody() formats the original details with users
	 * credentials e.g email, contact number, full name
	 * 
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private void addContentToOriginalMailBody(String originalMailBody) {
		StringBuilder result = new StringBuilder();

		result.append(originalMailBody);
		result.append(System.getProperty("line.separator"));
		result.append("If you didn't request this, please ignore this email. Your password won't change until you access the link above and create a new one.");
		result.append("Inquiry From : " + sendersEmail);
		result.append(System.getProperty("line.separator"));
	//	result.append(data.getStudentName());
	//	this.inquiry = result.toString();

	}

	/*
	 * systemMessage() handles the system Messages according to the state of the
	 * status passed in
	 * 
	 * @return String the message
	 * 
	 * @param status 3 request submitted successfully.
	 * 
	 * @param status -3 request submition fails.
	 */
	private String systemMessage(int status) {
		String message = SystemMessage.UNKNOWN.message();
		switch (status) {
		case 3:
			message = SystemMessage.PASS_REQUEST_SUBMISSION.message();
			break;
		case -3:
			message = SystemMessage.FAIL_REQUEST_SUBMISSION.message();
			break;
		default:
			break;
		}
		return message;
	}

}
