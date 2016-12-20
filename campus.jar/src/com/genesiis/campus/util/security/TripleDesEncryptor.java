package com.genesiis.campus.util.security;

//20162223 DN C18-student-signup-without-using-third-party-application-dn created TripleDesEncryptor.java

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * TripleDesEncryptor provides Triple Data Encryption Algorithm (TDEA or Triple
 * DEA), It is a symmetric-key block cipher, which applies the Data Encryption
 * Standard (DES) cipher algorithm three times to each data block
 * 
 * @author dushantha DN
 * 
 */

public class TripleDesEncryptor implements Encryptable {
	static Logger log = Logger.getLogger(TripleDesEncryptor.class.getName());
	private String passWord;

	public TripleDesEncryptor(String passWord) {
		this.passWord = passWord;

	}

	public TripleDesEncryptor() {

	}

	/**
	 * encrypt the key value bound to the TripleDesEncryptor instance itself
	 * 
	 * @author dushantha DN
	 * @return byte[] encrypted value
	 */
	@Override
	public byte[] encrypt() throws Exception {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA");
			final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
					.getBytes("utf-8"));// this will produce 20byte length
										// byte[]
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24); // first
																			// key
																			// and
																			// the
																			// last
																			// key
																			// is
																			// the
																			// same
																			// for
																			// the
																			// 24
																			// bits
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}

			final SecretKey key = new SecretKeySpec(keyBytes, "DESede"); // Constructs
																			// a
																			// secret
																			// key
																			// from
																			// the
																			// given
																			// byte
																			// array.
			final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			final byte[] plainTextBytes = passWord.getBytes("utf-8");
			final byte[] cipherText = cipher.doFinal(plainTextBytes);

			return cipherText;
		} catch (java.security.InvalidAlgorithmParameterException e) {
			log.error("Invalid Algorithm :" + e);
		} catch (javax.crypto.NoSuchPaddingException e) {
			log.error("No Such Padding :" + e);
		} catch (java.security.NoSuchAlgorithmException e) {
			log.error("No Such Algorithm :" + e);
		} catch (java.security.InvalidKeyException e) {
			log.error("Invalid Key :" + e);
		} catch (BadPaddingException e) {
			log.error("Invalid Key :" + e);
		} catch (IllegalBlockSizeException e) {
			log.error("Invalid Key :" + e);
		} catch (UnsupportedEncodingException e) {
			log.error("Invalid Key :" + e);
		}

		return null;
	}

	/**
	 * Deycrypt the value sendd in as a byte[] to a String, the same key value
	 * should be used to obtain the decrypted password
	 * 
	 * @author dushantha DN
	 * @param byte[] message decryptable value
	 * @return String decrypted value
	 */
	@Override
	public String decrypt(byte[] message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("SHA");
		final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
				.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		decipher.init(Cipher.DECRYPT_MODE, key, iv);

		final byte[] plainText = decipher.doFinal(message);

		return new String(plainText, "UTF-8");
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String encryptSensitiveDataToString() throws Exception {
		String encrypetedStringFormat = "";
		try {
			if (getPassWord() != null | getPassWord() != "") {
				encrypetedStringFormat = new String(encrypt());
			}
		} catch (Exception exp) {
			log.error("encryptSensitiveDataToString(): Exception "
					+ exp.toString());
			throw exp;
		}
		return encrypetedStringFormat;

	}

	public String decryptSensitiveDataToString(
			String encryptedStringTobeDecrypted) throws Exception {
		String byteArradecrypetedToStringFormat = "";
		byte[] encryptedByteArray = encryptedStringTobeDecrypted
				.getBytes(Charset.forName("UTF-8"));
		try {
			byteArradecrypetedToStringFormat = decrypt(encryptedByteArray);
		} catch (Exception e) {
			log.error("decryptSensitiveDataToString() :" + e);
		}
		return byteArradecrypetedToStringFormat;

	}

}