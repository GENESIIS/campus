//20170206 AS CAM-22 implemented Scrypt encode decode plugging classes. 

package com.genesiis.campus.scrypt.jni;

/**
 * A native library loader that refuses to load libraries.
 *
 * 
 */
public class NilLibraryLoader implements LibraryLoader {
    /**
     * Don't load a shared library.
     *
     * @param name      Name of the library to load.
     * @param verify    Ignored, no verification is done.
     *
     * @return false.
     */
    public boolean load(String name, boolean verify) {
        return false;
    }
}
