package com.genesiis.campus.factory;

//20161025 DN c10-contacting-us create the initial version of FactoryProducer.java
//20161130 PN c27-upload-user-image: removed return value for choice - "/PublicController".
//20170221 PN CAM-48: added return value for choice - '/AdminController'.

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
		} else if (choice.equalsIgnoreCase("/StudentController")) {
			return new StudentCmdFactory();
		}else if (choice.equalsIgnoreCase("/AdminController")) {
			return new AdminCmdFactory();
		}
		return null;
	}
}
