package running.database;

public interface IEntity {
	String database();
	String tableName();
	String primaryKey();
	Object primaryValue();

	/**
	 * set the source
	 * @param db
	 */
	void from(String db);

	/**
	 * mapping fields
	 * @param e
	 */
	void entries(running.core.Entry<String, Object> e);
}
