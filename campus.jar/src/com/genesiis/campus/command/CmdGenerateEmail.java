package com.genesiis.campus.command;

import java.sql.SQLException;
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

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.InstituteInquiryDAO;
import com.genesiis.campus.entity.model.InstituteInquiry;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;
import com.google.gson.Gson;

public class CmdGenerateEmail implements ICommand {

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
		String to = "dushantha@genesiis.com";

		// Sender's email ID needs to be mentioned
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
		properties.put("mail.smtp.password", "password"); // password
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"madushani@genesiis.com", "password");
					}
				});

		// Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage msg = new MimeMessage(session);

			// Set From: header field of the header.
			msg.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			msg.setSubject("This is the Subject Line!");

			// Now set the actual message
			msg.setText("This is actual message");

			// Send message
			Transport.send(msg);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			log.info(mex.toString());
			;
		}

		return view;

	}
}
