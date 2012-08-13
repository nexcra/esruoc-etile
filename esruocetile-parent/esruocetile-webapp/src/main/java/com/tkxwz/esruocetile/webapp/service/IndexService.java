package com.tkxwz.esruocetile.webapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.dao.ArticleDao;
import com.tkxwz.esruocetile.webapp.dao.ColumnDao;
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

	public void indexSessionData(HttpServletRequest request) {
		if (null == request.getSession().getAttribute("columnList")) {
			Page page = new Page(PageUtil.getPageNum("1"));
			List<Column> columnList = this.columnDao.listAllColumn();
			this.articleDao.listArticle(page);
			HttpSession session = request.getSession();
			session.setAttribute("columnList", columnList);

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

		}
	}
}
