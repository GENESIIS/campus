package com.genesiis.campus.command;

//20161026 CM c9-make-inquiry-for-institute INIT CmdSendInstituteInquiry.java
//20161027 CM c9-make-inquiry-for-institute Modified execute() method
//20161027 CM c9-make-inquiry-for-institute Add method comment
//20161027 CM c9-make-inquiry-for-institute Validate request parameters
//20161031 CM c9-make-inquiry-for-institute Create sendEmail() method for send inquiry
//20161031 CM c9-make-inquiry-for-institute Modified execute() method

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.CourseProviderInquiryDAO;
import com.genesiis.campus.entity.model.CourseProviderInquiry;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.mail.EmailDispenser;
import com.genesiis.campus.util.mail.GeneralMail;
import com.genesiis.campus.util.mail.IEmail;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;

public class CmdSendInstituteInquiry implements ICommand {

	static Logger log = Logger.getLogger(CmdSendInstituteInquiry.class
			.getName());

	private String fullname;
	private Connection connection;
	private String email;
	private String countryCode;
	private String areaCode;
	private String telNo;
	private String inquiryTitle;
	private String inquiry;
	private int studentCode;
	private int corseProviderCode;
	private ArrayList<String> recieversEmailAddreses;
	private EmailDispenser emailDispenser;
	private IEmail generalEmail;
	private String userName;
	private String port;
	private String passWord;
	private String host;

	/**
	 * @author Chathuri
	 * @param helepr
	 *            IDataHelper object of Object type view IView object of object
	 *            type
	 * @return View object to servlet
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		String message = "";
		setEnvironment( helper);
		try {
			final CourseProviderInquiry instituteInquiry = new CourseProviderInquiry();

			// String gsonData = helper.getParameter("jsonData");
			String validateResult = Validator.validateInquiry(helper);

			if (validateResult.equalsIgnoreCase("True")) {

				fullname = helper.getParameter("fullname");
				email = helper.getParameter("email");
				countryCode = helper.getParameter("countryCode");
				areaCode = helper.getParameter("areaCode");
				telNo = helper.getParameter("telNum");
				inquiryTitle = helper.getParameter("inquiryTitle");
				inquiry = helper.getParameter("inquiry");
				studentCode = Integer.parseInt(helper
						.getParameter("studentCode"));
				corseProviderCode = Integer.parseInt(helper
						.getParameter("courseProviderCode"));

				// InstituteInquiry data = getInstituteInquirydetails(gsonData);

				instituteInquiry.setStudentName(fullname);
				instituteInquiry.setStudentEmail(email);
				instituteInquiry.setTelephoneCountryCode(countryCode);
				instituteInquiry.setTelephoneAreaCode(areaCode);
				instituteInquiry.setTelNo(telNo);
				instituteInquiry.setInquiryTitle(inquiryTitle);
				instituteInquiry.setInquiryText(inquiry);
				instituteInquiry.setStudent(studentCode);
				instituteInquiry.setCourseProvider(corseProviderCode);

				final CourseProviderInquiryDAO inquiryDAO = new CourseProviderInquiryDAO();
				final CourseProviderDAO courseProviderDAO = new CourseProviderDAO();
				int result = inquiryDAO.add(instituteInquiry);
				if (result > 0) {
					Collection<Collection<String>> courseProviderEmail = courseProviderDAO
							.findById(instituteInquiry);

					recieversEmailAddreses = composeSingleEmailList(courseProviderEmail);
					generalEmail = formatEmailInstance();
					this.sendMail();
					message = SystemMessage.INQUIRYSENT.message();
					// for (Collection<String> collection : courseProviderEmail)
					// {
					// for (String emailAddress : collection) {
					// // message = sendEmail(emailAddress, inquiryTitle,
					// // inquiry, email);
					// //
					// message = SystemMessage.INQUIRYSENT.message();
					//
					// }
					// }

				} else {

					message = SystemMessage.ERROR.message();
				}
			} else {
				message = validateResult;

			}

		} catch (Exception exception) {
			log.error("execute() : " + exception);
			message = SystemMessage.ERROR.message();
			throw exception;
		} finally {
			helper.setAttribute("message", message);

		}
		return view;
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
		email = helper.getParameter("email");
		countryCode = helper.getParameter("countryCode");
		areaCode = helper.getParameter("areaCode");
		telNo = helper.getParameter("telNum");
		inquiryTitle = helper.getParameter("inquiryTitle");
		inquiry = helper.getParameter("inquiry");
		studentCode = Integer.parseInt(helper.getParameter("studentCode"));
		corseProviderCode = Integer.parseInt(helper
				.getParameter("courseProviderCode"));
		userName = (String) helper.getAttribute("userName");
		passWord = (String) helper.getAttribute("password");
		port = (String) helper.getAttribute("port");
		host = (String) helper.getAttribute("host");
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
		IEmail generalEmail = new GeneralMail(recieversEmailAddreses,
				email, host, inquiryTitle, inquiry, userName,
				passWord, port);
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
		result.append(email);
//		result.append(System.getProperty("line.separator"));
//		result.append(sendersphoneNumber);
//		result.append(System.getProperty("line.separator"));
//		result.append(sendersEmailAddress);
		this.inquiry = result.toString();

	}
}
