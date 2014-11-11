package org.unism.op.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.op.action.form.AutoCtrlConfigForm;
import org.unism.op.domain.AutoCtrlConfig;
import org.unism.op.domain.AutoCtrlFactor;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.AutoCtrlConfigService;
import org.unism.op.service.AutoCtrlFactorService;
import org.unism.op.service.Op_SceneService;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AutoCtrlConfigAction extends ActionSupport {

	public String list(){
		return "list";
	}
	
	public String add(){
		List<Gm_DevCtrl> devCtrls = devCtrlService.findByScene_id(sceneId);
		List<Gm_Channel> channels = channelService.findByScene_id(sceneId);
		Op_Scene scene = sceneService.findById(sceneId);
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("devCtrls", devCtrls);
		request.setAttribute("channels", channels);
		request.setAttribute("scene", scene);
		return "add";
	}
	
	public void findBySceneId(){
		try {
			PrintWriter writer = Struts2Utils.getResponse().getWriter();
			String json = autoCtrlConfigService.findBySceneId(sceneId);
			writer.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String save(){
		String message = autoCtrlConfigService.save(autoCtrlConfigForm);
		addActionMessage(message);
		return "success";
	}
	
	public String edit(){
		AutoCtrlConfig autoCtrlConfig = autoCtrlConfigService.findById(accId);
		List<AutoCtrlFactor> autoCtrlFactors = autoCtrlConfig.getAutoCtrlFactors();
		String sceneId = autoCtrlConfig.getScene().getScene_id();
		List<Gm_DevCtrl> devCtrls = devCtrlService.findByScene_id(sceneId);
		List<Gm_Channel> channels = channelService.findByScene_id(sceneId);
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("autoCtrlConfig", autoCtrlConfig);
		request.setAttribute("autoCtrlFactors", autoCtrlFactors);
		request.setAttribute("devCtrls", devCtrls);
		request.setAttribute("channels", channels);
		return "edit";
	}
	
	public String delete(){
		String message = autoCtrlConfigService.delete(accId);
		addActionMessage(message);
		return "success";
	}
	
	public String update(){
		String message = autoCtrlConfigService.update(autoCtrlConfigForm,accId);
		addActionMessage(message);
		return "success";
	}
	
	
	@Autowired AutoCtrlConfigService autoCtrlConfigService;
	@Autowired AutoCtrlFactorService autoCtrlFactorService;
	@Autowired Gm_DevCtrlService devCtrlService;
	@Autowired Gm_ChannelService channelService;
	@Autowired Op_SceneService sceneService;
	
	private String sceneId;
	private AutoCtrlConfigForm autoCtrlConfigForm;
	private String accId;

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public AutoCtrlConfigForm getAutoCtrlConfigForm() {
		return autoCtrlConfigForm;
	}

	public void setAutoCtrlConfigForm(AutoCtrlConfigForm autoCtrlConfigForm) {
		this.autoCtrlConfigForm = autoCtrlConfigForm;
	}
}
