package com.tkxwz.esruocetile.webapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.dao.UserDao;
import com.tkxwz.esruocetile.webapp.entity.User;

/**
 * @author Po Kong
 * @since 2012-5-20 上午11:37:56
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserDetail(int id) {
		User user = new User();
		user.setId(id);
		Map<String, Object> map = this.userDao.queryForMap(user);

		user.setName((String) map.get("name"));

		return user;

	}

	public Page listUser(Page page) {
		return this.userDao.listStaff(page);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:51:56
	 * @param user
	 */
	public int addUser(User user) {
		return this.userDao.addUser(user);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:12:54
	 * @param ids
	 */
	public int deleteUserById(String id) {
		return this.userDao.deleteUserById(id);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:14:08
	 * @param ids
	 */
	public int batchDeleteUser(String[] ids) {
		int result = 0;
		for (String id : ids) {
			this.deleteUserById(id);
			result++;
		}
		return result;

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:55:43
	 * @param id
	 */
	public Map getUserById(String id) {
		return this.userDao.getUserById(id);

	}

	 /**
	 * 
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:01
	 * @param user
	 */
	public int updateUser(User user) {
		return this.userDao.updateUser(user);
		
	}
}
