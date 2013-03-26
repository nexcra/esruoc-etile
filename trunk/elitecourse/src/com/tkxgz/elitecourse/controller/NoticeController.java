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

import com.tkxgz.elitecourse.bean.Notice;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.NoticeService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;



/**
 * 公告Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/notice.do")
public class NoticeController {

	private static final String MODULE_NAME = "公告管理";
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private NoticeService noticeService;
	
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

		return this.listNotice(request, currentPageNum);
	}

	/**
	 * 查询公告列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listNotice")
	public String listNotice(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.noticeService.listNotice(page);

		request.setAttribute("page", page);
		
		

		return "/admin/notice/listNotice.jsp";
	}

	/**
	 * 转到新增公告页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddNotice")
	public String toAddNotice(HttpServletRequest request) {
		
		
		
		return "/admin/notice/addNotice.jsp";
	}

	/**
	 * 新增公告
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addNotice")
	public String addNotice(HttpServletRequest request) throws Exception {
		
		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/notice.do?action=listNotice";

		// 获取表单参数
		Notice notice = new Notice();
		BeanUtil.populate(notice, request.getParameterMap());
		
		
		// 设置操作管理员
notice.setCreateUserId(admin.getId());notice.setCreateUserName(admin.getName());

		int affectedRows = this.noticeService.addNotice(notice);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddNotice(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加公告");

		this.logService.addLog(log);
		
		return result;
	}

	/**
	 * 删除公告
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteNotice")
	public String deleteNotice(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.noticeService.batchDeleteNotice(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");
		
	
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除公告");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改公告页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateNotice")
	public String toUpdateNotice(HttpServletRequest request, String id) {

		Map map = this.noticeService.getNoticeById(id);

		request.setAttribute("map", map);
		
		

		return "/admin/notice/updateNotice.jsp";
	}

	/**
	 * 修改公告
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
	@RequestMapping(params = "action=updateNotice")
	public String updateNotice(HttpServletRequest request, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());
		
		String result = "redirect:/admin/notice.do?action=listNotice";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Notice notice = this.noticeService.findNoticeById(id);
		BeanUtil.populate(notice, request.getParameterMap());

		
		
		int affectedRows = this.noticeService.updateNotice(notice);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddNotice(request);
		}
		
		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改公告");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看公告
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewNotice")
	public String viewNotice(HttpServletRequest request, String id) {

		Map map = this.noticeService.getNoticeById(id);

		request.setAttribute("map", map);

		return "/admin/notice/viewNotice.jsp";
	}

	/**
	 * 分页搜索公告列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchNotice")
	public String searchNotice(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Notice notice = new Notice();
		BeanUtil.populate(notice, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.noticeService.searchNotice(page, notice);
		
		
		
		request.setAttribute("page", page);
		request.setAttribute("bean", notice);

		return "/admin/notice/listNotice.jsp";
	}
	
	/**
	 * 导出公告数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportNoticeList")
	public String exportNoticeList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {
			
		// 查询需要公告的公告列表
		Notice notice = new Notice();
		BeanUtil.populate(notice, request.getParameterMap());
		List<Notice> excelContent = this.noticeService.searchNoticeForList(notice);

		String[] columnNames = new String[] { "Title" ,"Content" ,"CreateTime" ,"CreateUserName" ,"CreateUserId" };

		String titleName = "noticeList.xls";// 不创建说明;
		String sheetName = "公告列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.noticeService.exportNoticeList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}
	

}
