package org.unism.util;

import java.util.List;

/**
 * 表14.	控制设备信息表 控制设备使用状态
 * @author Wang_Yuliang
 *
 */
public class Gm_DevCtrl_Dect_state {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Gm_DevCtrl_Dect_state> gm_DevCtrl_Dect_states = StaticDataManage.getGm_DevCtrl_Dect_states();
        for(Gm_DevCtrl_Dect_state gm_DevCtrl_Dect_state : gm_DevCtrl_Dect_states){
        	if(id!=null && gm_DevCtrl_Dect_state.getId().equals(String.valueOf(id))){
        		name = gm_DevCtrl_Dect_state.getName();
        	}
        } 
        return name;
	}
	public Gm_DevCtrl_Dect_state(String id, String name){
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
