package com.tkxgz.elitecourse.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * 压缩或者解压缩zip的工具类，只需给相应参数即可
 * @author Administrator
 */
public class ZipUtil {

	/**
	 * 打包压缩学生照片
	 * 
	 * @author Po Kong
	 * @since 2013-2-2 下午11:30:59
	 * @param fileNamePathList
	 *        要压缩的文件路径列表
	 * @param zipFilePath
	 *        压缩文件路径
	 * @param zipComment
	 *        压缩注释
	 * @return true:成功 false:失败
	 */
	public  boolean generateStudentPhotoZip(
			List<String> fileNamePathList, String zipFilePath, String zipCommemt) {
		boolean result = true;
		ZipOutputStream out = null;
		FileOutputStream f = null;
		CheckedOutputStream cumu = null;
		try {
			f = new FileOutputStream(zipFilePath);// 文件输出流
			cumu = new CheckedOutputStream(f, new CRC32());// 校验输出流
			out = new ZipOutputStream(cumu);// 向压缩文件中输入数据
			out.setComment(zipCommemt);// 为指定的压缩文件写注释
			for (int i = 0; i < fileNamePathList.size(); i++) {
				File ff = new File(fileNamePathList.get(i));
				if(ff.exists()) {
					out.putNextEntry(new ZipEntry(ff.getName()));// 开始写入新的文件条目,并将流定位在新的文件条目位置
	
					BufferedInputStream bis = new BufferedInputStream(
							new FileInputStream(ff));
	
					int count;
					byte data[] = new byte[1024];
					while ((count = bis.read(data, 0, 1024)) != -1) {
						out.write(data, 0, count);
					}
					bis.close();
					out.closeEntry();
				}
			}

			System.out.println("checksum:" + cumu.getChecksum().getValue());// 返回此输入流的校验和
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
