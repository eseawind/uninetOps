package org.unism.op.domain;

import java.io.Serializable;

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
/**
 * 用户信息表
 * @author liucl
 *
 */

@Entity
@Table( name = "op_userinfo" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_UserInfo implements Serializable {
	
	private static final long serialVersionUID = -8983680746124667647L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String user_id;//用户信息表ID
	private String user_address;//地址
	private String user_company;//公司名称
	private String user_email;//邮箱
	private String user_loginName;//登录ID
	private String user_loginPwd;//登录密码
	private String user_mobie;//手机
	private String user_notes;//部门
	private String user_phone;//电话
	private String user_post;//邮编
	private Integer user_sex;//性别
	private String user_name;//真实姓名
	private Integer user_enable;//是否禁用
	private String user_creater;//创建人
	private Double user_longitude;//经度
	private Double user_latitude;//纬度
	
	public Double getUser_longitude() {
		return user_longitude;
	}

	public void setUser_longitude(Double user_longitude) {
		this.user_longitude = user_longitude;
	}

	public Double getUser_latitude() {
		return user_latitude;
	}

	public void setUser_latitude(Double user_latitude) {
		this.user_latitude = user_latitude;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Op_RoleInfo role_id =new Op_RoleInfo();//用户角色 关联角色信息表

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_company() {
		return user_company;
	}

	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_loginName() {
		return user_loginName;
	}

	public void setUser_loginName(String user_loginName) {
		this.user_loginName = user_loginName;
	}

	public String getUser_loginPwd() {
		return user_loginPwd;
	}

	public void setUser_loginPwd(String user_loginPwd) {
		this.user_loginPwd = user_loginPwd;
	}

	public String getUser_mobie() {
		return user_mobie;
	}

	public void setUser_mobie(String user_mobie) {
		this.user_mobie = user_mobie;
	}

	public String getUser_notes() {
		return user_notes;
	}

	public void setUser_notes(String user_notes) {
		this.user_notes = user_notes;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_post() {
		return user_post;
	}

	public void setUser_post(String user_post) {
		this.user_post = user_post;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Op_RoleInfo getRole_id() {
		return role_id;
	}

	public void setRole_id(Op_RoleInfo role_id) {
		this.role_id = role_id;
	}

	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{user_id:").append(user_id)
				.append(",user_address:\"").append(user_address).append("\"")
				.append(",user_company:\"").append(user_company).append("\"")
				.append(",user_email:\"").append(user_email).append("\"")
				.append(",user_loginName:\"").append(user_loginName).append("\"")
				.append(",user_loginPwd:\"").append(user_loginPwd).append("\"")
				.append(",user_mobie:\"").append(user_mobie).append("\"")
				.append(",user_notes:\"").append(user_notes).append("\"")
				.append(",user_phone:\"").append(user_phone).append("\"")
				.append(",user_post:\"").append(user_post).append("\"")
				.append(",user_sex:\"").append(user_sex).append("\"")
				.append(",user_name:\"").append(user_name).append("\"")
				.append(",role_id:\"").append(role_id).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}

	public Integer getUser_enable() {
		return user_enable;
	}

	public void setUser_enable(Integer user_enable) {
		this.user_enable = user_enable;
	}

	public String getUser_creater() {
		return user_creater;
	}

	public void setUser_creater(String user_creater) {
		this.user_creater = user_creater;
	}	
}
