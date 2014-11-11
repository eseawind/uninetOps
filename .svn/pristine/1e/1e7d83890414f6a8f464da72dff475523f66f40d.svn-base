package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.unism.util.ImageUrl;

@Entity
@Table(name = "op_devctrltype")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_DevCtrlType implements Serializable {

	private static final long serialVersionUID = 6652006369441575829L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String decttype_id;//控制设备应用类型信息表ID
	private String decttype_no; //类型编号
	private String decttype_displayName;//类型名称
	private String decttype_typeNo; //类型型号
	private Double decttype_power; //类型功率
	private Double decttype_voltage; //类型供电电压
	private String decttype_decription; //类型说明
	private String decttype_img; //类型图片资源
	private Integer decttype_btnNum; //类型按钮数量
	private Integer decttype_chlStsNum; //通道状态反馈数量
	public String getDecttype_id() {
		return decttype_id;
	}
	public void setDecttype_id(String decttype_id) {
		this.decttype_id = decttype_id;
	}
	public String getDecttype_no() {
		return decttype_no;
	}
	public void setDecttype_no(String decttype_no) {
		this.decttype_no = decttype_no;
	}
	public String getDecttype_displayName() {
		return decttype_displayName;
	}
	public void setDecttype_displayName(String decttype_displayName) {
		this.decttype_displayName = decttype_displayName;
	}
	public String getDecttype_typeNo() {
		return decttype_typeNo;
	}
	public void setDecttype_typeNo(String decttype_typeNo) {
		this.decttype_typeNo = decttype_typeNo;
	}
	public Double getDecttype_power() {
		return decttype_power;
	}
	public void setDecttype_power(Double decttype_power) {
		this.decttype_power = decttype_power;
	}
	public Double getDecttype_voltage() {
		return decttype_voltage;
	}
	public void setDecttype_voltage(Double decttype_voltage) {
		this.decttype_voltage = decttype_voltage;
	}
	public String getDecttype_decription() {
		return decttype_decription;
	}
	public void setDecttype_decription(String decttype_decription) {
		this.decttype_decription = decttype_decription;
	}
	public String getDecttype_img() {
		return decttype_img;
	}
	public void setDecttype_img(String decttype_img) {
		this.decttype_img = decttype_img;
	}
	public Integer getDecttype_btnNum() {
		return decttype_btnNum;
	}
	public void setDecttype_btnNum(Integer decttype_btnNum) {
		this.decttype_btnNum = decttype_btnNum;
	}
	public Integer getDecttype_chlStsNum() {
		return decttype_chlStsNum;
	}
	public void setDecttype_chlStsNum(Integer decttype_chlStsNum) {
		this.decttype_chlStsNum = decttype_chlStsNum;
	}	
	
}
