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
import org.unism.gm.domain.Gm_Device;

@Entity
@Table( name = "op_devctrlbtn" )
public class Op_DevCtrlBtn implements Serializable {
	
	private static final long serialVersionUID = 6553585801624805092L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String dectbtn_id; //ID
	private String dectbtn_name; //按钮名称 用户理解的名称
	private Integer deco_type; //操作类型 1：开（正向），2：停 3：关（反向）；其它无效 
	private String dect_id; //控制设备ID
	private Integer dect_chlNo; //控制通道
	private Integer dect_ctlType; //控制类型 1：正向脉冲 2：反向脉冲 3：高电平 4：低电平
	private Integer dect_ctlDelay; //操作延时
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_id")
	private Gm_Device dev_id = new Gm_Device(); //所属设备ID

	public String getDectbtn_id() {
		return dectbtn_id;
	}

	public void setDectbtn_id(String dectbtn_id) {
		this.dectbtn_id = dectbtn_id;
	}

	public String getDectbtn_name() {
		return dectbtn_name;
	}

	public void setDectbtn_name(String dectbtn_name) {
		this.dectbtn_name = dectbtn_name;
	}

	public Integer getDeco_type() {
		return deco_type;
	}

	public void setDeco_type(Integer deco_type) {
		this.deco_type = deco_type;
	}

	public String getDect_id() {
		return dect_id;
	}

	public void setDect_id(String dect_id) {
		this.dect_id = dect_id;
	}

	public Integer getDect_chlNo() {
		return dect_chlNo;
	}

	public void setDect_chlNo(Integer dect_chlNo) {
		this.dect_chlNo = dect_chlNo;
	}

	public Integer getDect_ctlType() {
		return dect_ctlType;
	}

	public void setDect_ctlType(Integer dect_ctlType) {
		this.dect_ctlType = dect_ctlType;
	}

	public Integer getDect_ctlDelay() {
		return dect_ctlDelay;
	}

	public void setDect_ctlDelay(Integer dect_ctlDelay) {
		this.dect_ctlDelay = dect_ctlDelay;
	}

	public Gm_Device getDev_id() {
		return dev_id;
	}

	public void setDev_id(Gm_Device dev_id) {
		this.dev_id = dev_id;
	}
		
}
