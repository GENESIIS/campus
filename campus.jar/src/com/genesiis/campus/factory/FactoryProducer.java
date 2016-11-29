package com.genesiis.campus.factory;


//20161025 CM c13-Display course details INIT FactoryProducer.java
//20161025 CM c13-Display course details Created getFactory() method

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
		} else{
		}

		return null;
	}
}