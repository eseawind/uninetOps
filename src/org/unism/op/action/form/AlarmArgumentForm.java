package org.unism.op.action.form;

import org.unism.op.domain.reference.Enable;

public class AlarmArgumentForm {

	private String id;
	
	private String chId;
	
	private String max;
	
	private String min;
	
	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	private Enable enable;
	
	public String getChId() {
		return chId;
	}

	public void setChId(String chId) {
		this.chId = chId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Enable getEnable() {
		return enable;
	}

	public void setEnable(Enable enable) {
		this.enable = enable;
	}
}
