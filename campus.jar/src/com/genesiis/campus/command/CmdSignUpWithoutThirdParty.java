package com.genesiis.campus.command;

import java.sql.SQLException;



import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.StudentDAO;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.Validatory;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;
import com.genesiis.campus.util.ConnectionManager;

import java.sql.Connection;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

public class CmdSignUpWithoutThirdParty implements ICommand{

	static Logger log = Logger.getLogger(CmdSignUpWithoutThirdParty.class.getName());
	private RowStudentForJason partialStudent;
	private Encryptable passwordEncryptor;
	private Connection conn ;
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		//accept all the data from the front end back end data validation
		// send the password and username to back end and  validate if they exist
		//else return and give error
		// if passes then write to the data base
		
		String message = "";
		String gsonData = helper.getParameter("jsonData");
		partialStudent = (RowStudentForJason)extractDumyObjectFrom(gsonData);
		ICrud studentDao = new StudentDAO();
		Collection<Collection<String>> userNames = new ArrayList<Collection<String>>();
		
		try{
			validateFrontEndUserProvidedInformation(partialStudent);
			passwordEncryptor = new TripleDesEncryptor(partialStudent.getPassWord()); ////CHECK FOR CORRECT PASS WORD ENCRYPTION PRIOR TO SENF TO DATA BASE
			conn =  ConnectionManager.getConnection();
			userNames	= studentDao.findById(induceRowStudentForJasonToStudent(), conn);
			
			
		} catch(SQLException sqle){
			log.error("execute():SQLException "+sqle.toString());
			throw sqle;
		}
		catch (PrevalentValidation.FailedValidationException e){
			log.error("execute():FailedValidationException "+e.toString());
			message = e.toString();		
			message = message.substring(message.lastIndexOf(":") + 1);
		} finally {
			
		}
		
		
		return null;
	}
	
	private Student induceRowStudentForJasonToStudent(){
		Student std = new Student();
		std.setPassword(partialStudent.getPassWord());
		std.setUsername(partialStudent.getUserName());
		std.setEmail(partialStudent.getEmail());
		std.setGender(Integer.parseInt(partialStudent.getGender()));
		std.setFirstName(partialStudent.getFirstName());
		std.setLastName(partialStudent.getLastName());
		std.setMobilePhoneNo(partialStudent.getMobilePhoneNo());
		return null;
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
}
