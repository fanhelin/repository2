package com.framework.base;
/**
 * 实体基类，抽象出所有表模型中公共字段
 * @author fhr
 *
 */
public class BaseEntity {
	
 protected Integer id ;
 
 
 public Integer getId() {
	return id;
}
 
 protected String app_info_code ;
 
 protected String code ;
 
 protected String app_type ;
 
 

public String getApp_type() {
	return app_type;
}

public void setApp_type(String app_type) {
	this.app_type = app_type;
}


public void setCode_prefix(String code_prefix) {
	this.code_prefix = code_prefix;
}

public void setId(Integer id) {
	this.id = id;
}

public String getCode_prefix() {
         return code_prefix;
  }

  protected  String code_prefix ;//编号前缀


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




}
