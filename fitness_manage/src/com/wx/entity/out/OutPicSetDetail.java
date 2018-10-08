package com.wx.entity.out;

public class OutPicSetDetail {
	Integer id ;
	private String set_code ;

	private String img_code ;
	
	private String img_name ;
	
	private String title ;
	
	private String des ;
	
	private String price ;
	
	private Integer seq_id; 

	public String getSet_code() {
		return set_code;
	}

	public void setSet_code(String set_code) {
		this.set_code = set_code;
	}

	public String getImg_code() {
		return img_code;
	}

	public void setImg_code(String img_code) {
		this.img_code = img_code;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(Integer seq_id) {
		this.seq_id = seq_id;
	}
	
	
}
