package com.genesiis.campus.util;

//20161121 PN c27-upload-user-image: INIT class to manage uploaded file details.
//20161121 PN c27-upload-user-image: implemented isFileExists() method.
//20161124 PN c27-upload-user-image: implemented isValidImageFileType() method.

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private String imageName;
	private int fileSize;

	/**
	 * constructor
	 * 
	 * @param item:FileItem
	 **/
	public FileUtility(FileItem item) {
		this.item = item;
	}

	/**
	 * constructor
	 * 
	 * @param file:File
	 **/
	public FileUtility(File file) {
		this.file = file;
	}

	/**
	 * constructor
	 * 
	 * @param item:FileItem
	 * @param uploadPath:String
	 **/
	public FileUtility(FileItem item, String uploadPath) {
		this(item);
		this.uploadPath = uploadPath;

	}

	/**
	 * Default constructor
	 */
	public FileUtility() {

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
			log.error(this + "rename" + e.toString());
			throw e;
		}

		return savePath;
	}

	/**
	 * this function uploads a file to the file system If the folder location
	 * does not exist, it creates all parent and child folders.
	 * 
	 * @return boolean file uploaded or not
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
			throw e;
		}
		return this.uploaded;
	}

	/**
	 * @return the absolute path of the file associated with the instance:
	 *         String
	 **/
	public String getUploadedFilePath() {
		return this.file.getAbsolutePath();
	}

	/**
	 * return this file associated with the instance
	 * 
	 * @return File
	 **/
	public File getFile() {
		return this.file;
	}

	/**
	 * set file
	 * 
	 * @param file:File
	 **/
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return uploaded file name
	 */
	public String getImageName() {
		return this.imageName;
	}

	/**
	 * @return uploaded file name
	 */
	public int getFileSize() {
		return this.fileSize;
	}

	/**
	 * parse all possible values in the list and sort them and retrieve the
	 * highest value
	 * 
	 * @param list:ArrayList:String
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

			// FileUtils.cleanDirectory(uploadLocation);

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
			String newfileName = "";
			String ext = "";

			ext = FilenameUtils.getExtension(this.getUploadedFilePath());
			// rename the file using student code
			newfileName = Integer.toString(StudentCode) + "." + ext;

			// create a copy of file
			savePath = folder.getAbsoluteFile() + "/" + newfileName;
			savePath = savePath.substring(savePath.lastIndexOf(".war\\") + 5).replace("\\", "/");
	
			//Delete all the files has same name as studentCode
			File[] listOfFiles = new File(folder.getAbsoluteFile() + "/").listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				// Check if the content inside folder is a file (not a directory)
				if (listOfFiles[i].isFile()) {
					String fileNameWithOutExt = FilenameUtils.removeExtension(listOfFiles[i].getName());
					if (Integer.toString(StudentCode).equals(fileNameWithOutExt)) {
						FileUtils.forceDelete(new File(folder.getAbsoluteFile() + "/" + listOfFiles[i].getName()));
					}
				}
			}
		
			// save the created copy of file
			FileUtils.copyFile(this.file.getAbsoluteFile(), new File(folder.getAbsoluteFile() + "/" + newfileName));
			// delete the original file
			FileUtils.forceDelete(this.file);

			this.renamedTo = newfileName;
		} catch (FileNotFoundException fne) {
			//Comes when C:\\Users\\username\\AppData\\Local\\Temp\\ is not found. It is specific to the user's computer
			log.error("renameIntoOne():  IGNORE THIS: " + fne.toString());
			this.uploaded = false;
			throw fne;
		} catch (Exception e) {
			log.error("renameIntoOne(): " + e.toString());
			this.uploaded = false;
			throw e;
		} 

		return savePath;
	}

	/**
	 * 
	 * @param dir
	 *            - images stored file
	 * @param name
	 *            - file name of the image
	 * @param studentCode
	 * @return true if the file exists
	 */
	public boolean isFileExists(String path, String studentCode) {
		boolean isFileExists = false;
		// Get List of files inside given folder
		File[] listOfFiles = new File(path).listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			// Check if the content inside folder is a file (not a directory)
			if (listOfFiles[i].isFile()) {
				String fileNameWithOutExt = FilenameUtils.removeExtension(listOfFiles[i].getName());
				if (studentCode.equals(fileNameWithOutExt)) {
					this.imageName = listOfFiles[i].getName();
					return true;
				}
			}
		}

		return isFileExists;
	}
	
	/**
	 * This method check if the uploaded file has correct extensions.
	 * @param fileName
	 * @param extensions - array of valid extensions
	 * @return true if valid
	 */
	public boolean isValidImageFileType(String fileName, String extensions[]) {
		// Valid file extensions
		String validExtensions[] = extensions;
		try {

			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			for (String validExtension : validExtensions) {
				if (ext.equalsIgnoreCase(validExtension))
					return true;
			}
		} catch (NullPointerException npe) {
			log.error("isValidcvfiletype(): " + fileName + npe.toString());
			throw npe;
		} catch (StringIndexOutOfBoundsException siobe) {
			log.error("isValidcvfiletype(): " + fileName + siobe.toString());
			throw siobe;
		} catch (Exception e) {
			log.error("isValidcvfiletype(): " + fileName + e.toString());
			throw e;
		}

		return false;
	}

}
