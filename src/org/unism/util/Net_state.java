package org.unism.util;

import java.util.List;

/**
 * 网络使用状态信息
 * @author Wang_Yuliang
 *
 */
public class Net_state {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Net_state> net_states = StaticDataManage.getNet_states();
        for(Net_state net_state : net_states){
        	if(id!=null && net_state.getId().equals(String.valueOf(id))){
        		name = net_state.getName();
        	}
        } 
        return name;
	}
	public Net_state(String id, String name){
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
