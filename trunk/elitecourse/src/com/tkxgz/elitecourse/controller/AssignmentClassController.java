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

import com.tkxgz.elitecourse.bean.AssignmentClass;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.AssignmentClassService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.bean.Classes;import com.tkxgz.elitecourse.service.ClassesService;


/**
 * 作业分类Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/assignmentClass.do")
public class AssignmentClassController {

	private static final String MODULE_NAME = "作业分类管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private AssignmentClassService assignmentClassService;
	
	@Autowired
private UserService userService;


	 @Autowired private ClassesService classesService;

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

		return this.listAssignmentClass(request, currentPageNum);
	}

	/**
	 * 查询作业分类列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listAssignmentClass")
	public String listAssignmentClass(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.assignmentClassService.listAssignmentClass(page);

		request.setAttribute("page", page);
		
		List<Classes> classesList = this.classesService.listAllClasses();request.setAttribute("classesList", classesList);

		return "/admin/assignmentClass/listAssignmentClass.jsp";
	}

	/**
	 * 转到新增作业分类页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddAssignmentClass")
	public String toAddAssignmentClass(HttpServletRequest request) {
		
		List<Classes> classesList = this.classesService.listAllClasses();request.setAttribute("classesList", classesList);
		
		return "/admin/assignmentClass/addAssignmentClass.jsp";
	}

	/**
	 * 新增作业分类
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addAssignmentClass")
	public String addAssignmentClass(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/assignmentClass.do?action=listAssignmentClass";

		// 获取表单参数
		AssignmentClass assignmentClass = new AssignmentClass();
		BeanUtil.populate(assignmentClass, request.getParameterMap());
		
		
		// 设置操作管理员
assignmentClass.setCreateUserId(admin.getId());assignmentClass.setCreateUserName(admin.getName());

		int affectedRows = this.assignmentClassService.addAssignmentClass(assignmentClass);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddAssignmentClass(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加作业分类");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除作业分类
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteAssignmentClass")
	public String deleteAssignmentClass(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.assignmentClassService.batchDeleteAssignmentClass(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除作业分类");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改作业分类页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateAssignmentClass")
	public String toUpdateAssignmentClass(HttpServletRequest request, String id) {

		Map map = this.assignmentClassService.getAssignmentClassById(id);

		request.setAttribute("map", map);
		
		List<Classes> classesList = this.classesService.listAllClasses();request.setAttribute("classesList", classesList);

		return "/admin/assignmentClass/updateAssignmentClass.jsp";
	}

	/**
	 * 修改作业分类
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
	@RequestMapping(params = "action=updateAssignmentClass")
	public String updateAssignmentClass(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/assignmentClass.do?action=listAssignmentClass";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		AssignmentClass assignmentClass = this.assignmentClassService.findAssignmentClassById(id);
		BeanUtil.populate(assignmentClass, request.getParameterMap());

		
		
		int affectedRows = this.assignmentClassService.updateAssignmentClass(assignmentClass);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddAssignmentClass(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改作业分类");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看作业分类
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewAssignmentClass")
	public String viewAssignmentClass(HttpServletRequest request, String id) {

		Map map = this.assignmentClassService.getAssignmentClassById(id);

		request.setAttribute("map", map);

		return "/admin/assignmentClass/viewAssignmentClass.jsp";
	}

	/**
	 * 分页搜索作业分类列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchAssignmentClass")
	public String searchAssignmentClass(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		AssignmentClass assignmentClass = new AssignmentClass();
		BeanUtil.populate(assignmentClass, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.assignmentClassService.searchAssignmentClass(page, assignmentClass);
		
		List<Classes> classesList = this.classesService.listAllClasses();request.setAttribute("classesList", classesList);
		
		request.setAttribute("page", page);
		request.setAttribute("bean", assignmentClass);

		return "/admin/assignmentClass/listAssignmentClass.jsp";
	}
	
	/**
	 * 导出作业分类数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportAssignmentClassList")
	public String exportAssignmentClassList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要作业分类的作业分类列表
		AssignmentClass assignmentClass = new AssignmentClass();
		BeanUtil.populate(assignmentClass, request.getParameterMap());
		List<AssignmentClass> excelContent = this.assignmentClassService.searchAssignmentClassForList(assignmentClass);

		String[] columnNames = new String[] { "Name" ,"Description" ,"OrderNumber" ,"ClassesId" ,"ClassesName" ,"CreateUserId" ,"StartTime" ,"EndTime" ,"CreateUserName" ,"CreateTime" };

		String titleName = "assignmentClassList.xls";// 不创建说明;
		String sheetName = "作业分类列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.assignmentClassService.exportAssignmentClassList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
