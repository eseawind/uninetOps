package org.unism.util;

import java.util.List;

/**
 * 供应商类型信息
 * @author Wang_Yuliang
 *
 */
public class Sup_type {
	
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Sup_type> sup_types = StaticDataManage.getSup_types();
        for(Sup_type sup_type : sup_types){
        	if(id!=null && sup_type.getId().equals(String.valueOf(id))){
        		name = sup_type.getName();
        	}
        } 
        return name;
	}
	public Sup_type(String id, String name){
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
