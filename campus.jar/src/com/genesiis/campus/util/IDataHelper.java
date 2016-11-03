package com.genesiis.campus.util;

//20161024 DN c10-contacting-us-page created the initial version of the IDataHelper.java
//20161103 CM c9-make-inquiry-for-institute Created getRemoteAddr() method

import javax.servlet.http.HttpSession;
import com.genesiis.campus.entity.IView;

public interface IDataHelper {
	public String getCommandCode();

	public String getResultPage(String cco);	

	public IView getResultView(String cco) throws Exception;

	public String getParameter(String paramName);

	public void setAttribute(String Name, Object o);
	
	public String[] getParameterValues(String name);

	HttpSession getSession(boolean status);
	
	String getRemoteAddress();

	String getHeader(String name);

	String getRedirectPage();

	void setRedirectPage(String pageName);
	
	public Object getAttribute(String attributeName);
	
	String getRemoteAddr();
}