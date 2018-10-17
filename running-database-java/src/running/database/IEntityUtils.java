package running.database;

import java.util.List;

public interface IEntityUtils {
	/**
	 * 查找列表
	 */
	<T extends IEntity> List<T> select(Class<T> clazz, IDb db);
	<T extends IEntity> List<T> select(Class<T> clazz, IDb db, String whereField, Object whereValue);

	/**
	 * 查找对象
	 */
	<T extends IEntity> T single(Class<T> clazz, IDb db);
	<T extends IEntity> T single(Class<T> clazz, IDb db, Object primary);

	/**
	 * 提交修改
	 */
	int submit(IEntity entity);
}
