package com.tkxwz.esruocetile.webapp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.core.page.Page;
import com.tkxwz.esruocetile.core.util.PageUtil;
import com.tkxwz.esruocetile.webapp.entity.Column;
import com.tkxwz.esruocetile.webapp.service.ColumnService;

/**
 * @author Po Kong
 * @since 23 Jul 2012 22:03:29
 */
@Controller("/column.do")
public class ColumnController {

	@Autowired
	private ColumnService columnService;

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
	public String toAddColumn() {
		return "/column/addColumn.jsp";
	}

	/**
	 * add a Column and then forward to the list page
	 * 
	 * @author Po Kong
	 * @since 21 Jul 2012 12:48:53
	 * @return the page to list the Columns
	 */
	@RequestMapping(params = "action=addColumn")
	public String addColumn(String columnName, String description, int orderNum) {
		Column column = new Column();
		column.setColumnName(columnName);
		column.setColumnType(2);
		column.setDescription(description);
		column.setOrderNum(orderNum);
		int result = this.columnService.addColumn(column);
		return "redirect:column.do?action=listColumn";
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
		return "/column/updateColumn.jsp";
	}

	@RequestMapping(params = "action=updateColumn")
	public String updateColumn(int id, String columnName, String description,
			int orderNum) {
		Column column = new Column();
		column.setId(id);
		column.setColumnName(columnName);
		column.setColumnType(2);
		column.setDescription(description);
		column.setOrderNum(orderNum);

		int result = this.columnService.updateColumn(column);
		return "redirect:column.do?action=listColumn";
	}

}
