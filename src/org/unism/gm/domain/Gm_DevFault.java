package org.unism.gm.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 故障信息表(智能设备)
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devfault" )
public class Gm_DevFault {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String def_id; //ID
	private Integer def_object;//故障对象0：平台,1：GPRS,2：WSN,3：传感器,4：控制设备
	private Integer def_type; //故障类型 1：通讯状况,2：设备运行状况,3：数据时间连续性和完整性,4：数据合理性
	private Integer def_symptom;//故障症状(码表)
	private Integer def_grade; //故障等级 0: 轻微 	1: 中度
	private String def_occurReason; //故障发生原因
	private Date def_occurTime; //故障发生时间
	private Date def_dealTime; //解决故障时间
	private String def_dealMethod; //解决故障方法
	private String ch_id; //通道ID
	@Column( length = 500 )
	private String def_desc; //故障说明
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_id")
	private Gm_Device dev_id = new Gm_Device(); //智能设备ID

	public String getDef_id()
	{
		return def_id;
	}

	public void setDef_id(String def_id)
	{
		this.def_id = def_id;
	}

	public Integer getDef_type()
	{
		return def_type;
	}
	public String getDef_type_str()
	{
		return org.unism.util.Def_type.findNameById(this.def_type);
	}

	public void setDef_type(Integer def_type)
	{
		this.def_type = def_type;
	}

	public Integer getDef_grade()
	{
		return def_grade;
	}

	public void setDef_grade(Integer def_grade)
	{
		this.def_grade = def_grade;
	}

	public String getDef_occurReason()
	{
		return def_occurReason;
	}

	public void setDef_occurReason(String def_occurReason)
	{
		this.def_occurReason = def_occurReason;
	}

	public Date getDef_occurTime()
	{
		return def_occurTime;
	}

	public void setDef_occurTime(Date def_occurTime)
	{
		this.def_occurTime = def_occurTime;
	}

	public Date getDef_dealTime()
	{
		return def_dealTime;
	}

	public void setDef_dealTime(Date def_dealTime)
	{
		this.def_dealTime = def_dealTime;
	}

	public String getDef_dealMethod()
	{
		return def_dealMethod;
	}

	public void setDef_dealMethod(String def_dealMethod)
	{
		this.def_dealMethod = def_dealMethod;
	}

	public String getCh_id()
	{
		return ch_id;
	}

	public void setCh_id(String ch_id)
	{
		this.ch_id = ch_id;
	}

	public String getDef_desc()
	{
		return def_desc;
	}

	public void setDef_desc(String def_desc)
	{
		this.def_desc = def_desc;
	}

	public Gm_Device getDev_id()
	{
		return dev_id;
	}

	public void setDev_id(Gm_Device dev_id)
	{
		this.dev_id = dev_id;
	}

	public Integer getDef_object() {
		return def_object;
	}
	public String getDef_object_str()
	{
		return org.unism.util.Def_object.findNameById(this.def_object);
	}

	public void setDef_object(Integer defObject) {
		def_object = defObject;
	}

	public Integer getDef_symptom() {
		return def_symptom;
	}
	public String getDef_symptom_str() {
		if(def_object!=null && def_type!=null && def_symptom!=null)
			return org.unism.util.StaticDataManage.getDevFaultInfo(def_object, def_type, def_symptom);
		else
			return null;
	}
	
	public void setDef_symptom(Integer defSymptom) {
		def_symptom = defSymptom;
	}

	


}
