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
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevCtrl;

@Entity
@Table( name = "op_devctrlsts" )
public class Op_DevCtrlSts implements Serializable {

	private static final long serialVersionUID = 8147695503413282279L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String dectSts_id; //ID
	private String dectSts_name; //状态名称
	private Integer dect_state; //状态类型 1：开（正向），2：停 3：关（反向）；4：运行；其它无效
	private Float ch_max; //范围上限
	private Float ch_min; //范围下限
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id = new Gm_DevCtrl(); //控制设备ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ch_id")
	private Gm_Channel ch_id = new Gm_Channel(); //状态通道ID
	public String getDectSts_id() {
		return dectSts_id;
	}
	public void setDectSts_id(String dectSts_id) {
		this.dectSts_id = dectSts_id;
	}
	public String getDectSts_name() {
		return dectSts_name;
	}
	public void setDectSts_name(String dectSts_name) {
		this.dectSts_name = dectSts_name;
	}
	public Integer getDect_state() {
		return dect_state;
	}
	public void setDect_state(Integer dect_state) {
		this.dect_state = dect_state;
	}
	public Float getCh_max() {
		return ch_max;
	}
	public void setCh_max(Float ch_max) {
		this.ch_max = ch_max;
	}
	public Float getCh_min() {
		return ch_min;
	}
	public void setCh_min(Float ch_min) {
		this.ch_min = ch_min;
	}
	public Gm_DevCtrl getDect_id() {
		return dect_id;
	}
	public void setDect_id(Gm_DevCtrl dect_id) {
		this.dect_id = dect_id;
	}
	public Gm_Channel getCh_id() {
		return ch_id;
	}
	public void setCh_id(Gm_Channel ch_id) {
		this.ch_id = ch_id;
	}
		
}
