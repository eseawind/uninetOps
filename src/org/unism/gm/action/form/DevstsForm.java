package org.unism.gm.action.form;

public class DevstsForm {

	private String scene_name;//场景名称
	
	private String dest_workSts;//设备状态，已经转为字符串的
	
	private String dest_workStsInfo;//状态信息
	
	//get set==================================================================

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public String getDest_workSts() {
		return dest_workSts;
	}

	public void setDest_workSts(String dest_workSts) {
		this.dest_workSts = dest_workSts;
	}

	public String getDest_workStsInfo() {
		return dest_workStsInfo;
	}

	public void setDest_workStsInfo(String dest_workStsInfo) {
		this.dest_workStsInfo = dest_workStsInfo;
	}
}
