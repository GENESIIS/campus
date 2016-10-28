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
    
    
    private void setSystemPropertiesAndMailEnvironment(String mailHost,String host){    	
    	this.setProperties(mailHost,host);
    	
    }
    
    
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
     * add to the message mailing list
     * @param receiversList list of recipient ,MimeMessage message being context
     */
	private void addRecipientToMail(MimeMessage message,ArrayList<String> receiversList)
			throws MessagingException {

		for (String emailAddress : receiversList) {
			message.addRecipients(Message.RecipientType.TO, emailAddress);
		}
		
	}
	
	/*
     * addSenderToMail() adds sender to the email 
     * @param message which is been composed, sender senders address
     */
	 private void addSenderToMail(MimeMessage message,String sender )throws MessagingException{
		message.setFrom(new InternetAddress(sender));
	 }

	 /*
     * addSubjectToMail() adds the subject to the message mailing list
     * @param message message which is been composed, subject senders address
     * @Throws MessagingException
     */
	 
	private void addSubjectToMail(MimeMessage message, String sublject)
			throws MessagingException {
		 message.setSubject(sublject);
	}
	
	/*
     * addBodyContentToMail() adds the message body to the email 
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
