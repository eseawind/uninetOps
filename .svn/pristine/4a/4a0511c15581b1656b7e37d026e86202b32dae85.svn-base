package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.op.action.form.AlarmArgumentForm;
import org.unism.op.action.form.AlarmConfigForm;
import org.unism.op.dao.Op_AlarmConfigDao;
import org.unism.op.domain.Op_AlarmConfig;
import org.unism.op.domain.Op_Alarmargument;
import org.unism.op.domain.Op_Scene;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Op_AlarmConfigService extends BaseService<Op_AlarmConfig, String> {

	@Autowired Op_AlarmConfigDao op_AlarmConfigDao;
	@Autowired Op_AlarmargumentService alarmargumentService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Gm_ChannelService channelService;
	
	@Override
	protected IBaseDao<Op_AlarmConfig, String> getEntityDao() {
		return op_AlarmConfigDao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Op_AlarmConfig> findBySceneId(String sceneId) {
		Search search = new Search();
		search.addFilterEqual("scene.scene_id", sceneId);
		return op_AlarmConfigDao.search(search);
	}

	@Transactional
	public void save(AlarmConfigForm configForm) {
		if(configForm!=null){
			op_AlarmConfigDao.deleteBySceneId(configForm.getSceneId());
			alarmargumentService.deleteBySceneId(configForm.getSceneId());
			Op_AlarmConfig alarmconfig = new Op_AlarmConfig();
			Op_Scene scene = op_SceneService.findById(configForm.getSceneId());
			alarmconfig.setId(configForm.getId());
			alarmconfig.setScene(scene);
			//alarmconfig.setId(configForm.getId());
			alarmconfig.setSceneName(scene.getScene_name());
			alarmconfig.setEmail(configForm.getEmail());
			alarmconfig.setEnable(configForm.getEnable());
			alarmconfig.setInterval(configForm.getInterval());
			alarmconfig.setNoticeType(configForm.getNoticeType());
			alarmconfig.setPhone(configForm.getPhone());
			op_AlarmConfigDao.save(alarmconfig);
			
			List<AlarmArgumentForm> alarmArgumentForms = configForm.getAlarmArgumentForms();
			for (AlarmArgumentForm alarmArgumentForm : alarmArgumentForms) {
				Op_Alarmargument alarmargument = new Op_Alarmargument();
				Gm_Channel channel = channelService.findById(alarmArgumentForm.getChId());
				alarmargument.setId(alarmArgumentForm.getId());
				alarmargument.setChannel(channel);
				alarmargument.setChName(channel.getCh_name());
				alarmargument.setEnable(alarmArgumentForm.getEnable());
				alarmargument.setId(alarmArgumentForm.getId());
				String maxString = alarmArgumentForm.getMax();
				if("".equals(maxString)||maxString==null){
					alarmargument.setMax(null);
				}else{
					alarmargument.setMax(Double.parseDouble(maxString));
				}
				String minString = alarmArgumentForm.getMin();
				if("".equals(minString)||minString==null){
					alarmargument.setMin(null);
				}else{
					alarmargument.setMin(Double.parseDouble(minString));
				}
				alarmargument.setScene(scene);
				alarmargumentService.save(alarmargument);
			}
		}
	}

}
