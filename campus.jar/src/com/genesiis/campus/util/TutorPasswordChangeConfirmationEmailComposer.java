package com.genesiis.campus.util;

//20170316 CW c149-tutor-email-confirmation-for-password-change-cw TutorPasswordChangeConfirmationEmailComposer class created.

import com.genesiis.campus.util.mail.EmailContentCreator;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemEmail;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
* TutorPasswordChangeConfirmationEmailComposer class handles the composing & formatting the email to be sent
* to the tutor to confirm password change.
* @author Chinthaka CW
*/
public class TutorPasswordChangeConfirmationEmailComposer implements IEmailComposer, EmailContentCreator  {
	static Logger log = Logger.getLogger(TutorPasswordChangeConfirmationEmailComposer.class.getName());
	private String recieversName;
	private String sendersEmailAddress;
	private ArrayList<String> recieversEmailAddreses;
	private String mailingSubject;
	private String mailBody;
	private IEmail generalEmail;
	private EmailDispenser emailDispenser;
	private String hashCode;
	
/**
* No Argument Constructor for the class
* @author CHINTHAKA
*/
	public TutorPasswordChangeConfirmationEmailComposer(){
		
	}

/**
* Single Argument Constructor for the class TutorPasswordChangeConfirmationEmailComposer
* this will assign the tutor status in order to use later to send the email
* @author CHINTHAKA
*/
	public TutorPasswordChangeConfirmationEmailComposer(String hashCode){
		this.hashCode = hashCode;
	}

	@Override
	public IEmail formatEmailInstance(String mailBodyForSpecificMail) throws IllegalArgumentException {
		try{
			addContentToOriginalMailBody(mailBodyForSpecificMail);
			if(getRecieversEmailAddreses()==null||getRecieversEmailAddreses().size()==0){
				throw new IllegalArgumentException("genRecieversEmailAddreses list is empty or undefined");
			} else{
				IEmail generalEmail = new GeneralMail(getRecieversEmailAddreses(),
						getSendersEmailAddress(),
						getMailingSubject(),getMailBody());
				this.setGeneralEmail(generalEmail);
			}
		} catch (IllegalArgumentException ilexp){
			log.error("formatEmailInstance(): IllegalArgumentException"+ ilexp.toString());
			throw ilexp;
		}
		return generalEmail;
	}

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
	public ArrayList<String> composeSingleEmailList(String anEmailAddress){
		ArrayList<String> singleRecieverList = new ArrayList<String>();
		singleRecieverList.add(anEmailAddress);
		return singleRecieverList;
	}

	@Override
	public void addContentToOriginalMailBody(String username) {
		StringBuilder result = new StringBuilder();
		
		result.append(getEmailSubjectBody());
		result.append(getEmailBody()) ;
		result.append(getEmailComplementaryClose(username));
		
		this.setMailBody(result.toString());
	}

	@Override
	public String getEmailSubjectBody() {
		StringBuilder result = new StringBuilder();
		
		result.append(new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()));
		result.append(System.getProperty("line.separator"));
		result.append("Dear ");
		result.append(this.getRecieversName());
		result.append(",");
		result.append(System.getProperty("line.separator"));
		
		return result.toString();
	}

	@Override
	public String getEmailBody() {
		StringBuilder result = new StringBuilder();		

		result.append(System.getProperty("line.separator"));
		result.append(SystemEmail.SEND_EMAIL_TUTOR_PASSWORD_CHANGE_CONFIRMATION_BODY1.getMailBody());
		result.append(System.getProperty("line.separator"));
		
		return result.toString();
	}

	@Override
	public String getEmailComplementaryClose(String username) {
		StringBuilder result = new StringBuilder();	
		
		result.append(System.getProperty("line.separator"));
		result.append(SystemEmail.SEND_EMAIL_TUTOR_SIGNUP_COMPLEMENTARY_CLOSE1.getMailBody());
		result.append(System.getProperty("line.separator"));
		result.append(SystemEmail.SEND_EMAIL_TUTOR_SIGNUP_COMPLEMENTARY_CLOSE2.getMailBody());
		result.append(System.getProperty("line.separator"));
		result.append(SystemEmail.SEND_EMAIL_TUTOR_SIGNUP_COMPLEMENTARY_CLOSE3.getMailBody());
		result.append(username);
		result.append(System.getProperty("line.separator"));
		
		return result.toString();
	}

	@Override
	public void setEnvironment(String recieversName, String sedersEmailAddress,
			ArrayList<String> recieversEmailAddreses, String mailSubject, String emailBodyText,
			String... restoftheParameters) {
		setRecieversName(recieversName);
		setSendersEmailAddress(sedersEmailAddress);
		setRecieversEmailAddreses(recieversEmailAddreses);// dummy setting this value will be overridden
		setMailingSubject(mailSubject);
		setMailBody(emailBodyText);
	}

	@Override
	public IEmail getGeneralEmail() {
		return generalEmail;
	}

	@Override
	public void setGeneralEmail(IEmail generalEmail) throws IllegalArgumentException {
		try{
			if(generalEmail==null)
				throw new IllegalArgumentException("generalEmail is undefined must be set");	
			this.generalEmail = generalEmail;
		}catch (IllegalArgumentException ilexp){
			log.error("setGeneralEmail(): IllegalArgumentException"+ ilexp.toString());
			throw ilexp;
		}
	}

	@Override
	public EmailDispenser getEmailDispenser() {
		return emailDispenser;
	}

	@Override
	public void setEmailDispenser(EmailDispenser emailDispenser) throws IllegalArgumentException {
		try{
			if(emailDispenser==null)
				throw new IllegalArgumentException("emailDispenser is undefined must be set");	
			this.emailDispenser = emailDispenser;
		}catch (IllegalArgumentException ilexp){
			log.error("setEmailDispenser(): IllegalArgumentException"+ ilexp.toString());
			throw ilexp;
		}
	}
	

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

	public void setRecieversEmailAddreses(ArrayList<String> recieversEmailAddreses) {
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