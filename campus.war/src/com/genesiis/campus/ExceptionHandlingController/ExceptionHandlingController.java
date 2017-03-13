package com.genesiis.campus.ExceptionHandlingController;

//20170111 PN CAM-72: INIT ExceptionHandlingController.java servlet. Implemented processError() method. - WIP
//20170111 PN CAM-72: modified processError() method to extend CampusController servlet.
//20170125 PN CAM-72: removed processError(HttpServletRequest request, HttpServletResponse response) method.

import com.genesiis.campus.controller.CampusController;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}

}
