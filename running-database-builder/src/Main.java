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

import running.core.Logger;
import running.core.PrintLogger;
import running.core.Running;
import running.database.SimpleDb;
import running.database.builder.*;
import running.util.*;

public class Main {
	static {
		Running.set(Logger.class, new PrintLogger(System.out, "FATAL","ERROR","WARN","INFO"));
		Running.set(FileUtils.class, new FileUtils());
		Running.set(StringUtils.class, new StringUtils());
		Running.set(TimeUtils.class, new TimeUtils());
		Running.set(PropertiesUtils.class, new PropertiesUtils());
		Running.set(JsonUtils.class, new JsonUtils());
		Running.set(JsonPackUtils.class, new JsonPackUtils());
		Running.set(ZipUtils.class, new ZipUtils());
	}

	public static void main(String[] args) {
		final PropertiesUtils propertiesUtils = Running.get(PropertiesUtils.class);
		propertiesUtils.load(args);
		String output = propertiesUtils.getProperty("output", "./output/");
		String type = propertiesUtils.getProperty("type", "zip").toLowerCase();
		String jsonOutput = propertiesUtils.getProperty("json-output");

		String host = propertiesUtils.getProperty("host", "127.0.0.1");
		String db = propertiesUtils.getProperty("db", "sys");
		String user = propertiesUtils.getProperty("user", "root");
		String password = propertiesUtils.getProperty("password", "");
		SimpleDb simpleDb = new SimpleDb(host, db, user, password);

		switch (type) {
			case "zip":
				new Db2Zip(simpleDb, output);
				break;
			case "json":
				new Db2Json(simpleDb, output);
				break;
			case "java":
				new Db2Java(simpleDb, output);
				break;
			case "javadao":
				new Db2JavaDao(simpleDb, output);
				break;
			case "javamodel":
				new Db2JavaModel(simpleDb, output);
				break;
			case "typescript":
			case "ts":
				new Db2TypeScript(simpleDb, output);
				break;
		}
		if (jsonOutput != null && !jsonOutput.isEmpty()) {
			new Db2Json(simpleDb, jsonOutput);
		}

	}
}
