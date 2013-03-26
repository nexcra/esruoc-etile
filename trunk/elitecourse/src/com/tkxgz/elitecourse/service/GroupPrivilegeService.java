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
import com.tkxgz.elitecourse.dao.GroupPrivilegeDao;
import com.tkxgz.elitecourse.bean.GroupPrivilege;

/**
 * 组权限关联Service类
 * 
 * @author Soyi Yao
 */
@Service
public class GroupPrivilegeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GroupPrivilegeDao groupPrivilegeDao;

	/**
	 * 分页查询组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 组权限关联分页数据
	 */
	public Page listGroupPrivilege(Page page) {

		return this.groupPrivilegeDao.listGroupPrivilege(page);
	}

	/**
	 * 添加组权限关联
	 * 
	 * @author Soyi Yao
	 * @param groupPrivilege
	 *        组权限关联bean
	 * @return
	 */
	public int addGroupPrivilege(GroupPrivilege groupPrivilege) {

		return this.groupPrivilegeDao.addGroupPrivilege(groupPrivilege);
	}

	/**
	 * 删除组权限关联
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的组权限关联id
	 * @return 影响行数
	 */
	public int deleteGroupPrivilegeById(String id) {

		return this.groupPrivilegeDao.deleteGroupPrivilegeById(id);
	}

	/**
	 * 批量删除组权限关联
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        组权限关联id数组
	 * @return 影响行数
	 */
	public int batchDeleteGroupPrivilege(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteGroupPrivilegeById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据组权限关联id查询组权限关联详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        组权限关联id
	 * @return 组权限关联Map
	 */
	public Map getGroupPrivilegeById(String id) {

		return this.groupPrivilegeDao.getGroupPrivilegeById(id);
	}

	/**
	 * 根据组权限关联id查询组权限关联实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public GroupPrivilege findGroupPrivilegeById(String id) {

		return this.groupPrivilegeDao.findGroupPrivilegeById(id);
	}

	/**
	 * 更新组权限关联
	 * 
	 * @author Soyi Yao
	 * @param groupPrivilege
	 *        组权限关联bean
	 * @return 影响行数
	 */
	public int updateGroupPrivilege(GroupPrivilege groupPrivilege) {

		return this.groupPrivilegeDao.updateGroupPrivilege(groupPrivilege);
	}


	/**
	 * 分页搜索组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param groupPrivilege
	 *        组权限关联bean
	 * @return 分页组权限关联列表
	 */
	public Page searchGroupPrivilege(Page page, GroupPrivilege groupPrivilege) {

		return this.groupPrivilegeDao.searchGroupPrivilege(page, groupPrivilege);
	}
	
	/**
	 * 列出所有组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @return 组权限关联列表
	 */
	public List<GroupPrivilege> listAllGroupPrivilege() {
		return this.groupPrivilegeDao.listAllGroupPrivilege();

	}
	
	/**
	 * 搜索组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param groupPrivilege
	 *        GroupPrivilege实体
	 * @return 组权限关联列表
	 */
	public List<GroupPrivilege> searchGroupPrivilegeForList(GroupPrivilege groupPrivilege) {

		return this.groupPrivilegeDao.searchGroupPrivilegeForList(groupPrivilege);
	}
	
	/**
	 * excel导出组权限关联列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的组权限关联内容列表
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
	public void exportGroupPrivilegeList(HttpServletRequest request,
			List<GroupPrivilege> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getGroupId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getPrivilegeId();
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
	 * 获取t_group_privilege表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_group_privilege表记录总数
	 */
	public int getTotalRecords() {
		return this.groupPrivilegeDao.getTotalRecords();
	}
}
