package running.database;

import java.util.List;

/**
 * Data Access Object
 */
public interface IDao<T> {
	List<T> select(IDb db);
	List<T> select(IDb db, String key, Object value);
	List<T> select(IDb db, Object primaryValue);
	int insert(IDb db, T obj);
	int update(IDb db, T obj);
	int delete(IDb db, T obj);
}
