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
import org.unism.gm.dao.Gm_DevCtrlOperateDao;
import org.unism.gm.service.Gm_DevCtrlOperateService;
import org.unism.gm.service.Gm_DevCtrlStsService;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.utils.SpringContextHolder;

/**
 * 表10.	控制设备状态表
 * @author Administrator
 *
 */
@Entity
@Table( name = "gm_devctrlsts" )
public class Gm_DevCtrlSts {
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String decst_id; //Id
	private Integer dect_state; //设备状态 0未知 1 开 2 关 3 停
	private Integer decst_valid; //有效性 0 无效 1 有效
	private Date decst_time; //状态变更时间
	private Integer deco_sort=3;//控制分类 0：未知，1：定时，2：自动，3：遥控，4：手动，5：异常保护（过流，缺相自动关阀），6：非法控制；7:短信控制，默认为3
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dect_id")
	private Gm_DevCtrl dect_id = new Gm_DevCtrl(); //控制设备ID

	public String getDecst_id() {
		return decst_id;
	}

	public void setDecst_id(String decst_id) {
		this.decst_id = decst_id;
	}

	public Integer getDect_state() {
		return dect_state;
	}

	public void setDect_state(Integer dect_state) {
		this.dect_state = dect_state;
	}

	public Integer getDecst_valid() {
		return decst_valid;
	}

	public void setDecst_valid(Integer decst_valid) {
		this.decst_valid = decst_valid;
	}

	public Date getDecst_time() {
		return decst_time;
	}

	public void setDecst_time(Date decst_time) {
		this.decst_time = decst_time;
	}

	public Gm_DevCtrl getDect_id() {
		return dect_id;
	}

	public void setDect_id(Gm_DevCtrl dect_id) {
		this.dect_id = dect_id;
	}

	public Integer getDeco_sort() {
		return deco_sort;
	}

	public void setDeco_sort(Integer deco_sort) {
		this.deco_sort = deco_sort;
	}
	
	//获取操作人员姓名
	public String getDeco_userName()
	{
		if(dect_id != null){
			Gm_DevCtrlOperateService gm_DevCtrlOperateService = SpringContextHolder.getBean("gm_DevCtrlOperateService");
			List<Gm_DevCtrlOperate> list = gm_DevCtrlOperateService.findAllEq("dect_id.dect_id", dect_id.getDect_id());
			if(list != null && list.size() > 0){
				for (Gm_DevCtrlOperate gm_DevCtrlOperate : list)
				{
					return gm_DevCtrlOperate.getDeco_userName();
				}
			}
			
		}
		return "";
	}
	

	
	
	
	
}
