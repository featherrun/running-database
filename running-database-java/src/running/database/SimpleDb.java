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

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 简单的Mysql操作封装 （非线程安全）
 * @author featherrun
 * @version 2015/4/16
 */
public class SimpleDb implements IDb {
	protected final Logger logger = Running.getLogger(getClass());
	protected final SimpleMapper mapper = new SimpleMapper();
	protected static long maxIdleTime = 30*60*1000; //最长闲置时间（毫秒）
	protected boolean normalize = true;

	protected String url;
	protected String db;
	protected String user;
	protected String password;
	protected Connection conn;
	protected long lastConnectTime;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param host
	 * @param db
	 * @param user
	 * @param password
	 */
	public SimpleDb(String host, String db, String user, String password) {
		this.url = "jdbc:mysql://"+host+":3306/"+db+"?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=true";
		this.db = db.intern();
		this.user = user;
		this.password = password;
	}

	/**
	 * 字段名自动格式化
	 * @param normalize
	 */
	public void setNormalize(boolean normalize) {
		this.normalize = normalize;
	}

	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		long curr = System.currentTimeMillis();
		if (conn != null) {
			if (curr - lastConnectTime > maxIdleTime) { //闲置太久则重新连接
				close();
			}
		}
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url, user, password);
			lastConnectTime = curr;
		}
		return conn;
	}

	@Override
	public boolean isConnecting() {
		try {
			Connection conn = getConnection();
			return conn != null && !conn.isClosed();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getName() {
		return db;
	}

	@Override
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			conn = null;
		}
	}

	@Override
	public <T> List<T> executeQuery(Class<T> clazz, String sql, Object ...params) {
		List<T> res = new LinkedList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConnection().prepareStatement(sql);
			int len = params.length;
			for (int i=1; i<=len; i++) {
				st.setObject(i, params[i-1]);
			}
			rs = st.executeQuery();

			SimpleMapper.QueryData queryData = mapper.getQueryData(rs, clazz);

			while (rs.next()) {
				T obj = mapper.getObject(rs, clazz, queryData, normalize);
				if (obj != null) {
					res.add(obj);
				}
			}

		} catch (Exception e) {
			logger.error(sql+"\n"+e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ignored) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignored) {
				}
			}
		}
		return res;
	}

	/**
	 * 查询单个
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public <T> T executeQueryOne(Class<T> clazz, String sql, Object ...params) {
		List<T> list = executeQuery(clazz, sql, params);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public int executeUpdate(String sql, Object... params) {
		try {
			PreparedStatement st = getConnection().prepareStatement(sql);
			int len = params.length;
			for (int i=1; i<=len; i++) {
				st.setObject(i, params[i-1]);
			}
			int res = st.executeUpdate();
			st.close();
			if (res <= 0) {
				logger.warn("Invalid SQL:"+sql);
			}
			return res;
		} catch (SQLException e) {
			logger.error(e.getMessage() + " SQL:"+sql, e);
			return -1;
		}
	}

	/**
	 * 执行简单SQL
	 * @param sql
	 * @return
	 */
	public boolean execute(String sql) {
		try {
			Statement st = getConnection().createStatement();
			boolean res = st.execute(sql);
			st.close();
			return res;
		} catch (SQLException e) {
			logger.error(sql+"\n"+e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 查询一串字符串
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<String> getStringList(String sql, Object ...params) {
		List<String> res = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConnection().prepareStatement(sql);
			int len = params.length;
			for (int i=1; i<=len; i++) {
				st.setObject(i, params[i-1]);
			}
			rs = st.executeQuery();
			while (rs.next()) {
				res.add(rs.getString(1));
			}
		} catch (Exception e) {
			logger.error(sql+"\n"+e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ignored) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignored) {
				}
			}
		}
		return res;
	}

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<String[]> getStringArrayList(String sql, Object... params) {
		List<String[]> res = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConnection().prepareStatement(sql);
			int len = params.length;
			for (int i=1; i<=len; i++) {
				st.setObject(i, params[i-1]);
			}
			rs = st.executeQuery();

			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				String[] arr = new String[col];
				res.add(arr);
				for (int i=1; i<=col; i++) {
					arr[i-1] = rs.getString(i);
				}
			}
		} catch (Exception e) {
			logger.error(sql+"\n"+e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ignored) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignored) {
				}
			}
		}
		return res;
	}
}

