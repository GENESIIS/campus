package com.genesiis.campus.controller;

// 20161024 DN c10-contacting-us-page created the initial version of the Servlet Controller
// 20161107 DN, JH, DJ, AS, CM, MM public-controller-testing Changed implementation of process()
// 								to support returning JSON as well as JSP as response
//20161108 JH removed unwanted imports

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
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
/**
 * Servlet implementation class PublicController
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

			if (ResponseType.JSP.equals(responseType)) {  
	
				request.setAttribute("result", result);
				request.getRequestDispatcher(helper.getResultPage(cco))
						.forward(request, response);
				
			} else if (ResponseType.JSON.equals(responseType)) {  
				
				StringBuilder json = new StringBuilder();
				Gson gson = new Gson();
				json.append("{result:");
				if (result.getCollection() != null) {
					
					json.append(gson.toJson(result.getCollection()));
	
					Enumeration<String> attributeNames = request
							.getAttributeNames();
	
					while (attributeNames.hasMoreElements()) {
						String currentAttributeName = attributeNames.nextElement();
						Object object = helper.getAttribute(currentAttributeName);
						String objectInJSON = gson.toJson(object);
						json.append(", " + currentAttributeName + ":" + objectInJSON);
					}
				} else {
					json.append("NO-DATA");
				}
				
				json.append("}");
				
				response.setContentType("application/json");
				response.getWriter().write(json.toString());
			}

		} catch (Exception e) {
			log.error("process(): Exception ", e);
		}
	}
}