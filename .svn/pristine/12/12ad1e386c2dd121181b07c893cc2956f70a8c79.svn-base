package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.op.action.form.AlarmConfigForm;
import org.unism.op.domain.Op_AlarmConfig;
import org.unism.op.domain.Op_Alarmargument;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_AlarmConfigService;
import org.unism.op.service.Op_AlarmargumentService;
import org.unism.op.service.Op_SceneService;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;

public class Op_AlarmConfigAction {

	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_AlarmConfigService alarmConfigService;
	@Autowired Op_AlarmargumentService alarmargumentService;
	@Autowired Gm_ChannelService gm_ChannelService;
	
	public String config(){
		return "config";
	}
	
	public String configureOpAlarmconfig(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			Op_Scene op_Scene = op_SceneService.findById(sceneId);
			//List<Gm_ChannelForm> channelForms = new ArrayList<Gm_ChannelForm>();
			List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
			if(op_Scene != null){
				List<Op_AlarmConfig> alarmConfigs = alarmConfigService.findBySceneId(op_Scene.getScene_id());
				if(alarmConfigs != null && alarmConfigs.size() > 0){
					for (Op_AlarmConfig op_Alarmconfig : alarmConfigs) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("noticeType", op_Alarmconfig.getNoticeType().getTitle());
						map.put("alarmConfigId", op_Alarmconfig.getId());
						map.put("phone", op_Alarmconfig.getPhone());
						map.put("email", op_Alarmconfig.getEmail());
						map.put("isConfigure", op_Alarmconfig.getEnable().getValue()+"");
						map.put("interval", op_Alarmconfig.getInterval()+"");
						jsons.add(map);
					}
					List<Op_Alarmargument> alarmarguments = alarmargumentService.findBySceneId(op_Scene.getScene_id());
					for (Op_Alarmargument op_Alarmargument : alarmarguments) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("alarmargumentId", op_Alarmargument.getId());
						map.put("chId", op_Alarmargument.getChannel().getCh_id());
						map.put("chName", op_Alarmargument.getChannel().getCh_name());
						map.put("max", op_Alarmargument.getMax()==null?"":op_Alarmargument.getMax()+"");
						map.put("min", op_Alarmargument.getMin()==null?"":op_Alarmargument.getMin()+"");
						map.put("isConfigure", op_Alarmargument.getEnable().getValue()+"");
						jsons.add(map);
					}
				}else {
					List<Gm_Channel> channels = gm_ChannelService.findByScene_id(sceneId);
					Map<String, String> mapTop = new HashMap<String, String>();
					mapTop.put("alarmConfigId", "");
					mapTop.put("noticeType", "All");
					mapTop.put("phone", "");
					mapTop.put("email", "");
					mapTop.put("isConfigure", "Stop");
					mapTop.put("interval", "");
					jsons.add(mapTop);
					for (Gm_Channel gm_Channel : channels) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("alarmargumentId", "");
						map.put("chId", gm_Channel.getCh_id());
						map.put("chName", gm_Channel.getCh_name());
						map.put("max", "");
						map.put("min", "");
						map.put("phone", "");
						map.put("email", "");
						map.put("isConfigure", "Stop");
						jsons.add(map);
					}
				}
			}
			String json  = new Gson().toJson(jsons);
			//System.out.println(json);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String save() throws IOException{
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		try {
			alarmConfigService.save(configForm);
			out.print("{message:'操作成功……'}");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{message:'操作失败……'}");
		}
		return null;
	}
	
	
	
	
	
	
	
	//------------------------------------------------------
	private String sceneId;
	private AlarmConfigForm configForm;


	public AlarmConfigForm getConfigForm() {
		return configForm;
	}

	public void setConfigForm(AlarmConfigForm configForm) {
		this.configForm = configForm;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	
}
