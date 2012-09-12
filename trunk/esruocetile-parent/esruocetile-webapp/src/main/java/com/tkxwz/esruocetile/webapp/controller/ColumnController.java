package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.BeanUtil;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.Column;
import com.tkxwz.esruocetile.webapp.service.ColumnService;
import com.tkxwz.esruocetile.webapp.service.IndexService;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:03:29
 */
@Controller("/column.do")
public class ColumnController {

	@Autowired
	private ColumnService columnService;

	@Autowired
	private IndexService indexService;

	@RequestMapping(params = "action=listColumn")
	public String listColumn(HttpServletRequest request, String currentPageNum) {
		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		this.columnService.listColumn(page);
		request.setAttribute("page", page);
		return "/column/listColumn.jsp";
	}

	/**
	 * to add a Column
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:41:27
	 * @return the page to add Column
	 */

	@RequestMapping(params = "action=toAddColumn")
	public String toAddColumn(HttpServletRequest request) {
		List<Column> list = this.columnService.listAllColumnForColumn();
		request.setAttribute("list", list);
		return "/column/addColumn.jsp";
	}

	/**
	 * add a Column and then forward to the list page
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:48:53
	 * @return the page to list the Columns
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(params = "action=addColumn")
	public String addColumn(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {

		Column column = new Column();
		BeanUtil.populate(column, request.getParameterMap());
		int result = this.columnService.addColumn(column);
		return "redirect:column.do?action=listColumn";
	}

	@RequestMapping(params = "action=isColumnExist")
	public String isColumnExist(HttpServletRequest request,
			HttpServletResponse response, String columnName,
			String oldColumnName) throws IOException {
		String result = "false";
		if (StringUtils.isNotEmpty(oldColumnName)
				&& oldColumnName.equals(columnName)) {
			result = "true";
		} else {
			boolean isUserExist = this.columnService.isUserExist(columnName);
			if (!isUserExist) {
				result = "true";
			}

		}

		response.getWriter().write(result);
		return null;
	}

	@RequestMapping(params = "action=deleteColumn")
	public String deleteColumn(HttpServletRequest request,
			HttpServletResponse response, String id) throws IOException {
		String[] ids = id.split(",");
		int result = this.columnService.batchDeleteColumn(ids);
		response.getWriter().write(" 成功删除 " + result + "条记录");
		return null;
	}

	@RequestMapping(params = "action=toUpdateColumn")
	public String toUpdateColumn(HttpServletRequest request, String id) {
		Map map = this.columnService.getColumnById(id);
		request.setAttribute("map", map);

		List<Column> list = this.columnService.listAllColumnForColumn();
		request.setAttribute("list", list);

		return "/column/updateColumn.jsp";
	}

	@RequestMapping(params = "action=updateColumn")
	public String updateColumn(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
		Column column = new Column();
		BeanUtil.populate(column, request.getParameterMap());

		int result = this.columnService.updateColumn(column);
		return "redirect:column.do?action=listColumn";
	}

	@RequestMapping(params = "action=viewColumn")
	public String viewColumn(HttpServletRequest request, String id) {
		Map map = this.columnService.getColumnById(id);
		request.setAttribute("map", map);
		return "/column/viewColumn.jsp";
	}

	@RequestMapping(params = "action=listArticle")
	public String listArticleByColumnId(HttpServletRequest request,
			String currentPageNum, String columnId, String columnType) {
		this.indexService.indexSessionData(request);

		String result = "/front/article/listArticle.jsp";
		if ("1".equals(columnType)) {
			Map map = this.columnService.getColumnById(columnId);
			request.setAttribute("map", map);
			result = "/front/column/viewColumnContent.jsp";
		} else {
			Page page = new Page();
			page = new Page(PageUtil.getPageNum(currentPageNum));
			this.columnService.listArticleByColumnId(page,
					Integer.valueOf(columnId));
			request.setAttribute("page", page);
		}
		return result;
	}

	@RequestMapping(params = "action=listArticleByColumnCode")
	public String listArticleByColumnCode(HttpServletRequest request,
			String currentPageNum, String columnCode, String columnType) {
		this.indexService.indexSessionData(request);

		String result = "/front/article/listArticle.jsp";

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));
		String columnName = "";
		if ("tzga".equalsIgnoreCase(columnCode)) {
			columnName = "通知公告";
		} else if ("cszn".equalsIgnoreCase(columnCode)) {
			columnName = "测试指南";
		}

		else if ("jgsz".equalsIgnoreCase(columnCode)) {
			columnName = "机构设置";
		} else if ("zcwj".equalsIgnoreCase(columnCode)) {
			columnName = "政策文件";
		}
		this.columnService.listArticleByColumnName(page, columnName);
		request.setAttribute("page", page);
		return result;
	}
}
