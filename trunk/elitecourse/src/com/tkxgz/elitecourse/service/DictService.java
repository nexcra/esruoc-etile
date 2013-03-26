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
import com.tkxgz.elitecourse.dao.DictDao;
import com.tkxgz.elitecourse.bean.Dict;

/**
 * 字典Service类
 * 
 * @author Soyi Yao
 */
@Service
public class DictService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictDao dictDao;

	/**
	 * 分页查询字典列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 字典分页数据
	 */
	public Page listDict(Page page) {

		return this.dictDao.listDict(page);
	}

	/**
	 * 添加字典
	 * 
	 * @author Soyi Yao
	 * @param dict
	 *        字典bean
	 * @return
	 */
	public int addDict(Dict dict) {

		return this.dictDao.addDict(dict);
	}

	/**
	 * 删除字典
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的字典id
	 * @return 影响行数
	 */
	public int deleteDictById(String id) {

		return this.dictDao.deleteDictById(id);
	}

	/**
	 * 批量删除字典
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        字典id数组
	 * @return 影响行数
	 */
	public int batchDeleteDict(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteDictById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据字典id查询字典详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        字典id
	 * @return 字典Map
	 */
	public Map getDictById(String id) {

		return this.dictDao.getDictById(id);
	}

	/**
	 * 根据字典id查询字典实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Dict findDictById(String id) {

		return this.dictDao.findDictById(id);
	}

	/**
	 * 更新字典
	 * 
	 * @author Soyi Yao
	 * @param dict
	 *        字典bean
	 * @return 影响行数
	 */
	public int updateDict(Dict dict) {

		return this.dictDao.updateDict(dict);
	}

	/**
	 * 分页搜索字典列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param dict
	 *        字典bean
	 * @return 分页字典列表
	 */
	public Page searchDict(Page page, Dict dict) {

		return this.dictDao.searchDict(page, dict);
	}

	/**
	 * 列出所有字典列表
	 * 
	 * @author Soyi Yao
	 * @return 字典列表
	 */
	public List<Dict> listAllDict() {
		return this.dictDao.listAllDict();

	}

	/**
	 * 搜索字典列表
	 * 
	 * @author Soyi Yao
	 * @param dict
	 *        Dict实体
	 * @return 字典列表
	 */
	public List<Dict> searchDictForList(Dict dict) {

		return this.dictDao.searchDictForList(dict);
	}

	/**
	 * excel导出字典列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的字典内容列表
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
	public void exportDictList(HttpServletRequest request,
			List<Dict> excelContent, String[] columnNames, String sheetName,
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
			cellValue = (String) excelContent.get(i).getDictCode();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getDictName();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getDictDesc();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getDictValue();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getStatus();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getIsApplicationLevel();
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
	 * 获取t_dict表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_dict表记录总数
	 */
	public int getTotalRecords() {
		return this.dictDao.getTotalRecords();
	}

	/**
	 * @author Soyi Yao
	 * @param string
	 * @return
	 */
	public Dict getDictByCode(String code) {

		return this.dictDao.getDictByCode(code);
	}
}
