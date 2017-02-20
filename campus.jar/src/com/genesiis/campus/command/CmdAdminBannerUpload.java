package com.genesiis.campus.command;
/*
 * 20170213 DN c131-admin-manage-banner-upload-banner-image-dn created the initial class stub
 * 				JasonInflator.java inner class and saveBannerPageCredential()/getInflatedObjectFromJason()
 * 				created.
 * 20170216 DN c131-admin-manage-banner-upload-banner-image-dn saveBannerPageCredential() method started implemented
 * 				getImageTeporyUploadPath() changed SystemConfig.BANNER_IMAGE_TEMPORARY_PATH -->SystemConfig.BANNER_IMAGE_ABSOLUTE_PATH
 * 				created the method uploadFullBannerCredentials(), changed the method execute()
 * 20170217 DN c131-admin-manage-banner-upload-banner-image-dn urlMiniWebOrPage field added and refactor the methods uploadFullBannerCredentials.execute and
 * 				inner class JasonInflator.java
 * 20170220 DN c131-admin-manage-banner-upload-banner-image-dn getSessionProperty() method created and add doc comments
 * 				removed the inner class JasonInflator.java and placed as a stand alone class.
 */

import com.genesiis.campus.entity.AdminBannerDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Banner;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ImageUtility;
import com.genesiis.campus.util.JasonInflator;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.UserType;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CmdAdminBannerUploadManager.java bears the responsibility of processing
 * the Banner and associated page information
 * @author dushantha DN
 *
 */
public class CmdAdminBannerUpload implements ICommand {

	
	private static final Logger log = Logger.getLogger(CmdAdminBannerUpload.class.getName());	
	
	private int successCode =0;
	private ImageUtility imageUtility =new ImageUtility();
	private ArrayList<FileItem> files = new ArrayList<FileItem>();
	private String message = "";
	private FileUtility fileUtility = new FileUtility();
	
	/* (non-Javadoc)
	 * @see com.genesiis.campus.command.ICommand#execute(com.genesiis.campus.util.IDataHelper, com.genesiis.campus.entity.IView)
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		try{
			Operation o = Operation.getOperation(helper.getCommandCode());
			switch (o){
			case UPLOAD_BANNER_IMAGE_TO_TEMP_FOLDER :
				 return saveBannerImageToTempLocation(helper,view);				 
			case UPLOAD_FULL_BANNER_CREDENTIALS:
				
				// clear the message if it's accumulated.
				this.setMessage(""); 
				String userName = (String) getSessionProperty("usenName",helper);
				/*
				 * ########################################################################################
				 * WARNING: The code --> 
				 * userName =(!userName.equals(null))?userName:UserType.ADMIN.getUserType().toLowerCase();
				 * 			has to be commented out once proper user name is obtained via
				 * 			the HttpSession. This implementation is only valid till integration.
				 * 			2017-02-20 09:02h
				 * ########################################################################################
				 */				
				userName =(!(userName==null))?userName:UserType.ADMIN.getUserType().toLowerCase();
				JasonInflator jsn= getInflatedObjectFromJason(helper.getParameter("jsonData"));
				return uploadFullBannerCredentials(jsn,view,userName);				
		    default:
		    	return view;
			}
		} catch (SQLException sqle) {
			log.error("execute(IDataHelper helper, IView view):SQLException "+ sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("execute(IDataHelper helper, IView view):Exception "+ exp.toString());
			throw exp;
		}
		
	}
	

	/*
	 * getSessionProperty provide session associated attribute
	 * @param userProperty
	 * @param helper  : Helper wraps the HttpRequest object and
	 * 					facilitating manipulating a limited set of properties
	 * 					bound to the request
	 * 					@see IDataHelper.
	 * @return Object : the session property which is a object and 
	 * 					the client who uses the method should cast 
	 * 					to the appropriate Object type.
	 *  @throws IllegalArgumentException
	 */
	private Object getSessionProperty(String userProperty, IDataHelper helper) throws IllegalArgumentException {
		try {
			if ((userProperty==null)||userProperty.trim().equals(""))
				throw new IllegalArgumentException();
			
			Object userSessionProperty = helper.getSession(false).getAttribute(userProperty);
			return userSessionProperty;
			
		} catch (IllegalArgumentException ilearg) {
			log.error("getUserProperty(String,IDataHelper) NullPointerException"
					+ userProperty
					+ "request parameter is Not Set : "
					+ ilearg.toString());
			throw ilearg;
		}

		
	}
	
	

/*
 * Save banner image to a temporary location. This method stores the image 
 * to a temporary location in the physical disk which is agreed by the system 
 * @param helper the helper
 * @param view the view
 * @return the i view
 * @throws SQLException the SQL exception
 * @throws Exception the exception
 */
private IView saveBannerImageToTempLocation(IDataHelper helper, IView view) throws SQLException,
Exception{
	Connection con = null;
	
	try{	
		// clear the message if it's accumulated.
		this.setMessage(""); 
		// get the banner
		files = imageUtility.getImageFileUploadedFromBrowser(helper);
		if((files.size()==0)|(files==null)){
			
			this.message = message +" "+ImageUtility.systemMessage(-1); // does not contain a file
			this.setSuccessCode(-1);
		} else{
			getFileUtility().setFileItem(files.get(0)); //setting the file Item in the FileUtility
			con = ConnectionManager.getConnection();
			//get the banner Absolute upload path
				String bannerImageUploadTemporaryPath = imageUtility
						.getImageTeporyUploadPath(
								SystemConfig.BANNER_IMAGE_ABSOLUTE_PATH,
								"tempbanner", con);

				fileUtility.setUploadPath(bannerImageUploadTemporaryPath);
				
			// check if it confirm to the standards-- pixels this should be stored in the database
			// pass the banner credentials to back end
			
			if((!isTheFileMovedTOTemporaryLocation(fileUtility,bannerImageUploadTemporaryPath,true))){
				this.message = message + " " +ImageUtility.systemMessage(-2);
				this.setSuccessCode(-2);
				setResponseCridentials(helper);
				return view;
			}
			
		}
	} catch(FileUploadException fle){
		log.error("saveBannerPageCredential():FileUploadException"+ fle.toString() );
		throw fle;			
	} catch(Exception exp) {
		log.error("saveBannerPageCredential(): Exception :"+ exp.toString());
		throw exp;
	}finally{
		DaoHelper.cleanup(con, null, null);
	}
	this.setMessage(ImageUtility.systemMessage(1));
	this.setSuccessCode(1);
	setResponseCridentials(helper);
	return view;
	
}

/*
 * isTheFileMovedTOTemporaryLocation(): moves the file or form item that was received within
 *  a multipart/form-data POST request and wrapped with by "fileUtility" parameter to a location specified
 *  by the movingDirectoryPath parameter. This method allows to move the said file to a clean directory
 *  where there is no any other files or folders but the moving file only. This fact is excreted by specifying
 *  the boolean parameter <I>shouldDirectoryContentBeRemoved</I> to true
 *   
 * @param fileUtility : FileUtility 
 * 
 * @param movingDirectoryPath : String denotes the absolute path of the directory
 *                             e.g  C:/sdb/ctxdeploy/education.war/banner/directoryName
 *                             
 * @param shouldDirectoryContentBeRemoved : boolean true for deleting files and sub directories within the Directory
 * 											if directory specified by the path do exist.
 * 
 * @return boolean stating if the operation gets successful or fails.
 * 
 * @throws Exception
 */
private boolean isTheFileMovedTOTemporaryLocation(FileUtility fileUtility,
		String movingDirectoryPath,boolean shouldDirectoryContentBeRemoved) throws Exception {
	
	boolean isTheFileMovedTOTemporaryLocation= false;
	try{
		isTheFileMovedTOTemporaryLocation= fileUtility.moveFileTODiferentDirectory(movingDirectoryPath,
				fileUtility.getFileItem(),shouldDirectoryContentBeRemoved );
		
	} catch (Exception exp) {
		log.error("isTheFileMovedTOTemporaryLocation(): Exception "+exp.toString());
		throw exp;
	}
	
	return isTheFileMovedTOTemporaryLocation;
}



/*
 * Method sets the response credentials, It sets the successfulness or the failure code,
 * amd the message to be dispatched to the view to the response as attributes
 * @author dushantha DN
 * setResponseCridentials sets the request attributes
 * @param helper: It is the HttpServletrequest wrapper instance.
 */
private void setResponseCridentials(IDataHelper helper){
	helper.setAttribute("successCode", getSuccessCode());
	helper.setAttribute("message", message);
	helper.setAttribute("bannerImageName", fileUtility.getFileItem().getName());
}
	

/*
 * uploadFullBannerCredentials Uploads the information of the banner
 * passed with rowBanner instance to the table Banner
 * @param rowBanner: JasonInflator type, it is the basic banner
 * 					 information passed to the method
 * @param view : it is IViewand captures the Collection to send  
 * 				 to the client for displaying purposes
 * @param userName : The user name of the person who logs with 
 * @return IView :captures the Collection to send to the client 
 * 				  the current session for displaying purposes
 * @throws Exception
 */
private IView uploadFullBannerCredentials(JasonInflator rowBanner, IView view,String userName)throws Exception{
		
try{
	 
	 String[] extension =rowBanner.getBannerImageName().split("\\.");
	 // want to get the banner code once the update is succeeded
	 int updateSuccessCode = new AdminBannerDAO().
			 addBannerRecordInOneTransAction(rowBanner,extension[1],userName);
	 
	// save to the data base table banner 
	// extract the banner code
	
	// store the banner to the permanent location		
		
	} catch (JsonSyntaxException jsyexp) {
		log.error("uploadFullBannerCredentials(IDataHelper,IView):JsonSyntaxException "+ jsyexp.toString());
		throw jsyexp;	
	}catch (SQLException sqle) {
		log.error("uploadFullBannerCredentials(IDataHelper,IView):SQLException "+ sqle.toString());
		throw sqle;
	} catch (Exception exp) {
		log.error("uploadFullBannerCredentials(IDataHelper,IView):Exception "+ exp.toString());
		throw exp;
	}	
	
		return null;
	}


/*
 * getInflatedObjectFromJason de serialized the flattened jason data in to an object
 * @param data String type which is the flatten Jason object state (serialized object)
 * sent from the server attached as a servlettRequest parameter IMPORTANT : String must not be  null
 * @return JasonInflator which is the inflated object with the client side informations sent in.
 * @throws JsonSyntaxException.
 */
private JasonInflator getInflatedObjectFromJason(String data) throws JsonSyntaxException {
	Gson gson = new Gson();
	JasonInflator rowbanner = null;
	try{
		rowbanner= (JasonInflator)gson.fromJson(data, JasonInflator.class);
		
	} catch (JsonSyntaxException jsyexp) {
		log.error("getInflatedObjectFromJason():JsonSyntaxException "+ jsyexp.toString());
		throw jsyexp;	
	}
	return rowbanner;
}


/*
 * Getters and setters methods of the containing class goes here
 */


private FileUtility getFileUtility() {
	// TODO Auto-generated method stub
	return fileUtility;
}

private ImageUtility getImageUtility() {
	return imageUtility;
}



private void setImageUtility(ImageUtility imageUtility) {
	this.imageUtility = imageUtility;
}



private ArrayList<FileItem> getFiles() {
	return files;
}



private void setFiles(ArrayList<FileItem> files) {
	this.files = files;
}



private String getMessage() {
	return message;
}



private void setMessage(String message) {
	this.message = message;
}



private void setFileUtility(FileUtility fileUtility) {
	this.fileUtility = fileUtility;
}



/**
 * Gets the success code.
 *
 * @return the success code
 */
public int getSuccessCode() {
	return successCode;
}



/**
 * Sets the success code.
 *
 * @param successCode the new success code
 */
public void setSuccessCode(int successCode) {
	this.successCode = successCode;
}


}
