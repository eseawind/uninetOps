package org.unism.util;

import java.util.List;

public class Def_object {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Def_object> def_objects = StaticDataManage.getDef_objects();
        for(Def_object def_object : def_objects){
        	if(id!=null && def_object.getId().equals(String.valueOf(id))){
        		name = def_object.getName();
        		break;
        	}
        } 
        return name;
	}
	public Def_object(String id, String name){
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
