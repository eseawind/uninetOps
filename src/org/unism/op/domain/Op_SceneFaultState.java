package org.unism.op.domain;

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
 * 场景故障报表
 * @author weixh
 *
 */
@Entity
@Table( name = "op_scenefaultstate" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_SceneFaultState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 452326413661657892L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String sfs_id;//ID
	private String sfs_sceneName;//场景名称
	private String sfs_sceneId;//场景id
	private String sfs_devState;//设备状态
	private String sfs_inf;//详细信息
	private Date sfs_date;//日期
	public String getSfs_id() {
		return sfs_id;
	}
	public void setSfs_id(String sfs_id) {
		this.sfs_id = sfs_id;
	}
	public String getSfs_sceneName() {
		return sfs_sceneName;
	}
	public void setSfs_sceneName(String sfs_sceneName) {
		this.sfs_sceneName = sfs_sceneName;
	}
	public String getSfs_sceneId() {
		return sfs_sceneId;
	}
	public void setSfs_sceneId(String sfs_sceneId) {
		this.sfs_sceneId = sfs_sceneId;
	}
	public String getSfs_devState() {
		return sfs_devState;
	}
	public void setSfs_devState(String sfs_devState) {
		this.sfs_devState = sfs_devState;
	}
	public String getSfs_inf() {
		return sfs_inf;
	}
	public void setSfs_inf(String sfs_inf) {
		this.sfs_inf = sfs_inf;
	}
	public Date getSfs_date() {
		return sfs_date;
	}
	public void setSfs_date(Date sfs_date) {
		this.sfs_date = sfs_date;
	}


}
