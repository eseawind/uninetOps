package org.unism.gm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;

/**
 * 采集通道信息表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_channel" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gm_Channel {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String ch_id; //Id
	private String ch_no; //通道编号
	private String ch_name; //通道名称
	private Integer ch_chlNo; //设备通道号
	private Integer ch_procesStyle; //通道数据处理方式 0：表示不处理（无效通道） 1：校正后存储（一般采集，数据上报-存储模式） 2：校正后定时存储（实时采集数据，平台定时存储） 3：校正后存储-设备能量状态显示 4：校正后状态为停不存储，其它定时存储-控制设备状态返回显示
	private Integer ch_memoryPeriod; //存储周期 单位为秒
	private Integer ch_dectDig; //小数位数
	private String ch_unit; //原数据单位
	private Float ch_max; //量程上限
	private Float ch_min; //量程下限
	private Double ch_crateMin;//存储变化率下限
	private Double ch_crateMax; //变化率上限
	private Integer ch_corrCyc; //校准周期 单位天
	private String ch_corrFormula; //校正公式
	private String ch_corrUnit; //校正后的单位
	private Integer ch_offerSer; //是否对外提供服务 0 否 1 是
	private Integer ch_state; //通道状态 0 未用 1 在用
	private String ch_seat_no;
	private String ch_depth;
	private Integer ch_datetype; //数值类型
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_collectId")
	private Gm_Device dev_collectId = new Gm_Device(); //采集设备ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_sensorId")
	private Gm_Device dev_sensorId = new Gm_Device(); //传感设备ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id = new Op_Scene(); //场景ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "chtype_id")
	private Op_ChannelType chtype_id = new Op_ChannelType(); //通道应用类型ID
	
	public String getOfferSerStr() {
		if(ch_offerSer == null)
			return "空配置";
		switch (ch_offerSer) {
		case 0:
			return "否";
		case 1:
			return "是";
		default:
			return "错误的配置";
		}
	}
	
	public String getProcesStyleStr() {
		if(ch_procesStyle == null)
			return "空配置";
		switch (ch_procesStyle) {
		case 0:
			return "不处理";
		case 1:
			return "校正后存储";
		case 2:
			return "校正后定时存储";
		case 3:
			return "设备能量状态显示";
		case 4:
			return "控制设备状态返回显示";
		default:
			return "错误的配置";
		}
	}
	
	public Op_ChannelType getChtype_id() {
		return chtype_id;
	}
	public void setChtype_id(Op_ChannelType chtype_id) {
		this.chtype_id = chtype_id;
	}
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public Integer getCh_chlNo() {
		return ch_chlNo;
	}
	public void setCh_chlNo(Integer ch_chlNo) {
		this.ch_chlNo = ch_chlNo;
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
	public String getCh_corrUnit() {
		return ch_corrUnit;
	}
	public void setCh_corrUnit(String ch_corrUnit) {
		this.ch_corrUnit = ch_corrUnit;
	}
	public Integer getCh_offerSer() {
		return ch_offerSer;
	}
	public void setCh_offerSer(Integer ch_offerSer) {
		this.ch_offerSer = ch_offerSer;
	}
	public Gm_Device getDev_collectId() {
		return dev_collectId;
	}
	public void setDev_collectId(Gm_Device dev_collectId) {
		this.dev_collectId = dev_collectId;
	}
	public Gm_Device getDev_sensorId() {
		return dev_sensorId;
	}
	public void setDev_sensorId(Gm_Device dev_sensorId) {
		this.dev_sensorId = dev_sensorId;
	}
	public Op_Scene getScene_id() {
		return scene_id;
	}
	public void setScene_id(Op_Scene scene_id) {
		this.scene_id = scene_id;
	}
	public Integer getCh_state() {
		return ch_state;
	}
	public void setCh_state(Integer ch_state) {
		this.ch_state = ch_state;
	}
	public Integer getCh_procesStyle() {
		return ch_procesStyle;
	}
	public void setCh_procesStyle(Integer ch_procesStyle) {
		this.ch_procesStyle = ch_procesStyle;
	}
	public String getCh_no() {
		return ch_no;
	}
	public void setCh_no(String ch_no) {
		this.ch_no = ch_no;
	}
	public Integer getCh_memoryPeriod() {
		return ch_memoryPeriod;
	}
	public void setCh_memoryPeriod(Integer ch_memoryPeriod) {
		this.ch_memoryPeriod = ch_memoryPeriod;
	}
	public String getCh_seat_no() {
		return ch_seat_no;
	}
	public void setCh_seat_no(String ch_seat_no) {
		this.ch_seat_no = ch_seat_no;
	}
	public String getCh_depth() {
		return ch_depth;
	}
	public void setCh_depth(String ch_depth) {
		this.ch_depth = ch_depth;
	}
	public Integer getCh_datetype() {
		return ch_datetype;
	}
	public void setCh_datetype(Integer ch_datetype) {
		this.ch_datetype = ch_datetype;
	}

	public Double getCh_crateMin() {
		return ch_crateMin;
	}

	public void setCh_crateMin(Double ch_crateMin) {
		this.ch_crateMin = ch_crateMin;
	}

	
}
