package org.unism.op.action.form;

import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.reference.Cond;
import org.unism.op.domain.reference.Condition;
import org.unism.op.domain.reference.Enable;
import org.unism.op.domain.reference.Handle;
import org.unism.op.domain.reference.IsNot;

public class AutoCtrlConfigForm {

	private Op_Scene scene;
	
	private Gm_DevCtrl devCtrl;
	
	private Integer type;
	
	private Cond cond;
	
	private String[] chIds;
	
	private Handle[] handles;
	
	private Condition[] conditions;
	
	private Double[] trigger;
	
	private IsNot enable;

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

	public String[] getChIds() {
		return chIds;
	}

	public void setChIds(String[] chIds) {
		this.chIds = chIds;
	}

	public Handle[] getHandles() {
		return handles;
	}

	public void setHandles(Handle[] handles) {
		this.handles = handles;
	}

	public Condition[] getConditions() {
		return conditions;
	}

	public void setConditions(Condition[] conditions) {
		this.conditions = conditions;
	}

	public Double[] getTrigger() {
		return trigger;
	}

	public void setTrigger(Double[] trigger) {
		this.trigger = trigger;
	}

	public IsNot getEnable() {
		return enable;
	}

	public void setEnable(IsNot enable) {
		this.enable = enable;
	}
}
