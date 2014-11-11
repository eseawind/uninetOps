package org.unism.pro.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 潮汐信息表
 * @author weixh
 *
 */
@Entity
@Table( name = "pro_tides" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pro_Tides  implements Serializable {
	
	private static final long serialVersionUID = 5939274294461093872L;
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String tide_id;//ID
	private Date tide_dateTime;//潮汐时间
	private Double tide_values;//潮高
	private Integer tide_type;//-1 不是最大值也不是最小值 0：最小值 1：最大值
	
	public String getTide_id() {
		return tide_id;
	}
	public void setTide_id(String tide_id) {
		this.tide_id = tide_id;
	}
	public Date getTide_dateTime() {
		return tide_dateTime;
	}
	public void setTide_dateTime(Date tide_dateTime) {
		this.tide_dateTime = tide_dateTime;
	}
	public Double getTide_values() {
		return tide_values;
	}
	public void setTide_values(Double tide_values) {
		this.tide_values = tide_values;
	}
	public Integer getTide_type() {
		return tide_type;
	}
	public void setTide_type(Integer tide_type) {
		this.tide_type = tide_type;
	}

}