package org.unism.util;

import java.util.List;

public class Scene_gtype {

	private String id;
	private String name;
	
	public static String findNameById(Object id){
		String name = null;
		List<Scene_gtype> scene_gtypes = StaticDataManage.getScene_gtypes();
        for(Scene_gtype scene_gtype : scene_gtypes){
        	if(id!=null && scene_gtype.getId().equals(String.valueOf(id))){
        		name = scene_gtype.getName();
        	}
        } 
        return name;
	}
	
	public Scene_gtype(String id, String name){
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
