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
import java.util.Iterator;
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
			
			//final Collection<Collection<String>> tutorCollection = publicTutorDAO.getAll();
			Collection<Collection<String>> tutorCollection = new ArrayList<Collection<String>>();
			final ArrayList<Collection<String>> tutorArrayList = (ArrayList<Collection<String>>)  publicTutorDAO.getAll();
			
			if(tutorArrayList.size() == 0 ){
				message = SystemMessage.NODATA;
			}else{
							
				String code = null;
				String firstName = null;
				String middleName = null;
				String lastName = null;
				String town = null;
				String countryCode = null;
				String areaCode = null;
				String landNumber = null;
				String networkCode = null;
				String mobileNumber = null;
				String email = null;
				String townCode = null;
				String qualification = null;
				String levelName = null;
				
				// maps to store repeating category and major details
				Map categoryMap = new HashMap<String, ArrayList<String>>();
				Map majorMap = new HashMap<String, ArrayList<String>>();
				
				Iterator iterator = tutorArrayList.iterator();
				
				// remove repeating tutor basic details
				while(iterator.hasNext()){
					ArrayList<String> singleList = (ArrayList<String>) iterator.next();
					code = singleList.get(0);
					firstName = singleList.get(1);
					middleName = singleList.get(2);
					lastName = singleList.get(3);
					email = singleList.get(4);
					countryCode = singleList.get(5);
					areaCode = singleList.get(6);
					landNumber = singleList.get(7);
					networkCode = singleList.get(8);
					mobileNumber = singleList.get(9);
					town = singleList.get(18);
					townCode = singleList.get(19);
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
