package org.unism.util;

/**
 * 表20.	控制设备操作表 操作类型信息
 * @author Wang_Yuliang
 *
 */
public class Deco_type {
	private String id;
	private String name;
	
	public Deco_type(String id, String name){
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
