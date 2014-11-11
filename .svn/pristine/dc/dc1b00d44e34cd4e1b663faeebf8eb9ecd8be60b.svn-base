package org.unism.util;

import java.util.List;

public class Ch_procesStyle {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Ch_procesStyle> ch_procesStyles = StaticDataManage.getCh_procesStyles();
        for(Ch_procesStyle ch_procesStyle : ch_procesStyles){
        	if(id!=null && ch_procesStyle.getId().equals(String.valueOf(id))){
        		name = ch_procesStyle.getName();
        	}
        } 
        return name;
	}
	public Ch_procesStyle(String id, String name){
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
