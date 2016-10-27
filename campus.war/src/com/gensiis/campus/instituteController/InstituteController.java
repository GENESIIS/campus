package com.gensiis.campus.instituteController;

//20161027 CM c9-make inquiry for institute INIT InstituteController.java


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.genesiis.campus.controller.CampusController;

public class InstituteController extends CampusController {
	
	static Logger log = Logger.getLogger(InstituteController.class.getName());

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
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
			processRequest(request, response);
		}
		
		protected void processRequest(HttpServletRequest 
				  request, HttpServletResponse response)
				  throws ServletException, java.io.IOException {
				  super.doPost(request, response);
		}

	}
	

