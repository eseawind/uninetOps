package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.unism.gm.domain.Gm_Channel;
import org.unism.op.domain.reference.Enable;

@Entity
@Table( name = "op_alarmargument" )
public class Op_Alarmargument implements Serializable {
	private static final long serialVersionUID = 2090783688669234415L;
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene; //场景
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ch_id")
	private Gm_Channel channel;
	
	@Column(name = "ch_name")
	private String chName;
	
	@Column(name = "al_max")
	private Double max;
	
	@Column(name = "al_min")
	private Double min;
	
	@Column(name = "al_enable")
	private Enable enable ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Op_Scene getScene() {
		return scene;
	}

	public void setScene(Op_Scene scene) {
		this.scene = scene;
	}

	public Gm_Channel getChannel() {
		return channel;
	}

	public void setChannel(Gm_Channel channel) {
		this.channel = channel;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Enable getEnable() {
		return enable;
	}

	public void setEnable(Enable enable) {
		this.enable = enable;
	}

}
