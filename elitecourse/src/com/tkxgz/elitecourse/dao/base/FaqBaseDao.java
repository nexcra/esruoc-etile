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
import com.tkxgz.elitecourse.bean.Faq;
import com.tkxgz.elitecourse.mapper.FaqMapper;
/**
 * 常见问题Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class FaqBaseDao extends BaseDao<Faq> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 常见问题分页数据
	 */
	public Page listFaq(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_faq t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加常见问题
	 * 
	 * @author Soyi Yao
	 * @param faq
	 *        常见问题bean
	 * @return 影响行数
	 */
	public int addFaq(Faq faq) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_faq (");
		   sql.append(" question , "); 
  sql.append(" answer , "); 
  sql.append(" status , "); 
  sql.append(" reference , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_user_name , "); 
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
		Object[] values = { faq.getQuestion(), 
faq.getAnswer(), 
faq.getStatus(), 
faq.getReference(), 
faq.getCreateUserId(), 
faq.getCreateUserName()};
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
	 * 根据常见问题id删除常见问题
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        常见问题id
	 * @return 影响行数
	 */
	public int deleteFaqById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_faq ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据常见问题查询常见问题信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        常见问题id
	 * @return 常见问题Map
	 */
	public Map getFaqById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_faq t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新常见问题
	 * 
	 * @author Soyi Yao
	 * @param faq
	 *        常见问题bean
	 * @return 影响行数
	 */
	public int updateFaq(Faq faq) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_faq t");
		sql.append(" set   ");
		 sql.append(" t.question = ? , "); 
  sql.append(" t.answer = ? , "); 
  sql.append(" t.status = ? , "); 
  sql.append(" t.reference = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_user_name = ? , "); 
  sql.append(" t.create_time = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { faq.getQuestion(), 
faq.getAnswer(), 
faq.getStatus(), 
faq.getReference(), 
faq.getCreateUserId(), 
faq.getCreateUserName(), 
faq.getCreateTime(), 
 faq.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
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
	 * 分页搜索常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param faq
	 *        常见问题bean
	 * @return 分页常见问题列表
	 */
	public Page searchFaq(Page page, Faq faq) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_faq t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(faq.getQuestion())) {sql.append( "and t.question like '%"+faq.getQuestion()+"%'");}if (StringUtils.isNotEmpty(faq.getAnswer())) {sql.append( "and t.answer like '%"+faq.getAnswer()+"%'");}if (StringUtils.isNotEmpty(faq.getStatus())) {sql.append( "and t.status like '%"+faq.getStatus()+"%'");}if (StringUtils.isNotEmpty(faq.getReference())) {sql.append( "and t.reference like '%"+faq.getReference()+"%'");}if (StringUtils.isNotEmpty(faq.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+faq.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(faq.getCreateTime())) {sql.append( "and t.create_time like '%"+faq.getCreateTime()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param faq
	 *        Faq实体
	 * @return 常见问题列表
	 */
	public List<Faq> searchFaqForList(Faq faq) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_faq t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(faq.getQuestion())) {sql.append( "and t.question like '%"+faq.getQuestion()+"%'");}if (StringUtils.isNotEmpty(faq.getAnswer())) {sql.append( "and t.answer like '%"+faq.getAnswer()+"%'");}if (StringUtils.isNotEmpty(faq.getStatus())) {sql.append( "and t.status like '%"+faq.getStatus()+"%'");}if (StringUtils.isNotEmpty(faq.getReference())) {sql.append( "and t.reference like '%"+faq.getReference()+"%'");}if (StringUtils.isNotEmpty(faq.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+faq.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(faq.getCreateTime())) {sql.append( "and t.create_time like '%"+faq.getCreateTime()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new FaqMapper());
	}
	
	/**
	 * 根据常见问题id获取常见问题实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        常见问题id
	 * @return Faq实体
	 */
	public Faq findFaqById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_faq t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Faq faq = this.queryForObject(sql.toString(), values, valueTypes,
				new FaqMapper());
		
		logger.info(sql.toString());

		return faq;
	}


	/**
	 * 列出所有常见问题
	 * 
	 * @author Soyi Yao
	 * @return 常见问题列表
	 */
	public List<Faq> listAllFaq() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_faq t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new FaqMapper());
	}
	
    /**
	 * 获取t_faq表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_faq表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_faq  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	
}
