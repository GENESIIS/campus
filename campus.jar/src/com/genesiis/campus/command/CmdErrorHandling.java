package com.genesiis.campus.command;

//20170111 PN CAM-72 INIT CmdErrorHandling class. Implementing execute() method WIP.
//20170112 PN CAM-72 modified the execute method to pass error/exception details to the front end on Servlet Exception or Error.
//20170125 PN CAM-72: removed the instantiation of Throwable at declaration of throwable variable.
//20170125 PN CAM-72: modified details view that passes to the JSP page.
//20170202 PN CAM-72: modified the values set of view that passes into the JSP page.
//20170214 PN CAM-72: modified execute function to use Validator.getErrorMessage(statusCode) and Validator.getErrorType(statusCode)
//					  to return error type and error message. error logging modified.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validator;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CmdErrorHandling implements ICommand {
	static Logger log = Logger.getLogger(CmdErrorHandling.class.getName());

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
			// Get error/exception details from the servlet request headers.
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
			errorType = Validator.getErrorType(statusCode);
			errorMessage = Validator.getErrorMessage(statusCode);

			if (throwable != null) {
				exceptionName = throwable.getClass().getName();
				exceptionMessage = throwable.getMessage();
			}
			// Send error details to the front page.
			errorDetails.add(Integer.toString(statusCode));
			errorDetails.add(errorMessage);
			errorDetailsWrapper.add(errorDetails);

			log.info("ERROR DETAILS: TYPE:" + errorType + " CODE:" + statusCode + " SERVLET:" + servletName
					+ " REQUESTED URI:" + requestUri + " MESSAGE:" + errorMessage + "EXCEPTION: " + exceptionName + " "
					+ exceptionMessage);
		} catch (Exception e) {
			log.error("execute() : Exception " + e.toString());
			throw e;
		}
		view.setCollection(errorDetailsWrapper);
		return view;
	}

}
