package com.genesiis.campus.command;

//20161026 DN c10-contacting-us-page created CmdGenerateEmail.java

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.SystemConfigDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.validation.Operation;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class CmdGenerateEmail implements ICommand {
	
	static Logger log = Logger.getLogger(CmdGenerateEmail.class.getName());
	String sendersName;
	String sendersEmailAddress;
	List<String> recieversEmailAddreses;
	String sendersphoneNumber;
	String mailingSubject;
	String mailBody;
	Connection connection = null;
	
	
	

	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {

		//getting the admin related data e.g email address
		sendersName = helper.getParameter("firstName")
				.concat(" "+ helper.getParameter("lastName"));		
		sendersEmailAddress = helper.getParameter("emailAddress");
		sendersphoneNumber= helper.getParameter("contactNumber");
		mailingSubject = helper.getParameter("subject");
		mailBody = helper.getParameter("message");
		String cco = helper.getCommandCode();
		String message = "";
		Collection<Collection<String>> collectionOfCollectionOfEmails = null;
		 
		ICrud genesiis = new SystemConfigDAO();
		
		 switch(Operation.getOperation(cco)){
		 case CONTACT_US_PUBLC:
			 this.createDatabaseConnection();
			 //SYSTEMCONFIGCODE for email
			 String[] sysEmailAdress = {"ENQUIRY_EMAIL_TO","ENQUIRY_EMIL_ADMIN"}; 
			 collectionOfCollectionOfEmails=genesiis.findById(sysEmailAdress, connection);
			 
			 
			 // TESTING STRUCTURE----
			 for( Collection<String> col :collectionOfCollectionOfEmails){
				   for(String s : col){
					   log.info("email SEQ ===" +s );  
				   }
			   }
			 
			// TESTING STRUCTURE GINISHES 
			 break;
		 default:
			 
			 break;	 
		 
		 }
		
		
		
		// composing the email and sending will be executed
		this.sendMail();
		
		return view;
	}
	
	
	private void sendMail(){
		log.info("SENDING EMAILS: ===="); 
	}
	
	private void createDatabaseConnection() throws SQLException{
		try{
			connection = ConnectionManager.getConnection();
		} catch (SQLException sqle) {
			log.error("add():SQLException :" +sqle.toString());
			throw sqle;			
		}
	}
	
	
}



