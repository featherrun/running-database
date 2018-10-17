package app.model.entity;

/**
 * 玩家表
 * Created by running-database-builder
 */
public final class TLead extends AbsEntity implements running.database.IEntity {
    private String _db;
	private String uid; //主键
	private String user; //账号
	private String password; //密码
	private int created; //创建时间
	private String nickname; //昵称
	private short level; //等级

	/**主键*/
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		if (assign(this, "uid", uid, this.uid)) {
			this.uid = uid;
		}
	}

	/**账号*/
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		if (assign(this, "user", user, this.user)) {
			this.user = user;
		}
	}

	/**密码*/
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if (assign(this, "password", password, this.password)) {
			this.password = password;
		}
	}

	/**创建时间*/
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		if (assign(this, "created", created, this.created)) {
			this.created = created;
		}
	}

	/**昵称*/
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		if (assign(this, "nickname", nickname, this.nickname)) {
			this.nickname = nickname;
		}
	}

	/**等级*/
	public short getLevel() {
		return level;
	}
	public void setLevel(short level) {
		if (assign(this, "level", level, this.level)) {
			this.level = level;
		}
	}


    @Override
    public String database() {
        return _db;
    }

    @Override
    public String tableName() {
        return "t_lead";
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
		e.entry("user", user);
		e.entry("password", password);
		e.entry("created", created);
		e.entry("nickname", nickname);
		e.entry("level", level);
    }
}
