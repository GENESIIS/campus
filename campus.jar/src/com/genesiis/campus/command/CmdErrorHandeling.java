package com.genesiis.campus.command;

//20170111 PN CAM-72 INIT CmdErrorHandeling class. Implementing execute() method WIP.
//20170112 PN CAM-72 modified the execute method to pass error/exception details to the front end on Servlet Exception or Error.
//20170125 PN CAM-72: removed the instantiation of Throwable at declaration of throwable variable.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdErrorHandeling implements ICommand {
	static Logger log = Logger.getLogger(CmdErrorHandeling.class.getName());

	// Variable declarations to hold he error details.
	private String errorType = "";
	private Integer statusCode = -11;
	private String servletName = "";
	private Throwable throwable;
	private String requestUri = "";
	private String errorMessage = "";
	private String exceptionName = "";
	private String exceptionMessage = "";

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException, Exception {

		Collection<Collection<String>> errorDetailsWrapper = new ArrayList<Collection<String>>();
		Collection<String> errorDetails = new ArrayList<String>();

		try {

			throwable = (Throwable) helper.getAttribute("javax.servlet.error.exception");
			statusCode = (Integer) helper.getAttribute("javax.servlet.error.status_code");
			servletName = (String) helper.getAttribute("javax.servlet.error.servlet_name");

			if (servletName == null) {
				servletName = "Unknown";
			}
			requestUri = (String) helper.getAttribute("javax.servlet.error.request_uri");
			if (requestUri == null) {
				requestUri = "Unknown";
			}

			// Analyze the servlet exception
			// This condition checks for the error status code, to show the
			// different error messages to the user.
			if (statusCode == 404) {
				errorType = SystemMessage.SYSTEM_ERROR.message();
				errorMessage = SystemMessage.ERROR404.message();
			} else {
				errorType = SystemMessage.SYSTEM_EXCEPTION.message();
				exceptionName = throwable.getClass().getName();
				exceptionMessage = SystemMessage.ERROR500.message() + " " + throwable.getMessage();
			}

			log.info(errorMessage + " " + errorType + " " + exceptionMessage + " " + exceptionName + " " + requestUri
					+ " " + servletName);
			// Send error details to the front page.
			errorDetails.add(errorType);
			errorDetails.add(Integer.toString(statusCode));
			errorDetails.add(servletName);
			errorDetails.add(requestUri);
			errorDetails.add(errorMessage);
			errorDetails.add(exceptionName);
			errorDetails.add(exceptionMessage);
			errorDetailsWrapper.add(errorDetails);
		} catch (Exception e) {
			log.info("execute() : Exception " + e.toString());
			throw e;
		}
		view.setCollection(errorDetailsWrapper);
		return view;
	}

}
