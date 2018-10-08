package com.wx.entity.sys;

import com.framework.base.BaseEntity;
/**
 * 
 * @todo 组织架构实体
 *
 * @author fhr
 * @dateTime 2016 2016-10-13 
 */
public class Organization extends BaseEntity {
	{
		code_prefix = "org" ;
	}

  private String organization_code ;
  private String organization_name ;
  private String parent_id ;
  private String remark ;
  private String organization_type ;
  private String address ;
  private String phone ;
  private String fax;
  private Integer account ;
  private Integer staff_id ;
  
  private String parentName;
  
  public String getParentName() {
	return parentName;
}
  
public void setParentName(String parentName) {
	this.parentName = parentName;
}

public Integer getAccount() {
	return account;
}

public void setAccount(Integer account) {
	this.account = account;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getFax() {
	return fax;
}

public void setFax(String fax) {
	this.fax = fax;
}

  
public String getOrganization_code() {
	return organization_code;
}

public void setOrganization_code(String organization_code) {
	this.organization_code = organization_code;
}

public String getOrganization_name() {
	return organization_name;
}
public void setOrganization_name(String organization_name) {
	this.organization_name = organization_name;
}

public String getParent_id() {
	return parent_id;
}

public void setParent_id(String parent_id) {
	this.parent_id = parent_id;
}

public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public String getOrganization_type() {
	return organization_type;
}

public void setOrganization_type(String organization_type) {
	this.organization_type = organization_type;
}

public Integer getStaff_id() {
	return staff_id;
}

public void setStaff_id(Integer staff_id) {
	this.staff_id = staff_id;
}



}
