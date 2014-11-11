package org.unism.util;

/**
 * 表19.	控制设备状态历史表 控制设备状态
 * @author Wang_Yulinag
 */
public class Dect_state {

	private String id;
	private String name;	
	public Dect_state(String id, String name){
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
