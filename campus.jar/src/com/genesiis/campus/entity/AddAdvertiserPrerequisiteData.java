package com.genesiis.campus.entity;
/**
 * 20170424 DN c88-admin-manage-advertiser-add-new-advertiser-dn. created the initial 
 * 				class deceleration of AddAdvertiserPrerequisiteData.java.
 * 			execute() modified to retrieve all the registered active course providers.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger; 


/**
 * The Class AddAdvertiserPrerequiaiteData.
 * The responsibility of the class is to provide all the means to<br>
 * obtain the required data for the page that adds a new advertiser<br> 
 * @author dushantha DN.
 */
public class AddAdvertiserPrerequisiteData {
	
	// field deceleration
	private static final Logger log = Logger.getLogger(AddAdvertiserPrerequisiteData.class.getName());
	private IDataHelper helper;
	private volatile int successCode =0;
	private String message="";
	
	
	/**
	 * Constructor which takes IDataHelper type argument<br>
	 * and sets the analogous field with caller provided data.
	 * @param helper the helper
	 */
	public AddAdvertiserPrerequisiteData(IDataHelper helper){
		this.helper= helper;
	}
	
	
	/**
	 * Gets the page on load data of the add advertiser page .<br>
	 * Generally the add advertiser requires all the available course providers code <br>
	 * and all the Countries to be retrieved from the database.
	 * Further on selection of country all the towns that belong to the country <br>
	 * should be extracted too.
	 * @author dushantha DN
	 * @return the adds the advertiser page on load data wrapped in a : Collection<Collection<String>>
	 * @throws SQLException the SQL exception
	 * @throws Exception the exception
	 */
	
	public Collection<Collection<String>> getAddAdvertiserPageOnLoadData()throws SQLException,
	Exception{
		Collection<Collection<String>> preRequisteCollnWrapper = new ArrayList<Collection<String>>();
		try{
			Operation op =Operation.getOperation(helper.getCommandCode()) ;
			switch(op){
				case DISPLAY_PREREQUISITE_DATA:
					ICrud country2Dao = new Country2DAO();
					preRequisteCollnWrapper = country2Dao.getAll();
					// retrieve all the registered active advertisers.
					ICrud registeredActivatedAdverTiser = new AdvertiserDAO();
					helper.setAttribute("advertisers", registeredActivatedAdverTiser.getAll());
					
					this.setSuccessCode(1);
					
					country2Dao =null;
					registeredActivatedAdverTiser=null;
					break;
				case DISPLAY_TOWN_DATA :					
					ICrud townDao = new TownDAO();
					preRequisteCollnWrapper = townDao.findById(Integer.parseInt(helper.getParameter("country")));
					this.setSuccessCode(1);
					townDao = null;
					break;
				default:
					Collection<String> signUpdata = new ArrayList<String>();
					preRequisteCollnWrapper.add(signUpdata); //NODATA
					signUpdata = null;
					break;
			}			
			op =null;
		} catch(SQLException sqle){
			log.error("getAddAdvertiserPagOnLoadData():SQLException"+sqle.toString());
			throw sqle;
		} catch (Exception exp){
			log.error("etAddAdvertiserPagOnLoadData() : Exception"+exp.toString());
			throw exp;			
		} finally{
			this.setResponseCridentials(helper);;
		}
		return preRequisteCollnWrapper;
	}

	/**
	 * Method sets the response credentials, It sets the successfulness or the failure code,
	 * and the message to be dispatched to the view to the response as attributes
	 * @author dushantha DN
	 * setResponseCridentials sets the request attributes
	 * @param helper: It is the HttpServletrequest wrapper instance.
	 */
	private void setResponseCridentials(IDataHelper helper){
		helper.setAttribute("successCode", getSuccessCode());
		helper.setAttribute("message", message);
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
