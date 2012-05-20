package com.tkxwz.esruocetile.webapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.webapp.dao.UserDao;
import com.tkxwz.esruocetile.webapp.entity.User;

/**
 * @author 孔沛洪
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
}
