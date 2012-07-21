package com.tkxwz.esruocetile.test;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Po Kong 
 * @since 2012-5-10 下午5:51:58
 */
public class Test {

	public static void main(String[] args) {
		List valueTypesList = new ArrayList();

		valueTypesList.add(1);
		valueTypesList.add(2);
		int valueTypesSize = valueTypesList.size();
		int[] valueTypes = new int[valueTypesSize];
		for (int i = 0; i < valueTypesSize; i++) {
			valueTypes[i] = (Integer) valueTypesList.get(i);
		}
		for (int a : valueTypes) {
			System.out.println(a);
		}

	}
}
