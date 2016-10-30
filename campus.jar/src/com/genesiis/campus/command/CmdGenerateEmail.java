package com.genesiis.campus.command;

//20161026 DN c10-contacting-us-page created CmdGenerateEmail.java
//20161028 DN c10-contacting-us-page execute(),formatEmailInstance(),composeSingleEmailList()
//sendMail(),createDatabaseConnection() created and defined

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.validation.Operation;



import java.sql.Connection;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.lang.System;

import org.apache.log4j.Logger;

public class CmdGenerateEmail implements ICommand {
	
	static Logger log = Logger.getLogger(CmdGenerateEmail.class.getName());
	private String sendersName;
	private String sendersEmailAddress;
	private ArrayList<String> recieversEmailAddreses;
	private String sendersphoneNumber;
	private String mailingSubject;
	private String mailBody;
	private Connection connection = null;
	private EmailDispenser emailDispenser;
	private IEmail generalEmail;
	//private MimeMessage message;
	
	
	

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		//getting the admin related data e.g email address
		sendersName = helper.getParameter("firstName")
				.concat(" "+ helper.getParameter("lastName"));		
		sendersEmailAddress = helper.getParameter("emailAddress");
		sendersphoneNumber= helper.getParameter("contactNumber");
		mailingSubject = helper.getParameter("subject");
		mailBody = helper.getParameter("message");
		String cco = helper.getCommandCode();
		String message = "";
		ArrayList<Collection<String>> collectionOfCollectionOfEmails = null;
		 
		ICrud genesiis = new SystemConfigDAO();
		try{
			 switch(Operation.getOperation(cco)){
			 case CONTACT_US_PUBLC:
				 this.createDatabaseConnection();
				 
				 String[] sysEmailAdress = {"ENQUIRY_EMAIL_TO","ENQUIRY_EMIL_ADMIN"}; 
				 collectionOfCollectionOfEmails=(ArrayList<Collection<String>>)genesiis.findById(sysEmailAdress, connection);
				 recieversEmailAddreses= composeSingleEmailList(collectionOfCollectionOfEmails);
				 generalEmail = formatEmailInstance();
				 
				 break;
			 default:
				 
				 break;	 
		 
		 }
			 log.info("execute(): before callling sendMail() ===");
		this.sendMail();
		
		return view;
		
		} catch (MessagingException msgexp){
			log.error("execute():MessagingException "+msgexp.toString());
			throw msgexp;
		} catch (SQLException sqle) {
			log.error("execute():SQLException"+ sqle.toString());
			throw sqle;
		} catch (Exception e) {
			log.error("execute():SQLException"+e.toString());
			throw e;
		}
	}
	
	
	private IEmail formatEmailInstance(){
		
		IEmail generalEmail= new GeneralMail(recieversEmailAddreses, sendersEmailAddress,
				"localhost","mail.smtp.host", mailingSubject, mailBody);
		return generalEmail;
		
	}
	
	
	/*
	 * composeSingleEmailList()  adds up all the email structured in Collection of Collections
	 *  to a monolithic Collection<String>
	 *  @author DN 
	 * @param outer accepts Collection<Collection<String> 
	 * @return email list as a mono ArrayList<String> 
	 */
	
	private ArrayList<String> composeSingleEmailList(Collection<Collection<String>> outer){
		ArrayList<String> monoList = new ArrayList<String>();
		for(Collection<String> emailAddressList: outer ){
			monoList.addAll(emailAddressList);
		}
		return monoList;
		
	}
	
	/*
	 * sendMail() method intended to dispense the email
	 * @author DN
	 * @throws MessagingException in any case dispensing email fails
	 */
	
	private void sendMail() throws MessagingException {		
//		emailDispenser = new EmailDispenser(generalEmail);
//		log.info("sendMail() :before calling emailDispense() :====");
//		emailDispenser.emailDispense();
//		log.info("email send successfully");
		
//################################################################################	
// 2ND CODE BUT THE ERROR IS THE SAME
//		try{
		
//		Properties properties=new Properties();  
//		//fill all the information like host name etc.  
//		Session session=Session.getDefaultInstance(properties,null);  
//		MimeMessage message=new MimeMessage(session);		 
//		message.setFrom(new InternetAddress("sonoojaiswal@sssit.org"));  
//		message.addRecipient(Message.RecipientType.TO,new InternetAddress("choi_chandima@yahoo.com"));  
//		message.setHeader("Hi, everyone","axy");  
//		message.setText("Hi, This mail is to inform you...");  
//		
//		Transport.send(message);  
//	}catch (MessagingException e) { 
//		  System.out.println("Erreur d'envoi, cause: " + e.toString());}
//#############################################################################		
//#######################################################################
//	1ST EXAMPLETESTED AND GOT 
//javax.mail.MessagingException: Could not connect to SMTP host: localhost, port: 465;
//#####################################################################################
		
		
		
		
//		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//		  // Get a Properties object
//		     Properties props = System.getProperties();
//		     props.setProperty("mail.smtp.host","smtp.gmail.com");
//		     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//		     props.setProperty("mail.smtp.socketFactory.fallback", "false");
//		     props.setProperty("mail.smtp.port", "465");//465
//		     props.setProperty("mail.smtp.socketFactory.port", "465");//465
//		     props.put("mail.smtp.auth", "true"); //true
//		     props.put("mail.debug", "true");
//		     props.put("mail.store.protocol", "pop3");
//		     props.put("mail.transport.protocol", "smtp");
//		     final String username = "xxxx@gmail.com";//
//		     final String password = "0000000";
//		     try{
//		    	 Session session = Session.getDefaultInstance(props ,
//                         new Authenticator(){
//                     protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                     }});
//
//		   // -- Create a new message --
//		     Message msg = new MimeMessage(session);
//
//		  // -- Set the FROM and TO fields --
//		     msg.setFrom(new InternetAddress("xxxx@gmail.com"));
//		     msg.setRecipients(Message.RecipientType.TO, 
//		                      InternetAddress.parse("choi_chandima@yahoo.com",false));
//		     msg.setSubject("Hello");
//		     msg.setText("How are you");
//		     msg.setSentDate(new Date());
//		     Transport.send(msg);
//		     System.out.println("Message sent.");
//		  }catch (MessagingException e) { 
//			  System.out.println("Erreur d'envoi, cause: " + e);}
//##########################################################################
		
		
	   }
		
		
		
	
	
	
	/*
	 * createDatabaseConnection() establishes the database connection
	 * with the data repository
	 * @author DN
	 * @throw SQLException if the connection causes errors.
	 */
	private void createDatabaseConnection() throws SQLException{
		try{
			connection = ConnectionManager.getConnection();
		} catch (SQLException sqle) {
			log.error("add():SQLException :" +sqle.toString());
			throw sqle;			
		}
	}
	
	
}



