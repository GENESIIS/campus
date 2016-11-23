package com.genesiis.campus.factory;

//20161123 JH c39-add-course-provider added conditions for AdminController servlet

/**
 * FactoryProducer class bares the responsibility of producing 
 * the Factory  
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

		} else if (choice.equalsIgnoreCase("/AdminController")) {
			return new AdminCmdFactory();
		}

		return null;
	}
}