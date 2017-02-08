package com.genesiis.campus.factory;

//20161025 DN c10-contacting-us create the initial version of FactoryProducer.java
//20170102 DN CAM: 47 added TutorController if else branch
//20161130 PN c27-upload-user-image: removed return value for choice - "/PublicController".
//20170208 DN c131-admin-manage-banner-upload-banner-image-dn  added /AdminController in getFactory().

import com.genesiis.campus.command.CmdGenerateEmail;
import org.apache.log4j.Logger;


/**
 * FactoryProducer class bares the responsibility of producing 
 * the Factory  
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
		}  else if (choice.equalsIgnoreCase("/TutorController")) {
			return new TutorCmdFactory();
		} else if (choice.equalsIgnoreCase("/StudentController")) {
			return new StudentCmdFactory();
		} else if (choice.equalsIgnoreCase("/AdminController")) {
			return new AdminCmdFactory();
		}
		return null;
	}
}
