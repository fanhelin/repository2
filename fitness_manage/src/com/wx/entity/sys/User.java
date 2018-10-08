package com.wx.entity.sys;



import com.framework.base.BaseEntity;


/**
 * 
 * @todo 用户实体
 *
 * @author fhr
 * @dateTime 2016 2016-11-7 
 */
public class User extends BaseEntity{
	{
		code_prefix = "user_" ;
	}

	String user_name;
	String user_code ;
	String role_code;

	String address ;
	String phone ;
	String password ;
	
	Integer bus_state ;

	Integer login_state ;
	
	String right_mask ;
	
	String is_admin ;

	
	//List<UserGroup> uerGroupList = new ArrayList<UserGroup>();

	
	public String getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}


	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBus_state() {
		return bus_state;
	}
	public void setBus_state(Integer bus_state) {
		this.bus_state = bus_state;
	}
	
	public Integer getLogin_state() {
		return login_state;
	}
	public void setLogin_state(Integer login_state) {
		this.login_state = login_state;
	}
	
	public String getRight_mask() {
		return right_mask;
	}
	
	public void setRight_mask(String right_mask) {
		this.right_mask = right_mask;
	}
	
	
	public boolean hasRight(int rightIndex){
		try {
			String [] rightArr = this.right_mask.split("");
			return rightArr[rightIndex ].equals("1") ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	
	}
	

	
	
	
}
