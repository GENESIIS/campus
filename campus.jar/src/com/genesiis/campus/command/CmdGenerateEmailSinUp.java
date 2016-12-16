package com.genesiis.campus.command;
// 20161216 DN CAMP:18 Created the CmdGenerateEmailSinUp.java class and Doc comment added.

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.mail.MessagingException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.RowStudentForJason;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

/**
 * CmdGenerateEmailSinUp class handles the email generation
 * when an user account for a Student is created.
 * @author dushantha DN
 *
 */
public class CmdGenerateEmailSinUp implements ICommand {
	static Logger log = Logger.getLogger(CmdGenerateEmailSinUp.class.getName());
	
	private String recieversName;
	private String sendersEmailAddress;
	private ArrayList<String> recieversEmailAddreses;
	private String sendersphoneNumber;
	private String mailingSubject;
	private String mailBody;
	private Connection connection = null;
	private EmailDispenser emailDispenser;
	private IEmail generalEmail;
	private RowStudentForJason partialStudent;
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		int status;
		String message = "";
		setEnvironment(helper);
		String gsonData = helper.getParameter("jsonData");
		generalEmail = formatEmailInstance();
		status=this.sendMail();
		helper.setAttribute("message", composeOutStatusMessageToClient(status));
		return null;
	}
	
	/*
	 * composeOutStatusMessageToClient(): composes the system message
	 * according to the int parameter sent in to the method.
	 * @author dushantha DN
	 * @param status int value which maps to a numeric table
	 * that has a meaning of the successfulness or un_successfulness
	 * of the the operation. 
	 */
	private String composeOutStatusMessageToClient(int status){
		return systemMessage(status);
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
	 * extractDumyObjectFrom helps extract the json data to a
	 * row object with the same field name similar to the fields json data 
	 * contains
	 * @param gsonData
	 * @return
	 */
	public Object extractDumyObjectFrom(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		Object rowStudentObj = null;
		try {
			rowStudentObj = gson.fromJson(gsonData,
					RowStudentForJason.class);

		} catch (Exception exception) {
			log.error("extractDumyObjectFrom(): " + exception.toString());
			throw exception;
		}
		return rowStudentObj;
	}
	
	/*
	 * setEnvironment() method initializes all the instance variable
	 * @author DN
	 * @param helper IDataHelper
	 */	
	private void setEnvironment(IDataHelper helper){
		 recieversName = partialStudent.getFirstName()
		 .concat(" "+ partialStudent.getLastName());
		 recieversEmailAddreses = wrapedRecieversCollection(partialStudent.getEmail());
		 mailingSubject = "ACCOUNT CREATION";
		 mailBody = "Your User account is Successfully created ";

	}
	
	/*
	 * addContentToOriginalMailBody() formats the original details with
	 * users credentials e.g email, contact number, full name
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private void addContentToOriginalMailBody(String originalMailBody){
		StringBuilder result = new StringBuilder();
		result.append(new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()));
		result.append("Dear ");
		result.append(recieversName);
		result.append(System.getProperty("line.separator"));
		result.append(originalMailBody) ;
		result.append(System.getProperty("line.separator"));
		result.append("Account credentials are as follows ");
		result.append(System.getProperty("line.separator"));
		result.append("User Name :"+partialStudent.getUserName());
		result.append(System.getProperty("line.separator"));
		result.append("Pass word :"+partialStudent.getPassWord());
		result.append(System.getProperty("line.separator"));
		result.append("It's so much plessure to have you with us. Have a good day!");
		this.mailBody = result.toString();
		
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
	
	private ArrayList<String> wrapedRecieversCollection(String anEmailAddress){
		ArrayList<String> singleRecieverList = new ArrayList<String>();
		singleRecieverList.add(anEmailAddress);
		return singleRecieverList;
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
			message =SystemMessage.MAIL_SUCCESS.message();
			break;
		case -3:
			message =SystemMessage.MAIL_UNSUCCESS.message();
			break;
		default:			
			break;
		}
		return message;
	}
	
	
}
