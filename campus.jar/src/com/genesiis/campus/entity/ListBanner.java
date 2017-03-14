package com.genesiis.campus.entity;
/*
 * 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn ListBanner.java class has been created
 * 				implemented getBanners(),BannerDisplayingInflator(),setResponseCridentials() methods
 * 				And add documentation comments.
 * 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn
 * 				addAttribute(IDataHelper,String,Object) and addToTheInnerCollection(Collection<Collection<String>>)
 * 				implemented.
 * 20170314 DN c81-admin-manage-banner-add-and-view-banner-dn refactor the method 
 *              Date getADate(String dateDelemeter,String date) to include custom error handling.
 *              getBanners() method is amended to add validation part and ammended the doc comments.
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
import java.util.regex.PatternSyntaxException;


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
	 * <p>getBanners() querying on the table Banner at DAO level and returns all the
	 * available Banners in a Collection of Collection of Strings according to the specified filtering criteria.<br>
	 * </P>
	 * e.g. [[1st row],[2nd row],[nth row]]	<br>
	 * 	[1st row]:--> [a,b,c,..,] 		
	 * </p>	
	 * <b>NOTE</b><br>
	 *  <p>
	 * This method provides a none null Collection&lt;Collection&lt;String&gt;&gt; if  a valid value<br>
	 * for the first date (start date)
	 * is provided or if all the fields (filtering commencing date and filtering end date) left blank,<br>
	 *  then the method thinks there is no requirement
	 * for validation and all the banner records for active advertisers will be provided. 
	 * Further if the client has provided values for all the fields but start date <br>
	 * there will validation errors be thrown.
	 * 
	 *  The banners will be returned if the validation is successful else returns null
	 * @param object :<p> Object which encapsulate the required operational
	 *  			  data which should be loaded with in order 
	 *  			  to use at DAO level classes
	 *                 when function on database</p>
	 * @return Collection&lt;Collection&lt;String&gt;&gt;
	 * @throws SQLException
	 * @throws Exception
	 */
	public Collection<Collection<String>> getBanners() throws SQLException,Exception{
		
		Collection<Collection<String>> bannerCollection =null;
		try {
			  BannerDisplayingInflator bannerDisplayCredential = inflateBannerFilterCredential(helper);
			  if (this.isClientInputAccordanceWithValidation(bannerDisplayCredential)){
				  ICrudSibling adminBannerDao = new AdminBannerDAO();
				  bannerCollection= adminBannerDao.getAll(bannerDisplayCredential);
				  this.setSuccessCode(1);
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
	 * <p>
	 * This method validates the fields if and only if the first date (start date)
	 * is provided. If it is not provided the method thinks there has no requirement
	 * for validation. 
	 * Further if the client has provided values for all the fields but start date
	 * there will validation errors be thrown.
	 * </P>
	 * @param rowDisplayCriteria : JasonInflator the object that is having the de-serialized values sent from
	 * 				  client side
	 * @return boolean 
	 * @throws Exception
	 */

	private boolean isClientInputAccordanceWithValidation(BannerDisplayingInflator rowDisplayCriteria) throws Exception {
		boolean isvalidationSuccess = false;
		if(rowDisplayCriteria==null){
			log.info("isClientInputAccordanceWithValidation (): --> rowDisplayCriteria object is null ");
			this.message = message +" "+SystemMessage.EMPTY_SEARCH_RESULT;
			this.setSuccessCode(-1);
			return isvalidationSuccess;
		}
		try {
			    String dateCommenced = rowDisplayCriteria.getCommencingDate();
			    String dateCessation = rowDisplayCriteria.getCessationDate();
			    
				
				
				//int activeInactiveStatus = Integer.parseInt(rowDisplayCriteria.getActiveInactiveStatus()); // if required for future use.
			    Validatory clientInputValidator = new PrevalentValidation();
			    boolean firstDateIsFilledAndNotNull =(dateCommenced != null) && (dateCommenced.trim().isEmpty() == false);
			    boolean secondDateIsFilledAndNotNull= (dateCessation != null) && (dateCessation.trim().isEmpty() == false);
			    /*
			     * The validation for fields will commences if and only if the
			     * start date has been filled by the user. else the system consider 
			     * that the user required all the banner data to be listed in which case the
			     * fields would not be required to be filled.
			     */
			    if (firstDateIsFilledAndNotNull){
			    		if(secondDateIsFilledAndNotNull){
			    			
			    			Date filterStartDate = this.getADate("-",dateCommenced );
			    			Date filterEndDate = this.getADate("-",dateCessation);
			    			int comparison=clientInputValidator.compareDates(filterStartDate, filterEndDate, "yyyy-MM-dd", SystemMessage.INVALID_DATE_FORMAT.message());
			    			if(comparison > 0)
			    				throw new PrevalentValidation().new FailedValidationException(SystemMessage.FIRST_DATE_GT_SECOND_DATE.message());
			    			isvalidationSuccess =true; //validation success.
			    		} else{
			    			throw new PrevalentValidation().new FailedValidationException(SystemMessage.INVALID_DATE.message()+
			    					" fill in a correct date \n for the end date field");
			    		}
			    
			    } else if (secondDateIsFilledAndNotNull) {
			    	throw new PrevalentValidation().new FailedValidationException(SystemMessage.INVALID_DATE.message()+" fill start date field");
			    } 
			    isvalidationSuccess =true;
			    
		}catch (FailedValidationException fvexp ){
			log.error("getBanners(Object) : FailedValidationException"+ fvexp.toString() );
			isvalidationSuccess =false;
			this.message = message +" "+ fvexp.toString();
			this.setSuccessCode(-2);
		}
		catch(Exception exp){
			log.error("isClientInputAccordanceWithValidation(JasonInflator) : Exception "+ exp.toString());
			throw exp;
		}
		return isvalidationSuccess;
	}
	
	/**
	 * getADate() returns a date.
	 * Method accepts a date in the form yyy?MM?dd <br>
	 * <b>?</b> denotes the <i>delimiter </i> which should be passed to the method,  
	 * using which the string date is split and forms a java.util.date.<br>
	 * @param dateDelemeter : can be any printable string character 
	 *  e.g. "-" "," "/" etc 
	 * @param date should be adhere to the format yyy?MM?dd &nbsp;<i><b>e.g. 2017-05-26</b></i><br>
	 * 			yyyy: year <br>
	 * 			MM  : Month <br>
	 * 			dd  : date <br>
	 * 		
	 * @return java.util.Date type or null
	 * @throws FailedValidationException 
	 */
	 private  Date getADate(String dateDelemeter,String date) throws FailedValidationException{
		 Calendar cal=null;
		 try {
				String [] array = date.split(dateDelemeter);
				cal = Calendar.getInstance();
				cal.set(Calendar.YEAR,Integer.parseInt(array[0]));
				cal.set(Calendar.MONTH,Integer.parseInt(array[1])-1);
				cal.set(Calendar.DATE,Integer.parseInt(array[2]));
				
			} catch (NumberFormatException nfExp) {
				log.error("getADate(String,String) :NumberFormatException:"+ nfExp.toString());
				throw new PrevalentValidation().new FailedValidationException("Date entered does not confirm \n to the required format");
				
			} catch (PatternSyntaxException psExp) {
				log.error("getADate(String,String) :PatternSyntaxException:"+ psExp.toString());
				throw new PrevalentValidation().new FailedValidationException("Date part seperator does not confirm \n to the required format ");
			}
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
