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
import com.tkxgz.elitecourse.bean.DataBackup;
import com.tkxgz.elitecourse.mapper.DataBackupMapper;
/**
 * 数据备份Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class DataBackupBaseDao extends BaseDao<DataBackup> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 数据备份分页数据
	 */
	public Page listDataBackup(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_data_backup t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加数据备份
	 * 
	 * @author Soyi Yao
	 * @param dataBackup
	 *        数据备份bean
	 * @return 影响行数
	 */
	public int addDataBackup(DataBackup dataBackup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_data_backup (");
		   sql.append(" name , "); 
  sql.append(" path , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_user_name , "); 
  sql.append(" create_time  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now()  ");

		sql.append(" ) ");
		Object[] values = { dataBackup.getName(), 
dataBackup.getPath(), 
dataBackup.getCreateUserId(), 
dataBackup.getCreateUserName()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据数据备份id删除数据备份
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        数据备份id
	 * @return 影响行数
	 */
	public int deleteDataBackupById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_data_backup ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据数据备份查询数据备份信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        数据备份id
	 * @return 数据备份Map
	 */
	public Map getDataBackupById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_data_backup t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新数据备份
	 * 
	 * @author Soyi Yao
	 * @param dataBackup
	 *        数据备份bean
	 * @return 影响行数
	 */
	public int updateDataBackup(DataBackup dataBackup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_data_backup t");
		sql.append(" set   ");
		 sql.append(" t.name = ? , "); 
  sql.append(" t.path = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_user_name = ? , "); 
  sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { dataBackup.getName(), 
dataBackup.getPath(), 
dataBackup.getCreateUserId(), 
dataBackup.getCreateUserName(), 
dataBackup.getCreateTime(), 
 dataBackup.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param dataBackup
	 *        数据备份bean
	 * @return 分页数据备份列表
	 */
	public Page searchDataBackup(Page page, DataBackup dataBackup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_data_backup t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(dataBackup.getName())) {sql.append( "and t.name like '%"+dataBackup.getName()+"%'");}if (StringUtils.isNotEmpty(dataBackup.getPath())) {sql.append( "and t.path like '%"+dataBackup.getPath()+"%'");}if (StringUtils.isNotEmpty(dataBackup.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+dataBackup.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(dataBackup.getCreateTime())) {sql.append( "and t.create_time like '%"+dataBackup.getCreateTime()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param dataBackup
	 *        DataBackup实体
	 * @return 数据备份列表
	 */
	public List<DataBackup> searchDataBackupForList(DataBackup dataBackup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_data_backup t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(dataBackup.getName())) {sql.append( "and t.name like '%"+dataBackup.getName()+"%'");}if (StringUtils.isNotEmpty(dataBackup.getPath())) {sql.append( "and t.path like '%"+dataBackup.getPath()+"%'");}if (StringUtils.isNotEmpty(dataBackup.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+dataBackup.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(dataBackup.getCreateTime())) {sql.append( "and t.create_time like '%"+dataBackup.getCreateTime()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new DataBackupMapper());
	}
	
	/**
	 * 根据数据备份id获取数据备份实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        数据备份id
	 * @return DataBackup实体
	 */
	public DataBackup findDataBackupById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_data_backup t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		DataBackup dataBackup = this.queryForObject(sql.toString(), values, valueTypes,
				new DataBackupMapper());
		
		logger.info(sql.toString());

		return dataBackup;
	}


	/**
	 * 列出所有数据备份
	 * 
	 * @author Soyi Yao
	 * @return 数据备份列表
	 */
	public List<DataBackup> listAllDataBackup() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_data_backup t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new DataBackupMapper());
	}
	
    /**
	 * 获取t_data_backup表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_data_backup表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_data_backup  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	/**
	 * 获取t_data_backup当前最大id值
	 * 
	 * @author Soyi Yao
	 * @return
	 */
	public int getCurrentMaxId() {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(id) from  t_data_backup  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}
	
	
}
