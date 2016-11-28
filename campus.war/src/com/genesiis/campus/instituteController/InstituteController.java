package com.genesiis.campus.instituteController;
//20161027 AS C8-inquiry-form-for-course InstituteController class created.
//20161031 AS C8-inquiry-form-for-course modified doget and dopost methods 
//20161128 PN C8-inquiry-form-for-course changed the logger class imports.

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genesiis.campus.controller.CampusController;
/**
 * Servlet implementation class InstituteController
 */
@WebServlet("/InstituteController")
public class InstituteController extends CampusController{

	static Logger log = Logger.getLogger(InstituteController.class.getName());
	private static final long serialVersionUID = 1L;
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	 

}
