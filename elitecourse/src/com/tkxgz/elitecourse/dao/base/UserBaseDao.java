package com.tkxgz.elitecourse.dao.base;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.ListUtil;
import com.tkxgz.elitecourse.mapper.UserMapper;

/**
 * 用户Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class UserBaseDao extends BaseDao<User> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询用户列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 用户分页数据
	 */
	public Page listUser(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_user t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加用户
	 * 
	 * @author Soyi Yao
	 * @param user
	 *        用户bean
	 * @return 影响行数
	 */
	public int addUser(User user) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_user (");
		sql.append(" name , ");
		sql.append(" real_name , ");
		sql.append(" student_no , ");
		sql.append(" password , ");
		sql.append(" status , ");
		sql.append(" gender , ");
		sql.append(" origin , ");
		sql.append(" birth_date , ");
		sql.append(" group_id , ");
		sql.append(" email , ");
		sql.append(" telephone , ");
		sql.append(" is_admin , ");
		sql.append(" is_locked , ");
		sql.append(" age , ");
		sql.append(" role , ");
		sql.append(" classes_id , ");
		sql.append(" create_user_name , ");
		sql.append(" create_user_id , ");
		sql.append(" create_time , ");
		sql.append(" update_user_name , ");
		sql.append(" update_user_id , ");
		sql.append(" update_time , ");
		sql.append(" remark  ");
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
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now() , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { user.getName(), user.getRealName(),
				user.getStudentNo(), user.getPassword(), user.getStatus(),
				user.getGender(), user.getOrigin(), user.getBirthDate(),
				user.getGroupId(), user.getEmail(), user.getTelephone(),
				user.getIsAdmin(), user.getIsLocked(), user.getAge(),
				user.getRole(), user.getClassesId(), user.getCreateUserName(),
				user.getCreateUserId(), user.getUpdateUserName(),
				user.getUpdateUserId(), user.getUpdateTime(), user.getRemark() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据用户id删除用户
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户id
	 * @return 影响行数
	 */
	public int deleteUserById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_user ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据用户查询用户信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户id
	 * @return 用户Map
	 */
	public Map getUserById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_user t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新用户
	 * 
	 * @author Soyi Yao
	 * @param user
	 *        用户bean
	 * @return 影响行数
	 */
	public int updateUser(User user) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_user t");
		sql.append(" set   ");
		sql.append(" t.name = ? , ");
		sql.append(" t.real_name = ? , ");
		sql.append(" t.student_no = ? , ");
		sql.append(" t.password = ? , ");
		sql.append(" t.status = ? , ");
		sql.append(" t.gender = ? , ");
		sql.append(" t.origin = ? , ");
		sql.append(" t.birth_date = ? , ");
		sql.append(" t.group_id = ? , ");
		sql.append(" t.email = ? , ");
		sql.append(" t.telephone = ? , ");
		sql.append(" t.is_admin = ? , ");
		sql.append(" t.is_locked = ? , ");
		sql.append(" t.age = ? , ");
		sql.append(" t.role = ? , ");
		sql.append(" t.classes_id = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_time = ? , ");
		sql.append(" t.update_user_name = ? , ");
		sql.append(" t.update_user_id = ? , ");
		sql.append(" t.update_time = now() , ");
		sql.append(" t.remark = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { user.getName(), user.getRealName(),
				user.getStudentNo(), user.getPassword(), user.getStatus(),
				user.getGender(), user.getOrigin(), user.getBirthDate(),
				user.getGroupId(), user.getEmail(), user.getTelephone(),
				user.getIsAdmin(), user.getIsLocked(), user.getAge(),
				user.getRole(), user.getClassesId(), user.getCreateUserName(),
				user.getCreateUserId(), user.getCreateTime(),
				user.getUpdateUserName(), user.getUpdateUserId(),
				user.getRemark(), user.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索用户列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param user
	 *        用户bean
	 * @return 分页用户列表
	 */
	public Page searchUser(Page page, User user) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_user t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(user.getName())) {
			sql.append("and t.name like '%" + user.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getRealName())) {
			sql.append("and t.real_name like '%" + user.getRealName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getPassword())) {
			sql.append("and t.password like '%" + user.getPassword() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getStatus())) {
			sql.append("and t.status like '%" + user.getStatus() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getGender())
				&& !"all".equalsIgnoreCase(user.getGender())) {
			sql.append("and t.gender like '%" + user.getGender() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getOrigin())) {
			sql.append("and t.origin like '%" + user.getOrigin() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getBirthDate())) {
			sql.append("and t.birth_date like '%" + user.getBirthDate() + "%'");
		}
		/*
		 * if (StringUtils.isNotEmpty(String.valueOf(user.getGroupId()))
		 * && (!"all".equalsIgnoreCase(user.getGroupId()))) {
		 * sql.append(" and t.group_id = ? ");
		 * valuesList.add(Integer.valueOf(user.getGroupId()));
		 * valueTypesList.add(Types.INTEGER);
		 * }
		 */
		if (StringUtils.isNotEmpty(user.getEmail())) {
			sql.append("and t.email like '%" + user.getEmail() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getTelephone())) {
			sql.append("and t.telephone like '%" + user.getTelephone() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getIsAdmin())) {
			sql.append("and t.is_admin like '%" + user.getIsAdmin() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getIsLocked())) {
			sql.append("and t.is_locked like '%" + user.getIsLocked() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getRole())) {
			sql.append("and t.role like '%" + user.getRole() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(user.getClassesId()))
				&& (!"all".equalsIgnoreCase(user.getClassesId()))) {
			sql.append(" and t.classes_id = ? ");
			valuesList.add(Integer.valueOf(user.getClassesId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(user.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ user.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getCreateTime())) {
			sql.append("and t.create_time like '%" + user.getCreateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(user.getUpdateUserName())) {
			sql.append("and t.update_user_name like '%"
					+ user.getUpdateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getUpdateTime())) {
			sql.append("and t.update_time like '%" + user.getUpdateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(user.getRemark())) {
			sql.append("and t.remark like '%" + user.getRemark() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索用户列表
	 * 
	 * @author Soyi Yao
	 * @param user
	 *        User实体
	 * @return 用户列表
	 */
	public List<User> searchUserForList(User user) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_user t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(user.getName())) {
			sql.append("and t.name like '%" + user.getName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getRealName())) {
			sql.append("and t.real_name like '%" + user.getRealName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getPassword())) {
			sql.append("and t.password like '%" + user.getPassword() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getStatus())) {
			sql.append("and t.status like '%" + user.getStatus() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getGender())
				&& !"all".equalsIgnoreCase(user.getGender())) {
			sql.append("and t.gender like '%" + user.getGender() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getOrigin())) {
			sql.append("and t.origin like '%" + user.getOrigin() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getBirthDate())) {
			sql.append("and t.birth_date like '%" + user.getBirthDate() + "%'");
		}
		/*
		 * if (StringUtils.isNotEmpty(String.valueOf(user.getGroupId()))
		 * && (!"all".equalsIgnoreCase(user.getGroupId()))) {
		 * sql.append(" and t.group_id = ? ");
		 * valuesList.add(Integer.valueOf(user.getGroupId()));
		 * valueTypesList.add(Types.INTEGER);
		 * }
		 */
		if (StringUtils.isNotEmpty(user.getEmail())) {
			sql.append("and t.email like '%" + user.getEmail() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getTelephone())) {
			sql.append("and t.telephone like '%" + user.getTelephone() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getIsAdmin())) {
			sql.append("and t.is_admin like '%" + user.getIsAdmin() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getIsLocked())) {
			sql.append("and t.is_locked like '%" + user.getIsLocked() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getRole())) {
			sql.append("and t.role like '%" + user.getRole() + "%'");
		}
		if (StringUtils.isNotEmpty(String.valueOf(user.getClassesId()))
				&& (!"all".equalsIgnoreCase(user.getClassesId()))) {
			sql.append(" and t.classes_id = ? ");
			valuesList.add(Integer.valueOf(user.getClassesId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(user.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ user.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getCreateTime())) {
			sql.append("and t.create_time like '%" + user.getCreateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(user.getUpdateUserName())) {
			sql.append("and t.update_user_name like '%"
					+ user.getUpdateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(user.getUpdateTime())) {
			sql.append("and t.update_time like '%" + user.getUpdateTime()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(user.getRemark())) {
			sql.append("and t.remark like '%" + user.getRemark() + "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new UserMapper());
	}

	/**
	 * 根据用户id获取用户实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户id
	 * @return User实体
	 */
	public User findUserById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_user t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		User user = this.queryForObject(sql.toString(), values, valueTypes,
				new UserMapper());

		logger.info(sql.toString());

		return user;
	}

	/**
	 * 列出所有用户
	 * 
	 * @author Soyi Yao
	 * @return 用户列表
	 */
	public List<User> listAllUser() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_user t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new UserMapper());
	}

	/**
	 * 获取t_user表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_user表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_user  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
