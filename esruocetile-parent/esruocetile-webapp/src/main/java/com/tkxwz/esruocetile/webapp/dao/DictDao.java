package com.tkxwz.esruocetile.webapp.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author Po Kong 
 * @since 2012-7-5 下午9:19:15
 */
@Repository
public class DictDao extends BaseDao {

	public String getDictValueByDictName(String dictName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.dict_value from t_dict t ");
		sql.append(" where t.dict_name = ? ");
		Object[] values = { dictName };
		int[] valueTypes = { Types.VARCHAR };
		return (String) this.queryForMap(sql.toString(), values, valueTypes)
				.get("dict_value");
	}

	public List<Map<String, Object>> getDictValuesByDictName(String dictName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.dict_value from t_dict t ");
		sql.append(" where t.dict_name = ? ");
		Object[] values = { dictName };
		int[] valueTypes = { Types.VARCHAR };
		return this.queryForList(sql.toString(), values, valueTypes);
	}
}
