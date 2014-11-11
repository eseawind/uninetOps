package org.unism.op.action.form;

import java.util.ArrayList;
import java.util.List;

import org.unism.op.domain.reference.Enable;
import org.unism.op.domain.reference.NoticeType;

public class AlarmConfigForm {

private String id;
	
	private String sceneId; //场景ID
	
	private NoticeType noticeType ;
	
	private String phone;
	
	private String email;
	
	private Integer interval;
	
	private Enable enable;
	
	private List<AlarmArgumentForm> alarmArgumentForms = new ArrayList<AlarmArgumentForm>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public NoticeType getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(NoticeType noticeType) {
		this.noticeType = noticeType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Enable getEnable() {
		return enable;
	}

	public void setEnable(Enable enable) {
		this.enable = enable;
	}

	public List<AlarmArgumentForm> getAlarmArgumentForms() {
		return alarmArgumentForms;
	}

	public void setAlarmArgumentForms(List<AlarmArgumentForm> alarmArgumentForms) {
		this.alarmArgumentForms = alarmArgumentForms;
	}
}
