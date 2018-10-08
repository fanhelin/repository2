package com.wx.entity.sys;

import com.framework.base.BaseEntity;

public class Period extends BaseEntity {
	
	 private Integer organization_id;
	 private Integer pyear ;
	 private Integer pmonth ;
	 private String begindate ;
	 private String enddate ;
	 private Integer fi_checkout ;
	 private Integer bu_checkout ; 
	 private String ficheckoutdate ; 
	 private String bucheckoutdate ;
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public Integer getPyear() {
		return pyear;
	}
	public void setPyear(Integer pyear) {
		this.pyear = pyear;
	}
	public Integer getPmonth() {
		return pmonth;
	}
	public void setPmonth(Integer pmonth) {
		this.pmonth = pmonth;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getFi_checkout() {
		return fi_checkout;
	}
	public void setFi_checkout(Integer fi_checkout) {
		this.fi_checkout = fi_checkout;
	}
	public Integer getBu_checkout() {
		return bu_checkout;
	}
	public void setBu_checkout(Integer bu_checkout) {
		this.bu_checkout = bu_checkout;
	}
	public String getFicheckoutdate() {
		return ficheckoutdate;
	}
	public void setFicheckoutdate(String ficheckoutdate) {
		this.ficheckoutdate = ficheckoutdate;
	}
	public String getBucheckoutdate() {
		return bucheckoutdate;
	}
	public void setBucheckoutdate(String bucheckoutdate) {
		this.bucheckoutdate = bucheckoutdate;
	}
	 
	 
	 
}
