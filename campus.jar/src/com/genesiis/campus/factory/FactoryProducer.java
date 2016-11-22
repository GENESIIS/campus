package com.genesiis.campus.factory;

//20161121 MM c25-student-login-create-dashboard-MP Added else if clause for StudentController

/**
 * FactoryProducer class bares the responsibility of producing 
 * the Factory  
 * @author PN,AS,DN
 *
 */

public class FactoryProducer {
	/**
	 * getFactory method provides the IAbstractCmdFactory  
	 * @param String choice decides the command factory associated with the
	 * choice
	 * @return ICmdFactory
	 */
	public static ICmdFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("/PublicController")) {
//			return new PublicCmdFactory();
		} else if (choice.equalsIgnoreCase("/StudentController")) {
			return new StudentCmdFactory();
		}

		return null;
	}
}