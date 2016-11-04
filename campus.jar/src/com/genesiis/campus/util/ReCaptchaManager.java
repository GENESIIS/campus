package com.genesiis.campus.util;

//20161026 CM c9-make-inquiry-for-institute INIT ReCaptchaManager.java
//20161026 CM c9-make-inquiry-for-institute Created  sentRequestToServe() method

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.CaptchaResponse;
import com.google.gson.Gson;

public class ReCaptchaManager {

	static Logger log = Logger.getLogger(ReCaptchaManager.class.getName());

	/**
	 * Created for send request to google recaptcha server
	 * @author Chathuri
	 * @param helper
	 * @return boolean
	 */
	public boolean sentRequestToServer(IDataHelper helper) {

		try {
			String gRecaptchaResponse = helper
					.getParameter("g-recaptcha-response");
			log.info(gRecaptchaResponse);
			String secretParameter = "6LfDaQoUAAAAAAA-CQEmfkChxk5Ns8OFh6LlKxUW";

			// Send get request to Google reCaptcha server with secret key
			URL url = new URL(
					"https://www.google.com/recaptcha/api/siteverify?secret="
							+ secretParameter + "&response="
							+ gRecaptchaResponse + "&remoteip="
							+ helper.getRemoteAddr());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}

			System.out.println(outputString);

			// Convert response into Object
			CaptchaResponse captchaResponse = new CaptchaResponse();
			CaptchaResponse capRes = new Gson().fromJson(outputString,
					CaptchaResponse.class);
			if (capRes.isSuccess()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception exception) {
			log.info("sentRequestToServer() :" + exception);
			return false;
		}

	}
}