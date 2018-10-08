package com.wx.entity.course;

public class Course {
	String code ; 
	String app_info_code; 
	String name; 
	String describe;
	Integer total_chapter; 
	Double price;
	String image_name ;
	String last_date;
	int sequence=1;
	int num;
	String state;
	Integer totalMinutes ; //所有章节的视频时间，用于首页统计【全部练习时间】
	Integer left_chapters ; //剩余未练习章节
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getState_str(){
		if("0".equals(this.state))
			return "下架";
		if("1".equals(this.state))
			return "上架";
		else
			return "未知状态";
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getTrucLast_date(){
		if(last_date!=null&&!"".equals(last_date.trim()))
			return last_date.split(" ")[0];
		else
			return "";
	}
	
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getApp_info_code() {
		return app_info_code;
	}
	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public Integer getTotal_chapter() {
		return total_chapter;
	}
	public void setTotal_chapter(Integer total_chapter) {
		this.total_chapter = total_chapter;
	}

	public Integer getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(Integer totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	public Integer getLeft_chapters() {
		return left_chapters;
	}

	public void setLeft_chapters(Integer left_chapters) {
		this.left_chapters = left_chapters;
	} 
	
	
}
