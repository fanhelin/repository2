package com.wx.entity.common;

import com.framework.base.BaseEntity;

public class UseCharacter extends BaseEntity {
   
	private Integer id;
	private String character_code;
	private String character_name;
	private String create_date;
	private Integer create_user_id;
	private String last_update_date;
	private Integer last_update_user_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCharacter_code() {
		return character_code;
	}
	public void setCharacter_code(String character_code) {
		this.character_code = character_code;
	}
	public String getCharacter_name() {
		return character_name;
	}
	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public Integer getLast_update_user_id() {
		return last_update_user_id;
	}
	public void setLast_update_user_id(Integer last_update_user_id) {
		this.last_update_user_id = last_update_user_id;
	}
}
