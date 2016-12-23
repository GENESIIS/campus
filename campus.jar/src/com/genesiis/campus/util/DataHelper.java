package com.genesiis.campus.util;

//20161024 DN c10-contacting-us-page created initial version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC and refactor getResultPage()
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes to getResultPage()
//20161031 DN c10-contacting-us-page getAttribute() method implemented
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161107 DN, JH, DJ, AS, CM, MM Added implementation of getAttribute(String) method
//20161108 DN, JH, DJ, AS, CM, MM Added implementation of getResponseType(String) method
//20161108 JH Added code to discern an Ajax request from a normal request via checking  
//				whether 'getHeader("x-requested-with")' returns 'XMLHttpRequest' in 
//				getResponseType() method			
//20161108 JH c7-higher-education-landing-page-mp removed unwanted imports
//20161116 MM c2-integrate-google-banners-MP Added call to BannerData.setBannerDetails(IDatahelper, String) 
//				in getResultView(String) method
//20161116 DN c10-contacting-us-page-MP-dn removed the method setContextAttribute(String attributeName,Object value)
// 			due to code review comment by CM

import java.io.IOException;
import java.util.Collection;

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.ResponseType;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.genesiis.campus.validation.BannerData;

public class DataHelper implements IDataHelper {
	static Logger logger = Logger.getLogger(DataHelper.class.getName());

	private static HttpServletRequest request;

	private String cco = "";
	private String commandChoice = "";
	private String redirectPage = "login.jsp";

	public DataHelper(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return String
	 * @param request
	 *            This will pass the CCO (Command Class Code) to the servlet
	 **/
	public String getCommandCode() {
		return request.getParameter("CCO");

	}

	/**
	 * @return String This will be use to select jsp page
	 **/
	@Override
	public String getResultPage(String cco) {
		Operation o = Operation.getOperation(cco);
		return o.getPageURL();
	}
	
	/**
	 * getResponseType(String) Returns the response type bound to each Operation
	 * enum constant.
	 * @return ResponseType Enum constant of type ResponseType indicating what type
	 * of response to send to the client
	 * @param String The value sent by the client as CCO 
	 */	
	@Override
	public ResponseType getResponseType(String cco) {
		Operation o = Operation.getOperation(cco);
		if (Operation.BAD_OPERATION.equals(o)) {
			String headerValue = getHeader("x-requested-with");
			if (headerValue != null && headerValue.equalsIgnoreCase("XMLHttpRequest")) {
				return ResponseType.JSON;
			} else {
				return ResponseType.JSP;
			}
		}
		return o.getResponseType();
	}

	/**
	 * getParameterValues() returns the set of parameter values thats bound to
	 * the passed parameter name if exists. If the seeking parameter name is not
	 * existing method returns null String array
	 * 
	 * @return String array if the parameter exists else null
	 * @param String
	 *            parameter name
	 */
	@Override
	public void setRedirectPage(String pageName) {
		this.redirectPage = pageName;
	}

	@Override
	public String getRedirectPage() {
		return this.redirectPage;
	}

	@Override
	public IView getResultView(String cco) throws Exception {
		IView result = new View();
		String sPath = request.getServletPath();
		try {
			final ICmdFactory factory = FactoryProducer.getFactory(sPath);
			final ICommand iCommand = factory.getCommand(cco);
			if (iCommand != null) {
				result = iCommand.execute(this, result);
				BannerData.setBannerDetails(this, getResultPage(cco));
			}
		} catch (Exception e) {
			logger.info("getResultView() : " + e.toString());
			throw e;
		}
		return result;
	}

	/**
	 * getParameter() returns the parameter value thats bound to the passed
	 * parameter name if exists. If the seeking parameter name is not existing
	 * method returns null String
	 * 
	 * @return String if the parameter exists else null
	 * @param String
	 *            parameter name
	 */
	public String getParameter(String paramName) {
		return request.getParameter(paramName);
	}

	/**
	 * setAttribute() method sets new Attribute to the HttpRequest
	 * 
	 * @param String
	 *            Name of the request attribute
	 * @param Object
	 *            the value of the attribute to be set
	 * @return void
	 */
	@Override
	public void setAttribute(String Name, Object o) {
		request.setAttribute(Name, o);
	}

	/**
	 * getAttribute() method retrieves the value of the attribute that has the
	 * name specified with the "name" parameter, from HttpServletRequest
	 * 
	 * @param String
	 *            Name of the request attribute
	 * @return Object The current value of the attribute
	 */
	@Override
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/**
	 * getParameterValues() returns the set of parameter values thats bound to
	 * the passed parameter name if exists. If the seeking parameter name is not
	 * existing method returns null String array
	 * 
	 * @return String array if the parameter exists else null
	 * @param String
	 *            parameter name
	 */
	@Override
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	/**
	 * getSession() returns a HttpSession binded with request
	 * 
	 * @return HttpSession
	 */
	@Override
	public HttpSession getSession(boolean status) {
		return request.getSession(status);
	}

	/*
	 * getSession() returns an ip address which the request is coming
	 * 
	 * @return String
	 */
	@Override
	public String getRemoteAddress() {
		return request.getRemoteAddr();
	}

	/**
	 * getSession() returns User-Agent which is the browser
	 * 
	 * @return String
	 */
	@Override
	public String getHeader(String name) {
		return request.getHeader(name);

	}

}
