package com.tkxgz.elitecourse.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tkxgz.elitecourse.core.constant.CommonConstants;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.dao.UserGroupDao;
import com.tkxgz.elitecourse.bean.UserGroup;

/**
 * 用户组Service类
 * 
 * @author Soyi Yao
 */
@Service
public class UserGroupService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserGroupDao userGroupDao;

	/**
	 * 分页查询用户组列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 用户组分页数据
	 */
	public Page listUserGroup(Page page) {

		return this.userGroupDao.listUserGroup(page);
	}

	/**
	 * 添加用户组
	 * 
	 * @author Soyi Yao
	 * @param userGroup
	 *        用户组bean
	 * @return
	 */
	public int addUserGroup(UserGroup userGroup) {

		return this.userGroupDao.addUserGroup(userGroup);
	}

	/**
	 * 删除用户组
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的用户组id
	 * @return 影响行数
	 */
	public int deleteUserGroupById(String id) {

		return this.userGroupDao.deleteUserGroupById(id);
	}

	/**
	 * 批量删除用户组
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        用户组id数组
	 * @return 影响行数
	 */
	public int batchDeleteUserGroup(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteUserGroupById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据用户组id查询用户组详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户组id
	 * @return 用户组Map
	 */
	public Map getUserGroupById(String id) {

		return this.userGroupDao.getUserGroupById(id);
	}

	/**
	 * 根据用户组id查询用户组实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public UserGroup findUserGroupById(String id) {

		return this.userGroupDao.findUserGroupById(id);
	}

	/**
	 * 更新用户组
	 * 
	 * @author Soyi Yao
	 * @param userGroup
	 *        用户组bean
	 * @return 影响行数
	 */
	public int updateUserGroup(UserGroup userGroup) {

		return this.userGroupDao.updateUserGroup(userGroup);
	}


	/**
	 * 分页搜索用户组列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param userGroup
	 *        用户组bean
	 * @return 分页用户组列表
	 */
	public Page searchUserGroup(Page page, UserGroup userGroup) {

		return this.userGroupDao.searchUserGroup(page, userGroup);
	}
	
	/**
	 * 列出所有用户组列表
	 * 
	 * @author Soyi Yao
	 * @return 用户组列表
	 */
	public List<UserGroup> listAllUserGroup() {
		return this.userGroupDao.listAllUserGroup();

	}
	
	/**
	 * 搜索用户组列表
	 * 
	 * @author Soyi Yao
	 * @param userGroup
	 *        UserGroup实体
	 * @return 用户组列表
	 */
	public List<UserGroup> searchUserGroupForList(UserGroup userGroup) {

		return this.userGroupDao.searchUserGroupForList(userGroup);
	}
	
	/**
	 * excel导出用户组列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的用户组内容列表
	 * @param columnNames
	 *        导出excel标题数组
	 * @param sheetName
	 *        excel的Sheet名字
	 * @param outputStream
	 *        OutputStream
	 * @throws IOException
	 *         IO异常
	 */
	@SuppressWarnings("deprecation")
	public void exportUserGroupList(HttpServletRequest request,
			List<UserGroup> excelContent, String[] columnNames, String sheetName,
			OutputStream outputStream) throws IOException {

		HSSFWorkbook wb = new HSSFWorkbook();

		// 标题列的字体样式
		HSSFFont headerFont = wb.createFont();
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFFont.BOLDWEIGHT_NORMAL);

		// 标题列样式
		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		headerStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setHidden(true);

		// 内容列的样式
		HSSFCellStyle contentStyle = wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		HSSFSheet sheet = wb.createSheet(sheetName);

		// 创建列名称
		HSSFRow headRow = sheet.createRow((short) 0);//
		headRow.setHeightInPoints(20.120f);

		// 生成标题列
		for (int i = 0; i < columnNames.length; i++) {
			HSSFCell cell = headRow.createCell((short) i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(columnNames[i]);
		}

		// 生成内容列
		for (int i = 0; i < excelContent.size(); i++) {
			short cellNo = 0;

			// 建立新行
			HSSFRow row = sheet.createRow((short) i + 1);

			HSSFCell cell = null; // 单元格
			String cellValue = ""; // 单元格值

			//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getUserId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getGroupId();
                          cell.setCellValue(cellValue);



		}

		request.setCharacterEncoding(CommonConstants.CHARACTER_GBK);

		MessageFormat.format("{0}/{1}.xls", request.getRealPath("exportExcel"),
				Long.toString(System.currentTimeMillis()));

		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
	
	 /**
	 * 获取t_user_group表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_user_group表记录总数
	 */
	public int getTotalRecords() {
		return this.userGroupDao.getTotalRecords();
	}
}
