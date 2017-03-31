package com.genesiis.campus.entity;

/*
 * 20170216 DN c131-admin-manage-banner-upload-banner-image-dn intial class stub has been created
 * 20170217 DN c131-admin-manage-banner-upload-banner-image-dn initial methods
 * 			   addBannerRecordInOneTransAction()/getTheURLType()/formADate() have been created 
 * 20170220 DN c131-admin-manage-banner-upload-banner-image-dn addBannerRecordInOneTransAction() re factor and
 * 			   add doc comments.
 * restructured the addBannerRecordInOneTransAction() sql query to be a stored proc alike.
 * 20170221 DN c131-admin-manage-banner-upload-banner-image-dn addBannerRecordInOneTransAction() and add 
 * 				in line variables instead of setting via setxxx() jDBC methods to set '?' values
 * 				in insertUpdateBannerTableSQL query.
 * 20170221 DN c131-admin-manage-banner-upload-banner-image-dn add lines to manage the auto commit option
 * 			   manually in addBannerRecordInOneTransAction(). changed the return type from int to
 * 			   Collection<Collection<String>>.
 * 20170223 DN c131-admin-manage-banner-upload-banner-image-dn commented the [TYPE] field as the field is deprecated in future use
               from [CAMPUS].[BAANER] table.
 * 20170224 DN c131-admin-manage-banner-upload-banner-image-dn The Order of the retrieving statements 
 *             of [IMAGE] and [CODE] in method addBannerRecordInOneTransAction() has been changed. 
 * 20170306 DN c131-admin-manage-banner-upload-banner-image-dn removed the method formADate() from the class and restructured with
 * 			   logic and placed in PrevalentValidation.java  
 * 20170321 DN c131-admin-manage-banner-upload-banner-image-dn typos corrected as per the QA comment 10 given in 201703132232-CN - Local test summary.   
 */

import com.genesiis.campus.command.CmdAdminBannerUpload;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;
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
import java.util.ArrayList;
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
	 * when a Banner is added to the System, and if the initial operation
	 * reported success then the method returns the banner code and the
	 * Banner Image name from the data base in a collection wrapped within a collection.
	 * 
	 * @param banner : JasonInflator instance wrapped as an Object
	 * @param bannerImageExtension : extension of the banner e.g. jpg,jpg,ping etc.
	 * @param userName : user name of the user who fires the method e.g."admin" etc
	 * @return Collection<Collection<String>> : Single record is wrapped within 
	 * another collection e.g. {{x,y},{s,d},..}
	 * @throws SQLException
	 * @throws Exception
	 */
	public Collection<Collection<String>> addBannerRecordInOneTransAction(Object banner, String bannerImageExtension,String userName) throws SQLException, Exception{
		Connection conn =null;
		PreparedStatement insertAndUpdateBannerTabelStatement = null;
		PreparedStatement retrieveBannerImageStatement = null;
		ResultSet result = null;
		String bannerImageExtenion= bannerImageExtension; // assign the image extension
		JasonInflator innerBannerInflator = (JasonInflator)banner;
		String modByAndCrtBy =userName;
		
		String insertUpdateBannerTableSQL = "DECLARE @sqlString nvarchar(MAX);"
				+"SET @sqlString =' "
				+"BEGIN "
				+"DECLARE @HasErrors int;"
				+"	DECLARE @MaxBannerCode int;"
				+"	DECLARE @BannerName varchar(20);"
				+"BEGIN TRANSACTION"
				+"	INSERT INTO [CAMPUS].[BANNER]"
				+"          ([IMAGE],[EXPIRATIONDATE],"
				//+ "[TYPE],"
				+ "[DISPLAYDURATION],[LINKTYPE]"
				+"          ,[URL],[ISACTIVE],[PAGESLOT],[ADVERTISER],[CRTON],[CRTBY]"
				+"          ,[MODON],[MODBY],[ACTIVATIONDATE])"
				+"  VALUES"
				+"         (''default."+bannerImageExtenion+"'' ,''"+java.sql.Date.valueOf(innerBannerInflator.getBannerPublishingEndDate())+"'',"//+"1"+","
				+	Integer.parseInt(innerBannerInflator.getDisplayDusration())+","
				+	getTheURLType(innerBannerInflator.getUrlMiniWebOrPage()).getMappingInt()+",''"
				+	innerBannerInflator.getUrlToBeDirectedOnBannerClick()+"'',"
				+	ApplicationStatus.ACTIVE.getStatusValue()+","
				+	Integer.parseInt(innerBannerInflator.getBannerSlotCode())+","
				+	Integer.parseInt(innerBannerInflator.getAdvertiserCode())+"," //('default.gif' ,getdate()+4,1,5 ,1,'www.topjobs.lk' ,'1',1,1,
				+	"getdate(),''"
				+	modByAndCrtBy+"'',getdate(),''"
				+	modByAndCrtBy +"'',''"
				+  java.sql.Date.valueOf(innerBannerInflator.getBannerPublishingDate())
				+"'');"      //getdate(),'admin',getdate(),'admin',getdate());
				+"IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"

				+"	select @MaxBannerCode = MAX(CODE)  FROM campus.BANNER;"
				+"	IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"
				
				+"	set @BannerName= @MaxBannerCode;"
				+"	IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"

				+"	UPDATE campus.BANNER SET [IMAGE]= @BannerName+''."+bannerImageExtenion+"'' WHERE code = @MaxBannerCode;"				
				+"	IF(@@ERROR !=0)"
				+"	SET @HasErrors = 1;"
				
				+"	IF @HasErrors>0"
				+"		ROLLBACK TRANSACTION;"
				
				+"	ELSE"
				+"		COMMIT TRANSACTION;"
				+"END'"
				+"EXECUTE sp_executesql @sqlString;";
				
		
		String bannerImageSQL = "select [IMAGE],[CODE] FROM campus.BANNER where code = (select MAX(CODE)  FROM campus.BANNER);";
		Collection<Collection<String>> outerWrapper =null;
		try{
			conn = ConnectionManager.getConnection();
			
			outerWrapper = new ArrayList<Collection<String>>();
			conn.setAutoCommit(false);
			insertAndUpdateBannerTabelStatement =	conn.prepareStatement(insertUpdateBannerTableSQL);
			retrieveBannerImageStatement = conn.prepareStatement(bannerImageSQL.toString());
			int status = insertAndUpdateBannerTabelStatement.executeUpdate();
		
			if(status >0){
				result = retrieveBannerImageStatement.executeQuery();				
				while(result.next()){
					Collection<String> bannerArray = new ArrayList<String>();					
					bannerArray.add(result.getString("CODE"));
					bannerArray.add(result.getString("IMAGE"));
					outerWrapper.add(bannerArray);
				}
				
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException sqle) {
			conn.rollback();
			Log.error("addBannerRecordInOneTransAction(Object,String): SQLException"+sqle.toString());
			throw sqle;
			
		} catch (Exception exp) {
			conn.rollback();
			Log.error("addBannerRecordInOneTransAction(Object,String): Exception"+exp.toString());
			throw exp;
		} finally{
			conn.setAutoCommit(true);
			DaoHelper.cleanup(conn, insertAndUpdateBannerTabelStatement, result);
			DaoHelper.closeStatement(retrieveBannerImageStatement);
		}
		return outerWrapper; 
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
