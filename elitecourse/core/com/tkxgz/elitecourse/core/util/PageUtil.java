package com.tkxgz.elitecourse.core.util;

/**
 * @author Po Kong 
 * @since 2012-2-26 下午2:05:44
 */
public class PageUtil {

	/**
	 * 传入页码字符串，返回有效的页码<br />
	 * 如果页码字符串在转化成Integer过程中抛异常
	 * （如页码字符串为null,"",或者超出Integer的范围)，则返回1<br />
	 * 
	 * @author Po Kong 
	 * @since 2012-2-26 下午2:09:24
	 * @param pageNum
	 *        要处理的页码字符串
	 * @return 返回有效页码
	 */
	public static int getPageNum(String pageNum) {
		int result = 1;
		try {
			result = Integer.valueOf(pageNum);
		} catch (Exception e) {
			result = 1;
		}

		return result;
	}
}
