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
import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.mapper.LogMapper;

/**
 * 日志Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class LogBaseDao extends BaseDao<Log> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询日志列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 日志分页数据
	 */
	public Page listLog(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_log t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加日志
	 * 
	 * @author Soyi Yao
	 * @param log
	 *        日志bean
	 * @return 影响行数
	 */
	public int addLog(Log log) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_log (");
		sql.append(" log_type , ");
		sql.append(" user_id , ");
		sql.append(" user_name , ");
		sql.append(" module , ");
		sql.append(" description , ");
		sql.append(" ip_address , ");
		sql.append(" create_time  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now()  ");

		sql.append(" ) ");
		Object[] values = { log.getLogType(), log.getUserId(),
				log.getUserName(), log.getModule(), log.getDescription(),
				log.getIpAddress() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据日志id删除日志
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        日志id
	 * @return 影响行数
	 */
	public int deleteLogById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_log ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据日志查询日志信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        日志id
	 * @return 日志Map
	 */
	public Map getLogById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_log t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新日志
	 * 
	 * @author Soyi Yao
	 * @param log
	 *        日志bean
	 * @return 影响行数
	 */
	public int updateLog(Log log) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_log t");
		sql.append(" set   ");
		sql.append(" t.log_type = ? , ");
		sql.append(" t.user_id = ? , ");
		sql.append(" t.user_name = ? , ");
		sql.append(" t.module = ? , ");
		sql.append(" t.description = ? , ");
		sql.append(" t.ip_address = ? , ");
		sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { log.getLogType(), log.getUserId(),
				log.getUserName(), log.getModule(), log.getDescription(),
				log.getIpAddress(), log.getCreateTime(), log.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索日志列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param log
	 *        日志bean
	 * @return 分页日志列表
	 */
	public Page searchLog(Page page, Log log) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_log t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(log.getLogType())
				&& !"all".equalsIgnoreCase(log.getLogType())) {
			sql.append("and t.log_type like '%" + log.getLogType() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getUserName())) {
			sql.append("and t.user_name like '%" + log.getUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getModule())) {
			sql.append("and t.module like '%" + log.getModule() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getDescription())) {
			sql.append("and t.description like '%" + log.getDescription()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(log.getIpAddress())) {
			sql.append("and t.ip_address like '%" + log.getIpAddress() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getCreateTime())) {
			sql.append("and t.create_time like '%" + log.getCreateTime() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索日志列表
	 * 
	 * @author Soyi Yao
	 * @param log
	 *        Log实体
	 * @return 日志列表
	 */
	public List<Log> searchLogForList(Log log) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_log t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(log.getLogType())
				&& !"all".equalsIgnoreCase(log.getLogType())) {
			sql.append("and t.log_type like '%" + log.getLogType() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getUserName())) {
			sql.append("and t.user_name like '%" + log.getUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getModule())) {
			sql.append("and t.module like '%" + log.getModule() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getDescription())) {
			sql.append("and t.description like '%" + log.getDescription()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(log.getIpAddress())) {
			sql.append("and t.ip_address like '%" + log.getIpAddress() + "%'");
		}
		if (StringUtils.isNotEmpty(log.getCreateTime())) {
			sql.append("and t.create_time like '%" + log.getCreateTime() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new LogMapper());
	}

	/**
	 * 根据日志id获取日志实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        日志id
	 * @return Log实体
	 */
	public Log findLogById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_log t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Log log = this.queryForObject(sql.toString(), values, valueTypes,
				new LogMapper());

		logger.info(sql.toString());

		return log;
	}

	/**
	 * 列出所有日志
	 * 
	 * @author Soyi Yao
	 * @return 日志列表
	 */
	public List<Log> listAllLog() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_log t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new LogMapper());
	}

}
