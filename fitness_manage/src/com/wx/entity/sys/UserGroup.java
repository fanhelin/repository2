package com.wx.entity.sys;

/**
 * 
 * @todo 用户分组关联表
 *
 * @author fhr
 * @dateTime 2016 2016-10-21
 */
public class UserGroup {
	Integer id ;
	String user_code ;
	String group_code ;
	Integer is_use ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	public Integer getIs_use() {
		return is_use;
	}
	public void setIs_use(Integer is_use) {
		this.is_use = is_use;
	}
	
	
}
