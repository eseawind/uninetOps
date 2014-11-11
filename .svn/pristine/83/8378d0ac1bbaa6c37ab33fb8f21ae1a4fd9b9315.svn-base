package org.unism.op.domain;

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
import org.unism.op.domain.reference.Condition;
import org.unism.op.domain.reference.Handle;

/**
 * 自动控制配置条件表
 * @author qinglin.mengql
 *
 */
@Entity
@Table(name = "op_autoCtrlFactor")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class AutoCtrlFactor {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	@Column(name = "acf_id")
	private String id;
	
	/**
	 * 所属自动控制
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "acc_id")
	private AutoCtrlConfig autoCtrlConfig;
	
	/**
	 * 通道id，多个“，”分隔开
	 */
	@Column(name = "ch_id")
	private String channelId;
	
	/**
	 *数据处理方式
	 */
	@Column(name = "acc_handle")
	private Handle handle;
	
	/**
	 * 控制条件
	 */
	@Column(name = "acc_condition")
	private Condition condition;
	
	/**
	 * 触发值
	 */
	@Column(name = "acc_trigger")
	private Double trigger;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AutoCtrlConfig getAutoCtrlConfig() {
		return autoCtrlConfig;
	}

	public void setAutoCtrlConfig(AutoCtrlConfig autoCtrlConfig) {
		this.autoCtrlConfig = autoCtrlConfig;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Handle getHandle() {
		return handle;
	}

	public void setHandle(Handle handle) {
		this.handle = handle;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Double getTrigger() {
		return trigger;
	}

	public void setTrigger(Double trigger) {
		this.trigger = trigger;
	}
}
