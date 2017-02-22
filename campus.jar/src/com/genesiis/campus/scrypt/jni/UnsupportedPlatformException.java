//20170206 AS CAM-22 implemented Scrypt encode decode plugging classes. 

package com.genesiis.campus.scrypt.jni;

/**
 * Exception thrown when the current platform cannot be detected.
 *
 * 
 */
public class UnsupportedPlatformException extends RuntimeException {
    public UnsupportedPlatformException(String s) {
        super(s);
    }
}
