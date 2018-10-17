package running.database;

import java.util.List;

public interface IDb {
	/**
	 * 是否连接中
	 */
	boolean isConnecting();

	/**
	 * 获取名称
	 */
	String getName();

	/**
	 * 关闭连接
	 */
	void close();

	/**
	 * 查询
	 * @param clazz
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	<T> List<T> executeQuery(Class<T> clazz, String sql, Object ...params);

	/**
	 * 增删改
	 * @param sql
	 * @param params
	 * @return
	 */
	int executeUpdate(String sql, Object... params);
}
