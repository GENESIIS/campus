package com.genesiis.campus.factory;

//20161025 DN c10-contacting-us create the initial version of FactoryProducer.java
//20161027 AS C8-inquiry-form-for-course added InstituteController to getFactory method 
import com.genesiis.campus.command.CmdGenerateEmail;
import org.apache.log4j.Logger;


/**
 * FactoryProducer class bares the responsibility of producing 
 * the Factory  
 * @author PN,AS,DN
 *
 */

public class FactoryProducer {
	
	static Logger log = Logger.getLogger(FactoryProducer.class.getName());
	/**
	 * getFactory method provides the IAbstractCmdFactory  
	 * @param String choice decides the command factory associated with the
	 * choice
	 * @return ICmdFactory
	 */
	public static ICmdFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("/PublicController")) {
			return new PublicCmdFactory();


		} else if (choice.equalsIgnoreCase("/PayrollController")) {
			//return new PayrollCmdFactory();
		}else if (choice.equalsIgnoreCase("/InstituteController")){
			
		}

		return null;
	}
}