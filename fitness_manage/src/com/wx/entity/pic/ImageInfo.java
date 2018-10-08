package com.wx.entity.pic;

import com.framework.base.BaseEntity;

public class ImageInfo extends BaseEntity {
	String img_code ;
	String img_name;
	String app_info_code ;
	String des ="";
	String exe_name ="";
	String data ;
	String title ;
	
	Integer width =0 ;
	Integer height=0 ;
	
	String path ; //外部
	Integer seq_id; 
	
	public String getImg_code() {
		return img_code;
	}
	public void setImg_code(String img_code) {
		this.img_code = img_code;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getApp_info_code() {
		return app_info_code;
	}
	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}
	
	public String getExe_name() {
		return exe_name;
	}
	public void setExe_name(String exe_name) {
		this.exe_name = exe_name;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	
	
}
