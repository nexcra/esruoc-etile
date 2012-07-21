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

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 12:52:25
	 * @param user
	 */
	public int addUser(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_user ");
		sql.append(" ( name, password ) ");
		sql.append(" values ");
		sql.append(" ( ?, ? ) ");
		Object[] values = { user.getName(), user.getPassword() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };
		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:13:11
	 * @param ids
	 */
	public int deleteUserById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_user ");
		sql.append(" where id = ?  ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 22:56:00
	 * @param i
	 */
	public Map getUserById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id,t.name,t.password from t_user t where t.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	/**
	 * @author Po Kong
	 * @since 21 Jul 2012 23:25:24
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_user t");
		sql.append(" set t.name = ? , ");
		sql.append("     t.password = ?   ");
		sql.append(" where t.id = ? ");
		Object[] values = { user.getName(), user.getPassword(), user.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}
}
