package com.genesiis.campus.instituteController;
//20161027 AS C8-inquiry-form-for-course InstituteController class created.
//20161031 AS C8-inquiry-form-for-course modified doget and dopost methods 
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
      super();
    }

    public void init() throws ServletException {

	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		log.info("Test");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	 

}
