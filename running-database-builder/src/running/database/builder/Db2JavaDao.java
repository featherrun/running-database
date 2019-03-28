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
import running.database.Struct;
import running.util.FileUtils;
import running.util.PropertiesUtils;
import running.util.StringUtils;

public class Db2JavaDao {
	final Logger logger = Running.getLogger(getClass());
	final FileUtils fileUtils = Running.get(FileUtils.class);
	final StringUtils stringUtils = Running.get(StringUtils.class);
	final PropertiesUtils propertiesUtils = Running.get(PropertiesUtils.class);

	final String TEMPLATE;
	final String packageName;
	final String packageEntity;

	public Db2JavaDao(final SimpleDb db, final String output) {
		TEMPLATE = fileUtils.getResource("template/dao.java.template");
		packageName = propertiesUtils.getProperty("packageName", "app.model.dao");
		packageEntity = propertiesUtils.getProperty("packageEntity", "app.model.entity");

		String path = output + packageName.replaceAll("\\.", "/") + "/";
		Struct struct = new Struct();
		struct.loadStruct(db);
		for (Struct.Table table : struct.getTables()) {
			create(table, path);
		}
	}

	void create(final Struct.Table table, final String output) {
		StringBuilder insert_key = new StringBuilder();
		StringBuilder insert_ask = new StringBuilder();
		StringBuilder insert_get = new StringBuilder();
		StringBuilder update_key = new StringBuilder();
		StringBuilder update_get = new StringBuilder();
		for (Struct.Field field : table.getFields()) {
			if (field == null) {
				break;
			}

			String func = "obj.get" + stringUtils.normalize(field.getName(), true) + "()";

			if (insert_key.length() > 0) insert_key.append(",");
			if (insert_ask.length() > 0) insert_ask.append(",");
			if (insert_get.length() > 0) insert_get.append(", ");
			insert_key.append(field.getFieldName());
			insert_ask.append("?");
			insert_get.append(func);

			if (field.getFieldName().equals(table.getPrimaryKey())) {
				continue;
			}

			if (update_key.length() > 0) update_key.append(",");
			if (update_get.length() > 0) update_get.append(", ");
			update_key.append(field.getFieldName()).append("=?");
			update_get.append(func);
		}

		String get_primary = "get" + stringUtils.normalize(table.getPrimaryKey(), true);

		String clazz = TEMPLATE;
		clazz = clazz.replace("__package__", packageName);
		clazz = clazz.replace("__package_entity__", packageEntity);
		clazz = clazz.replace("__class__", table.getName());
		clazz = clazz.replace("__tableName__", table.getTableName());
		clazz = clazz.replace("__primaryKey__", table.getPrimaryKey());
		clazz = clazz.replace("__get_primary__", get_primary);
		clazz = clazz.replace("__insert_key__", insert_key);
		clazz = clazz.replace("__insert_ask__", insert_ask);
		clazz = clazz.replace("__insert_get__", insert_get);
		clazz = clazz.replace("__update_key__", update_key);
		clazz = clazz.replace("__update_get__", update_get);

		String path = output + table.getName() + "Dao.java";
		fileUtils.mkdirs(output);
		fileUtils.save(path, clazz);
		logger.info(path);
	}
}
