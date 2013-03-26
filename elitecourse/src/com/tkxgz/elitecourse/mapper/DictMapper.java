package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Dict;

/**
	 * 字典映射器内部类，用于返回范型的字典实体
	 * 
	 * @author Soyi Yao
	 */
	public class DictMapper implements RowMapper<Dict> {

		public Dict mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dict dict = new Dict();
			
			dict.setId(rs.getString("id".toLowerCase())); 
dict.setDictCode(rs.getString("dict_code".toLowerCase())); 
dict.setDictName(rs.getString("dict_name".toLowerCase())); 
dict.setDictDesc(rs.getString("dict_desc".toLowerCase())); 
dict.setDictValue(rs.getString("dict_value".toLowerCase())); 
dict.setStatus(rs.getString("status".toLowerCase())); 
dict.setIsApplicationLevel(rs.getString("is_application_level".toLowerCase())); 

			return dict;
		}
	}
