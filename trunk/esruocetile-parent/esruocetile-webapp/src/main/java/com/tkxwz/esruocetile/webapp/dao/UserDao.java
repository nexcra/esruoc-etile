package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.webapp.entity.User;

/**
 * @author 孔沛洪
 * @since 2012-5-20 上午11:21:53
 */
@Repository
public class UserDao extends BaseDao<User> {

	public Map<String, Object> queryForMap(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_user t where t.id = ? ");
		Object[] values = { user.getId() };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);
	}
}
