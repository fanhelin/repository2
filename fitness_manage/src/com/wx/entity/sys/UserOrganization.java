package com.wx.entity.sys;
/**
 * 
 * @todo 用户组织架构实体
 *
 * @author fhr
 * @dateTime 2016 2016-10-20 
 */

public class UserOrganization {
private Integer ID ;
public Integer getID() {
	return ID;
}
public void setID(Integer iD) {
	ID = iD;
}
public Integer getORGANIZATION_ID() {
	return ORGANIZATION_ID;
}
public void setORGANIZATION_ID(Integer oRGANIZATION_ID) {
	ORGANIZATION_ID = oRGANIZATION_ID;
}
public Integer getUSER_ID() {
	return USER_ID;
}
public void setUSER_ID(Integer uSER_ID) {
	USER_ID = uSER_ID;
}
public Integer getENTITY_ID() {
	return ENTITY_ID;
}
public void setENTITY_ID(Integer eNTITY_ID) {
	ENTITY_ID = eNTITY_ID;
}
public String getREMARK() {
	return REMARK;
}
public void setREMARK(String rEMARK) {
	REMARK = rEMARK;
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
private Integer ORGANIZATION_ID;
private Integer USER_ID;
private Integer ENTITY_ID; 
private String REMARK; 
private String CREATE_DATE; 
private Integer CREATE_USER_ID; 
private String LAST_UPDATE_DATE;
private Integer LAST_UPDATE_USER_ID ;
private Integer MIAN_ORG ;
private String ORGANIZATION_NAME ;


public String getORGANIZATION_NAME() {
	return ORGANIZATION_NAME;
}
public void setORGANIZATION_NAME(String oRGANIZATION_NAME) {
	ORGANIZATION_NAME = oRGANIZATION_NAME;
}
public Integer getMIAN_ORG() {
	return MIAN_ORG;
}
public void setMIAN_ORG(Integer mIAN_ORG) {
	MIAN_ORG = mIAN_ORG;
}
	
}
