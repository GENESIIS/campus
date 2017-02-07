//20170206 AS CAM-22 HashCodeBuilder created for testing purpose. 
package com.genesiis.campus.scrypt.crypto;

/**
 *
 * @author anuradha
 */
 
public class HashCodeBuilder {
	 
	public void createHash(String firstname, String lastname){
		 String originalHash = firstname+lastname;
		 String generatedSecuredHash = SCryptUtil.scrypt(originalHash, 16, 16, 16);
		 System.out.println(generatedSecuredHash);
	
		// boolean matched = SCryptUtil.check("Anuradhasilva", "$s0$41010$ffwJmgi4Kcxgz3LqB1CRIA==$KyiEoXhpNKozpTCD/2Wn31EiHaUgL8qzMCxIjFfNl3U=");
	    // System.out.println(matched);
	}
	
	
	
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
