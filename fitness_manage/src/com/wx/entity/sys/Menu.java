package com.wx.entity.sys;
/**
 * 
 * @todo 菜单实体
 *
 * @author fhr
 * @dateTime 2016 2016-10-12 
 */
public class Menu {
 private Integer ID ;
 public Integer getID() {
	return ID;
}
public void setID(Integer iD) {
	ID = iD;
}

public String getMENU_NAME_CN() {
	return MENU_NAME_CN;
}
public void setMENU_NAME_CN(String mENU_NAME_CN) {
	MENU_NAME_CN = mENU_NAME_CN;
}
public String getMENU_NAME_EN() {
	return MENU_NAME_EN;
}
public void setMENU_NAME_EN(String mENU_NAME_EN) {
	MENU_NAME_EN = mENU_NAME_EN;
}
public String getFUNCA_PATH() {
	return FUNCA_PATH;
}
public void setFUNCA_PATH(String fUNCA_PATH) {
	FUNCA_PATH = fUNCA_PATH;
}
public Integer getENTITY_ID() {
	return ENTITY_ID;
}
public void setENTITY_ID(Integer eNTITY_ID) {
	ENTITY_ID = eNTITY_ID;
}
public String getPARENT_ID() {
	return PARENT_ID;
}
public void setPARENT_ID(String pARENT_ID) {
	PARENT_ID = pARENT_ID;
}
public String getICONCLS() {
	return ICONCLS;
}
public void setICONCLS(String iCONCLS) {
	ICONCLS = iCONCLS;
}
public String getSTATE() {
	return STATE;
}
public void setSTATE(String sTATE) {
	STATE = sTATE;
}
public String getIFRAM_NAME() {
	return IFRAM_NAME;
}
public void setIFRAM_NAME(String iFRAM_NAME) {
	IFRAM_NAME = iFRAM_NAME;
}
public Integer getIS_USE() {
	return IS_USE;
}
public void setIS_USE(Integer iS_USE) {
	IS_USE = iS_USE;
}

 public Integer getMENU_CODE() {
	return MENU_CODE;
}
public void setMENU_CODE(Integer mENU_CODE) {
	MENU_CODE = mENU_CODE;
}

private Integer MENU_CODE ;
 private String MENU_NAME_CN ;
 private String MENU_NAME_EN ;
 private String FUNCA_PATH ;
 private Integer ENTITY_ID ;
 private String PARENT_ID ;
 private String ICONCLS; 
 private String STATE ;
 private String IFRAM_NAME ;
 private Integer IS_USE ;
}
