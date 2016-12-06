package com.genesiis.campus.command;

//DJ 20161206 c52-report-banner-statistics-MP-dj created CmdReportBannerStatistics.java

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.genesiis.campus.entity.BannerDAO;
import com.genesiis.campus.entity.CourseProviderDAO;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

public class CmdReportBannerStatistics implements ICommand {

	static Logger log = Logger.getLogger(CmdReportBannerStatistics.class.getName());

	/**
	 * @author DJ
	 * @param helper
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@Override
	public IView execute(IDataHelper helper, IView iView) throws SQLException,
			Exception {
		final Collection<Collection<String>> bannerDetails = new ArrayList<Collection<String>>();
		SystemMessage systemMessage = SystemMessage.UNKNOWN;
		try {
			bannerDetails = new BannerDAO().getAll();
			iView.setCollection(bannerDetails);

		} catch (SQLException sql) {
			log.error("execute() : Exception " + sql);
			systemMessage = SystemMessage.ERROR;
			throw sql;
		} catch (Exception exception) {
			log.error("execute() : Exception " + exception);
			systemMessage = SystemMessage.ERROR;
			throw exception;
		}
		return iView;
	}

}
