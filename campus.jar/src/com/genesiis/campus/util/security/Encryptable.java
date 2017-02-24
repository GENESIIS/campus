package com.genesiis.campus.util.security;
//20162223 DN C18-student-signup-without-using-third-party-application-dn created Encryptable.java

/**
 * Encryptable provides the facility to
 * decrypting or encrypting 
 * @author dushantha DN
 *
 */
public interface Encryptable {

	byte[] encrypt() throws Exception;
	String decrypt(byte[] message) throws Exception ;
	String encryptSensitiveDataToString() throws Exception;
	String decryptSensitiveDataToString(String encryptedStringTobeDecrypted) throws Exception;
	
}
