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
 * 功能菜单表
 * @author liucl
 *
 */

@Entity
@Table( name = "op_sysfun" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_SysFun implements Serializable {
	
	private static final long serialVersionUID = 240624340965743472L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String node_id;//功能菜单表ID
	private String node_displayName;//菜单名称
	private String node_url;//连接地址
	private Integer node_sequence;//排序
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "node_pid")
	private Op_SysFun node_pid;//父节点ID  自关联功能菜单表

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getNode_displayName() {
		return node_displayName;
	}

	public void setNode_displayName(String node_displayName) {
		this.node_displayName = node_displayName;
	}

	public String getNode_url() {
		return node_url;
	}

	public void setNode_url(String node_url) {
		this.node_url = node_url;
	}

	public Op_SysFun getNode_pid() {
		return node_pid;
	}

	public void setNode_pid(Op_SysFun node_pid) {
		this.node_pid = node_pid;
	}
	
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{node_id:").append(node_id)
				.append(",node_displayName:\"").append(node_displayName).append("\"")
				.append(",node_url:\"").append(node_url).append("\"")
				.append(",node_pid:\"").append(node_pid).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}

	public Integer getNode_sequence() {
		return node_sequence;
	}

	public void setNode_sequence(Integer node_sequence) {
		this.node_sequence = node_sequence;
	}
}
