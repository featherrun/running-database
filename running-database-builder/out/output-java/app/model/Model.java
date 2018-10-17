package app.model;

import app.model.entity.*;

import java.util.LinkedList;
import java.util.List;

/**
 * TLead model
 * Created by running-database-builder
 */
public class Model {
    private TLead data; //玩家表
	private List<TItem> itemList; //玩家物品表
	private TLeadResource resource; //玩家资源扩展表

    /**
     * data's database
     */
    public String database() {
        return this.data.database();
    }

    /**玩家表*/
	public TLead getData() {
		return data;
	}
	public void setData(TLead data) {
        this.data = data;
	}

	/**玩家物品表*/
	public List<TItem> getItemList() {
		return itemList;
	}
	public void addItemList(TItem item) {
		if (itemList == null) itemList = new LinkedList<>();
		itemList.add(item)
		item.from(database());
	}

	/**玩家资源扩展表*/
	public TLeadResource getResource() {
		return resource;
	}
	public void setResource(TLeadResource resource) {
		this.resource = resource;
		resource.from(database());
	}


}
