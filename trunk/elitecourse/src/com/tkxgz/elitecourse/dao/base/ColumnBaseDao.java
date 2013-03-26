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
import com.tkxgz.elitecourse.bean.Column;
import com.tkxgz.elitecourse.mapper.ColumnMapper;

/**
 * 栏目Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class ColumnBaseDao extends BaseDao<Column> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询栏目列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 栏目分页数据
	 */
	public Page listColumn(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_column t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加栏目
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        栏目bean
	 * @return 影响行数
	 */
	public int addColumn(Column column) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_column (");
		sql.append(" parent_id , ");
		sql.append(" name , ");
		sql.append(" code , ");
		sql.append(" is_leaf , ");
		sql.append(" level , ");
		sql.append(" full_path_id , ");
		sql.append(" full_path_name , ");
		sql.append(" create_user_id , ");
		sql.append(" create_user_name , ");
		sql.append(" create_time , ");
		sql.append(" order_number  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now() , ");
		sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { column.getParentId(), column.getName(),
				column.getCode(), column.getIsLeaf(), column.getLevel(),
				column.getFullPathId(), column.getFullPathName(),
				column.getCreateUserId(), column.getCreateUserName(),
				column.getOrderNumber() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据栏目id删除栏目
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        栏目id
	 * @return 影响行数
	 */
	public int deleteColumnById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_column ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据栏目查询栏目信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        栏目id
	 * @return 栏目Map
	 */
	public Map getColumnById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_column t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新栏目
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        栏目bean
	 * @return 影响行数
	 */
	public int updateColumn(Column column) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_column t");
		sql.append(" set   ");
		sql.append(" t.parent_id = ? , ");
		sql.append(" t.name = ? , ");
		sql.append(" t.code = ? , ");
		sql.append(" t.is_leaf = ? , ");
		sql.append(" t.level = ? , ");
		sql.append(" t.full_path_id = ? , ");
		sql.append(" t.full_path_name = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_time = ? , ");
		sql.append(" t.order_number = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { column.getParentId(), column.getName(),
				column.getCode(), column.getIsLeaf(), column.getLevel(),
				column.getFullPathId(), column.getFullPathName(),
				column.getCreateUserId(), column.getCreateUserName(),
				column.getCreateTime(), column.getOrderNumber(), column.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索栏目列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param column
	 *        栏目bean
	 * @return 分页栏目列表
	 */
	public Page searchColumn(Page page, Column column) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_column t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(String.valueOf(column.getParentId()))
				&& (!"all".equalsIgnoreCase(column.getParentId()))) {
			sql.append(" and t.parent_id = ? ");
			valuesList.add(Integer.valueOf(column.getParentId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(column.getName())) {
			sql.append("and t.name like '%" + column.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getCode())) {
			sql.append("and t.code like '%" + column.getCode() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getIsLeaf())) {
			sql.append("and t.is_leaf like '%" + column.getIsLeaf() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(column.getLevel()))
				&& (!"all".equalsIgnoreCase(column.getLevel()))) {
			sql.append(" and t.level = ? ");
			valuesList.add(Integer.valueOf(column.getLevel()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(column.getFullPathId())) {
			sql.append("and t.full_path_id like '%" + column.getFullPathId()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(column.getFullPathName())) {
			sql.append("and t.full_path_name like '%"
					+ column.getFullPathName() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ column.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getCreateTime())) {
			sql.append("and t.create_time like '%" + column.getCreateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(column.getOrderNumber()))
				&& (!"all".equalsIgnoreCase(column.getOrderNumber()))) {
			sql.append(" and t.order_number = ? ");
			valuesList.add(Integer.valueOf(column.getOrderNumber()));
			valueTypesList.add(Types.INTEGER);
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索栏目列表
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        Column实体
	 * @return 栏目列表
	 */
	public List<Column> searchColumnForList(Column column) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_column t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(String.valueOf(column.getParentId()))
				&& (!"all".equalsIgnoreCase(column.getParentId()))) {
			sql.append(" and t.parent_id = ? ");
			valuesList.add(Integer.valueOf(column.getParentId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(column.getName())) {
			sql.append("and t.name like '%" + column.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getCode())) {
			sql.append("and t.code like '%" + column.getCode() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getIsLeaf())) {
			sql.append("and t.is_leaf like '%" + column.getIsLeaf() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(column.getLevel()))
				&& (!"all".equalsIgnoreCase(column.getLevel()))) {
			sql.append(" and t.level = ? ");
			valuesList.add(Integer.valueOf(column.getLevel()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(column.getFullPathId())) {
			sql.append("and t.full_path_id like '%" + column.getFullPathId()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(column.getFullPathName())) {
			sql.append("and t.full_path_name like '%"
					+ column.getFullPathName() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ column.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(column.getCreateTime())) {
			sql.append("and t.create_time like '%" + column.getCreateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(column.getOrderNumber()))
				&& (!"all".equalsIgnoreCase(column.getOrderNumber()))) {
			sql.append(" and t.order_number = ? ");
			valuesList.add(Integer.valueOf(column.getOrderNumber()));
			valueTypesList.add(Types.INTEGER);
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new ColumnMapper());
	}

	/**
	 * 根据栏目id获取栏目实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        栏目id
	 * @return Column实体
	 */
	public Column findColumnById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_column t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Column column = this.queryForObject(sql.toString(), values, valueTypes,
				new ColumnMapper());

		logger.info(sql.toString());

		return column;
	}

	/**
	 * 列出所有栏目
	 * 
	 * @author Soyi Yao
	 * @return 栏目列表
	 */
	public List<Column> listAllColumn() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_column t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new ColumnMapper());
	}

	/**
	 * 获取t_column表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_column表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_column  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

	/**
	 * 获取当前最大id值
	 * 
	 * @author Soyi Yao
	 * @return
	 */
	public int getCurrentMaxId() {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(id) from  t_column  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
