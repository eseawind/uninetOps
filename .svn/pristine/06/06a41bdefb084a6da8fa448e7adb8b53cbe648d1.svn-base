package org.unism.gm.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.unism.util.Dest_linkSts;
import org.unism.util.Dest_regSts;
import org.unism.util.Dest_timeSts;
import org.unism.util.Dest_workSts;
import org.unism.util.StaticDataManage;

/**
 * 智能设备状态表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devstshis" )
public class Gm_DevstsHis {
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String dest_id; //ID
	private String dev_addr; //设备地址
	private Integer dest_regSts; //注册身份 0: 未注册 1: 合法注册 2: 非法注册
	private Integer dest_linkSts; //连接状态 0: 长连接 1: 短连接 3：临时长连接
	private Integer dest_workSts; //运行状态 0: 离线 1: 在线
	private Long dest_commCyc; //心跳时间(秒)
	private Integer dest_repCyc; //上报周期(短连接)
	private Integer dest_sigStg; //信号强度
	private Double dest_commQuaily; //通信质量
	private Date dest_curTime; //设备当前时间
	private Integer dest_timeSts; //设备时间状态 0异步 1同步
	private Date dest_lastCommTime; //最近通信时间
	private Double dest_vol; //设备能量
	private Integer dest_volSts; //能量状态、
	private Integer dest_resetNum; //设备复位次数
	private Long dest_recData; //数据记录总数
	private Long dest_norepData; //数据未报数量
	private Integer dest_sleepCyc; //休眠时间(秒)
		
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dev_id")
	private Gm_Device dev_id = new Gm_Device(); //设备ID

	public String getDest_id() {
		return dest_id;
	}

	public void setDest_id(String dest_id) {
		this.dest_id = dest_id;
	}

	public String getDev_addr() {
		return dev_addr;
	}

	public void setDev_addr(String dev_addr) {
		this.dev_addr = dev_addr;
	}

	public Integer getDest_regSts() {
		return dest_regSts;
	}
	
	public String getDest_regSts_str(){
		if(this.dest_regSts!=null){
			List<Dest_regSts> dest_regStss = StaticDataManage.getDest_regStss();
			for(Dest_regSts dest_regSts : dest_regStss){
				if(String.valueOf(this.dest_regSts).equals(dest_regSts.getId())){
					return dest_regSts.getName();
				}
			}
		}
		return "";
	}

	public void setDest_regSts(Integer dest_regSts) {
		this.dest_regSts = dest_regSts;
	}

	public Integer getDest_linkSts() {
		return dest_linkSts;
	}
	
	public String getDest_linkSts_str() {
		if(this.dest_linkSts!=null){
			List<Dest_linkSts> dest_linkStss = StaticDataManage.getDest_linkStss();
			for(Dest_linkSts dest_linkSts : dest_linkStss){
				if(String.valueOf(this.dest_linkSts).equals(dest_linkSts.getId())){
					return dest_linkSts.getName();
				}
			}
		}
		return "";
	}

	public void setDest_linkSts(Integer dest_linkSts) {
		this.dest_linkSts = dest_linkSts;
	}

	public Integer getDest_workSts() {
		return dest_workSts;
	}
	
	public String getDest_workSts_str() {
		if(this.dest_workSts!=null){
			List<Dest_workSts> dest_workStss = StaticDataManage.getDest_workStss();
			for(Dest_workSts dest_workSts : dest_workStss){
				if(String.valueOf(this.dest_workSts).equals(dest_workSts.getId())){
					return dest_workSts.getName();
				}
			}
		}
		return "";
	}

	public void setDest_workSts(Integer dest_workSts) {
		this.dest_workSts = dest_workSts;
	}

	public Long getDest_commCyc() {
		return dest_commCyc;
	}

	public void setDest_commCyc(Long dest_commCyc) {
		this.dest_commCyc = dest_commCyc;
	}

	public Integer getDest_repCyc() {
		return dest_repCyc;
	}

	public void setDest_repCyc(Integer dest_repCyc) {
		this.dest_repCyc = dest_repCyc;
	}

	public Integer getDest_sigStg() {
		return dest_sigStg;
	}

	public void setDest_sigStg(Integer dest_sigStg) {
		this.dest_sigStg = dest_sigStg;
	}

	public Double getDest_commQuaily() {
		return dest_commQuaily;
	}

	public void setDest_commQuaily(Double dest_commQuaily) {
		this.dest_commQuaily = dest_commQuaily;
	}

	public Date getDest_curTime() {
		return dest_curTime;
	}

	public String getDest_curTime_str() {
		if(this.dest_curTime!=null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(this.dest_curTime);
		}
		return "";
	}
	
	public void setDest_curTime(Date dest_curTime) {
		this.dest_curTime = dest_curTime;
	}

	public Integer getDest_timeSts() {
		return dest_timeSts;
	}
	
	public String getDest_timeSts_str() {
		if(this.dest_timeSts!=null){
			List<Dest_timeSts> dest_timeStss = StaticDataManage.getDest_timeStss();
			for(Dest_timeSts dest_timeSts : dest_timeStss){
				if(String.valueOf(this.dest_timeSts).equals(dest_timeSts.getId())){
					return dest_timeSts.getName();
				}
			}
		}
		return "";
	}

	public void setDest_timeSts(Integer dest_timeSts) {
		this.dest_timeSts = dest_timeSts;
	}

	public Date getDest_lastCommTime() {
		return dest_lastCommTime;
	}

	public String getDest_lastCommTime_str() {
		if(this.dest_lastCommTime!=null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(this.dest_lastCommTime);
		}
		return "";
	}
	
	public void setDest_lastCommTime(Date dest_lastCommTime) {
		this.dest_lastCommTime = dest_lastCommTime;
	}

	public Double getDest_vol() {
		return dest_vol;
	}

	public void setDest_vol(Double dest_vol) {
		this.dest_vol = dest_vol;
	}

	public Integer getDest_volSts() {
		return dest_volSts;
	}

	public void setDest_volSts(Integer dest_volSts) {
		this.dest_volSts = dest_volSts;
	}

	public Integer getDest_resetNum() {
		return dest_resetNum;
	}

	public void setDest_resetNum(Integer dest_resetNum) {
		this.dest_resetNum = dest_resetNum;
	}

	public Long getDest_recData() {
		return dest_recData;
	}

	public void setDest_recData(Long dest_recData) {
		this.dest_recData = dest_recData;
	}

	public Long getDest_norepData() {
		return dest_norepData;
	}

	public void setDest_norepData(Long dest_norepData) {
		this.dest_norepData = dest_norepData;
	}

	public Integer getDest_sleepCyc() {
		return dest_sleepCyc;
	}

	public void setDest_sleepCyc(Integer dest_sleepCyc) {
		this.dest_sleepCyc = dest_sleepCyc;
	}

	public Gm_Device getDev_id() {
		return dev_id;
	}

	public void setDev_id(Gm_Device dev_id) {
		this.dev_id = dev_id;
	}
	
	
}
