package com.genesiis.campus.entity;

//20161024 DN c10-contacting-us-page created the initial version of view.java

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Collection;

/**
 * View class will be use as a wrapper class.
 * 
 * @since 201601024
 * @version 1.0
 * 
 */
public class View implements IView, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(this.getClass());

	private Collection<Collection<String>> collection;
	
	@Override
	public Collection<Collection<String>> getCollection() {
		return collection;
	}

	@Override
	public void setCollection(Collection<Collection<String>> collection) {
		this.collection = collection;
	}

}