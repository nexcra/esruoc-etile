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
		Page recentArticleList = new Page(PageUtil.getPageNum("1"));
		List<Column> columnList = this.columnService.listAllColumn();
		this.articleService.listArticle(recentArticleList);

		HttpSession session = request.getSession();

		session.setAttribute("columnList", columnList);
		request.setAttribute("recentArticleList", recentArticleList);
		return "/front/index.jsp";
	}
}
