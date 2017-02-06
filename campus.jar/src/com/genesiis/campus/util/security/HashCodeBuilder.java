package com.genesiis.campus.util.security;



//20170206 AS CAM-22 HashCodeBuilder created for testing purpose.  
public class HashCodeBuilder {

	 public static void main(String[] args) {
	        String originalPassword = "password";
	        String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	        System.out.println(generatedSecuredPasswordHash);
	         
	        boolean matched = SCryptUtil.check("password", generatedSecuredPasswordHash);
	        System.out.println(matched);
	         
	        matched = SCryptUtil.check("passwordno", generatedSecuredPasswordHash);
	        System.out.println(matched);
	    }

	
}
