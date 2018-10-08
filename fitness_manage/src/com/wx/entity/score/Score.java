package com.wx.entity.score;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Score {
	private String app_info_code ;
	private String code;
	private String openid;
	private String com_code;
	private String state;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date order_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date handle_date;
	public String getState() {
		return state;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public Date getHandle_date() {
		return handle_date;
	}
	private Integer score;
	private Integer leftScore;
	private String mobile;
	private String address;
	private String rcv_name;
	
	private String com_name;
	private String com_title;
	private String com_des ;
	private String fu_img ;
	private Double price ;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCom_code() {
		return com_code;
	}
	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}
	public String getStateStr() {
		if("1".equals(this.state))
			return "已处理";
		else if("0".equals(this.state))
			return "未处理";
		else return "未知状态";
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrder_date_str() {
		if(this.order_date!=null)
			return order_date.toLocaleString();
		else
			return null;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getHandle_date_str() {
		if(this.handle_date!=null)
			return handle_date.toLocaleString();
		else
			return null;
	}
	public void setHandle_date(Date handle_date) {
		this.handle_date = handle_date;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRcv_name() {
		return rcv_name;
	}
	public void setRcv_name(String rcv_name) {
		this.rcv_name = rcv_name;
	}
	
	public String getApp_info_code() {
		return app_info_code;
	}
	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_title() {
		return com_title;
	}
	public void setCom_title(String com_title) {
		this.com_title = com_title;
	}
	public String getCom_des() {
		return com_des;
	}
	public void setCom_des(String com_des) {
		this.com_des = com_des;
	}
	public String getFu_img() {
		return fu_img;
	}
	public void setFu_img(String fu_img) {
		this.fu_img = fu_img;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getLeftScore() {
		return leftScore;
	}
	public void setLeftScore(Integer leftScore) {
		this.leftScore = leftScore;
	}
	
	
	
}
