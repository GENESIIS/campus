package com.genesiis.campus.util;

//20161026 CM c9-make-inquiry-for-institute INIT ReCaptchaManager.java
//20161026 CM c9-make-inquiry-for-institute Created  sentRequestToServe() method
//20161109 CM c9-make-inquiry-for-institute Modified  sentRequestToServe() method
//20161109 CM c9-make-inquiry-for-institute close HttpConnection and BufferReader.
//20161109 CM c9-make-inquiry-for-institute Renamed  sentRequestToServe() method as sendRequestToServer
//20161125 AS c8-make-inquiry-for-course removed unwanted loggers 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class ReCaptchaManager {

	static Logger log = Logger.getLogger(ReCaptchaManager.class.getName());

	/**
	 * Created for send request to google recaptcha server
	 * 
	 * @author Chathuri
	 * @param helper
	 * @return boolean
	 */
	public boolean sendRequestToServer(IDataHelper helper) throws IOException,
			Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		boolean result = false;
		String gRecaptchaResponse = null;
		try {
			

			if (helper.getParameter("recapture") == null
					|| helper.getParameter("g-recaptcha-response") == null) {

				if (helper.getParameter("recapture") != null) {
					gRecaptchaResponse = helper.getParameter("recapture");
				} else if (helper.getParameter("g-recaptcha-response") != null) {
					gRecaptchaResponse = helper.getParameter("g-recaptcha-response");
				}
					
			
			String secretParameter = "6LfDaQoUAAAAAAA-CQEmfkChxk5Ns8OFh6LlKxUW";

			// Send get request to Google reCaptcha server with secret key
			URL url = new URL(
					"https://www.google.com/recaptcha/api/siteverify?secret="
							+ secretParameter + "&response="
							+ gRecaptchaResponse + "&remoteip="
							+ helper.getRemoteAddr());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String line, outputString = "";
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}

			// Convert response into Object
			CaptchaResponse captchaResponse = new CaptchaResponse();
			CaptchaResponse capRes = new Gson().fromJson(outputString,
					CaptchaResponse.class);
			if (capRes.isSuccess()) {
				result = true;
			} else {
				result = false;
			}
			
			
		}
		} catch (IOException ioException) {
			log.error("sendRequestToServer() :" + ioException);
			throw ioException;
		} catch (Exception exception) {
			log.error("sendRequestToServer() :" + exception);
			throw exception;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (reader != null) {
				reader.close();
			}
		}
		return result;

	}
}