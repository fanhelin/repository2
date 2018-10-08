package com.wx.entity.common;

import com.framework.base.BaseEntity;

/**
 * 
 * @todo TODO 删除业务公共实体
 * @author fhr
 * @dateTime 2017 2017-6-14 上午9:28:54
 */
public class DelEntity extends BaseEntity {
 private String ids ;
 private Integer id ;
 private Integer idNum = 0 ;
 private Integer i_state ;
 private String billCode ;
 
 
public String getIds() {
	return ids;
}

public void setIds(String ids) {
	this.ids = ids;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getIdNum() {
	return idNum;
}

public void setIdNum(Integer idNum) {
	this.idNum = idNum;
}

public Integer getI_state() {
	return i_state;
}

public void setI_state(Integer i_state) {
	this.i_state = i_state;
}

public String getBillCode() {
	return billCode;
}

public void setBillCode(String billCode) {
	this.billCode = billCode;
}
 
 
}
