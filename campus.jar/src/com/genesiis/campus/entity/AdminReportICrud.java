package com.genesiis.campus.entity;

//20170107 c6-list-available-institutes-on-the-view Initiated AdminReportICrud.java
//20170111 c52-report-banner-statistics-MP-dj Declare getBannerStatisticReport().

import com.genesiis.campus.entity.model.BannerStatSearchDTO;

import java.sql.SQLException;
import java.util.Collection;

/**The class  {@code AdminReportICrud} is a form of Interface class.
 * The Interface {@code AdminReportICrud} has precise control over admin report generation dao level manipulations. 
 *  @author dumani DJ   
 */
public interface AdminReportICrud extends ICrud{
	
	Collection<Collection<String>> getBannerStatisticReport(BannerStatSearchDTO searchDTO ) throws SQLException,Exception;

}
