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

import running.core.ClassBuilder;
import running.core.ILogger;
import running.core.Running;
import running.database.SimpleDb;
import running.database.Struct;
import running.util.FileUtils;
import running.util.PropertiesUtils;
import running.util.StringUtils;

public class Db2Java {
	final ILogger logger = Running.getLogger(getClass());
	final FileUtils fileUtils = Running.get(FileUtils.class);
	final StringUtils stringUtils = Running.get(StringUtils.class);
	final PropertiesUtils propertiesUtils = Running.get(PropertiesUtils.class);

	final String TEMPLATE;
	final String packageName;
	final String extendsName;
	final boolean assign;

	public Db2Java(final SimpleDb db, final String output) {
		TEMPLATE = fileUtils.getResource("template/entity.java.template");
		packageName = propertiesUtils.getProperty("packageName", "app.model.entity");
		extendsName = propertiesUtils.getProperty("extendsName");
		assign = propertiesUtils.getProperty("assign", "false").toLowerCase().equals("true") && extendsName != null && !extendsName.isEmpty();

		String path = output + packageName.replaceAll("\\.", "/") + "/";
		Struct struct = new Struct();
		struct.loadStruct(db);
		for (Struct.Table table : struct.getTables()) {
			create(table, path);
		}
	}

	void create(final Struct.Table table, final String output) {
		ClassBuilder fields = new ClassBuilder(1);
		ClassBuilder getAndSet = new ClassBuilder(1);
		ClassBuilder entries = new ClassBuilder(2);
		for (Struct.Field field : table.getFields()) {
			if (field == null) {
				break;
			}

			fields.append("private ");
			fields.append(field.getType()).append(" ");
			fields.append(field.getName()).append(";");
			fields.append(" //").append(field.getFieldComment());
			fields.appendLine();

			String fn = stringUtils.normalize(field.getFieldName(), true);
			getAndSet.append("/**").append(field.getFieldComment()).append("*/").appendLine();
			getAndSet.append("public ").append(field.getType()).append(" get").append(fn).startWith("() {");
			getAndSet.append("return ").append(field.getName()).appendLine(";");
			getAndSet.endWith("}");
			getAndSet.startWith(String.format("public void set%s(%s %s) {", fn, field.getType(), field.getName()));
			if (assign) getAndSet.startWith(String.format("if (assign(\"%s\", %s, this.%s)) {", field.getFieldName(), field.getName(), field.getName()));
			getAndSet.appendLine(String.format("this.%s = %s;", field.getName(), field.getName()));
			if (assign) getAndSet.endWith("}");
			getAndSet.endWith("}");
			getAndSet.appendLine();

			entries.appendLine(String.format("e.entry(\"%s\", %s);", field.getFieldName(), field.getName()));
		}

		entries.deleteLine();
		String ex = extendsName == null || extendsName.isEmpty() ? "" : " extends " + extendsName;

		String clazz = TEMPLATE;
		clazz = clazz.replace("__package__", packageName);
		clazz = clazz.replace("__tableComment__", table.getTableComment());
		clazz = clazz.replace("__class__", table.getName() + ex);
		clazz = clazz.replace("__fields__", fields.toString());
		clazz = clazz.replace("__get_and_set__", getAndSet.toString());
		clazz = clazz.replace("__tableName__", table.getTableName());
		clazz = clazz.replace("__primaryKey__", table.getPrimaryKey());
		clazz = clazz.replace("__primary__", table.getPrimary());
		clazz = clazz.replace("__entries__", entries.toString());

		String path = output + table.getName() + ".java";
		fileUtils.mkdirs(output);
		fileUtils.save(path, clazz);
		logger.info(path);
	}

}
