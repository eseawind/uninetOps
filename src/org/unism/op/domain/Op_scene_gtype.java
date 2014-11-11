package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "op_scene_gtype")
public class Op_scene_gtype implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5012241081006009198L;

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String id;
	
	@Column(name = "scene_gtype")
	private Integer gtype;
	
	@Column(name = "scene_gtype_name")
	private String name;
	
	@Column(name = "scene_gtype_desc")
	private String desc;

	public String getDesc() {
		return desc;
	}

	public Integer getGtype() {
		return gtype;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setGtype(Integer gtype) {
		this.gtype = gtype;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
