package com.genesiis.campus.validation;

//20161102 JH c7-higher-education-landing-page Validator.java created
//20161102 JH c7-higher-education-landing-page getDuration() created
//20161115 JH c7-higher-education-landing-page isEmpty()method modified to support any string validation
//20161115 JH c7-higher-education-landing-page add method comments
//20161116 MM c5-corporate-training-landing-page-MP Added isNumber(CharSequence) method
//20170214 PN CAM-72: implemented getErrorType(int status) and getErrorMessage(int status) static methods.
import java.util.ArrayList;
import org.apache.log4j.Logger;


public class Validator {
	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Programme durations are stored as days which is to be converted into
	 * years and months. getDuration method used to calculate the duration of a
	 * programme in years, months and days.
	 * 
	 * @param duration
	 * @return object
	 * @author JH
	 */
	public Object getDuration(int duration) {
		ArrayList<String> programDuration = new ArrayList<String>();

		int years = duration / 365;
		duration = duration % 365;
		int month = duration / 30;
		duration = duration % 30;

		programDuration.add(String.valueOf(years));
		programDuration.add(String.valueOf(month));
		programDuration.add(String.valueOf(duration));

		return programDuration;
	}

	/**
	 * isEmpty method used to validate string parameters.
	 * 
	 * @param parameter
	 * @return boolean true, false value
	 */
	public boolean isEmpty(String parameter) {
		boolean valid = false;
		if (parameter == null) {
			valid = true;
		}

		return valid;
	}

	public static boolean isNumber(final CharSequence cs) {
		if (cs == null || cs.toString().isEmpty()) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @author Pabodha Narmadani
	 * Method is to handle the error message according to the error status code.
	 * @param status
	 * @return
	 */
	public static String getErrorMessage(int status) {
		switch (status) {
		/*
		 * Cases to catch 4xx Client Errors. The 4xx class of status codes is
		 * intended for situations in which the client seems to have erred.
		 * Except when responding to a HEAD request, the server should include
		 * an entity containing an explanation of the error situation, and
		 * whether it is a temporary or permanent condition. These status codes
		 * are applicable to any request method. User agents should display any
		 * included entity to the user.
		 */
		case 400:
			return "The server cannot process the request due to an apparent client error.";

		case 401:
			return "Unauthorized request.";

		case 402:
			return "Payment Required.";

		case 403:
			return "Access Forbidden.";

		case 404:
			return "Page Not Found.";

		case 405:
			return "Method Not Allowed.";

		case 406:
			return "Not acceptable request.";

		case 407:
			return "Proxy Authentication Required.";

		case 408:
			return "Request Time-out.";

		case 409:
			return "Request could not be processed because of conflict in the request.";

		case 410:
			return "Resource requested is no longer available and will not be available again.";

		case 411:
			return "The request did not specify the length of its content, which is required by the requested resource.";

		case 412:
			return "The server does not meet one of the preconditions that the requester put on the request.";

		case 413:
			return "The request is larger than the server is willing or able to process.";

		case 414:
			return "The URI provided was too long for the server to process.";

		case 415:
			return "Unsupported Media Type.";

		case 416:
			return "Range Not Satisfiable.";

		case 417:
			return "Expectation Failed.";

		case 418:
			return "I\'m a teapot. Fake error message.";

		case 421:
			return "Misdirected Request.";

		case 422:
			return "Unprocessable Entity.";

		case 423:
			return "The resource that is being accessed is locked.";

		case 424:
			return "Failed Dependency.";

		case 426:
			return "Upgrade Required";

		case 428:
			return "Precondition Required.";

		case 429:
			return "Too Many Requests.";

		case 431:
			return "Request Header Fields Too Large.";

		case 451:
			return "Unavailable For Legal Reasons.";
		/*
		 * Cases to catch 5xx Server Error The server failed to fulfill an
		 * apparently valid request. Response status codes beginning with the
		 * digit "5" indicate cases in which the server is aware that it has
		 * encountered an error or is otherwise incapable of performing the
		 * request. Except when responding to a HEAD request, the server should
		 * include an entity containing an explanation of the error situation,
		 * and indicate whether it is a temporary or permanent condition.
		 * Likewise, user agents should display any included entity to the user.
		 * These response codes are applicable to any request method.
		 */
		case 500:
			return "Internal Server Error.";

		case 501:
			return "Unimplemented method call.";

		case 502:
			return "Bad Gateway.";

		case 503:
			return "The server is currently unavailable";

		case 504:
			return "Gateway time-out";

		case 505:
			return "HTTP Version Not Supported";

		case 506:
			return "The server request results in a circular reference.";

		case 507:
			return "The server is unable to store the representation needed to complete the request.";

		case 508:
			return "The server detected an infinite loop while processing the request.";

		case 510:
			return "Not extended server request.";

		case 511:
			return "The client needs to authenticate to gain network access.";

		default:
			return "Unexpected error encountered.";
		}
	}
	
	/**
	 * @author Pabodha Narmadani
	 * Method is to return the Error type according to the first value of the error code.
	 * @param status
	 * @return
	 */
	public static String getErrorType(int status){
		int firstCharIntValue = Integer.parseInt(Integer.toString(status).substring(0,1));		
		try {
			switch (firstCharIntValue) {
			case 4:
				return "Client Error";
			case 5:
				return "Server Error.";
			default:
				return "Unidentified Error type.";
			}
		} catch (Exception e) {
			log.info("getErrorType(): E: "+ e.toString());
		}
		return "Undefined Error.";
	}
}
