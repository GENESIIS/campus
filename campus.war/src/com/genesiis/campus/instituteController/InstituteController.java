package com.genesiis.campus.instituteController;
//20161027 AS C8-inquiry-form-for-course CmdSendCourseInquiry class created.
import com.genesiis.campus.controller.CampusController;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * Servlet implementation class InstituteController
 */
@WebServlet("/InstituteController")
public class InstituteController extends CampusController{

	static Logger log = Logger.getLogger(InstituteController.class.getName());
    /**
     * Default constructor. 
     */
    public InstituteController() {
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {

	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest 
			  request, HttpServletResponse response)
			  throws ServletException, java.io.IOException {
			  super.doPost(request, response);
	}

}
