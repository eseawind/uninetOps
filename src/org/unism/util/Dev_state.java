package org.unism.util;

import java.util.List;

/**
 * 设备使用状态信息
 * @author Wang_Yuliang
 *
 */
public class Dev_state {
	
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Dev_state> dev_states = StaticDataManage.getDev_states();
        for(Dev_state dev_state : dev_states){
        	if(id!=null && dev_state.getId().equals(String.valueOf(id))){
        		name = dev_state.getName();
        	}
        } 
        return name;
	}
	public Dev_state(String id, String name){
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
