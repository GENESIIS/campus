package com.genesiis.campus.ExceptionHandlingController;

//20170111 PN CAM-72: INIT ExceptionHandlingController.java servlet. Implemented processError() method. - WIP
//20170111 PN CAM-72: modified processError() method to extend CampusController servlet.

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genesiis.campus.controller.CampusController;

/**
 * Servlet implementation class ExceptionHandlingController
 */
@WebServlet("/ExceptionHandlingController")
public class ExceptionHandlingController extends CampusController {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ExceptionHandlingController.class.getName());
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}

	private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Exception/Error Details</title></head><body>");
		if (statusCode != 500) {
			out.write("<h3>Error Details</h3>");
			out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
			out.write("<strong>Requested URI</strong>:" + requestUri);
		} else {
			out.write("<h3>Exception Details</h3>");
			out.write("<ul><li>Servlet Name:" + servletName + "</li>");
			out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
			out.write("<li>Requested URI:" + requestUri + "</li>");
			out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
			out.write("</ul>");
		}

		out.write("<br><br>");
		out.write("<a href=\"index.html\">Home Page</a>");
		out.write("</body></html>");
	}
}
