//20170206 AS CAM-22 HashCodeBuilder created for testing purpose. 
package com.genesiis.campus.scrypt.crypto;

import org.apache.log4j.Logger;
import com.genesiis.campus.util.ResetPasswordInstructionEmailDispenser;

public class HashCodeBuilder {
	static Logger log = Logger.getLogger(HashCodeBuilder.class.getName());

	/**
	 * Pass the users first name and last name and encode to one string.
	 * 
	 * @param string
	 * @param string
	 * @return string (generated hash code )
	 */

	public String createHash(String firstname, String lastname) {
		String originalHash = firstname + lastname;
		String generatedSecuredHash = SCryptUtil.scrypt(originalHash, 16, 16,
				16);
		log.info(generatedSecuredHash);
		return generatedSecuredHash;
	}

	public void checkHash() {
		String originalPassword = "password";
		String generatedSecuredPasswordHash = SCryptUtil.scrypt(
				originalPassword, 16, 16, 16);
		System.out.println(generatedSecuredPasswordHash);

		boolean matched = SCryptUtil.check("password",
				generatedSecuredPasswordHash);
		System.out.println(matched);

		matched = SCryptUtil.check("passwordno", generatedSecuredPasswordHash);
		System.out.println(matched);
	}

}
