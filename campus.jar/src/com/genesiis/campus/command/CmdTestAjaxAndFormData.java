package com.genesiis.campus.command;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

public class CmdTestAjaxAndFormData implements ICommand{
	static Logger log = Logger.getLogger(CmdTestAjaxAndFormData.class.getName());
	@Override
	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
		
		log.info("Hit the Command class ");
		
		return null;
	}

}
