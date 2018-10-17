package app.model.dao;

import app.model.entity.TLeadResource;
import running.database.IDao;
import running.database.IDb;

import java.util.List;

/**
 * Created by running-database-builder
 */
public class TLeadResourceDao implements IDao<TLeadResource> {
    public static final String SQL_SELECT = "SELECT * FROM `t_lead_resource`";
    public static final String SQL_SELECT_INDEX = "SELECT * FROM `t_lead_resource` WHERE @KEY=?";
    public static final String SQL_SELECT_PRIMARY = "SELECT * FROM `t_lead_resource` WHERE uid=? LIMIT 1";
	public static final String SQL_INSERT = "INSERT INTO `t_lead_resource` (uid,gold) VALUES (?,?)";
	public static final String SQL_UPDATE = "UPDATE `t_lead_resource` SET gold=? WHERE uid=? LIMIT 1";
	public static final String SQL_DELETE = "DELETE FROM `t_lead_resource` WHERE uid=? LIMIT 1";

	@Override
	public List<TLeadResource> select(IDb db) {
		return db.executeQuery(TLeadResource.class, SQL_SELECT);
	}

	@Override
    public List<TLeadResource> select(IDb db, String key, Object value) {
        return db.executeQuery(TLeadResource.class, SQL_SELECT_INDEX.replace("@KEY", key), value);
    }

    @Override
    public List<TLeadResource> select(IDb db, Object primaryValue) {
        return db.executeQuery(TLeadResource.class, SQL_SELECT_PRIMARY, primaryValue);
    }

	@Override
	public int insert(IDb db, TLeadResource obj) {
		return db.executeUpdate(SQL_INSERT, obj.getUid(), obj.getUid());
	}

	@Override
	public int update(IDb db, TLeadResource obj) {
		return db.executeUpdate(SQL_UPDATE, obj.getUid(), obj.getUid());
	}

	@Override
	public int delete(IDb db, TLeadResource obj) {
		return db.executeUpdate(SQL_DELETE, obj.getUid());
	}
}
