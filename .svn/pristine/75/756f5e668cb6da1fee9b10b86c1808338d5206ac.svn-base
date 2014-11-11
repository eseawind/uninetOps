package org.unism.util;

import java.util.List;

/**
 * 表12.	智能设备状态表 电源类型
 * @author Wang_Yuliang
 *
 */
public class Dest_powerType {
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Dest_powerType> dest_powerTypes = StaticDataManage.getDest_powerTypes();
        for(Dest_powerType dest_powerType : dest_powerTypes){
        	if(id!=null && dest_powerType.getId().equals(String.valueOf(id))){
        		name = dest_powerType.getName();
        	}
        } 
        return name;
	}
	public Dest_powerType(String id, String name){
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
