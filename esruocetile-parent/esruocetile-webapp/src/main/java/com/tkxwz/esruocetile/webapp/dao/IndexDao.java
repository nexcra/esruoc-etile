package com.tkxwz.esruocetile.webapp.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * @author 孔沛洪
 * @since 2012-5-10 下午4:48:35
 */
@Repository
public class IndexDao extends JdbcDaoSupport {

	public String index() {
		this.test();
		return "hello";
	}

	public void test() {
		String sql = "select * from t_user";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				sql);
		Iterator<Map<String, Object>> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().get("name"));
		}

	}
}
