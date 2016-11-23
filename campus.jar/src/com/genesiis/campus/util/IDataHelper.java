package com.genesiis.campus.util;

import java.util.ArrayList;
import java.util.Map;

//20161024 DN c10-contacting-us-page created the initial version of the IDataHelper.java
//20161107 DN, JH, DJ, AS, CM, MM Added getAttribute(String) method
//20161121 PN c27-upload-user-image: declared getParameterMap() and getFiles() methods.


import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.validation.ResponseType;


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

	public Map<String, String[]> getParameterMap();

	public ArrayList<FileItem> getFiles() throws FileUploadException;
}