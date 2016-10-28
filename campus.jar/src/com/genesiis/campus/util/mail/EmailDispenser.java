package com.genesiis.campus.util.mail;
//20161027 c10-contacting-us-page EmailDispenser.java initial version created

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class EmailDispenser {
	static Logger log = Logger.getLogger(GeneralMail.class.getName());
	private IEmail email;
	
	/**
	 * EmailDispenser constructor accept a valid IEmail object
	 * to create a EmailDispenser instance. When a EmailDispenser is created
	 * instance is bound with a valid email object.
	 * @author DN
	 * @param emailtoDispnce the details induced email object
	 * @throws IllegalArgumentException if the emailtoDispnce is null
	 * the said exception will be thrown.
	 */
	public EmailDispenser(IEmail emailtoDispnce) throws IllegalArgumentException {
		setEmail(emailtoDispnce);
		
	}
	
	/**
	 * emailDispense() dispense the email to the email 
	 * bound to the emailDispense object.
	 * @author DN
	 * @throws MessagingException
	 */
	public void emailDispense() throws MessagingException{		
		try {
				dispenceEmail(email.setEmailMessage());
				
		} catch (MessagingException msgExp) {
			log.error("emailDispense():MessagingException :"+msgExp.toString());
			throw msgExp;
		}		
	}

	/**
	 * getEmail() accessor method intends to return an email
	 * bound with the EmailDis
	 * @return IEmail an email object
	 */
	public IEmail getEmail() {
		return email;
	}

	public void setEmail(IEmail email) throws IllegalArgumentException {
		if(!(isEmailNotSet())&(!email.equals(null)) ){
			this.email=email;
			}else{
				log.error("setEmail(IEmail email); noll email address has passed in ");
				throw new IllegalArgumentException();
			}
	}
	
	public boolean isEmailNotSet(){
		return (getEmail().equals(null));
	}
	
	/*
     * dispenceEmail() adds the message body to the email 
     * @param message which is been composed
     * @Throws MessagingException
     */
	private void dispenceEmail(MimeMessage message) throws MessagingException {
		Transport.send(message);
	}
	
}
