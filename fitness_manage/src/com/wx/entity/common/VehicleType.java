package com.wx.entity.common;

//卡车类型数表库表名 t_vehicle_type
public class VehicleType {

	private Integer id;
	private String vehicle_code;
	private String vehicle_name;
	private String create_date;
	private String create_user_id;
	private String last_update_date;
	private String last_update_user_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVehicle_code() {
		return vehicle_code;
	}
	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getLast_update_user_id() {
		return last_update_user_id;
	}
	public void setLast_update_user_id(String last_update_user_id) {
		this.last_update_user_id = last_update_user_id;
	}
	

	
	
	
}
