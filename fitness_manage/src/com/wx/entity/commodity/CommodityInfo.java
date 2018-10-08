package com.wx.entity.commodity;

import org.springframework.web.multipart.MultipartFile;

import com.framework.base.BaseEntity;

public class CommodityInfo extends BaseEntity {
	
	private String app_info_code ;
	private String code ;
	private String name ;
	private Double price ;
	private Double r_price ;
	private String unit ;
	private Integer sum ;
	private String des ;
	private String type_code ;
	private String sub_type ;
	private String remark ;
	private String main_pic;
	private String title ;
	private String status ;
	private Integer score;
	private MultipartFile main_img_file;
	private MultipartFile fu_img_file;
	private String main_img;
	private String fu_img;
	private Integer reduceSum ;
	
	public String getStatus_str(){
		if("1".equals(this.status))
			return "上架";
		if("0".equals(this.status))
			return "下架";
		if("2".equals(this.status))
			return "删除";
		else
			return "未知";
	}
	
	public String getMain_img() {
			return main_img;
	}
	public void setMain_img(String main_img) {
		this.main_img = main_img;
	}
	public String getFu_img() {
		return fu_img;
	}
	public void setFu_img(String fu_img) {
		this.fu_img = fu_img;
	}
	public MultipartFile getMain_img_file() {
		return main_img_file;
	}
	public void setMain_img_file(MultipartFile main_img_file) {
		this.main_img_file = main_img_file;
	}
	public MultipartFile getFu_img_file() {
		return fu_img_file;
	}
	public void setFu_img_file(MultipartFile fu_img_file) {
		this.fu_img_file = fu_img_file;
	}
	public String getApp_info_code() {
		return app_info_code;
	}
	public void setApp_info_code(String app_info_code) {
		this.app_info_code = app_info_code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getR_price() {
		return r_price;
	}
	public void setR_price(Double r_price) {
		this.r_price = r_price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getSub_type() {
		return sub_type;
	}
	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMain_pic() {
		return main_pic;
	}
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getReduceSum() {
		return reduceSum;
	}

	public void setReduceSum(Integer reduceSum) {
		this.reduceSum = reduceSum;
	}

	
	
	
}
