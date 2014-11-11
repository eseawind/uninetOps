package org.unism.util;

import java.util.List;
/**
 * 表12.	智能设备状态表 是否临时在线
 * @author Wang_Yuliang
 *
 */
public class Dest_ifOnlineTmp {
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Dest_ifOnlineTmp> dest_ifOnlineTmps = StaticDataManage.getDest_ifOnlineTmps();
        for(Dest_ifOnlineTmp dest_ifOnlineTmp : dest_ifOnlineTmps){
        	if(id!=null && dest_ifOnlineTmp.getId().equals(String.valueOf(id))){
        		name = dest_ifOnlineTmp.getName();
        	}
        } 
        return name;
	}
	public Dest_ifOnlineTmp(String id, String name){
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
