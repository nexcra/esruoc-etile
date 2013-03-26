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

import com.tkxgz.elitecourse.bean.Faq;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.FaqService;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.LogService;

/**
 * 常见问题Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/faq.do")
public class FaqController {

	private static final String MODULE_NAME = "常见问题管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private FaqService faqService;

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

		return this.listFaq(request, currentPageNum);
	}

	/**
	 * 查询常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listFaq")
	public String listFaq(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.faqService.listFaq(page);

		request.setAttribute("page", page);

		return "/admin/faq/listFaq.jsp";
	}

	/**
	 * 转到新增常见问题页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddFaq")
	public String toAddFaq(HttpServletRequest request) {

		return "/admin/faq/addFaq.jsp";
	}

	/**
	 * 新增常见问题
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addFaq")
	public String addFaq(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/faq.do?action=listFaq";

		// 获取表单参数
		Faq faq = new Faq();
		BeanUtil.populate(faq, request.getParameterMap());

		// 设置操作管理员
		faq.setCreateUserId(admin.getId());
		faq.setCreateUserName(admin.getName());

		int affectedRows = this.faqService.addFaq(faq);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddFaq(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加常见问题");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除常见问题
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteFaq")
	public String deleteFaq(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		// 批量删除
		int result = this.faqService.batchDeleteFaq(ids);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除常见问题");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改常见问题页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateFaq")
	public String toUpdateFaq(HttpServletRequest request, String id) {

		Map map = this.faqService.getFaqById(id);

		request.setAttribute("map", map);

		return "/admin/faq/updateFaq.jsp";
	}

	/**
	 * 修改常见问题
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
	@RequestMapping(params = "action=updateFaq")
	public String updateFaq(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/faq.do?action=listFaq";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Faq faq = this.faqService.findFaqById(id);
		BeanUtil.populate(faq, request.getParameterMap());

		int affectedRows = this.faqService.updateFaq(faq);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddFaq(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改常见问题");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看常见问题
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewFaq")
	public String viewFaq(HttpServletRequest request, String id) {

		Map map = this.faqService.getFaqById(id);

		request.setAttribute("map", map);

		return "/admin/faq/viewFaq.jsp";
	}

	/**
	 * 分页搜索常见问题列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchFaq")
	public String searchFaq(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Faq faq = new Faq();
		BeanUtil.populate(faq, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.faqService.searchFaq(page, faq);

		request.setAttribute("page", page);
		request.setAttribute("bean", faq);

		return "/admin/faq/listFaq.jsp";
	}

	/**
	 * 导出常见问题数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportFaqList")
	public String exportFaqList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要常见问题的常见问题列表
		Faq faq = new Faq();
		BeanUtil.populate(faq, request.getParameterMap());
		List<Faq> excelContent = this.faqService.searchFaqForList(faq);

		String[] columnNames = new String[] { "Question", "Answer", "Status",
				"Reference", "CreateUserId", "CreateUserName", "CreateTime" };

		String titleName = "faqList.xls";// 不创建说明;
		String sheetName = "常见问题列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.faqService.exportFaqList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}

}
