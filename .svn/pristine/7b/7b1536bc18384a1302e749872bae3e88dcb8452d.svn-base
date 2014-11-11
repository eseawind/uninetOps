package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
/**
 * 采集通道应用类型信息表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_channeltype" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_ChannelType implements Serializable {
	
	private static final long serialVersionUID = 5364692681112710068L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String chtype_id; //采集通道应用类型信息表ID
	private String chtype_no; //类型编号 唯一
	private String chtype_displayName; //类型名称
	private String dev_model; //设备型号
	private Integer ch_dectDig; //小数位数
	private String ch_unit; //原数据单位
	private String ch_corrUnit; //校正后单位
	private Float ch_max; //量程上限
	private Float ch_min; //量程下限
	private Double ch_crateMax; //变化率上限
	private Integer ch_corrCyc; //校准周期 单位天
	private String ch_corrFormula; //校正公式
	private String ch_className; //显示样式
	private String typeImg; //采集量显示的图片
	
	public String getChtype_id() {
		return chtype_id;
	}
	public void setChtype_id(String chtype_id) {
		this.chtype_id = chtype_id;
	}
	public String getChtype_no() {
		return chtype_no;
	}
	public void setChtype_no(String chtype_no) {
		this.chtype_no = chtype_no;
	}
	public String getChtype_displayName() {
		return chtype_displayName;
	}
	public void setChtype_displayName(String chtype_displayName) {
		this.chtype_displayName = chtype_displayName;
	}
	public String getDev_model() {
		return dev_model;
	}
	public void setDev_model(String dev_model) {
		this.dev_model = dev_model;
	}
	public Integer getCh_dectDig() {
		return ch_dectDig;
	}
	public void setCh_dectDig(Integer ch_dectDig) {
		this.ch_dectDig = ch_dectDig;
	}
	public String getCh_unit() {
		return ch_unit;
	}
	public void setCh_unit(String ch_unit) {
		this.ch_unit = ch_unit;
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
	public Double getCh_crateMax() {
		return ch_crateMax;
	}
	public void setCh_crateMax(Double ch_crateMax) {
		this.ch_crateMax = ch_crateMax;
	}
	public Integer getCh_corrCyc() {
		return ch_corrCyc;
	}
	public void setCh_corrCyc(Integer ch_corrCyc) {
		this.ch_corrCyc = ch_corrCyc;
	}
	public String getCh_corrFormula() {
		return ch_corrFormula;
	}
	public void setCh_corrFormula(String ch_corrFormula) {
		this.ch_corrFormula = ch_corrFormula;
	}
	public String getCh_className() {
		return ch_className;
	}
	public void setCh_className(String ch_className) {
		this.ch_className = ch_className;
	}
	public String getTypeImg() {
		return typeImg;
	}
	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}
	public String getCh_corrUnit() {
		return ch_corrUnit;
	}
	public void setCh_corrUnit(String ch_corrUnit) {
		this.ch_corrUnit = ch_corrUnit;
	}
	
}
