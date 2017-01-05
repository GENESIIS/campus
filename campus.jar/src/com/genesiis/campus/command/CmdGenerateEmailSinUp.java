package com.genesiis.campus.command;
// 20161216 DN CAMP:18 Created the CmdGenerateEmailSinUp.java class and Doc comment added.
// 20161222 DN CAMP:18 created accessors and mutators for fields changed the execute() and sendMail() setEnvironment()
//				to introduce the above methods when accessing private fields.
//CmdGenerateEmailSinUp
//20161227 DN CAMP:18 refactor the class to accommodate signUpEmailComposer.java and pushed all remained fields to signUpEmailComposer
//            for reusing the duplicated code
//20161228 DN CAMP:18 switched to static declared type to IEmailComposer in execute(), refactor sendMail() signature to acceptIEmailComposer parameter.
//20161228 DN CAMP:18  move the field signUpEmailComposer to the execute() method to reduce scope of variable to where the variable is used.

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.RowStudentForJason;
import com.genesiis.campus.util.StudentSignUpEmailComposer;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.IEmailComposer;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;

/**
 * CmdGenerateEmailSinUp class handles the email generation
 * when an user account for a Student is created.
 * @author dushantha DN
 *
 */
public class CmdGenerateEmailSinUp implements ICommand {
	static final Logger log = Logger.getLogger(CmdGenerateEmailSinUp.class.getName());

	private RowStudentForJason partialStudent;
	
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		try{ 
			IEmailComposer signUpEmailComposer = new StudentSignUpEmailComposer();
			int status;
			String message = "";
			String gsonData = helper.getParameter("jsonData");
			this.setPartialStudent((RowStudentForJason)extractDumyObjectFrom(gsonData));
			String recieversName = partialStudent.getFirstName().concat(" "+ partialStudent.getLastName());
			signUpEmailComposer.setEnvironment(recieversName,
										partialStudent.getEmail(),
										signUpEmailComposer.composeSingleEmailList(partialStudent.getEmail()),
										SystemMessage.STUDENT_ACCOUNT_CREATE_MAIL_SUBJECT.message(),
										SystemMessage.STUDENT_SUCCESSFULL_CREATTION.message());
			 							
			signUpEmailComposer.setGeneralEmail(signUpEmailComposer.formatEmailInstance(
				 addSpecificContentToOriginalMailBody())); 	
			status=this.sendMail(signUpEmailComposer);
			helper.setAttribute("message", composeOutStatusMessageToClient(status));
			
		}catch (SQLException sexp) {
			log.error("execute(): SQLException "+ sexp.toString());
			throw sexp;
		} catch (IllegalArgumentException ilexp){
			log.error("execute(): IllegalArgumentException"+ ilexp.toString());
			throw ilexp;
		} catch (Exception exp) {
			log.error("execute():Exception "+ exp.toString());
			throw exp;
		}
		return view;
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
	private int sendMail(IEmailComposer signUpEmailComposer)  {
		int MAIL_SENT_STATUS=3;
		try{ 
			if(signUpEmailComposer.getGeneralEmail()== null){
				Exception exp = new Exception("IEmail is not created ");
				log.error("sendMail(): Exception"+exp.toString());
				throw exp;
			}
				
			signUpEmailComposer.setEmailDispenser(
					new EmailDispenser(signUpEmailComposer.getGeneralEmail())
					);
			signUpEmailComposer.getEmailDispenser().emailDispense();
		} catch (IllegalArgumentException illearg){
			log.error("sendMail():IllegalArgumentException "+illearg.toString());
			 MAIL_SENT_STATUS= -3;
		} catch (MessagingException msexp) {
			log.error("sendMail():MessagingException "+msexp.toString());
		 MAIL_SENT_STATUS= -3;
		} catch (NullPointerException msexp) {
			log.error("sendMail():NullPointerException "+msexp.toString());
		 MAIL_SENT_STATUS= -3;
		} catch(Exception exp){
			 MAIL_SENT_STATUS= -3;
		}
		finally{
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
	public Object extractDumyObjectFrom(String gsonData) throws Exception{
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
	 * addSpecificContentToOriginalMailBody() formats the original details with
	 * users credentials e.g email, contact number, full name
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private String addSpecificContentToOriginalMailBody(){
		StringBuilder result = new StringBuilder();		
		result.append(System.getProperty("line.separator"));
		result.append("Account credentials are as follows. ");
		result.append(System.getProperty("line.separator"));
		result.append("User Name :"+partialStudent.getUserName());
		result.append(System.getProperty("line.separator"));
		result.append("Pass word :"+partialStudent.getPassWord());
		result.append(System.getProperty("line.separator"));
		result.append("It's so much pleasure to have you with us. Have a good day!");
		result.append(System.getProperty("line.separator"));
		result.append(SystemMessage.SUPERADMIN_NAME.message());
		return result.toString();
		
	}
	

	/*
	 * systemMessage() handles the system Messages according to
	 * the state of the status passed in
	 * @return String the message
	 * @param status 3 request submitted successfully.
	 * @param status -3 request submission fails.
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

	public RowStudentForJason getPartialStudent() {
		return partialStudent;
	}

	public void setPartialStudent(RowStudentForJason partialStudent) {
		this.partialStudent = partialStudent;
	}
	
	
}