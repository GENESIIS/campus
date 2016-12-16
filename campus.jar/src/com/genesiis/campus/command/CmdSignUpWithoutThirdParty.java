package com.genesiis.campus.command;
//20161128 DN C18-student-signup-without-using-third-party-application-test-dn created CmdSignUpWithoutThirdParty.java
//20161201 DN C18-student-signup-without-using-third-party-application-test-dn removed unnecessary comments as per CREV
//20161202 DN C18-student-signup-without-using-third-party-application-test-dn add user name validation and validation to the
//	validateFrontEndUserProvidedInformation()
//20161214 DN CAMP:18 changed the convertRowStudentForJasonToStudent() refactor to accomadate usercode
//20161216 DN CAMP:18 add email generation request to execute() and integrate
//				message generated from their with current class.
//				isEmailProduced() created and added exception handling.

import java.io.IOException;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpStudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.RowStudentForJason;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validatory;


import java.sql.Connection;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * this class manages the student sign ups to the system without
 * third party application, further it implements ICommand interface
 * @author DN
 *
 */
public class CmdSignUpWithoutThirdParty implements ICommand{

	static Logger log = Logger.getLogger(CmdSignUpWithoutThirdParty.class.getName());
	private RowStudentForJason partialStudent;
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		String message = "";
		Collection<Collection<String>> studentSignUps = new ArrayList<Collection<String>>();
		String gsonData = helper.getParameter("jsonData");
		partialStudent = (RowStudentForJason)extractDumyObjectFrom(gsonData);
		ICrud studentDao = new SigningUpStudentDAO();
		
		int status = 0;
		try{
			validateFrontEndUserProvidedInformation(partialStudent);
			status	= studentDao.add(convertRowStudentForJasonToStudent());
			message = systemMessage(status); 	// then change the message to the client side by appending to the message generated from here
			isEmailProduced(status,helper,view);
			Collection<String> signUpdata = new ArrayList<String>();
			signUpdata.add(null);
			studentSignUps.add(signUpdata);
			view.setCollection(studentSignUps); 
		} catch(SQLException sqle){
			log.error("execute():SQLException "+sqle.toString());
			throw sqle;
		}
		catch (PrevalentValidation.FailedValidationException e){
			log.error("execute():FailedValidationException "+e.toString());
			message = e.toString();		
			message = message.substring(message.lastIndexOf(":") + 1);
			setFrontEndViewingDataIfValidationFails(view);
		}catch (IOException ioExpe) {
			log.error("execute():IOException " + ioExpe.toString());
			throw ioExpe;
		} catch (Exception e) {
			log.error("execute():Exception " + e.toString());
			throw e;
		} finally {
			helper.setAttribute("message", message+" "+(String)helper.getAttribute("message"));
		}
		return view;
	}
	
	
	/*
	 * isEmailProduced() sends an email if the emapilSendingStatus is 1
	 * else it doesn't dispense an email
	 * @author dushantha DN
	 * @throws SQLException
	 * @param emapilSendingStatus
	 * @param helper
	 * @param view
	 */
	private void isEmailProduced(int emapilSendingStatus,IDataHelper helper, IView view) throws SQLException,Exception {
		
			try{
				if(emapilSendingStatus ==1){
					ICommand emailSignUp = new CmdGenerateEmailSinUp();
					emailSignUp.execute(helper, view); //send email
				}
			} catch (SQLException sqle){
				log.error("isEmailProduced: SQLException " + sqle.toString());
				throw sqle;
		} catch (Exception exp){
			log.error("isEmailProduced: Exception " + exp.toString());
			throw exp;
	}
	}
	
/*
 * convertRowStudentForJasonToStudent produces the Student
 * from the Row data obtained from the client side
 * @return Student object
 */
	
	
	private Student convertRowStudentForJasonToStudent(){
		Student indusedStudent = new Student();
		indusedStudent.setPassword(partialStudent.getPassWord());
		indusedStudent.setUsername(partialStudent.getUserName());
		indusedStudent.setEmail(partialStudent.getEmail());
		indusedStudent.setGender(Integer.parseInt(partialStudent.getGender()));
		indusedStudent.setFirstName(partialStudent.getFirstName());
		indusedStudent.setLastName(partialStudent.getLastName());
		indusedStudent.setMobilePhoneNo(partialStudent.getMobilePhoneNo());
		indusedStudent.setMobilePhoneCountryCode(partialStudent.getMobileCountryCode());
		indusedStudent.setMobilePhoneNetworkCode(partialStudent.getMobileNetworkCode());
		indusedStudent.setTown(partialStudent.getTown());
		indusedStudent.setUserTypeCode(Integer.parseInt(partialStudent.getUserCode()));
		return indusedStudent;
	}
	
	/*
	 * this method manages to set the front end displaying data to the IView object
	 * if the validation fails.
	 */
	private void setFrontEndViewingDataIfValidationFails(IView view){
		Collection<Collection<String>> outerCollectionWrapper = new ArrayList<Collection<String>>();
		Collection<String> innerCollection =  new ArrayList<String>();
		innerCollection.add(partialStudent.getFirstName());
		innerCollection.add(partialStudent.getLastName());
		innerCollection.add(partialStudent.getGender());
		innerCollection.add(partialStudent.getMobilePhoneNo());
		innerCollection.add(partialStudent.getEmail());
		innerCollection.add(partialStudent.getTown());//
		innerCollection.add(partialStudent.getUserName());
		innerCollection.add(partialStudent.getPassWord());
		innerCollection.add(partialStudent.getConfirmPw());
		innerCollection.add(String.valueOf(partialStudent.getIsPolicyConfirm()));
		outerCollectionWrapper.add(innerCollection); // now the collection is set with the initial set.
		view.setCollection(outerCollectionWrapper);
	}

	/*
	 * validateFrontEndUserProvidedInformation() method validates
	 * if the values entered from the user interface is confirm to the
	 * correct values.
	 *  @author DN 
	 * @param helper IDataHelper
	 * @throws Exception 
	 */
	private void validateFrontEndUserProvidedInformation(Object partialStudent) throws Exception{
		Validatory val = new PrevalentValidation();
		RowStudentForJason row =(RowStudentForJason)partialStudent;
			val.isNotEmpty(row.getFirstName());
			val.isNotEmpty(row.getLastName());	
			val.isNotEmpty(row.getGender());	
			val.isValidPhoneNumber(row.getMobilePhoneNo());
			val.validateEmail(row.getEmail());
			val.isNotEmpty(row.getTown());
			val.isNotEmpty(row.getUserName());
			val.isStringContainsAlphaNumericAndUnderscore(row.getUserName(), 5);
			val.isNotEmpty(row.getPassWord());
			val.isNotEmpty(row.getConfirmPw());
			val.isNotEmpty(String.valueOf(row.getIsPolicyConfirm())); // converts the boolean to String
		
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
	 * systemMessage() handles the system Messages according to
	 * the state of the status passed in
	 * @return String the message
	 * @param status 3 request submitted successfully.
	 * @param status -3 request submit fails.
	 * 
	 */
	private String systemMessage(int status){
		String message = SystemMessage.UNKNOWN.message();
		switch(status){		
		case 1:
			message =SystemMessage.ACCOUNT_CREATED.message();
			break;
		case -1:
			message =SystemMessage.USER_NAME_EXISTS.message();
			break;
		case -2:
			message =SystemMessage.ACCOUNT_NOT_CREATED.message();
			break;
		default:			
			break;
		}
		return message;
	}
	
	
	
	
}
