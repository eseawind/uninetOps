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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.unism.op.domain.Op_DevCtrlBtn;
import org.unism.op.domain.Op_DevCtrlType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_DevCtrlBtnService;
import org.wangzz.core.utils.SpringContextHolder;

/**
 * 控制设备信息表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devctrl" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gm_DevCtrl {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String dect_id; //Id
	private String dect_no; //控制设备编号
	private String dect_name; //设备名称
	private String dect_serial; //设备序列号
	private Integer decttype_btnNum; //类型按钮数量
	private Date dect_buyDate; //购买时间
	private Date dect_overDate; //服务到期时间
	private Date dect_effectTime; //生效时间
	private Integer dect_state = 1; //设备使用状态 0:未用 1:在用
	private Integer ch_offerSer; //是否对外提供服务
	private String dect_decsription; //设备说明
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id; //场景ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_id")
	private Gm_Device dev_id; //所属设备ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "decttype_id")
	private Op_DevCtrlType decttype_id; //所属设备类型ID
	
	public String getDect_id() {
		return dect_id;
	}

	public void setDect_id(String dect_id) {
		this.dect_id = dect_id;
	}

	public String getDect_name() {
		return dect_name;
	}

	public void setDect_name(String dect_name) {
		this.dect_name = dect_name;
	}

	public String getDect_serial() {
		return dect_serial;
	}

	public void setDect_serial(String dect_serial) {
		this.dect_serial = dect_serial;
	}

	public String getDect_no() {
		return dect_no;
	}

	public void setDect_no(String dect_no) {
		this.dect_no = dect_no;
	}

	public Integer getDecttype_btnNum() {
		return decttype_btnNum;
	}

	public void setDecttype_btnNum(Integer decttype_btnNum) {
		this.decttype_btnNum = decttype_btnNum;
	}

	public Date getDect_buyDate() {
		return dect_buyDate;
	}

	public void setDect_buyDate(Date dect_buyDate) {
		this.dect_buyDate = dect_buyDate;
	}

	public Date getDect_overDate() {
		return dect_overDate;
	}

	public void setDect_overDate(Date dect_overDate) {
		this.dect_overDate = dect_overDate;
	}

	public Date getDect_effectTime() {
		return dect_effectTime;
	}

	public void setDect_effectTime(Date dect_effectTime) {
		this.dect_effectTime = dect_effectTime;
	}

	public Integer getDect_state() {
		return dect_state;
	}

	public void setDect_state(Integer dect_state) {
		this.dect_state = dect_state;
	}

	public Integer getCh_offerSer() {
		return ch_offerSer;
	}

	public void setCh_offerSer(Integer ch_offerSer) {
		this.ch_offerSer = ch_offerSer;
	}

	public String getDect_decsription() {
		return dect_decsription;
	}

	public void setDect_decsription(String dect_decsription) {
		this.dect_decsription = dect_decsription;
	}

	public Op_Scene getScene_id() {
		return scene_id;
	}

	public void setScene_id(Op_Scene scene_id) {
		this.scene_id = scene_id;
	}

	public Op_DevCtrlType getDecttype_id() {
		return decttype_id;
	}

	public void setDecttype_id(Op_DevCtrlType decttype_id) {
		this.decttype_id = decttype_id;
	}

	public Gm_Device getDev_id() {
		return dev_id;
	}

	public void setDev_id(Gm_Device dev_id) {
		this.dev_id = dev_id;
	}
	

	public List<Op_DevCtrlBtn> getOpDevCtrlBtns() {
		Op_DevCtrlBtnService op_DevCtrlBtnService = SpringContextHolder.getBean("op_DevCtrlBtnService");
		return op_DevCtrlBtnService.findAllEq("dect_id", this.dect_id);
	}
}
