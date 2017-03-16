package com.genesiis.campus.entity;

//20170314 CW c148-tutor-verify-hashcode-reset-password-cw SigningUpTutorDAO class created.
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw query error fixed
//20170316 CW c149-tutor-email-confirmation-for-password-change-cw add doc comments & modified method comment

import com.genesiis.campus.command.CmdTutorPasswordChange;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.ConnectionManager;
import com.genesiis.campus.util.DaoHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * This class is used to manage signing up details of the tutor
 * @author chinthaka
 *
 */
public class SigningUpTutorDAO implements ICrud  {
	static Logger log = Logger.getLogger(SigningUpTutorDAO.class.getName());

	/**
	 * forget password function, update new password.
	 * @author Chinthaka
	 */
	@Override
	public int update(Object object) throws SQLException, Exception {
		Connection conn = null;
		String query = "UPDATE CAMPUS.TUTOR SET PASSWORD = ? WHERE CODE=? ";
		PreparedStatement ps = null;
		int rowInserted = -1;

		try {
			Tutor tutor = (Tutor) object;
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, tutor.getPassword());
			ps.setInt(2, tutor.getCode());

			rowInserted = ps.executeUpdate();

			if (rowInserted > 0) {
				rowInserted = 1;
			} else {
				rowInserted = 0;
			}
		} catch (SQLException e) {
			log.info("update(Object object): SQLexception" + e.toString());
			throw e;
		} catch (Exception ex) {
			log.info("update(Object object): Exception" + ex.toString());
			throw ex;
		} finally {
			DaoHelper.cleanup(conn, ps, null);
		}
		return rowInserted;
	}

	@Override
	public int add(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Collection<String>> findById(Object code) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Collection<String>> getAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object object, Connection conn) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
