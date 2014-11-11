package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "op_ledinfo")
public class Op_led implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8276521332587017182L;
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String led_id;//ID
	private String led_name; //LED名称
	private String led_no; //LED卡号 非空
	private String led_content; //内容
	private int led_interval; //更新间隔
	private int led_enable; //是否启用
	
	public int getLed_enable() {
		return led_enable;
	}
	public void setLed_enable(int led_enable) {
		this.led_enable = led_enable;
	}
	public String getLed_id() {
		return led_id;
	}
	public void setLed_id(String led_id) {
		this.led_id = led_id;
	}
	public String getLed_name() {
		return led_name;
	}
	public void setLed_name(String led_name) {
		this.led_name = led_name;
	}
	public String getLed_no() {
		return led_no;
	}
	public void setLed_no(String led_no) {
		this.led_no = led_no;
	}
	public String getLed_content() {
		return led_content;
	}
	public void setLed_content(String led_content) {
		this.led_content = led_content;
	}
	public int getLed_interval() {
		return led_interval;
	}
	public void setLed_interval(int led_interval) {
		this.led_interval = led_interval;
	}
	
	
}
