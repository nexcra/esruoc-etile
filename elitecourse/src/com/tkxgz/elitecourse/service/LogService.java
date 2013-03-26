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
import com.tkxgz.elitecourse.dao.LogDao;
import com.tkxgz.elitecourse.bean.Log;

/**
 * 日志Service类
 * 
 * @author Soyi Yao
 */
@Service
public class LogService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LogDao logDao;

	/**
	 * 分页查询日志列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 日志分页数据
	 */
	public Page listLog(Page page) {

		return this.logDao.listLog(page);
	}

	/**
	 * 添加日志
	 * 
	 * @author Soyi Yao
	 * @param log
	 *        日志bean
	 * @return
	 */
	public int addLog(Log log) {

		return this.logDao.addLog(log);
	}

	/**
	 * 删除日志
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的日志id
	 * @return 影响行数
	 */
	public int deleteLogById(String id) {

		return this.logDao.deleteLogById(id);
	}

	/**
	 * 批量删除日志
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        日志id数组
	 * @return 影响行数
	 */
	public int batchDeleteLog(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteLogById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据日志id查询日志详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        日志id
	 * @return 日志Map
	 */
	public Map getLogById(String id) {

		return this.logDao.getLogById(id);
	}

	/**
	 * 根据日志id查询日志实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Log findLogById(String id) {

		return this.logDao.findLogById(id);
	}

	/**
	 * 更新日志
	 * 
	 * @author Soyi Yao
	 * @param log
	 *        日志bean
	 * @return 影响行数
	 */
	public int updateLog(Log log) {

		return this.logDao.updateLog(log);
	}

	/**
	 * 分页搜索日志列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param log
	 *        日志bean
	 * @return 分页日志列表
	 */
	public Page searchLog(Page page, Log log) {

		return this.logDao.searchLog(page, log);
	}

	/**
	 * 列出所有日志列表
	 * 
	 * @author Soyi Yao
	 * @return 日志列表
	 */
	public List<Log> listAllLog() {
		return this.logDao.listAllLog();

	}

	/**
	 * 搜索日志列表
	 * 
	 * @author Soyi Yao
	 * @param log
	 *        Log实体
	 * @return 日志列表
	 */
	public List<Log> searchLogForList(Log log) {

		return this.logDao.searchLogForList(log);
	}

	/**
	 * excel导出日志列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的日志内容列表
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
	public void exportLogList(HttpServletRequest request,
			List<Log> excelContent, String[] columnNames, String sheetName,
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
			cellValue = (String) excelContent.get(i).getLogType();
			if ("login".equalsIgnoreCase(cellValue)) {
				cellValue = "登录";

			} else if ("logout".equalsIgnoreCase(cellValue)) {

				cellValue = "注销";
			} else if ("insert".equalsIgnoreCase(cellValue)) {
				cellValue = "新增";

			} else if ("update".equalsIgnoreCase(cellValue)) {
				cellValue = "更新";

			} else if ("delete".equalsIgnoreCase(cellValue)) {
				cellValue = "删除";

			}
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getUserName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getModule();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getDescription();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getIpAddress();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateTime();
			cell.setCellValue(cellValue);

		}

		request.setCharacterEncoding(CommonConstants.CHARACTER_GBK);

		MessageFormat.format("{0}/{1}.xls", request.getRealPath("exportExcel"),
				Long.toString(System.currentTimeMillis()));

		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
}
