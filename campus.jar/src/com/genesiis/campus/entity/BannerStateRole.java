package com.genesiis.campus.entity;
/*
 * 20170418 DN c86-admin-manage-banner-search-banner-dn. The initial class BannerStateRole.java is created
 * 20170419 DN c86-admin-manage-banner-search-banner-dn. implemented updateBannerStatus(),getStringBannerIdConvertedToArryOfInt()
 * 				statusUpdate(),setResponseCridentials() methods and add the doc comments.
 * 20170420 DN c86-admin-manage-banner-search-banner-dn.The method getStringBannerIdConvertedToArryOfInt() is included null checks 
 * 				for the array that brings the banner ids for deactivation or activation.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger; 

import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.Operation;

/**
 * Class BannerStateRole encapsulate the operations and the data<br>
 * that keeps the state of the banner
 * @author dushantha
 *
 */
public class BannerStateRole {
	private static final Logger log = Logger.getLogger(BannerStateRole.class.getName());
	private IDataHelper helper;
	private int successCode =0;
	private String message="";
	
	public BannerStateRole(IDataHelper helper){
		this.helper = helper;
	}
	
/**
 * updateBannerStatus method updates the status of a selective set of banners<br>
 * of which the banner ids are provided. Depending on the command string that the<br> 
 * user issues e.g. DEACTIVATE_BANNER/ACTIVATE_BANNER status of the banner is set <br> 
 * to active or inactive status.
 * @throws Exception
 */
	public void updateBannerStatus() throws Exception{
		message="";
		Operation op = Operation.getOperation(this.helper.getCommandCode());
		List<Integer> bannerIdsAndActivDeactive = new ArrayList<Integer>();
		try{
			bannerIdsAndActivDeactive = getStringBannerIdConvertedToArryOfInt();		
			switch(op){
			case DEACTIVATE_BANNER :			
				int activeStatus = ApplicationStatus.INACTIVE.getStatusValue();
				bannerIdsAndActivDeactive.add(activeStatus);
				break;
			case ACTIVATE_BANNER :			
				activeStatus = ApplicationStatus.ACTIVE.getStatusValue();
				bannerIdsAndActivDeactive.add(activeStatus);
				break;
			default:
					
			}
			
		} catch (Exception exp){
			log.error("updateBannerStatus() :Exception "+exp.toString());
			throw exp;
		}
		
		this.statusUpdate(bannerIdsAndActivDeactive);
		
	}
	
/**
 * Method converts Banner ids that comes as a string to an List<br>
 * 	of Integers.
 * @return List<Integer> containing the banner ids
 * @throws NumberFormatException :if the string does not contain a parsable integer.
 */
	private List<Integer> getStringBannerIdConvertedToArryOfInt()
			throws StringIndexOutOfBoundsException, PatternSyntaxException,
			NumberFormatException {
	try{
		StringBuilder strb = new StringBuilder(helper.getParameter("selectedBannerCode"));//-->["235","238","237"]
		String[] treatedBannerIdArray = strb.substring(1,strb.length()-1).replaceAll("\"", "").toString().split(",");
		Integer[] bannerIds =new Integer[treatedBannerIdArray.length];
		
		// if no id's are selected the array contains 1 element which is null
		// avoid  it by checking for null element.
		if(bannerIds.length==1 && treatedBannerIdArray[0]==null ){ 
			
			List<Integer> listId = new ArrayList<Integer>();
			listId.add(null);
			return listId;
		}
		
		for(int x=0;x<treatedBannerIdArray.length;x++){
			 bannerIds[x]= Integer.parseInt(treatedBannerIdArray[x]);
		 }
		
		return new ArrayList<Integer>( Arrays.asList(bannerIds));
	} catch (StringIndexOutOfBoundsException sobexp){
		log.error("getStringBannerIdConvertedToArryOfInt() :StringIndexOutOfBoundsException "+sobexp.toString());
		throw sobexp;
	} catch (PatternSyntaxException psexp) {
		log.error("getStringBannerIdConvertedToArryOfInt() :PatternSyntaxException "+psexp.toString());
		throw psexp;
	} catch (NumberFormatException nfexp) {
		log.error("getStringBannerIdConvertedToArryOfInt() :NumberFormatException "+nfexp.toString());
		throw nfexp;
	}
	
}

/**
 * Method updates the status of the banners,<br>
 *  banner Ids are given by the bannerIdsAndStatus parameter.
 * @param bannerIdsAndStatus : List<Integer> containing ids of the banners <br>
 * 	last element of the List is the activation or the deactivation flag.<br>
 * using the flag data base updating is performed.
 * @author dushantha DN
 * @throws SQLException : if the Banner Record status update issues a DB error
 * @throws Exception : 
 */
private void statusUpdate(List<Integer> bannerIdsAndStatus) throws SQLException, Exception{
	
	// create an adapter class as this is used once and GC will collect
	// Anonymous adaptor class has been created that is a sub class of AdminBannerDAO
	ICrudSibling bannerStatusDAO = new AdminBannerDAO (){
		/**
		 * method updates the banner records of which the banner code(s) are sent via the Object <br>
		 * that is passed in to the method itself.
		 * @param 	object : it is a List &lt Integer &gt	
		 * @author dushantha DN
		 * @return int : if none of the records are updated returns 0 <br>
		 * 				else the number od records been affected are returned 
		 */
			@Override
			public int update(Object object) throws SQLException, Exception {				
				if(!(object instanceof ArrayList)){
					return -1;
				}
				@SuppressWarnings("unchecked")
				ArrayList <Integer> bannerIdsAndStatus = (ArrayList<Integer>) object;
				int ACTIVE = bannerIdsAndStatus.get(bannerIdsAndStatus.size()-1); // last element is the active and inactive flag
				bannerIdsAndStatus.remove(bannerIdsAndStatus.size()-1); //remove the active flag from the list
				String bannerIds = "";
				int totalelements = bannerIdsAndStatus.size();				
				int iterationCounter=0;
				
				if((bannerIdsAndStatus.get(0)==null)){
					bannerIds=null;
					
				} else{
					
					while(iterationCounter<totalelements){
						if(totalelements >= 1 && iterationCounter==0){
							bannerIds =""+bannerIdsAndStatus.get(iterationCounter).intValue();
						}else {
							bannerIds = bannerIds +","+bannerIdsAndStatus.get(iterationCounter).intValue();
						}
						iterationCounter ++;
					}
				}
				
				Connection con 			  = null;
				int result 			  = 0;
				PreparedStatement prepare = null;				
				String sqlUpdate = "UPDATE [CAMPUS].[BANNER] SET ISACTIVE = "+ACTIVE+" WHERE CODE IN ("+bannerIds+");";
				
				try{
					con = ConnectionManager.getConnection();
					prepare = con.prepareStatement(sqlUpdate);
					result = prepare.executeUpdate();
					
				} catch (SQLTimeoutException stoe){
					Log.error("update(Object) : SQLTimeoutException "+stoe.toString());
					throw stoe;
				} catch (SQLException sqle) {
					Log.error("update(Object) : SQLException "+sqle.toString());
					throw sqle;
				}catch (Exception exp) {
					Log.error("update(Object) : Exception "+exp.toString());
					throw exp;
				} finally {
					DaoHelper.closeConnection(con);
					DaoHelper.closeStatement(prepare);
					bannerIdsAndStatus = null;
					bannerIds = null;
					sqlUpdate = null;
				}
				return result;
			}
	};
	
	try{
		 int statusOfUpdate = bannerStatusDAO.update(bannerIdsAndStatus);
		 // setting the success code.
		  setSuccessCode( (statusOfUpdate < 0 )? 0: 1);
		  
		 if(statusOfUpdate==0){
			 this.message = message +" No any records belongs to this criteria to be updated !";
		 } else {
			 this.message = message + statusOfUpdate + " record(s) updated ";
			
		 }
		 
	 } catch (SQLException sqle) {
			log.error("getBanners(Object) : SQLException "+sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("getBanners(Object) :Exception"+exp.toString());
			throw exp;
		} finally {
			this.setResponseCridentials(helper);
		}
	 
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
	
}
