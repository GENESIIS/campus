package com.genesiis.campus.command;
/*
 * 20170425 DN c88-admin-manage-advertiser-add-new-advertiser-dn. class CmdAdvertiserCredentialRetriever.java created.
 */
import com.genesiis.campus.entity.AddAdvertiserPrerequisiteData;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.util.IDataHelper;

import org.jboss.logging.Logger;



/**
 * The Class CmdRetrieveingAdvertiserCredential.
 */
public class CmdAdvertiserCredentialRetriever implements ICommand {

	private static Logger log = Logger.getLogger(CmdAdvertiserCredentialRetriever.class.getName());
	private AddAdvertiserPrerequisiteData pagePrerequisiteData = null;
	
	/** 
	 * @see com.genesiis.campus.command.ICommand#execute(com.genesiis.campus.util.IDataHelper, com.genesiis.campus.entity.IView)
	 * 
	 */
	@Override
	public IView execute(IDataHelper helper, IView view) throws Exception {
		try{
			
			this.setPagePrerequisiteData(new AddAdvertiserPrerequisiteData(helper));
			view.setCollection(pagePrerequisiteData
					.getCourseProviderboundToSelectedCode(pagePrerequisiteData
							.getHelper().getParameter("courseProvider")));
		} catch (Exception exp){
			log.error("execute : Exception: "+exp.toString());
			throw exp;
		}
				
		return view;
	}
	
	/**
	 *getPagePrerequiaiteData method returns the AddAdvertiserPrerequiaiteData <br>
	 *field value to the caller.
	 * @return AddAdvertiserPrerequiaiteData
	 */
	public AddAdvertiserPrerequisiteData getPagePrerequisiteData() {
		return pagePrerequisiteData;
	}
	
	/**
	 * setPagePrerequiaiteData sets the field value <br>
	 * from the callers argument
	 * @param pagePrerequiaiteData: AddAdvertiserPrerequiaiteData type
	 * which handles and provides a means to manage the prerequisite data for the <br>
	 * page where the advertiser is added.
	 */
	public void setPagePrerequisiteData(
			AddAdvertiserPrerequisiteData pagePrerequiaiteData) {
		this.pagePrerequisiteData = pagePrerequiaiteData;
	}
	
	
	

}
