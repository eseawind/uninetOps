package org.unism.phone.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tblsendsms" )
public class TblSendSMS implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id; //ID
	private String mobileNumber; //手机号
	private String sendmsg; //发送内容
	private Integer bhz; //
	private String flag; //标识
	private Date dd; //发送时间

	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSendmsg() {
		return sendmsg;
	}
	public void setSendmsg(String sendmsg) {
		this.sendmsg = sendmsg;
	}
	public Integer getBhz() {
		return bhz;
	}
	public void setBhz(Integer bhz) {
		this.bhz = bhz;
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
