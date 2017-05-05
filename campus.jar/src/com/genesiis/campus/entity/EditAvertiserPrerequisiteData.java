package com.genesiis.campus.entity;

/*
 * 20170505 DN c89-admin-manage-advertiser-update-details-of-existing-advertiser-dn. created the initial 
 * 			class deceleration of EditvertiserPrerequisiteData.java.
 * 			execute() modified to retrieve all the registered active course providers.
 * 			getEditAdvertiserPageOnLoadData()/getAdvertisers()/setResponseCridentials() methods are created.
 */
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;

import org.apache.log4j.Logger; 

/**
 * The Class EditvertiserPrerequisiteData.
 * The responsibility of the class is to provide all the means to<br>
 * obtain the required data for the page that Edit an advertiser<br> 
 * @author dushantha DN.
 */
public class EditAvertiserPrerequisiteData {
	// field deceleration
		private static final Logger log = Logger.getLogger(EditAvertiserPrerequisiteData.class.getName());
		private IDataHelper helper;
		private volatile int successCode =0;
		private String message="";
		
		
		/**
		 * Constructor which takes IDataHelper type argument<br>
		 * and sets the analogous field with caller provided data.
		 * @param helper the helper
		 */
		public EditAvertiserPrerequisiteData(IDataHelper helper){
			this.helper= helper;
		}
		
		/**
		 * Gets the page on load data of the Edit advertiser page .<br>
		 * Generally the add advertiser requires all the available course providers code <br>
		 * and all the Countries to be retrieved from the database.
		 * Further on selection of country all the towns that belong to the country <br>
		 * should be extracted too.
		 * @author dushantha DN
		 * @return the adds the advertiser page on load data wrapped in a : Collection<Collection<String>>
		 * @throws SQLException the SQL exception
		 * @throws Exception the exception
		 */
		
		public Collection<Collection<String>> getEditAdvertiserPageOnLoadData()throws SQLException,
		Exception{	
			Collection<Collection<String>> preRequisteCollnWrapper = new ArrayList<Collection<String>>();
			
			try{
				Operation operation = Operation.getOperation(helper.getCommandCode()) ;
				switch(operation){
				case DISPLAY_PREREQUISITE_ADVERTISER_DATA:
					
					/* get the all the countries*/
					ICrud country2Dao = new Country2DAO();
					preRequisteCollnWrapper = country2Dao.getAll();
					
					/* retrieve all the registered active advertisers.*/
					ICrud registeredActivatedAdverTiser =getAdvertisers(ApplicationStatus.ACTIVE.getStatusValue());
					
					/* set the retrieved advertiser to the attribute*/
					helper.setAttribute("advertisers", registeredActivatedAdverTiser.getAll());
					
					/* set the success flag to success */
					this.setSuccessCode(1);
					
					/* Facilitating the GC to fetch elements */
					country2Dao =null;
					registeredActivatedAdverTiser=null;
					break;
				case DISPLAY_ADVERTISER_TOWN_DATA :					
					ICrud townDao = new TownDAO();
					preRequisteCollnWrapper = townDao.findById(Integer.parseInt(helper.getParameter("country")));
					this.setSuccessCode(1);
					townDao = null;
					break;
				default:
				Collection<String> signUpdata = new ArrayList<String>();
					preRequisteCollnWrapper.add(signUpdata); //NODATA
					break;
				}
				
				operation =null;
				
			} catch(SQLException sqle){				
				log.error("getAddAdvertiserPagOnLoadData():SQLException"+sqle.toString());
				throw sqle;
			} catch (Exception exp){				
				log.error("AddAdvertiserPagOnLoadData() : Exception"+exp.toString());
				throw exp;			
			} finally{
				this.setResponseCridentials(helper);;
			}
			
			return preRequisteCollnWrapper;
		}

		
		
		private GeneralPurposeDAO getAdvertisers(final int activestatus) {
			return new GeneralPurposeDAO(){
				
				/**
				 * getAll() method bears the responsibility of extracting all the advertisers
				 * who are featured and status been 1
				 * 
				 */
				@Override
				public Collection<Collection<String>> getAll() throws SQLException,
						Exception {
					// Extract all the course providers who are featured providers and status is one
							StringBuilder advertisersSQL =new StringBuilder("SELECT [CODE] ,[NAME]  FROM [CAMPUS].[ADVERTISER]");
							advertisersSQL.append("WHERE [ISACTIVE] = "+activestatus);		
							String allAdvertisersSQL = advertisersSQL.toString();
							Connection advertiserConnection =null;
							PreparedStatement preparedStatement = null;
							ResultSet resultSet = null;
							
							try{
								advertiserConnection =  ConnectionManager.getConnection();
								preparedStatement =advertiserConnection.prepareStatement(allAdvertisersSQL);
								resultSet = preparedStatement.executeQuery();
								final Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
								
								while(resultSet.next()){
									final ArrayList<String> advertiserRecord = new ArrayList<String>();
									advertiserRecord.add(resultSet.getString("CODE"));
									advertiserRecord.add(resultSet.getString("NAME"));
									outerWrapper.add(advertiserRecord);
								}
								
								return outerWrapper;
								
							}catch (SQLException sqle){
								log.error("getAll() :SQLException"+sqle.toString());
								throw sqle;
							} catch (Exception exp) {
								log.error("getAll() :Exception"+exp.toString());
								throw exp;
							} finally{
								DaoHelper.cleanup(advertiserConnection, preparedStatement, resultSet);
							}
				}
			};
			
			
		}
	
		/**
		 * Method sets the response credentials, It sets the successfulness or the failure code,
		 * and the message to be dispatched to the view to the response as attributes.
		 * @author dushantha DN
		 * setResponseCridentials sets the request attributes
		 * @param helper the new response cridentials
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
		
	

}
