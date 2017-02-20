package com.genesiis.campus.entity;

/*
 * 20170216 DN c131-admin-manage-banner-upload-banner-image-dn intial class stub has been created
 * 20170217 DN c131-admin-manage-banner-upload-banner-image-dn initial methods
 * 			   addBannerRecordInOneTransAction()/getTheURLType()/formADate() have been created 
 * 20170220 DN c131-admin-manage-banner-upload-banner-image-dn addBannerRecordInOneTransAction() re factor and
 * 			   add doc comments.
 * restructured the addBannerRecordInOneTransAction() sql query to be a stored proc alike.
 */

import com.genesiis.campus.command.CmdAdminBannerUpload;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.JasonInflator;
import com.genesiis.campus.validation.ApplicationStatus;
import com.genesiis.campus.validation.LinkType;
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

	/**
	 * addBannerRecordInOneTransAction Method insert a record to table banner 
	 * when a Banner is added to the System.
	 * @param banner : JasonInflator instance wrapped as an Object
	 * @param bannerImageExtension : extension of the banner eg. jpg,jpg,ping etc.
	 * @param userName : user name of the user who fires the method eg."admin" etc
	 * @return int : 
	 * @throws SQLException
	 * @throws Exception
	 */
	public int addBannerRecordInOneTransAction(Object banner, String bannerImageExtension,String userName) throws SQLException, Exception{
		Connection conn =null;
		PreparedStatement insertAndUpdateBannerTabelStatement = null;
		PreparedStatement retrieveBannerImageStatement = null;
		ResultSet result = null;
		String bannerImageExtenion= bannerImageExtension; // assign the image extension
		//CmdAdminBannerUpload.JasonInflator innerBannerInflator = (CmdAdminBannerUpload.JasonInflator)banner;
		JasonInflator innerBannerInflator = (JasonInflator)banner;
		String modByAndCrtBy =userName;
		
		
		
		String insertUpdateBannerTableSQL = "";
		 insertUpdateBannerTableSQL = "DECLARE @sqlString nvarchar(MAX);"
				+"SET @sqlString =' "
				+"BEGIN "
				+"DECLARE @HasErrors int;"
				+"	DECLARE @MaxBannerCode int;"
				+"	DECLARE @BannerName varchar(20);"
				+"BEGIN TRANSACTION"
				+"	INSERT INTO [CAMPUS].[BANNER]"
				+"          ([IMAGE],[EXPIRATIONDATE],[TYPE],[DISPLAYDURATION],[LINKTYPE]"
				+"          ,[URL],[ISACTIVE],[PAGESLOT],[ADVERTISER],[CRTON],[CRTBY]"
				+"          ,[MODON],[MODBY],[ACTIVATIONDATE])"
				+"  VALUES"
				+"         (''default.gif'' ,getdate()+4,1,5 ,1,''www.topjobs.lk'' ,''1'',1,1"
				+"        ,getdate(),''admin'',getdate(),''admin'',getdate());"
				+"IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"

				+"	select @MaxBannerCode = MAX(CODE)  FROM campus.BANNER;"
				+"	IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"
				+"	 select * from campus.BANNER;"
				+"	set @BannerName= @MaxBannerCode;"
				+"	IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"

				+"	UPDATE campus.BANNER SET [IMAGE]= @BannerName+''.jpg'' WHERE code = @MaxBannerCode;"
				+"	select * from campus.BANNER;"
				+"	IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"

				+"	IF @HasErrors>0"
				+"		ROLLBACK TRANSACTION;"
				+"	ELSE"
				+"		COMMIT TRANSACTION;"
				+"END'"
				+"EXECUTE sp_executesql @sqlString;";
		
		StringBuilder bannerImageSQL = new StringBuilder("select [IMAGE] FROM campus.BANNER where code = (select MAX(CODE)  FROM campus.BANNER);");
		
		try{
			conn = ConnectionManager.getConnection();
			
			//conn.setAutoCommit(false);
			
			insertAndUpdateBannerTabelStatement =	conn.prepareStatement(insertUpdateBannerTableSQL.toString());
			retrieveBannerImageStatement = conn.prepareStatement(bannerImageSQL.toString());
			//populating the query with data
			insertAndUpdateBannerTabelStatement.setDate(1,formADate("yyyy-M-dd",innerBannerInflator.getBannerPublishingEndDate()));
			insertAndUpdateBannerTabelStatement.setString(2, "1"); //type ***
			insertAndUpdateBannerTabelStatement.setInt(3, Integer.parseInt(innerBannerInflator.getDisplayDusration())); //DisplayDuration
			insertAndUpdateBannerTabelStatement.setInt(4,getTheURLType(innerBannerInflator.getUrlMiniWebOrPage()).getMappingInt()); //LinkType 
			insertAndUpdateBannerTabelStatement.setString(5,innerBannerInflator.getUrlToBeDirectedOnBannerClick()); //URL
			insertAndUpdateBannerTabelStatement.setInt(6,  ApplicationStatus.ACTIVE.getStatusValue());//ISACTIVE
			insertAndUpdateBannerTabelStatement.setInt(7, Integer.parseInt(innerBannerInflator.getBannerSlotCode()));//PAGESLOT
			insertAndUpdateBannerTabelStatement.setInt(8, Integer.parseInt(innerBannerInflator.getAdvertiserCode())); //ADVERTISER
			insertAndUpdateBannerTabelStatement.setString(9,modByAndCrtBy); //crtby
			insertAndUpdateBannerTabelStatement.setString(10,modByAndCrtBy); //modby
			insertAndUpdateBannerTabelStatement.setDate(11,formADate("yyyy-M-dd",innerBannerInflator.getBannerPublishingDate()));//ACTIVATIONDATE
			
			int status = insertAndUpdateBannerTabelStatement.executeUpdate();
			return status;
			
		} catch (SQLException sqle) {
			Log.error("addBannerRecordInOneTransAction(Object,String): SQLException"+sqle.toString());
			throw sqle;
			
		} catch (Exception exp) {
			Log.error("addBannerRecordInOneTransAction(Object,String): Exception"+exp.toString());
			throw exp;
		}
		 
	}
	
	/*
	 * formADate creates a java sql date from the passed in parameter date.
	 * @param dateFormat: the format we required the return value to be in. 
	 * 						e.g.  "dd/MM/yyyy, dd-MM-yyyy, MM/dd/yyyy, yyyy-MM-dd."
	 * @param date : String date in the format e.g "2017-02-14" "
	 * @return java.sql.Date if the date has been set else return null
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	private Date formADate(String dateFormat,String date)throws NullPointerException,
	IllegalArgumentException,Exception{
		java.sql.Date sqlDate = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			java.util.Date dateToCreate = sdf.parse(date);
			 sqlDate =  new java.sql.Date(dateToCreate.getTime());
			
			
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
		return sqlDate;
	}
	
	/*
	 * getTheURLType method returns the enum link type
	 * by processing the parameter sends in. if in valid 
	 * parameter send other than the mapped int values 
	 * Declared in LinkType.java enum class , then it returns 
	 * enum BAD_LINK 
	 * @param urlType
	 * @return
	 */
	private LinkType getTheURLType(String urlType){
		if(urlType.equals("1")){
			return LinkType.WEB_LINK;
		} else if(urlType.equals("2")){
			return LinkType.MINI_WEB_LINK;
		}else if(urlType.equals("0")){
			return LinkType.PAGE_LINK;
		}
		return LinkType.BAD_LINK;
	}
	
	
	
}
