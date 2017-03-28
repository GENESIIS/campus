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
 * 20170222 DN c131-admin-manage-banner-upload-banner-image-dn code the confirmation of the capacity of banner image in
 * 				isImageAccordanceWithSystemRequirement(). changed FileUtility to static field deleteTempFolder()/getElementFromCollection()/moveFileToPhysicalLocation() implemented
				uploadFullBannerCredentials() refactored.
 * 20170223 DN c131-admin-manage-banner-upload-banner-image-dn getFileReNamedTo() implemented
 * 20170224 DN c131-admin-manage-banner-upload-banner-image-dn SystemMessage[ENUM].toString() is called when using the systemMessages
 * 			setResponseCridentials() has called in uploadFullBannerCredentials(JasonInflator, IView, String, IDataHelper)
 * 20170303 DN c131-admin-manage-banner-upload-banner-image-dn isClientInputAccordanceWithValidation() implemented
 * 20170306 DN c131-admin-manage-banner-upload-banner-image-dn implemented isClientInputAccordanceWithValidation() and getADate() methods.
 * 20170308 DN c131-admin-manage-banner-upload-banner-image-dn corrected as per the CREV comments 20170307.1645h PN.
 * 				getSessionProperty() method catch IllegalArgumentException. But logs NullPointerException corrected.
 * 				clientInputValidator.isNotEmpty(bannerPublishingEndDate,"End Publishing Date field is empty !"); Typo error is corrected.
 * 20170314 DN c81-admin-manage-banner-add-and-view-banner-dn in isClientInputAccordanceWithValidation() add the correct SystemMessage
 *  			SystemMessage.EMPTY_SEARCH_RESULT.message() to the method
 *  20170321 DN c131-admin-manage-banner-upload-banner-image-dn isClientInputAccordanceWithValidation() the Exception message to the client has been better formatted.		
 * 			 typos corrected as per the QA comment 10 given in 201703132232-CN - Local test summary. 
 * 20170324 DN c83-admin-manage-banner-update-banner-info-dn refactor the method uploadFullBannerCredentials() to adhere the banner record update: insertion. 
 * 20170327 DN c83-admin-manage-banner-update-banner-info-dn updateBannerCredentials(JasonInflator ,IDataHelper) method implemented.
 * 				bannerRecordUpdated() method has been implemented.The execute() is changed to have the switch case UPDATE_ONLY_THE_BANNER_RECORD.
 *              uploadFullBannerCredentials() refactor to include bannerRecordUpdated() method call.
 *              bannerRecordUpdated(JasonInflator) has been modified to include ZERO_UPDATES enum instead of SystemMessage.UPDATE_UNSUCCESSFUL.
 *              Rearranged the doc comment.
 *              updateBannerCredentials() method has been changed to include the message & setResponseCridentials() and to set the success code.
 * 20170328 DN  c83-admin-manage-banner-update-banner-info-dn. In setResponseCridentials() :null check for fileUtility.getFileItem() is implemented.
 */

import com.genesiis.campus.entity.AdminBannerDAO;
import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.ICrudSibling;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.model.Banner;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.FileUtility;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ImageUtility;
import com.genesiis.campus.util.JasonInflator;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.PrevalentValidation.FailedValidationException;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.UserType;
import com.genesiis.campus.validation.Validatory;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
	private static final FileUtility fileUtility = new FileUtility();
	
	
	/* (non-Javadoc)
	 * @see com.genesiis.campus.command.ICommand#execute(com.genesiis.campus.util.IDataHelper, com.genesiis.campus.entity.IView)
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		try{
			Operation o = Operation.getOperation(helper.getCommandCode());
			String userName ="";
			JasonInflator jsn= null;
			switch (o){
			case UPLOAD_BANNER_IMAGE_TO_TEMP_FOLDER :
				 return saveBannerImageToTempLocation(helper,view);				 
			case UPLOAD_FULL_BANNER_CREDENTIALS:
				
				// clear the message if it's accumulated.
				this.setMessage(""); 
			    userName = (String) getSessionProperty("usenName",helper);
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
				jsn= getInflatedObjectFromJason(helper.getParameter("jsonData"));
				jsn.setUser(userName);
				if(isClientInputAccordanceWithValidation(jsn)){
					return uploadFullBannerCredentials(jsn,view,userName,helper);
				}
				
				//return uploadFullBannerCredentials(jsn,view,userName,helper);	
				// if validation fails
				setResponseCridentials(helper);
				return view;
			case UPDATE_ONLY_THE_BANNER_RECORD:
				
				// clear the message if it's accumulated.
				this.setMessage(""); 
				userName = (String) getSessionProperty("usenName",helper);
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
				jsn= getInflatedObjectFromJason(helper.getParameter("jsonData"));
				jsn.setUser(userName);
				if(isClientInputAccordanceWithValidation(jsn)){
					updateBannerCredentials(jsn, helper);
				}
				
				return view;
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
	 * isClientInputAccordanceWithValidation() validates if the input fields are 
	 * having values and those are according to the business logic.
	 * @author dushantha DN
	 * @param jason : JasonInflator the object that is having the de-serialized values sent from
	 * 				  client side
	 * @return boolean
	 * @throws Exception
	 */

	private boolean isClientInputAccordanceWithValidation(JasonInflator jason) throws Exception {
		boolean isvalidationSuccess = false;
		if(jason==null){
			log.info("isClientInputAccordanceWithValidation (): --> JasonInflator object is null ");
			this.message = message +" "+SystemMessage.EMPTY_SEARCH_RESULT.message();
			return false;
		}
		
		String advertiserCode 		= jason.getAdvertiserCode();
		String codeOfSelectedPage   = jason.getCodeOfSelectedPage();	
	    String bannerSlotCode 		= jason.getBannerSlotCode();	
		String displayDusration		= jason.getDisplayDusration();
		String banerToBeActive      = jason.getBanerToBeActive();
		String bannerPublishingDate = jason.getBannerPublishingDate(); //"2017-02-14"
		String bannerPublishingEndDate = jason.getBannerPublishingEndDate();		
		String urlMiniWebOrPage        = jason.getUrlMiniWebOrPage();	//"Page:0","URL: 1" or "Mini Web:2"
		String urlToBeDirectedOnBannerClick = jason.getUrlToBeDirectedOnBannerClick();	
		String bannerImageName     = jason.getBannerImageName();
		
		
		try {
			    Validatory clientInputValidator = new PrevalentValidation();
			    clientInputValidator.isNotEmpty(advertiserCode," Advertiser field is empty !");
				clientInputValidator.isInteger(advertiserCode," Choose Advertiser from the list");
				clientInputValidator.isNotEmpty(codeOfSelectedPage,"Advertiser field is empty !");
				clientInputValidator.isInteger(codeOfSelectedPage," Choose a page from the list");
				clientInputValidator.isNotEmpty(bannerSlotCode," Choose a page slot from the list !");
				clientInputValidator.isNotEmpty(displayDusration," The display Duration field is empty !");
				clientInputValidator.isInteger(displayDusration,"Kindly enter a numerical value");
				clientInputValidator.isNotEmpty(banerToBeActive,"Please select enable or disable option");
				clientInputValidator.isNotEmpty(bannerPublishingDate,"Publishing Date field is empty !");
				clientInputValidator.isNotEmpty(bannerPublishingEndDate,"Publishing End Date field is empty !");
				
				Date publishingDate 	= getADate("-",bannerPublishingDate);
				Date endPublishingDate 	= getADate("-",bannerPublishingEndDate);
				// comparison with the current date
				if(!(clientInputValidator.compareDates(publishingDate, endPublishingDate,"yyyy-MM-dd", "date comparison failure")<=0))
					throw new PrevalentValidation().new FailedValidationException("Publishing Start Date must be <= Publishing end Date");
				
				if(!(clientInputValidator.compareDates(publishingDate, new Date(),"yyyy-MM-dd", "date comparison failure")>=0))
					throw new PrevalentValidation().new FailedValidationException(" Publishing Start Date must be >= Current Date");
				
				clientInputValidator.isInteger(urlMiniWebOrPage);
				//clientInputValidator.isUrlValid(urlToBeDirectedOnBannerClick, UrlValidator.ALLOW_ALL_SCHEMES+UrlValidator.ALLOW_LOCAL_URLS, "Url provided is not a valid URL");
				isvalidationSuccess=true;
				
		} catch (FailedValidationException fvexp) {
			String [] errorMessagePart =fvexp.toString().split(":");
			this.message = message +" "+ errorMessagePart[1];
			this.setSuccessCode(-2); 
			return false;
		} catch(Exception exp){
			log.error("isClientInputAccordanceWithValidation(JasonInflator) : Exception "+ exp.toString());
			throw exp;
		}
		return isvalidationSuccess;
	}
	
	/**
	 * getADate() returns a date.
	 * Method accepts a date in the form yyyy?MM?dd
	 * ? denotes the delimiter which should be passed to the method, 
	 * using which the string date is split and forms a java.util.date
	 * @param dateDelemeter : can be any printable string character 
	 * @author dushantha DN
	 *  e.g. "-" "," "/" etc 
	 * @param date should be adhere to the format yyyy?MM?dd
	 * 			yyyy: year
	 * 			MM  : Month
	 * 			dd  : date
	 * 		e.g. 2017-05-26
	 * @return java.util.Date type
	 */
	 private  Date getADate(String dateDelemeter,String date){
			String [] array = date.split(dateDelemeter);
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,Integer.parseInt(array[0]));
			cal.set(Calendar.MONTH,Integer.parseInt(array[1])-1);
			cal.set(Calendar.DATE,Integer.parseInt(array[2]));
			return cal.getTime();
		}

	/**
	 * getSessionProperty provide session associated attribute
	 * @author dushantha DN
	 * @param userProperty
	 * @param helper  : Helper wraps the HttpRequest object and<br>
	 * 					facilitating manipulating a limited set of properties<br>
	 * 					bound to the request<br>
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
			log.error("getUserProperty(String,IDataHelper) IllegalArgumentException  : 'userProperty' request parameter is Not Set : "
					+ ilearg.toString());
			throw ilearg;
		}
		
	}
	

	/**
	 * Save banner image to a temporary location. This method stores the image<br> 
	 * to a temporary location in the physical disk which is agreed by the system <br>
	 * @author dushantha DN
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
			// get the banner from the browser(client) and set field -files are set now
			files = imageUtility.getImageFileUploadedFromBrowser(helper);
			if((files.size()==0)|(files==null)){
				this.message = message +" "+ImageUtility.systemMessage(-1); // does not contain a file
				this.setSuccessCode(-1);
			} else{
				
				getFileUtility().setFileItem(files.get(0)); //Setting the file Item in the FileUtility
				con = ConnectionManager.getConnection();
				
				//get the banner Absolute temporary upload path
				String bannerImageTemporaryUploadPath = imageUtility
							.getImageTeporyUploadPath(
									SystemConfig.BANNER_IMAGE_ABSOLUTE_PATH,
									"tempbanner", con);
				
				// check if it confirm to the standards
				if(!isImageAccordanceWithSystemRequirement(con,helper)){
					setResponseCridentials(helper);
					return view;
				}
				
				fileUtility.setUploadPath(bannerImageTemporaryUploadPath);
	
				if((!isTheFileMovedToTemporaryLocation(fileUtility,bannerImageTemporaryUploadPath,true))){
					 deleteTempFolder(fileUtility,bannerImageTemporaryUploadPath); // temporary folder deleted if moving failed
					this.message = message + " " +ImageUtility.systemMessage(-2); //upload fail
					this.setSuccessCode(-2);
					setResponseCridentials(helper);
					return view;
				}
				
			}
		} catch(FileUploadException fle){
			log.error("saveBannerImageToTempLocation():FileUploadException"+ fle.toString() );
			throw fle;			
		} catch(Exception exp) {
			log.error("saveBannerImageToTempLocation(): Exception :"+ exp.toString());
			throw exp;
		}finally{
			DaoHelper.cleanup(con, null, null);
		}
		
		this.setMessage(ImageUtility.systemMessage(0)); //processing...
		this.setSuccessCode(1);
		setResponseCridentials(helper);
		return view;
	}

	/**
	 * deleteTempFolder() method deletes a directory in the given
	 * path if and only if it exists.
	 * @author dushantha DN
	 * @param fileUtility2 FileUtility
	 * @param directoryPathToBeDeleted String , the path of the folder to be deleted
	 * e.g C:\eclipse\plugins\org.apache.axis_1.4.0.v201005080400
	 * @return true if the folder is deleted and else false
	 * @throws NullPointerException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
		private void deleteTempFolder(FileUtility fileUtility2,String directoryPathToBeDeleted) 
				throws NullPointerException,FileNotFoundException,IOException {
			
			try {
				 fileUtility2.deleteDirectory(directoryPathToBeDeleted);
				
			} catch (NullPointerException npexp) {
				log.error("deleteTempFolder(String,String): NullPointerException "+ npexp.toString());
			} catch (FileNotFoundException fnfexp) {
				log.error("deleteTempFolder(String,String): FileNotFoundException "+ fnfexp.toString());
			} catch (IOException ioexp) {
				log.error("deleteTempFolder(String,String): IOException "
						+ ioexp.toString());
				throw ioexp;
			} 
	}


	/**
	 * Checks if is image accordance with system requirement.
	 * <b>Note</b> the file to be tested must not be null
	 * @author dushantha DN
	 * @param con
	 * @param requestWrapper
	 * @return boolean true if the Banner image matches the set system constraints
	 * 			else return false.
	 * @throws SQLException
	 * @throws Exception
	 */
	private boolean isImageAccordanceWithSystemRequirement(Connection con,
			IDataHelper requestWrapper) throws SQLException, Exception {
		
		boolean isFilePassSizeRequirement=false;
		boolean isBannerWithCorrectExtension = false;
		try{
			if((files.size()==0)|(files==null))
				return isFilePassSizeRequirement = false; // important then
															// there is no
															// trouble operating
															// on file (it's not
															// null) after this line
			
			isFilePassSizeRequirement = imageUtility.isImageWithinSize(
					SystemConfig.BANNER_IMAGE_SIZE, con, files.get(0));
			
			isBannerWithCorrectExtension = fileUtility.isValidImageFileType(
					fileUtility.getFileItem().getName(),
					imageUtility.getValidExtensions());
			
			//set the messages to be sent to the client side
			if(!isFilePassSizeRequirement){
				this.message =this.message + " "+ImageUtility.systemMessage(-3);
				setSuccessCode(-3);
			}
			
			if(!isBannerWithCorrectExtension){
				this.message =this.message + " "+ImageUtility.systemMessage(-4);
				setSuccessCode(-4);
			}
			
		} catch(SQLException exp) {
			log.error("isImageAccordanceWithSystemRequirement(): SQLException"+exp.toString());
			throw exp;
			
		} catch(FileUploadException fle){
			log.error("isImageAccordanceWithSystemRequirement():FileUploadException"+ fle.toString() );
			throw fle;
			
		}	catch(Exception exp) {
			log.error("isImageAccordanceWithSystemRequirement(): SQLException"+exp.toString());
			throw exp;
		}
		return (isFilePassSizeRequirement & isBannerWithCorrectExtension);
	}
	
	
	/**
	 * isTheFileMovedTOTemporaryLocation(): moves the file or form item that was received within<br>
	 *  a multipart/form-data POST request and wrapped with by "fileUtility" parameter to a location specified<br>
	 *  by the movingDirectoryPath parameter. This method allows to move the said file to a clean directory<br>
	 *  where there is no any other files or folders but the moving file only. This fact is excreted by specifying<br>
	 *  the boolean parameter <I>shouldDirectoryContentBeRemoved</I> to true
	 * @author dushantha DN  
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
	private boolean isTheFileMovedToTemporaryLocation(FileUtility fileUtility,
			String movingDirectoryPath,boolean shouldDirectoryContentBeRemoved) throws Exception {
		
		boolean isTheFileMovedToTemporaryLocation= false;
		try{
			isTheFileMovedToTemporaryLocation= fileUtility.moveFileToDiferentDirectory(movingDirectoryPath,
					fileUtility.getFileItem(),shouldDirectoryContentBeRemoved );
			
		} catch (Exception exp) {
			log.error("isTheFileMovedTOTemporaryLocation(): Exception "+exp.toString());
			throw exp;
		}
		
		return isTheFileMovedToTemporaryLocation;
	}


	/**
	 * Method sets the response credentials, It sets the successfulness or the failure code,
	 * and the message to be dispatched to the view to the response as attributes
	 * @author dushantha DN
	 * setResponseCridentials sets the request attributes
	 * @param helper: It is the HttpServletrequest wrapper instance.
	 */
	private void setResponseCridentials(IDataHelper helper) throws Exception{
		try {
			helper.setAttribute("successCode", getSuccessCode());
			helper.setAttribute("message", message);
			if( fileUtility.getFileItem()!=null)
			 helper.setAttribute("bannerImageName", fileUtility.getFileItem().getName());
		} catch (Exception exp) {
			log.error("setResponseCridentials(IDataHelper):SQLException "+ exp.toString());
			throw exp;
		}
	}
		
	
	/**
	 * getElementFromCollection() method unwraps the inner Collection<String>
	 * and retrieves the element at given index wrapped in an Object
	 * @author dushantha DN
	 * @param banners Collection<Collection<String>>
	 * @param indexOfTheElement the index of the required element of Collection<String>
	 * i.e in the inner Collection
	 * @return string wrapped in a Object type
	 */
	private Object getElementFromCollection(Collection<Collection<String>> banners, int indexOfTheElement){
		Object element = null;
		for(Collection<String> innerWrapper:banners){
			 Object[] objectArray=innerWrapper.toArray();
			 element = objectArray[indexOfTheElement];
		 }
		return element;
	}

	/**
	 * moveFileToPhysicalLocation() will move the banner file to the 
	 * physical location agreed by the SystemCofig table or the enum,
	 * By default if the folder path given by bannerImagePhysicalUploadPath contains any files
	 * those files will be deleted.
	 * @author dushantha DN
	 * @param bannerImagePhysicalUploadPath 
	 * @return boolean true if the file is written to the location given by the 
	 * physical path. else false
	 */
	private boolean moveFileToPhysicalLocation(	String bannerImagePhysicalUploadPath) throws Exception{
		
		//### MOVE THE BANNER TO THE PHYSICAL LOCATION ####
		boolean isTheFileMovedToPhysicalLocation = false;
	 
	/*
	 *   move the image to the correct physical location and if the destination
	 *   contains any files those will be deleted prior to move.
	 */
		isTheFileMovedToPhysicalLocation = fileUtility.moveFileToDiferentDirectory(bannerImagePhysicalUploadPath,
							fileUtility.getFileItem(), true);
		return isTheFileMovedToPhysicalLocation;
	}
	
	/**
	 * updateBannerCredentials acts as a wrapper function which calls the actual update method<br>
	 * and handles the exceptions 
	 * @author dushantha DN
	 * @param rowBanner
	 * @param helper
	 * @throws SQLException
	 * @throws Exception
	 */
	private void updateBannerCredentials(JasonInflator rowBanner,IDataHelper helper) throws SQLException,Exception{
		try {
			bannerRecordUpdated(rowBanner);
			this.message = message +" "+SystemMessage.UPDATE_SUCCESSFUL.toString();
			this.setSuccessCode(1);
			helper.setAttribute("bannerWarPath", SystemConfig.BANNER_IMAGE_WAR_PATH.getValue1());
			setResponseCridentials(helper);
			log.info("updateBannerCredentials(JasonInflator,IDataHelper) --> banner records are updated successfully ");
		} catch (FailedValidationException fvexp) {
			String [] errorMessagePart =fvexp.toString().split(":");
			this.message = message +" "+ errorMessagePart[1];
			this.setSuccessCode(-2);
			setResponseCridentials(helper);
		} catch (SQLException sqle) {
			log.error("updateBannerCredentials(JasonInflator,IDataHelper):SQLException "+ sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("updateBannerCredentials(JasonInflator,IDataHelper) :Exception "+ exp.toString());
			throw exp;
		}	
	}
	
	/**
	 * bannerRecordUpdated <b>Updates</b> the Banner table with the data that has been <br>
	 * passed in with the JasonInflator rowBanner which should <b>not be null</b> and the client<br>
	 * who uses the bannerRecordUpdated method must ensure that the parameter is not null.<br>
	 * <br>
	 * @author dushantha DN
	 * @param rowBanner JasonInflator type instance that captured the data about<br>
	 * 					the current banner which is to be updated.
	 * @throws FailedValidationException : User defined Custom Exception is thrown if zero records are <br>
	 * 			updated.
	 * @throws SQLException
	 * @throws Exception
	 */
	private void bannerRecordUpdated(JasonInflator rowBanner) throws FailedValidationException,SQLException,Exception{
		 ICrudSibling adminBanerInsert = new AdminBannerDAO();
		 if(adminBanerInsert.update(rowBanner)== 0){
				throw new PrevalentValidation().new FailedValidationException(
						SystemMessage.ZERO_UPDATES.toString());
		 }
	}
	
	/**
	 * uploadFullBannerCredentials Uploads the information of the banner
	 * passed with rowBanner instance to the table Banner
	 * @author dushantha DN
	 * @param rowBanner: JasonInflator type, it is the basic banner
	 * 					 information passed to the method
	 * @param view : it is IViewand captures the Collection to send  
	 * 				 to the client for displaying purposes
	 * @param userName : The user name of the person who logs with 
	 * @param helper : IDatahelper
	 * @return IView :captures the Collection to send to the client 
	 * 				  the current session for displaying purposes
	 * @throws Exception
	 * 		   SQLException	
	 */
	private IView uploadFullBannerCredentials(JasonInflator rowBanner,
			IView view,String userName,IDataHelper helper) throws SQLException,Exception{
		Connection con = null;
	try{
		 
		 String[] extension =rowBanner.getBannerImageName().split("\\.");
		 
		 /*
		  * want to get the banner code and bannerName once the update is succeeded
		  * and save to the data base table banner
		  */
		 
		 String bannerCode = rowBanner.getBannerCode();
		 String bannerNamer="";
		 if(bannerCode.isEmpty()){ // new banner record is adding
			 
			 Collection<Collection<String>> banners = new AdminBannerDAO().
					 addBannerRecordInOneTransAction(rowBanner,extension[1],userName);
			 bannerCode = (String) getElementFromCollection(banners,0);
			 bannerNamer = (String) getElementFromCollection(banners,1);
			 
		 } else { 
			 // this is an insert or update for an existing record			 
			 bannerRecordUpdated(rowBanner);
		 }
		 
		 con = ConnectionManager.getConnection();
		 
		// get the banner absolute path for physical location to store the image 
		 
		String bannerImagePhysicalUploadPath = imageUtility
				.getImageTeporyUploadPath(
						SystemConfig.BANNER_IMAGE_ABSOLUTE_PATH,
						bannerCode, con);
		
		// store the banner to the permanent location	
		 boolean isTheFileMovedToPhysicalLocation = moveFileToPhysicalLocation(bannerImagePhysicalUploadPath);
		 
		 //get the banner Absolute upload path for temporary folder
		String bannerImageTemporaryUploadPath = imageUtility
						.getImageTeporyUploadPath(
								SystemConfig.BANNER_IMAGE_ABSOLUTE_PATH,
								"tempbanner", con);
	
		// if only the file is moved ,delete the temporary folder which is created before	
		 
			if(isTheFileMovedToPhysicalLocation && getFileReNamedTo(bannerCode,fileUtility,
					 bannerImagePhysicalUploadPath,rowBanner.getBannerImageName()) ){
				
				this.deleteTempFolder(fileUtility, bannerImageTemporaryUploadPath);
				this.message =this.message + " ";
				this.message = (rowBanner.getBannerCode().isEmpty()) ? SystemMessage.SUCCESSFULLY_IMAGE_UPLOAD
						.toString() : SystemMessage.UPDATE_SUCCESSFUL
						.toString();
				this.setSuccessCode(1);
				log.info("uploadFullBannerCredentials(JasonInflator.IView,String) completed --> :"
						+ "Banner Credentials are written to the rpository and image is uploaded");
				
			}
			
		}catch (FailedValidationException fvexp) {
			String [] errorMessagePart =fvexp.toString().split(":");
			this.message = message +" "+ errorMessagePart[1];
			this.setSuccessCode(-2);
			setResponseCridentials(helper);
			return view;
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
		
	 	setResponseCridentials(helper); 
	 	return view;
	}

/**
 * getFileReNamedTo rename the currentFilenameWithExtension
 *  to newFileNameWithoutExtension and deletes the source file once the coping
 *  process is succeeded.
 * @author dushantha DN 
 * @param newFileNameWithoutExtension : filename to be renamed to without the 
 * 										the extension e.g. 'account123'
 * @param fileUtility : FileUtility type instance 
 * @param fileLocatedPath: String the fully qualified path
 * 						 e.g.C:/sdb/ctxdeploy/education.war/banner
 * @param currentFilenameWithExtension : String source file name with extension
 * 									    "image0123.jpg".
 * @return true if the file copy succeeded else false.
 *  if the deleteSorcefileAfterCoppying set to true. then the return true if and 
 *  only if the file gets copied and deletion gets succeeded.Else returns false
 */
private boolean getFileReNamedTo(String newFileNameWithoutExtension,FileUtility fileUtility,
		String fileLocatedPath,String currentFilenameWithExtension) throws NullPointerException,
		IOException,SecurityException,Exception {
	
	
	boolean isFileRenamedToNewFileName = false;
	
	try {
		String[] extension =currentFilenameWithExtension.split("\\.");
		String oldFileNameWithoutExtension = extension[0];
		String fileExtension =extension[1];
		String sourceFileName=fileLocatedPath+currentFilenameWithExtension;
		String destinationFileName =fileLocatedPath+newFileNameWithoutExtension+"."+fileExtension;
		
		//check if the file exists by given name
		if(fileUtility.isFileExists(fileLocatedPath, oldFileNameWithoutExtension)){	
			/*
			 * if file exist, then 
			 * rename to the given name.
			 */
			if(!(isFileRenamedToNewFileName=fileUtility.copyFile(sourceFileName, destinationFileName,true))){
				this.message =this.message + " "+SystemMessage.FILE_UPLOAD_FAILED.toString();
				setSuccessCode(-2);
				log.info("getFileReNamedTo() failed --> : Image Name:"+sourceFileName
						+" has not get copied to :"+destinationFileName);
			} 
			log.info("getFileReNamedTo() completed --> : Image Name:"+sourceFileName
					+" has copied to :"+destinationFileName);
		} else{
			this.message =this.message + " "+SystemMessage.FILE_UPLOAD_FAILED.toString();
			setSuccessCode(-2);
			log.info("getFileReNamedTo() failed --> : Image Name:"
						+oldFileNameWithoutExtension+'.'+fileExtension
						+"located at :"+fileLocatedPath+" has not get renamed to :"
						+newFileNameWithoutExtension+'.'+fileExtension);
		}
	}  catch (NullPointerException npexp) {
		log.error("getFileReNamedTo() NullPointerException : " +npexp.toString());
		throw npexp;
	} catch (IOException ioexp) {
		log.error("getFileReNamedTo() IOException : "+ ioexp.toString());
		throw ioexp;
	} catch (SecurityException sexp){
		log.error("getFileReNamedTo() SecurityException : "+ sexp.toString());
		throw sexp;
	} catch (Exception exp){
		log.error("getFileReNamedTo() Exception : "+ exp.toString());
		throw exp;
	}
	return isFileRenamedToNewFileName;
}


/**
 *  getInflatedObjectFromJason deserialized the flattened jason data in to an object
 *  @author dushantha DN
 * @param data String type which is the flatten Jason object state (serialized object)
 * sent from the server attached as a servlettRequest parameter<br>
 *  IMPORTANT : String must not be  null
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
