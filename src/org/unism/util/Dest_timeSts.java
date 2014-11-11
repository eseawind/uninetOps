package org.unism.util;

/**
 * 设备时间状态
 * @author Wang_Yuliang
 */
public class Dest_timeSts {
	private String id;
	private String name;
	
	public Dest_timeSts(String id, String name){
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
