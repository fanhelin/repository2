package com.wx.entity.rcv;

import com.framework.base.BaseEntity;

public class RcvInfo extends BaseEntity 
{
	private String app_info_code ;
	private String openid ;
	private String rcv_code ;
	private String name ;
	private String mobile ;
	private String address ;
	private Integer status ;
	private Integer isDefault;
	
	
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
	public String getRcv_code() {
		return rcv_code;
	}
	public void setRcv_code(String rcv_code) {
		this.rcv_code = rcv_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
