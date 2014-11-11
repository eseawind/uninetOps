package org.unism.util;

import java.util.List;

public class Def_type {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Def_type> def_types = StaticDataManage.getDef_types();
        for(Def_type def_type : def_types){
        	if(id!=null && def_type.getId().equals(String.valueOf(id))){
        		name = def_type.getName();
        		break;
        	}
        } 
        return name;
	}
	public Def_type(String id, String name){
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
