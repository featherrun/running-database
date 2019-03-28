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

package running.database.builder;

import running.core.Logger;
import running.core.Running;
import running.database.SimpleDb;
import running.help.ClassBuilder;
import running.util.FileUtils;
import running.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db2TypeScript {
	final Logger logger = Running.getLogger(getClass());
	final FileUtils fileUtils = Running.get(FileUtils.class);
	final StringUtils stringUtils = Running.get(StringUtils.class);

	public Db2TypeScript(final SimpleDb db, final String output) {
		List<String> tables =  db.getStringList("SHOW TABLES");
		Map<String, String> primaryKeys = new HashMap<>();
		ClassBuilder sb = new ClassBuilder();
		sb.startWith("namespace db {");
		for (String table : tables) {
			List<TableColumn> columns = db.executeQuery(TableColumn.class, "SHOW FULL COLUMNS FROM " + table);
			if (!columns.isEmpty()) {
				String clazz = stringUtils.normalize(table, true);
				primaryKeys.put(clazz, stringUtils.normalize(columns.get(0).Field, false));
				sb.startWith("export interface "+clazz+" {");
				for (TableColumn column : columns) {
					String field = stringUtils.normalize(column.Field, false);
					sb.appendLine(field + ":" + getType(column.Type) + "; //" + column.Comment);
				}
				sb.endWith("}");
			}
		}
		sb.endWith("}");

		sb.appendLine();
		sb.startWith("const LocalCache = {");
		for (String clazz : primaryKeys.keySet()) {
			sb.appendLine(clazz+": new running.SimpleData<db."+clazz+">(\""+primaryKeys.get(clazz)+"\"),");
		}
		sb.endWith("};");

		//生成文件
		String path = output;
		int i1 = path.lastIndexOf('.');
		int i2 = path.lastIndexOf('/');
		if (i1 <= i2) {
			fileUtils.mkdirs(output);
			path += "db.ts";
		} else {
			fileUtils.mkdirs(output.substring(0, i2));
		}
		fileUtils.save(path, sb.toString());
		logger.info(path);
	}

	public static class TableColumn {
		public String Comment;
		public String Field;
		public String Type;
	}

	String getType(String type) {
		if (type.contains("int") || type.contains("double") || type.contains("float")) {
			return "number";
		} else {
			return "string";
		}
	}

}
