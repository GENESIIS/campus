package com.genesiis.campus.controller;
// 20161024 DN c10-contacting-us-page created the initial version of the Servlet Controller


import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ResponseType;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CampusController
 * extract from XecoController.java
 * 
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
		helper = new DataHelper(request);
		cco = helper.getCommandCode();
		ResponseType responseType = helper.getResponseType(cco);
		try {
			result = helper.getResultView(cco);
			Gson gson = new Gson();

			if (ResponseType.JSP.equals(responseType)) {

				request.setAttribute("result", result);
				request.getRequestDispatcher(helper.getResultPage(cco))
						.forward(request, response);

			} else if (ResponseType.JSON.equals(responseType)) {
				Map<String, Object> objectMap = new LinkedHashMap<String, Object>();

				if (result.getCollection() != null) {
					objectMap.put("result", result.getCollection());

					Enumeration<String> attributeNames = request
							.getAttributeNames();

					while (attributeNames.hasMoreElements()) {
						String currentAttributeName = attributeNames
								.nextElement();
						Object object = helper
								.getAttribute(currentAttributeName);
						objectMap.put(currentAttributeName, object);
					}
				} else {
					objectMap.put("result", "NO-DATA");
				}

				response.setContentType("application/json");
				response.getWriter().write(gson.toJson(objectMap));
			}
		} catch (Exception e) {
			log.error("process(): Exception ", e);
		}
	}
}