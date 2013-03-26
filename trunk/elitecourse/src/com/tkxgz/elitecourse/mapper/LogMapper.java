package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.Log;

/**
	 * 日志映射器内部类，用于返回范型的日志实体
	 * 
	 * @author Soyi Yao
	 */
	public class LogMapper implements RowMapper<Log> {

		public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
			Log log = new Log();
			
			log.setId(rs.getString("id".toLowerCase())); 
log.setLogType(rs.getString("log_type".toLowerCase())); 
log.setUserId(rs.getString("user_id".toLowerCase())); 
log.setUserName(rs.getString("user_name".toLowerCase())); 
log.setModule(rs.getString("module".toLowerCase())); 
log.setDescription(rs.getString("description".toLowerCase())); 
log.setIpAddress(rs.getString("ip_address".toLowerCase())); 
log.setCreateTime(rs.getString("create_time".toLowerCase())); 

			return log;
		}
	}
