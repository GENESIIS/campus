package com.genesiis.campus.adminController;
//20161123 JH c39-add-course-provider AdminController.java created

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
@WebServlet("/AdminController")
public class AdminController extends CampusController {
	static Logger log = Logger.getLogger(AdminController.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
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