package com.genesiis.campus.studentController;

//20161122 MM c5-corporate-training-landing-page - INIT - Initialised file
//20170308 AS C142 Session validation handling and testing 
import com.genesiis.campus.controller.CampusController;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PublicController
 */
@WebServlet("/StudentController")
public class StudentController extends CampusController {
	static Logger log = Logger.getLogger(StudentController.class.getName());
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
	}

	/**
	 * @see CampusController#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see CampusController#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
				super.doPost(request, response);
	
	}
}