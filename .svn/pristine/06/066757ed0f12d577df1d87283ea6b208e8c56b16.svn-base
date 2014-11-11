package org.unism.gm.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.unism.op.domain.Op_PlantformUser;

/**
 * 控制设备操作表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devctrloperate" )
public class Gm_DevCtrlOperate {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String deco_id; //Id
	private String deco_userId; //操作用户ID
	private String deco_userName; //操作用户姓名
	private Integer deco_userType; //用户类型 0:运维，1：应用（固定）
	private Integer deco_type; //操作类型 1：开（正向），2：停 3：关（反向）；其它无效
	private Date deco_time; //请求时间
	private Integer deco_state; //操作状态 0:空闲 1：操作请求（应用写） 2：命令下发 3：命令返回 4：处理完成
	private Integer deco_result; //返回结果 默认0（等待中）
	private Integer deco_errorCode; //返回错误码
	private Integer deco_sort; //控制分类 0 网页控制  1 短信控制
	private String deco_userIp; //用户IP
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id = new Gm_DevCtrl(); //控制设备ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "pla_id")
	private Op_PlantformUser pla_id = new Op_PlantformUser(); //平台用户ID
	
	public String getDeco_id() {
		return deco_id;
	}
	public void setDeco_id(String deco_id) {
		this.deco_id = deco_id;
	}
	public String getDeco_userId() {
		return deco_userId;
	}
	public void setDeco_userId(String deco_userId) {
		this.deco_userId = deco_userId;
	}
	public String getDeco_userName() {
		return deco_userName;
	}
	public void setDeco_userName(String deco_userName) {
		this.deco_userName = deco_userName;
	}
	public Integer getDeco_userType() {
		return deco_userType;
	}
	public void setDeco_userType(Integer deco_userType) {
		this.deco_userType = deco_userType;
	}
	public Integer getDeco_type() {
		return deco_type;
	}
	public void setDeco_type(Integer deco_type) {
		this.deco_type = deco_type;
	}
	public Date getDeco_time() {
		return deco_time;
	}
	public void setDeco_time(Date deco_time) {
		this.deco_time = deco_time;
	}
	public Integer getDeco_state() {
		return deco_state;
	}
	public void setDeco_state(Integer deco_state) {
		this.deco_state = deco_state;
	}
	public Integer getDeco_result() {
		return deco_result;
	}
	public void setDeco_result(Integer deco_result) {
		this.deco_result = deco_result;
	}
	public Integer getDeco_errorCode() {
		return deco_errorCode;
	}
	public void setDeco_errorCode(Integer deco_errorCode) {
		this.deco_errorCode = deco_errorCode;
	}
	public Gm_DevCtrl getDect_id() {
		return dect_id;
	}
	public void setDect_id(Gm_DevCtrl dect_id) {
		this.dect_id = dect_id;
	}
	public Op_PlantformUser getPla_id() {
		return pla_id;
	}
	public void setPla_id(Op_PlantformUser pla_id) {
		this.pla_id = pla_id;
	}
	public Integer getDeco_sort() {
		return deco_sort;
	}
	public void setDeco_sort(Integer deco_sort) {
		this.deco_sort = deco_sort;
	}
	public String getDeco_userIp() {
		return deco_userIp;
	}
	public void setDeco_userIp(String deco_userIp) {
		this.deco_userIp = deco_userIp;
	}
	
}
