package com.wx.entity.sys;
/**
 * 
 * @todo 关联组织架构实体
 *
 * @author fhr
 * @dateTime 2017 2017-4-20 下午2:49:22
 */
public class RelaOrganiztion extends Organization {
	
	private Integer relation_station_id ;
	private Integer relation_type ;
	private String staff_name ;

	
	
	public Integer getRelation_station_id() {
		return relation_station_id;
	}
	public void setRelation_station_id(Integer relation_station_id) {
		this.relation_station_id = relation_station_id;
	}
	public Integer getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(Integer relation_type) {
		this.relation_type = relation_type;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	
}
