package com.wx.entity.client;


public class Client {

	  String app_info_code;
	  String openid;
	  String mobile;
	  String name;
	  String  email;
	  String address;
	  Integer sign_days;
	  Integer con_sign_days =0 ;
	  String  last_sign_day ="";
	  Integer score =0;
	  String last_share_info ;
	  String sign_image;
	  
	  
	public String getSign_image() {
		return sign_image;
	}
	public void setSign_image(String sign_image) {
		this.sign_image = sign_image;
	}
	public String getApp_info_code() {
		return app_info_code;
	}
	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getSign_days() {
		return sign_days;
	}
	public void setSign_days(Integer sign_days) {
		this.sign_days = sign_days;
	}
	public Integer getCon_sign_days() {
		return con_sign_days;
	}
	public void setCon_sign_days(Integer con_sign_days) {
		this.con_sign_days = con_sign_days;
	}
	public String getLast_sign_day() {
		return last_sign_day;
	}
	public void setLast_sign_day(String last_sign_day) {
		this.last_sign_day = last_sign_day;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getLast_share_info() {
		return last_share_info;
	}
	public void setLast_share_info(String last_share_info) {
		this.last_share_info = last_share_info;
	}
	  
	  
	  

}
