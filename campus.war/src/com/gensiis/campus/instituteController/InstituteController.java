package com.gensiis.campus.instituteController;

//20161027 CM c9-make-inquiry-for-institute INIT InstituteController.java
//20161102 CM c9-make-inquiry-for-institute Removed unused variables

import java.io.IOException;

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
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstituteController() {
		super();
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
		super.doPost(request, response);
	}


}
