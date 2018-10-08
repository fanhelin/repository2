package com.wx.entity.common;

import com.framework.base.BaseEntity;

/**
 * 
 * @todo 车辆类型实体
 *
 * @author fhr
 * @dateTime 2016 2016-10-12 上午11:49:51
 */

public class CarClass extends BaseEntity {
   
	public String getClass_code() {
		return class_code;
	}
	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getOther_cartype() {
		return other_cartype;
	}
	public void setOther_cartype(String other_cartype) {
		this.other_cartype = other_cartype;
	}
	private String class_code;
	private String class_name; 
	private String car_type; 
	private String other_cartype;
	
}
