package com.genesiis.campus.util.mail;

/**
 * 20160328 LE SDB-536-4353 creating file: MailUtil.java
 * 20151009 AM SDB-426-4278 Added in the send method and  get connection method, this will putting the variable coming in together and send it out.
 * **/

import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.IllegalWriteException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class MailUtil {
	private final String MAIL_SESSION = "java:jboss/mail/Default";
	private Logger logger = Logger.getLogger(this.getClass());
	private Session session;
	private final Logger log = Logger.getLogger(this.getClass());

	public MailUtil() {
		logger.debug("--> init...");
	}

	/**
	 * send an email - note: even thou bcc, cc has are being passed in the
	 * behavior is not coded in.
	 * 
	 * @param subject
	 *            : String
	 * @param body
	 *            : String
	 * @param from
	 *            : String
	 * @param to
	 *            : String[]
	 * @param replyTo
	 *            : String []
	 * @param cc
	 *            : String []
	 * @param bcc
	 *            : String []
	 * @param filePath
	 *            : String
	 * @param fileName
	 *            : String
	 * @param debugSession
	 *            : boolean
	 * @return true if the email is send successfully - else false.
	 * **/
	public boolean send(String subject, String body, String from, String[] to,
			String[] replyTo, String[] cc, String[] bcc, String filePath,
			String fileName, boolean debugSession) {
		boolean sent = false;
		boolean valid = this.isValid(subject, body);
		if (!valid) {
			logger.debug("the email subject or the body is empty");
			return false;
		}
		try {
			Session session = this.getMailSession(this.getMailJNDI(),
					debugSession);
			if (session == null) {
				logger.debug("An email session could not be retrieved");
				return false;
			}
			MimeMessage message = new MimeMessage(session);
			/**
			 * set subject
			 * **/
			message.setSubject(subject);
			/**
			 * set email: from address
			 * **/
			this.setSender(from, message);
			/**
			 * set email: recipient address
			 * **/
			this.setRecipients(to, message);
			/**
			 * set email: reply to address
			 * **/
			this.setReplyToEmails(replyTo, message);
			/**
			 * set the email content
			 * **/
			if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(filePath))
				this.setContent(body, message);
			else
				this.setMultipartContent(fileName, filePath, body, message);
			/**
			 * send email
			 * **/
			Transport.send(message);
			sent = true;
		} catch (NamingException nme) {
			logger.error("--> send(subject, email, to, replyTo, cc, bcc, file, debug): Failed to aquire mail session: NMEERR"
					+ nme);
		} catch (SendFailedException sfe) {
			logger.error("--> send(subject, email, to, replyTo, cc, bcc, file, debug): failed to send email: SFEERR"
					+ sfe);
		} catch (MessagingException mse) {
			logger.error("--> send(subject, email, to, replyTo, cc, bcc, file, debug): a messagin exception occured: MSEERR"
					+ mse);
		} catch (Exception e) {
			logger.error("--> send(subject, email, to, replyTo, cc, bcc, file, debug): unexpecte error: ERR-"
					+ e);
		}
		return sent;
	}

	/**
	 * set the email body content.
	 * 
	 * @param body
	 *            : String
	 * @param message
	 *            : MimeMessage
	 * **/
	private void setContent(String body, MimeMessage message) {
		try {
			MimeMultipart multipart = new MimeMultipart();
			BodyPart msgPart = new MimeBodyPart();
			msgPart.setContent(body, "text/html");
			multipart.addBodyPart(msgPart);
			message.setContent(multipart);
		} catch (IllegalStateException ise) {
			logger.error("--> setContent(): body part is obtained from a READ_ONLY folder: ISE"
					+ ise);
		} catch (IllegalWriteException iwe) {
			logger.error("--> setContent(): cannot modify existing values... IWE: "
					+ iwe);
		} catch (MessagingException mse) {
			logger.error("--> setContent(): MSE: " + mse);
		} catch (Exception e) {
			logger.error("--> setContent(): ERR: " + e);
		}
	}

	/**
	 * set the email content: string content + multipart content (attachment)
	 * 
	 * @param fileName
	 *            : String - attachment name
	 * @param filePath
	 *            : String - attachment sys path (C:/sdb/*)
	 * @param body
	 *            : String - email body
	 * @param message
	 *            : MimeMessage
	 * **/
	private void setMultipartContent(String fileName, String filePath,
			String body, MimeMessage message) {
		try {
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(body, "text/html");
			multipart.addBodyPart(bodyPart);
			bodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			bodyPart.setDataHandler(new DataHandler(source));
			String extension = source.getName().substring(
					source.getName().lastIndexOf("."));
			bodyPart.setFileName(fileName + extension);
			multipart.addBodyPart(bodyPart);
			message.setContent(multipart);
		} catch (NullPointerException npe) {
			logger.error("--> setMultipartContent(): " + npe);
		} catch (IllegalStateException ise) {
			logger.error("--> setMultipartContent(): body part is obtained from a READ_ONLY folder: ISE"
					+ ise);
		} catch (IllegalWriteException iwe) {
			logger.error("--> setMultipartContent(): cannot modify existing values... IWE: "
					+ iwe);
		} catch (MessagingException mse) {
			logger.error("--> setMultipartContent(): MSE: " + mse);
		} catch (Exception e) {
			logger.error("--> setMultipartContent(): ERR: " + e);
		}
	}

	/**
	 * set the from address
	 * 
	 * @param from
	 *            - email send from address.
	 * @param message
	 *            : MimeMessage
	 * **/
	private void setSender(String from, MimeMessage message)
			throws MessagingException {
		try {
			message.setFrom(new InternetAddress(from));
		} catch (IllegalStateException ise) {
			logger.error("--> setSender(): body part is obtained from a READ_ONLY folder: ISE"
					+ ise);
		} catch (IllegalWriteException iwe) {
			logger.error("--> setSender(): cannot modify existing values... IWE: "
					+ iwe);
		} catch (MessagingException mse) {
			logger.error("--> setSender(): MSE: " + mse);
		} catch (Exception e) {
			logger.error("--> setSender(): ERR: " + e);
		}
	}

	/**
	 * set the reply to email addresses
	 * 
	 * @param replyTo
	 *            : String[]
	 * @param message
	 *            : MimeMessage
	 * **/
	private void setReplyToEmails(String[] replyTo, MimeMessage message)
			throws MessagingException {
		try {
			javax.mail.Address[] replyToEmail = new javax.mail.Address[1];
			for (int i = 0; i < replyTo.length; i++)
				replyToEmail[0] = new InternetAddress(replyTo[0]);
			message.setReplyTo(replyToEmail);
		} catch (IllegalStateException ise) {
			logger.error("--> setReplyToEmails(): body part is obtained from a READ_ONLY folder: ISE"
					+ ise);
		} catch (IllegalWriteException iwe) {
			logger.error("--> setReplyToEmails(): cannot modify existing values... IWE: "
					+ iwe);
		} catch (MessagingException mse) {
			logger.error("--> setReplyToEmails(): MSE: " + mse);
		} catch (Exception e) {
			logger.error("--> setReplyToEmails(): ERR: " + e);
		}
	}

	/**
	 * set the email recipients - to address list
	 * 
	 * @param to
	 *            : String[] - list of recipient addresses
	 * @param message
	 *            : MimeMessage
	 * **/
	private void setRecipients(String[] to, MimeMessage message)
			throws MessagingException {
		if (to != null && to.length > 0) {
			try {
				InternetAddress[] addressTo = new InternetAddress[to.length];
				for (int i = 0; i < to.length; i++)
					addressTo[i] = new InternetAddress(to[i]);
				message.setRecipients(javax.mail.Message.RecipientType.TO,
						addressTo);
			} catch (IllegalStateException ise) {
				logger.error("--> setRecipients(): body part is obtained from a READ_ONLY folder: ISE"
						+ ise);
			} catch (IllegalWriteException iwe) {
				logger.error("--> setRecipients(): cannot modify existing values... IWE: "
						+ iwe);
			} catch (MessagingException mse) {
				logger.error("--> setRecipients(): MSE: " + mse);
			} catch (Exception e) {
				logger.error("--> setRecipients(): ERR: " + e);
			}
		}
	}

	/**
	 * validate the subject and the body. Note: technically, an email can be
	 * sent without a subject and body. However, from a business perspective we
	 * never want an email being sent to an end-user without a subject or a body
	 * 
	 * @return true if body and subject exist, else false;
	 * **/
	private boolean isValid(String subject, String email) {
		if (StringUtils.isEmpty(subject) || StringUtils.isEmpty(email))
			return false;
		return true;
	}

	/**
	 * get mail session
	 * 
	 * @param jndiName
	 *            :String - the namspace name
	 * @param debug
	 *            : boolean - controls debug settings for the session
	 * @return a mail session, else null
	 * **/
	public Session getMailSession(String jndiName, boolean debug)
			throws NamingException, ClassCastException {
		Session session = null;
		try {
			session = (Session) new InitialContext().lookup(jndiName);
			if (session != null)
				session.setDebug(debug);
		} catch (ClassCastException cce) {
			logger.error("--> getMailSession(jndiName): failed to cast object to type Session: CCE  "
					+ cce);
		} catch (NamingException ne) {
			logger.error("--> getMailSession(jndiName): failed to aquire mail session: NAMING: "
					+ ne);
		} catch (NullPointerException npe) {
			logger.error("--> getMailSession(jndiName): jndiName is null: NPX: "
					+ npe);
		}
		return session;
	}

	/**
	 * get the mail jndi namespace name
	 * **/
	public String getMailJNDI() {
		return this.MAIL_SESSION;
	}

	/**
	 * @param to
	 * @param subject
	 * @param from
	 * @param txt
	 * @param img
	 * @param ccList
	 *            send an email with an in-line image to one recipient and copy
	 *            to one more email.
	 * @return a true if the mail is sent, else false
	 * **/
	public boolean send(String subject, String to, String from, String txt,
			String img, String[] ccList) throws Exception {
		boolean emailSent = false;
		try {
			this.getMailcon();
			MimeMessage msg = new MimeMessage(session);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			MimeMultipart multipart = new MimeMultipart();
			BodyPart msgPart = new MimeBodyPart();
			msgPart.setContent(txt, "text/html");
			if (!StringUtils.isEmpty(img)) {
				File file = new File(img);
				if (file.isFile()) {
					multipart = new MimeMultipart("related");
					String image = "<img src=\"cid:image\">";
					txt += image;
					msgPart.setContent(txt, "text/html");
					multipart.addBodyPart(msgPart);
					msgPart = new MimeBodyPart();
					DataSource fds = new FileDataSource(img);
					msgPart.setDataHandler(new DataHandler(fds));
					msgPart.setHeader("Content-ID", "<image>");
				} else
					this.log.info("file not found for: " + img);
			} else
				this.log.trace("--> send(a,b,c,d,e).  Sending a plain text message.");
			if (ccList != null && ccList.length > 0) {
				InternetAddress[] recipientAddress = new InternetAddress[ccList.length];
				int counter = 0;
				for (String recipient : ccList) {
					recipientAddress[counter] = new InternetAddress(
							recipient.trim());
					counter++;
				}
				msg.addRecipients(Message.RecipientType.CC, recipientAddress);
			}
			multipart.addBodyPart(msgPart);
			msg.setContent(multipart);
			Transport.send(msg);
			emailSent = true;
		} catch (SMTPSendFailedException sfe) {
			log.error("--> send(a,b,c,d,e): " + "" + "; " + sfe.getCause()
					+ "; " + sfe.toString());
		} catch (Exception e) {
			this.log.error(this + ":send(a,b,c,d,e) " + e.toString());
			throw e;
		}
		this.log.info("mail fn finished executing...");
		return emailSent;
	}

	/**
	 * look up mail-service.xml and set up a mail session
	 * */
	public void getMailcon() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			session = (Session) ic.lookup("java:jboss/mail/Default");
		} catch (NamingException e) {
			this.log.error(this + "getMailCon, cause: " + e.getRootCause()
					+ ", " + e.toString());
		}
	}
}