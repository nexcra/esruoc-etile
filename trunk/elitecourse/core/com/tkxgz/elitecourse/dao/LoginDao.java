package com.tkxgz.elitecourse.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.dao.base.BaseDao;
import com.tkxgz.elitecourse.mapper.UserMapper;

/**
 * @author Po Kong
 * @since 22 Jul 2012 12:36:40
 */
@Repository
public class LoginDao extends BaseDao<User> {

	/**
	 * @author Po Kong
	 * @since 22 Jul 2012 12:46:04
	 * @param user
	 * @return
	 */
	public int getUserCountByNameAndPassword(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) ");
		sql.append(" from t_user t ");
		sql.append(" where t.name = ? and t.password = ? ");
		Object[] values = { user.getName(), user.getPassword() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };

		return this.queryForInt(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 22 Jul 2012 16:28:36
	 * @param user
	 * @return
	 */
	public Map getUserByNameAndPassword(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id, t.name, t.password ");
		sql.append(" from t_user t ");
		sql.append(" where t.name = ? and t.password = ? ");
		Object[] values = { user.getName(), user.getPassword() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 19:00:42
	 * @param name
	 * @param password
	 * @return
	 */
	public int getStudentCountByNameAndPassword(String name, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) ");
		sql.append(" from t_student t ");
		sql.append(" where t.student_no = ? and t.id_no = ? ");
		Object[] values = { name, password };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };

		return this.queryForInt(sql.toString(), values, valueTypes);
	}

	public int getStudentCountByIdNoAndPassword(String idNo, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) ");
		sql.append(" from t_student t ");
		sql.append(" where t.id_no = ? and t.password = ? ");
		sql.append(" and t.status not in('1','2') "); // 未报名的注册信息不给登录
		Object[] values = { idNo, password };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };

		return this.queryForInt(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Po Kong
	 * @since 29 Jul 2012 19:02:47
	 * @param name
	 * @param password
	 * @return
	 */
	public Map getUserByNameAndPassword(String name, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_student t ");
		sql.append(" where t.id_no = ? and t.password = ? ");
		Object[] values = { name, password };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Soyi Yao
	 * @param name
	 * @return
	 */
	public User findUserByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_user t where t.name = ? ");

		Object[] values = { name };
		int[] valueTypes = { Types.VARCHAR };

		User user = this.queryForObject(sql.toString(), values, valueTypes,
				new UserMapper());

		return user;
	}

}
