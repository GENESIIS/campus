/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//20170206 AS CAM-22 HashCodeBuilder created for testing purpose. 
package com.genesiis.campus.scrypt.crypto;

/**
 *
 * @author anuradha
 */
 
public class HashCodeBuilder {
	 
		 public void checkHash(){
	        String originalPassword = "password";
	        String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	        System.out.println(generatedSecuredPasswordHash);
	         
	        boolean matched = SCryptUtil.check("password", generatedSecuredPasswordHash);
	        System.out.println(matched);
	         
	        matched = SCryptUtil.check("passwordno", generatedSecuredPasswordHash);
	        System.out.println(matched);
	    }

	
}
