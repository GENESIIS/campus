package com.genesiis.campus.publicController;

//20161101 PN c11-criteria-based-filter-search modified the doPost method with a null validation for IView type object.

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genesiis.campus.controller.CampusController;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Category;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

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

	public void init() throws ServletException {

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
