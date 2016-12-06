package com.genesiis.campus.util;
//20161206 AS C19-student-login-without-using-third-party-application-test-as  CookieHandler created for handle cookies. 
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHandler {

	public static String getCookieValue(HttpServletRequest request, String name) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (name.equals(cookie.getName())) {
	                return cookie.getValue();
	            }
	        }
	    }
	    return null;
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
	    Cookie cookie = new Cookie(name, value);
	    cookie.setPath("/");
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	    
	}

	public static void removeCookie(HttpServletResponse response, String name) {
	    addCookie(response, name, null, 0);
	}
	
}
