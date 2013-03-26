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

import com.tkxgz.elitecourse.bean.CourseIntroduction;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.CourseIntroductionService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 课程简介Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/courseIntroduction.do")
public class CourseIntroductionController {

	private static final String MODULE_NAME = "课程简介管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private CourseIntroductionService courseIntroductionService;
	
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

		return this.listCourseIntroduction(request, currentPageNum);
	}

	/**
	 * 查询课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listCourseIntroduction")
	public String listCourseIntroduction(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.courseIntroductionService.listCourseIntroduction(page);

		request.setAttribute("page", page);
		
		

		return "/admin/courseIntroduction/listCourseIntroduction.jsp";
	}

	/**
	 * 转到新增课程简介页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddCourseIntroduction")
	public String toAddCourseIntroduction(HttpServletRequest request) {
		
		
		
		return "/admin/courseIntroduction/addCourseIntroduction.jsp";
	}

	/**
	 * 新增课程简介
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addCourseIntroduction")
	public String addCourseIntroduction(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/courseIntroduction.do?action=listCourseIntroduction";

		// 获取表单参数
		CourseIntroduction courseIntroduction = new CourseIntroduction();
		BeanUtil.populate(courseIntroduction, request.getParameterMap());
		
		
		

		int affectedRows = this.courseIntroductionService.addCourseIntroduction(courseIntroduction);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddCourseIntroduction(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加课程简介");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除课程简介
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteCourseIntroduction")
	public String deleteCourseIntroduction(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.courseIntroductionService.batchDeleteCourseIntroduction(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除课程简介");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改课程简介页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateCourseIntroduction")
	public String toUpdateCourseIntroduction(HttpServletRequest request, String id) {

		Map map = this.courseIntroductionService.getCourseIntroductionById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/courseIntroduction/updateCourseIntroduction.jsp";
	}

	/**
	 * 修改课程简介
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
	@RequestMapping(params = "action=updateCourseIntroduction")
	public String updateCourseIntroduction(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/courseIntroduction.do?action=listCourseIntroduction";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		CourseIntroduction courseIntroduction = this.courseIntroductionService.findCourseIntroductionById(id);
		BeanUtil.populate(courseIntroduction, request.getParameterMap());

		
		
		int affectedRows = this.courseIntroductionService.updateCourseIntroduction(courseIntroduction);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddCourseIntroduction(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改课程简介");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看课程简介
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewCourseIntroduction")
	public String viewCourseIntroduction(HttpServletRequest request, String id) {

		Map map = this.courseIntroductionService.getCourseIntroductionById(id);

		request.setAttribute("map", map);

		return "/admin/courseIntroduction/viewCourseIntroduction.jsp";
	}

	/**
	 * 分页搜索课程简介列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchCourseIntroduction")
	public String searchCourseIntroduction(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		CourseIntroduction courseIntroduction = new CourseIntroduction();
		BeanUtil.populate(courseIntroduction, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.courseIntroductionService.searchCourseIntroduction(page, courseIntroduction);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", courseIntroduction);

		return "/admin/courseIntroduction/listCourseIntroduction.jsp";
	}
	
	/**
	 * 导出课程简介数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportCourseIntroductionList")
	public String exportCourseIntroductionList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要课程简介的课程简介列表
		CourseIntroduction courseIntroduction = new CourseIntroduction();
		BeanUtil.populate(courseIntroduction, request.getParameterMap());
		List<CourseIntroduction> excelContent = this.courseIntroductionService.searchCourseIntroductionForList(courseIntroduction);

		String[] columnNames = new String[] { "CourseHostName" ,"IntroContent" ,"Pic" };

		String titleName = "courseIntroductionList.xls";// 不创建说明;
		String sheetName = "课程简介列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.courseIntroductionService.exportCourseIntroductionList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
