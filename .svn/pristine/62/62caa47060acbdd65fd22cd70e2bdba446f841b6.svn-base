package org.unism.phone.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tblreceive" )
public class TblReceive implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id; //ID
	private String mobileNumber; //手机号
	private String receivemsg; //接受内容
	private String flag; //标识
	private Date dd; //接收时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getReceivemsg() {
		return receivemsg;
	}
	public void setReceivemsg(String receivemsg) {
		this.receivemsg = receivemsg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Date getDd() {
		return dd;
	}
	public void setDd(Date dd) {
		this.dd = dd;
	}

	
}
