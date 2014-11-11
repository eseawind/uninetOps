package org.unism.util;

import java.util.List;

/**
 * 采集通道使用状态
 * @author Wang_Yuliang
 *
 */
public class Ch_state {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Ch_state> ch_states = StaticDataManage.getCh_states();
        for(Ch_state ch_state : ch_states){
        	if(id!=null && ch_state.getId().equals(String.valueOf(id))){
        		name = ch_state.getName();
        	}
        } 
        return name;
	}
	public Ch_state(String id, String name){
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
