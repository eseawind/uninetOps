package org.unism.util;

/**
 * 故障信息---故障症状
 * @author Wang_Yuliang
 *
 */
public class Def_symptom {
	
	private Integer id;
	private String name;
	
	public Def_symptom(Integer id, String name){
		this.id = id;
		this.name = name;
	}	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
