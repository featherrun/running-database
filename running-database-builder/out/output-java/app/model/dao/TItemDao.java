package app.model.dao;

import app.model.entity.TItem;
import running.database.IDao;
import running.database.IDb;

import java.util.List;

/**
 * Created by running-database-builder
 */
public class TItemDao implements IDao<TItem> {
    public static final String SQL_SELECT = "SELECT * FROM `t_item`";
    public static final String SQL_SELECT_INDEX = "SELECT * FROM `t_item` WHERE @KEY=?";
    public static final String SQL_SELECT_PRIMARY = "SELECT * FROM `t_item` WHERE uid=? LIMIT 1";
	public static final String SQL_INSERT = "INSERT INTO `t_item` (uid,lead_uid,itemId,itemCount) VALUES (?,?,?,?)";
	public static final String SQL_UPDATE = "UPDATE `t_item` SET lead_uid=?,itemId=?,itemCount=? WHERE uid=? LIMIT 1";
	public static final String SQL_DELETE = "DELETE FROM `t_item` WHERE uid=? LIMIT 1";

	@Override
	public List<TItem> select(IDb db) {
		return db.executeQuery(TItem.class, SQL_SELECT);
	}

	@Override
    public List<TItem> select(IDb db, String key, Object value) {
        return db.executeQuery(TItem.class, SQL_SELECT_INDEX.replace("@KEY", key), value);
    }

    @Override
    public List<TItem> select(IDb db, Object primaryValue) {
        return db.executeQuery(TItem.class, SQL_SELECT_PRIMARY, primaryValue);
    }

	@Override
	public int insert(IDb db, TItem obj) {
		return db.executeUpdate(SQL_INSERT, obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid());
	}

	@Override
	public int update(IDb db, TItem obj) {
		return db.executeUpdate(SQL_UPDATE, obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid());
	}

	@Override
	public int delete(IDb db, TItem obj) {
		return db.executeUpdate(SQL_DELETE, obj.getUid());
	}
}
