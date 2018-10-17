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

import running.core.ILogger;
import running.core.Running;
import running.database.SimpleDb;
import running.database.Struct;
import running.util.FileUtils;
import running.util.PropertiesUtils;
import running.util.ZipUtils;

import java.io.File;
import java.util.List;

/**
 * Created by featherrun on 2015/10/16.
 */
public class Db2Zip {
	final ILogger logger = Running.getLogger(getClass());
	final FileUtils fileUtils = Running.get(FileUtils.class);
	final ZipUtils zipUtils = Running.get(ZipUtils.class);
	final PropertiesUtils propertiesUtils = Running.get(PropertiesUtils.class);

	final String s_col = Struct.Table.s_col;
	final String s_row = Struct.Table.s_row;

	public Db2Zip(final SimpleDb db, final String output) {
		String path = output;
		String dir;
		int i1 = path.lastIndexOf('.');
		int i2 = path.lastIndexOf('/');
		if (i1 <= i2) {
			dir = output;
			path += "db.zip";
		} else {
			dir = output.substring(0, i2);
		}

		String tempOutput = propertiesUtils.getProperty("tempOutput", dir + "tmp/");
		fileUtils.delete(tempOutput);
		fileUtils.mkdirs(tempOutput);

		List<String> tables = db.getStringList("SHOW TABLES");
		for (String table : tables) {
			logger.info(table);

			List<String[]> createSql = db.getStringArrayList("SHOW CREATE TABLE " + table);
			String sql = createSql.get(0)[1] + ";";
			fileUtils.save(tempOutput + table + ".sql", sql);

			List<String[]> data = db.getStringArrayList("SELECT * FROM " + table);
			StringBuilder sb = new StringBuilder();
			for (String[] arr : data) {
				for (String s : arr) {
					sb.append(s).append(s_col);
				}
				sb.append(s_row);
			}
			fileUtils.save(tempOutput + table + ".txt", sb.toString());
		}

		//打包成ZIP
		fileUtils.mkdirs(dir);
		zipUtils.compress(new File(tempOutput), path, false);
		logger.info(path);
	}

}
