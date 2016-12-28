package com.genesiis.campus.entity.model;
// 20161227 DN CAM 18 create the class SignUpEmailComposer.java to help compose the mailing 
// specially when student Sign up to the system.
//20161227 DN CAM 18 add override methods to the concrete implementation.
//20161228 DN CAM 18 shift getEmailDispenser(),getGeneralEmail(),setGeneralEmail(),setEmailDispenser() methods to interface IEmailComposer.java
// for code reuse purpose.

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.util.mail.IEmailComposer;


/**
 * StudentSignUpEmailComposer.java handles the composing of the email
 * which is intended for creating once and Student has successfully signed up 
 * to the system.
 * @author dushantha DN
 *
 */
public class StudentSignUpEmailComposer implements IEmailComposer {
	static Logger log = Logger.getLogger(StudentSignUpEmailComposer.class.getName());
	private String recieversName;
	private String sendersEmailAddress;
	private ArrayList<String> recieversEmailAddreses;
	private String sendersphoneNumber;
	private String mailingSubject;
	private String mailBody;
	private EmailDispenser emailDispenser;
	private IEmail generalEmail;

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
	public void addContentToOriginalMailBody(String originalMailBody) {
		StringBuilder result = new StringBuilder();
		result.append(new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()));
		result.append(System.getProperty("line.separator"));
		result.append(" Dear ");
		result.append(this.getRecieversName());
		result.append(",");
		result.append(System.getProperty("line.separator"));
		result.append(this.getMailBody());
		result.append(originalMailBody) ;
		result.append(System.getProperty("line.separator"));
		this.setMailBody(result.toString());
	}

	@Override
	public void setEnvironment(String recieversName,
			String sedersEmailAddress,
			ArrayList<String> recieversEmailAddreses, 
			String mailSubject,
			String emailBodyText,
			String ... restoftheParameters){
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
				throw new IllegalArgumentException("generalEmail  is undefined must be set");	
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
				throw new IllegalArgumentException("emailDispenser  is undefined must be set");	
			this.emailDispenser = emailDispenser;
		}catch (IllegalArgumentException ilexp){
			log.error("setEmailDispenser(): IllegalArgumentException"+ ilexp.toString());
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

	public void setRecieversEmailAddreses(ArrayList<String> recieversEmailAddreses) {
		this.recieversEmailAddreses = recieversEmailAddreses;
	}

	public String getSendersphoneNumber() {
		return sendersphoneNumber;
	}

	public void setSendersphoneNumber(String sendersphoneNumber) {
		this.sendersphoneNumber = sendersphoneNumber;
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
