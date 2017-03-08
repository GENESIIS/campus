package com.genesiis.campus.util.mail;
//20161027 c10-contacting-us-page EmailDispenser.java initial version created
//20161031 c10-contacting-us-page add document and method comments
//20170308 AS C9-logger Spelling mistake fixed.
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
/**
 * EmailDispenser class holds the responsibility of delivering the mail
 * Across recipients
 * @author DN
 *
 */

public class EmailDispenser {
	static Logger log = Logger.getLogger(EmailDispenser.class.getName());
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
				log.info("Email delivered successfully ");
				
		} catch (MessagingException msgExp) {
			log.error("emailDispense():MessagingException :"+msgExp.toString());
			throw msgExp;
		}		
	}

	/**
	 * getEmail() accessor method intends to return an email
	 * bound with the EmailDis
	 * @author DN
	 * @return IEmail an email object
	 */
	public IEmail getEmail() {
		return this.email;
	}
	
	/**
	 * setEmail() method sets the email if the email is originally not set
	 * @param email IEmail the email needs to be set to.
	 * @throws IllegalArgumentException
	 */
	public void setEmail(IEmail email) throws IllegalArgumentException {
			if(!email.equals(null)){			
			 this.email=email;
			}else{
				log.error("setEmail(IEmail email); null email address has passed in ");
				throw new IllegalArgumentException();
			}
	}
	
	/**
	 * isEmailNotSet() checks id the emaik is null or set to a value
	 * @return
	 */
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