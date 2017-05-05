package com.genesiis.campus.command;
/*
 * 20170505 DN CAM-89:Inital class stub has been created and the execute() with utility methods has been created
 *             add the doc comments for the class.
 */
import java.sql.SQLException;

import com.genesiis.campus.entity.AddAdvertiserPrerequisiteData;
import com.genesiis.campus.entity.EditAvertiserPrerequisiteData;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;



/**
 * The Class CmdEditingAdvertiserPrerequisiteData.<br>
 * Bears the responsibility of handling Editing command issued to<br>
 * the advertiser
 * @author dushantha DN
 */
public class CmdEditingAdvertiserPrerequisiteData implements ICommand {
	
	
	private static Logger log = Logger.getLogger(CmdEditingAdvertiserPrerequisiteData.class.getName());
	private EditAvertiserPrerequisiteData pagePrerequiaiteData = null;
	
	/* (non-Javadoc)
	 * @see com.genesiis.campus.command.ICommand#execute(com.genesiis.campus.util.IDataHelper, com.genesiis.campus.entity.IView)
	 */
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
	 * from the callers argument.
	 *
	 * @param pagePrerequiaiteData the new page prerequiaite data
	 */
	public void setPagePrerequiaiteData(
			EditAvertiserPrerequisiteData pagePrerequiaiteData) {
		this.pagePrerequiaiteData = pagePrerequiaiteData;
	}

}
