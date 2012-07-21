package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.entity.User;

/**
 * @author Po Kong 
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
	
	public Page listStaff(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.name, t.password ");
		sql.append(" from t_user t ");
		return this.queryForPage(sql.toString(), page);
	}
}
