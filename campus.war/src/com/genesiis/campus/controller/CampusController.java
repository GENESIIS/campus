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
//20170131 AS CAM-20 clear cache data in session null stage.
//20170228 AS CAM-21 Session null checker and result.getCollection() methods removed. 
//20170310 AS CAM-142-clear-browser-cache-data-as - Changed implementation of process(), process() implementation move to mediateResponceOnType(request, response) method. 
//													mediateResponceOnType(request, response) method created, and Session validation handle in process method.
//													if Session is null and request comes from PublicController or LoginController its execute and return the result.
//													otherwise check session already invalidated, then redirect to error-content.jsp page. 
//20170314 AS CAM-142-clear-browser-cache-data-as- rtc 201703131532-DN code review issues fixed, code duplication and hard code URL get from systemconfig enum.
//													addAttributesToResponse(objectMap,  request,  helper) and clearResponseCache(response ) created. code duplication resolved from these two methods.
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.DataHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ResponseType;
import com.genesiis.campus.validation.SystemConfig;
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
		String cco = "";
		helper = new DataHelper(request, response);
		cco = helper.getCommandCode();
		ResponseType responseType = helper.getResponseType(cco);

		try {
			//get Session attribute already available or not
			HttpSession session = request.getSession(false);
			String currentSessionUser = (String) session.getAttribute("currentSessionUser");
			
			//get actual servlet Path
			String servletPath = request.getServletPath(); 
			String redirectURL = SystemConfig.SESSION_EXPIRATION_URL.getValue1(); //session expiration redirect URL 
			Gson gson = new Gson();
			
			//this condition checks Session attribute null or available and only execute the PublicController and LoginController 
			if((currentSessionUser == null) && (servletPath.equalsIgnoreCase("/PublicController") || servletPath.equalsIgnoreCase("/LoginController"))){
				mediateResponceOnType(request, response);

			}else if (currentSessionUser != null ) {
				mediateResponceOnType(request, response);

			}else{
				
				if (ResponseType.JSP.equals(responseType)) {
					response.sendRedirect(redirectURL); //if session null in button action, and its JSP Form submits. Set the Session expiration URL to redirect.
					
				}else{
					request.setAttribute("redirectURL", redirectURL); //if session null in button action, and its AJAX call function. Set the Session expiration URL to redirect.
					Map<String, Object> objectMap = new LinkedHashMap<String, Object>();
					
					addAttributesToResponse(objectMap, request, helper);
				
					response.getWriter().write(gson.toJson(objectMap));
					response.setContentType("application/json");
					
					clearResponseCache(response);
					
				}
			
			}
		} catch (Exception e) {
			log.error("process(): Exception ", e);
		}
	}
	
	/**
	 * mediateResponceOnType method to pass HttpServletRequest and HttpServletResponse , then processes and return the response type and result 
	 * @param request
	 * @param response
	 */
	
	public void mediateResponceOnType(HttpServletRequest request,
			HttpServletResponse response) {

		IDataHelper helper = null;
		IView result = null;
		String cco = "";
		helper = new DataHelper(request, response);
		cco = helper.getCommandCode();
		ResponseType responseType = helper.getResponseType(cco);
		Gson gson = new Gson();

		try {
			result = helper.getResultView(cco);

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

				addAttributesToResponse(objectMap, request, helper);
				response.getWriter().write(gson.toJson(objectMap));
				response.setContentType("application/json");
				clearResponseCache(response);

			}

		} catch (Exception e) {
			log.error("process(): Exception ", e);
		}

	}
	
	/** set result Attributes to object map. 
	 * 
	 * @param objectMap
	 * @param request
	 * @param helper
	 */
	
	public void addAttributesToResponse(Map<String, Object> objectMap, HttpServletRequest request, IDataHelper helper) {
		Enumeration<String> attributeNames = request.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String currentAttributeName = attributeNames.nextElement();
			Object object = helper.getAttribute(currentAttributeName);
			objectMap.put(currentAttributeName, object);
		}
	}
	
	
	/**
	 * Clear cache data in response header 
	 * @param response
	 */
	
	public void clearResponseCache(HttpServletResponse response ){
		response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility 
	}
}