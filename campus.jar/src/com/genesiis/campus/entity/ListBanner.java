package com.genesiis.campus.entity;
/*
 * 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn ListBanner.java class has been created
 */
import org.jboss.logging.Logger;

import com.genesiis.campus.util.BannerDisplayingInflator;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.PrevalentValidation;
import com.genesiis.campus.validation.Validatory;

import java.sql.SQLException;
import java.util.Collection;


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
