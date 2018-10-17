/*
 * Copyright 2013-2018 featherrun
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

import running.core.ILogger;
import running.core.Running;
import running.util.StringUtils;
import running.util.ZipUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库结构
 */
public class Struct {
	protected Table[] tables = null;

	public Table[] getTables() {
		return tables;
	}

	public Table getTableByName(String name) {
		for (Table t : tables) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public Map<String, List<Map<String, Object>>> toData() {
		Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
		for (Table t : tables) {
			dataMap.put(t.getName(), t.toData());
		}
		return dataMap;
	}


	/**
	 * 从db.zip加载
	 * @param file
	 */
	public void load(String file) {
		final ZipUtils zipUtils = Running.get(ZipUtils.class);
		Map<String, String> textMap = zipUtils.read(file);
		loadStruct(textMap);
		loadValues(textMap);
	}

	protected void loadStruct(Map<String, String> textMap) {
		List<Table> list = new ArrayList<>();
		for (Map.Entry<String, String> entry : textMap.entrySet()) {
			String name = entry.getKey();
			if (name.contains(".sql")) {
				Table t = new Table();
				t.parseSql(entry.getValue());
				list.add(t);
			}
		}
		tables = list.toArray(new Table[list.size()]);
	}

	protected void loadValues(Map<String, String> textMap) {
		for (Table t : tables) {
			String name = t.getTableName() + ".txt";
			if (textMap.containsKey(name)) {
				t.parseData(textMap.get(name));
			}
		}
	}

	/**
	 * 从数据库加载
	 * @param db
	 */
	public void load(SimpleDb db) {
		loadStruct(db);
		loadValues(db);
	}

	public void loadStruct(SimpleDb db) {
		Collection<String> tableNames = db.getStringList("SHOW TABLES");
		List<Table> list = new ArrayList<>();
		for (String tableName : tableNames) {
			List<String[]> createSql = db.getStringArrayList("SHOW CREATE TABLE " + tableName);
			String text = createSql.get(0)[1] + ";";
			Table t = new Table();
			t.parseSql(text);
			list.add(t);
		}
		tables = list.toArray(new Table[list.size()]);
	}

	public void loadValues(SimpleDb db) {
		if (tables == null) {
			return;
		}
		for (Table t : tables) {
			List<String[]> list = db.getStringArrayList("SELECT * FROM " + t.getTableName());
			t.values = list.toArray(new String[list.size()][]);
		}
	}


	/**
	 * 数据表
	 */
	public static class Table {
		static final Pattern p1 = Pattern.compile("CREATE TABLE `([A-Za-z0-9_]+)` \\((.+)\\)([^;]+);");
		static final Pattern p2 = Pattern.compile("`([A-Za-z0-9_]+)` ([a-z\\(\\)0-9]+).+COMMENT '(.+)'");
		static final Pattern p3 = Pattern.compile("`([A-Za-z0-9_]+)` ([a-z\\(\\)0-9]+)");
		static final Pattern p4 = Pattern.compile("PRIMARY KEY \\(`([A-Za-z0-9_]+)`\\)");
		static final Pattern p5 = Pattern.compile("COMMENT='(.+)'");

		public static final String s_col = "\t#\t";
		public static final String s_row = "\n-\n";

		protected String sql;
		protected String tableName;
		protected String tableComment;
		protected Field[] fields;
		protected String[][] values;
		protected int fieldCount;
		protected String primaryKey;
		protected String primary;
		protected String name;

		public String getSql() {
			return sql;
		}

		public String getTableName() {
			return tableName;
		}

		public String getTableComment() {
			return tableComment;
		}

		public String getPrimaryKey() {
			return primaryKey;
		}

		public String getPrimary() {
			return primary;
		}

		public String getName() {
			return name;
		}

		public Field[] getFields() {
			return fields;
		}

		public String[][] getValues() {
			return values;
		}

		public List<Map<String, Object>> toData() {
			List<Map<String, Object>> list = new ArrayList<>();
			if (values != null) {
				for (String[] line : values) {
					Map<String, Object> map = new HashMap<>();
					list.add(map);
					int i = 0;
					for (String val : line) {
						Field f = fields[i];
						i++;
						if (f == null) break;
						map.put(f.getName(), f.getValue(val));
					}
				}
			}
			return list;
		}

		/**
		 * 解析CREATE TABLE结构
		 *
		 * @param sql
		 */
		public void parseSql(String sql) {
			this.sql = sql;
			final StringUtils stringUtils = Running.get(StringUtils.class);
			sql = sql.replaceAll("\\n", "");
			Matcher m1 = p1.matcher(sql);
			if (m1.find()) {
				tableName = m1.group(1);
				name = stringUtils.normalize(tableName, true);

				tableComment = m1.group(3);
				Matcher m5 = p5.matcher(tableComment);
				if (m5.find()) {
					tableComment = m5.group(1);
				}

				String fieldStr = m1.group(2);
				String[] fieldArr = fieldStr.split(",");
				fields = new Field[fieldArr.length];
				fieldCount = 0;
				for (String field : fieldArr) {
					if (field.contains("KEY")) {
						Matcher m4 = p4.matcher(field);
						if (m4.find()) {
							primaryKey = m4.group(1);
							primary = stringUtils.normalize(primaryKey, false);
						}
					} else {
						Field f = null;
						Matcher m2 = p2.matcher(field);
						if (m2.find()) {
							f = new Field();
							f.fieldName = m2.group(1);
							f.fieldType = m2.group(2);
							f.fieldComment = m2.group(3);
						} else {
							Matcher m3 = p3.matcher(field);
							if (m3.find()) {
								f = new Field();
								f.fieldName = m3.group(1);
								f.fieldType = m3.group(2);
							}
						}
						if (f != null) {
							f.name = stringUtils.normalize(f.fieldName, false);
							fields[fieldCount] = f;
							fieldCount++;
						}
					}
				}

				if (primaryKey == null) {
					primaryKey = fields[0].fieldName;
					primary = stringUtils.normalize(primaryKey, false);
					final ILogger logger = Running.getLogger(getClass());
					logger.warn(tableName + ": unable to find the primary key, use `" + primaryKey + "` instead.");
				}
			}
		}

		/**
		 * 解析数据
		 *
		 * @param text
		 */
		public void parseData(String text) {
			if (text.isEmpty())
				return;
			String[] lines = text.split(s_row);
			values = new String[lines.length][];
			int i = 0;
			for (String line : lines) {
				values[i] = line.split(s_col);
				i++;
			}

			//如果解析的数据长度和字段长度不符，给以提示
			if (values.length > 1 && values[1].length != fieldCount) {
				final ILogger logger = Running.getLogger(getClass());
				logger.warn("DATA-FIELD COUNT DIFFERENT: " + tableName + " " + values[1].length + " " + fieldCount);
			}
		}


	}

	/**
	 * 数据字段
	 */
	public static class Field {
		protected String fieldName;
		protected String fieldType;
		protected String fieldComment;
		protected String name;
		protected String type;

		public String getFieldName() {
			return fieldName;
		}

		public String getFieldType() {
			return fieldType;
		}

		public String getFieldComment() {
			return fieldComment;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			if (type == null) {
				String t = fieldType.toLowerCase();
				int index = t.indexOf("(");
				if (index != -1) {
					t = t.substring(0, index);
				}
				switch (t) {
					case "tinyint":
						if (fieldType.charAt(index + 1) == '3') {
							return "short";
						}
						type = "byte";
						break;
					case "smallint":
						type = "short";
						break;
					case "mediumint":
					case "int":
						type = "int";
						break;
					case "bigint":
						type = "long";
						break;
					case "float":
					case "double":
						type = "double";
						break;
					case "bit":
						type = "boolean";
						break;
					default:
						type = "String";
						break;
				}
			}
			return type;
		}

		public Object getValue(String s) {
			String t = getType();
			switch (t) {
				case "byte":
					return s == null ? (byte) 0 : Byte.parseByte(s);
				case "short":
					return s == null ? (short) 0 : Short.parseShort(s);
				case "int":
					return s == null ? 0 : Integer.parseInt(s);
				case "long":
					return s == null ? 0 : Long.parseLong(s);
				case "double":
					return s == null ? 0d : Double.parseDouble(s);
				default:
					return s.intern();
			}
		}
	}

}
