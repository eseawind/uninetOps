package org.unism.op.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.op.action.form.AutoCtrlConfigForm;
import org.unism.op.dao.AutoCtrlConfigDao;
import org.unism.op.domain.AutoCtrlConfig;
import org.unism.op.domain.AutoCtrlFactor;
import org.unism.op.domain.Op_UserInfo;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
import org.wangzz.core.web.struts2.Struts2Utils;

@Service
public class AutoCtrlConfigService extends BaseService<AutoCtrlConfig, String> {

	private Logger logger = LoggerFactory.getLogger(AutoCtrlConfigService.class);
	
	@Autowired AutoCtrlConfigDao autoCtrlConfigDao;
	@Autowired Gm_ChannelService channelService;
	@Autowired AutoCtrlFactorService autoCtrlFactorService;
	
	@Override
	protected IBaseDao<AutoCtrlConfig, String> getEntityDao() {
		return autoCtrlConfigDao;
	}

	public String findBySceneId(String sceneId) {
		StringBuffer buffer = new StringBuffer("[");
		try {
			Search search = new Search();
			search.addFilterEqual("scene.scene_id", sceneId);
			List<AutoCtrlConfig> autoCtrlConfigs = autoCtrlConfigDao.search(search);
			for (AutoCtrlConfig autoCtrlConfig : autoCtrlConfigs) {
				List<AutoCtrlFactor> autoCtrlFactors = autoCtrlConfig.getAutoCtrlFactors();
				StringBuffer channelName = new StringBuffer("[");
				StringBuffer handle = new StringBuffer("[");
				StringBuffer condition = new StringBuffer("[");
				StringBuffer trigger = new StringBuffer("[");
				for (AutoCtrlFactor autoCtrlFactor : autoCtrlFactors) {
					StringBuffer chName = new StringBuffer();
					String chId = autoCtrlFactor.getChannelId();
					if(StringUtils.hasText(chId)){
						String[] chIdArr = chId.split(",");
						List<Gm_Channel> channels = channelService.findByIds(chIdArr);
						for (Gm_Channel gm_Channel : channels) {
							chName.append(gm_Channel.getCh_name()).append("（").append(gm_Channel.getCh_no()).append("）<br/>");
						}
						channelName.append("{\"name\":\"").append(chName.toString()).append("\"},");
						handle.append("{\"handle\":\"").append(autoCtrlFactor.getHandle().getTitle()).append("\"},");
						condition.append("{\"condition\":\"").append(autoCtrlFactor.getCondition().getTitle()).append("\"},");
						trigger.append("{\"trigger\":\"").append(autoCtrlFactor.getTrigger()).append("\"},");
					}
				}
				if(channelName.length() > 1){
					channelName.deleteCharAt(channelName.length()-1);
				}
				if(handle.length() > 1){
					handle.deleteCharAt(handle.length()-1);
				}
				if(condition.length() > 1){
					condition.deleteCharAt(condition.length()-1);
				}
				if(trigger.length() > 1){
					trigger.deleteCharAt(trigger.length()-1);
				}
				channelName.append("]");
				handle.append("]");
				condition.append("]");
				trigger.append("]");
				buffer.append("{\"name\":\"").append(autoCtrlConfig.getDevCtrl().getDect_name()).append("\",\"enable\":\"").append(autoCtrlConfig.getEnable().getTitle()).append("\",\"type\":\"").append(autoCtrlConfig.getType()).append("\",\"cond\":\"").append(autoCtrlConfig.getCond().getTitle()).append("\",\"chName\":").append(channelName.toString()).append(",\"handle\":").append(handle.toString()).append(",\"condition\":").append(condition.toString()).append(",\"trigger\":").append(trigger.toString()).append(",\"id\":\"").append(autoCtrlConfig.getId()).append("\"},");
			}
			if(buffer.length() > 1){
				buffer.deleteCharAt(buffer.length()-1);
			}
		} catch (Exception e) {
			logger.error("查询场景 id "+ sceneId+" 下的自动控制设备异常",e);
		}
		buffer.append("]");
		return buffer.toString();
	}

	public String save(AutoCtrlConfigForm autoCtrlConfigForm) {
		try {
			AutoCtrlConfig config = new AutoCtrlConfig();
			config.setScene(autoCtrlConfigForm.getScene());
			config.setDevCtrl(autoCtrlConfigForm.getDevCtrl());
			config.setType(autoCtrlConfigForm.getType());
			config.setCond(autoCtrlConfigForm.getCond());
			config.setAddTime(new Date());
			config.setEnable(autoCtrlConfigForm.getEnable());
			Op_UserInfo user = (Op_UserInfo) Struts2Utils.getSession().getAttribute("user");
			config.setUser(user.getUser_name());
			autoCtrlConfigDao.save(config);
			Double[] triggerArr = autoCtrlConfigForm.getTrigger();
			for (int i = 0; i < triggerArr.length; i++) {
				AutoCtrlFactor autoCtrlFactor = new AutoCtrlFactor();
				autoCtrlFactor.setAutoCtrlConfig(config);
				autoCtrlFactor.setChannelId(autoCtrlConfigForm.getChIds()[i]);
				autoCtrlFactor.setHandle(autoCtrlConfigForm.getHandles()[i]);
				autoCtrlFactor.setCondition(autoCtrlConfigForm.getConditions()[i]);
				autoCtrlFactor.setTrigger(triggerArr[i]);
				autoCtrlFactorService.save(autoCtrlFactor);
			}
			return "操作成功！";
		} catch (Exception e) {
			logger.error("自动控制配置保存异常 ",e);
		}
		return "操作失败！";
	}
	
	public AutoCtrlConfig findByDect(Gm_DevCtrl devCtrl){
		Search search = new Search();
		search.addFilterEqual("devCtrl", devCtrl);
		List<AutoCtrlConfig> list = this.autoCtrlConfigDao.search(search);
		if(!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Transactional
	public String update(AutoCtrlConfigForm autoCtrlConfigForm, String accId) {
		try {
			AutoCtrlConfig autoCtrlConfig = this.autoCtrlConfigDao.findById(accId);
			List<AutoCtrlFactor> autoCtrlFactors = autoCtrlConfig.getAutoCtrlFactors();
			for (AutoCtrlFactor autoCtrlFactor : autoCtrlFactors) {
				autoCtrlFactorService.delete(autoCtrlFactor);
			}
			autoCtrlConfigDao.delete(autoCtrlConfig);
			this.save(autoCtrlConfigForm);
			return "操作成功！";
		} catch (Exception e) {
			logger.error("自动控制配置修改异常 ",e);
		}
		return "操作失败！";
	}

	@Transactional
	public String delete(String accId) {
		try {
			AutoCtrlConfig autoCtrlConfig = this.autoCtrlConfigDao.findById(accId);
			List<AutoCtrlFactor> autoCtrlFactors = autoCtrlConfig.getAutoCtrlFactors();
			for (AutoCtrlFactor autoCtrlFactor : autoCtrlFactors) {
				autoCtrlFactorService.delete(autoCtrlFactor);
			}
			autoCtrlConfigDao.delete(autoCtrlConfig);
			return "操作成功！";
		} catch (Exception e) {
			logger.error("自动控制配置删除异常 accId："+accId,e);
		}
		return "操作失败！";
	}
}
