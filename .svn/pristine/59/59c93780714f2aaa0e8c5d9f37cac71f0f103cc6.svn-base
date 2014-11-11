package org.unism.util;

import java.util.List;

public class Net_role {
	
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Net_role> net_roles = StaticDataManage.getNet_roles();
        for(Net_role net_role : net_roles){
        	if(id!=null && net_role.getId().equals(String.valueOf(id))){
        		name = net_role.getName();
        	}
        } 
        return name;
	}
	public Net_role(String id, String name){
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
