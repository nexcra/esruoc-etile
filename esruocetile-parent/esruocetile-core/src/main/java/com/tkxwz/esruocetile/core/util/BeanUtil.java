package com.tkxwz.esruocetile.core.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.tkxwz.esruocetile.core.common.DateConverter;

/**
 * @author Po Kong
 * @since 27 Jul 2012 23:44:40
 */
public class  BeanUtil extends BeanUtils {

	static {
		ConvertUtils.register(new DateConverter(), java.util.Date.class);
		ConvertUtils.register(new DateConverter(), java.sql.Date.class);
		ConvertUtils.register(new DateConverter(), java.sql.Timestamp.class);
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}
}
