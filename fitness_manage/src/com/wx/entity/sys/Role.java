package com.wx.entity.sys;

/**
 * 
 * @todo 角色实体
 *
 * @author fhr
 * @dateTime 2016 2016-10-22 
 */
public class Role {
	
	@Override
	public String toString() {
		return "Role [ID=" + ID + ", ROLE_CODE=" + ROLE_CODE + ", ROLE_NAME="
				+ ROLE_NAME + ", ENTITY_ID=" + ENTITY_ID + ", CREATE_DATE="
				+ CREATE_DATE + ", CREATE_USER_ID=" + CREATE_USER_ID
				+ ", LAST_UPDATE_DATE=" + LAST_UPDATE_DATE
				+ ", LAST_UPDATE_USER_ID=" + LAST_UPDATE_USER_ID + ", IS_USE="
				+ IS_USE + ", REMARK=" + REMARK + "]";
	}
	public Integer getID() {
		return ID;
	}
	
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getROLE_CODE() {
		return ROLE_CODE;
	}
	public void setROLE_CODE(String rOLE_CODE) {
		ROLE_CODE = rOLE_CODE;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public Integer getENTITY_ID() {
		return ENTITY_ID;
	}
	public void setENTITY_ID(Integer eNTITY_ID) {
		ENTITY_ID = eNTITY_ID;
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
	public Integer getIS_USE() {
		return IS_USE;
	}
	public void setIS_USE(Integer iS_USE) {
		IS_USE = iS_USE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	private Integer ID ;                  
	private String ROLE_CODE	;          
	private String ROLE_NAME ;	          
	private Integer ENTITY_ID ;         
	private String CREATE_DATE  ;         
	private Integer CREATE_USER_ID	 ;     
	private String LAST_UPDATE_DATE	 ; 
	private Integer LAST_UPDATE_USER_ID	;  
	private Integer IS_USE	;              
	private String REMARK	;
	private String LAST_UPDATE_USER ;
	public String getLAST_UPDATE_USER() {
		return LAST_UPDATE_USER;
	}
	public void setLAST_UPDATE_USER(String lAST_UPDATE_USER) {
		LAST_UPDATE_USER = lAST_UPDATE_USER;
	}
	public String getCREATE_USER() {
		return CREATE_USER;
	}
	public void setCREATE_USER(String cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}
	private String CREATE_USER ;
	
	public String getCREATE_USER_NAME() {
		return CREATE_USER_NAME;
	}
	public void setCREATE_USER_NAME(String cREATE_USER_NAME) {
		CREATE_USER_NAME = cREATE_USER_NAME;
	}
	public String getLAST_UPDATE_USER_NAME() {
		return LAST_UPDATE_USER_NAME;
	}
	public void setLAST_UPDATE_USER_NAME(String lAST_UPDATE_USER_NAME) {
		LAST_UPDATE_USER_NAME = lAST_UPDATE_USER_NAME;
	}
	private String CREATE_USER_NAME ;
	private String LAST_UPDATE_USER_NAME ;
	
}
