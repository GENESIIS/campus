package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view created BannerICrud.java
//20170111 DJ c52-report-banner-statistics-MP-dj getAllPages(),getAllPageSlotByPageCode(),getBannerByPageSlotCode() method declaration.

import java.sql.SQLException;
import java.util.Collection;


/**The class  {@code BannerICrud} is a form of Interface class.
 * The Interface {@code BannerICrud} has precise control over banner related dao level manipulations. 
 *  @author dumani DJ   
 */
public interface BannerICrud extends ICrud {
	
	public Collection<Collection<String>> getAllPages() throws SQLException,Exception;
	
	public Collection<Collection<String>> getAllPageSlotByPageCode(int pageCode) throws SQLException,Exception;
	
	public Collection<Collection<String>> getBannerProviderByPageSlotCode(int pageSlotCode) throws SQLException,Exception;

}
