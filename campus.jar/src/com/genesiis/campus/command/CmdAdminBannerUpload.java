package com.genesiis.campus.command;
/*
 * 20170213 DN c131-admin-manage-banner-upload-banner-image-dn created the initial class stub
 * 				JasonInflator.java inner class and saveBannerPageCredential()/getInflatedObjectFromJason()
 * 				created.
 * 20170216 DN c131-admin-manage-banner-upload-banner-image-dn saveBannerPageCredential() method started implemented
 * 				getImageTeporyUploadPath() changed SystemConfig.BANNER_IMAGE_TEMPORARY_PATH -->SystemConfig.BANNER_IMAGE_ABSOLUTE_PATH
 * 				created the method uploadFullBannerCredentials(), changed the method execute()
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
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemConfig;
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
				return uploadFullBannerCredentials(helper,view);				
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
	
	
	

/**
 * Save banner image to a temporary location.
 *
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
}
	
	
private IView uploadFullBannerCredentials(IDataHelper helper, IView view)throws Exception{
		
try{
	 // inflate the Gson object
	 JasonInflator rowBanner= getInflatedObjectFromJason(helper.getParameter("jasondata"));
	 // want to get the banner code once the update is succedded
	 int updateSuccessCode = new AdminBannerDAO().update(rowBanner);
	 
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



private JasonInflator getInflatedObjectFromJason(String data) throws JsonSyntaxException {
	Gson gson = new Gson();
	try{
		return gson.fromJson(data, JasonInflator.class);
		
	} catch (JsonSyntaxException jsyexp) {
		log.error("getInflatedObjectFromJason():JsonSyntaxException "+ jsyexp.toString());
		throw jsyexp;	
	}	
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



/**
 * JasonInflator inner class inflate the jason data in to a
 * dummy object which bears the properties 
 * which have been sent from the client.
 *
 * @author dushantha DN
 */
public class JasonInflator {
	
	
	private String advertiserCode;
	
	private String codeOfSelectedPage;
	
	private String bannerSlotCode;	
	
	private String displayDusration;	
	
	private String banerToBeActive;
	
	private String bannerPublishingDate;

	private String bannerPublishingEndDate;	
	
	private String urlToBeDirectedOnBannerClick;
	
	
	/**
	 * Gets the advertiser code.
	 *
	 * @return the advertiser code
	 */
	public String getAdvertiserCode() {
		return advertiserCode;
	}
	
	/**
	 * Sets the advertiser code.
	 *
	 * @param advertiserCode the new advertiser code
	 */
	public void setAdvertiserCode(String advertiserCode) {
		this.advertiserCode = advertiserCode;
	}
	
	/**
	 * Gets the code of selected page.
	 *
	 * @return the code of selected page
	 */
	public String getCodeOfSelectedPage() {
		return codeOfSelectedPage;
	}
	
	/**
	 * Sets the code of selected page.
	 *
	 * @param codeOfSelectedPage the new code of selected page
	 */
	public void setCodeOfSelectedPage(String codeOfSelectedPage) {
		this.codeOfSelectedPage = codeOfSelectedPage;
	}
	
	/**
	 * Gets the banner slot code.
	 *
	 * @return the banner slot code
	 */
	public String getBannerSlotCode() {
		return bannerSlotCode;
	}
	
	/**
	 * Sets the banner slot code.
	 *
	 * @param bannerSlotCode the new banner slot code
	 */
	public void setBannerSlotCode(String bannerSlotCode) {
		this.bannerSlotCode = bannerSlotCode;
	}
	
	/**
	 * Gets the dusration.
	 *
	 * @return the dusration
	 */
	public String getDusration() {
		return displayDusration;
	}
	
	/**
	 * Sets the dusration.
	 *
	 * @param dusration the new dusration
	 */
	public void setDusration(String dusration) {
		this.displayDusration = dusration;
	}
	
	/**
	 * Gets the baner to be active.
	 *
	 * @return the baner to be active
	 */
	public String getBanerToBeActive() {
		return banerToBeActive;
	}
	
	/**
	 * Sets the baner to be active.
	 *
	 * @param banerToBeActive the new baner to be active
	 */
	public void setBanerToBeActive(String banerToBeActive) {
		this.banerToBeActive = banerToBeActive;
	}
	
	/**
	 * Gets the banner publishing date.
	 *
	 * @return the banner publishing date
	 */
	public String getBannerPublishingDate() {
		return bannerPublishingDate;
	}
	
	/**
	 * Sets the banner publishing date.
	 *
	 * @param bannerPublishingDate the new banner publishing date
	 */
	public void setBannerPublishingDate(String bannerPublishingDate) {
		this.bannerPublishingDate = bannerPublishingDate;
	}
	
	/**
	 * Gets the banner publishing end date.
	 *
	 * @return the banner publishing end date
	 */
	public String getBannerPublishingEndDate() {
		return bannerPublishingEndDate;
	}
	
	/**
	 * Sets the banner publishing end date.
	 *
	 * @param bannerPublishingEndDate the new banner publishing end date
	 */
	public void setBannerPublishingEndDate(String bannerPublishingEndDate) {
		this.bannerPublishingEndDate = bannerPublishingEndDate;
	}
	
	/**
	 * Gets the url to be directed on banner click.
	 *
	 * @return the url to be directed on banner click
	 */
	public String getUrlToBeDirectedOnBannerClick() {
		return urlToBeDirectedOnBannerClick;
	}
	
	/**
	 * Sets the url to be directed on banner click.
	 *
	 * @param urlToBeDirectedOnBannerClick the new url to be directed on banner click
	 */
	public void setUrlToBeDirectedOnBannerClick(String urlToBeDirectedOnBannerClick) {
		this.urlToBeDirectedOnBannerClick = urlToBeDirectedOnBannerClick;
	}
	
}

}
