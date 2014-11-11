package org.unism.gm.domain;

import java.util.ArrayList;
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
import org.unism.util.Decst_valid;
import org.unism.util.Dect_state;
import org.unism.util.StaticDataManage;

/**
 * 控制设备状态历史表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devctrlstshistory" )
public class Gm_DevCtrlStsHistory {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String decst_id; //Id
	private Integer dect_state; //设备状态 0未知 1 开 2 关 3 停 
	private Integer decst_valid; //有效性 0 无效 1 有效
	private Date decst_time; //状态变更时间
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id = new Gm_DevCtrl(); //控制设备ID

	public String getDecst_id() {
		return decst_id;
	}

	public void setDecst_id(String decst_id) {
		this.decst_id = decst_id;
	}

	public Integer getDect_state() {
		return dect_state;
	}

	public String getDect_state_str(){
		if(this.dect_state!=null){
			List<Dect_state> dect_states = StaticDataManage.getDect_states();
			for(Dect_state dect_state : dect_states){
				if(String.valueOf(this.dect_state).equals(dect_state.getId())){
					return dect_state.getName();
				}
			}
		}
		return "";
	}
	
	public void setDect_state(Integer dect_state) {
		this.dect_state = dect_state;
	}

	public Integer getDecst_valid() {
		return decst_valid;
	}

	public String getDecst_valid_str(){
		if(this.decst_valid!=null){
			List<Decst_valid> decst_valids = new ArrayList<Decst_valid>();
			for(Decst_valid decst_valid : decst_valids){
				if(String.valueOf(this.decst_valid).equals(decst_valid.getId())){
					return decst_valid.getName();
				}
			}
		}
		return "";
	}
	
	public void setDecst_valid(Integer decst_valid) {
		this.decst_valid = decst_valid;
	}

	public Date getDecst_time() {
		return decst_time;
	}

	public void setDecst_time(Date decst_time) {
		this.decst_time = decst_time;
	}

	public Gm_DevCtrl getDect_id() {
		return dect_id;
	}

	public void setDect_id(Gm_DevCtrl dect_id) {
		this.dect_id = dect_id;
	}
}
