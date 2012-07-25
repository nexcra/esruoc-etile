package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.Article;
import com.tkxwz.esruocetile.webapp.entity.Column;
import com.tkxwz.esruocetile.webapp.service.ArticleService;
import com.tkxwz.esruocetile.webapp.service.ColumnService;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:03:29
 */
@Controller("/article.do")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ColumnService columnService;

	@RequestMapping(params = "action=listArticle")
	public String listArticle(HttpServletRequest request, String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.articleService.listArticle(page);
		request.setAttribute("page", page);
		return "/article/listArticle.jsp";
	}

	/**
	 * to add a Article
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:41:27
	 * @return the page to add Article
	 */

	@RequestMapping(params = "action=toAddArticle")
	public String toAddArticle(HttpServletRequest request) {
		List<Column> list = this.columnService.listAllColumn();
		request.setAttribute("list", list);
		return "/article/addArticle.jsp";
	}

	/**
	 * add a Article and then forward to the list page
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:48:53
	 * @return the page to list the Articles
	 */
	@RequestMapping(params = "action=addArticle")
	public String addArticle(String title, String subTitle, Integer columnId,
			String content, String author, String keywords, String copyFrom,
			Integer source, Integer hitCount) {
		Article article = new Article();
		article.setTitle(title);
		article.setSubTitle(subTitle);
		article.setColumnId(columnId);
		article.setContent(content);
		article.setAuthor(author);
		article.setKeywords(keywords);
		article.setCopyFrom(copyFrom);
		article.setSource(source);
		article.setHitCount(hitCount);

		int result = this.articleService.addArticle(article);
		return "redirect:article.do?action=listArticle";
	}

	@RequestMapping(params = "action=deleteArticle")
	public String deleteArticle(HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		String[] ids = id.split(",");
		int result = this.articleService.batchDeleteArticle(ids);
		response.getWriter().write(" 成功删除 " + result + "条记录");
		return null;
	}

	@RequestMapping(params = "action=toUpdateArticle")
	public String toUpdateArticle(HttpServletRequest request, String id) {
		Map map = this.articleService.getArticleById(id);
		request.setAttribute("map", map);
		return "/article/updateArticle.jsp";
	}

	@RequestMapping(params = "action=updateArticle")
	public String updateArticle(int id, String title, String content,
			String author, String keywords, String copyFrom, Integer source,
			Integer hitCount) {
		Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setContent(content);
		article.setAuthor(author);
		article.setKeywords(keywords);
		article.setCopyFrom(copyFrom);
		article.setSource(source);
		article.setHitCount(hitCount);

		int result = this.articleService.updateArticle(article);
		return "redirect:article.do?action=listArticle";
	}

}
