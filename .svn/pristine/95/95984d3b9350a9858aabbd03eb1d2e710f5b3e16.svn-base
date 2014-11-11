package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.unism.op.domain.reference.Enable;
import org.unism.op.domain.reference.NoticeType;

@Entity
@Table( name = "op_alarmconfig" )
public class Op_AlarmConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4634381966764362678L;

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene; //场景ID
	
	@Column(name = "scene_name")
	private String sceneName;
	
	@Column(name = "al_noticeType")
	private NoticeType noticeType ;
	
	@Column(name = "al_phone", length = 8000)
	private String phone;
	
	@Column(name = "al_email")
	private String email;
	
	@Column(name = "al_interval")
	private Integer interval;
	
	@Column(name = "al_enable")
	private Enable enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Op_Scene getScene() {
		return scene;
	}

	public void setScene(Op_Scene scene) {
		this.scene = scene;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
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
}