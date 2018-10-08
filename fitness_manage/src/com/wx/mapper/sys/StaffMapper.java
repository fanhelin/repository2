package com.wx.mapper.sys;

import java.util.List;

import com.wx.entity.sys.Staff;

public interface StaffMapper {
   List<Staff>selectStaffs(Staff staff) throws Exception;
   
   int insertStaff(Staff staff) throws Exception;

   int updateStaff(Staff staff) throws Exception ;
   
   int deleteStaff(Integer id) throws Exception ;

}
