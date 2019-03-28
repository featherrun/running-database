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
import running.util.JsonPackUtils;
import running.util.JsonUtils;
import running.util.PropertiesUtils;

public class Db2Json {
	final Logger logger = Running.getLogger(getClass());
	final FileUtils fileUtils = Running.get(FileUtils.class);
	final JsonUtils jsonUtils = Running.get(JsonUtils.class);
	final JsonPackUtils jsonPackUtils = Running.get(JsonPackUtils.class);
	final PropertiesUtils propertiesUtils = Running.get(PropertiesUtils.class);

	public Db2Json(final SimpleDb db, final String output) {
		Struct struct = new Struct();
		struct.load(db);
		String json = jsonUtils.stringify(struct.toData());

		if (propertiesUtils.getProperty("json-pack", "false").toLowerCase().equals("true")) {
			json = jsonPackUtils.pack(json);
		}

		//生成JSON
		String path = output;
		int i1 = path.lastIndexOf('.');
		int i2 = path.lastIndexOf('/');
		if (i1 <= i2) {
			fileUtils.mkdirs(output);
			path += "db.json";
		} else {
			fileUtils.mkdirs(output.substring(0, i2));
		}
		fileUtils.save(path, json);
		logger.info(path);
	}

}
