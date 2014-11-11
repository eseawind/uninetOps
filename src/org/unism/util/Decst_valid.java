package org.unism.util;

/**
 * 表19.	控制设备状态历史表 有效性
 * @author Wang_Yuliang
 */
public class Decst_valid {

	private String id;
	private String name;
	
	public Decst_valid(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
