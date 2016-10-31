package com.genesiis.campus.publicController;

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
public class PublicController extends HttpServlet {
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
	 * @see XenoController#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// super.doGet(request, response);
		this.doPost(request, response);
	}

	/**
	 * @see XenoController#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// super.doPost(request, response);

		IDataHelper helper = null;
		IView result = null;
		String cco = "";
		try {
			helper = new DataHelper(request);
			cco = helper.getCommandCode();
			result = helper.getResultView(cco);

		} catch (Exception e) {
			log.error("process(): ", e);
		}

		// request.setAttribute("result", result);
		// request.getRequestDispatcher(helper.getResultPage(cco)).forward(
		// request, response);

		List<Object> list = new ArrayList<Object>();
		for (Collection<String> view : result.getCollection()) {
			Object[] category = view.toArray();
			list.add(category);
		}

		String json = null;
		json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(json);

	}
}
