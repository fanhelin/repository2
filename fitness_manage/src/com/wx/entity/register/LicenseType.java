package com.wx.entity.register;

import com.framework.base.BaseEntity;

public class LicenseType extends BaseEntity {

	String license_type ;
	double price ;
	

	public String getLicense_type() {
		return license_type;
	}
	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
