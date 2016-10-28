package com.genesiis.campus.entity;

//20161024 DN c10-contacting-us created the initial version. 

import java.io.Serializable;
import java.util.Collection;

public interface IView extends Serializable{

	public Collection<Collection<String>> getCollection();

	public void setCollection(Collection<Collection<String>> empCollection);

}