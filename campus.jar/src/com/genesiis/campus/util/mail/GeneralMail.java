package com.genesiis.campus.util.mail;

//20161027 DN c10-contacting-us-page GeneralMail.java initial version created
//20161027 DN c10-contacting-us-page coded sendEmail() and supportive mutator methods
//20161028 DN c10-contacting-us-page developed sendEmail(),setSystemPropertiesAndMailEnvironment(),setProperties()

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.genesiis.campus.command.CmdGenerateEmail;
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
    private String mailHost;
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
     * @param sender String: email address of  the person who sends the email
     * @param host String: Mail transferring host or the domain address
     * @param mailHost String: the system property of mail transfer protocol
     * @param subject String: subject of the email
     * @param emailBody String : body content of the email
     */
    public GeneralMail( ArrayList<String> receivers,String sender,String host,
    		String mailHost,String subject,String emailBody){
    	this.receivers = receivers;
    	this.sender = sender;
    	this.host = host;
    	this.mailHost = mailHost;
    	this.subject = subject;
    	this.emailBody = emailBody;    	    	
    	setSystemPropertiesAndMailEnvironment(mailHost,this.host);
    	
    }
    
    /**
     * setEmailMessage() setup sender,receiver list,subject,and the message body
     * @author DN
     * @return MimeMessage
     * @throws MessagingException
     */
    @Override
	public MimeMessage setEmailMessage() throws MessagingException {
	    try {
	    	
	         addSenderToMail(message,sender); 
	         addRecipientToMail(message,receivers);
	         addSubjectToMail(message,subject);
	         addBodyContentToMail(message,emailBody);	
	         return message;	        
	         
	      }catch (MessagingException mex) {
	    	  log.error("sendEmail(MimeMessage message):MessagingException :" +mex.toString());
	    	  throw mex;
	      }

	}
    
   
    /*
     * setSystemPropertiesAndMailEnvironment sets system properties relates to mailing and creates 
     * MimeMessage instance 
     * @author DN 
     * @param mailHost Mailing protocol name which acts as the system property name
     * e.g.mail.smtp.host
     * @param host Mailing host name such as "localhost" etc
     */    
	    private void setSystemPropertiesAndMailEnvironment(String mailHost,String host){    	
	    	this.setProperties(mailHost,host);
	    	
	    }
    
 /*
  * setProperties() method sets the system property for mail transfer
  * protocol.
  * @author DN
  * @param mailHost system property for various mail transfer protocol. 
  * @param host contains the DNS name of a server
  * if the mailHost is null then the host is set to SMPT
  */
    private void setProperties(String mailHost,String host) {    	  	
    	String defaultMailingHost = "mail.smtp.host";
    	//this.mailHost=(mailHost.equals(null))?defaultMailingHost:mailHost;
    	if(mailHost.equals(null)){
    		this.mailHost=defaultMailingHost;
    	}else{
    		this.mailHost=mailHost;
    	}
    	properties=System.getProperties();
    	properties.setProperty(this.mailHost, host);
    	session = Session.getDefaultInstance(properties);
    	message = new MimeMessage(session);
    	
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



	public String getMailHost() {
		return mailHost;
	}



	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}



	

}
