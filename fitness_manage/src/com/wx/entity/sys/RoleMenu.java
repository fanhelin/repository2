package com.wx.entity.sys;
/**
 * 
 * @todo 角色 菜单关联实体
 *
 * @author fhr
 * @dateTime 2016 2016-10-19 
 */
public class RoleMenu {
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getMENU_ID() {
		return MENU_ID;
	}
	public void setMENU_ID(Integer mENU_ID) {
		MENU_ID = mENU_ID;
	}
	public Integer getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(Integer rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public Integer getENTITY_ID() {
		return ENTITY_ID;
	}
	public void setENTITY_ID(Integer eNTITY_ID) {
		ENTITY_ID = eNTITY_ID;
	}
	public Integer getCREATE_USER_ID() {
		return CREATE_USER_ID;
	}
	public void setCREATE_USER_ID(Integer cREATE_USER_ID) {
		CREATE_USER_ID = cREATE_USER_ID;
	}
	public Integer getLAST_UPDATE_USER_ID() {
		return LAST_UPDATE_USER_ID;
	}
	public void setLAST_UPDATE_USER_ID(Integer lAST_UPDATE_USER_ID) {
		LAST_UPDATE_USER_ID = lAST_UPDATE_USER_ID;
	}
	public String getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public String getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}
	public void setLAST_UPDATE_DATE(String lAST_UPDATE_DATE) {
		LAST_UPDATE_DATE = lAST_UPDATE_DATE;
	}
	private Integer ID ;
	private Integer MENU_ID ;
	private Integer ROLE_ID ;
	private Integer ENTITY_ID ;
	private Integer CREATE_USER_ID ;
	private Integer LAST_UPDATE_USER_ID ;
	private String CREATE_DATE ;
	private String LAST_UPDATE_DATE ;
	
	@Override
	public String toString() {
		return "MenuRole [ID=" + ID + ", MENU_ID=" + MENU_ID + ", ROLE_ID="
				+ ROLE_ID + ", ENTITY_ID=" + ENTITY_ID + ", CREATE_USER_ID="
				+ CREATE_USER_ID + ", LAST_UPDATE_USER_ID="
				+ LAST_UPDATE_USER_ID + ", CREATE_DATE=" + CREATE_DATE
				+ ", LAST_UPDATE_DATE=" + LAST_UPDATE_DATE + "]";
	}
	
}
