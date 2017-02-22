package com.genesiis.campus.util;

//20170207 AS CAM-22 ResetPasswordInstructionEmailDispenser created, to send Instructions via email
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.SystemMessage;

public class ResetPasswordInstructionEmailDispenser implements IEmailComposer {

	static Logger log = Logger
			.getLogger(ResetPasswordInstructionEmailDispenser.class.getName());

	private EmailDispenser emailDispenser;
	private IEmail generalEmail;
	private String recieversName;
	private String sendersEmailAddress;
	private ArrayList<String> recieversEmailAddreses;
	private String mailingSubject = "Reset password instructions";
	private String mailBody = "Someone has requested a link to change your password. You can do this through the Link below.";

	/*
	 * formatEmailInstance() creates an IEmail with email receiver and sender
	 * addresses, host ,SMTP host, subject, mailBody bounded
	 */

	@Override
	public IEmail formatEmailInstance(String mailBodyForSpecificMail)
			throws IllegalArgumentException {
		try {
			addContentToOriginalMailBody(mailBodyForSpecificMail);

			if (getRecieversEmailAddreses() == null
					|| getRecieversEmailAddreses().size() == 0) {
				throw new IllegalArgumentException(
						"genRecieversEmailAddreses list is empty or undefined");
			} else {
				IEmail generalEmail = new GeneralMail(
						getRecieversEmailAddreses(), getSendersEmailAddress(),
						getMailingSubject(), getMailBody());
				this.setGeneralEmail(generalEmail);
			}
		} catch (IllegalArgumentException ilexp) {
			log.error("formatEmailInstance(): IllegalArgumentException"
					+ ilexp.toString());
			throw ilexp;
		}
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

	@Override
	public ArrayList<String> composeSingleEmailList(
			Collection<Collection<String>> outer) {
		ArrayList<String> monoList = new ArrayList<String>();
		for (Collection<String> emailAddressList : outer) {
			monoList.addAll(emailAddressList);
		}
		return monoList;
	}

	@Override
	public ArrayList<String> composeSingleEmailList(String anEmailAddress) {
		ArrayList<String> singleRecieverList = new ArrayList<String>();
		singleRecieverList.add(anEmailAddress);
		return singleRecieverList;
	}

	/*
	 * addContentToOriginalMailBody() formats the original details with users
	 * credentials e.g email, contact number, full name
	 * 
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */

	@Override
	public void addContentToOriginalMailBody(String originalMailBody) {
		try {
			StringBuilder result = new StringBuilder();

			// MimeMultipart multipart = new MimeMultipart("related");
			//
			// BodyPart messageBodyPart = new MimeBodyPart();
			// String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
			// messageBodyPart.setContent(htmlText, "text/html");
			//
			//
			// multipart.addBodyPart(messageBodyPart);
			// result.append(multipart);
			result.append(new SimpleDateFormat("dd/MM/yy HH:mm:ss")
					.format(new Date()));
			result.append(System.getProperty("line.separator"));
			result.append(System.getProperty("line.separator"));
			result.append(" Dear ");
			result.append(this.getRecieversName());
			result.append(",");
			result.append(System.getProperty("line.separator"));
			result.append(System.getProperty("line.separator"));
			result.append(this.getMailBody());
			result.append(originalMailBody);
			result.append(System.getProperty("line.separator"));
			result.append("If you didn't request this, please ignore this email. Your password won't change until you access the link above and create a new one.");
			result.append(System.getProperty("line.separator"));
			this.setMailBody(result.toString());

		} catch (Exception e) {
			log.error("addContentToOriginalMailBody(String originalMailBody): Exception"
					+ e.toString());
			throw e;
		}

	}

	@Override
	public void setEnvironment(String recieversName, String sedersEmailAddress,
			ArrayList<String> recieversEmailAddreses, String mailSubject,
			String emailBodyText, String... restoftheParameters) {
		setRecieversName(recieversName);
		setSendersEmailAddress(sedersEmailAddress);
		setRecieversEmailAddreses(recieversEmailAddreses);// dummy setting this
															// value will be
															// overridden
		setMailingSubject(mailSubject);
		setMailBody(emailBodyText + "   " + restoftheParameters);

	}

	@Override
	public IEmail getGeneralEmail() {

		return generalEmail;
	}

	@Override
	public void setGeneralEmail(IEmail generalEmail)
			throws IllegalArgumentException {
		try {
			if (generalEmail == null)
				throw new IllegalArgumentException(
						"generalEmail  is undefined must be set");
			this.generalEmail = generalEmail;
		} catch (IllegalArgumentException ilexp) {
			log.error("setGeneralEmail(): IllegalArgumentException"
					+ ilexp.toString());
			throw ilexp;
		}

	}

	@Override
	public EmailDispenser getEmailDispenser() {
		// TODO Auto-generated method stub
		return emailDispenser;
	}

	@Override
	public void setEmailDispenser(EmailDispenser emailDispenser)
			throws IllegalArgumentException {
		try {
			if (emailDispenser == null)
				throw new IllegalArgumentException(
						"emailDispenser  is undefined must be set");
			this.emailDispenser = emailDispenser;
		} catch (IllegalArgumentException ilexp) {
			log.error("setEmailDispenser(): IllegalArgumentException"
					+ ilexp.toString());
			throw ilexp;
		}

	}

	// getters and setters of the private fields
	public String getRecieversName() {
		return recieversName;
	}

	public void setRecieversName(String recieversName) {
		this.recieversName = recieversName;
	}

	public String getSendersEmailAddress() {
		return sendersEmailAddress;
	}

	public void setSendersEmailAddress(String sendersEmailAddress) {
		this.sendersEmailAddress = sendersEmailAddress;
	}

	public ArrayList<String> getRecieversEmailAddreses() {
		return recieversEmailAddreses;
	}

	public void setRecieversEmailAddreses(
			ArrayList<String> recieversEmailAddreses) {
		this.recieversEmailAddreses = recieversEmailAddreses;
	}

	public String getMailingSubject() {
		return mailingSubject;
	}

	public void setMailingSubject(String mailingSubject) {
		this.mailingSubject = mailingSubject;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

}
