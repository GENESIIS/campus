package com.genesiis.campus.entity;

/*
 * 20170426 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class  AdvertiserFacilitator.java has been created.
 * 20170427 DN c88-admin-manage-advertiser-add-new-advertiser-dn. created the methods processAdvertiser(),createNewAdvertiser()
 * 				getAdvertiserClientFedData() and add doc comments and in line comments to the class.
 * 				The method  setResponseCridentials(IDataHelper) is implemented.
 * 				The error :jdbc.SQLServerException: The index 19 is out of range has been corrected in createNewAdvertiser() method.
 * 				Removed the preceding '+' sign from the country code of both land line and the mobile in createNewAdvertiser() method.
 */
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.JasonInflator;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;




/**
 * The Class AdvertiserFacilitator.
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
	 * Instantiates a new advertiserfacilitater.<br>
	 *This constructor accept an argument IDataHelper<br>
	 *to 
	 * @param helper the IDataHelper
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
						 executionStatus=createNewAdvertiser();
					
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
	private int createNewAdvertiser() throws SQLException,Exception{
		//call request parameters and set the values
		Map<String,String[]> advertiserCredentialMappings = helper.getParameterMap() ;
		this.setMessage(""); // clears any message brought forward
		//call the data base to insert the record
		int status = new GeneralPurposeDAO(){
			
			public int update(Object object) throws SQLException, Exception {
				@SuppressWarnings("unchecked")
				Map<String,String[]> advertiserCredentialMappings = (Map<String,String[]>) object;
				
				Connection conn=null;
				PreparedStatement prepare = null;				
				String ACTIVE = Integer.toString(ApplicationStatus.INACTIVE.getStatusValue());
				String modOrCreateBy =(String) helper.getSession(true).getAttribute("currentSessionUsername");
				int executionStatus = 0;
				
				String sqlInsertAdvertiser = "INSERT INTO [CAMPUS].[ADVERTISER] ";
						sqlInsertAdvertiser = sqlInsertAdvertiser+"([NAME],[EMAIL],[LANDPHONECOUNTRYCODE],[LANDPHONEAREACODE] ,[LANDPHONENUM],[MOBILEPHONECOUNTRYCODE]";
						sqlInsertAdvertiser = sqlInsertAdvertiser+",[MOBILEPHONENETWORKCODE],[MOBILEPHONENUM],[DESCRIPTION],[ADDRESS1],[ADDRESS2] ,[ADDRESS3]";
						sqlInsertAdvertiser = sqlInsertAdvertiser+",[TOWN],[ISACTIVE],[COURSEPROVIDER] ,[CRTON],[CRTBY],[MODON] ,[MODBY])";
						sqlInsertAdvertiser = sqlInsertAdvertiser +"VALUES";
						sqlInsertAdvertiser =sqlInsertAdvertiser +"(?,?,?,?,?,?,?,?,?,?,?,?,";
						sqlInsertAdvertiser =sqlInsertAdvertiser +" ?,?,?,getDate(),?,getDate(),? );";
				try{
					conn = ConnectionManager.getConnection();		
					prepare = conn.prepareStatement(sqlInsertAdvertiser);
					prepare.setString(1, advertiserCredentialMappings.get("advertiserName")[0]);
					prepare.setString(2, advertiserCredentialMappings.get("advertiserEmail")[0]);
					prepare.setString(3, advertiserCredentialMappings.get("landCountryCode")[0].split("\\+")[1]);
					prepare.setString(4, advertiserCredentialMappings.get("landAreaCode")[0]);
					prepare.setString(5, advertiserCredentialMappings.get("landPhoneNumber")[0]);				
					prepare.setString(6, advertiserCredentialMappings.get("mobileCountryCode")[0].split("\\+")[1]);
					prepare.setString(7, advertiserCredentialMappings.get("mobileAreaCode")[0]);
					prepare.setString(8, advertiserCredentialMappings.get("mobilePhoneNumber")[0]);	
					prepare.setString(9, advertiserCredentialMappings.get("advertiserDescription")[0]);
					prepare.setString(10, advertiserCredentialMappings.get("address1")[0]);
					prepare.setString(11, advertiserCredentialMappings.get("address2")[0]);
					prepare.setString(12, advertiserCredentialMappings.get("address3")[0]);				
					prepare.setString(13, advertiserCredentialMappings.get("townCode")[0]);
					
					if(advertiserCredentialMappings.get("AdvertiserStatus")[0].equals("1"))					
						ACTIVE=Integer.toString(ApplicationStatus.ACTIVE.getStatusValue());				
					prepare.setString(14, ACTIVE);
					
					prepare.setString(15, advertiserCredentialMappings.get("courseProviderCode")[0]);
					
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
					advertiserCredentialMappings=null;
					sqlInsertAdvertiser=null;
				}
				return executionStatus;
			}
		}.update(advertiserCredentialMappings);
		
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
	 * @author dushantha DN
	 * @return boolean
	 * @throws Exception
	 */

	private boolean isClientInputAccordanceWithValidation() throws Exception {
		boolean isvalidationSuccess = true;	
		
		
		return isvalidationSuccess;
	}
	
	/**
	 * Gets the helper.
	 * @return the helper
	 */
	public IDataHelper getHelper() {
		return helper;
	}

	/**
	 * Sets the helper.
	 * @param helper the new helper
	 */
	public void setHelper(IDataHelper helper) {
		this.helper = helper;
	}

	/**
	 * Gets the success code.
	 * @return the success code
	 */
	public int getSuccessCode() {
		return successCode;
	}

	/**
	 * Sets the success code.
	 * @param successCode the new success code
	 */
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}

	/**
	 * Gets the message.
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
