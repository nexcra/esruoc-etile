package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.DataBackup;

/**
	 * 数据备份映射器内部类，用于返回范型的数据备份实体
	 * 
	 * @author Soyi Yao
	 */
	public class DataBackupMapper implements RowMapper<DataBackup> {

		public DataBackup mapRow(ResultSet rs, int rowNum) throws SQLException {
			DataBackup dataBackup = new DataBackup();
			
			dataBackup.setId(rs.getString("id".toLowerCase())); 
dataBackup.setName(rs.getString("name".toLowerCase())); 
dataBackup.setPath(rs.getString("path".toLowerCase())); 
dataBackup.setCreateUserId(rs.getString("create_user_id".toLowerCase())); 
dataBackup.setCreateUserName(rs.getString("create_user_name".toLowerCase())); 
dataBackup.setCreateTime(rs.getString("create_time".toLowerCase())); 

			return dataBackup;
		}
	}
