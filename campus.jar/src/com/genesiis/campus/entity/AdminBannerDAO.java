package com.genesiis.campus.entity;

/*
 * 20170216 DN c131-admin-manage-banner-upload-banner-image-dn intial class stub has been created
 * 20170217 DN c131-admin-manage-banner-upload-banner-image-dn initial methods
 * 			   addBannerRecordInOneTransAction()/getTheURLType()/formADate() have been created 
 * 
 */

import org.apache.log4j.Logger; 

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;






import java.util.GregorianCalendar;

import com.genesiis.campus.command.CmdAdminBannerUpload;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.LinkType;

public class AdminBannerDAO implements ICrud {

	static Logger Log = Logger.getLogger(AdminBannerDAO.class.getName());
	
	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object object,
			Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int addBannerRecordInOneTransAction(Object banner, String bannerImageExtension) throws SQLException, Exception{
		Connection con =null;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		String bannerImageExtenion= bannerImageExtension; // assign the image extension
		CmdAdminBannerUpload.JasonInflator innerBannerInflator = (CmdAdminBannerUpload.JasonInflator)banner;
		
		
		
		StringBuilder updateBannerSQL = new StringBuilder("BEGIN");
		
		updateBannerSQL.append("DECLARE @HasErrors int ");
		updateBannerSQL.append("DECLARE @MaxBannerCode int ");
		updateBannerSQL.append("DECLARE @BannerName varchar(50) ");
		
		updateBannerSQL.append("BEGIN TRANSACTION ");
		
		updateBannerSQL.append("INSERT INTO [CAMPUS].[BANNER]([IMAGE],[EXPIRATIONDATE],[TYPE],[DISPLAYDURATION],[LINKTYPE], ");
		updateBannerSQL.append("[URL],[ISACTIVE],[PAGESLOT],[ADVERTISER],[CRTON],[CRTBY],[MODON],[MODBY],[ACTIVATIONDATE]) ");
		updateBannerSQL.append("VALUES ");
		updateBannerSQL.append("('default.gif' ,?,?,?,?,?,?,?,?, "); //('default.gif' ,getdate()+4,1,5 ,1,'www.topjobs.lk' ,'1',1,1,
		updateBannerSQL.append("getdate(),'',getdate(),'',?);"); 	//getdate(),'admin',getdate(),'admin',getdate());
		updateBannerSQL.append("IF(@@ERROR !=0)");
		updateBannerSQL.append("SET @HasErrors = 1");
		
		updateBannerSQL.append("select @MaxBannerCode = MAX(CODE)  FROM campus.BANNER;");
		updateBannerSQL.append("IF(@@ERROR !=0)");
		updateBannerSQL.append("SET @HasErrors = 1");
		
		updateBannerSQL.append("set @BannerName= @MaxBannerCode;");
		updateBannerSQL.append("IF(@@ERROR !=0)");
		updateBannerSQL.append("SET @HasErrors = 1");
		
		updateBannerSQL.append("UPDATE campus.BANNER SET [IMAGE]= @BannerName+.'"+bannerImageExtenion+"'  WHERE code = @MaxBannerCode;");
		updateBannerSQL.append("IF(@@ERROR !=0)");
		updateBannerSQL.append("SET @HasErrors = 1");
		
		
		updateBannerSQL.append("IF @HasErrors>0 ");
		updateBannerSQL.append("ROLLBACK TRANSACTION ");
		updateBannerSQL.append("ELSE ");
		updateBannerSQL.append("COMMIT TRANSACTION ");
		updateBannerSQL.append("END ");
		
		try{
			prepareStatement = ConnectionManager.getConnection().prepareStatement(updateBannerSQL.toString());
			
			//populating the query with data
			prepareStatement.setDate(1,formADate("yyyy-M-dd",innerBannerInflator.getBannerPublishingEndDate()));
			prepareStatement.setString(2, "1"); //type ***
			prepareStatement.setInt(3, Integer.parseInt(innerBannerInflator.getDisplayDusration())); //DisplayDuration
			prepareStatement.setInt(4,getTheURLType(innerBannerInflator.getUrlMiniWebOrPage()).getMappingInt()); //LinkType 
			prepareStatement.setString(5,innerBannerInflator.getUrlToBeDirectedOnBannerClick()); //URL
			prepareStatement.setInt(6,  ApplicationStatus.ACTIVE.getStatusValue());//ISACTIVE
			prepareStatement.setInt(7, Integer.parseInt(innerBannerInflator.getBannerSlotCode()));//PAGESLOT
			prepareStatement.setInt(8, Integer.parseInt(innerBannerInflator.getAdvertiserCode())); //ADVERTISER
			prepareStatement.setDate(9,formADate("yyyy-M-dd",innerBannerInflator.getBannerPublishingDate()));//ACTIVATIONDATE
			
			
		} catch (SQLException sqle) {
			Log.error("addBannerRecordInOneTransAction(Object,String): SQLException"+sqle.toString());
			throw sqle;
			
		} catch (Exception exp) {
			Log.error("addBannerRecordInOneTransAction(Object,String): Exception"+exp.toString());
			throw exp;
		}
		 return 0;
	}
	
	/**
	 * formADate creates a java sql date
	 * @param dateFormat
	 * @param date
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	private Date formADate(String dateFormat,String date)throws NullPointerException,
	IllegalArgumentException,Exception{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			java.util.Date dateToCreate = sdf.parse(date);
			java.sql.Date sqlDate =  new java.sql.Date(dateToCreate.getTime());
			return sqlDate;
			
		}catch (NullPointerException npexp){
			Log.error("formADate(String,String):NullPointerException "+ npexp.toString());
			throw npexp;
			
		}catch (IllegalArgumentException ilarg) {
			Log.error("formADate(String,String): IllegalArgumentException"+ ilarg.toString());
			throw ilarg;
		}catch(Exception exp){
			Log.error("formADate(String,String): Exception"+ exp.toString());
			throw exp;
		}
		
	}
	
	/**
	 * getTheURLType method returns the enum link type
	 * by processing the parameter sends in. if in valid 
	 * parameter send other than the mappped int values 
	 * daeclared in LinkType.java enum class , then it returns 
	 * enum BAD_LINK 
	 * @param urlType
	 * @return
	 */
	private LinkType getTheURLType(String urlType){
		if(urlType=="1"){
			return LinkType.WEB_LINK;
		} else if(urlType=="2"){
			return LinkType.MINI_WEB_LINK;
		}else if(urlType=="0"){
			return LinkType.PAGE_LINK;
		}
		return LinkType.BAD_LINK;
	}
	
	
	
}
