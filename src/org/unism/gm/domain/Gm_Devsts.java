package org.unism.gm.domain;

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
import org.unism.util.Dest_powerType;

/**
 * 智能设备状态表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devsts" )
public class Gm_Devsts {

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
	private String dest_serial; //序列号
	private Double dest_softVersion; //软件版本
	private Double dest_hardwareVersion; //硬件版本
	private Long dest_collectCyc; //采集周期
	private Long dest_storageCyc; //存储周期
	private Integer dest_ifClearData; //是否清除历史数据
	private Integer dest_ifReset; //是否重启
	private Integer dest_ifOnlineTmp; //是否临时在线
	private Integer dest_powerType = 1; //电源类型 0电池 1市电 默认1
	private String dest_workStsInfo;//运行状态详细信息
	private String dest_askForAddr;//索要地址

	public String getDest_askForAddr() {
		return dest_askForAddr;
	}


	public void setDest_askForAddr(String dest_askForAddr) {
		this.dest_askForAddr = dest_askForAddr;
	}


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


	public void setDest_regSts(Integer dest_regSts) {
		this.dest_regSts = dest_regSts;
	}


	public Integer getDest_linkSts() {
		return dest_linkSts;
	}


	public void setDest_linkSts(Integer dest_linkSts) {
		this.dest_linkSts = dest_linkSts;
	}


	public Integer getDest_workSts() {
		return dest_workSts;
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


	public void setDest_curTime(Date dest_curTime) {
		this.dest_curTime = dest_curTime;
	}


	public Integer getDest_timeSts() {
		return dest_timeSts;
	}


	public void setDest_timeSts(Integer dest_timeSts) {
		this.dest_timeSts = dest_timeSts;
	}


	public Date getDest_lastCommTime() {
		return dest_lastCommTime;
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


	public String getDest_serial() {
		return dest_serial;
	}


	public void setDest_serial(String dest_serial) {
		this.dest_serial = dest_serial;
	}


	public Double getDest_softVersion() {
		return dest_softVersion;
	}


	public void setDest_softVersion(Double dest_softVersion) {
		this.dest_softVersion = dest_softVersion;
	}


	public Double getDest_hardwareVersion() {
		return dest_hardwareVersion;
	}


	public void setDest_hardwareVersion(Double dest_hardwareVersion) {
		this.dest_hardwareVersion = dest_hardwareVersion;
	}


	public Long getDest_collectCyc() {
		return dest_collectCyc;
	}


	public void setDest_collectCyc(Long dest_collectCyc) {
		this.dest_collectCyc = dest_collectCyc;
	}


	public Long getDest_storageCyc() {
		return dest_storageCyc;
	}


	public void setDest_storageCyc(Long dest_storageCyc) {
		this.dest_storageCyc = dest_storageCyc;
	}


	public Integer getDest_ifClearData() {
		return dest_ifClearData;
	}


	public void setDest_ifClearData(Integer dest_ifClearData) {
		this.dest_ifClearData = dest_ifClearData;
	}


	public Integer getDest_ifOnlineTmp() {
		return dest_ifOnlineTmp;
	}


	public void setDest_ifOnlineTmp(Integer dest_ifOnlineTmp) {
		this.dest_ifOnlineTmp = dest_ifOnlineTmp;
	}


	public Integer getDest_ifReset() {
		return dest_ifReset;
	}


	public void setDest_ifReset(Integer dest_ifReset) {
		this.dest_ifReset = dest_ifReset;
	}


	public Integer getDest_powerType() {
		return dest_powerType;
	}


	public void setDest_powerType(Integer dest_powerType) {
		this.dest_powerType = dest_powerType;
	}
	
	public String getDest_powerType_str() {
		return Dest_powerType.findNameById(dest_powerType);
	}
	public String getDest_workStsInfo() {
		return dest_workStsInfo;
	}


	public void setDest_workStsInfo(String dest_workStsInfo) {
		this.dest_workStsInfo = dest_workStsInfo;
	}
}
