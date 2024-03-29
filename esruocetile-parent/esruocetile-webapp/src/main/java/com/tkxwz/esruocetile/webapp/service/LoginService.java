package com.tkxwz.esruocetile.webapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.webapp.dao.LoginDao;
import com.tkxwz.esruocetile.webapp.entity.User;

/**
 * @author Po Kong
 * @since 22 Jul 2012 12:37:08
 */
@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	/**
	 * @author Po Kong
	 * @since 22 Jul 2012 12:45:20
	 * @param user
	 * @return
	 */
	public int getUserCountByNameAndPassword(User user) {
		return this.loginDao.getUserCountByNameAndPassword(user);

	}

	public boolean isUserExist(int count) {

		boolean result = true;
		if (count == 0) {
			result = false;
		}
		return result;
	}

	/**
	 * @author Po Kong
	 * @since 22 Jul 2012 16:27:53
	 * @param user
	 * @return
	 */
	public Map getUserByUserAndPassword(User user) {

		return this.loginDao.getUserByNameAndPassword(user);
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 19:00:24
	 * @param name
	 * @param password
	 * @return
	 */
	public int getStudentCountByNameAndPassword(String name, String password) {
		return this.loginDao.getStudentCountByNameAndPassword(name, password);
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 19:02:29
	 * @param name
	 * @param password
	 * @return
	 */
	public Map getStudentByNameAndPassword(String name, String password) {
		return this.loginDao.getUserByNameAndPassword(name, password);
	}

}
