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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;

/**
 * 日志Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/log.do")
public class LogController {

	private static final String MODULE_NAME = "日志管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

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

		return this.listLog(request, currentPageNum);
	}

	/**
	 * 查询日志列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listLog")
	public String listLog(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.logService.listLog(page);

		request.setAttribute("page", page);

		return "/admin/log/listLog.jsp";
	}

	/**
	 * 转到新增日志页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddLog")
	public String toAddLog(HttpServletRequest request) {

		return "/admin/log/addLog.jsp";
	}

	/**
	 * 新增日志
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addLog")
	public String addLog(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/log.do?action=listLog";

		// 获取表单参数
		Log log = new Log();
		BeanUtil.populate(log, request.getParameterMap());

		int affectedRows = this.logService.addLog(log);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddLog(request);
		}

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除日志
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteLog")
	public String deleteLog(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.logService.batchDeleteLog(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除用户");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改日志页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateLog")
	public String toUpdateLog(HttpServletRequest request, String id) {

		Map map = this.logService.getLogById(id);

		request.setAttribute("map", map);

		return "/admin/log/updateLog.jsp";
	}

	/**
	 * 修改日志
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
	@RequestMapping(params = "action=updateLog")
	public String updateLog(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/log.do?action=listLog";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Log log = this.logService.findLogById(id);
		BeanUtil.populate(log, request.getParameterMap());

		int affectedRows = this.logService.updateLog(log);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddLog(request);
		}

		return result;
	}

	/**
	 * 查看日志
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewLog")
	public String viewLog(HttpServletRequest request, String id) {

		Map map = this.logService.getLogById(id);

		request.setAttribute("map", map);

		return "/admin/log/viewLog.jsp";
	}

	/**
	 * 分页搜索日志列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchLog")
	public String searchLog(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Log log = new Log();
		BeanUtil.populate(log, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.logService.searchLog(page, log);

		request.setAttribute("page", page);
		request.setAttribute("bean", log);

		return "/admin/log/listLog.jsp";
	}

	/**
	 * 导出日志数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportLogList")
	public String exportLogList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要日志的日志列表
		Log log = new Log();
		BeanUtil.populate(log, request.getParameterMap());
		List<Log> excelContent = this.logService.searchLogForList(log);

		String[] columnNames = new String[] { "日志类型",   "用户",
				"模块", "描述", "IP地址", "日志时间" };

		String titleName = "logList.xls";// 不创建说明;
		String sheetName = "日志列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.logService.exportLogList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}

}
