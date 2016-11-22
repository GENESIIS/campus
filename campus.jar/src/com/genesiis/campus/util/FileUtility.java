package com.genesiis.campus.util;

//20161121 PN c27-upload-user-image: INIT class to manage uploaded file details.

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.apache.log4j.Logger;

public class FileUtility {

	private Logger log = Logger.getLogger(FileUtility.class.getName());

	private File file;
	private FileItem item;
	private String uploadPath;
	private boolean uploaded;
	private String renamedTo;

	/**
	 * constructor
	 * 
	 * @param item
	 *            :FileItem
	 **/
	public FileUtility(FileItem item) {
		this.item = item;
	}

	/**
	 * constructor
	 * 
	 * @param file
	 *            :File
	 **/
	public FileUtility(File file) {
		this.file = file;
	}

	/**
	 * constructor
	 * 
	 * @param item
	 *            :FileItem
	 * @param uploadPath
	 *            :String
	 **/
	public FileUtility(FileItem item, String uploadPath) {
		this(item);
		this.uploadPath = uploadPath;

	}

	public FileUtility() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This function renames the file associated with the instance
	 * 
	 * @return the path to the renamed image.
	 * @throws Exception
	 **/
	public String rename() throws Exception {
		String savePath = "";
		try {

			File folder = new File(this.getFile().getParent());
			File[] paths = folder.listFiles();
			String fileName = "";
			String ext = "";

			ArrayList<String> list = new ArrayList<String>();

			for (File f : paths)
				list.add(f.getName().replaceFirst("[.][^.]+$", ""));

			String highestValue = this.getTopOfSequence(list);

			ext = FilenameUtils.getExtension(this.getUploadedFilePath());

			if (highestValue == null || highestValue.equalsIgnoreCase(""))
				fileName = "0." + ext;
			else
				fileName = Integer.toString((Integer.parseInt(highestValue) + 1)) + "." + ext;

			savePath = folder.getAbsoluteFile() + "/" + fileName;
			savePath = savePath.substring(savePath.lastIndexOf(".war\\") + 5).replace("\\", "/");

			FileUtils.copyFile(this.file.getAbsoluteFile(), new File(folder.getAbsoluteFile() + "/" + fileName));
			FileUtils.forceDelete(this.file);

			this.renamedTo = fileName;

		} catch (Exception e) {
			this.log.error(this + "rename" + e.toString());
			throw new Exception(e);
		}

		return savePath;
	}

	/**
	 * this function uploads a file to the file system If the folder location
	 * does not exist, it creates all parent and child folders.
	 * 
	 * @return boolean file uploaded or not
	 * @author LE
	 * @throws Exception
	 **/
	public boolean upload() throws Exception {

		try {
			this.uploaded = false;
			File uploadLocation = new File(this.uploadPath);

			if (!uploadLocation.isDirectory())
				uploadLocation.mkdirs();

			String fileName = this.item.getName();

			/**
			 * check whether the / exists at the end of the upload path
			 **/
			if (this.uploadPath.length() - 1 == this.uploadPath.lastIndexOf("/"))
				this.file = new File(this.uploadPath + fileName);
			else
				this.file = new File(this.uploadPath + "/" + fileName);

			/**
			 * write the file to the file system
			 **/
			if (this.file.exists())
				this.file = new File(this.uploadPath + "/(copy)" + fileName);

			this.item.write(this.file);
			this.uploaded = true;

		} catch (Exception e) {
			log.error(this + ".uploadFile, " + e.toString());
			this.uploaded = false;
			throw new Exception(e);
		}

		return this.uploaded;
	}

	/**
	 * @return the absolute path of the file associated with the instance:
	 *         String
	 * @author LE
	 **/
	public String getUploadedFilePath() {
		return this.file.getAbsolutePath();
	}

	/**
	 * return this file associated with the instance
	 * 
	 * @return File
	 * @author LE
	 **/
	public File getFile() {
		return this.file;
	}

	/**
	 * set file
	 * 
	 * @param file
	 *            :File
	 * @author LE
	 **/
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * parse all possible values in the list and sort them and retrieve the
	 * highest value
	 * 
	 * @param list
	 *            :ArrayList:String
	 * @return the highest value
	 **/
	private String getTopOfSequence(ArrayList<String> list) {

		String name = "";
		ArrayList<Integer> values = new ArrayList<Integer>();

		for (String each : list) {
			try {
				values.add(Integer.parseInt(each));
			} catch (NumberFormatException e) {
				log.info(this + "ignore: nfe on parsing: " + each);
			}

		}

		if (values.size() > 0) {

			Collections.sort(values);
			name = Integer.toString(values.get(values.size() - 1));
		}

		return name;

	}

	public void setFileItem(FileItem item) {
		this.item = item;
	}

	public void setUploadPath(String path) {
		this.uploadPath = path;
	}

	public String getNewName() {
		return this.renamedTo;
	}
	
	/**
	 * This function renames the file associated with the instance into one
	 * 
	 * @return the path to the renamed image.
	 * @throws Exception
	 **/
	public String renameIntoOne(int StudentCode) throws Exception {
		String savePath = "";
		try {			
			this.uploaded = false;
			File uploadLocation = new File(this.uploadPath);

			if (!uploadLocation.isDirectory())
				uploadLocation.mkdirs();

			FileUtils.cleanDirectory(uploadLocation); 
			
			String fileName = this.item.getName();

			/**
			 * check whether the / exists at the end of the upload path
			 **/
			if (this.uploadPath.length() - 1 == this.uploadPath.lastIndexOf("/"))
				this.file = new File(this.uploadPath + fileName);
			else
				this.file = new File(this.uploadPath + "/" + fileName);

			/**
			 * write the file to the file system
			 **/
			if (this.file.exists())
				this.file = new File(this.uploadPath + "/(copy)" + fileName);

			this.item.write(this.file);
			this.uploaded = true;
			
			
			File folder = new File(this.getFile().getParent());
			File[] paths = folder.listFiles();
			String newfileName = "";
			String ext = "";
			
			
			ext = FilenameUtils.getExtension(this.getUploadedFilePath());

			newfileName = Integer.toString(StudentCode) + "." + ext;

			savePath = folder.getAbsoluteFile() + "/" + newfileName;
			savePath = savePath.substring(savePath.lastIndexOf(".war\\") + 5).replace("\\", "/");

			FileUtils.copyFile(this.file.getAbsoluteFile(), new File(folder.getAbsoluteFile() + "/" + newfileName));
			FileUtils.forceDelete(this.file);

			this.renamedTo = newfileName;
			

		} catch (Exception e) {
			log.error(this + ".uploadFile, " + e.toString());
			this.uploaded = false;
			throw new Exception(e);
		}

		return savePath;
	}
	
}
