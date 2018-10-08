package com.wx.mapper.course;

import java.util.List;

import com.wx.entity.client.Client;
import com.wx.entity.course.Course;
import com.wx.entity.course.CourseOrder;




public interface CourseMapper {
  List<Course> selectCourses(Course course) throws Exception ;
 
  int addCourse(Course course) throws Exception ;
  
  int updateCourse(Course course) throws Exception ; 
  
  int deleteCourse(Course course) throws Exception ; 
  
  int updateCourseImg(Course course)throws Exception;
  
  List<Course> findClientCourse(Client Client)throws Exception;
  
  List<Course> findCourseByOrder(Course course)throws Exception;

  int addCourseOrder (List<CourseOrder> list) throws Exception ; 
  
  void updateCourseOrder(CourseOrder courseOrder)throws Exception;
  
  void courseRestart(CourseOrder courseOrder)throws Exception;
  
  int getClientCourseCount(CourseOrder courseOrder)throws Exception;
}
