package com.tkxgz.elitecourse.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxgz.elitecourse.bean.Log;
import com.tkxgz.elitecourse.bean.User;
import com.tkxgz.elitecourse.bean.Video;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.core.util.BeanUtil;
import com.tkxgz.elitecourse.core.util.PageUtil;
import com.tkxgz.elitecourse.service.DictService;
import com.tkxgz.elitecourse.service.LogService;
import com.tkxgz.elitecourse.service.UserService;
import com.tkxgz.elitecourse.service.VideoService;

/**
 * 视频Controller类
 * 
 * @author Soyi Yao
 */
@Controller(value = "/admin/video.do")
public class VideoController {

	private static final String MODULE_NAME = "视频管理";

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogService logService;

	@Autowired
	private VideoService videoService;

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

		return this.listVideo(request, currentPageNum);
	}

	/**
	 * 查询视频列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 */
	@RequestMapping(params = "action=listVideo")
	public String listVideo(HttpServletRequest request, String currentPageNum) {

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.videoService.listVideo(page);

		request.setAttribute("page", page);

		return "/admin/video/listVideo.jsp";
	}

	/**
	 * 转到新增视频页
	 * 
	 * @author Soyi Yao
	 * @return
	 */

	@RequestMapping(params = "action=toAddVideo")
	public String toAddVideo(HttpServletRequest request) {

		return "/admin/video/addVideo.jsp";
	}

	/**
	 * 新增视频
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=addVideo")
	public String addVideo(HttpServletRequest request) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/video.do?action=listVideo";

		// 获取表单参数
		Video video = new Video();
		BeanUtil.populate(video, request.getParameterMap());

		// 设置操作管理员
		video.setCreateUserId(admin.getId());
		video.setCreateUserName(admin.getName());

		String name = "";// 文件名
		String source = "";// 文件来源
		String type = "";// 文件类型
		String description = "";// 文件描述
		String fileName = "";// 上传文件
		String randomName = RandomStringUtils.randomAlphanumeric(15);// 随机名字
		FileItem uploadFileItem = null; // 用于后面上传

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查输入请求是否为multipart表单数据。
		if (isMultipart == true) {
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> itr = items.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				// 检查当前项目是普通表单项目还是上传文件。
				if (item.isFormField()) {// 如果是普通表单项目，显示表单内容。
					String fieldName = item.getFieldName();
					if (fieldName.equals("name")) {
						name = item.getString("UTF-8");
					} else if (fieldName.equals("source")) {
						source = item.getString("UTF-8");
					} else if (fieldName.equals("type")) {
						type = item.getString("UTF-8");
					} else if (fieldName.equals("description")) {
						description = item.getString("UTF-8");
					}
				} else {// 如果是上传文件，显示文件名。

					fileName = item.getName();
					uploadFileItem = item;

				}
			}

			// 如果直接取item.getName得到文件名，在IE中上传的话，会得出本地全路径"C:\test.flv"，
			// 而其他浏览器得出"test.flv",但我们要的是"test"部分，因此，需要去掉前面的本地盘符信息
			String fileBaseName = FilenameUtils.getBaseName(fileName);
			String fileExtension = FilenameUtils.getExtension(fileName);

			fileName = fileBaseName + "." + fileExtension;

			String uploadFilePath = this.dictService.getDictByCode(
					"uploadFilePath").getDictValue()
					+ "/video/";

			final String tempFileName = randomName + "." + fileExtension;

			video.setPath("/" + uploadFilePath + tempFileName);

			String tempPath = request.getSession().getServletContext()
					.getRealPath(uploadFilePath);

			File file = new File(tempPath);

			// 上传路径不存在则创建,存在则直接返回，忽略
			file.mkdirs();

			File fullFile = new File(tempFileName);
			File savedFile = new File(tempPath, fullFile.getName());

			uploadFileItem.write(savedFile);

		}

		// 设置Video相关参数
		video.setName(name);
		video.setDescription(description);
		video.setSource(source);
		video.setType(type);
		video.setRandomName(randomName);// 十五位随机数

		int affectedRows = this.videoService.addVideo(video);// 新增

		// 新增失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "新增不成功，请检查");
			return this.toAddVideo(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_INSERT);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("添加视频");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 删除视频
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=deleteVideo")
	public String deleteVideo(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		// 获取需要删除的id数组
		String[] ids = id.split(",");

		String uploadFilePath = request.getSession().getServletContext()
				.getRealPath("/");

		// 批量删除数据,包括媒体数据
		int result = this.videoService.batchDeleteVideo(ids, uploadFilePath);

		response.getWriter().write(" 成功删除 " + result + "条记录");

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_DELETE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("删除视频");

		this.logService.addLog(log);
		return null;
	}

	/**
	 * 转到修改视频页
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=toUpdateVideo")
	public String toUpdateVideo(HttpServletRequest request, String id) {

		Map map = this.videoService.getVideoById(id);

		request.setAttribute("map", map);

		return "/admin/video/updateVideo.jsp";
	}

	/**
	 * 修改视频
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
	@RequestMapping(params = "action=updateVideo")
	public String updateVideo(HttpServletRequest request, String id)
			throws Exception {

		// 获取操作管理员信息
		User admin = (User) request.getSession().getAttribute("admin");
		admin = this.userService.findUserById(admin.getId());

		String result = "redirect:/admin/video.do?action=listVideo";

		// 根据id查询数据库详细，然后根据所修改的字段进行更改，最后保存修改的值
		Video video = this.videoService.findVideoById(id);
		BeanUtil.populate(video, request.getParameterMap());

		int affectedRows = this.videoService.updateVideo(video);// 修改

		// 修改失败处理
		if (affectedRows < 1) {
			request.setAttribute("error", "修改不成功，请检查");
			return this.toAddVideo(request);
		}

		Log log = new Log();
		log.setModule(MODULE_NAME);
		log.setUserId(admin.getId());
		log.setUserName(admin.getName());
		log.setLogType(CommonConstants.LOG_TYPE_UPDATE);
		log.setIpAddress(request.getRemoteAddr());
		log.setDescription("修改视频");

		this.logService.addLog(log);

		return result;
	}

	/**
	 * 查看视频
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "action=viewVideo")
	public String viewVideo(HttpServletRequest request, String id) {

		Map map = this.videoService.getVideoById(id);

		request.setAttribute("map", map);

		return "/admin/video/viewVideo.jsp";
	}

	/**
	 * 分页搜索视频列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param currentPageNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(params = "action=searchVideo")
	public String searchVideo(HttpServletRequest request, String currentPageNum)
			throws IllegalAccessException, InvocationTargetException {

		Video video = new Video();
		BeanUtil.populate(video, request.getParameterMap());

		Page page = new Page();
		page = new Page(PageUtil.getPageNum(currentPageNum));

		this.videoService.searchVideo(page, video);

		request.setAttribute("page", page);
		request.setAttribute("bean", video);

		return "/admin/video/listVideo.jsp";
	}

	/**
	 * 导出视频数据
	 * 
	 * @author Soyi Yao
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@RequestMapping(params = "action=exportVideoList")
	public String exportVideoList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IOException {

		// 查询需要视频的视频列表
		Video video = new Video();
		BeanUtil.populate(video, request.getParameterMap());
		List<Video> excelContent = this.videoService.searchVideoForList(video);

		String[] columnNames = new String[] { "Name", "Source", "Type",
				"Description", "Path", "CreateUserId", "CreateUserName",
				"CreateTime" };

		String titleName = "videoList.xls";// 不创建说明;
		String sheetName = "视频列表";

		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(titleName.getBytes(CommonConstants.CHARACTER_GBK),
						CommonConstants.CHARACTER_ISO8859));
		this.videoService.exportVideoList(request, excelContent, columnNames,
				sheetName, outputStream);

		return null;
	}

}
