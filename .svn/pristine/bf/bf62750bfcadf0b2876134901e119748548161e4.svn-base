package org.unism.util;

import java.util.List;

public class Net_linkSts {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Net_linkSts> net_linkStss = StaticDataManage.getNet_linkStss();
        for(Net_linkSts net_linkSts : net_linkStss){
        	if(id!=null && net_linkSts.getId().equals(String.valueOf(id))){
        		name = net_linkSts.getName();
        	}
        } 
        return name;
	}
	public Net_linkSts(String id, String name){
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
