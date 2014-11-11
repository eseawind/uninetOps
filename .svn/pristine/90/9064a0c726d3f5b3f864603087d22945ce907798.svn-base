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

import org.hibernate.annotations.GenericGenerator;

/**
 * 历史数据表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_historydata" )
public class Gm_Historydata {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String hida_id; //ID
	private Double hida_origValue; //原数据值
	private Double hida_corrValue; //处理后的值
	private Date hida_reportTime; //上报时间
	private Date hida_record_time; //记录时间
	private Integer hida_dateQuality;//数据质量
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ch_id")
	private Gm_Channel ch_id = new Gm_Channel(); //通道ID

	public String getHida_id() {
		return hida_id;
	}

	public void setHida_id(String hida_id) {
		this.hida_id = hida_id;
	}

	public Double getHida_origValue() {
		return hida_origValue;
	}

	public void setHida_origValue(Double hida_origValue) {
		this.hida_origValue = hida_origValue;
	}

	public Double getHida_corrValue() {
		return hida_corrValue;
	}

	public void setHida_corrValue(Double hida_corrValue) {
		this.hida_corrValue = hida_corrValue;
	}

	public Date getHida_reportTime() {
		return hida_reportTime;
	}

	public void setHida_reportTime(Date hida_reportTime) {
		this.hida_reportTime = hida_reportTime;
	}

	public Date getHida_record_time() {
		return hida_record_time;
	}

	public void setHida_record_time(Date hida_record_time) {
		this.hida_record_time = hida_record_time;
	}

	public Gm_Channel getCh_id() {
		return ch_id;
	}

	public void setCh_id(Gm_Channel ch_id) {
		this.ch_id = ch_id;
	}

	public Integer getHida_dateQuality() {
		return hida_dateQuality;
	}

	public void setHida_dateQuality(Integer hidaDateQuality) {
		hida_dateQuality = hidaDateQuality;
	}
	
}
