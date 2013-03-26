package com.tkxgz.elitecourse.dao;

import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.bean.Column;
import com.tkxgz.elitecourse.dao.base.ColumnBaseDao;
import com.tkxgz.elitecourse.mapper.ColumnMapper;

/**
 * 栏目Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class ColumnDao extends ColumnBaseDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 栏目树列表
	 * 
	 * @author Soyi Yao
	 * @param parentId
	 *        父栏目id
	 * @return 栏目树数据
	 */
	public List<Column> listColumnTreeByParentId(String parentId) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_column t ");
		sql.append(" where 1 = 1  ");
		if (!"0".equalsIgnoreCase(parentId)) {
			sql.append(" and t.full_path_id  like '%" + parentId + "%'  ");
		}
		sql.append(" order by t.full_path_id asc, t.order_number desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new ColumnMapper());
	}

	/**
	 * 根据栏目id删除栏目，连同子栏目一起删除
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        栏目id
	 * @return 影响行数
	 */
	@Override
	public int deleteColumnById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_column ");
		sql.append(" where full_path_id like'%" + id + "%'  ");

		logger.info(sql.toString());

		return this.insert(sql.toString());
	}

	/**
	 * 添加栏目
	 * 
	 * @author Soyi Yao
	 * @param column
	 *        栏目bean
	 * @return 影响行数
	 */
	@Override
	public int addColumn(Column column) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_column (");
		sql.append(" id , ");
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
		sql.append(" ? , ");
		sql.append(" now() , ");
		sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { column.getId(), column.getParentId(),
				column.getName(), column.getCode(), column.getIsLeaf(),
				column.getLevel(), column.getFullPathId(),
				column.getFullPathName(), column.getCreateUserId(),
				column.getCreateUserName(), column.getOrderNumber() };
		int[] valueTypes = { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}
}
