package com.genesiis.campus.util;

//20170405 PN CAM-137: ConnectionManagerException class to handle the ConnectionManager class errors.

public class ConnectionManagerException extends RuntimeException {
	private static final long serialVersionUID = 1L; 

	public ConnectionManagerException() {
	}

	public ConnectionManagerException(String s) {
		super(s);
	}

	public ConnectionManagerException(Exception e) {
		super(e.getMessage());
	}

}
