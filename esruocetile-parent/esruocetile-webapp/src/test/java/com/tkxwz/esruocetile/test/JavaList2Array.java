package com.tkxwz.esruocetile.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Po Kong 
 * @since 2012-7-7 上午12:31:20
 */
public class JavaList2Array {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("a");
		list.add("b");

		// 转换成String[]类型，使用List接口的Object[] toArray()方法
		// 输出
		// a
		// b

		//String[] strArr = (String[]) list.toArray();
//		for (String str : strArr) {
//			System.out.println(str);
//		}

		// 转换成String[]类型，使用List接口的 <T> T[] toArray(T[] a)方法
		// 输出
		// a
		// b
		String []strArr = (String[]) list.toArray(new String[list.size()]);
		for (String str : strArr) {
			System.out.println(str);
		}
	}
}
