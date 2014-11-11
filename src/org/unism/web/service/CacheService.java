package org.unism.web.service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_HistorydataService;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.op.service.Op_SceneService;

import com.google.common.collect.MapMaker;

@Service
public class CacheService {
	
	private Logger logger = Logger.getLogger(CacheService.class);
	
	private ConcurrentMap<String, String> monitorChartJsonBySceneIdChTypeMap = new MapMaker().expiration(10, TimeUnit.MINUTES).makeMap() ;
	
	
	public String getJsonBySceneIdChType(String sceneId, String chTypeNo) {
		String json = monitorChartJsonBySceneIdChTypeMap.get(sceneId + "," + chTypeNo);
		if(json == null) {
			Op_Scene op_Scene = this.op_SceneService.findById(sceneId);			
			json = "[";
			if(op_Scene != null){
				Op_ChannelType op_ChannelType = this.op_ChannelTypeService.findByChtype_no(chTypeNo);
				if(op_ChannelType!=null){
					if(op_ChannelType.getChtype_id()!=null){
						List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(),op_ChannelType.getChtype_id());
						if(gm_Channel_list.size() > 0){
							for(Gm_Channel gm_Channel : gm_Channel_list){
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',visible:true,marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";
							}
							json = json.substring(0,json.length()-1);
						}
					}	
				}	
			}
			json =  json + "]"; 
			monitorChartJsonBySceneIdChTypeMap.put(sceneId + "," + chTypeNo, json);
		} else {
			logger.info("cacheed:sceneId-" + sceneId + ",chTypeNo-" + chTypeNo);
		}
		return json;
	}
	
	
	@Autowired Op_SceneService op_SceneService;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	@Autowired Gm_HistorydataService gm_HistorydataService;
	
}
