package com.wx.controller.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.wx.entity.course.Course;
import com.wx.service.course.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {
	
	@Autowired
	CourseService courseService ;

	@ResponseBody
	@RequestMapping("/selectCourses.do")
	private JSONObject selectCourses(Course course){
		try {
			course.setApp_info_code(user.getApp_info_code());
			List<Course> list = courseService.selectCourses(course) ;
			JSONObject ret = new JSONObject() ;
			ret.put("total", list.size()) ;
			ret.put("rows", JSONArray.fromObject(list)) ;
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj() ;
		}
	
	}

	@ResponseBody
	@RequestMapping("/addCourse.do")
	private Response addCourse(Course course){
		try {
			course.setApp_info_code(user.getApp_info_code()) ;
			courseService.addCourses(course) ;
			return Response.success("新增课程成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	

	@ResponseBody
	@RequestMapping("/updateCourse.do")
	private Response updateCourse(Course course){
		try {
			course.setApp_info_code(user.getApp_info_code()) ;
			courseService.updateCourses(course) ;
			return Response.success("修改课程成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteCourse.do")
	private Response deleteCourse(Course course){
		try {
			course.setApp_info_code(user.getApp_info_code()) ;
			courseService.deleteCourse(course) ;
			return Response.success("删除课程成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	@RequestMapping("/upLoadCourseImg.do")
	@ResponseBody
	public Response upLoadCourseImg(Course course,HttpServletRequest request , HttpServletResponse rep) throws IllegalStateException, IOException {
		rep.addHeader("Access-Control-Allow-Origin", "*");
				try {
					courseService.updateCourseImg(course ,request) ;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
					return Response.error(e.getMessage()) ;
				}
				return Response.success("上传成功" );
		}
	
	@ResponseBody
	@RequestMapping("/findCourseByOrder.do")
	private JSONObject findCourseByOrder(Course course){
		try {
			List<Course> list = courseService.findCourseByOrder(course);
			JSONObject ret = new JSONObject() ;
			ret.put("total", list.size()) ;
			ret.put("rows", JSONArray.fromObject(list)) ;
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj() ;
		}
	
	}
}
