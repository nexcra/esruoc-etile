package com.tkxgz.elitecourse.core.util;

import java.util.List;

/**
 * @author Po Kong 
 * @since 2012-7-7 上午12:57:23
 */
public class ListUtil {

	private ListUtil() {

	}

	/**
	 * 用于将List转化成int []类型
	 * 
	 * @author Po Kong 
	 * @since 2012-7-7 上午12:56:21
	 * @param list
	 * @return int[]
	 */
	public static int[] list2intArray(List<Integer> list) {
		int size = list.size();
		int[] intArr = new int[size];
		for (int i = 0; i < size; i++) {
			intArr[i] = (int) list.get(i);
		}
		return intArr;
	}

	/**
	 * 用于将List转化成Object []类型
	 * 
	 * @author Po Kong 
	 * @since 2012-7-7 上午12:56:21
	 * @param list
	 * @return Object []
	 */
	public static Object[] list2objectArray(List<Object> list) {
		int size = list.size();
		return list.toArray(new Object[size]);
	}
}
