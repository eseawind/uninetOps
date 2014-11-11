package org.unism.pro.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.op.domain.Op_Scene;

@Entity
@Table(name = "pro_fisheries")
public class Pro_Fisheries implements Serializable {

	private static final long serialVersionUID = 3992574856202465783L;

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String fi_id; // ID

	private String fi_pondNo; // 养殖池编号
	private String fi_pondName; // 养殖池名称
	private String fi_userName; // 养殖户姓名
	private Double fi_area; // 面积
	private Double fi_depth; // 水深
	private String fi_aquaticType; // 水草种类
	private String fi_cultureObject; // 养殖对象
	private String fi_species; // 品种
	private String fi_batch; // 批次
	private Integer fi_remainNum; // 剩余数
	private String fi_productionStage; // 生长阶段
	private String fi_doyj; // 预警值
	private String fi_do; // 报警值
	private String fi_phone; //手机号
	private Integer fi_state=0; //状态 短信标识
	private Date  fi_putTime;  //投放时间
	private String fi_putTime_sub;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Op_Scene scene; // 所属场景ID

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id; // 控制设备ID
	
	public String getFi_id() {
		return fi_id;
	}

	public void setFi_id(String fi_id) {
		this.fi_id = fi_id;
	}

	public String getFi_pondNo() {
		return fi_pondNo;
	}

	public void setFi_pondNo(String fi_pondNo) {
		this.fi_pondNo = fi_pondNo;
	}

	public String getFi_pondName() {
		return fi_pondName;
	}

	public void setFi_pondName(String fi_pondName) {
		this.fi_pondName = fi_pondName;
	}

	public Double getFi_area() {
		return fi_area;
	}

	public void setFi_area(Double fi_area) {
		this.fi_area = fi_area;
	}

	public Double getFi_depth() {
		return fi_depth;
	}

	public void setFi_depth(Double fi_depth) {
		this.fi_depth = fi_depth;
	}

	public String getFi_aquaticType() {
		return fi_aquaticType;
	}

	public void setFi_aquaticType(String fi_aquaticType) {
		this.fi_aquaticType = fi_aquaticType;
	}

	public String getFi_cultureObject() {
		return fi_cultureObject;
	}

	public void setFi_cultureObject(String fi_cultureObject) {
		this.fi_cultureObject = fi_cultureObject;
	}

	public String getFi_species() {
		return fi_species;
	}

	public void setFi_species(String fi_species) {
		this.fi_species = fi_species;
	}

	public String getFi_batch() {
		return fi_batch;
	}

	public void setFi_batch(String fi_batch) {
		this.fi_batch = fi_batch;
	}

	public Integer getFi_remainNum() {
		return fi_remainNum;
	}

	public void setFi_remainNum(Integer fi_remainNum) {
		this.fi_remainNum = fi_remainNum;
	}

	public String getFi_productionStage() {
		return fi_productionStage;
	}

	public void setFi_productionStage(String fi_productionStage) {
		this.fi_productionStage = fi_productionStage;
	}

	public String getFi_do() {
		return fi_do;
	}

	public void setFi_do(String fi_do) {
		this.fi_do = fi_do;
	}

	public Op_Scene getScene() {
		return scene;
	}

	public void setScene(Op_Scene scene) {
		this.scene = scene;
	}

	public String getFi_doyj() {
		return fi_doyj;
	}

	public void setFi_doyj(String fi_doyj) {
		this.fi_doyj = fi_doyj;
	}

	public String getFi_phone() {
		return fi_phone;
	}

	public void setFi_phone(String fi_phone) {
		this.fi_phone = fi_phone;
	}

	public Integer getFi_state() {
		return fi_state;
	}

	public void setFi_state(Integer fi_state) {
		this.fi_state = fi_state;
	}

	public Gm_DevCtrl getDect_id() {
		return dect_id;
	}

	public void setDect_id(Gm_DevCtrl dect_id) {
		this.dect_id = dect_id;
	}

	public String getFi_userName() {
		return fi_userName;
	}

	public void setFi_userName(String fi_userName) {
		this.fi_userName = fi_userName;
	}

	public Date getFi_putTime() {
		return fi_putTime;
	}

	public void setFi_putTime(Date fi_putTime) {
		this.fi_putTime = fi_putTime;
	}

	
	public String getFi_putTime_sub() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date backTime=fi_putTime;
		if(backTime!=null){
			return df.format(fi_putTime);
		}else{
			return "";
		}
	}

	public void setFi_putTime_sub(String fi_putTime_sub) {
		this.fi_putTime_sub = fi_putTime_sub;
	}

//	public Date getFi_putTime() {
//		return fi_putTime;
//	}
//
//	public Date getFi_putTime_sub() {		
//		this.fi_putTime = fi_putTime;
//	}
//	
//	public void setFi_putTime(Date fi_putTime) {
//		this.fi_putTime = fi_putTime;
//	}
//
//	public void setFi_putTime_sub(String fi_putTime_sub) {
//		this.fi_putTime_sub = fi_putTime_sub;

//	}
}
