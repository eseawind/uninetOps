package org.unism.util;

import java.util.List;

public class Scene_ctype {
	
	private String id;
	private String name;
	
	public static String findNameById(Object id){
		String name = null;
		List<Scene_ctype> scene_ctypes = StaticDataManage.getScene_ctypes();
        for(Scene_ctype scene_ctype : scene_ctypes){
        	if(id!=null && scene_ctype.getId().equals(String.valueOf(id))){
        		name = scene_ctype.getName();
        	}
        } 
        return name;
	}
	
	public Scene_ctype(String id, String name){
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
