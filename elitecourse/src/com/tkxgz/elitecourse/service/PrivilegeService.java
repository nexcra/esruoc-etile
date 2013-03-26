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
import com.tkxgz.elitecourse.dao.PrivilegeDao;
import com.tkxgz.elitecourse.bean.Privilege;

/**
 * 权限Service类
 * 
 * @author Soyi Yao
 */
@Service
public class PrivilegeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PrivilegeDao privilegeDao;

	/**
	 * 分页查询权限列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 权限分页数据
	 */
	public Page listPrivilege(Page page) {

		return this.privilegeDao.listPrivilege(page);
	}

	/**
	 * 添加权限
	 * 
	 * @author Soyi Yao
	 * @param privilege
	 *        权限bean
	 * @return
	 */
	public int addPrivilege(Privilege privilege) {

		return this.privilegeDao.addPrivilege(privilege);
	}

	/**
	 * 删除权限
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的权限id
	 * @return 影响行数
	 */
	public int deletePrivilegeById(String id) {

		return this.privilegeDao.deletePrivilegeById(id);
	}

	/**
	 * 批量删除权限
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        权限id数组
	 * @return 影响行数
	 */
	public int batchDeletePrivilege(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deletePrivilegeById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据权限id查询权限详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        权限id
	 * @return 权限Map
	 */
	public Map getPrivilegeById(String id) {

		return this.privilegeDao.getPrivilegeById(id);
	}

	/**
	 * 根据权限id查询权限实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Privilege findPrivilegeById(String id) {

		return this.privilegeDao.findPrivilegeById(id);
	}

	/**
	 * 更新权限
	 * 
	 * @author Soyi Yao
	 * @param privilege
	 *        权限bean
	 * @return 影响行数
	 */
	public int updatePrivilege(Privilege privilege) {

		return this.privilegeDao.updatePrivilege(privilege);
	}


	/**
	 * 分页搜索权限列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param privilege
	 *        权限bean
	 * @return 分页权限列表
	 */
	public Page searchPrivilege(Page page, Privilege privilege) {

		return this.privilegeDao.searchPrivilege(page, privilege);
	}
	
	/**
	 * 列出所有权限列表
	 * 
	 * @author Soyi Yao
	 * @return 权限列表
	 */
	public List<Privilege> listAllPrivilege() {
		return this.privilegeDao.listAllPrivilege();

	}
	
	/**
	 * 搜索权限列表
	 * 
	 * @author Soyi Yao
	 * @param privilege
	 *        Privilege实体
	 * @return 权限列表
	 */
	public List<Privilege> searchPrivilegeForList(Privilege privilege) {

		return this.privilegeDao.searchPrivilegeForList(privilege);
	}
	
	/**
	 * excel导出权限列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的权限内容列表
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
	public void exportPrivilegeList(HttpServletRequest request,
			List<Privilege> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getPrivilegeCode();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getStatus();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateTime();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getRemark();
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
	 * 获取t_privilege表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_privilege表记录总数
	 */
	public int getTotalRecords() {
		return this.privilegeDao.getTotalRecords();
	}
}
