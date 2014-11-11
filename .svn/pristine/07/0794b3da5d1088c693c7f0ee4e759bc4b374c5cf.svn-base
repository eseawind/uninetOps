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
 * 平台用户表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_plantformuser" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_PlantformUser implements Serializable {
	
	private static final long serialVersionUID = -6592361644699738486L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String pla_id;//平台用户表ID
	private String pla_username;//平台用户名称
	private Integer pla_userType;//平台用户类型
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id=new Op_Scene();//对应场景ID 关联场景信息表

	public String getPla_id() {
		return pla_id;
	}

	public void setPla_id(String pla_id) {
		this.pla_id = pla_id;
	}

	public String getPla_username() {
		return pla_username;
	}

	public void setPla_username(String pla_username) {
		this.pla_username = pla_username;
	}

	public Integer getPla_userType() {
		return pla_userType;
	}

	public void setPla_userType(Integer pla_userType) {
		this.pla_userType = pla_userType;
	}

	public Op_Scene getOpScene() {
		return scene_id;
	}

	public void setOpScene(Op_Scene opScene) {
		this.scene_id = opScene;
	}
	
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{pla_id:").append(pla_id)
				.append(",pla_username:\"").append(pla_username).append("\"")
				.append(",pla_userType:\"").append(pla_userType).append("\"")				
				.append(",scene_id:\"").append(scene_id).append("\"");				
				builder.append("},");				
		return builder.toString();
	}
}
