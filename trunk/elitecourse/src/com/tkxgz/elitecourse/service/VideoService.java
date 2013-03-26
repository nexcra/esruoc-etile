package com.tkxgz.elitecourse.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkxgz.elitecourse.bean.Video;
import com.tkxgz.elitecourse.core.constant.CommonConstants;
import com.tkxgz.elitecourse.core.page.Page;
import com.tkxgz.elitecourse.dao.VideoDao;

/**
 * 视频Service类
 * 
 * @author Soyi Yao
 */
@Service
public class VideoService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VideoDao videoDao;

	/**
	 * 分页查询视频列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @return 视频分页数据
	 */
	public Page listVideo(Page page) {

		return this.videoDao.listVideo(page);
	}

	/**
	 * 添加视频
	 * 
	 * @author Soyi Yao
	 * @param video
	 *        视频bean
	 * @return
	 */
	public int addVideo(Video video) {

		return this.videoDao.addVideo(video);
	}

	/**
	 * 删除视频
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        要删除的视频id
	 * @return 影响行数
	 */
	public int deleteVideoById(String id) {

		return this.videoDao.deleteVideoById(id);
	}

	/**
	 * 批量删除视频
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        视频id数组
	 * @return 影响行数
	 */
	public int batchDeleteVideo(String[] ids) {

		int result = 0;

		for (String id : ids) {
			int affectedRows = this.deleteVideoById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 批量删除视频及物理媒体数据
	 * 
	 * @author Soyi Yao
	 * @param ids
	 *        视频id数组
	 * @return 影响行数
	 */
	public int batchDeleteVideo(String[] ids, String filePath) {

		int result = 0;

		for (String id : ids) {
			// 先删除物理数据
			Video video = this.findVideoById(id);
			File file = new File(filePath + video.getPath());
			if (file.exists()) {// 如果存在，则在硬盘上面删除
				file.delete();
			}

			// 再删除数据库记录
			int affectedRows = this.deleteVideoById(id);
			if (affectedRows > 0) {// 删除成功
				result++;
			}
		}

		return result;
	}

	/**
	 * 根据视频id查询视频详细
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        视频id
	 * @return 视频Map
	 */
	public Map getVideoById(String id) {

		return this.videoDao.getVideoById(id);
	}

	/**
	 * 根据视频id查询视频实体
	 * 
	 * @author Soyi Yao
	 * @param id
	 *        公告id
	 * @return 公告实体
	 */
	public Video findVideoById(String id) {

		return this.videoDao.findVideoById(id);
	}

	/**
	 * 更新视频
	 * 
	 * @author Soyi Yao
	 * @param video
	 *        视频bean
	 * @return 影响行数
	 */
	public int updateVideo(Video video) {

		return this.videoDao.updateVideo(video);
	}

	/**
	 * 分页搜索视频列表
	 * 
	 * @author Soyi Yao
	 * @param page
	 *        分页bean
	 * @param video
	 *        视频bean
	 * @return 分页视频列表
	 */
	public Page searchVideo(Page page, Video video) {

		return this.videoDao.searchVideo(page, video);
	}

	/**
	 * 列出所有视频列表
	 * 
	 * @author Soyi Yao
	 * @return 视频列表
	 */
	public List<Video> listAllVideo() {
		return this.videoDao.listAllVideo();

	}

	/**
	 * 搜索视频列表
	 * 
	 * @author Soyi Yao
	 * @param video
	 *        Video实体
	 * @return 视频列表
	 */
	public List<Video> searchVideoForList(Video video) {

		return this.videoDao.searchVideoForList(video);
	}

	/**
	 * excel导出视频列表
	 * 
	 * @author Soyi Yao
	 * @param request
	 *        HttpServletRequest
	 * @param excelContent
	 *        需要导出的视频内容列表
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
	public void exportVideoList(HttpServletRequest request,
			List<Video> excelContent, String[] columnNames, String sheetName,
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
			cellValue = (String) excelContent.get(i).getSource();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getType();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getDescription();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getPath();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateUserId();
			cell.setCellValue(cellValue);

			// 新建一列

			cell = row.createCell(cellNo++);
			cell.setCellStyle(contentStyle);
			cellValue = (String) excelContent.get(i).getCreateUserName();
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

	/**
	 * 获取t_video表记录总数
	 * 
	 * @author Soyi Yao
	 * @return t_video表记录总数
	 */
	public int getTotalRecords() {
		return this.videoDao.getTotalRecords();
	}

	/**
	 * 获取t_video表当前最大id
	 */
	public int getCurrentMaxId() {
		return this.videoDao.getCurrentMaxId();
	}
}
