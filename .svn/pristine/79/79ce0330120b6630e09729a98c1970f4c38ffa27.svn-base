package org.unism.util;


import java.util.List;

/**
 * 表12.	智能设备状态表 是否清除历史数据
 * @author Wang_Yuliang
 *
 */
public class Dest_ifClearData {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Dest_ifClearData> dest_ifClearDatas = StaticDataManage.getDest_ifClearDatas();
        for(Dest_ifClearData dest_ifClearData : dest_ifClearDatas){
        	if(id!=null && dest_ifClearData.getId().equals(String.valueOf(id))){
        		name = dest_ifClearData.getName();
        	}
        } 
        return name;
	}
	public Dest_ifClearData(String id, String name){
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