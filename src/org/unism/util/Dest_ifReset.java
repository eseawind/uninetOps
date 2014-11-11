package org.unism.util;

import java.util.List;
/**
 * 表12.	智能设备状态表 是否重启
 * @author Wang_Yuliang
 *
 */
public class Dest_ifReset {
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Dest_ifReset> dest_ifResets = StaticDataManage.getDest_ifResets();
        for(Dest_ifReset dest_ifReset : dest_ifResets){
        	if(id!=null && dest_ifReset.getId().equals(String.valueOf(id))){
        		name = dest_ifReset.getName();
        	}
        } 
        return name;
	}
	public Dest_ifReset(String id, String name){
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
