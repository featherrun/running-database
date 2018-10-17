package running.database;

import java.util.List;

/**
 * local data cache
 */
public interface IDataSystem {
	<T> List<T> getList(Class<T> clazz);
	<T> T getObject(Class<T> clazz, Object primary);
	<T> List<T> getGroup(Class<T> clazz, String groupName, Object group);
	<T> void grouping(Class<T> clazz, String groupName);
	<T> void replace(Class<T> clazz, List<T> list);
}
