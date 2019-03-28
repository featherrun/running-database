/*
 * Copyright 2013-2019 featherrun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package running.database;

import running.core.Logger;
import running.core.Running;
import running.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleMapper {
	protected final Logger logger = Running.getLogger(getClass());
	protected Map<Class<?>, QueryData> queryDataMap = new ConcurrentHashMap<>(); //查询结构

	public QueryData getQueryData(ResultSet rs, Class<?> clazz) throws SQLException {
		QueryData queryData = queryDataMap.get(clazz);
		if (queryData == null) {
			queryData = new QueryData(clazz, rs.getMetaData());
			queryDataMap.put(clazz, queryData);
		}
		return queryData;
	}

	public static class QueryData {
		protected static StringUtils stringUtils = Running.get(StringUtils.class);
		protected Class<?> clazz;
		protected Map<String, Field> fieldMap;
		protected String[] cols;
		protected String[] colsUpper;
		public QueryData(Class<?> clazz, ResultSetMetaData metaData) throws SQLException {
			this.clazz = clazz;
			this.fieldMap = new HashMap<>();
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				fieldMap.put(field.getName(), field);
			}

			int count = metaData.getColumnCount();
			this.cols = new String[count];
			this.colsUpper = new String[count];
			for (int i=0; i<count; i++) {
				String s = metaData.getColumnLabel(i+1);
				cols[i] = s;
				colsUpper[i] = stringUtils.normalize(s, false);
			}
		}
	}

	public <T> T getObject(ResultSet rs, Class<T> clazz) throws Exception {
		return getObject(rs, clazz, getQueryData(rs, clazz), true);
	}

	public <T> T getObject(ResultSet rs, Class<T> clazz, QueryData queryData, boolean normalize) throws Exception {
		T obj = clazz.newInstance();
		int len = queryData.cols.length;
		for (int i=0; i<len; i++) {
			String s = queryData.cols[i];
			String fieldName = normalize ? queryData.colsUpper[i] : s;
			Field field = queryData.fieldMap.get(fieldName);
			if (field != null) {
				Class<?> type = field.getType();
				if (type == int.class || type == Integer.class) {
					field.setInt(obj, rs.getInt(s));
				} else if (type == String.class) {
					field.set(obj, rs.getString(s));
				} else if (type == long.class || type == Long.class) {
					field.setLong(obj, rs.getLong(s));
				} else if (type == short.class || type == Short.class) {
					field.setShort(obj, rs.getShort(s));
				} else if (type == byte.class || type == Byte.class) {
					field.setByte(obj, rs.getByte(s));
				} else if (type == boolean.class || type == Boolean.class) {
					field.setBoolean(obj, rs.getBoolean(s));
				} else if (type == double.class || type == Double.class) {
					field.setDouble(obj, rs.getDouble(s));
				} else if (type == float.class || type == Float.class) {
					field.setFloat(obj, rs.getFloat(s));
				} else if (logger != null) {
					logger.debug("FIELD TYPE NOT FOUND：" + clazz.getName() + " " + fieldName);
				}
			} else {
				if (logger != null) {
					logger.debug("FIELD isn't IN CLASS：" + clazz.getName() + " " + fieldName);
				}
			}
		}
		return obj;
	}
}
