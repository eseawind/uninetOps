package org.unism.util;

import java.util.List;

/**
 * 表14.	控制设备信息表 是否对外提供服务
 * @author Wang_Yuliang
 *
 */
public class Gm_DevCtrl_Ch_offerSer {
	
	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Gm_DevCtrl_Ch_offerSer> gm_DevCtrl_Ch_offerSers = StaticDataManage.getGm_DevCtrl_Ch_offerSers();
        for(Gm_DevCtrl_Ch_offerSer gm_DevCtrl_Ch_offerSer : gm_DevCtrl_Ch_offerSers){
        	if(id!=null && gm_DevCtrl_Ch_offerSer.getId().equals(String.valueOf(id))){
        		name = gm_DevCtrl_Ch_offerSer.getName();
        	}
        } 
        return name;
	}
	public Gm_DevCtrl_Ch_offerSer(String id, String name){
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
