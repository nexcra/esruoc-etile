package com.tkxgz.elitecourse.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.ListUtil;
import com.tkxgz.elitecourse.bean.SystemConfig;
import com.tkxgz.elitecourse.mapper.SystemConfigMapper;
/**
 * 系统配置Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class SystemConfigBaseDao extends BaseDao<SystemConfig> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 系统配置分页数据
	 */
	public Page listSystemConfig(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_system_config t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加系统配置
	 * 
	 * @author Soyi Yao
	 * @param systemConfig
	 *        系统配置bean
	 * @return 影响行数
	 */
	public int addSystemConfig(SystemConfig systemConfig) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_system_config (");
		   sql.append(" site_name , "); 
  sql.append(" copyright , "); 
  sql.append(" site_owner , "); 
  sql.append(" contact_phone , "); 
  sql.append(" contact_email , "); 
  sql.append(" status  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { systemConfig.getSiteName(), 
systemConfig.getCopyright(), 
systemConfig.getSiteOwner(), 
systemConfig.getContactPhone(), 
systemConfig.getContactEmail(), 
systemConfig.getStatus()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据系统配置id删除系统配置
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        系统配置id
	 * @return 影响行数
	 */
	public int deleteSystemConfigById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_system_config ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据系统配置查询系统配置信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        系统配置id
	 * @return 系统配置Map
	 */
	public Map getSystemConfigById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_system_config t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新系统配置
	 * 
	 * @author Soyi Yao
	 * @param systemConfig
	 *        系统配置bean
	 * @return 影响行数
	 */
	public int updateSystemConfig(SystemConfig systemConfig) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_system_config t");
		sql.append(" set   ");
		 sql.append(" t.site_name = ? , "); 
  sql.append(" t.copyright = ? , "); 
  sql.append(" t.site_owner = ? , "); 
  sql.append(" t.contact_phone = ? , "); 
  sql.append(" t.contact_email = ? , "); 
  sql.append(" t.status = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { systemConfig.getSiteName(), 
systemConfig.getCopyright(), 
systemConfig.getSiteOwner(), 
systemConfig.getContactPhone(), 
systemConfig.getContactEmail(), 
systemConfig.getStatus(), 
 systemConfig.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param systemConfig
	 *        系统配置bean
	 * @return 分页系统配置列表
	 */
	public Page searchSystemConfig(Page page, SystemConfig systemConfig) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_system_config t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(systemConfig.getSiteName())) {sql.append( "and t.site_name like '%"+systemConfig.getSiteName()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getCopyright())) {sql.append( "and t.copyright like '%"+systemConfig.getCopyright()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getSiteOwner())) {sql.append( "and t.site_owner like '%"+systemConfig.getSiteOwner()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getContactPhone())) {sql.append( "and t.contact_phone like '%"+systemConfig.getContactPhone()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getContactEmail())) {sql.append( "and t.contact_email like '%"+systemConfig.getContactEmail()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getStatus())) {sql.append( "and t.status like '%"+systemConfig.getStatus()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param systemConfig
	 *        SystemConfig实体
	 * @return 系统配置列表
	 */
	public List<SystemConfig> searchSystemConfigForList(SystemConfig systemConfig) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_system_config t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(systemConfig.getSiteName())) {sql.append( "and t.site_name like '%"+systemConfig.getSiteName()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getCopyright())) {sql.append( "and t.copyright like '%"+systemConfig.getCopyright()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getSiteOwner())) {sql.append( "and t.site_owner like '%"+systemConfig.getSiteOwner()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getContactPhone())) {sql.append( "and t.contact_phone like '%"+systemConfig.getContactPhone()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getContactEmail())) {sql.append( "and t.contact_email like '%"+systemConfig.getContactEmail()+"%'");}if (StringUtils.isNotEmpty(systemConfig.getStatus())) {sql.append( "and t.status like '%"+systemConfig.getStatus()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new SystemConfigMapper());
	}
	
	/**
	 * 根据系统配置id获取系统配置实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        系统配置id
	 * @return SystemConfig实体
	 */
	public SystemConfig findSystemConfigById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_system_config t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		SystemConfig systemConfig = this.queryForObject(sql.toString(), values, valueTypes,
				new SystemConfigMapper());
		
		logger.info(sql.toString());

		return systemConfig;
	}


	/**
	 * 列出所有系统配置
	 * 
	 * @author Soyi Yao
	 * @return 系统配置列表
	 */
	public List<SystemConfig> listAllSystemConfig() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_system_config t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new SystemConfigMapper());
	}
	
    /**
	 * 获取t_system_config表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_system_config表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_system_config  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
