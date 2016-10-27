package com.genesiis.campus.util.mail;
//20161027 DN c10-contacting-us-page initialized IEmail.java interface

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * IEmail interface is a functional interface(at the moment)
 * which handles email sending protocol and strategy
 * @author DN
 *
 */
@FunctionalInterface
public interface IEmail {
/**
 * sendEmail() sends the message it receives as the parameter
 * @param message javax.mail.internet.MimeMessage
 * @throws MessagingException
 */
	public void sendEmail(MimeMessage message) throws MessagingException;
	
}
