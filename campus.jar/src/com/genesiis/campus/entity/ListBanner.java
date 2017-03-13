package com.genesiis.campus.entity;
/*
 * 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn ListBanner.java class has been created
 * 				implemented getBanners(),BannerDisplayingInflator(),setResponseCridentials() methods
 * 				And add documentation comments.
 *  20170309 DN c81-admin-manage-banner-add-and-view-banner-dn
 * 				addAttribute(IDataHelper,String,Object) and addToTheInnerCollection(Collection<Collection<String>>)
 * 				implemented
 */
import org.jboss.logging.Logger;

import com.genesiis.campus.util.BannerDisplayingInflator;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.JasonInflator;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.SystemMessage;
import com.genesiis.campus.validation.Validatory;
import com.genesiis.campus.validation.PrevalentValidation.FailedValidationException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


/**
 * ListBanner helps to encapsulate the logic and conditions
 * associated with displaying all the banners.
 * The purpose is to decouple the client being knowing 
 * the lower level details.
 * @author dushantha DN
 *
 */

public class ListBanner {
	private static final Logger log = Logger.getLogger(ListBanner.class.getName());
	private Validatory preValidatory = new PrevalentValidation();
	IDataHelper helper;
	private int successCode =0;
	private String message="";
	
	/**
	 * ListBanner constructor that accepts a IDataHelper
	 * instance and binds to the ListBanner itself.
	 * @param helper
	 */
	public ListBanner(IDataHelper helper){
		this.helper = helper;
	}
	
	/**
	 * getBanners() querying on the table Banner at DAO level and returns all the
	 * 	available Banners in a Collection of Collection of Strings.
	 * e.g. [[1st row],[2nd row],[nth row]]	
	 * 	[1st row]:--> [a,b,c,..,] 			
	 * @param object : Object which encapsulate the required operational
	 *  			  data which should be loaded with in order 
	 *  			  to use at DAO level classes
	 *                 when function on database
	 * @return Collection<Collection<String>>
	 * @throws SQLException
	 * @throws Exception
	 */
	public Collection<Collection<String>> getBanners() throws SQLException,Exception{
		
		Collection<Collection<String>> bannerCollection =null;
		try {
			
			  ICrudSibling adminBannerDao = new AdminBannerDAO();
			  bannerCollection= adminBannerDao.getAll(inflateBannerFilterCredential(helper));
			  this.setSuccessCode(1);
			  this.setResponseCridentials(helper);
		} catch (SQLException sqle) {
			log.error("getBanners(Object) : SQLException "+sqle.toString());
			throw sqle;
		} catch (Exception exp) {
			log.error("getBanners(Object) :Exception"+exp.toString());
			throw exp;
		}
		return bannerCollection;
	}
	
	/*
	 * inflateBannerFilterCredential() creates an 
	 * instance of BannerDisplayingInflator which intends to encapsulate
	 * the filtering data of the listing of Banners.
	 * @author dushantha DN
	 * @param helper: IDataHelper which wraps the HttpServletRequest and HttpServletResponce
	 * @return BannerDisplayingInflator : which encapsulate the user provide filtering 
	 * data of the banner displaying criteria.
	 */
	private BannerDisplayingInflator inflateBannerFilterCredential(IDataHelper helper){
		
		BannerDisplayingInflator bannerDisplayCredential = new BannerDisplayingInflator();
		bannerDisplayCredential.setCommencingDate(helper.getParameter("commencingDate"));
		bannerDisplayCredential.setCessationDate(helper.getParameter("cessationDate"));
		bannerDisplayCredential.setActiveInactiveStatus(helper.getParameter("activeInactiveStatus"));
		return bannerDisplayCredential;
		
	}
	/**
	 * addToTheInnerCollection method add element to the innerCollection
	 * @param wraper
	 * @param value
	 * @return boolean true if adding is successfull else false
	 */
	public boolean addToTheInnerCollection(Collection<Collection<String>> wraper,String value) {
		boolean addingSuccess =false;
		for(Collection<String> innerCol:wraper ){
			addingSuccess=innerCol.add(value);
		}
		return addingSuccess;
	}
	
	/**
	 * addAttribute method sets the request attribute
	 * @param helper
	 * @param attributeName
	 * @param attribute
	 */
	public void addAttribute(IDataHelper helper,String attributeName,Object attribute){
		helper.setAttribute(attributeName,attribute);
	}
	
	/*
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
	 * isClientInputAccordanceWithValidation() validates if the input fields are 
	 * having values and those are according to the business logic.
	 * @param rowDisplayCriteria : JasonInflator the object that is having the de-serialized values sent from
	 * 				  client side
	 * @return boolean
	 * @throws Exception
	 */

	private boolean isClientInputAccordanceWithValidation(BannerDisplayingInflator rowDisplayCriteria) throws Exception {
		boolean isvalidationSuccess = false;
		if(rowDisplayCriteria==null){
			log.info("isClientInputAccordanceWithValidation (): --> JasonInflator object is null ");
			this.message = message +" "+SystemMessage.UPDATE_SUCCESSFUL;
			return false;
		}
		
		
		try {
			
			// validation has to done in a such a way that if none of the values are provieded then it's ok
			//if one date is provided then we have to start validation
			//TO BE DONE ON 20170314
			    String dateCommenced = rowDisplayCriteria.getCommencingDate();
			    String dateCessation = rowDisplayCriteria.getCessationDate();
				Date filterStartDate = this.getADate("-",dateCommenced );
				Date filterEndDate = this.getADate("-",dateCessation);
				int activeInactiveStatus = Integer.parseInt(rowDisplayCriteria.getActiveInactiveStatus());
			    Validatory clientInputValidator = new PrevalentValidation();
			    
			    
//			    clientInputValidator.isNotEmpty(advertiserCode," Advertiser field is empty !");
//				clientInputValidator.isInteger(advertiserCode," Choose Advertiser from the list");
//				clientInputValidator.isNotEmpty(codeOfSelectedPage,"Advertiser field is empty !");
//				clientInputValidator.isInteger(codeOfSelectedPage," Choose a page from the list");
//				clientInputValidator.isNotEmpty(bannerSlotCode," Choose a page slot from the list !");
//				clientInputValidator.isNotEmpty(displayDusration," Display Duration field is empty !");
//				clientInputValidator.isInteger(displayDusration,"Kindly enter a numerical value");
//				clientInputValidator.isNotEmpty(banerToBeActive,"Please select enable or dissable option");
//				clientInputValidator.isNotEmpty(bannerPublishingDate,"Publishing Date field is empty !");
//				clientInputValidator.isNotEmpty(bannerPublishingEndDate,"Endp Publishing Date field is empty !");
//				
//				Date publishingDate 	= getADate("-",bannerPublishingDate);
//				Date endPublishingDate 	= getADate("-",bannerPublishingEndDate);
//				// comparison with the current date
//				if(!(clientInputValidator.compareDates(publishingDate, endPublishingDate,"yyyy-MM-dd", "date comparison failure")<=0))
//					throw new PrevalentValidation().new FailedValidationException("Publishing Start Date must be <= Publishing end Date");
//				
//				if(!(clientInputValidator.compareDates(publishingDate, new Date(),"yyyy-MM-dd", "date comparison failure")>=0))
//					throw new PrevalentValidation().new FailedValidationException(" Publishing Start Date must be >= Current Date");
//				
//				clientInputValidator.isInteger(urlMiniWebOrPage);
//				//clientInputValidator.isUrlValid(urlToBeDirectedOnBannerClick, UrlValidator.ALLOW_ALL_SCHEMES+UrlValidator.ALLOW_LOCAL_URLS, "Url provided is not a valid URL");
//				isvalidationSuccess=true;
				
//		} catch (FailedValidationException fvexp) {
//			this.message = message +" "+ fvexp.toString();
//			this.setSuccessCode(-2); 
//			return false;
		} catch(Exception exp){
			log.error("isClientInputAccordanceWithValidation(JasonInflator) : Exception "+ exp.toString());
			throw exp;
		}
		return isvalidationSuccess;
	}
	
	/**
	 * getADate() returns a date.
	 * Method accepts a date in the form yyy?MM?dd
	 * ? denotes the delimiter which should be passed to the method, 
	 * using which the string date is split and forms a java.util.date
	 * @param dateDelemeter : can be any printable string character 
	 *  e.g. "-" "," "/" etc 
	 * @param date should be adhere to teh format yyy?MM?dd
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
