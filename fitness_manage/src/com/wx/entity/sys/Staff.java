package com.wx.entity.sys;

import com.framework.base.BaseEntity;

/**
 * 
 * @todo 人员实体
 *
 * @author fhr
 * @dateTime 2016 2016-11-5
 */
public class Staff extends BaseEntity {
	
	{
		code_prefix = "staff_" ;
	}
	
	
	private String staff_code; 
	private String  staff_name; 
	private String  sex; 
	private Integer stature; 
	private Double weight; 
	private Integer age; 
	private String birthday; 
	private String family_address; 
	private String e_mail;
	private String phone; 
	private String office_telephone; 
	private String linkman; 
	private String linkman_telephone; 
	private String educate ;
			
	public String getStaff_code() {
		return staff_code;
	}
	public void setStaff_code(String staff_code) {
		this.staff_code = staff_code;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getStature() {
		return stature;
	}
	public void setStature(Integer stature) {
		this.stature = stature;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getFamily_address() {
		return family_address;
	}
	public void setFamily_address(String family_address) {
		this.family_address = family_address;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOffice_telephone() {
		return office_telephone;
	}
	public void setOffice_telephone(String office_telephone) {
		this.office_telephone = office_telephone;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkman_telephone() {
		return linkman_telephone;
	}
	public void setLinkman_telephone(String linkman_telephone) {
		this.linkman_telephone = linkman_telephone;
	}
	public String getEducate() {
		return educate;
	}
	public void setEducate(String educate) {
		this.educate = educate;
	}

	
}
