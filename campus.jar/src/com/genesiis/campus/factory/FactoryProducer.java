package com.genesiis.campus.factory;

//20161025 DN c10-contacting-us create the initial version of FactoryProducer.java
//20170111 PN CAM-72 modified getFactory() method to return ExceptionCmdFactory object.
//20161130 PN c27-upload-user-image: removed return value for choice - "/PublicController".
//20161205 DJ  c51-report-courses-by-course-provider-MP-dj initiate the ReportController factory

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
		}if (choice.equalsIgnoreCase("/ExceptionHandlingController")) {
			return new ExceptionCmdFactory();
		} else if (choice.equalsIgnoreCase("/StudentController")) {
			return new StudentCmdFactory();
		}
		if (choice.equalsIgnoreCase("/ReportController")) {
			return new ReportCmdFactory();			
		} 
		return null;
	}
}
