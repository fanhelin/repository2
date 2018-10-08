package com.wx.service.practice;

import java.util.List;
import java.util.Map;

import com.wx.entity.course.CourseOrder;
import com.wx.entity.practice.Practice;

public interface PracticeService {
	 List<Practice> selectPractice(Map<?,?> client) throws Exception ;
	 void addPractice(Practice practice,CourseOrder courseOrder)throws Exception;
	// Practice getLastPractice(Practice practice) throws Exception ;
}
