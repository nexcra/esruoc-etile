package com.tkxwz.esruocetile.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.Column;
import com.tkxwz.esruocetile.webapp.service.ArticleService;
import com.tkxwz.esruocetile.webapp.service.ColumnService;

/**
 * @author Po Kong
 * @since 2012-8-4 上午11:31:42
 */
@Controller(value = "/index.do")
public class IndexController {

	@Autowired
	private ColumnService columnService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping()
	public String defaultPage(HttpServletRequest request) {
		return this.toIndex(request);
	}

	@RequestMapping(params = "action=toIndex")
	public String toIndex(HttpServletRequest request) {
		Page page = new Page(PageUtil.getPageNum("1"));
		List<Column> columnList = this.columnService.listAllColumn();
		this.articleService.listArticle(page);
		HttpSession session = request.getSession();
		session.setAttribute("columnList", columnList);

		request.setAttribute("recentArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleService.listArticleByColumnName(page, "通知公告");
		request.setAttribute("tzggArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleService.listArticleByColumnName(page, "常见问题");
		request.setAttribute("cjwtArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleService.listArticleByColumnName(page, "测试指南");
		request.setAttribute("csznArticleList", page);

		page = new Page(PageUtil.getPageNum("1"));
		this.articleService.listArticleByColumnName(page, "文档下载");
		request.setAttribute("wdxzArticleList", page);
		
		page = new Page(PageUtil.getPageNum("1"));
		this.articleService.listArticleByColumnName(page, "机构设置");
		request.setAttribute("jgszArticleList", page);
		//政策文件二级
		page = new Page(PageUtil.getPageNum("1"));
		//this.articleService.listColumnByParentColumnName(page, "机构设置");
		//request.setAttribute("jgszArticleList", page);
		 
		

		session.setAttribute("csznColumnList", columnList);

		return "/front/index.jsp";
	}
}
