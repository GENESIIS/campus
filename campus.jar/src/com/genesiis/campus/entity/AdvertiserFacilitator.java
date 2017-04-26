package com.genesiis.campus.entity;

/*
 * 20170426 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The Class  AdvertiserFacilitator.java has been created.
 */

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.IDataHelper;

public class AdvertiserFacilitator {
	
	private static Logger log = Logger.getLogger(AdvertiserFacilitator.class.getName());
	private IDataHelper helper;
	private volatile int successCode =0;
	private String message="";
	
	public AdvertiserFacilitator(IDataHelper helper){
		this.helper =helper;
	}

	public int createNewAdvertiser() throws SQLException,Exception{
		//call request parameters and set the values
		//call the data base call to insert the record
		
		return 0;
	}
	
	
	
}
