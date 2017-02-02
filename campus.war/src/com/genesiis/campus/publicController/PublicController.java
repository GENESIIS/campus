package com.genesiis.campus.publicController;

//20161031 DN c10-contacting-us-page setSMPTspecificToRequest() implemented
//20161031 DN c10-contacting-us-page init() implemented
//20161102 DN c10-contacting-us-page init() implemented and 
// setSMPTspecificToRequest() removed
//20161024 DN c10-contacting-us-page created the initial version of the Public Controller
//20161130 PN c27-upload-user-image: removed incorrect import from the class.
import com.genesiis.campus.controller.CampusController;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PublicController
 */
@WebServlet("/PublicController")
public class PublicController extends CampusController {
	static Logger log = Logger.getLogger(PublicController.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicController() {
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