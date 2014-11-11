package org.unism.op.domain;

import java.util.List;

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
import org.unism.util.Cho_datetype;
import org.unism.util.Scene_gtype;
import org.unism.util.StaticDataManage;

/**
 * 采集通道应用类型操作表
 * @author Wang_Yuliang
 *
 */
@Entity
@Table( name = "op_chtypeoperate" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_ChtypeOperate {
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String cho_id; //主键
	private Integer cho_sequence; //序号
	private Integer scene_gtype; //场景类型细类
	private String ch_seat_no; //位置编号
	private String ch_depth; //通道深度
	private Integer cho_datetype; //数值类型
	private String cho_rowname; //列名称
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "chtype_id")
	private Op_ChannelType chtype_id = new Op_ChannelType(); //应用类型

	public String getCho_id() {
		return cho_id;
	}

	public void setCho_id(String cho_id) {
		this.cho_id = cho_id;
	}

	public Integer getCho_sequence() {
		return cho_sequence;
	}

	public void setCho_sequence(Integer cho_sequence) {
		this.cho_sequence = cho_sequence;
	}

	public Integer getScene_gtype() {
		return scene_gtype;
	}

	public String getScene_gtype_str() {
		if(this.scene_gtype!=null){		
			List<Scene_gtype> scene_gtypes = StaticDataManage.getScene_gtypes();
			for(Scene_gtype scene_gtype :scene_gtypes){
				if(String.valueOf(this.scene_gtype).equals(scene_gtype.getId())){
					return scene_gtype.getName();
				}
			}
		}	
		return "";
	}
	
	public void setScene_gtype(Integer scene_gtype) {
		this.scene_gtype = scene_gtype;
	}

	public String getCh_seat_no() {
		return ch_seat_no;
	}

	public void setCh_seat_no(String ch_seat_no) {
		this.ch_seat_no = ch_seat_no;
	}

	public String getCh_depth() {
		return ch_depth;
	}

	public void setCh_depth(String ch_depth) {
		this.ch_depth = ch_depth;
	}

	public Op_ChannelType getChtype_id() {
		return chtype_id;
	}

	public void setChtype_id(Op_ChannelType chtype_id) {
		this.chtype_id = chtype_id;
	}

	public Integer getCho_datetype() {
		return cho_datetype;
	}

	public String getCho_datetype_str() {
		if(this.cho_datetype!=null){
			List<Cho_datetype> cho_datetypes = StaticDataManage.getCho_datetypes();
			for(Cho_datetype cho_datetype : cho_datetypes){
				if(String.valueOf(this.cho_datetype).equals(cho_datetype.getId())){
					return cho_datetype.getName();
				}
			}
		}
		return "";
	}
	
	public void setCho_datetype(Integer cho_datetype) {
		this.cho_datetype = cho_datetype;
	}

	public String getCho_rowname() {
		return cho_rowname;
	}

	public void setCho_rowname(String cho_rowname) {
		this.cho_rowname = cho_rowname;
	}
	
	
}
