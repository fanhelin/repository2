package com.wx.entity.common;

import java.io.Serializable;

import com.framework.base.BaseEntity;
/**
 * 
 * @todo 货物类型实体
 *
 * @author fhr
 * @dateTime 2016 2016-11-10 上午9:46:07
 */
public class CargoType extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8952322681004898922L;
	private String cargo_type_code ;
	private String cargo_type_name;
	private String remark ;
	public String getCargo_type_code() {
		return cargo_type_code;
	}
	public void setCargo_type_code(String cargo_type_code) {
		this.cargo_type_code = cargo_type_code;
	}
	public String getCargo_type_name() {
		return cargo_type_name;
	}
	public void setCargo_type_name(String cargo_type_name) {
		this.cargo_type_name = cargo_type_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

