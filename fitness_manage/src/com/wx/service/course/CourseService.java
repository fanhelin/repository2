package com.wx.service.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wx.entity.course.Course;
import com.wx.entity.course.CourseOrder;

public interface CourseService {
	  List<Course> selectCourses(Course course) throws Exception ;
	  
	  void addCourses(Course course) throws Exception ;
	  void updateCourses(Course course) throws Exception ; 
	  void deleteCourse(Course course) throws Exception ; 
	  void updateCourseImg(Course course,HttpServletRequest request) throws Exception ; 
	  List<Course> findCourseByOrder(Course course)throws Exception ;
	  void courseRestart(CourseOrder courseOrder)throws Exception;
	  int getClientCourseCount(CourseOrder courseOrder)throws Exception;
}
