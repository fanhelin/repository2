package com.wx.entity.register;

import com.framework.base.BaseEntity;

public class Student extends BaseEntity {
    Integer state ;
    String name ;
    String license_code ;
    String idcard ;
    String addr_identity ;
    String mobile ;
    String apply_station ; //报名站点code
    String sex ;
    String birthday ;
    Integer idcard_type ;
    String idcard_img ;
    String face_img ;
    String health_img ;
    String protocol_img ;
    String post_addr ;
    Double paid_amount ;
    String classify ;
    String app_id ;
    
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getAddr_identity() {
		return addr_identity;
	}
	public void setAddr_identity(String addr_identity) {
		this.addr_identity = addr_identity;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getApply_station() {
		return apply_station;
	}
	public void setApply_station(String apply_station) {
		this.apply_station = apply_station;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getIdcard_type() {
		return idcard_type;
	}
	public void setIdcard_type(Integer idcard_type) {
		this.idcard_type = idcard_type;
	}
	public String getIdcard_img() {
		return idcard_img;
	}
	public void setIdcard_img(String idcard_img) {
		this.idcard_img = idcard_img;
	}
	public String getFace_img() {
		return face_img;
	}
	public void setFace_img(String face_img) {
		this.face_img = face_img;
	}
	public String getHealth_img() {
		return health_img;
	}
	public void setHealth_img(String health_img) {
		this.health_img = health_img;
	}
	public String getProtocol_img() {
		return protocol_img;
	}
	public void setProtocol_img(String protocol_img) {
		this.protocol_img = protocol_img;
	}
	public String getPost_addr() {
		return post_addr;
	}
	public void setPost_addr(String post_addr) {
		this.post_addr = post_addr;
	}
	public Double getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(Double paid_amount) {
		this.paid_amount = paid_amount;
	}
	public String getLicense_code() {
		return license_code;
	}
	public void setLicense_code(String license_code) {
		this.license_code = license_code;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
    
    
    
    
}
