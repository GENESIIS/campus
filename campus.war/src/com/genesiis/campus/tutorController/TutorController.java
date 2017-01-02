package com.genesiis.campus.tutorController;

//20170102 DN CAM:47 created the Servlet class for managing tutor related 

import com.genesiis.campus.controller.CampusController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TutorController
 */
@WebServlet("/TutorController")
public class TutorController extends CampusController {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TutorController() {
    	 super();
    }
    
   
	public void init() throws ServletException {

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

}
