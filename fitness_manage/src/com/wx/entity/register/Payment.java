package com.wx.entity.register;

import com.framework.base.BaseEntity;

public class Payment extends BaseEntity {
   String student_code ;
   Double amount ;
   String pay_time ;
   String name ;
   String nonceStr;
   String prepay_id ;
   
   

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStudent_code() {
	return student_code;
}
public void setStudent_code(String student_code) {
	this.student_code = student_code;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getPay_time() {
	return pay_time;
}
public void setPay_time(String pay_time) {
	this.pay_time = pay_time;
}
public String getNonceStr() {
	return nonceStr;
}
public void setNonceStr(String nonceStr) {
	this.nonceStr = nonceStr;
}
public String getPrepay_id() {
	return prepay_id;
}
public void setPrepay_id(String prepay_id) {
	this.prepay_id = prepay_id;
}
   
   
}
