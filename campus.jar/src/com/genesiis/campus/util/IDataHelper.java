package com.genesiis.campus.util;
import javax.servlet.http.HttpSession;

import com.genesiis.campus.entity.IView;
import com.genesiis.campus.validation.ResponseType;

//20161024 DN c10-contacting-us-page created the initial version of the IDataHelper.java
//20161107 DN, JH, DJ, AS, CM, MM Added getAttribute(String) method

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
	
	String getRemoteAddr();
}