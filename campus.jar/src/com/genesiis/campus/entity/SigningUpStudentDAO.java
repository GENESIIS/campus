package com.genesiis.campus.entity;

//20161121 MM c25-student-login-create-dashboard-MP-mm INIT - Initialised file
//20161122 MM c25-student-login-create-dashboard-MP-mm Added code to retrieve more columns from the result set
//20161122 MM c25-student-login-create-dashboard-MP-mm Fixed logger class import issue
//20161128 DN C18-student-signup-without-using-third-party-application-dn userNameExisyt() -->userNameAndEmailExist()
//		add(),addSignInDataWOThirdPartyAppToRepository():changed to use trim() and bug fixed in code
//20161207 DN C18: add data to town field in Student table from back end in addSignInDataWOThirdPartyAppToRepository() method 
//20161207 DN C18: add USERTYPE to the data base table STUDENT
//20161214 DN CAM:18 changed the method addSignInDataWOThirdPartyAppToRepository() to accommodate town code as a Long
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class SigningUpStudentDAO implements ICrud {
	
	static Logger Log = Logger.getLogger(SigningUpStudentDAO.class.getName());

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
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

		final Collection<Collection<String>> studentDetailsCollectionList = new ArrayList<Collection<String>>();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Student student = (Student) code;
			int studentCode = student.getCode();

			String query = "SELECT * FROM [CAMPUS].[STUDENT] WHERE CODE = ?";

			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, studentCode);
			ResultSet rs = ps.executeQuery();

			retrieveStudentsFromResultSet(rs, studentDetailsCollectionList);

		} catch (ClassCastException cce) {
			Log.info("findById(Object): ClassCastException: " + cce.toString());
			throw new IllegalArgumentException(
					"The argument passed is not of expected type (Programme)!");
		} catch (SQLException sqle) {
			Log.info("findById(Object): SQLException: " + sqle.toString());
			throw sqle;
		} catch (Exception e) {
			Log.info("findById(Object): Exception: " + e.toString());
			throw e;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return studentDetailsCollectionList;
	}

	private void retrieveStudentsFromResultSet(ResultSet rs,
			Collection<Collection<String>> studentList) throws SQLException {

		while (rs.next()) {
			final ArrayList<String> singleStudent = new ArrayList<String>();
			singleStudent.add(rs.getString("CODE")); // 0
			singleStudent.add(rs.getString("USERNAME")); // 1
			singleStudent.add(rs.getString("PASSWORD")); // 2
			singleStudent.add(rs.getString("INDEXNO")); // 3
			singleStudent.add(rs.getString("FIRSTNAME")); // 4
			singleStudent.add(rs.getString("MIDDLENAME")); // 5
			singleStudent.add(rs.getString("LASTNAME")); // 6
			singleStudent.add(rs.getString("DATEOFBIRTH")); // 7
			singleStudent.add(rs.getString("GENDER")); // 8
			singleStudent.add(rs.getString("EMAIL")); // 9
			singleStudent.add(rs.getString("TYPE")); // 10
			singleStudent.add(rs.getString("IMAGEPATH")); // 11
			singleStudent.add(rs.getString("LANDPHONECOUNTRYCODE")); // 12
			singleStudent.add(rs.getString("LANDPHONEAREACODE")); // 13
			singleStudent.add(rs.getString("LANDPHONENO")); // 14
			singleStudent.add(rs.getString("MOBILEPHONECOUNTRYCODE")); // 15
			singleStudent.add(rs.getString("MOBILEPHONEAREACODE")); // 16
			singleStudent.add(rs.getString("MOBILEPHONENO")); // 17
			singleStudent.add(rs.getString("DESCRIPTION")); // 18
			singleStudent.add(rs.getString("FACEBOOKURL")); // 19
			singleStudent.add(rs.getString("TWITTERURL")); // 20
			singleStudent.add(rs.getString("MYSPACEURL")); // 21
			singleStudent.add(rs.getString("LINKEDINURL")); // 22
			singleStudent.add(rs.getString("INSTAGRAMURL")); // 23
			singleStudent.add(rs.getString("VIBERNUMBER")); // 24
			singleStudent.add(rs.getString("WHATSAPPNUMBER")); // 24
			singleStudent.add(rs.getString("ADDRESS1")); // 24
			singleStudent.add(rs.getString("ADDRESS2")); // 24
			singleStudent.add(rs.getString("ADDRESS3")); // 24
			singleStudent.add(rs.getString("TOWN")); // 24
			singleStudent.add(rs.getString("ACCOUNTTYPE")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINUSERAGENT")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINSESSIONID")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINDATE")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINTIME")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDINIPADDRESS")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDOUTDATE")); // 24
			singleStudent.add(rs.getString("LASTLOGGEDOUTTIME")); // 24
			singleStudent.add(rs.getString("LASTLOGINAUTHENTICATEDBY")); // 24
			singleStudent.add(rs.getString("ISACTIVE")); // 24
			singleStudent.add(rs.getString("CRTON")); // 24
			singleStudent.add(rs.getString("CRTBY")); // 24
			singleStudent.add(rs.getString("MODON")); // 24
			singleStudent.add(rs.getString("MODBY")); // 24
			final Collection<String> singleStudentCollection = singleStudent;
			studentList.add(singleStudentCollection);
		}
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object) throws SQLException,
			Exception {
			Connection conn = null;
			final ArrayList<String> singleStudent = new ArrayList<String>();
			Student student = (Student)object;
			String  userName = student.getUsername().trim();
			String email = student.getEmail().trim();
			PreparedStatement prepstmt =null;
			ResultSet res = null;
			int status=0;
			try{
				conn =ConnectionManager.getConnection();
				res = userNameAndEmailExist(conn,prepstmt,userName,email);
				if(res.next()){
					status= -1; // a record exists
				} else {
					status = addSignInDataWOThirdPartyAppToRepository(conn,prepstmt,student);
				}
			}catch (SQLException sqle){
				Log.error("findById(Object objecConnection conn) :SQLException "+sqle.toString());  
				throw sqle;
			} catch(Exception e){
				Log.error("findById(Object objecConnection conn) :Exception "+e.toString());
				throw e;
			} finally{
				if(prepstmt!=null)prepstmt.close();
				if(conn != null) conn.close();
			}
			return status;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException,Exception {
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
		
		 return null;
		
}
	
/*
 * Method use connection and adds the sign in information
 * to the repository table:Student
 * @param conn Connection to the data base
 * @return int:integer containing the status of the update if success returns >0
 * else = value.
 * @throws SQLException
 * @throws Exception
 */
	private int addSignInDataWOThirdPartyAppToRepository(Connection conn,PreparedStatement prepstmt,Student student)throws SQLException,Exception{
		int status = 0;
		StringBuilder queryBuilder = new StringBuilder("INSERT INTO [CAMPUS].[STUDENT]");
		queryBuilder.append(" ([USERNAME],[PASSWORD],[FIRSTNAME],[LASTNAME],[GENDER],[EMAIL],");
		queryBuilder.append(" [MOBILEPHONENO],[TOWN],[CRTON],[CRTBY],[MODON],[MODBY],[MOBILEPHONECOUNTRYCODE],[MOBILEPHONENETWORKCODE],[USERTYPE] ) ");
		queryBuilder.append("  VALUES( ?,?,?,?,?,?,?,?,getDate(),?,getDate(),?,?,?,? );");
		try{
				Log.info("Adding user Account datails to the repository");
				prepstmt =conn.prepareStatement(queryBuilder.toString());		
				Encryptable passwordEncryptor = new TripleDesEncryptor(student.getPassword());
				prepstmt.setString(1, student.getUsername());
				prepstmt.setString(2, passwordEncryptor.encryptSensitiveDataToString()); 
				prepstmt.setString(3, student.getFirstName());
				prepstmt.setString(4, student.getLastName());
				prepstmt.setInt(5, student.getGender());
				prepstmt.setString(6,student.getEmail());
				prepstmt.setString(7,student.getMobilePhoneNo());//correct
				prepstmt.setLong(8,Long.parseLong(student.getTown())); //Default town code is set when a new account is created
				prepstmt.setString(9,student.getUsername());
				prepstmt.setString(10,student.getUsername()); // this has to change once the Login session is implemented
				prepstmt.setString(11,student.getMobilePhoneCountryCode());
				prepstmt.setString(12,student.getMobilePhoneNetworkCode());
				prepstmt.setInt(13,student.getUserTypeCode());
				status = (prepstmt.executeUpdate()==1)?1:-2; //if execution succeeded set to 1 else to -2
				
			
		} catch(SQLException sqle) {
			Log.error("addSignInDataWOThirdPartyAppToRepository(): SQLException"+ sqle.toString());
			throw sqle;
		} catch(Exception exp) {
			Log.error("addSignInDataWOThirdPartyAppToRepository(): Exception"+exp.toString());
			throw exp;
		}
		return status;
	}
	
	/*
	 * method UserNameExist() checks if the if there an record exist for the given
	 * username and the email address. since both
	 * @returns returns ResultSet object which is empty if there is no any records else return null
	 * having values that is returned from the data base
	 * @param con Connection data base connection
	 * @param String userName
	 * @param String email
	 * 
	 */
	private ResultSet userNameAndEmailExist( Connection con,PreparedStatement prs,String userName,String email) throws SQLException {
		String userName1 = ((userName==null)||(userName==""))?null:userName;
		String emailAddress = ((email==null)||(email==""))?null:email;
		StringBuilder sb = new StringBuilder("SELECT [USERNAME],[ISACTIVE],[EMAIL] FROM [CAMPUS].[STUDENT] ");		
		sb.append(" WHERE [USERNAME]  = ? OR [EMAIL]= ?  ;");
		final String checkActive = sb.toString();
		ResultSet res = null;
		try{
			Log.info("Checking if User name and Email is Already Taken");
			prs = con.prepareStatement(checkActive);
			prs.setString(1, userName1);
			prs.setString(2,emailAddress);
			boolean resStatus = false;
			res= prs.executeQuery();
			
		} catch (SQLException sqle) {			
			Log.error("userNameAndEmailExist(): SQLException"+ sqle.toString());
			throw sqle;
		} catch(Exception exp) {
			Log.error("userNameAndEmailExist(): Exception"+ exp.toString());
			throw exp;
		} 
		return res;
	}
	
}