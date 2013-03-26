package com.tkxgz.elitecourse.dao;

import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.dao.base.UserBaseDao;

/**
 * 用户Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class UserDao extends UserBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @author Soyi Yao
	 * @param name
	 * @return
	 */
	public int getUserCountByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_user  t ");
		sql.append("where t.name = ?  ");
		Object[] values = { name };
		int[] valueTypes = { Types.VARCHAR };

		logger.info(sql.toString());

		return this.queryForInt(sql.toString(), values, valueTypes);
	}

	/**
	 * @author Soyi Yao
	 * @return
	 */
	public int getUserCountByNameAndPassword(String name, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_user  t ");
		sql.append("where t.name = ?  and t.password = ? ");
		sql.append(" and t.is_admin = 'student' ");
		Object[] values = { name, password };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.queryForInt(sql.toString(), values, valueTypes);
	}

}
