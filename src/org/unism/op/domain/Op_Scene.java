package org.unism.op.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.unism.op.service.Op_SceneService;
import org.unism.util.Scene_ctype;
import org.unism.util.Scene_gtype;
import org.unism.util.Scene_type;
import org.unism.util.StaticDataManage;
import org.wangzz.core.utils.SpringContextHolder;
/**
 * 场景信息表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_scene" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_Scene implements Serializable {
	
	private static final long serialVersionUID = -5686349206195919301L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String scene_id;//场景信息ID
	private String scene_no;//场景编号 唯一
	private String scene_addr;//场景所在地
	
	@Column(updatable = false)
	private java.util.Date scene_createDate;//场景添加时间
	private String scene_creater;//创建者
	private String scene_desc;//场景的说明
	private String scene_keyWord;//关键字
	private String scene_name;//场景名称
	private String scene_pid;//所属场景父ID(父节点)
	private Integer scene_type;//场景类型 1.设施园艺 2.水产养殖 3.大田种植 4.畜禽养殖
	private Integer scene_ctype;//场景类型子类 11： 设施蔬菜 12:  设施花卉 21：池塘水产养殖 22：设施水产养殖
	private Integer scene_gtype;//1: 养殖池塘 2：池塘组 3：基地 4：企业 5：项目
	private Integer scene_rank;//所属等级 0是最低级
	private String scene_image;//场景的图片
	private String scene_url;//定制路径
	private Integer scene_state;//场景使用状态
	private String scene_longitude;//经度
	private String scene_latitude;//纬度
	private String scene_videoUrl;//视频url
	private Integer scene_order;//排序号
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Op_Areas area_id=new Op_Areas();//区划ID 关联行政区划表

	public String getScene_pname(){
		Op_SceneService op_SceneService = SpringContextHolder.getBean("op_SceneService");
		if(this.scene_pid == null){
			return "root";
		}else if(this.scene_pid.equals("FF")){
			return "FF";			
		}else{
			return op_SceneService.findScene_pname(this.scene_pid);
		}
	}	

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getScene_addr() {
		return scene_addr;
	}

	public void setScene_addr(String scene_addr) {
		this.scene_addr = scene_addr;
	}

	public java.util.Date getScene_createDate() {
		return scene_createDate;
	}

	public void setScene_createDate(java.util.Date scene_createDate) {
		this.scene_createDate = scene_createDate;
	}

	public String getScene_creater() {
		return scene_creater;
	}

	public void setScene_creater(String scene_creater) {
		this.scene_creater = scene_creater;
	}

	public String getScene_desc() {
		return scene_desc;
	}

	public void setScene_desc(String scene_desc) {
		this.scene_desc = scene_desc;
	}

	public String getScene_keyWord() {
		return scene_keyWord;
	}

	public void setScene_keyWord(String scene_keyWord) {
		this.scene_keyWord = scene_keyWord;
	}

	public String getScene_name() {
		return scene_name;
	}

	public void setScene_name(String scene_name) {
		this.scene_name = scene_name;
	}

	public String getScene_pid() {
		return scene_pid;
	}

	public void setScene_pid(String scene_pid) {
		this.scene_pid = scene_pid;
	}

	public Integer getScene_type() {
		return scene_type;
	}
	
	public String getScene_type_str() {
		if(this.scene_type!=null){
			List<Scene_type> scene_types = StaticDataManage.getScene_types();
			for(Scene_type scene_type : scene_types){
				if(String.valueOf(this.scene_type).equals(scene_type.getId())){
					return scene_type.getName();
				}				
			}
		}
		return "";
	}

	public void setScene_type(Integer scene_type) {
		this.scene_type = scene_type;
	}

	public Integer getScene_ctype() {
		return scene_ctype;
	}

	public String getScene_ctype_str() {
		if(this.scene_ctype!=null){
			List<Scene_ctype> scene_ctypes = StaticDataManage.getScene_ctypes();
			for(Scene_ctype scene_ctype : scene_ctypes){
				if(String.valueOf(this.scene_ctype).equals(scene_ctype.getId())){
					return scene_ctype.getName();
				}				
			}
		}
		return "";
	}
	
	public void setScene_ctype(Integer scene_ctype) {
		this.scene_ctype = scene_ctype;
	}

	public Integer getScene_rank() {
		return scene_rank;
	}

	public void setScene_rank(Integer scene_rank) {
		this.scene_rank = scene_rank;
	}

	public String getScene_image() {
		return scene_image;
	}

	public void setScene_image(String scene_image) {
		this.scene_image = scene_image;
	}

	public String getScene_url() {
		return scene_url;
	}

	public void setScene_url(String scene_url) {
		this.scene_url = scene_url;
	}

	public Op_Areas getOpAreas() {
		return area_id;
	}

	public void setOpAreas(Op_Areas opAreas) {
		this.area_id = opAreas;
	}
	
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{scene_id:").append(scene_id)
				.append(",scene_addr:\"").append(scene_addr).append("\"")
				.append(",scene_createDate:\"").append(scene_createDate).append("\"")
				.append(",scene_creater:\"").append(scene_creater).append("\"")
				.append(",scene_desc:\"").append(scene_desc).append("\"")
				.append(",scene_keyWord:\"").append(scene_keyWord).append("\"")
				.append(",scene_name:\"").append(scene_name).append("\"")
				.append(",scene_pid:\"").append(scene_pid).append("\"")
				.append(",scene_type:\"").append(scene_type).append("\"")
				.append(",scene_ctype:\"").append(scene_ctype).append("\"")
				.append(",scene_rank:\"").append(scene_rank).append("\"")
				.append(",scene_image:\"").append(scene_image).append("\"")
				.append(",scene_url:\"").append(scene_url).append("\"")				
				.append(",area_id:\"").append(area_id).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}

	public Op_Areas getArea_id() {
		return area_id;
	}

	public void setArea_id(Op_Areas area_id) {
		this.area_id = area_id;
	}

	public String getScene_no() {
		return scene_no;
	}

	public void setScene_no(String scene_no) {
		this.scene_no = scene_no;
	}
	public Integer getScene_state() {
		return scene_state;
	}
	public void setScene_state(Integer scene_state) {
		this.scene_state = scene_state;
	}
	public Integer getScene_gtype() {
		return scene_gtype;
	}
	
	public String getScene_gtype_str() {
		if(this.scene_gtype!=null){
			List<Scene_gtype> scene_gtypes = StaticDataManage.getScene_gtypes();
			for(Scene_gtype scene_gtype : scene_gtypes){
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
	public String getScene_longitude()
	{
		return scene_longitude;
	}
	public void setScene_longitude(String scene_longitude)
	{
		this.scene_longitude = scene_longitude;
	}
	public String getScene_latitude()
	{
		return scene_latitude;
	}
	public void setScene_latitude(String scene_latitude)
	{
		this.scene_latitude = scene_latitude;
	}
	public String getScene_videoUrl()
	{
		return scene_videoUrl;
	}
	public void setScene_videoUrl(String scene_videoUrl)
	{
		this.scene_videoUrl = scene_videoUrl;
	}
	public Integer getScene_order() {
		return scene_order;
	}
	public void setScene_order(Integer sceneOrder) {
		scene_order = sceneOrder;
	}

}
