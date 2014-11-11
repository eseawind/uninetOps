package org.unism.pro.domain;

import java.io.Serializable;
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
import org.unism.gm.domain.Gm_Channel;
import org.unism.op.domain.Op_Scene;


@Entity
@Table(name = "pro_forecastdata")
public class Pro_forecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1124475141996111665L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String fc_id; // 主键
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene = new Op_Scene(); //所属场景ID
//	private Double hida_origValue; //原数据值
//	private Double hida_corrValue; //处理后数据值
	private Double hida_restoreValue; //修复后的数据值
	private Double hida_forecastValue; //预测之后的数据值
	private Date hida_record_time; //采集时间
	private Date forecast_time; //预测时间 
	private Integer fc_forecastType;//预测类型 0：半个小时；1:1个小时；2:2个小时；3:3个小时；4:5个小时
	private Integer fc_modelType;//模型分类 0：神经网络；1：线性回归
	public String getFc_id() {
		return fc_id;
	}
	public void setFc_id(String fc_id) {
		this.fc_id = fc_id;
	}
	public Double getHida_restoreValue() {
		return hida_restoreValue;
	}
	public void setHida_restoreValue(Double hida_restoreValue) {
		this.hida_restoreValue = hida_restoreValue;
	}
	public Double getHida_forecastValue() {
		return hida_forecastValue;
	}
	public void setHida_forecastValue(Double hida_forecastValue) {
		this.hida_forecastValue = hida_forecastValue;
	}
	public Date getHida_record_time() {
		return hida_record_time;
	}
	public void setHida_record_time(Date hida_record_time) {
		this.hida_record_time = hida_record_time;
	}
	public Date getForecast_time() {
		return forecast_time;
	}
	public void setForecast_time(Date forecast_time) {
		this.forecast_time = forecast_time;
	}
	public Integer getFc_modelType() {
		return fc_modelType;
	}
	public void setFc_modelType(Integer fc_modelType) {
		this.fc_modelType = fc_modelType;
	}
	public Integer getFc_forecastType() {
		return fc_forecastType;
	}
	public void setFc_forecastType(Integer fc_forecastType) {
		this.fc_forecastType = fc_forecastType;
	}
	public Op_Scene getScene() {
		return scene;
	}
	public void setScene(Op_Scene scene) {
		this.scene = scene;
	}
	
	

}
