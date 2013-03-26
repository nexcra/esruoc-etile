package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Column;

/**
	 * 栏目映射器内部类，用于返回范型的栏目实体
	 * 
	 * @author Soyi Yao
	 */
	public class ColumnMapper implements RowMapper<Column> {

		public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
			Column column = new Column();
			
			column.setId(rs.getString("id".toLowerCase())); 
column.setParentId(rs.getString("parent_id".toLowerCase())); 
column.setName(rs.getString("name".toLowerCase())); 
column.setCode(rs.getString("code".toLowerCase())); 
column.setIsLeaf(rs.getString("is_leaf".toLowerCase())); 
column.setLevel(rs.getString("level".toLowerCase())); 
column.setFullPathId(rs.getString("full_path_id".toLowerCase())); 
column.setFullPathName(rs.getString("full_path_name".toLowerCase())); 
column.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
column.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
column.setCreateTime(rs.getString("create_time".toLowerCase())); 
column.setOrderNumber(rs.getString("order_number".toLowerCase())); 

			return column;
		}
	}
