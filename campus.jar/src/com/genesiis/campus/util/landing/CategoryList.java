package com.genesiis.campus.util.landing;

//20170215 PN CAM-137: init CategoryList enum class to return categories associated with the landing page, if an error thrown when accessing the DB. 

import java.util.ArrayList;
import java.util.Collection;

public enum CategoryList {
	/**
	 * It's important to synchronize this enum with the DB values, because category details are using every where in the application as well as header section is loading from this.
	 * This approach is not 100% correct since all the data is static. But still we can go with this, other than returning an empty category collection.
	 */
	SchoolEducation("1","School Education","Education in Sri Lanka has a long history that dates back two millennia. The Constitution of Sri Lanka provides for education as a fundamental right. Sri Lanka's population had an adult literacy rate of 92.63% in 2015, which is above average by world and regional standards.[note 1] Education plays a major part in the life and culture of the country and dates back to 543 BC. Sri Lanka's modern educational system was brought about by its integration into the British Empire in the 19th century. Education currently falls under the control of both the Central Government and the Provincial Councils, with some responsibilities lying with the Central Government and the Provincial Council having autonomy for others."),
	HigherEducation("2","Higher Education","Higher Education Description"),
	CorporateTraining("3","Corporate Training","Corporate Training Education Description"),
	PreEducation("4","Pre Education","Pre Education Description");
	
	private final String code;
	private final String name;
	private final String description;
	
	private CategoryList(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	/**
	 * CategoryList emun values will convert into Collection<Collection<String>> and returns
	 * @return
	 */
	public static Collection<Collection<String>> getEnumAsCollection(){
		final Collection<Collection<String>> allCategoryList = new ArrayList<Collection<String>>();
		for(CategoryList list: CategoryList.values()){
			final ArrayList<String> singleCategoryList = new ArrayList<String>();
			singleCategoryList.add(list.getCode());
			singleCategoryList.add(list.getName());
			singleCategoryList.add(list.getDescription());
			allCategoryList.add(singleCategoryList);
		}		
		return allCategoryList;
	}
}
