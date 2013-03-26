package com.tkxgz.elitecourse.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.bean.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Column;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.ColumnService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;

/**
 * 栏目Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/column.do")
public class ColumnController {

	private static final String MODULE_NAME = "栏目管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private ColumnService columnService;

	@Autowired
	private UserService userService;

	/**
	 * 默认action
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping
	public String defaultAction(HttpServletRequest request,
			String currentPageNum) {

		return this.listColumn(request, currentPageNum);
	}

	/**
	 * 查询栏目列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listColumn")
	public String listColumn(HttpServletRequest request, String currentPageNum) {

		List<Column> columnList = this.columnService
				.listColumnTreeByParentId("0");

		request.setAttribute("columnList", columnList);

		return "/admin/column/listColumn.jsp";
	}

	/**
	 * 转到新增栏目页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddColumn")
	public String toAddColumn(HttpServletRequest request) {
		List<Column> columnList = this.columnService
				.listColumnTreeByParentId("0");

		request.setAttribute("columnList", columnList);

		return "/admin/column/addColumn.jsp";
	}

	/**
	 * 新增栏目
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addColumn")
	public String addColumn(HttpServletRequest request, String parentParams)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/column.do?action=listColumn";

		// 获取表单参数
		Column column = new Column();
		BeanUtil.populate(column, request.getParameterMap());

		String parentFullPathId = parentParams.split("@@")[0];

		String parentFullPathName = parentParams.split("@@")[1];

		String parentLevel = parentParams.split("@@")[2];

		String parentId = parentParams.split("@@")[3];

		int nodeId = this.columnService.getCurrentMaxId() + 1;// 加1作为主键

		column.setId(String.valueOf(nodeId));
		if ("0".equalsIgnoreCase(parentLevel)) {
			column.setFullPathName(column.getName());
			column.setFullPathId(String.valueOf(nodeId));
		} else {
			column.setFullPathId(parentFullPathId + "-" + nodeId);
			column.setFullPathName(parentFullPathName + "-" + column.getName());
		}

		column.setLevel(String.valueOf((Integer.valueOf(parentLevel) + 1)));
		column.setParentId(parentId);

		// 设置操作管理员
		column.setCreateUserId(admin.getId());
		column.setCreateUserName(admin.getName());

		int affectedRows = this.columnService.addColumn(column);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddColumn(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加栏目");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除栏目
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteColumn")
	public String deleteColumn(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.columnService.batchDeleteColumn(ids);

		response.getWriter().write(" 成功删除 " + result + "条父记录，子记录也已级联删除");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除栏目");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改栏目页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateColumn")
	public String toUpdateColumn(HttpServletRequest request, String id) {

		Map map = this.columnService.getColumnById(id);

		request.setAttribute("map", map);

		List<Column> columnList = this.columnService
				.listColumnTreeByParentId("0");

		request.setAttribute("columnList", columnList);

		return "/admin/column/updateColumn.jsp";
	}

	/**
	 * 修改栏目
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @param name
	 * @param password
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(params = "action=updateColumn")
	public String updateColumn(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/column.do?action=listColumn";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Column column = this.columnService.findColumnById(id);
		BeanUtil.populate(column, request.getParameterMap());

		int affectedRows = this.columnService.updateColumn(column);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddColumn(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改栏目");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看栏目
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewColumn")
	public String viewColumn(HttpServletRequest request, String id) {

		Map map = this.columnService.getColumnById(id);

		request.setAttribute("map", map);

		return "/admin/column/viewColumn.jsp";
	}

	/**
	 * 分页搜索栏目列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchColumn")
	public String searchColumn(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Column column = new Column();
		BeanUtil.populate(column, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.columnService.searchColumn(page, column);

		request.setAttribute("page", page);
		request.setAttribute("bean", column);

		return "/admin/column/listColumn.jsp";
	}

	/**
	 * 导出栏目数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportColumnList")
	public String exportColumnList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要栏目的栏目列表
		Column column = new Column();
		BeanUtil.populate(column, request.getParameterMap());
		List<Column> excelContent = this.columnService
				.searchColumnForList(column);

		String[] columnNames = new String[] { "ParentId", "Name", "Code",
				"IsLeaf", "Level", "FullPathId", "FullPathName",
				"CreateUserId", "CreateUserName", "CreateTime", "OrderNumber" };

		String titleName = "columnList.xls";// 不创建说明;
		String sheetName = "栏目列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.columnService.exportColumnList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}

}
