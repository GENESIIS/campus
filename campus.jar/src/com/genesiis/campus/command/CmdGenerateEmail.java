package com.genesiis.campus.command;

//20161026 DN c10-contacting-us-page created CmdGenerateEmail.java
//20161028 DN c10-contacting-us-page execute(),formatEmailInstance(),composeSingleEmailList()
//sendMail(),createDatabaseConnection() created and defined
//20161031 DN c10-contacting-us-page sendMail() changed, add doc comments and method comments
//									  addContentToOriginalMailBody() initialized
//									setEnvironment() method initialiZed.execute() restructured.
//20161031 DN c10-contacting-us-page refactor formatEmailInstance() host,user_name,password fields 
//									removed
//20161109 DN c10-contacting-us-page-MP execute() changed to include RecaptureManager codes
//20161111 DN c10-contacting-us-page-MP execute() refactor to include SRP-Single Responsibility Principle. created isReCaptureResponseSuccess()
//						validateFrontEndUserProvidedInformation(),modified sendMail() and systemMessage()
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
import com.genesiis.campus.util.ReCaptchaManager;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validatory;

import java.sql.Connection;
import java.util.Date;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
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
	


/**
 * execute() method in CmdGenerateEmail generates list of email(s) according to the command class
 * chosen by the ICommand factory class. 
 * @author DN
 * @param helper IDataHelper
 * @param view IView
 * @return IView
 * @throws SQLException
 * @throws Exception
 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {		

			int status;
			 setEnvironment( helper);
			 String cco = helper.getCommandCode();
			 String message = "";
			 ArrayList<Collection<String>> collectionOfCollectionOfEmails =
			 null;
			
			 ICrud genesiis = new SystemConfigDAO();
			 try{
				 	validateFrontEndUserProvidedInformation(helper);
				 	boolean isResponseSuccess =isReCaptureResponseSuccess(helper);
				 	
					// Verify whether the input from Human or Robot
				 	if (isResponseSuccess) {
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
					 status=this.sendMail();
					 message = systemMessage(status);
				 } else {
					// Input by Robot
						message = SystemMessage.RECAPTCHAVERIFICATION.message();
				 }
		 } catch (MessagingException msgexp){
			 log.error("execute():MessagingException "+msgexp.toString());
			 throw msgexp;
		 } catch (SQLException sqle) {
			 log.error("execute():SQLException "+ sqle.toString());
			 throw sqle;
		} catch (PrevalentValidation.FailedValidationException e){
			log.error("execute():FailedValidationException "+e.toString());
			message = e.toString();		
			message = message.substring(message.lastIndexOf(":") + 1);
		}catch (IOException ioExpe) {
			log.error("execute():IOException " + ioExpe.toString());
			throw ioExpe;
		} catch (Exception e) {
			log.error("execute():Exception " + e.toString());
			throw e;
		} finally {
			helper.setAttribute("message", message);
		}
			 return view;
	}
	
	/*
	 * isReCaptureResponseSuccess() decides if the google image recapturing
	 * @author DN
	 * @param helper IDataHelper
	 * @return true; boolean, if the capturing is success else false
	 * @throws IOException
	 * @throws Exception
	 */
	private boolean isReCaptureResponseSuccess(IDataHelper helper) throws IOException,Exception{
		final ReCaptchaManager reCaptchaManager = new ReCaptchaManager();
		return reCaptchaManager.sendRequestToServer(helper);
		
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

	}
	
	/*
	 * validateFrontEndUserProvidedInformation() method validates
	 * if the values entered from the user interface is confirm to the
	 * correct values.
	 *  @author DN 
	 * @param helper IDataHelper
	 * @throws Exception 
	 */
	private void validateFrontEndUserProvidedInformation(IDataHelper helper) throws Exception{
		Validatory val = new PrevalentValidation();
		
			val.isNotEmpty(helper.getParameter("firstName"));
			val.isNotEmpty(helper.getParameter("lastName"));			
			val.isValidPhoneNumber(sendersphoneNumber);
			val.validateEmail(sendersEmailAddress);
			val.isNotEmpty(mailingSubject);
			val.isNotEmpty(mailBody);
		
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
				sendersEmailAddress,
				mailingSubject,mailBody);
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
	 * @return int -3 fail sending email 3 sent email successfully 
	 * @throws MessagingException in any case dispensing email fails
	 */

	private int sendMail()  {
		
		int MAIL_SENT_STATUS=3;
		try{ 
			emailDispenser = new EmailDispenser(generalEmail);
			emailDispenser.emailDispense();
		 
		} catch (IllegalArgumentException illearg){
			log.error("sendMail():IllegalArgumentException "+illearg.toString());
			 MAIL_SENT_STATUS= -3;
		} catch (MessagingException msexp) {
			log.error("sendMail():MessagingException "+msexp.toString());
		 MAIL_SENT_STATUS= -3;
		} finally{
			return MAIL_SENT_STATUS;
		}
		
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
	
	/*
	 * systemMessage() handles the system Messages according to
	 * the state of the status passed in
	 * @return String the message
	 * @param status 3 request submitted successfully.
	 * @param status -3 request submition fails.
	 * 
	 */
	private String systemMessage(int status){
		String message = SystemMessage.UNKNOWN.message();
		switch(status){		
		case 3:
			message =SystemMessage.PASS_REQUEST_SUBMISSION.message();
			break;
		case -3:
			message =SystemMessage.FAIL_REQUEST_SUBMISSION.message();
			break;
		default:			
			break;
		}
		return message;
	}
	


}
