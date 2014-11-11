package org.unism.gm.domain;

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
import org.unism.gm.service.Gm_DevNetService;
import org.wangzz.core.utils.SpringContextHolder;

/**
 * 网络信息表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devnet" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gm_DevNet {

	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String net_id; //Id
	private String net_no; //网络编号
	private String net_addr; //网络地址
	private Integer net_type; //网络类型 0：远程网 M2M 1：无线传感网络 wsn 2：有线传感网 智能单元
	private String net_role; //网内角色 01: 网关 02: 独立设备 11: RD 12: ED 	13：CD 14：LD 21：传感器 22：变送器
	private Integer net_depth; //网内深度
	private Integer net_linkSts; //连接方式 1: 长连接 2: 短连接 	3：无连接
	private Integer net_appType; //应用类型
	private String net_sim; //SIM卡号
	private String net_smsc; //短信中心号
	private Integer net_pltType; //协议类型
	private Integer net_state; //网络使用状态 0:未用 1:在用
	private String net_pid; //父节点ID
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_id")
	private Gm_Device dev_id = new Gm_Device(); //设备ID
	
	public String getNet_pno(){
		Gm_DevNetService gm_DevNetService = SpringContextHolder.getBean("gm_DevNetService");
		if(this.net_pid == null){
			return "root";
		}else if(this.net_pid.equals("FF")){
			return "FF";			
		}else{
			return gm_DevNetService.findNet_pno(this.net_pid);
		}
	}
	
	public String getLinkStsStr() {
		if(net_linkSts == null)
			return "空配置";
		switch (net_linkSts) {
		case 1:
			return "长连接";
		case 2:
			return "短连接";
		case 3:
			return "无连接";
		default:
			return "无连接";
		}
	}
	
	public String getNet_id() {
		return net_id;
	}
	
	public void setNet_id(String net_id) {
		this.net_id = net_id;
	}
	public String getNet_addr() {
		return net_addr;
	}
	public void setNet_addr(String net_addr) {
		this.net_addr = net_addr;
	}
	public Integer getNet_type() {
		return net_type;
	}
	public void setNet_type(Integer net_type) {
		this.net_type = net_type;
	}
	public String getNet_role() {
		return net_role;
	}
	public void setNet_role(String net_role) {
		this.net_role = net_role;
	}
	public Integer getNet_depth() {
		return net_depth;
	}
	public void setNet_depth(Integer net_depth) {
		this.net_depth = net_depth;
	}
	public Integer getNet_appType() {
		return net_appType;
	}
	public void setNet_appType(Integer net_appType) {
		this.net_appType = net_appType;
	}
	public String getNet_sim() {
		return net_sim;
	}
	public void setNet_sim(String net_sim) {
		this.net_sim = net_sim;
	}
	public String getNet_smsc() {
		return net_smsc;
	}
	public void setNet_smsc(String net_smsc) {
		this.net_smsc = net_smsc;
	}
	public Integer getNet_pltType() {
		return net_pltType;
	}
	public void setNet_pltType(Integer net_pltType) {
		this.net_pltType = net_pltType;
	}
	public Integer getNet_state() {
		return net_state;
	}
	public void setNet_state(Integer net_state) {
		this.net_state = net_state;
	}
	public Gm_Device getDev_id() {
		return dev_id;
	}
	public void setDev_id(Gm_Device dev_id) {
		this.dev_id = dev_id;
	}

	public String getNet_no() {
		return net_no;
	}

	public void setNet_no(String net_no) {
		this.net_no = net_no;
	}

	public Integer getNet_linkSts() {
		return net_linkSts;
	}

	public void setNet_linkSts(Integer net_linkSts) {
		this.net_linkSts = net_linkSts;
	}

	public String getNet_pid() {
		return net_pid;
	}

	public void setNet_pid(String net_pid) {
		this.net_pid = net_pid;
	}

}
