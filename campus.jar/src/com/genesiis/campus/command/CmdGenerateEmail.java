package com.genesiis.campus.command;

//20161026 DN c10-contacting-us-page created CmdGenerateEmail.java
//20161028 DN c10-contacting-us-page execute(),formatEmailInstance(),composeSingleEmailList()
//sendMail(),createDatabaseConnection() created and defined
//20161031 DN c10-contacting-us-page sendMail() changed, add doc comments and method comments
//									  addContentToOriginalMailBody() initialized
//									setEnvironment() method initialiZed.execute() restructured.

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
	private String userName;
    private String port;
    private String passWord;
    private String host;


/**
 * execute() method generates list of email(s)
 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {		

			 setEnvironment( helper);
			 String cco = helper.getCommandCode();
			 String message = "";
			 ArrayList<Collection<String>> collectionOfCollectionOfEmails =
			 null;
			
			 ICrud genesiis = new SystemConfigDAO();
			 try{
					 switch(Operation.getOperation(cco)){
						 case CONTACT_US_PUBLC:
							 this.createDatabaseConnection();
						
							 String[] sysEmailAdress = {"ENQUIRY_EMAIL_TO","ENQUIRY_EMIL_ADMIN"};
							 collectionOfCollectionOfEmails=(ArrayList<Collection<String>>)genesiis.findById(sysEmailAdress,
						 connection);
						 recieversEmailAddreses= composeSingleEmailList(collectionOfCollectionOfEmails);
						 generalEmail = formatEmailInstance();
						
						 break;
						 default:
						
						 break;
					
					 }					 
			 
			this.sendMail();

			return view;

		 } catch (MessagingException msgexp){
		 log.error("execute():MessagingException "+msgexp.toString());
		 throw msgexp;
		 } catch (SQLException sqle) {
		 log.error("execute():SQLException"+ sqle.toString());
		 throw sqle;
		} catch (Exception e) {
			log.error("execute():SQLException" + e.toString());
			throw e;
		}
	}
	
	/*
	 * setEnvironment() method initializes all the instance variable
	 * @author DN
	 * @param helper IDataHelper
	 */	
	private void setEnvironment(IDataHelper helper){
		//getting the admin related data e.g email address
		 sendersName = helper.getParameter("firstName")
		 .concat(" "+ helper.getParameter("lastName"));
		 sendersEmailAddress = helper.getParameter("emailAddress");
		 sendersphoneNumber= helper.getParameter("contactNumber");
		 mailingSubject = helper.getParameter("subject");
		 mailBody = helper.getParameter("message");
//		 userName = (String)helper.getAttribute("userName");
//		 passWord = (String)helper.getAttribute("password");
//		 port = (String)helper.getAttribute("port");
//		 host = (String)helper.getAttribute("host");
	}
	
	/*
	 * formatEmailInstance() creates an IEmail with email receiver and sender addresses,
	 * host ,SMTP host, subject, mailBody bounded
	 * @author DN
	 * @return IEmail formatted Email out put
	 */
	private IEmail formatEmailInstance() {
		addContentToOriginalMailBody(mailBody);
		IEmail generalEmail = new GeneralMail(recieversEmailAddreses,
				sendersEmailAddress,// host,
				mailingSubject,mailBody);//,userName,passWord,port);
		return generalEmail;

	}

	/*
	 * composeSingleEmailList() adds up all the email structured in Collection
	 * of Collections to a monolithic Collection<String>
	 * @author DN
	 * @param outer accepts Collection<Collection<String>
	 * @return email list as a mono ArrayList<String>
	 */

	private ArrayList<String> composeSingleEmailList(
			Collection<Collection<String>> outer) {
		ArrayList<String> monoList = new ArrayList<String>();
		for (Collection<String> emailAddressList : outer) {
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
		 emailDispenser = new EmailDispenser(generalEmail);
		 emailDispenser.emailDispense();		
	}


	/*
	 * createDatabaseConnection() establishes the database connection with the
	 * data repository
	 * @author DN
	 * @throw SQLException if the connection causes errors.
	 */
	private void createDatabaseConnection() throws SQLException {
		try {
			connection = ConnectionManager.getConnection();
		} catch (SQLException sqle) {
			log.error("add():SQLException :" + sqle.toString());
			throw sqle;
		}
	}
	
	/*
	 * addContentToOriginalMailBody() formats the original details with
	 * users credentials e.g email, contact number, full name
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private void addContentToOriginalMailBody(String originalMailBody){
		StringBuilder result = new StringBuilder();
		
		result.append(originalMailBody) ;
		result.append(System.getProperty("line.separator"));
		result.append(sendersName);
		result.append(System.getProperty("line.separator"));
		result.append(sendersphoneNumber);
		result.append(System.getProperty("line.separator"));
		result.append(sendersEmailAddress);
		this.mailBody = result.toString();
		
	}
	
	


}
