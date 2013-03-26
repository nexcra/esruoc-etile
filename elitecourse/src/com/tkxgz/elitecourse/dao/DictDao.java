package com.tkxgz.elitecourse.dao;

import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.bean.Dict;
import com.tkxgz.elitecourse.dao.base.DictBaseDao;
import com.tkxgz.elitecourse.mapper.DictMapper;

/**
 * 字典Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class DictDao extends DictBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @author Soyi Yao
	 * @param code
	 * @return
	 */
	public Dict getDictByCode(String code) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_dict t where t.dict_code= ? ");

		Object[] values = { code };
		int[] valueTypes = { Types.VARCHAR };

		Dict dict = this.queryForObject(sql.toString(), values, valueTypes,
				new DictMapper());

		logger.info(sql.toString());

		return dict;
	}

}
