package com.wx.entity.commodity;

import com.framework.base.BaseEntity;

public class CommodityType extends BaseEntity {
   String app_info_code ;
   String type_code ;
   String name ;
   String des ;
   
public String getApp_info_code() {
	return app_info_code;
}
public void setApp_info_code(String app_info_code) {
	this.app_info_code = app_info_code;
}
public String getType_code() {
	return type_code;
}
public void setType_code(String type_code) {
	this.type_code = type_code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
   
   
}
