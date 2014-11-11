package org.unism.util;

import java.util.List;

/**
 * 表12.	采集通道信息表 是否对外提供服务
 * @author Wang_Yuliang
 *
 */
public class Ch_offerSer {

	private String id;
	private String name;
	public static String findNameById(Object id){
		String name = null;
		List<Ch_offerSer> ch_offerSers = StaticDataManage.getCh_offerSers();
        for(Ch_offerSer ch_offerSer : ch_offerSers){
        	if(id!=null && ch_offerSer.getId().equals(String.valueOf(id))){
        		name = ch_offerSer.getName();
        	}
        } 
        return name;
	}
	public Ch_offerSer(String id, String name){
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
