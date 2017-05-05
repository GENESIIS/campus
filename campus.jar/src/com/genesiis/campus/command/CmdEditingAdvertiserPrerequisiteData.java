package com.genesiis.campus.command;

import java.sql.SQLException;

import com.genesiis.campus.entity.AddAdvertiserPrerequisiteData;
import com.genesiis.campus.entity.EditAvertiserPrerequisiteData;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

public class CmdEditingAdvertiserPrerequisiteData implements ICommand {
	private static Logger log = Logger.getLogger(CmdEditingAdvertiserPrerequisiteData.class.getName());
	private EditAvertiserPrerequisiteData pagePrerequiaiteData = null;
	
	@Override
	public IView execute(IDataHelper helper, IView view) throws	Exception {
		try{
			
			setPagePrerequiaiteData(new EditAvertiserPrerequisiteData(helper));
			view.setCollection(pagePrerequiaiteData.getEditAdvertiserPageOnLoadData());	
			
		} catch(Exception exp) {
			log.error("execute: Exception "+exp.toString());
			throw exp;
		}		
		return view;
	}
	
	/**
	 *getPagePrerequiaiteData method returns the AddAdvertiserPrerequiaiteData <br>
	 *field value to the caller.
	 * @return EditvertiserPrerequisiteData
	 */
	public EditAvertiserPrerequisiteData getPagePrerequiaiteData() {
		return pagePrerequiaiteData;
	}
	
	/**
	 * setPagePrerequiaiteData sets the field value <br>
	 * from the callers argument
	 * @param pagePrerequiaiteData: EditvertiserPrerequisiteData type
	 * which handles and provides a means to manage the prerequisite data for the <br>
	 * page where the advertiser is added.
	 */
	public void setPagePrerequiaiteData(
			EditAvertiserPrerequisiteData pagePrerequiaiteData) {
		this.pagePrerequiaiteData = pagePrerequiaiteData;
	}

}
