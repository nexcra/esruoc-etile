package com.tkxgz.elitecourse.core.converter;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public final class SqlTimestampConverter implements Converter {

	public SqlTimestampConverter() {

		this.defaultValue = null;
		this.useDefault = false;
	}

	public SqlTimestampConverter(Object defaultValue) {
		this.defaultValue = defaultValue;
		this.useDefault = true;
	}

	private Object defaultValue = null;

	private boolean useDefault = true;

	public Object convert(Class type, Object value) {
		if (value == null || "".equals(value)) {
			if (useDefault) {
				return (defaultValue);
			} else {
				throw new ConversionException("No value specified");
			}
		}
		if (value instanceof Timestamp) {
			return (value);
		}
		try {
			return (Timestamp.valueOf(value.toString()));
		} catch (Exception e) {
			if (useDefault) {
				return (defaultValue);
			} else {
				throw new ConversionException(e);
			}
		}
	}
}
