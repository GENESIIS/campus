package com.genesiis.campus.studentController;

//20161122 DN C18-student-signup-without-using-third-party-application-dn created the class
//StudentController.java
//20161122 DN C18-student-signup-without-using-third-party-application-dn removed unnecessary log message

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.genesiis.campus.controller.CampusController;


/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends CampusController {
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getLogger(CampusController.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        
    }
    
	public void init() throws ServletException {

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
