package com.wx.entity.pic;

import com.framework.base.BaseEntity;

public class PicSet extends BaseEntity {
	
	private String set_code ;
	
	private String app_info_code ;
	
	private String set_name ;
	
	private String des ;

	public String getSet_code() {
		return set_code;
	}

	public void setSet_code(String set_code) {
		this.set_code = set_code;
	}

	public String getApp_info_code() {
		return app_info_code;
	}

	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}

	public String getSet_name() {
		return set_name;
	}

	public void setSet_name(String set_name) {
		this.set_name = set_name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
	
}
