package com.genesiis.campus.util;

//20161024 DN c10-contacting-us-page created initial version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC and refactor getResultPage()
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes to getResultPage()
//20161031 DN c10-contacting-us-page getAttribute() method implemented
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161107 DN, JH, DJ, AS, CM, MM Added implementation of getAttribute(String) method
//20161116 DN c10-contacting-us-page-MP-dn removed the method setContextAttribute(String attributeName,Object value)
// 			due to code review comment by CM
//20170119 Dn CAMP47 changed the method ArrayList<FileItem> getFiles()  to accept List<FileItem> list insted of List<Objects> list

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.genesiis.campus.command.ICommand;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.View;
import com.genesiis.campus.factory.FactoryProducer;
import com.genesiis.campus.factory.ICmdFactory;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.ResponseType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @author pabodha
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
			if (headerValue !=null && headerValue.equalsIgnoreCase("XMLHttpRequest")) {
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
	 * @author PN
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
	 * @author DN
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
	 * @author DN
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
	 * getParameterValues() returns the set of parameter values thats bound to
	 * the passed parameter name if exists. If the seeking parameter name is not
	 * existing method returns null String array
	 * 
	 * @author PN
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
	 * @author PN
	 * @return HttpSession
	 */
	@Override
	public HttpSession getSession(boolean status) {
		return request.getSession(status);
	}

	/*
	 * getSession() returns an ip address which the request is coming
	 * 
	 * @author PN
	 * @return String
	 */
	@Override
	public String getRemoteAddress() {
		return request.getRemoteAddr();
	}

	/**
	 * getSession() returns User-Agent which is the browser 
	 * @author PN
	 * @return String
	 */
	@Override
	public String getHeader(String name) {
		return request.getHeader(name);

	}
	
	/**
	 * getAttribute method returns the attribute value bound to the
	 * request instance by attributeName
	 * @author  DN
	 * @param attributeName String
	 * @return Object 
	 * @since 20161031
	 */
	public Object getAttribute(String attributeName){
		return request.getAttribute(attributeName);
	}
	
	
	/**
	 * getFiles() - method is to get files inside a specific folder in disk.
	 * 
	 * @return ArrayList<FileItem> - contains list of images
	 * @author pabodha
	 */
	@Override
	public ArrayList<FileItem> getFiles() throws FileUploadException{
		ArrayList<FileItem> files = null;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {

			if (request != null && request.getContentType() != null) {
				files = new ArrayList<FileItem>();
				@SuppressWarnings("unchecked")
				List<FileItem> list = upload.parseRequest(request);

				if (list != null) {

					for (Object fileItem : list) {

						FileItem item = (FileItem) fileItem;
						if (!(item.isFormField()))
							files.add(item);

					}

				}

			}
		} catch (Exception e) {
			logger.error("getFiles(): " + e);
			throw e;
		}

		return files;
	}

	
}