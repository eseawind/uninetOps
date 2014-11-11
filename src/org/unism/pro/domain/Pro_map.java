package org.unism.pro.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.unism.op.domain.Op_Scene;

@Entity
@Table(name = "pro_map")
public class Pro_map  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4683917929746146894L;
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String ma_id; // 主键ID
	
	private Double ma_longitude;//经度
	private Double ma_latitude;//纬度
	private int map_type;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene_id = new Op_Scene(); //场景ID
	public String getMa_id() {
		return ma_id;
	}
	public void setMa_id(String ma_id) {
		this.ma_id = ma_id;
	}
	public Double getMa_longitude() {
		return ma_longitude;
	}
	public void setMa_longitude(Double ma_longitude) {
		this.ma_longitude = ma_longitude;
	}
	public Double getMa_latitude() {
		return ma_latitude;
	}
	public void setMa_latitude(Double ma_latitude) {
		this.ma_latitude = ma_latitude;
	}
	public Op_Scene getScene_id() {
		return scene_id;
	}
	public void setScene_id(Op_Scene scene_id) {
		this.scene_id = scene_id;
	}
	public int getMap_type()
	{
		return map_type;
	}
	public void setMap_type(int map_type)
	{
		this.map_type = map_type;
	}

}
