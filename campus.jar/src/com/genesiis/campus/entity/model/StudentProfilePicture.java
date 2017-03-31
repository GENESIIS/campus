package com.genesiis.campus.entity.model;

//20161227 PN CAM-27: INIT StudentProfilePicture.java POJO class.

public class StudentProfilePicture {
	private String fileName;
	private long fileSize;
	private String filePath;
	private String fileUploadSuccess;
	private String fileUploadError;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileUploadSuccess() {
		return fileUploadSuccess;
	}
	public void setFileUploadSuccess(String fileUploadSuccess) {
		this.fileUploadSuccess = fileUploadSuccess;
	}
	public String getFileUploadError() {
		return fileUploadError;
	}
	public void setFileUploadError(String fileUploadError) {
		this.fileUploadError = fileUploadError;
	}
	
}
