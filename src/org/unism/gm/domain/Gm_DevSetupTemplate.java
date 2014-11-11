package org.unism.gm.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
/**
 * 在线配置模板信息表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devsetuptemplate" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gm_DevSetupTemplate {
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String devst_id; //Id
	private String devst_name;//模板名称
	private String devst_author;//添加者
	private Date devst_createDateTime;//创建时间
	
	@Column(length = 8000)
	private String devst_template;//模板内容
	
	public String getDevst_id() {
		return devst_id;
	}
	public void setDevst_id(String devst_id) {
		this.devst_id = devst_id;
	}
	public String getDevst_name() {
		return devst_name;
	}
	public void setDevst_name(String devst_name) {
		this.devst_name = devst_name;
	}	
	public String getDevst_author() {
		return devst_author;
	}
	public void setDevst_author(String devst_author) {
		this.devst_author = devst_author;
	}
	public Date getDevst_createDateTime() {
		return devst_createDateTime;
	}
	public void setDevst_createDateTime(Date devst_createDateTime) {
		this.devst_createDateTime = devst_createDateTime;
	}
	public String getDevst_template() {
		return devst_template;
	}
	public void setDevst_template(String devst_template) {
		this.devst_template = devst_template;
	}
}
