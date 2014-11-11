package org.unism.gm.action.form;

import java.util.ArrayList;
import java.util.List;

import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlSts;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.wangzz.core.utils.SpringContextHolder;

public class DevCtrlStsForm {
	
	private String[] dectStsId;
	
	private String[] dectStsName;
	
	private String[] dectState;
	
	private String[] chMax;
	
	private String[] chMin;
	
	private String[] dectNo;
	
	private String[] chId;

	public String[] getDectStsId() {
		return dectStsId;
	}

	public void setDectStsId(String[] dectStsId) {
		this.dectStsId = dectStsId;
	}

	public String[] getDectStsName() {
		return dectStsName;
	}

	public void setDectStsName(String[] dectStsName) {
		this.dectStsName = dectStsName;
	}

	public String[] getDectState() {
		return dectState;
	}

	public void setDectState(String[] dectState) {
		this.dectState = dectState;
	}

	public String[] getChMax() {
		return chMax;
	}

	public void setChMax(String[] chMax) {
		this.chMax = chMax;
	}

	public String[] getChMin() {
		return chMin;
	}

	public void setChMin(String[] chMin) {
		this.chMin = chMin;
	}

	public String[] getDectNo() {
		return dectNo;
	}

	public void setDectNo(String[] dectNo) {
		this.dectNo = dectNo;
	}

	public String[] getChId() {
		return chId;
	}

	public void setChId(String[] chId) {
		this.chId = chId;
	}
	
	public List<Gm_Channel> getChannel(String[] chId){
		Gm_ChannelService gm_ChannelService = SpringContextHolder.getBean("gm_ChannelService");
		List<Gm_Channel> channelList = new ArrayList<Gm_Channel>();
		for (int i = 0; i < chId.length; i++) {
			Gm_Channel gm_Channel = gm_ChannelService.findById(chId[i]);
			channelList.add(gm_Channel);
		}
		return channelList;
	}

}
