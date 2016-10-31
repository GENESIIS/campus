package com.genesiis.campus.command;

//20161026 CM c9-make-inquiry-for-institute INIT CmdSendInstituteInquiry.java
//20161027 CM c9-make-inquiry-for-institute Modified execute() method
//20161027 CM c9-make-inquiry-for-institute Add method comment
//20161027 CM c9-make-inquiry-for-institute Validate request parameters
//20161031 CM c9-make-inquiry-for-institute Create sendEmail() method for send inquiry
//20161031 CM c9-make-inquiry-for-institute Modified execute() method

import java.sql.SQLException;
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
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;

public class CmdSendInstituteInquiry implements ICommand {

	static Logger log = Logger.getLogger(CmdSendInstituteInquiry.class
			.getName());

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

		try {
			final CourseProviderInquiry instituteInquiry = new CourseProviderInquiry();

			// String gsonData = helper.getParameter("jsonData");
			String validateResult = Validator.validateInquiry(helper);

			if (validateResult.equalsIgnoreCase("True")) {

				String fullname = helper.getParameter("fullname");
				String email = helper.getParameter("email");
				String countryCode = helper.getParameter("countryCode");
				String areaCode = helper.getParameter("areaCode");
				String telNo = helper.getParameter("telNum");
				String inquiryTitle = helper.getParameter("inquiryTitle");
				String inquiry = helper.getParameter("inquiry");
				int studentCode = Integer.parseInt(helper
						.getParameter("studentCode"));
				int corseProviderCode = Integer.parseInt(helper
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
					for (Collection<String> collection : courseProviderEmail) {
						for (String emailAddress : collection) {
							message = sendEmail(emailAddress, inquiryTitle,
									inquiry, email);

							message = SystemMessage.INQUIRYSENT.message();

						}
					}

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
	
	/**
	 * Method for send inquiry to Course provider/Institute
	 * @param email
	 * @param title
	 * @param emailBody
	 * @param studentEmail
	 * @return String
	 */

	public String sendEmail(String email, String title, String emailBody,
			String studentEmail) {

		try {
			String message = "ok";
			String to = email;

			// Sender's email ID needs to be mentioned
			// String from = "topjobs.apptest@gmail.com";
			String from = "madushani@genesiis.com";

			// Assuming you are sending email from localhost
			String host = "localhost";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);

			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.user", from); // User name
			properties.put("mail.smtp.password", "brian1993"); // password
			properties.put("mail.smtp.port", "25");
			properties.put("mail.smtp.auth", "true");

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"madushani@genesiis.com", "brian1993");
						}
					});

			// Create a default MimeMessage object.
			MimeMessage msg = new MimeMessage(session);

			// Set From: header field of the header.
			msg.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			msg.setSubject(title);

			// Now set the actual message
			msg.setText(emailBody + "  Inquiry from :" + studentEmail);

			// Send message
			Transport.send(msg);
			return message;
		} catch (MessagingException mex) {
			log.info(mex.toString());
			return "error";

		}

	}
	// public Object extractFromJason(String gsonData) {
	// Gson gson = new Gson();
	// String message = "";
	// InstituteInquiry instituteInquiry = null;
	// try {
	// instituteInquiry = gson.fromJson(gsonData, InstituteInquiry.class);
	//
	// } catch (Exception exception) {
	// log.error("extractFromJason(): " + exception.toString());
	// throw exception;
	// }
	// return instituteInquiry;
	// }
	//
	// /**
	// * Exctract data from json object and assign it to InstituteInquiry object
	// *
	// * @author Chathuri
	// * @param data
	// * String type
	// * @return InstituteInquery object
	// */
	// private InstituteInquiry getInstituteInquirydetails(String data) {
	//
	// InstituteInquiry instituteInquiry = (InstituteInquiry)
	// extractFromJason(data);
	//
	// return instituteInquiry;
	// }

}
