package com.wx.entity.chapter;

public class Chapter {
	String code ;
	String course_code;
	Integer sequence=1;
	String name;
	Integer minutes;
	String describe;
	String image_name;
	String vido_name;
	Integer vido_size;
	Integer num=0;


	
	public Integer getPercent(){
		if(num!=null&&num>0&&sequence!=null&&sequence>0){
			return (int)(((float)(sequence-1))/((float)num)*100);
		}
		else
			return 0;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getVido_name() {
		return vido_name;
	}
	public void setVido_name(String vido_name) {
		this.vido_name = vido_name;
	}
	public Integer getVido_size() {
		return vido_size;
	}
	public void setVido_size(Integer vido_size) {
		this.vido_size = vido_size;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	
	
}
