package com.genesiis.campus.command;

//20170228 JH c96-public-list-all-tutors INIT CmdPublicListTutors.java
//20170306 JH c96-public-list-all-tutors get tutor profile image path using SystemConfig enum, show error messages
//20170308 JH c96-public-list-all-tutors remove repeating records of the tutor from the tutorCollection

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PublicTutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.validation.SystemConfig;
import com.genesiis.campus.validation.SystemMessage;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JH
 *
 */
public class CmdPublicListTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdPublicListTutors.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
	
		final ICrud publicTutorDAO = new PublicTutorDAO();
		SystemMessage message = SystemMessage.UNKNOWN;
		
		try{
			
			final Collection<Collection<String>> tutorCollection = publicTutorDAO.getAll();
			
			if(tutorCollection.size() == 0 ){
				message = SystemMessage.NODATA;
			}else{
				Map categoryMap = new HashMap<String, ArrayList<String>>();
				for(Collection<String> singleList : tutorCollection){
					
				}
			}
			view.setCollection(tutorCollection);
			
			
		}catch(SQLException sqlException){
			message = SystemMessage.ERROR;
			log.error("execute() SQLException : "+ sqlException.toString());
			throw sqlException;
		}catch(Exception exception){
			message = SystemMessage.ERROR;
			log.error("execute() Exception : "+ exception.toString());
			throw exception;
		}finally{
			final String tutorImagePath = SystemConfig.TUTOR_PROFILE_IMAGE_PATH.getValue1();
			helper.setAttribute("userMessage", message.toString());
			helper.setAttribute("tutorProfileImagePath", tutorImagePath);
		}
		
		
		return view;
	}

}
