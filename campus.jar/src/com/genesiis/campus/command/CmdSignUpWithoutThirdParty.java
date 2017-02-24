package com.genesiis.campus.command;
//20161128 DN C18-student-signup-without-using-third-party-application-test-dn created CmdSignUpWithoutThirdParty.java
//20161201 DN C18-student-signup-without-using-third-party-application-test-dn removed unnecessary comments as per CREV
//20161202 DN C18-student-signup-without-using-third-party-application-test-dn add user name validation and validation to the
//	validateFrontEndUserProvidedInformation()
//20161214 DN CAMP:18 changed the convertRowStudentForJasonToStudent() refactor to accommodate user_code
//20161216 DN CAMP:18 add email generation request to execute() and integrate
//				message generated from their with current class.
//				isEmailProduced() created and added exception handling.
//20161218 DN CAMP:18 added successCode int field to keep track of success/failure error status
//20161222 DN CAMP:18 created accessors and mutators for fields changed the execute()
//				to introduce the above methods when accessing private fields. setPartialStudent() amend to handle null instances

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
	private int successCode=0;

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		String message = "";
		
		Collection<Collection<String>> studentSignUps = new ArrayList<Collection<String>>();
		String gsonData = helper.getParameter("jsonData");
		this.setPartialStudent((RowStudentForJason)extractDumyObjectFrom(gsonData));
		ICrud studentDao = new SigningUpStudentDAO();
		
		int status = 0;
		try{
			validateFrontEndUserProvidedInformation(this.getPartialStudent());
			status	= studentDao.add(convertRowStudentForJasonToStudent());
			message = systemMessage(status); 	// then change the message to the client side by appending to the message generated from here
			isEmailProduced(status,helper,view);
			Collection<String> signUpdata = new ArrayList<String>();
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
			String premessage = (String)helper.getAttribute("message");
			premessage=(premessage==null)?"":premessage;
			helper.setAttribute("message", message+" "+premessage);
			helper.setAttribute("successCode", this.getSuccessCode());
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
		indusedStudent.setPassword(this.getPartialStudent().getPassWord());
		indusedStudent.setUsername(this.getPartialStudent().getUserName());
		indusedStudent.setEmail(this.getPartialStudent().getEmail());
		indusedStudent.setGender(Integer.parseInt(this.getPartialStudent().getGender()));
		indusedStudent.setFirstName(this.getPartialStudent().getFirstName());
		indusedStudent.setLastName(this.getPartialStudent().getLastName());
		indusedStudent.setMobilePhoneNo(this.getPartialStudent().getMobilePhoneNo());
		indusedStudent.setMobilePhoneCountryCode(this.getPartialStudent().getMobileCountryCode());
		indusedStudent.setMobilePhoneNetworkCode(this.getPartialStudent().getMobileNetworkCode());
		indusedStudent.setTown(this.getPartialStudent().getTown());
		indusedStudent.setUserTypeCode(Integer.parseInt(this.getPartialStudent().getUserCode()));
		return indusedStudent;
	}
	
	/*
	 * this method manages to set the front end displaying data to the IView object
	 * if the validation fails.
	 */
	private void setFrontEndViewingDataIfValidationFails(IView view){
		Collection<Collection<String>> outerCollectionWrapper = new ArrayList<Collection<String>>();
		Collection<String> innerCollection =  new ArrayList<String>();
		innerCollection.add(this.getPartialStudent().getFirstName());
		innerCollection.add(this.getPartialStudent().getLastName());
		innerCollection.add(this.getPartialStudent().getGender());
		innerCollection.add(this.getPartialStudent().getMobilePhoneNo());
		innerCollection.add(this.getPartialStudent().getEmail());
		innerCollection.add(this.getPartialStudent().getTown());//
		innerCollection.add(this.getPartialStudent().getUserName());
		innerCollection.add(this.getPartialStudent().getPassWord());
		innerCollection.add(this.getPartialStudent().getConfirmPw());
		innerCollection.add(String.valueOf(this.getPartialStudent().getIsPolicyConfirm()));
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
		setSuccessCode(status);
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
	
	/*
	 * isNullInstance () checks if the passed parameter is a null.
	 * @param checkingInstance object thats to be checked if it's null
	 * @return boolean true if it's null object else true
	 */
	private boolean isNullInstance(Object checkingInstance){
		boolean isNull=( checkingInstance == null);
		return isNull;
	}
	
	/*
	 * Accessor and mutator methods
	 */
	public int getSuccessCode() {
		return successCode;
	}


	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}

	public RowStudentForJason getPartialStudent() {
		return partialStudent;
	}


	public void setPartialStudent(RowStudentForJason partialStudent) {
		this.partialStudent = (this.isNullInstance(partialStudent)) ? new RowStudentForJason()
				: partialStudent;
	}
	
	
}
