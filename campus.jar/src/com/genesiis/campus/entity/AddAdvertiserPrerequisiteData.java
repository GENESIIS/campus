package com.genesiis.campus.entity;
/**
 * 20170424 DN c88-admin-manage-advertiser-add-new-advertiser-dn. created the initial 
 * 				class deceleration of AddAdvertiserPrerequisiteData.java.
 * 			execute() modified to retrieve all the registered active course providers.
 * 20170425 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The method getAddAdvertiserPageOnLoadData() changed to 
 *             include AllPurposeDAO class, when calls to Database.
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
					ICrud registeredActivatedAdverTiser =new AllPurposeDAO(){
						
						/**
						 * getAll() method bears the responsibility of extracting all the course providers
						 * who are featured and status been 1
						 * 
						 */
						@Override
						public Collection<Collection<String>> getAll() throws SQLException,
								Exception {
							// Extract all the course providers who are featured providers and status is one
									StringBuilder CourseProvidersSQL =new StringBuilder("SELECT [CODE] ,[SHORTNAME]  FROM [CAMPUS].[COURSEPROVIDER]");
									CourseProvidersSQL.append("WHERE [COURSEPROVIDERSTATUS] = "+ApplicationStatus.ACTIVE.getStatusValue());		
									String allCourseProviderSQL = CourseProvidersSQL.toString();
									Connection courseProviderConnection =null;
									PreparedStatement preparedStatement = null;
									ResultSet resultSet = null;
									
									try{
										courseProviderConnection =  ConnectionManager.getConnection();
										preparedStatement =courseProviderConnection.prepareStatement(allCourseProviderSQL);
										resultSet = preparedStatement.executeQuery();
										final Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();
										
										while(resultSet.next()){
											final ArrayList<String>courseProviderRecord = new ArrayList<String>();
											courseProviderRecord.add(resultSet.getString("CODE"));
											courseProviderRecord.add(resultSet.getString("SHORTNAME"));
											outerWrapper.add(courseProviderRecord);
										}
										
										return outerWrapper;
										
									}catch (SQLException sqle){
										log.error("getAll() :SQLException"+sqle.toString());
										throw sqle;
									} catch (Exception exp) {
										log.error("getAll() :Exception"+exp.toString());
										throw exp;
									} finally{
										DaoHelper.cleanup(courseProviderConnection, preparedStatement, resultSet);
									}
						}
					};
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
	 * Method returns the course provider informations bound to<br>
	 * the request courseProvider code which is passed in as type String.
	 *
	 * @param courseProviderCode the course provider code :String
	 * @return Collection &lt;Collection&lt;String&gt;&gt: contains the details of the Course Provider.<br>
	 * If the returning Collection of collection of string becomes length 1<br>
	 * i.e it contains only one element which is Collection&lt;String&gt;.<br>
	 * size of which reflects the number of columns which has been extracted.<br>
	 * If that collection (the latter one) is zero length, then there are no records for the selected course provider code
	 * @author dushantha DN
	 * @throws Exception
	 */
	public Collection<Collection<String>> getCourseProviderboundToSelectedCode(String courseProviderCode) throws Exception {
		
		Collection<Collection<String>> adviserCredentials = new ArrayList<Collection<String>>();
		try{
			
			adviserCredentials = new AllPurposeDAO(){				
				public Collection<Collection<String>> findById(Object object) throws SQLException, Exception {
					
					Collection<Collection<String>> outerWrapper = new ArrayList<Collection<String>>();					
					if(!(object instanceof String)){
						throw new IllegalArgumentException("CourseProvider Code is not Type String..Check for null ");//return an empty collection of collection.
					}
					
			Connection conn = null;
			PreparedStatement prepare = null;
			ResultSet result = null;
			String sqlSelect = "SELECT * FROM [CAMPUS].COURSEPROVIDER WHERE Code = ?;";
			try{
				conn = ConnectionManager.getConnection();
				prepare = conn.prepareStatement(sqlSelect);
				prepare.setInt(1, Integer.parseInt((String)object));
				result = prepare.executeQuery();
				while(result.next()){
					Collection <String> innerWrapper = new ArrayList<String>();
					innerWrapper.add(result.getString("CODE")); 
					innerWrapper.add(result.getString("SHORTNAME"));
					innerWrapper.add(result.getString("GENERALEMAIL")); 
					innerWrapper.add(result.getString("LANDPHONECOUNTRYCODE"));
					innerWrapper.add(result.getString("LANDPHONEAREACODE"));
					innerWrapper.add(result.getString("LANDPHONENO"));					
					innerWrapper.add(result.getString("MOBILEPHONECOUNTRYCODE")); 
					innerWrapper.add(result.getString("MOBILEPHONENETWORKCODE"));
					innerWrapper.add(result.getString("MOBILEPHONENO")); 
					innerWrapper.add(result.getString("DESCRIPTION"));
					innerWrapper.add(result.getString("ADDRESS1"));
					innerWrapper.add(result.getString("ADDRESS2"));
					innerWrapper.add(result.getString("ADDRESS3"));					
					innerWrapper.add(result.getString("COURSEPROVIDERSTATUS"));
					//town is missing; 
					outerWrapper.add(innerWrapper);
				}
				return outerWrapper;
			} catch (SQLException sqle){
				log.error("findById(Object): SQLException "+ sqle.toString());
				throw sqle;				
			} catch (NumberFormatException nfexp){
				log.error("findById(Object) : NumberFormatException "+nfexp.toString());
				throw nfexp;
			} catch (Exception exp){
				log.error("findById(Object) : Exception "+exp.toString());
				throw exp;
			} finally {
				DaoHelper.cleanup(conn, prepare, result);
				sqlSelect =null;				
			}
				} 
			}.findById(courseProviderCode);
			this.setSuccessCode(1);
			
		}catch (SQLException sqle){
			log.error("getAdvertisersBoundToCourseProviderCode():SQLException"+sqle.toString());
			throw sqle;
		} catch (Exception exp){
			log.error("getAdvertisersBoundToCourseProviderCode() : Exception"+exp.toString());
			throw exp;			
		} finally{
			this.setResponseCridentials(helper);;
		}
		return adviserCredentials;
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
