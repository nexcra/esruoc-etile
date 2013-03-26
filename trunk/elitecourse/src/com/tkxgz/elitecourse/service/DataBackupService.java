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
import com.tkxgz.elitecourse.dao.DataBackupDao;
import com.tkxgz.elitecourse.bean.DataBackup;

/**
 * 数据备份Service类
 * 
 * @author Soyi Yao
 */
@Service
public class DataBackupService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DataBackupDao dataBackupDao;

	/**
	 * 分页查询数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 数据备份分页数据
	 */
	public Page listDataBackup(Page page) {

		return this.dataBackupDao.listDataBackup(page);
	}

	/**
	 * 添加数据备份
	 * 
	 * @author Soyi Yao
	 * @param dataBackup
	 *        数据备份bean
	 * @return
	 */
	public int addDataBackup(DataBackup dataBackup) {

		return this.dataBackupDao.addDataBackup(dataBackup);
	}

	/**
	 * 删除数据备份
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的数据备份id
	 * @return 影响行数
	 */
	public int deleteDataBackupById(String id) {

		return this.dataBackupDao.deleteDataBackupById(id);
	}

	/**
	 * 批量删除数据备份
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        数据备份id数组
	 * @return 影响行数
	 */
	public int batchDeleteDataBackup(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteDataBackupById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据数据备份id查询数据备份详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        数据备份id
	 * @return 数据备份Map
	 */
	public Map getDataBackupById(String id) {

		return this.dataBackupDao.getDataBackupById(id);
	}

	/**
	 * 根据数据备份id查询数据备份实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public DataBackup findDataBackupById(String id) {

		return this.dataBackupDao.findDataBackupById(id);
	}

	/**
	 * 更新数据备份
	 * 
	 * @author Soyi Yao
	 * @param dataBackup
	 *        数据备份bean
	 * @return 影响行数
	 */
	public int updateDataBackup(DataBackup dataBackup) {

		return this.dataBackupDao.updateDataBackup(dataBackup);
	}


	/**
	 * 分页搜索数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param dataBackup
	 *        数据备份bean
	 * @return 分页数据备份列表
	 */
	public Page searchDataBackup(Page page, DataBackup dataBackup) {

		return this.dataBackupDao.searchDataBackup(page, dataBackup);
	}
	
	/**
	 * 列出所有数据备份列表
	 * 
	 * @author Soyi Yao
	 * @return 数据备份列表
	 */
	public List<DataBackup> listAllDataBackup() {
		return this.dataBackupDao.listAllDataBackup();

	}
	
	/**
	 * 搜索数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param dataBackup
	 *        DataBackup实体
	 * @return 数据备份列表
	 */
	public List<DataBackup> searchDataBackupForList(DataBackup dataBackup) {

		return this.dataBackupDao.searchDataBackupForList(dataBackup);
	}
	
	/**
	 * excel导出数据备份列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的数据备份内容列表
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
	public void exportDataBackupList(HttpServletRequest request,
			List<DataBackup> excelContent, String[] columnNames, String sheetName,
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
                         cellValue  =  (String)  excelContent.get(i).getName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getPath();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserId();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateUserName();
                          cell.setCellValue(cellValue);

//  新建一列 

 cell  =  row.createCell(cellNo++); 
                         cell.setCellStyle(contentStyle); 
                         cellValue  =  (String)  excelContent.get(i).getCreateTime();
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
	 * 获取t_data_backup表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_data_backup表记录总数
	 */
	public int getTotalRecords() {
		return this.dataBackupDao.getTotalRecords();
	}
	
	/**
	 * 获取t_data_backup表当前最大id
	 */
	public int getCurrentMaxId() {
		return this.dataBackupDao.getCurrentMaxId();
	}
}
