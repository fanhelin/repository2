package com.wx.entity.commodity;

import com.framework.base.BaseEntity;

public class CommodityImg extends BaseEntity {
   String code ;
   String com_code ;
   String app_info_code ;
   String img_name ;
   String img_type ;
   String title ;
   String des ;
   Integer seq_id ;
   
public String getCom_code() {
	return com_code;
}
public void setCom_code(String com_code) {
	this.com_code = com_code;
}
public String getApp_info_code() {
	return app_info_code;
}
public void setApp_info_code(String app_info_code) {
	this.app_info_code = app_info_code;
}
public String getImg_name() {
	return img_name;
}
public void setImg_name(String img_name) {
	this.img_name = img_name;
}
public String getImg_type() {
	return img_type;
}
public void setImg_type(String img_type) {
	this.img_type = img_type;
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
public Integer getSeq_id() {
	return seq_id;
}
public void setSeq_id(Integer seq_id) {
	this.seq_id = seq_id;
}


   
}
