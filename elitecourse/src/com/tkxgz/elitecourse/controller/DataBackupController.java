package com.tkxgz.elitecourse.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import com.tkxgz.elitecourse.core.constant.CommonConstants;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tkxgz.elitecourse.bean.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.DataBackup;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.DataBackupService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.DictService;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.util.DataBackupUtils;

/**
 * 数据备份Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/dataBackup.do")
public class DataBackupController {

	private static final String MODULE_NAME = "数据备份管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private DataBackupService dataBackupService;

	@Autowired
	private UserService userService;

	@Autowired
	private DictService dictService;

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

		return this.listDataBackup(request, currentPageNum);
	}

	/**
	 * 查询数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listDataBackup")
	public String listDataBackup(HttpServletRequest request,
			String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.dataBackupService.listDataBackup(page);

		request.setAttribute("page", page);

		return "/admin/dataBackup/listDataBackup.jsp";
	}

	/**
	 * 转到新增数据备份页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddDataBackup")
	public String toAddDataBackup(HttpServletRequest request) {

		return "/admin/dataBackup/addDataBackup.jsp";
	}

	@RequestMapping(params = "action=toRestoreDataBackup")
	public String toRestoreDataBackup(HttpServletRequest request, String id) {
		Map map = this.dataBackupService.getDataBackupById(id);

		request.setAttribute("map", map);
		return "/admin/dataBackup/restoreDataBackup.jsp";
	}

	/**
	 * 新增数据备份
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addDataBackup")
	public String addDataBackup(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/dataBackup.do?action=listDataBackup";

		// 获取表单参数
		DataBackup dataBackup = new DataBackup();
		BeanUtil.populate(dataBackup, request.getParameterMap());

		// 设置操作管理员
		dataBackup.setCreateUserId(admin.getId());
		dataBackup.setCreateUserName(admin.getName());

		// 备份准备数据
		String name = dataBackup.getName() + dataBackup.getName() + "_"
				+ RandomStringUtils.randomAlphanumeric(10) + ".sql";
		dataBackup.setName(name);
		int affectedRows = this.dataBackupService.addDataBackup(dataBackup);// 新增
		String mysqlInstallPath = this.dictService.getDictByCode(
				"mysqlInstallPath").getDictValue();
		String sqlBackupFilePath = request.getSession().getServletContext()
				.getRealPath(CommonConstants.DATA_BACK_UP_DIRECTORY)
				+ "\\" + name;

		// 备份操作
		DataBackupUtils.backup(mysqlInstallPath, sqlBackupFilePath);

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddDataBackup(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加数据备份");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 还原数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=restoreDataBackup")
	public String restoreDataBackup(HttpServletRequest request)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/dataBackup.do?action=listDataBackup";

		// 获取表单参数
		DataBackup dataBackup = new DataBackup();
		BeanUtil.populate(dataBackup, request.getParameterMap());

		// 设置操作管理员
		dataBackup.setCreateUserId(admin.getId());
		dataBackup.setCreateUserName(admin.getName());

		String name = dataBackup.getName();

		String mysqlInstallPath = this.dictService.getDictByCode(
				"mysqlInstallPath").getDictValue();
		String sqlBackupFilePath = request.getSession().getServletContext()
				.getRealPath(CommonConstants.DATA_BACK_UP_DIRECTORY)
				+ "\\" + name;

		// 备份操作
		DataBackupUtils.restore(mysqlInstallPath, sqlBackupFilePath);

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("还原数据备份");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除数据备份
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteDataBackup")
	public String deleteDataBackup(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.dataBackupService.batchDeleteDataBackup(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除数据备份");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改数据备份页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateDataBackup")
	public String toUpdateDataBackup(HttpServletRequest request, String id) {

		Map map = this.dataBackupService.getDataBackupById(id);

		request.setAttribute("map", map);

		return "/admin/dataBackup/updateDataBackup.jsp";
	}

	/**
	 * 修改数据备份
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
	@RequestMapping(params = "action=updateDataBackup")
	public String updateDataBackup(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/dataBackup.do?action=listDataBackup";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		DataBackup dataBackup = this.dataBackupService.findDataBackupById(id);
		BeanUtil.populate(dataBackup, request.getParameterMap());

		int affectedRows = this.dataBackupService.updateDataBackup(dataBackup);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddDataBackup(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改数据备份");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看数据备份
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewDataBackup")
	public String viewDataBackup(HttpServletRequest request, String id) {

		Map map = this.dataBackupService.getDataBackupById(id);

		request.setAttribute("map", map);

		return "/admin/dataBackup/viewDataBackup.jsp";
	}

	/**
	 * 分页搜索数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchDataBackup")
	public String searchDataBackup(HttpServletRequest request,
			String currentPageNum) throws IllegalAccessException,
			InvocationTargetException {

		DataBackup dataBackup = new DataBackup();
		BeanUtil.populate(dataBackup, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.dataBackupService.searchDataBackup(page, dataBackup);

		request.setAttribute("page", page);
		request.setAttribute("bean", dataBackup);

		return "/admin/dataBackup/listDataBackup.jsp";
	}

	/**
	 * 导出数据备份数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportDataBackupList")
	public String exportDataBackupList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要数据备份的数据备份列表
		DataBackup dataBackup = new DataBackup();
		BeanUtil.populate(dataBackup, request.getParameterMap());
		List<DataBackup> excelContent = this.dataBackupService
				.searchDataBackupForList(dataBackup);

		String[] columnNames = new String[] { "Name", "Path", "CreateUserId",
				"CreateUserName", "CreateTime" };

		String titleName = "dataBackupList.xls";// 不创建说明;
		String sheetName = "数据备份列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.dataBackupService.exportDataBackupList(request, excelContent,
				columnNames, sheetName, outputStream);

		return null;
	}

}
