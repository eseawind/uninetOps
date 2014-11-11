package org.unism.op.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
/**
 * 供应商信息表
 * @author liucl
 *
 */
@Entity
@Table( name = "op_supplier" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Op_Supplier implements Serializable {
	
	private static final long serialVersionUID = 119306208372527180L;
	
	@Id
	@GenericGenerator(name = "uu_id", strategy = "uuid")
	@GeneratedValue(generator = "uu_id")
	private String sup_id;//供应商信息表ID
	private String sup_no;//供应商编号 唯一
	private String sup_name;//供应商名称
	private Integer sup_type;//供应商类型 0 销售商 1 供应商
	private String sup_country;//供应商国家
	private String sup_address;//供应商地址
	private Integer sup_zip;//供应商邮编
	private String sup_phone;//供应商固定电话
	private String sup_fax;//供应商传真
	private String sup_contactName;//联系人姓名
	private String sup_contactPhone;//联系人电话
	private String sup_contactMobile;//联系人手机
	private String sup_contactEmail;//联系人邮箱
	
	public String getSup_id() {
		return sup_id;
	}
	public void setSup_id(String sup_id) {
		this.sup_id = sup_id;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public String getSup_address() {
		return sup_address;
	}
	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}
	public Integer getSup_zip() {
		return sup_zip;
	}
	public void setSup_zip(Integer sup_zip) {
		this.sup_zip = sup_zip;
	}
	public String getSup_phone() {
		return sup_phone;
	}
	public void setSup_phone(String sup_phone) {
		this.sup_phone = sup_phone;
	}
	public String getSup_fax() {
		return sup_fax;
	}
	public void setSup_fax(String sup_fax) {
		this.sup_fax = sup_fax;
	}
	public String getSup_contactName() {
		return sup_contactName;
	}
	public void setSup_contactName(String sup_contactName) {
		this.sup_contactName = sup_contactName;
	}
	public String getSup_contactPhone() {
		return sup_contactPhone;
	}
	public void setSup_contactPhone(String sup_contactPhone) {
		this.sup_contactPhone = sup_contactPhone;
	}
	public String getSup_contactMobile() {
		return sup_contactMobile;
	}
	public void setSup_contactMobile(String sup_contactMobile) {
		this.sup_contactMobile = sup_contactMobile;
	}
	public String getSup_contactEmail() {
		return sup_contactEmail;
	}
	public void setSup_contactEmail(String sup_contactEmail) {
		this.sup_contactEmail = sup_contactEmail;
	}	
	public String getSup_no() {
		return sup_no;
	}
	public void setSup_no(String sup_no) {
		this.sup_no = sup_no;
	}
	public Integer getSup_type() {
		return sup_type;
	}
	public void setSup_type(Integer sup_type) {
		this.sup_type = sup_type;
	}
	public String getSup_country() {
		return sup_country;
	}
	public void setSup_country(String sup_country) {
		this.sup_country = sup_country;
	}
	
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{sup_id:").append(sup_id)
				.append(",sup_name:\"").append(sup_name).append("\"")
				.append(",sup_type:\"").append(sup_type).append("\"")
				.append(",sup_country:\"").append(sup_country).append("\"")
				.append(",sup_address:\"").append(sup_address).append("\"")
				.append(",sup_zip:\"").append(sup_zip).append("\"")
				.append(",sup_phone:\"").append(sup_phone).append("\"")
				.append(",sup_fax:\"").append(sup_fax).append("\"")
				.append(",sup_contactName:\"").append(sup_contactName).append("\"")
				.append(",sup_contactPhone:\"").append(sup_contactPhone).append("\"")
				.append(",sup_contactMobile:\"").append(sup_contactMobile).append("\"")
				.append(",sup_contactEmail:\"").append(sup_contactEmail).append("\"");				
				builder.append("},");				
		return builder.toString();		
	}
}
