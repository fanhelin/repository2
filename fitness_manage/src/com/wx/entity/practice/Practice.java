package com.wx.entity.practice;

public class Practice {
	String app_info_code ;
	String code ;
	String openid ;
	String course ;
	String chapter ;
	Integer minute ;
	String datetime ;
	String course_name;
	String chapter_name;
	String chapter_imge;
	String course_code;
	String chapter_code;
	
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public String getChapter_code() {
		return chapter_code;
	}
	public void setChapter_code(String chapter_code) {
		this.chapter_code = chapter_code;
	}
	public String getChapter_imge() {
		return chapter_imge;
	}
	public void setChapter_imge(String chapter_imge) {
		this.chapter_imge = chapter_imge;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public String getCode() {
		return code;
	}
	public String getTrucDate(){
		if(this.datetime!=null&&!"".equals(this.datetime.trim()))
			return this.datetime.split(" ")[0];
		else
			return "";
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getApp_info_code() {
		return app_info_code;
	}
	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}
	
	
	
}
