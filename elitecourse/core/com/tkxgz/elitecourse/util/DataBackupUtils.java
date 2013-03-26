package com.tkxgz.elitecourse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DataBackupUtils {

	/**
	 * 数据备份
	 * 
	 * @author Soyi Yao
	 * @param mysqlInstallPath
	 * @param sqlBackupFilePath
	 * @throws IOException
	 */
	public static void backup(String mysqlInstallPath, String sqlBackupFilePath)
			throws IOException {
		Runtime runtime = Runtime.getRuntime();
		// -u后面是用户名，-p是密码-p后面最好不要有空格，
		Process process = runtime.exec(mysqlInstallPath
				+ "\\bin\\mysqldump -u root -proot elite_course");
		InputStream inputStream = process.getInputStream();// 得到输入流，写成.sql文件
		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(reader);
		String s = null;
		StringBuffer sb = new StringBuffer();
		while ((s = br.readLine()) != null) {
			sb.append(s + "\r\n");
		}
		s = sb.toString();
		System.out.println(s);
		File file = new File(sqlBackupFilePath);
		file.getParentFile().mkdirs();
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(s.getBytes());
		fileOutputStream.close();
		br.close();
		reader.close();
		inputStream.close();
	}

	/**
	 * 数据恢复
	 * 
	 * @author Soyi Yao
	 * @param mysqlInstallPath
	 * @param sqlBackupFilePath
	 * @throws IOException
	 */
	public static void restore(String mysqlInstallPath, String sqlBackupFilePath)
			throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime
				.exec(mysqlInstallPath
						+ "\\bin\\mysql -u root -proot --default-character-set=utf8 elite_course");
		OutputStream outputStream = process.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(sqlBackupFilePath)));
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = br.readLine()) != null) {
			sb.append(str + "\r\n");
		}
		str = sb.toString();
		System.out.println(str);
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,
				"utf-8");
		writer.write(str);
		writer.flush();
		outputStream.close();
		br.close();
		writer.close();
	}

}
