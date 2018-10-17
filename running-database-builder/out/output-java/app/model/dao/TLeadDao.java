package app.model.dao;

import app.model.entity.TLead;
import running.database.IDao;
import running.database.IDb;

import java.util.List;

/**
 * Created by running-database-builder
 */
public class TLeadDao implements IDao<TLead> {
    public static final String SQL_SELECT = "SELECT * FROM `t_lead`";
    public static final String SQL_SELECT_INDEX = "SELECT * FROM `t_lead` WHERE @KEY=?";
    public static final String SQL_SELECT_PRIMARY = "SELECT * FROM `t_lead` WHERE uid=? LIMIT 1";
	public static final String SQL_INSERT = "INSERT INTO `t_lead` (uid,user,password,created,nickname,level) VALUES (?,?,?,?,?,?)";
	public static final String SQL_UPDATE = "UPDATE `t_lead` SET user=?,password=?,created=?,nickname=?,level=? WHERE uid=? LIMIT 1";
	public static final String SQL_DELETE = "DELETE FROM `t_lead` WHERE uid=? LIMIT 1";

	@Override
	public List<TLead> select(IDb db) {
		return db.executeQuery(TLead.class, SQL_SELECT);
	}

	@Override
    public List<TLead> select(IDb db, String key, Object value) {
        return db.executeQuery(TLead.class, SQL_SELECT_INDEX.replace("@KEY", key), value);
    }

    @Override
    public List<TLead> select(IDb db, Object primaryValue) {
        return db.executeQuery(TLead.class, SQL_SELECT_PRIMARY, primaryValue);
    }

	@Override
	public int insert(IDb db, TLead obj) {
		return db.executeUpdate(SQL_INSERT, obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid());
	}

	@Override
	public int update(IDb db, TLead obj) {
		return db.executeUpdate(SQL_UPDATE, obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid(), obj.getUid());
	}

	@Override
	public int delete(IDb db, TLead obj) {
		return db.executeUpdate(SQL_DELETE, obj.getUid());
	}
}
