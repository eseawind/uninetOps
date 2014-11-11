package org.unism.util;

import java.util.List;

/**
 * 表16.	控制设备按钮配置表 操作类型信息
 * @author Wang_Yuliang
 *
 */
public class Op_DevCtrlBtn_Deco_type {
	
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Op_DevCtrlBtn_Deco_type> op_DevCtrlBtn_Deco_types = StaticDataManage.getOp_DevCtrlBtn_Deco_types();
        for(Op_DevCtrlBtn_Deco_type op_DevCtrlBtn_Deco_type : op_DevCtrlBtn_Deco_types){
        	if(id!=null && op_DevCtrlBtn_Deco_type.getId().equals(String.valueOf(id))){
        		name = op_DevCtrlBtn_Deco_type.getName();
        	}
        } 
        return name;
	}
	public Op_DevCtrlBtn_Deco_type(String id, String name){
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
