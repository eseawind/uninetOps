package org.unism.util;

import java.util.ArrayList;
import java.util.List;

public class Net_type {

	private String id;
	private String name;
	public Net_type(String id, String name){
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
	public List<Net_role> getNet_roles(){
		List<Net_role> net_roles = new ArrayList<Net_role>();
		if(this.id.equals("0")){
			net_roles.add(new Net_role("01","网关"));
			net_roles.add(new Net_role("02","独立设备"));
			net_roles.add(new Net_role("03","移动设备"));
		}else if(this.id.equals("1")){
			net_roles.add(new Net_role("11","RD"));
			net_roles.add(new Net_role("12","ED"));
			net_roles.add(new Net_role("13","CD"));	
			net_roles.add(new Net_role("14","LD"));	
		}else if(this.id.equals("2")){
			net_roles.add(new Net_role("21","传感器"));
			net_roles.add(new Net_role("22","变送器"));
		}
		return net_roles;
	}
}
