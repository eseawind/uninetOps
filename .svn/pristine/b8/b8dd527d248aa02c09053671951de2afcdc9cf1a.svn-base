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
 * 角色权限对应表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_roleregith" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_RoleRegith implements Serializable {
	
	private static final long serialVersionUID = 674630017108870991L;

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String id;//角色权限对应表ID
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private  Op_RoleInfo role_id=new Op_RoleInfo();//角色ID 关联角色信息表
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	private Op_SysFun node_id =new Op_SysFun();//菜单ID 关联功能菜单表

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Op_RoleInfo getOpRoleInfo() {
		return role_id;
	}

	public void setOpRoleInfo(Op_RoleInfo opRoleInfo) {
		this.role_id = opRoleInfo;
	}

	public Op_SysFun getOpSysFun() {
		return node_id;
	}

	public void setOpSysFun(Op_SysFun opSysFun) {
		this.node_id = opSysFun;
	}
	
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{id:").append(id)
				.append(",role_id:\"").append(role_id).append("\"")
				.append(",node_id:\"").append(node_id).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}
}
