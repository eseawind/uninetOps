package org.unism.gm.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.unism.util.Deco_errorCode;
import org.unism.util.Deco_result;
import org.unism.util.Deco_type;
import org.unism.util.Deco_userType;
import org.unism.util.StaticDataManage;

/**
 * 控制设备操作记录表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devctrloperatehistory" )
public class Gm_DevCtrlOperateHistory {
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String deco_id; //Id
	private String deco_userId; //操作用户ID
	private String deco_userName; //操作用户姓名
	private Integer deco_userType; //用户类型 0: 运维 1: 应用（固定）
	private String pla_id; //平台用户ID
	private Integer deco_type; //操作类型 1：开（正向），2：停 3：关（反向）；其它无效
	private Date deco_time; //请求时间
	private Integer deco_result; //0 无效 1 成功 2 失败
	private Integer deco_errorCode; //返回错误码
	private String deco_userIp; //用户IP
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id = new Gm_DevCtrl(); //控制设备ID

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

	public String getDeco_userType_str(){
		if(this.deco_userType!=null){
			List<Deco_userType> deco_userTypes = StaticDataManage.getDeco_userTypes();
			for(Deco_userType deco_userType : deco_userTypes){
				if(String.valueOf(this.deco_userType).equals(deco_userType.getId())){
					return deco_userType.getName();
				}
			}
		}
		return "";
	}
	
	public void setDeco_userType(Integer deco_userType) {
		this.deco_userType = deco_userType;
	}

	public String getPla_id() {
		return pla_id;
	}

	public void setPla_id(String pla_id) {
		this.pla_id = pla_id;
	}

	public Integer getDeco_type() {
		return deco_type;
	}
	
	public String getDeco_type_str(){
		if(this.deco_type!=null){
			List<Deco_type> deco_types = StaticDataManage.getDeco_types();
			for(Deco_type deco_type : deco_types){
				if(String.valueOf(this.deco_type).equals(deco_type.getId())){
					return deco_type.getName();
				}
			}
		}
		return "";
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

	public Integer getDeco_result() {
		return deco_result;
	}
	
	public String getDeco_result_str(){
		if(this.deco_result!=null){
			List<Deco_result> deco_results = StaticDataManage.getDeco_results();
			for(Deco_result deco_result : deco_results){
				if(String.valueOf(this.deco_result).equals(deco_result.getId())){
					return deco_result.getName();
				}
			}
		}
		return "";
	}

	public void setDeco_result(Integer deco_result) {
		this.deco_result = deco_result;
	}

	public Integer getDeco_errorCode() {
		return deco_errorCode;
	}

	public String getDeco_errorCode_str(){
		if(this.deco_errorCode!=null){
			List<Deco_errorCode> deco_errorCodes = StaticDataManage.getDeco_errorCodes();
			for(Deco_errorCode deco_errorCode : deco_errorCodes){
				if(String.valueOf(this.deco_errorCode).equals(deco_errorCode.getId())){
					return deco_errorCode.getName();
				}
			}
		}
		return "";
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

	public String getDeco_userIp() {
		return deco_userIp;
	}

	public void setDeco_userIp(String deco_userIp) {
		this.deco_userIp = deco_userIp;
	}

}
