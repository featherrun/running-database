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

import running.core.Logger;
import running.core.Running;

import java.lang.reflect.Field;
import java.util.*;

/**
 * local data system
 */
public class SimpleDataSystem implements IDataSystem {
	protected final Logger logger = Running.getLogger(getClass());
	protected final Map<Class<?>, SimpleData<?>> cache;

	public SimpleDataSystem(final Struct struct, final String packageName) {
		this(struct, packageName, false, null);
	}

	/**
	 * constructor
	 *
	 * @param struct       database's struct
	 * @param packageName  entity's package
	 * @param unmodifiable use to checking some mistake
	 * @param copy         copy all replaced data
	 */
	public SimpleDataSystem(final Struct struct, final String packageName, final boolean unmodifiable, final SimpleDataSystem copy) {
		cache = new HashMap<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for (Struct.Table table : struct.getTables()) {
			String name = packageName + "." + table.getName();
			try {
				Class<?> clazz = classLoader.loadClass(name);
				if (copy != null) {
					SimpleData<?> copyData = copy.getData(clazz);
					if (copyData != null && copyData.copy) {
						cache.put(clazz, copyData);
						continue;
					}
				}
				SimpleData<?> data = new SimpleData<>(clazz);
				data.parse(table, unmodifiable);
				cache.put(clazz, data);
			} catch (ClassNotFoundException e) {
				logger.warn("ClassNotFound:" + name);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		logger.info("DataSystem's size:" + cache.size());
	}

	@Override
	public <T> List<T> getList(Class<T> clazz) {
		SimpleData<T> data = getData(clazz);
		return data != null ? data.getList() : null;
	}

	@Override
	public <T> T getObject(Class<T> clazz, Object primary) {
		SimpleData<T> data = getData(clazz);
		return data != null ? data.getMap().get(primary) : null;
	}

	@Override
	public <T> List<T> getGroup(Class<T> clazz, String groupName, Object group) {
		SimpleData<T> data = getData(clazz);
		if (data != null) {
			if (data.getGroupMap() != null) {
				Map<Object, List<T>> map = data.getGroupMap().get(groupName);
				if (map != null) {
					return map.get(group);
				}
			}
		}
		return null;
	}

	@Override
	public <T> void grouping(Class<T> clazz, String groupName) {
		SimpleData<T> data = getData(clazz);
		if (data != null) {
			try {
				data.grouping(groupName);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public <T> void replace(Class<T> clazz, List<T> list) {
		SimpleData<T> data = getData(clazz);
		if (data != null) {
			try {
				SimpleData<T> copy = data.copy();
				copy.replace(list);
				cache.put(clazz, copy);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> SimpleData<T> getData(Class<T> clazz) {
		return (SimpleData<T>) cache.get(clazz);
	}

	/**
	 * local data
	 * @param <T>
	 */
	public static class SimpleData<T> {
		protected Class<T> clazz;
		protected String primary;
		protected List<T> list;
		protected Map<Object, T> map;
		protected Map<String, Map<Object, List<T>>> groupMap;
		protected boolean copy;

		public SimpleData(final Class<T> clazz) {
			this.clazz = clazz;
		}

		public List<T> getList() {
			return list;
		}

		public Map<Object, T> getMap() {
			return map;
		}

		public Map<String, Map<Object, List<T>>> getGroupMap() {
			return groupMap;
		}

		public SimpleData<T> copy() {
			SimpleData<T> copy = new SimpleData<>(clazz);
			copy.primary = primary;
			copy.list = new LinkedList<>(list);
			copy.map = new HashMap<>(map);
			if (groupMap != null) {
				copy.groupMap = new HashMap<>(groupMap);
			}
			copy.copy = true;
			return copy;
		}

		/**
		 * parse struct and data
		 *
		 * @param table
		 * @param unmodifiable
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 */
		public void parse(final Struct.Table table, final boolean unmodifiable) throws InstantiationException, IllegalAccessException {
			final Logger logger = Running.getLogger(getClass());
			primary = table.primary;
			list = new LinkedList<>();
			map = new HashMap<>();

			Map<String, Field> fieldMap = new HashMap<>();
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				fieldMap.put(field.getName(), field);
			}

			if (table.getValues() != null) {
				for (String[] row : table.getValues()) {
					T obj = clazz.newInstance();
					list.add(obj);
					for (int i = 0, len = row.length; i < len; i++) {
						String val = row[i];
						Struct.Field f = table.getFields()[i];
						if (f == null) {
							logger.warn(table.getTableName() + " NOT FIELD(" + i + "): " + val);
							break;
						}

						Object v = f.getValue(val);
						Field field = fieldMap.get(f.getName());
						if (field != null) {
							field.set(obj, v);
						}

						if (primary != null) {
							if (primary.equals(f.getName())) {
								map.put(v, obj);
							}
						}
					}
				}
			}

			if (unmodifiable) {
				list = Collections.unmodifiableList(list);
			}
		}

		/**
		 * grouping by one field
		 *
		 * @param groupName
		 * @throws NoSuchFieldException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public void grouping(String groupName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
			Map<Object, List<T>> map = new HashMap<>();
			Field field = clazz.getDeclaredField(groupName);
			field.setAccessible(true);
			for (T obj : list) {
				Object val = field.get(obj);
				List<T> groupList = map.get(val);
				if (groupList == null) {
					groupList = new LinkedList<>();
					map.put(val, groupList);
				}
				groupList.add(obj);
			}

			if (groupMap == null) {
				groupMap = new HashMap<>();
			}
			groupMap.put(groupName, map);
		}

		/**
		 * change data
		 *
		 * @param list
		 * @return
		 * @throws NoSuchFieldException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public void replace(List<T> list) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
			this.list = list;

			if (primary != null) {
				map.clear();
				Field field = clazz.getDeclaredField(primary);
				field.setAccessible(true);
				for (T obj : list) {
					Object val = field.get(obj);
					map.put(val, obj);
				}
			}

			if (groupMap != null) {
				List<String> groupNameList = new LinkedList<>(groupMap.keySet());
				groupMap.clear();
				for (String groupName : groupNameList) {
					grouping(groupName);
				}
			}
		}

	}

}
