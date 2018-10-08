package com.wx.entity.common;

import java.io.Serializable;

public class Area implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1242332522609504520L;
	private Integer id; 
	private String name; 
	private String short_name; 
	private String level_type; 
	private String city_code; 
	private String zip_code; 
	private String merger_name; 
	private Float lng; 
	private Float lat; 
	private String pinyin;
	private String first_char; 
	private String short_hand; 
	private String parent_id; 
	private Integer is_use ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getLevel_type() {
		return level_type;
	}
	public void setLevel_type(String level_type) {
		this.level_type = level_type;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getMerger_name() {
		return merger_name;
	}
	public void setMerger_name(String merger_name) {
		this.merger_name = merger_name;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getFirst_char() {
		return first_char;
	}
	public void setFirst_char(String first_char) {
		this.first_char = first_char;
	}
	public String getShort_hand() {
		return short_hand;
	}
	public void setShort_hand(String short_hand) {
		this.short_hand = short_hand;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getIs_use() {
		return is_use;
	}
	public void setIs_use(Integer is_use) {
		this.is_use = is_use;
	}
	
	
	
}
