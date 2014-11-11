package org.unism.gm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 设备上报通道配置表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devchannel" )
public class Gm_DevChannel {
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String dch_id; //Id
	private String dev_addr; //设备地址
	private Integer dch_order; //通道顺序
	private Integer ch_procesStyle; //通道数据处理方式 0：表示不处理（无效通道） 1：校正后存储（一般采集，数据上报-存储模式） 2：校正后定时存储（实时采集数据，平台定时存储） 3：校正后存储-设备能量状态显示 4：校正后状态为停不存储，其它定时存储-控制设备状态返回显示
	private Integer ch_memoryPeriod; //存储周期 单位为秒
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ch_id")
	private Gm_Channel ch_id = new Gm_Channel(); //通道ID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_id")
	private Gm_Device dev_id = new Gm_Device(); //设备ID
	
	public String getDch_id() {
		return dch_id;
	}
	public void setDch_id(String dch_id) {
		this.dch_id = dch_id;
	}
	public String getDev_addr() {
		return dev_addr;
	}
	public void setDev_addr(String dev_addr) {
		this.dev_addr = dev_addr;
	}
	public Integer getDch_order() {
		return dch_order;
	}
	public void setDch_order(Integer dch_order) {
		this.dch_order = dch_order;
	}
	public Gm_Channel getCh_id() {
		return ch_id;
	}
	public void setCh_id(Gm_Channel ch_id) {
		this.ch_id = ch_id;
	}
	public Gm_Device getDev_id() {
		return dev_id;
	}
	public void setDev_id(Gm_Device dev_id) {
		this.dev_id = dev_id;
	}
	public Integer getCh_procesStyle() {
		return ch_procesStyle;
	}
	public void setCh_procesStyle(Integer ch_procesStyle) {
		this.ch_procesStyle = ch_procesStyle;
	}
	public Integer getCh_memoryPeriod() {
		return ch_memoryPeriod;
	}
	public void setCh_memoryPeriod(Integer ch_memoryPeriod) {
		this.ch_memoryPeriod = ch_memoryPeriod;
	}

}
