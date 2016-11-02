package com.genesiis.campus.command;
//20161027 AS C8-inquiry-form-for-course CmdSendCourseInquiry class created.
//20161101 AS C8-inquiry-form-for-course email class methods implemented 
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.mail.MessagingException;

import com.genesiis.campus.entity.CourseInquiryDAO;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

public class CmdSendCourseInquiry implements ICommand{

	static Logger log = Logger.getLogger(CmdSendCourseInquiry.class.getName());

	private String fullname;
	private Connection connection;
	private String sendersEmail;
	private String countryCode;
	private String areaCode;
	private String telNo;
	private String inquiryTitle;
	private String inquiry;
	private int studentCode;
	private int programmeCode;
	private ArrayList<String> recieversEmailAddreses;
	private EmailDispenser emailDispenser;
	private IEmail generalEmail;
//	private String userName;
//	private String port;
//	private String passWord;
//	private String host;
	
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "Unsuccessfull";
		String gsonData = helper.getParameter("jsonData");
		StudentProgrammeInquiry data = getInstituteInquirydetails(gsonData);
	//	setEnvironment( helper);
		CourseInquiryDAO inquiryDAO = new CourseInquiryDAO();
		int result = inquiryDAO.add(data);
		if (result > 0) {
		//	message = "Inquiry Send successfylly";
			final CourseProviderDAO courseProviderDAO = new CourseProviderDAO();
			Collection<Collection<String>> courseProviderEmail = courseProviderDAO.findById(data);
			recieversEmailAddreses = composeSingleEmailList(courseProviderEmail);
			generalEmail = formatEmailInstance();
			this.sendMail();
		}

		helper.setAttribute("message", message);
		return view;
		
	
	}

	/**
	 * extract data fromm json object and assign to StudentProgrammeInquiry object 
	 * @author anuradha
	 * @param gsonData
	 * @return StudentProgrammeInquiry object 
	 */
	
	private StudentProgrammeInquiry getInstituteInquirydetails(String gsonData) {
		StudentProgrammeInquiry courseInquiry = (StudentProgrammeInquiry) extractFromJason(gsonData);
		
		return courseInquiry;
	}

	public Object extractFromJason(String gsonData) {
		Gson gson = new Gson();
		String message = "";
		StudentProgrammeInquiry courseInquiry = null;
		try {
			courseInquiry = gson.fromJson(gsonData, StudentProgrammeInquiry.class);

		} catch (Exception exception) {
			log.error("extractFromJason(): " + exception.toString());
			throw exception;
		}
		return courseInquiry;
	}
	
	
	/*
	 * setEnvironment() method initializes all the instance variable
	 * 
	 * @author DN
	 * 
	 * @param helper IDataHelper
	 */
	private void setEnvironment(IDataHelper helper) {
		// getting the admin related data e.g email address
		fullname = helper.getParameter("fullname");
		sendersEmail = helper.getParameter("email");
		countryCode = helper.getParameter("countryCode");
		areaCode = helper.getParameter("areaCode");
		telNo = helper.getParameter("telNum");
		inquiryTitle = helper.getParameter("inquiryTitle");
		inquiry = helper.getParameter("inquiry");
//		studentCode = Integer.parseInt(helper.getParameter("studentCode"));
		programmeCode = Integer.parseInt(helper.getParameter("programmeCode"));
//		userName = (String) helper.getAttribute("userName");
//		passWord = (String) helper.getAttribute("password");
//		port = (String) helper.getAttribute("port");
//		host = (String) helper.getAttribute("host");
	}

	/*
	 * formatEmailInstance() creates an IEmail with email receiver and sender
	 * addresses, host ,SMTP host, subject, mailBody bounded
	 * 
	 * @author DN
	 * 
	 * @return IEmail formatted Email out put
	 */
	private IEmail formatEmailInstance() {
		addContentToOriginalMailBody(inquiry);
		IEmail generalEmail = new GeneralMail(recieversEmailAddreses,sendersEmail, inquiryTitle, inquiry);
		return generalEmail;

	}

	/*
	 * composeSingleEmailList() adds up all the email structured in Collection
	 * of Collections to a monolithic Collection<String>
	 * 
	 * @author DN
	 * 
	 * @param outer accepts Collection<Collection<String>
	 * 
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
	 * 
	 * @author DN
	 * 
	 * @throws MessagingException in any case dispensing email fails
	 */

	private void sendMail() throws MessagingException {
		emailDispenser = new EmailDispenser(generalEmail);
		emailDispenser.emailDispense();
	}

	/*
	 * createDatabaseConnection() establishes the database connection with the
	 * data repository
	 * 
	 * @author DN
	 * 
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
	 * addContentToOriginalMailBody() formats the original details with users
	 * credentials e.g email, contact number, full name
	 * 
	 * @param originalMailBody String the original message that the user send to
	 * the SMPT mail server
	 */
	private void addContentToOriginalMailBody(String originalMailBody) {
		StringBuilder result = new StringBuilder();

		result.append(originalMailBody);
		result.append(System.getProperty("line.separator"));
		result.append("Inquiry From : ");
		result.append(System.getProperty("line.separator"));
		result.append(sendersEmail);
		this.inquiry = result.toString();

	}
	
	

}
