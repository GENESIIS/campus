package com.genesiis.campus.entity;

/*
 * 20170426 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class  AdvertiserFacilitator.java has been created.
 * 20170427 DN c88-admin-manage-advertiser-add-new-advertiser-dn. created the methods processAdvertiser(),createNewAdvertiser()
 * 				getAdvertiserClientFedData() and add doc comments and in line comments to the class.
 */
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;




/**
 * The Class AdvertiserFacilitator.
 */
public class AdvertiserFacilitator {
	
	
	private static org.jboss.logging.Logger log = Logger.getLogger(AdvertiserFacilitator.class.getName());	
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
		switch(operation){
		case CREATE_NEW_ADVERTISER :			
			try{
				// validate incoming data is correct
				
				//get the data
				createNewAdvertiser();
			} catch (SQLException sqle){
				log.error("processAdvertiser() SQLException: "+sqle.toString());
				throw sqle;
			} catch (Exception exp) {
				log.error(""+exp.toString());
				throw exp;
			} finally{
				
			}
			
			break;
			default:
				break;
		} 
		
	}
	
	
	/**
	 * Creates the new advertiser.
	 *
	 * @return the int
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	private int createNewAdvertiser() throws SQLException,Exception{
		//call request parameters and set the values
		Map<String,String> advertiserCredentialMappings = this.getAdvertiserClientFedData() ;
		//call the data base call to insert the record
		
		int status = new GeneralPurposeDAO(){
			public int update(Object object) throws SQLException, Exception {
				
				
				return 0;
			}
		}.update(advertiserCredentialMappings);
		
		return 0;
	}
	
	/**
	 * Gets the advertiser client fed data.
	 * @return the advertiser client fed data
	 */
	private Map<String,String> getAdvertiserClientFedData(){
		
		return null;
		
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
