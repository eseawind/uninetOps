package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
/**
 * 用户场景关联表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_userinfo_scene" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_UserInfo_Scene implements Serializable {
	
	private static final long serialVersionUID = 6827154215721076675L;

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String ID;//用户场景关联表ID

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id =new Op_Scene();//场景ID 关联项目信息表
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Op_UserInfo user_id =new Op_UserInfo();//用户ID 关联用户信息表
	
	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public Op_Scene getScene_id() {
		return scene_id;
	}



	public void setScene_id(Op_Scene scene_id) {
		this.scene_id = scene_id;
	}



	public Op_UserInfo getUser_id() {
		return user_id;
	}



	public void setUser_id(Op_UserInfo user_id) {
		this.user_id = user_id;
	}



	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{ID:").append(ID)				
				.append(",scene_id:\"").append(scene_id).append("\"")
				.append(",user_id:\"").append(user_id).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}
}
