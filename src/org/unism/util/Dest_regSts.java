package org.unism.util;

/**
 * 注册身份信息
 * @author Wang_Yuliang
 */
public class Dest_regSts {

	private String id; //value
	private String name; //name
	
	public Dest_regSts(String id, String name){
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
