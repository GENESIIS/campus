package com.genesiis.campus.command;

import java.io.IOException;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SigningUpStudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validatory;



import java.sql.Connection;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

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
			message = systemMessage(status);
			
		} catch(SQLException sqle){
			log.error("execute():SQLException "+sqle.toString());
			throw sqle;
		}
		catch (PrevalentValidation.FailedValidationException e){
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
		
		// have to code how to return for JASON data.
		return null;
	}
	

	

	
	
	private Student convertRowStudentForJasonToStudent(){
		Student indusedStudent = new Student();
		indusedStudent.setPassword(partialStudent.getPassWord());
		indusedStudent.setUsername(partialStudent.getUserName());
		indusedStudent.setEmail(partialStudent.getEmail());
		indusedStudent.setGender(Integer.parseInt(partialStudent.getGender()));
		indusedStudent.setFirstName(partialStudent.getFirstName());
		indusedStudent.setLastName(partialStudent.getLastName());
		indusedStudent.setMobilePhoneNo(partialStudent.getMobilePhoneNo());
		return indusedStudent;
	}
	
	
	private void setFrontEndViewingDataIfValidationFails(){
		Collection<Collection<String>> outerCollectionWrapper = new ArrayList<Collection<String>>();
		Collection<String> innerCollection =  new ArrayList<String>();
		innerCollection.add(partialStudent.getFirstName());
		innerCollection.add(partialStudent.getLastName());
		innerCollection.add(partialStudent.getGender());
		innerCollection.add(partialStudent.getMobilePhoneNo());
		innerCollection.add(partialStudent.getEmail());
		innerCollection.add(partialStudent.getPathway());//
		innerCollection.add(partialStudent.getUserName());
		innerCollection.add(partialStudent.getPassWord());
		innerCollection.add(partialStudent.getConfirmPw());
		innerCollection.add(String.valueOf(partialStudent.getIsPolicyConfirm()));
		outerCollectionWrapper.add(innerCollection);
		
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
			val.isNotEmpty(row.getPathway());
			val.isNotEmpty(row.getUserName());
			val.isNotEmpty(row.getPassWord());
			val.isNotEmpty(row.getConfirmPw());
			val.isNotEmpty(String.valueOf(row.getIsPolicyConfirm())); // converts the boolean to String
		
	}
	
	
	
/**
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

/**
 * RowStudentForJason  class maps the JSON data to raw Student object
 * till induce the brought in data from client side to proper  business model object
 * e.g. Student, 
 * @author dushantha DN
 *
 */
	private class RowStudentForJason{
		String firstName ;
		String lastName  ;
		String gender	;
		String email;
		String mobilePhoneNo;
		String pathway	;
		String userName	;
		String passWord	;
		String confirmPw;
		boolean isPolicyConfirm	;
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobilePhoneNo() {
			return mobilePhoneNo;
		}
		public void setMobilePhoneNo(String mobilePhoneNo) {
			this.mobilePhoneNo = mobilePhoneNo;
		}
		public String getPathway() {
			return pathway;
		}
		public void setPathway(String pathway) {
			this.pathway = pathway;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassWord() {
			return passWord;
		}
		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
		public String getConfirmPw() {
			return confirmPw;
		}
		public void setConfirmPw(String confirmPw) {
			this.confirmPw = confirmPw;
		}
		public boolean getIsPolicyConfirm() {
			return isPolicyConfirm;
		}
		public void setIsPolicyConfirm(boolean isPolicyConfirm) {
			this.isPolicyConfirm = isPolicyConfirm;
		}
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
