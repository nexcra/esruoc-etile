package com.tkxwz.esruocetile.webapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkxwz.esruocetile.webapp.service.DataService;
import com.tkxwz.esruocetile.webapp.service.DictService;

/**
 * @author Po Kong
 * @since 2012-7-5 上午10:11:57
 */
@Controller(value = "/data.do")
public class DataController {

	@Autowired
	private DataService dataService;

	@Autowired
	private DictService dictService;

	@RequestMapping(params = "action=toImportStudentData")
	public String toImportStudentData() {
		return "importData.jsp";
	}

	@RequestMapping(params = "action=importStudentData")
	public String importStudentData(HttpServletRequest request) {
		// 检查是否multipart的enctype
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		int result = 0;
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();

			FileItemIterator iter;
			try {
				iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					String name = item.getFieldName();// 表单名
					InputStream is = item.openStream();
					// 普通的表单域
					if (item.isFormField()) {
						// todo
					} else {// 文件上传域
						// 如果直接取item.getName得到文件名，在IE中上传的话，会得出本地全路径"C:\test.xls"，
						// 而其他浏览器得出"test.xls",但我们要的是"test"部分，因此，需要去掉前面的本地盘符信息
						String fileBaseName = FilenameUtils.getBaseName(item
								.getName());
						String fileExtension = FilenameUtils.getExtension(item
								.getName());
						// 保存到服务器的文件名后面加上"-",再加10位由字母及数字组成的随机数
						String fileName = fileBaseName + "_"
								+ RandomStringUtils.randomAlphanumeric(10)
								+ "." + fileExtension;

						String uploadFilePath = this.dictService
								.getDictValueByDictName("uploadFilePath")
								+ "/import/";

						File file = new File(uploadFilePath);

						// 上传路径不存在则创建,存在则直接返回，忽略
						file.mkdirs();

						final String filePath = uploadFilePath + fileName;

						if (is.available() != 0) {
							Streams.copy(is, new FileOutputStream(filePath),
									true);
							List<Map<Integer, String>> studentList = dataService
									.extractExcelData(filePath, fileExtension);

							result = this.dataService
									.batchAddStudent(studentList);
						}
						// 导入完成后，删除服务器上的临时文件
						new File(filePath).delete();

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("成功导入" + result + "条记录");
		return "redirect:/student.do?action=listStudent";
	}
}
