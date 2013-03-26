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
import com.tkxgz.elitecourse.bean.Template;
import com.tkxgz.elitecourse.mapper.TemplateMapper;
/**
 * 模板Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class TemplateBaseDao extends BaseDao<Template> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询模板列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 模板分页数据
	 */
	public Page listTemplate(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_template t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加模板
	 * 
	 * @author Soyi Yao
	 * @param template
	 *        模板bean
	 * @return 影响行数
	 */
	public int addTemplate(Template template) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_template (");
		   sql.append(" template_name , "); 
  sql.append(" description , "); 
  sql.append(" create_user_id , "); 
  sql.append(" create_user_name , "); 
  sql.append(" create_time , "); 
  sql.append(" file_name , "); 
  sql.append(" template_code , "); 
  sql.append(" template_content  ");  
		sql.append(" ) ");
		sql.append(" values ( ");
		   sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" now() , "); 
  sql.append(" ? , "); 
  sql.append(" ? , "); 
  sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { template.getTemplateName(), 
template.getDescription(), 
template.getCreateUserId(), 
template.getCreateUserName(), 
template.getFileName(), 
template.getTemplateCode(), 
template.getTemplateContent()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR , 
 Types.VARCHAR  };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据模板id删除模板
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        模板id
	 * @return 影响行数
	 */
	public int deleteTemplateById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_template ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据模板查询模板信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        模板id
	 * @return 模板Map
	 */
	public Map getTemplateById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_template t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };
		
		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新模板
	 * 
	 * @author Soyi Yao
	 * @param template
	 *        模板bean
	 * @return 影响行数
	 */
	public int updateTemplate(Template template) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_template t");
		sql.append(" set   ");
		 sql.append(" t.template_name = ? , "); 
  sql.append(" t.description = ? , "); 
  sql.append(" t.create_user_id = ? , "); 
  sql.append(" t.create_user_name = ? , "); 
  sql.append(" t.create_time = ? , "); 
  sql.append(" t.file_name = ? , "); 
  sql.append(" t.template_code = ? , "); 
  sql.append(" t.template_content = ?  ");
		sql.append(" where t.id = ? ");

		Object[] values = { template.getTemplateName(), 
template.getDescription(), 
template.getCreateUserId(), 
template.getCreateUserName(), 
template.getCreateTime(), 
template.getFileName(), 
template.getTemplateCode(), 
template.getTemplateContent(), 
 template.getId()};
		int[] valueTypes = {  Types.VARCHAR , 
 Types.VARCHAR , 
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
	 * 分页搜索模板列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param template
	 *        模板bean
	 * @return 分页模板列表
	 */
	public Page searchTemplate(Page page, Template template) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_template t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(template.getTemplateName())) {sql.append( "and t.template_name like '%"+template.getTemplateName()+"%'");}if (StringUtils.isNotEmpty(template.getDescription())) {sql.append( "and t.description like '%"+template.getDescription()+"%'");}if (StringUtils.isNotEmpty(template.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+template.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(template.getCreateTime())) {sql.append( "and t.create_time like '%"+template.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(template.getFileName())) {sql.append( "and t.file_name like '%"+template.getFileName()+"%'");}if (StringUtils.isNotEmpty(template.getTemplateCode())) {sql.append( "and t.template_code like '%"+template.getTemplateCode()+"%'");}if (StringUtils.isNotEmpty(template.getTemplateContent())) {sql.append( "and t.template_content like '%"+template.getTemplateContent()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}
	
	/**
	 * 搜索模板列表
	 * 
	 * @author Soyi Yao
	 * @param template
	 *        Template实体
	 * @return 模板列表
	 */
	public List<Template> searchTemplateForList(Template template) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_template t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(template.getTemplateName())) {sql.append( "and t.template_name like '%"+template.getTemplateName()+"%'");}if (StringUtils.isNotEmpty(template.getDescription())) {sql.append( "and t.description like '%"+template.getDescription()+"%'");}if (StringUtils.isNotEmpty(template.getCreateUserName())) {sql.append( "and t.create_user_name like '%"+template.getCreateUserName()+"%'");}if (StringUtils.isNotEmpty(template.getCreateTime())) {sql.append( "and t.create_time like '%"+template.getCreateTime()+"%'");}if (StringUtils.isNotEmpty(template.getFileName())) {sql.append( "and t.file_name like '%"+template.getFileName()+"%'");}if (StringUtils.isNotEmpty(template.getTemplateCode())) {sql.append( "and t.template_code like '%"+template.getTemplateCode()+"%'");}if (StringUtils.isNotEmpty(template.getTemplateContent())) {sql.append( "and t.template_content like '%"+template.getTemplateContent()+"%'");}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new TemplateMapper());
	}
	
	/**
	 * 根据模板id获取模板实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        模板id
	 * @return Template实体
	 */
	public Template findTemplateById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_template t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		Template template = this.queryForObject(sql.toString(), values, valueTypes,
				new TemplateMapper());
		
		logger.info(sql.toString());

		return template;
	}


	/**
	 * 列出所有模板
	 * 
	 * @author Soyi Yao
	 * @return 模板列表
	 */
	public List<Template> listAllTemplate() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_template t  ");
		sql.append(" order by t.id desc ");
		
		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new TemplateMapper());
	}
	
    /**
	 * 获取t_template表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_template表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_template  ");

		logger.info(sql.toString());
		
		return this.queryForInt(sql.toString());
	}
	
	/**
	 * 获取t_template当前最大id值
	 * 
	 * @author Soyi Yao
	 * @return
	 */
	public int getCurrentMaxId() {
		StringBuilder sql = new StringBuilder();
		sql.append("select max(id) from  t_template  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}
	
	
}
