package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "op_led_scene")
public class Op_led_scene implements Serializable {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String id; //ID
	
	private String scene_name;
	
	private Integer scene_gtype;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "led_id")
	private Op_led led_id;//LED屏
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id;//场景ID 关联项目信息表

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Op_led getLed_id() {
		return led_id;
	}

	public void setLed_id(Op_led led_id) {
		this.led_id = led_id;
	}

	public Op_Scene getScene_id() {
		return scene_id;
	}

	public void setScene_id(Op_Scene scene_id) {
		this.scene_id = scene_id;
	}

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public Integer getScene_gtype() {
		return scene_gtype;
	}

	public void setScene_gtype(Integer scene_gtype) {
		this.scene_gtype = scene_gtype;
	}

	
}
