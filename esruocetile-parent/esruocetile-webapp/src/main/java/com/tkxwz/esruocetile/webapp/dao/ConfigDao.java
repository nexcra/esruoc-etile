package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.webapp.entity.Config;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:12:25
 */
@Repository
public class ConfigDao extends BaseDao<Config> {

	public Page listConfig(Page page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.* ");
		sql.append(" FROM t_config a ");
		sql.append(" order by a.insert_time desc ");
		return this.queryForPage(sql.toString(), page);
	}

	public Map getConfigById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.*   ");
		sql.append(" FROM t_config a ");
		sql.append(" WHERE   ");
		sql.append(" a.id = ? ");
		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	
	public Map getConfigByCode(String code) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.*   ");
		sql.append(" FROM t_config a ");
		sql.append(" WHERE   ");
		sql.append(" a.code = ? ");
		Object[] values = { code };
		int[] valueTypes = { Types.VARCHAR };
		return this.queryForMap(sql.toString(), values, valueTypes);

	}

	public int updateConfig(Config config) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update t_config t ");
		sql.append(" set t.title = ? , ");
		sql.append("     content = ? , ");
		sql.append("     update_time  = now() ");
		sql.append(" where t.id = ? ");

		Object[] values = { config.getTitle(), config.getContent(),
				config.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return this.update(sql.toString(), values, valueTypes);
	}

}
