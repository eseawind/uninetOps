package org.unism.util;

import java.util.List;

/**
 *封装场景大类的实体
 *@author Wang_Yuliang
 */
public class Scene_type {

	private String id;
	private String name;
	
	public static String findNameById(Object id){
		String name = null;
		List<Scene_type> scene_types = StaticDataManage.getScene_types();
        for(Scene_type scene_type : scene_types){
        	if(id!=null && scene_type.getId().equals(String.valueOf(id))){
        		name = scene_type.getName();
        	}
        } 
        return name;
	}
	
	public Scene_type(String id, String name){
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
