package com.genesiis.campus.controller;

//20161024 DN c10-contacting-us-page created the initial version of the Servlet Controller
//20161107 DN, JH, DJ, AS, CM, MM public-controller-testing Changed implementation of process()
//								to support returning JSON as well as JSP as response
//20161108 DN, JH, DJ, AS, CM, PN, MM public-controller-testing-2 Changed implementation of process()
//								to test for ResponseType to decide if JSP or JSON response to send
//20161109 PN, MM public-controller-testing-2 Changed implementation of process() so that when composing 
//								JSON content a Java Map is utilised so the returned JSON is in proper format.
//20161114 MM public-controller-testing-2 Changed implementation of process() so that even when 
//								view.getCollection() returns null, the rest of the Objects set as 
//								attributes to DataHelper are included in the JSON object created
//20170117 AS CAM-21-student-logout-clear-session-details-update-logout-data-as Session details invalidation result.getCollection() modified. 
//20170106 AS CAM-20 Session attributes handled from process method and null sessions also handled
//20170125 AS CAM-20 unwanted loggers and comments removed.

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ResponseType;
import com.genesiis.campus.validation.SystemMessage;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * Servlet implementation class CampusController extract from
 * XenoController.java *
 * 
 */
@WebServlet("/CampusController")
public class CampusController extends HttpServlet {

	static Logger log = Logger.getLogger(CampusController.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		process(request, response);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IDataHelper helper = null;
		IView result = null;
		String cco = "";
		helper = new DataHelper(request, response);
		cco = helper.getCommandCode();
		ResponseType responseType = helper.getResponseType(cco);

		try {
			result = helper.getResultView(cco);
			Gson gson = new Gson();

			HttpSession session = request.getSession(false);

			if (session != null && !session.isNew()) {
				String name = (String) session.getAttribute("name");

				if (ResponseType.JSP.equals(responseType)) {

					request.setAttribute("result", result);
					request.getRequestDispatcher(helper.getResultPage(cco))
							.forward(request, response);

				} else if (ResponseType.JSON.equals(responseType)) {

					Map<String, Object> objectMap = new LinkedHashMap<String, Object>();

					if (result != null && result.getCollection() != null) {
						objectMap.put("result", result.getCollection());
					} else {
						objectMap.put("result", "NO-DATA");
					}

					Enumeration<String> attributeNames = request
							.getAttributeNames();

					while (attributeNames.hasMoreElements()) {
						String currentAttributeName = attributeNames
								.nextElement();
						Object object = helper
								.getAttribute(currentAttributeName);
						objectMap.put(currentAttributeName, object);
					}

					response.getWriter().write(gson.toJson(objectMap));
					response.setContentType("application/json");
				}
			} else {

				Map<String, Object> objectMap = new LinkedHashMap<String, Object>();

				if (result != null && result.getCollection() != null) {
					objectMap.put("result", result.getCollection());
				} else {
					objectMap.put("result", "NO-DATA");
				}

				Enumeration<String> attributeNames = request
						.getAttributeNames();

				while (attributeNames.hasMoreElements()) {
					String currentAttributeName = attributeNames.nextElement();
					Object object = helper.getAttribute(currentAttributeName);
					objectMap.put(currentAttributeName, object);
				}

				response.getWriter().write(gson.toJson(objectMap));
				response.setContentType("application/json");
				getServletContext().getRequestDispatcher(
						"/dist/partials/login.jsp");

			}
		} catch (Exception e) {
			log.error("process(): Exception ", e);
		}
	}
}