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
import com.tkxgz.elitecourse.bean.UserGroup;
import com.tkxgz.elitecourse.mapper.UserGroupMapper;
/**
 * 用户组Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class UserGroupBaseDao extends BaseDao<UserGroup> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询用户组列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 用户组分页数据
	 */
	public Page listUserGroup(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_user_group t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加用户组
	 * 
	 * @author Soyi Yao
	 * @param userGroup
	 *        用户组bean
	 * @return 影响行数
	 */
	public int addUserGroup(UserGroup userGroup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_user_group (");
		   sql.append(" user_id , "); 
  sql.append(" group_id  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { userGroup.getUserId(), 
userGroup.getGroupId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据用户组id删除用户组
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户组id
	 * @return 影响行数
	 */
	public int deleteUserGroupById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_user_group ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据用户组查询用户组信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户组id
	 * @return 用户组Map
	 */
	public Map getUserGroupById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_user_group t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新用户组
	 * 
	 * @author Soyi Yao
	 * @param userGroup
	 *        用户组bean
	 * @return 影响行数
	 */
	public int updateUserGroup(UserGroup userGroup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_user_group t");
		sql.append(" set   ");
		 sql.append(" t.user_id = ? , "); 
  sql.append(" t.group_id = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { userGroup.getUserId(), 
userGroup.getGroupId(), 
 userGroup.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.INTEGER };
		
		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}


	/**
	 * 分页搜索用户组列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param userGroup
	 *        用户组bean
	 * @return 分页用户组列表
	 */
	public Page searchUserGroup(Page page, UserGroup userGroup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_user_group t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

			if (StringUtils.isNotEmpty(String.valueOf(userGroup.getUserId()))&& (!"all".equalsIgnoreCase(userGroup.getUserId()))){		sql.append(" and t.user_id = ? ");	valuesList.add(Integer.valueOf(userGroup.getUserId()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(userGroup.getGroupId()))&& (!"all".equalsIgnoreCase(userGroup.getGroupId()))){		sql.append(" and t.group_id = ? ");	valuesList.add(Integer.valueOf(userGroup.getGroupId()));	valueTypesList.add(Types.INTEGER);}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索用户组列表
	 * 
	 * @author Soyi Yao
	 * @param userGroup
	 *        UserGroup实体
	 * @return 用户组列表
	 */
	public List<UserGroup> searchUserGroupForList(UserGroup userGroup) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_user_group t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

			if (StringUtils.isNotEmpty(String.valueOf(userGroup.getUserId()))&& (!"all".equalsIgnoreCase(userGroup.getUserId()))){		sql.append(" and t.user_id = ? ");	valuesList.add(Integer.valueOf(userGroup.getUserId()));	valueTypesList.add(Types.INTEGER);}	if (StringUtils.isNotEmpty(String.valueOf(userGroup.getGroupId()))&& (!"all".equalsIgnoreCase(userGroup.getGroupId()))){		sql.append(" and t.group_id = ? ");	valuesList.add(Integer.valueOf(userGroup.getGroupId()));	valueTypesList.add(Types.INTEGER);}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new UserGroupMapper());
	}
	
	/**
	 * 根据用户组id获取用户组实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户组id
	 * @return UserGroup实体
	 */
	public UserGroup findUserGroupById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_user_group t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		UserGroup userGroup = this.queryForObject(sql.toString(), values, valueTypes,
				new UserGroupMapper());
		
		logger.info(sql.toString());

		return userGroup;
	}


	/**
	 * 列出所有用户组
	 * 
	 * @author Soyi Yao
	 * @return 用户组列表
	 */
	public List<UserGroup> listAllUserGroup() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_user_group t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new UserGroupMapper());
	}
	
    /**
	 * 获取t_user_group表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_user_group表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_user_group  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
