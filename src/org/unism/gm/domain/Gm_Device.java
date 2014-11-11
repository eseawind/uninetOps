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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_Supplier;
import org.wangzz.core.utils.SpringContextHolder;

/**
 * 设备信息表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_device" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gm_Device {
	
	public static final int dev_btype_sensor=4; //传感器
	public static final int dev_btype_devctrl=3; //执行器
	public static final int dev_btype_isensor=2; //智能单元
	public static final int dev_btype_wsn=1; //WSN
	public static final int dev_btype_m2m=0; //M2M
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String dev_id; //Id
	private String dev_no; //设备编号
	private String dev_name; //设备名称
	private String dev_serial; //设备序列号
	private Integer dev_btype; //设备类型
	private Integer dev_stype; //设备小类 待定	
	private String dev_model; //设备型号 
	private String dev_powerType; //供电方式 市电 仅3V电池供电 3V+9V电池供电
	private String dev_imsi; //设备IMSI
	private Date dev_buyDate; //购买时间
	private Date dev_overDate; //服务到期时间
	private Date dev_effectTime; //生效时间
	private Integer dev_state; //设备使用状态 0:未用 1:在用

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_saleId")
	private Op_Supplier	cust_saleId = new Op_Supplier(); //设备厂家ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_serviceId")
	private Op_Supplier cust_serviceId = new Op_Supplier(); //设备服务商ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id = new Op_Scene(); //所属场景ID
	
	//编辑的时候做验证用 
	public boolean getIsUsing(){
		Gm_DeviceService gm_DeviceService = SpringContextHolder.getBean("gm_DeviceService");
		if(gm_DeviceService.isExistNet(this.dev_id) || gm_DeviceService.isExistChannel(this.dev_id) || gm_DeviceService.isExistDevCtrl(this.dev_id) || gm_DeviceService.isExistBtn(dev_id)){
			return true;
		}else{
			return false;
		}
	}
	public String getDev_id() {
		return dev_id;
	}
	public void setDev_id(String dev_id) {
		this.dev_id = dev_id;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getDev_serial() {
		return dev_serial;
	}
	public void setDev_serial(String dev_serial) {
		this.dev_serial = dev_serial;
	}
	public Integer getDev_btype() {
		return dev_btype;
	}
	public void setDev_btype(Integer dev_btype) {
		this.dev_btype = dev_btype;
	}
	public Integer getDev_stype() {
		return dev_stype;
	}
	public void setDev_stype(Integer dev_stype) {
		this.dev_stype = dev_stype;
	}

	public String getDev_model() {
		return dev_model;
	}
	public void setDev_model(String dev_model) {
		this.dev_model = dev_model;
	}
	public String getDev_powerType() {
		return dev_powerType;
	}
	public void setDev_powerType(String dev_powerType) {
		this.dev_powerType = dev_powerType;
	}
	public String getDev_imsi() {
		return dev_imsi;
	}
	public void setDev_imsi(String dev_imsi) {
		this.dev_imsi = dev_imsi;
	}
	public Date getDev_buyDate() {
		return dev_buyDate;
	}
	public void setDev_buyDate(Date dev_buyDate) {
		this.dev_buyDate = dev_buyDate;
	}
	public Date getDev_overDate() {
		return dev_overDate;
	}
	public void setDev_overDate(Date dev_overDate) {
		this.dev_overDate = dev_overDate;
	}
	public Date getDev_effectTime() {
		return dev_effectTime;
	}
	public void setDev_effectTime(Date dev_effectTime) {
		this.dev_effectTime = dev_effectTime;
	}
	public Integer getDev_state() {
		return dev_state;
	}
	public void setDev_state(Integer dev_state) {
		this.dev_state = dev_state;
	}
	public Op_Supplier getCust_saleId() {
		return cust_saleId;
	}
	public void setCust_saleId(Op_Supplier cust_saleId) {
		this.cust_saleId = cust_saleId;
	}
	public Op_Supplier getCust_serviceId() {
		return cust_serviceId;
	}
	public void setCust_serviceId(Op_Supplier cust_serviceId) {
		this.cust_serviceId = cust_serviceId;
	}
	public Op_Scene getScene_id() {
		return scene_id;
	}
	public void setScene_id(Op_Scene scene_id) {
		this.scene_id = scene_id;
	}
	public static int getDev_btype_sensor() {
		return dev_btype_sensor;
	}
	public static int getDev_btype_devctrl() {
		return dev_btype_devctrl;
	}
	public static int getDev_btype_isensor() {
		return dev_btype_isensor;
	}
	public static int getDev_btype_wsn() {
		return dev_btype_wsn;
	}
	public static int getDev_btype_m2m() {
		return dev_btype_m2m;
	}
	public String getDev_no() {
		return dev_no;
	}
	public void setDev_no(String dev_no) {
		this.dev_no = dev_no;
	}

}
