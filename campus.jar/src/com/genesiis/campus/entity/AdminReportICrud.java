package com.genesiis.campus.entity;

//20170107 DJ c6-list-available-institutes-on-the-view Initiated AdminReportICrud.java
//20170111 DJ c52-report-banner-statistics-MP-dj Declare getBannerStatisticReport().
//20170421 DJ c54-report-course-stats-MP-dj -Init: method getProgrammeStatsReport().
//20170425 DJ c54-report-course-stats-MP-dj changed the return type of getProgrammeStatsReport() to list of collection.
//20170427 DJ c54-report-course-stats-MP-dj organized imports..

import com.genesiis.campus.entity.model.BannerStatSearchDTO;
import com.genesiis.campus.entity.model.CourseStatSearchDTO;
import com.genesiis.campus.entity.model.StudentSearchDTO;
import com.genesiis.campus.entity.model.StudentSearchResultDTO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**The class  {@code AdminReportICrud} is a form of Interface class.
 * The Interface {@code AdminReportICrud} has precise control over admin report generation dao level manipulations. 
 *  @author dumani DJ   
 */
public interface AdminReportICrud extends ICrud{
	
	Collection<Collection<String>> getBannerStatisticReport(BannerStatSearchDTO searchDTO ) throws SQLException,Exception;
	
	List <StudentSearchResultDTO> getRegisteredStudentReport(StudentSearchDTO searchDTO ) throws SQLException,Exception;	
	
	Collection<Collection<String>> getProgrammeStatsReport(CourseStatSearchDTO searchDTO) throws SQLException,Exception;

}
