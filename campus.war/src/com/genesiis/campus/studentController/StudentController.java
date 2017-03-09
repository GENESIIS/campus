package com.genesiis.campus.studentController;

//20161122 MM c5-corporate-training-landing-page - INIT - Initialised file
//20170308 AS C142 Session validation handling and testing 
import com.genesiis.campus.controller.CampusController;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ResponseType;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PublicController
 */
@WebServlet("/StudentController")
public class StudentController extends CampusController {
	static Logger log = Logger.getLogger(StudentController.class.getName());
	private static final long serialVersionUID = 1L;
	HttpSession session;
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
		
		//Session validation from backend side. 

			session = request.getSession(false);
			String currentSessionUser = (String) session.getAttribute("currentSessionUser");
			IDataHelper helper = new DataHelper(request, response);
			
			if (currentSessionUser == null) {
				String cco = helper.getCommandCode();
				ResponseType responseType = helper.getResponseType(cco);
				if(ResponseType.JSP.equals(responseType)){
					response.sendRedirect("http://www.campus.dev:8080/dist/partials/error/error-content.jsp");
				}else if(ResponseType.JSON.equals(responseType)){
				//	JSONObject jobj = new JSONObject();
			//		Gson gson = new Gson();
			//		String urlToRedirect = "http://www.campus.dev:8080/dist/partials/error/error-content.jsp";
			//		gson.put("url",urlToRedirect);
			//		response.getWriter().write(gson.toJson("url",urlToRedirect));
					
					response.setHeader("Location", "http://www.campus.dev:8080/dist/partials/error/error-content.jsp");
				}
				
			}else{
				super.doPost(request, response);
			}


		
		
	}
}