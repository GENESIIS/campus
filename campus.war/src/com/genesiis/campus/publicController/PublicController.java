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
public class PublicController extends HttpServlet{
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
		//super.doGet(request, response);
	}

	/**
	 * @see XenoController#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//super.doPost(request, response);
		
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
//		String json = null;
//		json = new Gson().toJson("Pabodha");
//		response.setContentType("application/json");
//		response.getWriter().write(json);
		
//		request.setAttribute("result", result);
//		request.getRequestDispatcher(helper.getResultPage(cco)).forward(
//				request, response);
		
//		String category = "serial";//request.getParameter("category");
//        List<String> result1 = new ArrayList<String>();
//
//        if (category.equalsIgnoreCase("serial")) {
//                result1.add("Game Of Throme");
//                result1.add("Prison Break");
//                result1.add("Breaking Bad");
//                result1.add("Sherlok Home");
//                result1.add("Suits");
//        } else if (category.equalsIgnoreCase("movies")) {
//                result1.add("Inception");
//                result1.add("War Horse");
//                result1.add("Avatar");
//                result1.add("Titanic");
//                result1.add("Life is Beautiful");
//        } else if (category.equalsIgnoreCase("sports")) {
//                result1.add("Basket Ball");
//                result1.add("Football");
//                result1.add("Tennis");
//                result1.add("Rugby");
//                result1.add("Cricket");
//        }
		List<Object> result1 = new ArrayList<Object>();
        for (Collection<String> view : result.getCollection()) {
        	Object[] category = view.toArray();
        	result1.add(category);
		}
		

		String[] strArr = new String[] {"A", "B", "C", "D"};
        String json = new Gson().toJson(result1);
        response.setContentType("application/json");
        response.getWriter().write(json);
		
		
	}
}
