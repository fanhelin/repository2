package com.wx.entity.common;

import java.io.Serializable;

import com.framework.base.BaseEntity;
/**
 * 
 * @todo 捆绑单位实体
 *
 * @author fhr
 * @dateTime 2016 2016-11-9 下午5:51:32
 */
public class PackageUnit extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1712039748186385288L;
	private String package_code;
	private String package_name;
	private String remark ;
	
	public String getPackage_code() {
		return package_code;
	}
	
	public void setPackage_code(String package_code) {
		this.package_code = package_code;
	}
	
	public String getPackage_name() {
		return package_name;
	}
	
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
