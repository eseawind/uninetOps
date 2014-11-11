package org.unism.phone.domain;

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
import org.unism.gm.domain.Gm_DevCtrl;

@Entity
@Table( name = "phone_devctrl" )
public class Phone_DevCtrl implements Serializable {
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String phone_id; //ID
	private String phone_no; //手机号
	private String phone_dev_id; //手机设备编号
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id = new Gm_DevCtrl(); //对应控制设备ID
	
	public String getPhone_id() {
		return phone_id;
	}
	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getPhone_dev_id() {
		return phone_dev_id;
	}
	public void setPhone_dev_id(String phone_dev_id) {
		this.phone_dev_id = phone_dev_id;
	}
	public Gm_DevCtrl getDect_id() {
		return dect_id;
	}
	public void setDect_id(Gm_DevCtrl dect_id) {
		this.dect_id = dect_id;
	}
	
}
