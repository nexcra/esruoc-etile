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
import com.tkxgz.elitecourse.dao.SystemConfigDao;
import com.tkxgz.elitecourse.bean.SystemConfig;

/**
 * 系统配置Service类
 * 
 * @author Soyi Yao
 */
@Service
public class SystemConfigService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemConfigDao systemConfigDao;

	/**
	 * 分页查询系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 系统配置分页数据
	 */
	public Page listSystemConfig(Page page) {

		return this.systemConfigDao.listSystemConfig(page);
	}

	/**
	 * 添加系统配置
	 * 
	 * @author Soyi Yao
	 * @param systemConfig
	 *        系统配置bean
	 * @return
	 */
	public int addSystemConfig(SystemConfig systemConfig) {

		return this.systemConfigDao.addSystemConfig(systemConfig);
	}

	/**
	 * 删除系统配置
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的系统配置id
	 * @return 影响行数
	 */
	public int deleteSystemConfigById(String id) {

		return this.systemConfigDao.deleteSystemConfigById(id);
	}

	/**
	 * 批量删除系统配置
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        系统配置id数组
	 * @return 影响行数
	 */
	public int batchDeleteSystemConfig(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteSystemConfigById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据系统配置id查询系统配置详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        系统配置id
	 * @return 系统配置Map
	 */
	public Map getSystemConfigById(String id) {

		return this.systemConfigDao.getSystemConfigById(id);
	}

	/**
	 * 根据系统配置id查询系统配置实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public SystemConfig findSystemConfigById(String id) {

		return this.systemConfigDao.findSystemConfigById(id);
	}

	/**
	 * 更新系统配置
	 * 
	 * @author Soyi Yao
	 * @param systemConfig
	 *        系统配置bean
	 * @return 影响行数
	 */
	public int updateSystemConfig(SystemConfig systemConfig) {

		return this.systemConfigDao.updateSystemConfig(systemConfig);
	}


	/**
	 * 分页搜索系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param systemConfig
	 *        系统配置bean
	 * @return 分页系统配置列表
	 */
	public Page searchSystemConfig(Page page, SystemConfig systemConfig) {

		return this.systemConfigDao.searchSystemConfig(page, systemConfig);
	}
	
	/**
	 * 列出所有系统配置列表
	 * 
	 * @author Soyi Yao
	 * @return 系统配置列表
	 */
	public List<SystemConfig> listAllSystemConfig() {
		return this.systemConfigDao.listAllSystemConfig();

	}
	
	/**
	 * 搜索系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param systemConfig
	 *        SystemConfig实体
	 * @return 系统配置列表
	 */
	public List<SystemConfig> searchSystemConfigForList(SystemConfig systemConfig) {

		return this.systemConfigDao.searchSystemConfigForList(systemConfig);
	}
	
	/**
	 * excel导出系统配置列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的系统配置内容列表
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
	public void exportSystemConfigList(HttpServletRequest request,
			List<SystemConfig> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getSiteName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCopyright();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getSiteOwner();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getContactPhone();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getContactEmail();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getStatus();
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
	 * 获取t_system_config表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_system_config表记录总数
	 */
	public int getTotalRecords() {
		return this.systemConfigDao.getTotalRecords();
	}
}
