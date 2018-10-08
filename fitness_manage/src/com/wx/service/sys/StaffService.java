package com.wx.service.sys;

import java.util.List;

import com.wx.entity.sys.Staff;

public interface StaffService {
	   List<Staff>selectStaffs(Staff staff) throws Exception;
	   
	   int insertStaff(Staff staff) throws Exception;

	   int updateStaff(Staff staff) throws Exception ;
	   
	   int deleteStaff(Integer id) throws Exception ;
}
