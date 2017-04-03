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
 * 20170308 DN c131-admin-manage-banner-upload-banner-image-dn  made implemented ICrudSibling interface 
 * 			   and override the method  getAll(Object jsnObject)
 * 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn implemented methods getAplicationStatus(int) 
 *             and Collection<Collection<String>> getAll(Object). Add doc comments to class and methods
 * 20170313 DN c81-admin-manage-banner-add-and-view-banner-dn getAll(Object object)   innerCol.add(resultSet.getString("NAME ACTIVATIONDATE")
 * 			   corrected to innerCol.add(resultSet.getString("ACTIVATIONDATE") 
 * 20170315 DN c81-admin-manage-banner-add-and-view-banner-dn add ORDER BY ACTIVATIONDATE ASC to the  getAll(Object) method sql query. 
 * 20170321 DN c131-admin-manage-banner-upload-banner-image-dn typos corrected as per the QA comment 10 given in 201703132232-CN - Local test summary. 
 * 20170323 DN c83-admin-manage-banner-update-banner-info-dn add extra INNER JOIN statement with  [CAMPUS].PAGE in sql query of
 * 			   getAll(Object object) method and add   PG.NAME PAGE_NAME,PG.CODE PAGE_CODE to the select statement. Retrieve extra 02 columns namely
 * 			   PAGE_NAME and PAGE_CODE from the resultset.	
 * 			   add the missing "break" statement for the getAplicationStatus()s' case statement.
 * 20170324 DN c83-admin-manage-banner-update-banner-info-dn implemented the update(Object object) method to update the banner record.
 * 20170327 DN c83-admin-manage-banner-update-banner-info-dn update(Object) method has been changed to correct the syntax errors.
 *             Commented out the  [CRTON],[CRTBY] fields in the update query of update(Object object) method. 
 * 20170403 DN c86-admin-manage-banner-search-banner-dn getAll(Object object) method amended to the bannercode filter in the sql query string. 
 */

import com.genesiis.campus.command.CmdAdminBannerUpload;
import com.genesiis.campus.util.BannerDisplayingInflator;
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
import java.sql.SQLTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * AdminBannerDAO class process the Banner table on behalf of
 * Administrator operations.
 * @author dushantha
 *
 */
public class AdminBannerDAO implements ICrudSibling {

	static Logger Log = Logger.getLogger(AdminBannerDAO.class.getName());
	
	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
/**
 * method updates a banner records which is sent to the method by
 * encapsulating in the Object.<br>
 * @param Object is the JasonInflator<br>
 * If the update is success it returns the number of rows been updated <br>
 * if the update query fails then returns 0
 */
	@Override
	public int update(Object object) throws SQLException, Exception {
		
		JasonInflator aBannerRecord = (JasonInflator)object;
		String bannerCode = aBannerRecord.getBannerCode();
		Connection conn = null;
		PreparedStatement prepare = null;
		int result=0;
		String[] imageNameSplitter = aBannerRecord.getBannerImageName().split("\\.");
		String storingImageName = aBannerRecord.getBannerCode()+"."+imageNameSplitter[1];
		
		StringBuilder insertingQueryBuilder = new StringBuilder("UPDATE [CAMPUS].[BANNER] ");
		insertingQueryBuilder.append(" SET  ");
		insertingQueryBuilder.append("[IMAGE]= '"+storingImageName+"' ,[EXPIRATIONDATE] ='"+aBannerRecord.getBannerPublishingEndDate()+"',"); //+"1"+","
		insertingQueryBuilder.append("[DISPLAYDURATION] ="+Integer.parseInt(aBannerRecord.getDisplayDusration())+",");
		insertingQueryBuilder.append("[LINKTYPE] = "+getTheURLType(aBannerRecord.getUrlMiniWebOrPage()).getMappingInt()+",");
		insertingQueryBuilder.append("[URL] ='"+aBannerRecord.getUrlToBeDirectedOnBannerClick()+"',");
		insertingQueryBuilder.append("[ISACTIVE] ='"+ aBannerRecord.getBanerToBeActive()+"',");
		insertingQueryBuilder.append("[PAGESLOT] ="+ Integer.parseInt(aBannerRecord.getBannerSlotCode())+",");
		insertingQueryBuilder.append("[ADVERTISER]="+ Integer.parseInt(aBannerRecord.getAdvertiserCode())+"," );//('default.gif' ,getdate()+4,1,5 ,1,'www.topjobs.lk' ,'1',1,1,
		//insertingQueryBuilder.append("[CRTON] = getdate(),"); 
		//insertingQueryBuilder.append("[CRTBY] ='"+	aBannerRecord.getUser()+"',");
		insertingQueryBuilder.append("[MODON] =getdate(),");
		insertingQueryBuilder.append("[MODBY] ='"+	aBannerRecord.getUser()+"',");
		insertingQueryBuilder.append("[ACTIVATIONDATE] ='"+ java.sql.Date.valueOf(aBannerRecord.getBannerPublishingDate())+"' ");
		insertingQueryBuilder.append(" WHERE CODE ="+Integer.parseInt(aBannerRecord.getBannerCode()));
		insertingQueryBuilder.append(";" );   
		
		try {
			conn = ConnectionManager.getConnection();
			prepare = conn.prepareStatement(insertingQueryBuilder.toString());
			result = prepare.executeUpdate();	
			
		} catch (SQLTimeoutException stoe){
			Log.error("update(Object) : SQLTimeoutException "+stoe.toString());
			throw stoe;
		} catch (SQLException sqle) {
			Log.error("update(Object) : SQLException "+sqle.toString());
			throw sqle;
			
		} catch (Exception exp) {
			Log.error("update(Object) : Exception "+exp.toString());
			throw exp;
		} finally {
			DaoHelper.closeConnection(conn);
			DaoHelper.closeStatement(prepare);
		}
		return result;
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
	
	/**
	 * getAplicationStatus: method returns the appropriate ApplicationStatus 
	 *  based on the input int parameter.
	 * if the supplied int does not confirm to any valid ApplicationStatus,
	 * then ApplicationStatus.UNDEFINED will be returned.
	 * @param value
	 * @return ApplicationStatus <br>
	 * 			1-->  ApplicationStatus.ACTIVE<br>
	 * 			0-->  ApplicationStatus.INACTIVE<br>
	 * 			3-->  ApplicationStatus.PENDING<br>
	 * 			4-->  ApplicationStatus.EXPIRED<br>
	 * 			any thing else other than the above values
	 * 			returns ApplicationStatus.UNDEFINED.
	 */
	private ApplicationStatus getAplicationStatus(int value){
		ApplicationStatus activeStatus= ApplicationStatus.UNDEFINED;
		switch(value){
		case 1 :
			activeStatus = ApplicationStatus.ACTIVE;
			break;
		case 0:
			activeStatus = ApplicationStatus.INACTIVE;
			break;
		case 2:
			activeStatus = ApplicationStatus.PENDING;
			break;
		case 3:
			activeStatus = ApplicationStatus.EXPIRED;
			break;
		
		}
		return activeStatus;
	}
	
	
	/**
	 * getAll(Object o) method returns all the banners that all the advertiser have published
	 * Depending on the induced details of the incoming object
	 * @author dushantha DN
	 * @param  object :Object type argument which has the filtering criteria
	 * @return Collection<Collection<String>>
	 */
	
	@Override
	public Collection<Collection<String>> getAll(Object object) throws SQLException,
			Exception {
		
		BannerDisplayingInflator jsn = (BannerDisplayingInflator)object;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection<Collection<String>> outerWrapper = null;
		try {
			if(jsn == null)
				throw new NullPointerException("The parameter passed to the method is null.");
			
			int ISACTIVE 			=  getAplicationStatus(Integer.parseInt(jsn.getActiveInactiveStatus())).getStatusValue();
			String activationDate	=  jsn.getCommencingDate();
			String deActivateDate	=  jsn.getCessationDate();
			String bannerCode		=  jsn.getBannerCode();
					
			
			StringBuilder querybuilder = new StringBuilder(
					"SELECT BNR.*,PGS.NAME PAGESLOT_NAME,ADVR.NAME ADVERTISER_NAME,PG.NAME PAGE_NAME,PG.CODE PAGE_CODE ");
			querybuilder.append(" FROM [CAMPUS].BANNER BNR ");
			querybuilder.append(" INNER JOIN [CAMPUS].ADVERTISER ADVR ");
			querybuilder.append(" ON BNR.ADVERTISER = ADVR.CODE "
					+ " INNER JOIN"
					+ " [CAMPUS].PAGESLOT PGS"
					+ " ON BNR.PAGESLOT = PGS.CODE"
					+ " INNER JOIN [CAMPUS].PAGE PG"
					+ " ON PG.CODE=PGS.PAGE"
					+ " WHERE ADVR.ISACTIVE = "+ApplicationStatus.ACTIVE.getStatusValue()
					+ " AND PGS.ISACTIVE = "+ApplicationStatus.ACTIVE.getStatusValue()
					+ " AND PG.ISACTIVE = "+ApplicationStatus.ACTIVE.getStatusValue());
			
			if(ISACTIVE != ApplicationStatus.UNDEFINED.getStatusValue() ){
				querybuilder.append(" AND BNR.ISACTIVE = "+ISACTIVE+ " ");
			}
			if(activationDate!=null&& activationDate !=""){
				if(deActivateDate!=null && deActivateDate !=""){
					querybuilder.append(" AND (BNR.EXPIRATIONDATE<='"+deActivateDate+"' AND ACTIVATIONDATE>='"+activationDate+"')");
				}
				else{
					querybuilder.append(" AND ( ACTIVATIONDATE>='"+activationDate+"')");
				}				
			}
			if(bannerCode!=null && bannerCode !=""){
				querybuilder.append(" AND BNR.CODE = '"+bannerCode+"' ");
			}
			querybuilder.append(" ORDER BY ACTIVATIONDATE ASC ");
			
			conn = ConnectionManager.getConnection();
			statement = conn.prepareStatement(querybuilder.toString());
			resultSet = statement.executeQuery();
			outerWrapper = new ArrayList<Collection<String>>();
			
			while(resultSet.next()){
				
				Collection<String> innerCol = new ArrayList<String>();				
				innerCol.add(resultSet.getString("CODE"));	//0
				innerCol.add(resultSet.getString("IMAGE"));	//1
				innerCol.add(resultSet.getString("DISPLAYDURATION")); //2
				innerCol.add(resultSet.getString("LINKTYPE")); //3
				innerCol.add(resultSet.getString("URL"));  //4
				innerCol.add(resultSet.getString("PAGESLOT")); //5
				innerCol.add(resultSet.getString("PAGESLOT_NAME")); //6
				innerCol.add(resultSet.getString("ISACTIVE"));//7
				innerCol.add(resultSet.getString("ACTIVATIONDATE")); //8
				innerCol.add(resultSet.getString("EXPIRATIONDATE")); //9
				innerCol.add(resultSet.getString("ADVERTISER"));	//10
				innerCol.add(resultSet.getString("ADVERTISER_NAME")); //11
				innerCol.add(resultSet.getString("PAGE_NAME"));//12
				innerCol.add(resultSet.getString("PAGE_CODE"));//13
				
				outerWrapper.add(innerCol);
			}
			
		} catch (NullPointerException npexp) {
			Log.error("getAll(Object): NullPointerException :  "+npexp.toString());
			throw npexp;			
		} catch (SQLException sqle){
			Log.error("getAll(Object): SQLException : "+sqle.toString());
			throw sqle;
		} catch (Exception exp){
			Log.error("getAll(Object): Exception : "+exp.toString());
			throw exp;
		} finally{
			DaoHelper.cleanup(conn, statement, resultSet);
		}
		
		return outerWrapper;
	}
	
	
	
}
