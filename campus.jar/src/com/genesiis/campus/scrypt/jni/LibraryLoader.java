//20170206 AS CAM-22 implemented Scrypt encode decode plugging classes. 

package com.genesiis.campus.scrypt.jni;

/**
 * A {@code LibraryLoader} attempts to load the appropriate native library
 * for the current platform.
 *
 * 
 */
public interface LibraryLoader {
    /**
     * Load a native library, and optionally verify any signatures.
     *
     * @param name      Name of the library to load.
     * @param verify    Verify signatures if signed.
     *
     * @return true if the library was successfully loaded.
     */
    boolean load(String name, boolean verify);
}
