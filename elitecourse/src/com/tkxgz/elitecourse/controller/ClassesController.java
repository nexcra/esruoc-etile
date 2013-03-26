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

import com.tkxgz.elitecourse.bean.Classes;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.ClassesService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 班级Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/classes.do")
public class ClassesController {

	private static final String MODULE_NAME = "班级管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private ClassesService classesService;
	
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

		return this.listClasses(request, currentPageNum);
	}

	/**
	 * 查询班级列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listClasses")
	public String listClasses(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.classesService.listClasses(page);

		request.setAttribute("page", page);
		
		

		return "/admin/classes/listClasses.jsp";
	}

	/**
	 * 转到新增班级页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddClasses")
	public String toAddClasses(HttpServletRequest request) {
		
		
		
		return "/admin/classes/addClasses.jsp";
	}

	/**
	 * 新增班级
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addClasses")
	public String addClasses(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/classes.do?action=listClasses";

		// 获取表单参数
		Classes classes = new Classes();
		BeanUtil.populate(classes, request.getParameterMap());
		
		
		// 设置操作管理员
classes.setCreateUserId(admin.getId());classes.setCreateUserName(admin.getName());

		int affectedRows = this.classesService.addClasses(classes);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddClasses(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加班级");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除班级
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteClasses")
	public String deleteClasses(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.classesService.batchDeleteClasses(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除班级");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改班级页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateClasses")
	public String toUpdateClasses(HttpServletRequest request, String id) {

		Map map = this.classesService.getClassesById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/classes/updateClasses.jsp";
	}

	/**
	 * 修改班级
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
	@RequestMapping(params = "action=updateClasses")
	public String updateClasses(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/classes.do?action=listClasses";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Classes classes = this.classesService.findClassesById(id);
		BeanUtil.populate(classes, request.getParameterMap());

		
		
		int affectedRows = this.classesService.updateClasses(classes);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddClasses(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改班级");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看班级
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewClasses")
	public String viewClasses(HttpServletRequest request, String id) {

		Map map = this.classesService.getClassesById(id);

		request.setAttribute("map", map);

		return "/admin/classes/viewClasses.jsp";
	}

	/**
	 * 分页搜索班级列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchClasses")
	public String searchClasses(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Classes classes = new Classes();
		BeanUtil.populate(classes, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.classesService.searchClasses(page, classes);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", classes);

		return "/admin/classes/listClasses.jsp";
	}
	
	/**
	 * 导出班级数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportClassesList")
	public String exportClassesList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要班级的班级列表
		Classes classes = new Classes();
		BeanUtil.populate(classes, request.getParameterMap());
		List<Classes> excelContent = this.classesService.searchClassesForList(classes);

		String[] columnNames = new String[] { "Name" ,"OrderNumber" ,"Remark" ,"CreateUserName" ,"CreateUserId" ,"CreateTime" };

		String titleName = "classesList.xls";// 不创建说明;
		String sheetName = "班级列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.classesService.exportClassesList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
