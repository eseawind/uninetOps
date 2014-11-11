package org.unism.gm.action.form;

public class DevFaultTypeForm {
	
	private String scene_name;//场景名称
	
	private String net_addr;//设备地址
	
	private Integer platformCount=0;//平台故障
	
	private Integer GPRSCount=0;//网关故障
	
	private Integer wsnCount=0;//小无线故障
	
	private Integer sensorCount=0;//传感器故障
	
	private Integer devctrlCount=0;//控制设备故障
	
	
	//get set==================================================================

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public String getNet_addr() {
		return net_addr;
	}

	public void setNet_addr(String net_addr) {
		this.net_addr = net_addr;
	}

	public Integer getPlatformCount() {
		return platformCount;
	}

	public void setPlatformCount(Integer platformCount) {
		this.platformCount = platformCount;
	}

	

	public Integer getGPRSCount() {
		return GPRSCount;
	}

	public void setGPRSCount(Integer count) {
		GPRSCount = count;
	}

	public Integer getWsnCount() {
		return wsnCount;
	}

	public void setWsnCount(Integer wsnCount) {
		this.wsnCount = wsnCount;
	}

	public Integer getSensorCount() {
		return sensorCount;
	}

	public void setSensorCount(Integer sensorCount) {
		this.sensorCount = sensorCount;
	}

	public Integer getDevctrlCount() {
		return devctrlCount;
	}

	public void setDevctrlCount(Integer devctrlCount) {
		this.devctrlCount = devctrlCount;
	}

}
