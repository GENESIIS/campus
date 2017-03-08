package com.genesiis.campus.util;

//20161121 PN c27-upload-user-image: INIT class to manage uploaded file details. File taken from SDB project.
//20161121 PN c27-upload-user-image: implemented isFileExists() method.
//20161124 PN c27-upload-user-image: implemented isValidImageFileType() method.
//20161202 PN c27-upload-user-image: expressions arranged within if() statement and for() loop body ,is enclosed within the "{" "}".
//		   PN c27-upload-user-image: implemented isFileExistsEndofUP() and createCopyofFile() methods.
//20170110 DN c47-tutor-add-tutor-information-upload-image-dn getFileItem() method
//20170216 DN c131-admin-manage-banner-upload-banner-image-dn created the method moveFileTODiferentDirectory(String,FileItem,boolean)
//20170222 DN c131-admin-manage-banner-upload-banner-image-dn implement the method deleteDirectory(String)
//20170223 DN c131-admin-manage-banner-upload-banner-image-dn copyFile() is introduced.
//20170224 DN c131-admin-manage-banner-upload-banner-image-dn moveFileToDiferentDirectory() doc comment modified.
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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

	public void setFileItem(FileItem item) {
		this.item = item;
	}
	
	public FileItem getFileItem() {
		return item ;
	}

	public void setUploadPath(String path) {
		this.uploadPath = path;
	}

	public String getNewName() {
		return this.renamedTo;
	}
	
	public void setRenamedTo(String renamedTo) {
		this.renamedTo = renamedTo;
	}

	public String getRenamedTo() {
		return renamedTo;
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

			for (File file : paths) {
				list.add(file.getName().replaceFirst("[.][^.]+$", ""));
			}

			String highestValue = this.getTopOfSequence(list);

			ext = FilenameUtils.getExtension(this.getUploadedFilePath());

			if (highestValue == null || highestValue.equalsIgnoreCase("")) {
				fileName = "0." + ext;
			} else {
				fileName = Integer.toString((Integer.parseInt(highestValue) + 1)) + "." + ext;
			}
			savePath = folder.getAbsoluteFile() + "/" + fileName;
			savePath = savePath.substring(savePath.lastIndexOf(".war\\") + 5).replace("\\", "/");

			FileUtils.copyFile(this.file.getAbsoluteFile(), new File(folder.getAbsoluteFile() + "/" + fileName));
			FileUtils.forceDelete(this.file);

			//this.renamedTo = fileName;
			setRenamedTo(fileName);
		} catch (SecurityException se) {
			log.error(this + "rename(): " + se.toString());
			throw se;
		} catch (NumberFormatException  nfe) {
			log.error(this + "rename(): " + nfe.toString());
			throw nfe;
		} catch (PatternSyntaxException  pse) {
			log.error(this + "rename(): " + pse.toString());
			throw pse;
		} catch (Exception e) {
			log.error(this + "rename(): " + e.toString());
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

			if (!uploadLocation.isDirectory()) {
				uploadLocation.mkdirs();
			}
			String fileName = this.item.getName();
			
			//check whether the / exists at the end of the upload path
			if (this.uploadPath.length() - 1 == this.uploadPath.lastIndexOf("/")) {
				this.file = new File(this.uploadPath + fileName);
			} else {
				this.file = new File(this.uploadPath + "/" + fileName);
			}

			//write the file to the file system
			if (this.file.exists()) {
				this.file = new File(this.uploadPath + "/(copy)" + fileName);
			}
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
				log.info(this + "ignore: nfe on parsing: getTopOfSequence() " + each);
			}

		}

		if (values.size() > 0) {
			Collections.sort(values);
			name = Integer.toString(values.get(values.size() - 1));
		}

		return name;

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

			if (!uploadLocation.isDirectory()){
				uploadLocation.mkdirs();
			}
			String fileName = this.item.getName();

			this.file = isFileExistsEndofUP(this.uploadPath, fileName, this.file);	
			this.file = createCopyofFile(this.uploadPath, fileName, this.file);
			
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
	 * @author dushantha DN
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
			log.error("isValidImageFileType(): NullPointerException " + fileName + npe.toString());
			throw npe;
		} catch (StringIndexOutOfBoundsException siobe) {
			log.error("isValidImageFileType(): StringIndexOutOfBoundsException " + fileName + siobe.toString());
			throw siobe;
		} catch (Exception e) {
			log.error("isValidImageFileType(): Exception " + fileName + e.toString());
			throw e;
		}

		return false;
	}
	
	/**
	 * This method is to check whether the / exists at the end of the upload path
	 * @param uploadPath
	 * @param fileName
	 * @param file
	 * @return File - after checking the existence. 
	 */
	public File isFileExistsEndofUP(String uploadPath, String fileName, File file ){
		if (uploadPath.length() - 1 == uploadPath.lastIndexOf("/")) {
			file = new File(uploadPath + fileName);
		} else {
			file = new File(uploadPath + "/" + fileName);
		}
		return file;
	}
	
	/**
	 * This method is to rename file name post fix with copy.
	 * @param uploadPath
	 * @param fileName
	 * @param file
	 * @return
	 */
	public File createCopyofFile(String uploadPath, String fileName, File file){
		if (file.exists()){
			file = new File(uploadPath + "/(copy)" + fileName);
		}
		return file;
	}
	
	/**
	 * moveFileTODiferentDirectory method moves the file to the directory given
	 * by movingDirectoryPath,
	 * @author dushantha DN
	 * @since 20170216 v1.0
	 * @param movingDirectoryPath: String typeThe directory and the path
	 * 							e.g.C:/sdb/ctxdeploy/education.war/banner/tempdeleteable/
	 * 							<b>tempdeleteable</b>: is the physical directory name.
	 * @param fileItemTOBeMoved : FileItem type, it is the actual file that has to be moved
	 * 						  This class represents a file or form item that was received 
	 * 						  within a multipart/form-data POST request.
	 * @param shouldDirectoryContentBeRemoved : boolean type instructs if the file has to be moved to a cleaned 
	 * 								  folder, by deleting the content within it (any existing Files and Folders). if true : cleans the folder,
	 * 								  else the folder is not cleaned prior to move the file.	
	 * @return boolean if the moving to a given folder is a success the function returns a true else a false.
	 */
	public boolean moveFileToDiferentDirectory(String movingDirectoryPath,
			FileItem fileItemTOBeMoved, boolean shouldDirectoryContentBeRemoved)
			throws NullPointerException, SecurityException, IOException,
			Exception {
		boolean isTheFileMoved = false;

		try {
			File folderIntendedToMove = new File(movingDirectoryPath);
			if (!folderIntendedToMove.isDirectory()) {
				folderIntendedToMove.mkdir();
			}
			// remove any content within the folder ,if it is a requirement
			if (shouldDirectoryContentBeRemoved) {
				FileUtils.cleanDirectory(folderIntendedToMove);
			}

			String nameOfTheFileToBeMoved = fileItemTOBeMoved.getName();

			// attach the file to be moved to the directory but physically has
			// not been created
			File fileToBeMoved = new File(folderIntendedToMove,
					nameOfTheFileToBeMoved);

			// move the file to the folder where we hope to move by creating the
			// file physically.
			fileToBeMoved.createNewFile();

			// write an uploaded item to disk
			fileItemTOBeMoved.write(fileToBeMoved);
			isTheFileMoved = true;

			return isTheFileMoved;

		} catch (NullPointerException npexp) {
			log.error("moveFileTODiferentDirectory(String,FileItem,boolean): NullPointerException "
					+ npexp.toString());
			throw npexp;

		} catch (SecurityException scexp) {
			log.error("moveFileTODiferentDirectory(String,FileItem,boolean): SecurityException "
					+ scexp.toString());
			throw scexp;

		} catch (IOException ioexp) {
			log.error("moveFileTODiferentDirectory(String,FileItem,boolean): IOException "
					+ ioexp.toString());
			throw ioexp;
		} catch (Exception exp) {
			log.error("moveFileTODiferentDirectory(String,FileItem,boolean): Exception "
					+ exp.toString());
			throw exp;
		}

	}
	
	/**
	 * deleteDirectory() method deletes a directory in the given
	 * path if and only if it exists.
	 * @author dushantha DN
	 * @param deletableDirectoryPath String , the path of the folder to be deleted
	 * e.g C:\eclipse\plugins\org.apache.axis_1.4.0.v201005080400
	 * @return true if the folder is deleted and else false
	 * @throws NullPointerException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean deleteDirectory(String deletableDirectoryPath) throws NullPointerException,
	FileNotFoundException,IOException{
		boolean isDirectoryDeleted = false;
		try {
			 File directory =new File(deletableDirectoryPath);
			 if(directory.isDirectory()){
				FileUtils.forceDelete(directory);
				return directory.isDirectory();
			}
		} catch (NullPointerException npexp) {
			log.error("deleteDirectory(String): NullPointerException "+ npexp.toString());
		} catch (FileNotFoundException fnfexp) {
			log.error("deleteDirectory(String): FileNotFoundException "+ fnfexp.toString());
		} catch (IOException ioexp) {
			log.error("deleteDirectory(String): IOException "
					+ ioexp.toString());
			throw ioexp;
		} 
		return isDirectoryDeleted;
	}
	
	/**
	 * coppyFile method copies the source file to the destination file
	 * @author dushantha DN
	 * @param sourceFileName filename fully qualified file name
	 * @param destinationFileName fully qualified file name
	 * @param deleteSorcefileAfterCoppying if it is required to delete the source file 
	 * after the copying process
	 * @return true if the file copy succeeded else false.
	 *  if the deleteSorcefileAfterCoppying set to true. then the return true if and 
	 *  only if the file gets copied and deletion gets succeeded.Else returns false
	 *  @exception 	NullPointerException,
	 *  			IOException,
	 *  			SecurityException
	 */
	
	public boolean copyFile(String sourceFileName,
			String destinationFileName,boolean deleteSorcefileAfterCoppying) 
					throws NullPointerException,IOException,SecurityException{
		boolean isCoppyingFileSuccess= false;
		try {
			File sourceFile = new File(sourceFileName);
			File destinationFile = new File(destinationFileName);
			
			FileUtils.copyFile(sourceFile, destinationFile);
			isCoppyingFileSuccess= destinationFile.exists();
			
			if(isCoppyingFileSuccess && deleteSorcefileAfterCoppying){
				isCoppyingFileSuccess=sourceFile.delete();
			}			
		} catch (NullPointerException npexp) {
			log.error("coppyFile() NullPointerException : " +npexp.toString());
			throw npexp;
		} catch (IOException ioexp) {
			log.error("coppyFile() IOException : "+ ioexp.toString());
			throw ioexp;
		} catch (SecurityException sexp){
			log.error("coppyFile() SecurityException : "+ sexp.toString());
			throw sexp;
		}
		
		return isCoppyingFileSuccess;
	}
	
}

