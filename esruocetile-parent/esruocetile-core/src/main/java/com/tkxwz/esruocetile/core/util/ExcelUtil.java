package com.tkxwz.esruocetile.core.util;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * @author Po Kong
 * @since 29 Jul 2012 11:42:26
 */
/**
 * 导出Excel的工具类，只需给相应参数即可
 * 
 * @author Administrator
 */
public class ExcelUtil {

	/**
	 * 导出Excel的方法
	 * 
	 * @param request
	 *        Web应用中的request对象
	 * @param excelContent
	 *        数据体集合，集合内放置String数组
	 * @param columnNames
	 *        数据列的头，使用数组
	 * @param titleName
	 *        要导出的文件名称
	 * @param fOut
	 *        导出流，Web中使用response得到
	 * @throws IOException
	 *         可能会爆出的异常
	 */
	@SuppressWarnings("deprecation")
	public static void create(HttpServletRequest request,
			List<Map<String, Object>> excelContent, String[] columnNames,
			String titleName, String sheetName, OutputStream fOut)
			throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象
		HSSFSheet sheet = wb.createSheet(sheetName);// 建立新的sheet对象
		// ================合并标题列=========================
		/*
		 * Region region = new Region();
		 * region.setColumnFrom((short) 0);
		 * region.setColumnTo((short) (columnNames.length - 1));
		 * region.setRowFrom((short) 0);
		 * region.setRowTo((short) 0);
		 * sheet.addMergedRegion(region);
		 */
		// ===============================================
		// 标题列列的字体样式
		HSSFFont titleFont = wb.createFont();
		titleFont.setColor(HSSFFont.COLOR_RED);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeight((short) 300);

		// 选择列的字体样式
		HSSFFont headerFont = wb.createFont();
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFFont.BOLDWEIGHT_NORMAL);
		HSSFPrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		sheet.createFreezePane(0, 1);
		sheet.setAutobreaks(true);
		sheet.setDefaultColumnWidth((short) 13.5);
		printSetup.setFitHeight((short) 100);
		printSetup.setFitWidth((short) 180);
		// 标题列样式
		HSSFCellStyle titlestyle = wb.createCellStyle();
		titlestyle.setFont(titleFont);
		titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titlestyle.setFillForegroundColor(HSSFColor.WHITE.index);
		titlestyle.setFillPattern(HSSFCellStyle.SQUARES);
		titlestyle.setLeftBorderColor(HSSFColor.BLACK.index);
		titlestyle.setRightBorderColor(HSSFColor.BLACK.index);
		titlestyle.setTopBorderColor(HSSFColor.BLACK.index);
		titlestyle.setBottomBorderColor(HSSFColor.BLACK.index);
		// titlestyle.setFillPattern((short)300);

		titlestyle.setWrapText(true);
		// 选择列样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(headerFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setHidden(true);
		// 内容列的样式
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style2.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		/*
		 * 写进标题
		 * HSSFRow titleRow = null;
		 * if (!titleName.equals("")) {
		 * titleRow = sheet.createRow((short) 0);
		 * titleRow.setHeightInPoints(30.120f);
		 * HSSFCell titlecell = titleRow.createCell((short) 0);// 标题
		 * // titlecell.setEncoding(HSSFCell.ENCODING_UTF_16);//
		 * titlecell.setCellValue(titleName);
		 * }
		 */
		// 创建列名称
		HSSFRow headRow = sheet.createRow((short) 0);//
		headRow.setHeightInPoints(20.120f);

		for (int i = 0; i < columnNames.length; i++) {
			HSSFCell cell = headRow.createCell((short) i);
			cell.setCellStyle(style);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 设置cell编码解决中文高位字节截断
			cell.setCellValue(columnNames[i]);
		}
		// 创建内容
		for (int i = 0; i < excelContent.size(); i++) {
			short cellNo = 0;
			// 建立新行

			HSSFRow row = sheet.createRow((short) i + 1);
			// 新建一列
			HSSFCell cell = null;
			String cellValue = "";
			// 校区
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("campus");
			cellValue = cellValue.equals("1") ? "石牌" : "大学城";

			cell.setCellValue(cellValue);
			// 考试名称
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("test_booking_name");
			cell.setCellValue(cellValue);

			// 学院
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("college");
			cell.setCellValue(cellValue);
			// 年级
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("grade");
			cell.setCellValue(cellValue);
			// 学号
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("student_no");
			cell.setCellValue(cellValue);
			// 姓名
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("name");
			cell.setCellValue(cellValue);
			// 性别
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("gender");
			cell.setCellValue(cellValue);

			// 民族
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("nationality");
			cell.setCellValue(cellValue);
			// 民族代码
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("nationality_code");
			cell.setCellValue(cellValue);
			// 出生日期
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateOfBirth = sdf.format(excelContent.get(i).get(
					"date_of_birth"));
			cell.setCellValue(dateOfBirth);

			// 身份证号
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("id_no");
			cell.setCellValue(cellValue);
			// 专业名称
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("major");
			cell.setCellValue(cellValue);
			// 行政班
			// 新建一列
			cell = row.createCell(cellNo++);
			cell.setCellStyle(style2);
			cellValue = (String) excelContent.get(i).get("executive_class");
			cell.setCellValue(cellValue);

		}

		request.setCharacterEncoding("gbk");
		MessageFormat.format("{0}/{1}.xls", request.getRealPath("expExcel"),
				Long.toString(System.currentTimeMillis())); // filename是工作薄的存放位置
		wb.write(fOut);
		fOut.flush();
		fOut.close();
	}
}
