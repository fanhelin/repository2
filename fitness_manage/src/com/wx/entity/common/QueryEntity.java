package com.wx.entity.common;

import com.framework.base.BaseEntity;

/**
 * 
 * @todo TODO 查询公共实体
 *
 * @author fhr
 * @dateTime 2017 2017-6-14 下午5:27:28
 */
public class QueryEntity extends BaseEntity {
 private String param_1 ;
 
 private String param_2 ;
 
 private Integer param_i1 ;
 private Integer param_i2 ;

public String getParam_1() {
	return param_1;
}
	
public void setParam_1(String param_1) {
	this.param_1 = param_1;
}

public String getParam_2() {
	return param_2;
}

public void setParam_2(String param_2) {
	this.param_2 = param_2;
}

public Integer getParam_i1() {
	return param_i1;
}

public void setParam_i1(Integer param_i1) {
	this.param_i1 = param_i1;
}

public Integer getParam_i2() {
	return param_i2;
}

public void setParam_i2(Integer param_i2) {
	this.param_i2 = param_i2;
}

 
}
