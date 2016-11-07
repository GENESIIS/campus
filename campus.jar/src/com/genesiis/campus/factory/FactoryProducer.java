package com.genesiis.campus.factory;

//20161026 JH c7-higher-education-landing-page change servlet path EmployeeController to PublicController

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
			return new PublicCmdFactory();

		} else if (choice.equalsIgnoreCase("/PayrollController")) {
			//return new PayrollCmdFactory();
		}

		return null;
	}
}