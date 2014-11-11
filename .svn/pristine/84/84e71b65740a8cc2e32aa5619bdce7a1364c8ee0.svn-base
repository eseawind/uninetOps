package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
/**
 * 角色信息表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_roleinfo" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_RoleInfo implements Serializable {
	
	private static final long serialVersionUID = -301199406414385331L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String role_id;//角色信息表ID
	private String role_name;//角色名称
	private String role_desc;//角色说明
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{role_id:").append(role_id)
				.append(",role_name:\"").append(role_name).append("\"")
				.append(",role_desc:\"").append(role_desc).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}
}
