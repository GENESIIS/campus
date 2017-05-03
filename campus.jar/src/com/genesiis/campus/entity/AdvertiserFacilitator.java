package com.genesiis.campus.entity;

/*
 * 20170426 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class  AdvertiserFacilitator.java has been created.
 * 20170427 DN c88-admin-manage-advertiser-add-new-advertiser-dn. created the methods processAdvertiser(),createNewAdvertiser()
 * 				getAdvertiserClientFedData() and add doc comments and in line comments to the class.
 * 				The method  setResponseCridentials(IDataHelper) is implemented.
 * 				The error :jdbc.SQLServerException: The index 19 is out of range has been corrected in createNewAdvertiser() method.
 * 				Removed the preceding '+' sign from the country code of both land line and the mobile in createNewAdvertiser() method.
 * 20170428 DN c88-admin-manage-advertiser-add-new-advertiser-dn.The inner class GeneralAdvertiser.java has been created. The method 
 * 				getAnAdvertiser() implemented and the method createNewAdvertiser() has been refactored.
 * 				The inner class GeneralAdvertiser.java has been removed favour of com.genesiis.campus.entity.model.BannerAdvertiser.java.
 * 				The method isClientInputAccordanceWithValidation() has been fully implemented.
 * 20170502 DN c88-admin-manage-advertiser-add-new-advertiser-dn. Changed the getAnAdvertiser() ao that the Land/Mobile country code does not handle "+" sign. 
 */

import com.genesiis.campus.entity.model.AdvertiserRole;
import com.genesiis.campus.entity.model.BannerAdvertiser;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.JasonInflator;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.PrevalentValidation.FailedValidationException;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validatory;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/**
 * The Class AdvertiserFacilitator, <br>
 * contains one public constructor and a method for processing<br> 
 * of the Advertiser against the data base.
 * 
 */
public class AdvertiserFacilitator {
	
	private static Logger log = Logger.getLogger(AdvertiserFacilitator.class.getName());
	private IDataHelper helper;
	
	/** The success code. 
	 * This code manages the successful execution of the process or the failure.
	 * if success the code gets the value of 1<br>
	 * else any other number.
	 * 
	 */
	private volatile int successCode =0;
	
	/** The message. 
	 * This sets the user informative message that the system<br>
	 * should compose to pass to the client regarding necessary success<br>
	 * or the failure information which may helpful for the client.<br>
	 *
	 */
	private String message="";
	
	/**
	 * <b>Constructor</b><br>
	 * Instantiates a new advertiserfacilitater.<br>
	 * This constructor accept an argument IDataHelper<br>
	 *
	 * @param helper the helper
	 */
	public AdvertiserFacilitator(IDataHelper helper){
		this.helper =helper;
	}

	/**
	 * The method processes the advertiser.<br>
	 * The client may execute the method and the method decide which command to be executed.<br>
	 * Depending on the 'CCO' request parameter which is bound to the HttpServletRequest <br>
	 * @return the collection Collection<Collection<String>>:
	 *  The return value may differ depending on the CCO value ( command ) bound to the HttpServletRequest<br>
	 *  If the command requires only to update insert a record to the data base then an empty Collection&lt;Collection&lt;String&gt;&gt; will be returned.
	 * @throws Exception the exception
	 */
	public  Collection<Collection<String>> processAdvertiser() throws Exception{		
		Operation operation= Operation.getOperation(helper.getCommandCode());
		Collection<Collection<String>> processedWrapper = new ArrayList<Collection<String>>();
		try{
			switch(operation){
			case CREATE_NEW_ADVERTISER :
					// validate incoming data is correct
				int executionStatus=0;
					if(isClientInputAccordanceWithValidation())					
						 executionStatus=createNewAdvertiser(this.getAnAdvertiser());
					
					if(executionStatus>0){
						setSuccessCode(1);
						message =message + SystemMessage.UPDATE_SUCCESSFUL.message();						
					} else {
						setSuccessCode(0);
						message =message + SystemMessage.UPDATE_UNSUCCESSFUL.message();
					}
				
				break;
			default:
					break;
			} 
		} catch (SQLException sqle){
			log.error("processAdvertiser() SQLException: "+sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error(""+exp.toString());
			throw exp;
		} finally{
			setResponseCridentials(helper);
			operation=null;
		}
		return processedWrapper;
	}
	
	
	/**
	 * Creates the new advertiser.
	 * method adds a new advertiser to the data base<br> 
	 * If the action is successful then an int id returned.<br>
	 * @return the int : If the record is added to the repository <br>
	 * 	1 will be returned else 0 will be returned
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	private int createNewAdvertiser(AdvertiserRole advertiserrRole) throws SQLException,Exception{
		
		// clears any message brought forward
		this.setMessage(""); 
		int status = 0;
		try{
			
			// THIS ARGUMENT CAN BE SENT TO THE createNewAdvertiser() FROM
			// processAdvertiser() METHOD.
			AdvertiserRole advertiser  = advertiserrRole; 
			if(advertiser == null) throw new IllegalArgumentException("AdvertiserRole is null");
			/*
			 * ###########################################################
			 * Start of adapter class creation and call to the data base
			 * ###########################################################
			 */		
			status = new GeneralPurposeDAO(){
				
				public int update(Object object) throws SQLException, Exception {
					
					AdvertiserRole advertiserToBeProcessed = (AdvertiserRole)object;					
					Connection conn=null;
					PreparedStatement prepare = null;
					int executionStatus = 0;
					
					String ACTIVE = Integer.toString(ApplicationStatus.INACTIVE.getStatusValue());
					String modOrCreateBy =(String) helper.getSession(true).getAttribute("user");
					
					String sqlInsertAdvertiser = "INSERT INTO [CAMPUS].[ADVERTISER] ";
							sqlInsertAdvertiser = sqlInsertAdvertiser+"([NAME],[EMAIL],[LANDPHONECOUNTRYCODE],[LANDPHONEAREACODE] ,[LANDPHONENUM],[MOBILEPHONECOUNTRYCODE]";
							sqlInsertAdvertiser = sqlInsertAdvertiser+",[MOBILEPHONENETWORKCODE],[MOBILEPHONENUM],[DESCRIPTION],[ADDRESS1],[ADDRESS2] ,[ADDRESS3]";
							sqlInsertAdvertiser = sqlInsertAdvertiser+",[TOWN],[ISACTIVE],[COURSEPROVIDER] ,[CRTON],[CRTBY],[MODON] ,[MODBY])";
							sqlInsertAdvertiser = sqlInsertAdvertiser +"VALUES";
							sqlInsertAdvertiser =sqlInsertAdvertiser +"(?,?,?,?,?,?,?,?,?,?,?,?,";
							sqlInsertAdvertiser =sqlInsertAdvertiser +" ?,?,?,getDate(),?,getDate(),? );";
					try{
						conn = ConnectionManager.getConnection();
						
						// Populating the table columns.
						prepare = conn.prepareStatement(sqlInsertAdvertiser);
						prepare.setString(1, advertiserToBeProcessed.getName());					
						prepare.setString(2, advertiserToBeProcessed.getGeneralEmail());					
						prepare.setString(3,advertiserToBeProcessed.getLandPhoneCountryCode());
						prepare.setString(4, advertiserToBeProcessed.getLandPhoneAreaCode());
						prepare.setString(5, advertiserToBeProcessed.getLandPhoneNo());				
						prepare.setString(6, advertiserToBeProcessed.getMobilePhoneCountryCode());
						prepare.setString(7, advertiserToBeProcessed.getMobilePhoneNetworkCode());
						prepare.setString(8, advertiserToBeProcessed.getMobilePhoneNumber());
						prepare.setString(9, advertiserToBeProcessed.getDescription());
						prepare.setString(10, advertiserToBeProcessed.getAddress1());
						prepare.setString(11, advertiserToBeProcessed.getAddress2());
						prepare.setString(12, advertiserToBeProcessed.getAddress3());				
						prepare.setString(13, advertiserToBeProcessed.getTownCode());								
						prepare.setString(14, Integer.toString(advertiserToBeProcessed.getActiveStatus()));
						prepare.setString(15,helper.getParameter("courseProviderCode"));
						
						// if the session attribute has not been set then set to the default value 
						// which is accepted by data base table column.
						// by data base definition.
						if(modOrCreateBy==null)
							modOrCreateBy= "";				
						prepare.setString(16, modOrCreateBy);//CRTBY
						prepare.setString(17,modOrCreateBy);//MODBY
						
						executionStatus= prepare.executeUpdate();				
							
					} catch (SQLException sqle) {
						log.error("update(Object) : SQLException "+ sqle.toString());
						throw sqle;
					} catch (Exception exp) {
						log.error("update(Object) :Exception "+exp.toString());
						throw exp;					
					} finally{
						DaoHelper.closeConnection(conn);
						DaoHelper.closeStatement(prepare);
						modOrCreateBy=null;
						ACTIVE = null;
						sqlInsertAdvertiser=null;
						advertiserToBeProcessed = null;
					}
					return executionStatus;
				}
			}.update(advertiser);
			/*
			 * ###########################################################
			 * End of adapter class creation and call to the data base
			 * ###########################################################
			 */	
			
		}
		catch (SQLException sqle) {
			log.error("createNewAdvertiser() : SQLException "+ sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("createNewAdvertiser() :Exception "+exp.toString());
			throw exp;					
		}
		
		return status;
	}
		
	/**
	 * Method sets the response credentials, It sets the successfulness or the failure code,
	 * and the message to be dispatched to the view to the response as attributes.
	 * @author dushantha DN
	 * setResponseCridentials sets the request attributes
	 * @param helper the new response credentials
	 */
	private void setResponseCridentials(IDataHelper helper){
		helper.setAttribute("successCode", getSuccessCode());
		helper.setAttribute("message", message);
	}
	
	/**
	 * isClientInputAccordanceWithValidation() validates if the input fields are 
	 * having values and those are according to the business logic.
	 * @return boolean
	 * @throws Exception the exception
	 */

	private boolean isClientInputAccordanceWithValidation() throws Exception {
		boolean isvalidationSuccess = true;	
		Validatory advertiserValidator = new PrevalentValidation();
	 try{
		 	// Advertiser Name Validation
			advertiserValidator.isNotEmpty(helper.getParameter("advertiserName"),					
							" please fill the advertiser name " +SystemMessage.EMPTYFIELD.message() + "\n");
			// email validation
			advertiserValidator.validateEmail(
					helper.getParameter("advertiserEmail"),
					SystemMessage.INVALID_EMAIL_FORMAT.message()+"\n");
			
			//Land Phone country code
			advertiserValidator.isInteger( helper.getParameter("landCountryCode"),
					"Please enter a valid land phone Country Code \n");
			// Land phone area code
			advertiserValidator.isInteger(helper.getParameter("landAreaCode"),
					"Please enter a valid land phone area Code");
			//Land phone number
			advertiserValidator.isValidWholeNumber(helper.getParameter("landPhoneNumber"),"Please enter valid phone Number \n");
			
			//Land mobile country code
			advertiserValidator.isInteger( helper.getParameter("mobileCountryCode"),
					"Please enter a valid  mobile phone Country Code \n");
			// Land mobile area code
			advertiserValidator.isInteger( helper.getParameter("mobileAreaCode"),
					"Please enter a valid mobile phone area Code \n");
			// Land mobile number
			advertiserValidator.isValidWholeNumber(helper.getParameter("mobilePhoneNumber"),
					"Please enter valid mobile Number \n");
			// Address1
			advertiserValidator.isNotEmpty(helper.getParameter("address1"),
					"Address Line 1 " + SystemMessage.EMPTYFIELD.message());
			//Address2
			advertiserValidator.isNotEmpty(helper.getParameter("address2"),
					"Address Line 2 " + SystemMessage.EMPTYFIELD.message()+"\n");
			//Address3
			advertiserValidator.isNotEmpty(helper.getParameter("address3"),
					"Address Line 3 " + SystemMessage.EMPTYFIELD.message()+"\n");
			// town validation
						advertiserValidator.isNotEmpty(helper.getParameter("townCode"),
								"Invalid town Please select correct available Country and a town \n ");
						advertiserValidator.isValidWholeNumber(helper.getParameter("townCode"),
								"Please enter a valid Town");
		 
		 }catch (NumberFormatException  nfexp){
			 log.error("isClientInputAccordanceWithValidation() : NumberFormatException "+ nfexp.toString());
				throw nfexp; 
		 } catch (FailedValidationException fvexp) {
			
			 log.error("isClientInputAccordanceWithValidation() : FailedValidationException "+ fvexp.toString());
			 String [] errorMessagePart =fvexp.toString().split(":");
			 this.message = message +" "+ errorMessagePart[1];
			 this.setSuccessCode(-2);
			 setResponseCridentials(helper);
			 return false;
			 
		 } catch (Exception exp) {
			 log.error("isClientInputAccordanceWithValidation() : Exception "+ exp.toString());
				throw exp;
		 }		
		return isvalidationSuccess;
	}
	
		
	/**
	 * Gets the helper.
	 * @return the helper
	 */
	private IDataHelper getHelper() {
		return helper;
	}

	/**
	 * Sets the helper.
	 * @param helper the new helper
	 */
	private void setHelper(IDataHelper helper) {
		this.helper = helper;
	}

	/**
	 * Gets the success code.
	 * @return the success code
	 */
	private int getSuccessCode() {
		return successCode;
	}

	/**
	 * Sets the success code.
	 * @param successCode the new success code
	 */
	private void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}

	/**
	 * Gets the message.
	 * @return the message
	 */
	private String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * @param message the new message
	 */
	private void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the an advertiser.<br>
	 * The Purpose of the method is to obtain an Advertiser with the data fields which are<br> 
	 * populated with the client fed informations that are bound to the HttpServletRequest as parameters. <br>
	 * <b>NOTE</b><br>
	 * <p>
	 * 		If the user has not fed data related to the Advertiser a <b><u>null</u></b> will be returned
	 * </p>
	 * @return the an advertiser AdvertiserRole
	 * @throws NumberFormatException -  the strings are not parsed to integer
	 * @throws Exception -
	 */
	private AdvertiserRole getAnAdvertiser() throws NumberFormatException, Exception{
		
		AdvertiserRole advertiser = new BannerAdvertiser();//new GeneralAdvertiser();		
		Map<String,String[]> advertiserCredentialMappings = helper.getParameterMap() ;
		
		if(advertiserCredentialMappings.isEmpty())
			return null;
		try{
			//Inducing the advertiser with user fed data.
			advertiser.setName(advertiserCredentialMappings.get("advertiserName")[0]);
			advertiser.setGeneralEmail(advertiserCredentialMappings.get("advertiserEmail")[0]);
			advertiser.setLandPhoneCountryCode(advertiserCredentialMappings.get("landCountryCode")[0]); //.split("\\+")[1]);
			advertiser.setLandPhoneAreaCode(advertiserCredentialMappings.get("landAreaCode")[0]);
			advertiser.setLandPhoneNo(advertiserCredentialMappings.get("landPhoneNumber")[0]);
			advertiser.setMobilePhoneCountryCode(advertiserCredentialMappings.get("mobileCountryCode")[0]); //.split("\\+")[1]);
			advertiser.setMobilePhoneNetworkCode(advertiserCredentialMappings.get("mobileAreaCode")[0]);
			advertiser.setMobilePhoneNumber(advertiserCredentialMappings.get("mobilePhoneNumber")[0]);
			advertiser.setDescription(advertiserCredentialMappings.get("advertiserDescription")[0]);
			advertiser.setAddress1( advertiserCredentialMappings.get("address1")[0]);
			advertiser.setAddress2( advertiserCredentialMappings.get("address2")[0]);
			advertiser.setAddress3( advertiserCredentialMappings.get("address3")[0]);
			advertiser.setTownCode(advertiserCredentialMappings.get("townCode")[0]);
			advertiser.setActiveStatus(Integer.parseInt(advertiserCredentialMappings.get("AdvertiserStatus")[0]));
			
		}catch(NumberFormatException nfexp){
			log.error("getAnAdvertiser() : NumberFormatException "+ nfexp.toString());
			throw nfexp;
		} catch (Exception exp){
			log.error("getAnAdvertiser() : Exception "+ exp.toString());
			throw exp;
		} finally{
			//making the instance suitable for GC.
			advertiserCredentialMappings =null;
		}
		return advertiser;
		
	}
		
}
