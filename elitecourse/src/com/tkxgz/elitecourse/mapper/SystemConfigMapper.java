package com.tkxgz.elitecourse.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.tkxgz.elitecourse.bean.SystemConfig;

/**
	 * 系统配置映射器内部类，用于返回范型的系统配置实体
	 * 
	 * @author Soyi Yao
	 */
	public class SystemConfigMapper implements RowMapper<SystemConfig> {

		public SystemConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
			SystemConfig systemConfig = new SystemConfig();
			
			systemConfig.setId(rs.getString("id".toLowerCase())); 
systemConfig.setSiteName(rs.getString("site_name".toLowerCase())); 
systemConfig.setCopyright(rs.getString("copyright".toLowerCase())); 
systemConfig.setSiteOwner(rs.getString("site_owner".toLowerCase())); 
systemConfig.setContactPhone(rs.getString("contact_phone".toLowerCase())); 
systemConfig.setContactEmail(rs.getString("contact_email".toLowerCase())); 
systemConfig.setStatus(rs.getString("status".toLowerCase())); 

			return systemConfig;
		}
	}
