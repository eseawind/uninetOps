package org.unism.util;

import java.util.List;
/**
 * 控制类型信息
 * @author Wang_Yuliang
 *
 */
public class Dect_ctlType {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Dect_ctlType> dect_ctlTypes = StaticDataManage.getDect_ctlTypes();
        for(Dect_ctlType dect_ctlType : dect_ctlTypes){
        	if(id!=null && dect_ctlType.getId().equals(String.valueOf(id))){
        		name = dect_ctlType.getName();
        	}
        } 
        return name;
	}
	public Dect_ctlType(String id, String name){
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
