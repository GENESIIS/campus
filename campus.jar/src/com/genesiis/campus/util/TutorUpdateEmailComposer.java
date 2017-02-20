package com.genesiis.campus.util;

//20170119 CW CAM-125 create the class SignUpEmailComposer.java to help compose the mailing using CAM-18
//20170123 CW c125-un-formatted-email-sending-tutor-signup-removing un-wanted codes & cleaning the code
//20170125 CW c125-un-formatted-email-sending-tutor-signup-add comments to the Class - cw
//20170127 CW c126-formatting-un-formatted-email-tutor-signup-cw modified addContentToOriginalMailBody() method 
//20170127 CW c126-formatting-un-formatted-email-tutor-signup-cw implement EmailContentCreator Interface
//20170219 CW c103-send-email-tutor-status-change-cw Create the constructors & modified getEmailBody() method
//20170220 CW c103-send-email-tutor-status-change-cw modified single argument constructor & getEmailBody() method.

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.genesiis.campus.util.mail.EmailContentCreator;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.SystemEmail;

import org.apache.log4j.Logger;

/**
 * SignUpEmailComposer class handles the composing & formatting the email to be sent
 * at the time of tutor signup.
 * @author Chinthaka CW
 */
public class TutorUpdateEmailComposer implements IEmailComposer, EmailContentCreator  {
	static Logger log = Logger.getLogger(TutorUpdateEmailComposer.class.getName());
	private String recieversName;
	private String sendersEmailAddress;
	private ArrayList<String> recieversEmailAddreses;
	private String mailingSubject;
	private String mailBody;
	private IEmail generalEmail;
	private EmailDispenser emailDispenser;
	private String tutorStatus;
	
/**
 * No Argument Constructor for the class
 * @author CHINTHAKA
 */
	public TutorUpdateEmailComposer(){
		
	}

/**
 * Single Argument Constructor for the class TutorUpdateEmailComposer
 * this will assign the tutor status in order to use later to send the email
 * @author CHINTHAKA
 */
	public TutorUpdateEmailComposer(IDataHelper helper){
		ApplicationStatus[] applicationStatus = ApplicationStatus.values();
		tutorStatus = applicationStatus[Integer.parseInt(helper.getParameter("newtutorStatus"))].toString().toLowerCase();
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
		result.append(SystemEmail.SEND_EMAIL_ADMIN_TUTOR_UPDATE_BODY1.getMailBody());
		result.append(" "+tutorStatus+" ");
		result.append(SystemEmail.SEND_EMAIL_ADMIN_TUTOR_UPDATE_BODY2.getMailBody());
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
