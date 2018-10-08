package com.wx.serviceImpl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.sys.Staff;
import com.wx.mapper.sys.StaffMapper;
import com.wx.service.sys.StaffService;

@Service
public class StaffServiceImpl extends BaseService implements StaffService {

	@Autowired
	private StaffMapper staffMapper ;
	
	@Override
	public List<Staff> selectStaffs(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		return staffMapper.selectStaffs(staff) ;

	}

	@Override
	public int insertStaff(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		return staffMapper.insertStaff(staff) ;
		
	}

	@Override
	public int updateStaff(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		
		return staffMapper.updateStaff(staff);
	}

	@Override
	public int deleteStaff(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		return staffMapper.deleteStaff(id);
	}

}
