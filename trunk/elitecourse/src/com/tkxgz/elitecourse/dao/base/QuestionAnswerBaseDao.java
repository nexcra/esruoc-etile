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
import com.tkxgz.elitecourse.bean.QuestionAnswer;
import com.tkxgz.elitecourse.mapper.QuestionAnswerMapper;

/**
 * 答疑Dao类
 * 
 * @author Soyi Yao
 */
@Repository
public class QuestionAnswerBaseDao extends BaseDao<QuestionAnswer> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页查询答疑列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 答疑分页数据
	 */
	public Page listQuestionAnswer(Page page) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_question_answer t ");
		sql.append(" where 1 = 1  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), page);
	}

	/**
	 * 添加答疑
	 * 
	 * @author Soyi Yao
	 * @param questionAnswer
	 *        答疑bean
	 * @return 影响行数
	 */
	public int addQuestionAnswer(QuestionAnswer questionAnswer) {

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into t_question_answer (");
		sql.append(" title , ");
		sql.append(" content , ");
		sql.append(" create_user_id , ");
		sql.append(" create_user_name , ");
		sql.append(" create_time , ");
		sql.append(" create_user_ip , ");
		sql.append(" re_content , ");
		sql.append(" re_user_id , ");
		sql.append(" re_user_name , ");
		sql.append(" re_time  ");
		sql.append(" ) ");
		sql.append(" values ( ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" now() , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ? , ");
		sql.append(" ?  ");

		sql.append(" ) ");
		Object[] values = { questionAnswer.getTitle(),
				questionAnswer.getContent(), questionAnswer.getCreateUserId(),
				questionAnswer.getCreateUserName(),
				questionAnswer.getCreateUserIp(),
				questionAnswer.getReContent(), questionAnswer.getReUserId(),
				questionAnswer.getReUserName(), questionAnswer.getReTime() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);

	}

	/**
	 * 根据答疑id删除答疑
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        答疑id
	 * @return 影响行数
	 */
	public int deleteQuestionAnswerById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  t_question_answer ");
		sql.append(" where id = ?  ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.insert(sql.toString(), values, valueTypes);
	}

	/**
	 * 根据答疑查询答疑信息
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        答疑id
	 * @return 答疑Map
	 */
	public Map getQuestionAnswerById(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_question_answer t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		logger.info(sql.toString());

		return this.queryForMap(sql.toString(), values, valueTypes);
	}

	/**
	 * 更新答疑
	 * 
	 * @author Soyi Yao
	 * @param questionAnswer
	 *        答疑bean
	 * @return 影响行数
	 */
	public int updateQuestionAnswer(QuestionAnswer questionAnswer) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update  t_question_answer t");
		sql.append(" set   ");
		sql.append(" t.title = ? , ");
		sql.append(" t.content = ? , ");
		sql.append(" t.create_user_id = ? , ");
		sql.append(" t.create_user_name = ? , ");
		sql.append(" t.create_time = ? , ");
		sql.append(" t.create_user_ip = ? , ");
		sql.append(" t.re_content = ? , ");
		sql.append(" t.re_user_id = ? , ");
		sql.append(" t.re_user_name = ? , ");
		sql.append(" t.re_time = now()  ");
		sql.append(" where t.id = ? ");

		Object[] values = { questionAnswer.getTitle(),
				questionAnswer.getContent(), questionAnswer.getCreateUserId(),
				questionAnswer.getCreateUserName(),
				questionAnswer.getCreateTime(),
				questionAnswer.getCreateUserIp(),
				questionAnswer.getReContent(), questionAnswer.getReUserId(),
				questionAnswer.getReUserName(), questionAnswer.getId() };
		int[] valueTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

		logger.info(sql.toString());

		return this.update(sql.toString(), values, valueTypes);
	}

	/**
	 * 分页搜索答疑列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param questionAnswer
	 *        答疑bean
	 * @return 分页答疑列表
	 */
	public Page searchQuestionAnswer(Page page, QuestionAnswer questionAnswer) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_question_answer t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(questionAnswer.getTitle())) {
			sql.append("and t.title like '%" + questionAnswer.getTitle() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getContent())) {
			sql.append("and t.content like '%" + questionAnswer.getContent()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ questionAnswer.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getCreateTime())) {
			sql.append("and t.create_time like '%"
					+ questionAnswer.getCreateTime() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getCreateUserIp())) {
			sql.append("and t.create_user_ip like '%"
					+ questionAnswer.getCreateUserIp() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getReContent())) {
			sql.append("and t.re_content like '%"
					+ questionAnswer.getReContent() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getReUserName())) {
			sql.append("and t.re_user_name like '%"
					+ questionAnswer.getReUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getReTime())) {
			sql.append("and t.re_time like '%" + questionAnswer.getReTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForPage(sql.toString(), values, valueTypes, page);
	}

	/**
	 * 搜索答疑列表
	 * 
	 * @author Soyi Yao
	 * @param questionAnswer
	 *        QuestionAnswer实体
	 * @return 答疑列表
	 */
	public List<QuestionAnswer> searchQuestionAnswerForList(
			QuestionAnswer questionAnswer) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from  t_question_answer t ");
		sql.append(" where 1 = 1 ");

		List<Object> valuesList = new ArrayList<Object>();
		List<Integer> valueTypesList = new ArrayList<Integer>();

		if (StringUtils.isNotEmpty(questionAnswer.getTitle())) {
			sql.append("and t.title like '%" + questionAnswer.getTitle() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getContent())) {
			sql.append("and t.content like '%" + questionAnswer.getContent()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getCreateUserName())) {
			sql.append("and t.create_user_name like '%"
					+ questionAnswer.getCreateUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getCreateTime())) {
			sql.append("and t.create_time like '%"
					+ questionAnswer.getCreateTime() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getCreateUserIp())) {
			sql.append("and t.create_user_ip like '%"
					+ questionAnswer.getCreateUserIp() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getReContent())) {
			sql.append("and t.re_content like '%"
					+ questionAnswer.getReContent() + "%'");
		}
		if (StringUtils
				.isNotEmpty(String.valueOf(questionAnswer.getReUserId()))
				&& (!"all".equalsIgnoreCase(questionAnswer.getReUserId()))) {
			sql.append(" and t.re_user_id = ? ");
			valuesList.add(Integer.valueOf(questionAnswer.getReUserId()));
			valueTypesList.add(Types.INTEGER);
		}
		if (StringUtils.isNotEmpty(questionAnswer.getReUserName())) {
			sql.append("and t.re_user_name like '%"
					+ questionAnswer.getReUserName() + "%'");
		}
		if (StringUtils.isNotEmpty(questionAnswer.getReTime())) {
			sql.append("and t.re_time like '%" + questionAnswer.getReTime()
					+ "%'");
		}

		sql.append(" order by t.id desc ");

		Object[] values = ListUtil.list2objectArray(valuesList);
		int[] valueTypes = ListUtil.list2intArray(valueTypesList);

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), values, valueTypes,
				new QuestionAnswerMapper());
	}

	/**
	 * 根据答疑id获取答疑实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        答疑id
	 * @return QuestionAnswer实体
	 */
	public QuestionAnswer findQuestionAnswerById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from  t_question_answer t where t.id = ? ");

		Object[] values = { Integer.parseInt(id) };
		int[] valueTypes = { Types.INTEGER };

		QuestionAnswer questionAnswer = this.queryForObject(sql.toString(),
				values, valueTypes, new QuestionAnswerMapper());

		logger.info(sql.toString());

		return questionAnswer;
	}

	/**
	 * 列出所有答疑
	 * 
	 * @author Soyi Yao
	 * @return 答疑列表
	 */
	public List<QuestionAnswer> listAllQuestionAnswer() {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from t_question_answer t  ");
		sql.append(" order by t.id desc ");

		logger.info(sql.toString());

		return this.queryForList(sql.toString(), new QuestionAnswerMapper());
	}

	/**
	 * 获取t_question_answer表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_question_answer表记录总数
	 */
	public int getTotalRecords() {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from  t_question_answer  ");

		logger.info(sql.toString());

		return this.queryForInt(sql.toString());
	}

}
