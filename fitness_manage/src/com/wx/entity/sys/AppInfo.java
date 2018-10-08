package com.wx.entity.sys;

import com.framework.base.BaseEntity;

public class AppInfo extends BaseEntity {

	  String app_name ;
	  String app_id ;
	  String app_type ;
	  String img_fold ;
	  String group_code ;
	  String secret ;
	  String mch_id ; //商户号
	  Integer is_olt ; //是否在线交易
	  Integer is_tpl ;// 是否模板
	  String app_status ;//小程序状态
	  String privilege_code ; //特权码
	  String mch_key ;//商家秘钥

	
	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_type() {
		return app_type;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public String getImg_fold() {
		return img_fold;
	}

	public void setImg_fold(String img_fold) {
		this.img_fold = img_fold;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public Integer getIs_olt() {
		return is_olt;
	}

	public void setIs_olt(Integer is_olt) {
		this.is_olt = is_olt;
	}

	public Integer getIs_tpl() {
		return is_tpl;
	}

	public void setIs_tpl(Integer is_tpl) {
		this.is_tpl = is_tpl;
	}

	public String getApp_status() {
		return app_status;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}

	public String getPrivilege_code() {
		return privilege_code;
	}

	public void setPrivilege_code(String privilege_code) {
		this.privilege_code = privilege_code;
	}

	public String getMch_key() {
		return mch_key;
	}

	public void setMch_key(String mch_key) {
		this.mch_key = mch_key;
	}

	@Override
	public String toString() {
		return "AppInfo [app_name=" + app_name + ", app_id=" + app_id
				+ ", app_type=" + app_type + ", img_fold=" + img_fold + "]";
	}


	
	
}
