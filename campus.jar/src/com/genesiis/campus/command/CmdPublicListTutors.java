package com.genesiis.campus.command;

//20170228 JH c96-public-list-all-tutors INIT CmdPublicListTutors.java
//20170306 JH c96-public-list-all-tutors get tutor profile image path using SystemConfig enum, show error messages
//20170308 JH c96-public-list-all-tutors remove repeating records of the tutor from the tutorCollection 
//20170309 JH c96-public-list-all-tutors created seperateBasicData(Collection<Collection<String>>) to separate tutor basic details, category, major and qualification 
//				details from the initial DAO class result wip, removed commented lines
//20170310 JH c96-public-list-all-tutors separate tutor category details from the tutor basic data wip
//20170312 JH c96-public-list-all-tutors removed unwanted comments and remove repeating details from the tutor basic data wip

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

		try {

			/*
			 * newTutorCollectio - does not contain tutor repeating records,
			 * contains only the basic details tutorArrayList - contains tutor
			 * repeating records with all details
			 */
			Collection<Collection<String>> newTutorCollection = new ArrayList<Collection<String>>();

			final Collection<Collection<String>> tutorCollection = publicTutorDAO
					.getAll();

			if (tutorCollection.size() == 0) {
				message = SystemMessage.NODATA;
			} else {
				//newTutorCollection = seperateBasicData(tutorCollection);
				Map returnData = new HashMap();
				returnData = seperateBasicData(tutorCollection);
				newTutorCollection = (Collection<Collection<String>>) returnData.get("tutorCollection");
				
			}
			view.setCollection(newTutorCollection);

		} catch (SQLException sqlException) {
			message = SystemMessage.ERROR;
			log.error("execute() SQLException : " + sqlException.toString());
			throw sqlException;
		} catch (Exception exception){
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

	
	/**
	 * seperateBasicData() used to remove repeating records and of the tutor while
	 * @return collection of tutor basic data 
	 * @param tutorCollection type of Collection<Collection<String>> 
	 * @author JH
	 */
	public static Map seperateBasicData(Collection<Collection<String>> tutorCollection){
		
		ArrayList<ArrayList<String>> newTutorList = new ArrayList<ArrayList<String>>();
		final ArrayList<Collection<String>> tutorArrayList = (ArrayList<Collection<String>>) tutorCollection;
		
				
			String code = "0";
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
			String categoryCode = null;
			String categoryName = null;
			String majorCode = null;
			String majorName = null;
			String nvq = null;

			// maps to store repeating category and major details
			Map categoryMap = new HashMap<String, ArrayList<ArrayList<String>>>();
			Map majorMap = new HashMap<String, ArrayList<ArrayList<String>>>();
			
			
			ArrayList<ArrayList<String>> categoryList =  null;
			ArrayList<ArrayList<String>> majorList = null;
			
			Iterator resultIterator = tutorArrayList.iterator();
			Iterator newTuorsIterator = newTutorList.iterator();
			
			//category list
			ArrayList<ArrayList<String>> x = null;
			
			// remove repeating tutor basic details
			while(resultIterator.hasNext()){
				
				ArrayList<String> singleList = (ArrayList<String>) resultIterator.next();
				
				if(code.equalsIgnoreCase(singleList.get(0))){
					
				}else{
					if(!code.equalsIgnoreCase("0")){// to exclude the first iteration
						categoryMap.put(code, categoryList);
						categoryList = null;
					}
				}
				
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
				
				// create a temporary tutor record 
				ArrayList<String> temporaryTutor = new ArrayList<String>();
				temporaryTutor.add(code);
				temporaryTutor.add(firstName);
				temporaryTutor.add(middleName);
				temporaryTutor.add(lastName);
				temporaryTutor.add(email);
				temporaryTutor.add(countryCode);
				temporaryTutor.add(areaCode);
				temporaryTutor.add(landNumber);
				temporaryTutor.add(networkCode);
				temporaryTutor.add(mobileNumber);
				temporaryTutor.add(town);
				temporaryTutor.add(townCode);
				
				
				// create a temporary category record
				ArrayList<String> temporaryCategory = new ArrayList<String>();
				temporaryCategory.add(singleList.get(12));
				temporaryCategory.add(singleList.get(13));

//				
//				ArrayList<String> temporaryMajor  = new ArrayList<String>();
//				temporaryMajor.add(singleList.get(10));
//				temporaryMajor.add(singleList.get(11));
				
				
				// if previous records are available: check whether the last
				// record matches the current temporary tutor record
				
				int count = newTutorList.size();	
				int index = 0;
						
				if(count != 0){
					index = count - 1;//get the last index of the collection					
				}else{
					index = count;
				}
	
				
				if (count> 0) {

					/*
					 *  The new temporary arrayList record need to compare against 
					 *  the newTutorCollection records before adding it. 
					 *  This is used to remove the repeating records of the 
					 *  tutor collection which is originally by the DAO class.
					 *  
					 *  The collection result set which is returned by the PublicTutorDAO class getAll() method 
					 *  is already ordered by the Tutor CODE. Therefore it is assumed that only
					 *  the last record of the tutor basic details collection is able to have a duplicate record.  
					 */
					
					ArrayList<String> compareArray = (ArrayList<String>) newTutorList.get(index);
					
					if (compareArray.equals(temporaryTutor)) {
						// the same tutor record is available, do nothing
						log.info(">>>>>..................already exist " + temporaryTutor.toString());	
						if(categoryList != null){
							categoryList.add(temporaryCategory);
						}
						
					} else {
						// the tutor record does not available, insert the temporary tutor record
						log.info(">>>>>..................tutor doesn't exist " + temporaryTutor.toString());
						newTutorList.add(temporaryTutor);
						
					categoryList = new ArrayList<ArrayList<String>>();
					categoryList.add(temporaryCategory);
					}
					

				} else {// no previous tutor records are available
					log.info(">>>>>..................first tutor record " + temporaryTutor.toString());
					newTutorList.add(temporaryTutor);
					
					categoryList = new 	ArrayList<ArrayList<String>>();
					categoryList.add(temporaryCategory);
				}

			}
			
			
//			while(newTuorsIterator.hasNext()){
//				ArrayList<String> singleList = (ArrayList<String>) newTuorsIterator.next();
//				
//				String tutorCode = singleList.get(0);
//				
//				categoryList = new ArrayList<ArrayList<String>>();
//				
//				while(resultIterator.hasNext()){
//					
//				}
//				
//			}
			if(!categoryMap.isEmpty()){
				log.info("cateogry map " + categoryMap.toString());
			}
			
			Map returData = new HashMap();
			returData.put("tutorCollection", newTutorList);
			returData.put("categoryData", categoryMap);
			returData.put("majaorData", majorMap);
		return returData;
	}
}

