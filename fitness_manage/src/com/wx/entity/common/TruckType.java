package com.wx.entity.common;

//卡车类型数表库表名 t_truck_type
public class TruckType {
	
	private Integer ID;
	private String TRUCK_CODE;
	private String TRUCK_NAME;
	private String BEARING;
	private Integer TONNAGE;
	private String CREATE_DATE;
	private Integer CREATE_USER_ID;
	private String LAST_UPDATE_DATE;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getTRUCK_CODE() {
		return TRUCK_CODE;
	}
	public void setTRUCK_CODE(String tRUCK_CODE) {
		TRUCK_CODE = tRUCK_CODE;
	}
	public String getTRUCK_NAME() {
		return TRUCK_NAME;
	}
	public void setTRUCK_NAME(String tRUCK_NAME) {
		TRUCK_NAME = tRUCK_NAME;
	}
	public String getBEARING() {
		return BEARING;
	}
	public void setBEARING(String bEARING) {
		BEARING = bEARING;
	}
	public Integer getTONNAGE() {
		return TONNAGE;
	}
	public void setTONNAGE(Integer tONNAGE) {
		TONNAGE = tONNAGE;
	}
	public String getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public Integer getCREATE_USER_ID() {
		return CREATE_USER_ID;
	}
	public void setCREATE_USER_ID(Integer cREATE_USER_ID) {
		CREATE_USER_ID = cREATE_USER_ID;
	}
	public String getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}
	public void setLAST_UPDATE_DATE(String lAST_UPDATE_DATE) {
		LAST_UPDATE_DATE = lAST_UPDATE_DATE;
	}
	public Integer getLAST_UPDATE_USER_ID() {
		return LAST_UPDATE_USER_ID;
	}
	public void setLAST_UPDATE_USER_ID(Integer lAST_UPDATE_USER_ID) {
		LAST_UPDATE_USER_ID = lAST_UPDATE_USER_ID;
	}
	private Integer LAST_UPDATE_USER_ID;
}
