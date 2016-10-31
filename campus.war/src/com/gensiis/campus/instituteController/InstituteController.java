package com.gensiis.campus.instituteController;

//20161027 CM c9-make-inquiry-for-institute INIT InstituteController.java

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.genesiis.campus.controller.CampusController;

/**
 * Servlet implementation class CompanyController
 */
@WebServlet("/InstituteController")
public class InstituteController extends CampusController {
	static Logger log = Logger.getLogger(InstituteController.class.getName());
	private static final long serialVersionUID = 1L;
	private String userName;
	private String port;
	private String password;
	private String host;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstituteController() {
		super();
	}

	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		this.userName = application.getInitParameter("user");
		this.password = application.getInitParameter("password");
		this.host = application.getInitParameter("host");
		this.port = application.getInitParameter("port");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setSMPTspecificToRequest(request);
		super.doPost(request, response);
	}

	/*
	 * setSMPTspecificToRequest() facilitates in binding SMPT specific data to
	 * request as an attribute e.g. username,host,port number and pass word
	 * 
	 * @param request
	 */
	private void setSMPTspecificToRequest(HttpServletRequest request) {
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("host", host);
		request.setAttribute("port", port);

	}
}
