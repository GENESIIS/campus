package com.genesiis.campus.loginController;
//20170131 AS CAM-20 NoCacheFilter class created to clear cache details.
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NoCacheFilter
 */
@WebFilter("/NoCacheFilter")
public class NoCacheFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NoCacheFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        PrintWriter out=res.getWriter();  
        out.print("filter is invoked before");
		// pass the request along the filter chain
		chain.doFilter(req, res);
		out.print("filter is invoked after"); 
        
        // cache clear 
        if (!req.getRequestURI().startsWith(req.getContextPath())) {
            // HTTP 1.1.
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            // HTTP 1.0.
            res.setHeader("Pragma", "no-cache");
            // Proxies.
            res.setDateHeader("Expires", 0);
        }
        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
