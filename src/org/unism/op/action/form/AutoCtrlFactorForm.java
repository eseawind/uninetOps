package org.unism.op.action.form;

import java.util.ArrayList;
import java.util.List;

import org.unism.gm.domain.Gm_Channel;
import org.unism.op.domain.AutoCtrlConfig;
import org.unism.op.domain.reference.Condition;
import org.unism.op.domain.reference.Handle;

public class AutoCtrlFactorForm {

	private String id;
	
	private AutoCtrlConfig autoCtrlConfig;
	
	private List<String> channels = new ArrayList<String>();
	
	private Handle handle;
	
	private Condition condition;
	
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

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
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
