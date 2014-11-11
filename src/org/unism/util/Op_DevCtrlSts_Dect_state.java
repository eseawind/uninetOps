package org.unism.util;

import java.util.List;

/**
 * 表17.	控制设备状态配置表 状态类型
 * @author Wang_Yuliang
 *
 */
public class Op_DevCtrlSts_Dect_state {
	
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Op_DevCtrlSts_Dect_state> op_DevCtrlSts_Dect_states = StaticDataManage.getOp_DevCtrlSts_Dect_states();
        for(Op_DevCtrlSts_Dect_state op_DevCtrlSts_Dect_state : op_DevCtrlSts_Dect_states){
        	if(id!=null && op_DevCtrlSts_Dect_state.getId().equals(String.valueOf(id))){
        		name = op_DevCtrlSts_Dect_state.getName();
        	}
        } 
        return name;
	}
	public Op_DevCtrlSts_Dect_state(String id, String name){
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
