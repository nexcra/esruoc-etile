package com.tkxwz.esruocetile.webapp.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author 孔沛洪
 * @since 2012-5-20 上午11:23:32
 */
public class BaseDao<T> {

	 static  Logger logger = LoggerFactory.getLogger(BaseDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected Map<String, Object> queryForMap(String sql, Object[] values,
			int[] valueTypes) {
		logger.debug("执行" + this.getClass().getName() + "类中的queryForMap方法");

		return this.jdbcTemplate.queryForMap(sql, values, valueTypes);
	}
	
	public static void main(String[] args) {
		logger.debug("执行" + "类中的queryForMap方法");
	}

}
