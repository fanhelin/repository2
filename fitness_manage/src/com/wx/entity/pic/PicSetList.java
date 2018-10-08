package com.wx.entity.pic;

import com.framework.base.BaseEntity;

public class PicSetList extends BaseEntity {
	
	private String set_code ;

	private String img_code ;
	
	private String img_name ;
	
	private String title ;
	
	private String des ;
	
	private String path ;
	
	private Integer width ;
	
	private Integer height ;
	
	private String exe_name ;
	
	private String data ; //base64
	
	private String price ;
	
	private String app_info_code ;
	
	Integer seq_id; 

	public String getSet_code() {
		return set_code;
	}

	public void setSet_code(String set_code) {
		this.set_code = set_code;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getExe_name() {
		return exe_name;
	}

	public void setExe_name(String exe_name) {
		this.exe_name = exe_name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getApp_info_code() {
		return app_info_code;
	}

	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	
	
	
}
