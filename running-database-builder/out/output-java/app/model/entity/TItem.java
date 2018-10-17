package app.model.entity;

/**
 * 玩家物品表
 * Created by running-database-builder
 */
public final class TItem extends AbsEntity implements running.database.IEntity {
    private String _db;
	private String uid; //主键
	private String leadUid; //玩家ID
	private String itemId; //物品ID
	private int itemCount; //数量

	/**主键*/
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		if (assign(this, "uid", uid, this.uid)) {
			this.uid = uid;
		}
	}

	/**玩家ID*/
	public String getLeadUid() {
		return leadUid;
	}
	public void setLeadUid(String leadUid) {
		if (assign(this, "lead_uid", leadUid, this.leadUid)) {
			this.leadUid = leadUid;
		}
	}

	/**物品ID*/
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		if (assign(this, "itemId", itemId, this.itemId)) {
			this.itemId = itemId;
		}
	}

	/**数量*/
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		if (assign(this, "itemCount", itemCount, this.itemCount)) {
			this.itemCount = itemCount;
		}
	}


    @Override
    public String database() {
        return _db;
    }

    @Override
    public String tableName() {
        return "t_item";
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
		e.entry("lead_uid", leadUid);
		e.entry("itemId", itemId);
		e.entry("itemCount", itemCount);
    }
}
