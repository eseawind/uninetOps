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
 * 行政区划表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_areas" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_Areas implements Serializable {
	
	private static final long serialVersionUID = -2519985766343102749L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String area_id;//代码
	private String area_name;//名称
	private String area_desc;//说明
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getArea_desc() {
		return area_desc;
	}
	public void setArea_desc(String area_desc) {
		this.area_desc = area_desc;
	}
	
	
}
