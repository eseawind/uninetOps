package org.unism.gm.action.form;

public class ReportDataForm {

    private String scene_name;//场景名称
	
	private String dev_addr;//设备地址
	
	private Integer reportCount=0;//上报个数
	
	//get set==================================================================

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public String getDev_addr() {
		return dev_addr;
	}

	public void setDev_addr(String dev_addr) {
		this.dev_addr = dev_addr;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
}
