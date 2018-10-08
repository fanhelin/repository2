package com.wx.serviceImpl.practice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.wx.entity.course.CourseOrder;
import com.wx.entity.practice.Practice;
import com.wx.mapper.course.CourseMapper;
import com.wx.mapper.practice.PracticeMapper;
import com.wx.service.practice.PracticeService;


@Service
public class PracticeServicImpl extends BaseService implements PracticeService {

	@Autowired
	private PracticeMapper practiceMapper ;
	
	@Autowired
	private CourseMapper courseMapper ;
	
	@Override
	public List<Practice> selectPractice(Map<?, ?> client) throws Exception {
		// TODO Auto-generated method stub
		return practiceMapper.selectPractice(client);
	}

	@Override
	public void addPractice(Practice practice,CourseOrder courseOrder) throws Exception {
		// TODO Auto-generated method stub
		practice.setCode(UUIDUntil.genUUID(30, "pc_", CaseEnum.LOWER));
		courseOrder.setCode(UUIDUntil.genUUID(20, "co_", CaseEnum.LOWER));
		this.practiceMapper.addPractice(practice);
		this.courseMapper.updateCourseOrder(courseOrder);
	}



}
