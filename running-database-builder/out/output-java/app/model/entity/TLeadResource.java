package app.model.entity;

/**
 * 玩家资源扩展表
 * Created by running-database-builder
 */
public final class TLeadResource extends AbsEntity implements running.database.IEntity {
    private String _db;
	private String uid; //主键
	private int gold; //金币

	/**主键*/
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		if (assign(this, "uid", uid, this.uid)) {
			this.uid = uid;
		}
	}

	/**金币*/
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		if (assign(this, "gold", gold, this.gold)) {
			this.gold = gold;
		}
	}


    @Override
    public String database() {
        return _db;
    }

    @Override
    public String tableName() {
        return "t_lead_resource";
    }

    @Override
	public String primaryKey() {
		return "uid";
	}

    @Override
	public Object primaryValue() {
		return uid;
	}

	@Override
	public void from(String db) {
	    _db = db;
	}

    @Override
	public void entries(running.core.Entry<String, Object> e) {
		e.entry("uid", uid);
		e.entry("gold", gold);
    }
}
