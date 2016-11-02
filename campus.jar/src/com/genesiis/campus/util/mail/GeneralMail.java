package com.genesiis.campus.util.mail;

//20161027 DN c10-contacting-us-page GeneralMail.java initial version created
//20161027 DN c10-contacting-us-page coded sendEmail() and supportive mutator methods
//20161028 DN c10-contacting-us-page developed sendEmail(),setSystemPropertiesAndMailEnvironment(),setProperties()
//20161031 DN c10-contacting-us-page setEmailMessage(),setProperties() re-factored to set authentication details
//				add documentation comments
//			  	Removed GeneralMail() constructor argument 'mailHost'.
//				SetProperties(),setSystemPropertiesAndMailEnvironment() restructured.
//20161102 DN c10-contacting-us-page removed host,user name,password fields and changed the class constructor accordingly
//				setProperties() method refactor accordingly.

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.genesiis.campus.command.CmdGenerateEmail;
import com.genesiis.campus.util.MailServerManager;

import org.apache.log4j.Logger;

/**
 * GeneralMail class captures the essence of an email
 * and binds email related properties such as from, to ,message context , subject
 * and the system properties related to Mailing protocol. 
 * @author DN
 *
 */
public class GeneralMail implements IEmail {

	static Logger log = Logger.getLogger(GeneralMail.class.getName());

    private ArrayList<String> receivers = null;    
    private String sender;    
    private String host ;
    private String subject;
    private String emailBody; 
    private Properties properties;
    private Session session ;
    private MimeMessage message ;
    
  
	/**
     * GeneralMail constructor creates the MimeMessage and Session automatically 
     * with system properties.
     * @author DN
     * @param receivers to list of the email ArrayList<String>
     * @param sender String: email address of  the person who sends the email     * 
     * @param subject String: subject of the email
     * @param emailBody String : body content of the email
     */
    public GeneralMail( ArrayList<String> receivers,String sender,
    		String subject,String emailBody){
    		
    	this.receivers = receivers;
    	this.sender = sender; 	
    	this.subject = subject;
    	this.emailBody = emailBody;	
    
    	setSystemPropertiesAndMailEnvironment();
    }
    
    /**
     * setEmailMessage() setup sender,receiver list,subject,and the message bodyand the
     * date of generating the email
     * @author DN
     * @return MimeMessage formatted message
     * @throws MessagingException
     */
    @Override
	public MimeMessage setEmailMessage() throws MessagingException {
	    try {
	    	
	         addSenderToMail(this.message,this.sender); 
	         addRecipientToMail(this.message,this.receivers);
	         addSubjectToMail(this.message,this.subject);
	         addBodyContentToMail(this.message,this.emailBody);
	         setSentDateToMail(this.message);
	         return this.message;	        
	         
	      }catch (MessagingException mex) {
	    	  log.error("sendEmail(MimeMessage message):MessagingException :" +mex.toString());
	    	  throw mex;
	      }

	}
    
   
    /*
     * setSystemPropertiesAndMailEnvironment sets system properties relates to mailing and creates 
     * MimeMessage instance 
     * @author DN 
     *      
     */    
	    private void setSystemPropertiesAndMailEnvironment(){		    	
	    	this.setProperties();
	    }
    
 /*
  * setProperties() method sets the system property for mail transfer
  * protocol.
  * @author DN  
  */
	private void setProperties() {
		try {
			session = createMailSession();
			properties = session.getProperties();
			properties.setProperty("mail.smtp.starttls.enable", "true");
		} catch (SQLException sqle) {
			log.error("setProperties() SQLException:" + sqle.toString());

		}
		message = new MimeMessage(session);
	}
	    
    /*
     * createMailSession() manages to provide the MailSession instance
     * by accessing MailServerManger
     * @author DN
     * @return Session
     * @throws SQLException
     */
	 private Session createMailSession() throws SQLException{
		 return MailServerManager.mailSession();
	 } 

	/*
     * addRecipientToMail() unfolds the list of recipients and
     * add to the message mailing list only if the element is not null.
     * @author DN
     * @param receiversList list of recipient ,MimeMessage message being context
     */
	private void addRecipientToMail(MimeMessage message,ArrayList<String> receiversList)
			throws MessagingException {

		for (String emailAddress : receiversList) {
			if(!emailAddress.equals(null)){
				message.addRecipients(Message.RecipientType.TO, emailAddress);
			}
			
		}
		
	}
	
	/*
	 * setSentDateToMail() binds the sending date to the mail
	 * @author DN
	 * @param message MimeMessage
	 * @throws MessagingException
	 */
	private void setSentDateToMail(MimeMessage message)throws MessagingException{
		message.setSentDate(new Date());
		
	}
	
	/*
     * addSenderToMail() adds sender to the email
     * @author DN 
     * @param message which is been composed, sender senders address
     */
	 private void addSenderToMail(MimeMessage message,String sender )throws MessagingException{
		message.setFrom(new InternetAddress(sender));
	 }

	 /*
     * addSubjectToMail() adds the subject to the message mailing list
     * @author DN
     * @param message message which is been composed, subject senders address
     * @Throws MessagingException
     */
	 
	private void addSubjectToMail(MimeMessage message, String sublject)
			throws MessagingException {
		 message.setSubject(sublject);
	}
	
	/*
     * addBodyContentToMail() adds the message body to the email 
     * @author DN
     * @param message which is been composed, bodyContent body of the message
     */
	private void addBodyContentToMail(MimeMessage message, String bodyContent)
			throws MessagingException {
		message.setText(bodyContent);
	}
	
	// getters and setters

	public ArrayList<String> getReceivers() {
		return receivers;
	}



	public void setReceivers(ArrayList<String> receivers) {
		this.receivers = receivers;
	}



	public String getSender() {
		return sender;
	}



	public void setSender(String sender) {
		this.sender = sender;
	}



	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


}
