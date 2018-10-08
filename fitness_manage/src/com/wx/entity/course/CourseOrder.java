package com.wx.entity.course;

public class CourseOrder {
	String code ;
	String nonce_str;
	String app_info_code;
	String openid;
	String course_code;
	String buy_date;
	String last_date;
	Integer sequence;
	Double price; //单位元
	Integer num;
	String course_name ;
	String chapter_code;
	public String getChapter_code() {
		return chapter_code;
	}
	public void setChapter_code(String chapter_code) {
		this.chapter_code = chapter_code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
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
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	
}
