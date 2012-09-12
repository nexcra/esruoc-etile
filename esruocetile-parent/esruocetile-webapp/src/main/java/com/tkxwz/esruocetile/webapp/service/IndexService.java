package com.tkxwz.esruocetile.webapp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.dao.ArticleDao;
import com.tkxwz.esruocetile.webapp.dao.ColumnDao;
import com.tkxwz.esruocetile.webapp.dao.ConfigDao;
import com.tkxwz.esruocetile.webapp.entity.Column;

/**
 * @author Po Kong
 * @since 2012-8-10 下午7:50:43
 */
@Service
public class IndexService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private ColumnDao columnDao;
	
	@Autowired
	private ConfigDao configDao;

	public void indexSessionData(HttpServletRequest request) {

		Page page = new Page(PageUtil.getPageNum("1"));
		List<Column> columnList = this.columnDao.listAllColumnForNav();
		
		this.articleDao.listArticle(page);

		request.setAttribute("columnList", columnList);
		request.getSession().setAttribute("columnList", columnList);

		request.setAttribute("recentArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleDao.listArticleByColumnName(page, "通知公告");
		request.setAttribute("tzggArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleDao.listArticleByColumnName(page, "常见问题");
		request.setAttribute("cjwtArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleDao.listArticleByColumnName(page, "测试指南");
		request.setAttribute("csznArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleDao.listArticleByColumnName(page, "文档下载");
		request.setAttribute("wdxzArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleDao.listArticleByColumnName(page, "机构设置");
		request.setAttribute("jgszArticleList", page);
		
		int id = this.columnDao.getColumnIdByColumnName("政策文件");
		List<Column> zcwjColumnList = this.columnDao.listColumnByParentId(id);
		request.setAttribute("zcwjColumnList", zcwjColumnList);
		
		//网站底部
		Map footerMap = this.configDao.getConfigByCode("footer");
		request.setAttribute("footerMap", footerMap);
		
		//预约说明
		Map bookingDescriptionMap = this.configDao.getConfigByCode("bookingDescription");
		request.setAttribute("bookingDescriptionMap", bookingDescriptionMap);
		
		

	}
}
