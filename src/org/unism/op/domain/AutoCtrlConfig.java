package org.unism.op.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.op.domain.reference.Cond;
import org.unism.op.domain.reference.Enable;
import org.unism.op.domain.reference.IsNot;

/**
 * 自动控制配置表
 * @author qinglin.mengql
 *
 */
@Entity
@Table(name = "op_autoCtrlConfig")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class AutoCtrlConfig {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	@Column(name = "acc_id")
	private String id;
	
	/**
	 * 所属场景
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene;
	
	/**
	 * 控制设备
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl devCtrl;
	
	/**
	 * 操作类型
	 */
	@Column(name = "deco_type")
	private Integer type;
	
	/**
	 * 条件关系
	 */
	@Column(name = "acc_cond")
	private Cond cond;
	
	/**
	 * 添加人
	 */
	@Column(name = "acc_user")
	private String user;
	
	/**
	 * 添加时间
	 */
	@Column(name = "acc_addTime")
	private Date addTime;
	
	/**
	 * 是否开启
	 */
	@Column(name = "acc_enable")
	private IsNot enable;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy="autoCtrlConfig")
	List<AutoCtrlFactor> autoCtrlFactors = new ArrayList<AutoCtrlFactor>();
	
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

	public Gm_DevCtrl getDevCtrl() {
		return devCtrl;
	}

	public void setDevCtrl(Gm_DevCtrl devCtrl) {
		this.devCtrl = devCtrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Cond getCond() {
		return cond;
	}

	public void setCond(Cond cond) {
		this.cond = cond;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public List<AutoCtrlFactor> getAutoCtrlFactors() {
		return autoCtrlFactors;
	}

	public void setAutoCtrlFactors(List<AutoCtrlFactor> autoCtrlFactors) {
		this.autoCtrlFactors = autoCtrlFactors;
	}

	public IsNot getEnable() {
		return enable;
	}

	public void setEnable(IsNot enable) {
		this.enable = enable;
	}
}
