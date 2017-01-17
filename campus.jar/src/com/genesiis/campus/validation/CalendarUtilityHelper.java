package com.genesiis.campus.validation;

//DJ 20161219 c51-report-courses-by-course-provider-MP-dj created CalendarUtilityHelper.java

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class {@code CalendarUtilityHelper} is a form of utility helper class for calendar function implementation.
 * @author dumani DJ
 *
*/
public class CalendarUtilityHelper {
	public static java.sql.Date getSqlDate(String dateString) throws Exception {
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final Date utilDate = df.parse((dateString));
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
}
