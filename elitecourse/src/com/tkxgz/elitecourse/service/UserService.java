package com.tkxgz.elitecourse.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tkxgz.elitecourse.core.constant.CommonConstants;

import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.dao.UserDao;
import com.tkxgz.elitecourse.bean.User;

/**
 * 用户Service类
 * 
 * @author Soyi Yao
 */
@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	/**
	 * 分页查询用户列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 用户分页数据
	 */
	public Page listUser(Page page) {

		return this.userDao.listUser(page);
	}

	/**
	 * 添加用户
	 * 
	 * @author Soyi Yao
	 * @param user
	 *        用户bean
	 * @return
	 */
	public int addUser(User user) {

		return this.userDao.addUser(user);
	}

	/**
	 * 删除用户
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的用户id
	 * @return 影响行数
	 */
	public int deleteUserById(String id) {

		return this.userDao.deleteUserById(id);
	}

	/**
	 * 批量删除用户
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        用户id数组
	 * @return 影响行数
	 */
	public int batchDeleteUser(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteUserById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据用户id查询用户详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        用户id
	 * @return 用户Map
	 */
	public Map getUserById(String id) {

		return this.userDao.getUserById(id);
	}

	/**
	 * 根据用户id查询用户实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public User findUserById(String id) {

		return this.userDao.findUserById(id);
	}

	/**
	 * 更新用户
	 * 
	 * @author Soyi Yao
	 * @param user
	 *        用户bean
	 * @return 影响行数
	 */
	public int updateUser(User user) {

		return this.userDao.updateUser(user);
	}

	/**
	 * 分页搜索用户列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param user
	 *        用户bean
	 * @return 分页用户列表
	 */
	public Page searchUser(Page page, User user) {

		return this.userDao.searchUser(page, user);
	}

	/**
	 * 列出所有用户列表
	 * 
	 * @author Soyi Yao
	 * @return 用户列表
	 */
	public List<User> listAllUser() {
		return this.userDao.listAllUser();

	}

	/**
	 * 搜索用户列表
	 * 
	 * @author Soyi Yao
	 * @param user
	 *        User实体
	 * @return 用户列表
	 */
	public List<User> searchUserForList(User user) {

		return this.userDao.searchUserForList(user);
	}

	/**
	 * excel导出用户列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的用户内容列表
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
	public void exportUserList(HttpServletRequest request,
			List<User> excelContent, String[] columnNames, String sheetName,
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

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getRealName();
			cell.setCellValue(cellValue);

			// 新建一列

			/*
			 * cell = row.createCell(cellNo++);
			 * cell.setCellStyle(contentStyle);
			 * cellValue = (String) excelContent.get(i).getPassword();
			 * cell.setCellValue(cellValue);
			 */

			// 新建一列

			/*
			 * cell = row.createCell(cellNo++);
			 * cell.setCellStyle(contentStyle);
			 * cellValue = (String) excelContent.get(i).getStatus();
			 * cell.setCellValue(cellValue);
			 */

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getGender();
			if ("male".equals(cellValue)) {
				cellValue = "男";
			} else {
				cellValue = "女";
			}
			cell.setCellValue(cellValue);

			// 新建一列

			/*
			 * cell = row.createCell(cellNo++);
			 * cell.setCellStyle(contentStyle);
			 * cellValue = (String) excelContent.get(i).getOrigin();
			 * cell.setCellValue(cellValue);
			 */

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getBirthDate();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getGroupId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getEmail();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getTelephone();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getIsAdmin();
			if ("manager".equalsIgnoreCase(cellValue)) {
				cellValue = "管理员";
			} else {
				cellValue = "学生";
			}
			cell.setCellValue(cellValue);

			// 新建一列

			/*
			 * cell = row.createCell(cellNo++);
			 * cell.setCellStyle(contentStyle);
			 * cellValue = (String) excelContent.get(i).getIsLocked();
			 * cell.setCellValue(cellValue);
			 */

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getAge();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getRole();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getClassesId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateUserName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateUserId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateTime();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getUpdateUserName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getUpdateUserId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getUpdateTime();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getRemark();
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
	 * 获取t_user表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_user表记录总数
	 */
	public int getTotalRecords() {
		return this.userDao.getTotalRecords();
	}

}
