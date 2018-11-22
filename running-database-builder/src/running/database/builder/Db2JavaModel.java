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

public class Db2JavaModel {
	final ILogger logger = Running.getLogger(getClass());
	final FileUtils fileUtils = Running.get(FileUtils.class);
	final StringUtils stringUtils = Running.get(StringUtils.class);
	final PropertiesUtils propertiesUtils = Running.get(PropertiesUtils.class);

	final String TEMPLATE;
	final String packageName;
	final String packageEntity;
	final String className;
	final String baseTable;
	final String prefix;

	public Db2JavaModel(final SimpleDb db, final String output) {
		TEMPLATE = fileUtils.getResource("template/model.java.template");
		packageName = propertiesUtils.getProperty("packageName", "app.model");
		packageEntity = propertiesUtils.getProperty("packageEntity", "app.model.entity");
		className = propertiesUtils.getProperty("className", "Model");
		baseTable = propertiesUtils.getProperty("baseTable", "t_lead");
		prefix = propertiesUtils.getProperty("prefix", "t_");

		final String baseName = stringUtils.normalize(baseTable, true);
		final String baseNoPrefix = baseTable.substring(prefix.length());

		Struct struct = new Struct();
		struct.loadStruct(db);
		Struct.Table baseStruct = struct.getTableByName(baseName);
		if (baseStruct == null) {
			logger.warn("Not found baseTable:" + baseTable);
			return;
		}

		final String listKey = baseNoPrefix + "_" + baseStruct.getPrimaryKey();
		final String listKeyString1 = "KEY `"+listKey+"` (`"+listKey+"`)";
		final String listKeyString2 = "INDEX `"+listKey+"` (`"+listKey+"`)";

		ClassBuilder fields = new ClassBuilder(1);
		ClassBuilder getAndSet = new ClassBuilder(1);
		ClassBuilder loads = new ClassBuilder(2);
		for (Struct.Table table : struct.getTables()) {
			String sql = table.getSql();
			boolean isExtend = (table.getTableName().indexOf(baseTable) == 0 && table.getPrimaryKey().equals(baseStruct.getPrimaryKey()));
			boolean hasListIndex = (sql.contains(listKeyString1) || sql.contains(listKeyString2));
			boolean isOne;
			if (isExtend && !hasListIndex) {
				isOne = true;
			} else if (hasListIndex) {
				isOne = false;
			} else {
				continue;
			}

			String name = table.getTableName().substring(prefix.length());
			if (name.indexOf(baseNoPrefix) == 0 && table != baseStruct) {
				name = name.substring(baseNoPrefix.length());
			}
			String fieldType = table.getName();
			String fieldName = stringUtils.normalize(name, false);
			String oneType = fieldType;
			String oneName = fieldName;
			if (!isOne) {
				fieldType = "List<"+fieldType+">";
				fieldName = fieldName + "List";
			}
			String getName = "get" + stringUtils.firstUpperCase(fieldName);
			String setName = (isOne ? "set" : "add") + stringUtils.firstUpperCase(fieldName);

			fields.append("private ").append(fieldType).append(" ").append(fieldName).append("; //").appendLine(table.getTableComment());

			getAndSet.appendLine(String.format("/**%s*/", table.getTableComment()));
			getAndSet.startWith(String.format("public %s %s() {", fieldType, getName));
			getAndSet.appendLine(String.format("return %s;", fieldName));
			getAndSet.endWith("}");
			getAndSet.startWith(String.format("public void %s(%s %s) {", setName, oneType, oneName));
			if (isOne) {
				getAndSet.appendLine(String.format("this.%s = %s;", fieldName, fieldName));
				getAndSet.appendLine(String.format("%s.from(database());", fieldName));
			} else {
				getAndSet.appendLine(String.format("if (%s == null) %s = new LinkedList<>();", fieldName, fieldName));
				getAndSet.appendLine(String.format("%s.add(%s);", fieldName, oneName));
				getAndSet.appendLine(String.format("%s.from(database());", oneName));
			}
			getAndSet.endWith("}");
			getAndSet.appendLine();

			String load;
			if (isOne) {
				load = String.format("if (%s == null) %s = utils.single(%s.class, db, key);", fieldName, fieldName, fieldType);
			} else {
				load = String.format("if (%s == null) %s = utils.select(%s.class, db, \"%s\", key);", fieldName, fieldName, oneType, listKey);
			}
			loads.appendLine(load);
		}
		
		fields.deleteLine();
		getAndSet.deleteLine();
		loads.deleteLine();

		String clazz = TEMPLATE;
		clazz = clazz.replace("__package__", packageName);
		clazz = clazz.replace("__package_entity__", packageEntity);
		clazz = clazz.replaceAll("__tableComment__", baseStruct.getTableComment());
		clazz = clazz.replaceAll("__class__", className);
		clazz = clazz.replaceAll("__base_class__", baseName);
		clazz = clazz.replace("__fields__", fields.toString());
		clazz = clazz.replace("__get_and_set__", getAndSet.toString());
		clazz = clazz.replace("__loads__", loads.toString());

		String path = output + packageName.replaceAll("\\.", "/") + "/";
		fileUtils.mkdirs(path);
		path += className + ".java";
		fileUtils.save(path, clazz);
		logger.info(path);
	}
}
