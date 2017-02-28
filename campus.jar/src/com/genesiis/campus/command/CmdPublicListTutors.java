package com.genesiis.campus.command;

//20170228 JH c96-publicListTuors INIT CmdPublicListTutors.java

import com.genesiis.campus.entity.ICrud;
import com.genesiis.campus.entity.IView;
import com.genesiis.campus.entity.PublicTutorDAO;
import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;

/**
 * @author JH
 *
 */
public class CmdPublicListTutors implements ICommand{
	
	static Logger log = Logger.getLogger(CmdPublicListTutors.class.getName());

	public IView execute(IDataHelper helper, IView view) throws SQLException,
			Exception {
	
		final ICrud publicTutorDAO = new PublicTutorDAO();
		
		try{
			
			final Collection<Collection<String>> tutorCollection = publicTutorDAO.getAll();
			view.setCollection(tutorCollection);
			
			
		}catch(SQLException sqlException){
			log.error("execute() SQLException : "+ sqlException.toString());
			throw sqlException;
		}catch(Exception exception){
			log.error("execute() Exception : "+ exception.toString());
			throw exception;
		}finally{
			
		}
		
		
		return view;
	}

}
