package com.genesiis.campus.util;
//20161024 DN c10-contacting-us-page created the initial version of the IDataHelper.java
//20161031 DN c10-contacting-us-page getAttribute(String attributeName) implemented
//20161107 DN, JH, DJ, AS, CM, MM Added getAttribute(String) method
//20161108 DN, JH, DJ, AS, CM, MM Added getResponseType(String) method
//20161116 Dn c10-contacting-us-page-MP removed setContextAttribute(String,Object) method
// due to Code review comment
//20161024 DN c10-contacting-us-page created the initial version of the IDataHelper.java
//20161107 DN, JH, DJ, AS, CM, MM Added getAttribute(String) method
//20161121 PN c27-upload-user-image: declared getParameterMap() and getFiles() methods.


import com.genesiis.campus.entity.IView;
import com.genesiis.campus.validation.ResponseType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpSession;

public interface IDataHelper {
	public String getCommandCode();

	public String getResultPage(String cco);

	public IView getResultView(String cco) throws Exception;
	
	public ResponseType getResponseType(String cco);

	public String getParameter(String paramName);

	public void setAttribute(String name, Object o);

	Object getAttribute(String name);
	
	public String[] getParameterValues(String name);

	HttpSession getSession(boolean status);
	
	String getRemoteAddress();

	String getHeader(String name);

	String getRedirectPage();

	void setRedirectPage(String pageName);	
	
	public ArrayList<FileItem> getFiles() throws FileUploadException;

	public Map<String, String[]> getParameterMap();
}
