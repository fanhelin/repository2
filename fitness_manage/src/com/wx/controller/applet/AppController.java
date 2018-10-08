package com.wx.controller.applet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.util.pay.WxPayUtil;
import com.wx.entity.chapter.Chapter;
import com.wx.entity.client.Client;
import com.wx.entity.course.Course;
import com.wx.entity.course.CourseOrder;
import com.wx.entity.practice.Practice;
import com.wx.entity.sys.AppInfo;
import com.wx.service.applet.AppService;
import com.wx.service.chapter.ChapterService;
import com.wx.service.client.ClientService;
import com.wx.service.course.CourseService;
import com.wx.service.practice.PracticeService;


@Controller
@RequestMapping("/app")
public class AppController extends BaseController {
	@Autowired
	AppService appService ;
	
	@Autowired
	CourseService courseService ;
	
	@Autowired
	ChapterService chapterService ;
	
	@Autowired
	ClientService clientService ;
	
	@Autowired
	PracticeService practiceService ;
	/**
	 * 视频订购小程序
	 * @param appinfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAppInfo.do")
	private Response getAppInfo(AppInfo appinfo){

		   if(appinfo.getApp_info_code() == null ||appinfo.getApp_info_code().equals("")){
			   return Response.error("无效的小程序编号");
		   }
			   
		try{
			    JSONObject ret  = appService.getAppJson(appinfo) ;
			    if(ret != null){
			    	 return Response.success("加载小程序数据成功", ret) ;
			    }else{
			    	return Response.error("加载小程序数据失败",null) ;
			    }
			  
			} catch (Exception e) {
				// TODO Auto-generated catch block
			
				return Response.error(e.getMessage());
			}	
	}
	
	

	@RequestMapping("/getOpenid.do")
	@ResponseBody
	private Response getOpenid(String app_info_code,String code,HttpServletRequest req)
	{
		//https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
       try {
               if (StringUtils.isEmpty(code) || StringUtils.isEmpty(app_info_code) ) {
                  throw new Exception("参数异常");
           }

            AppInfo appInfo = new AppInfo();
            appInfo.setApp_info_code(app_info_code) ;
            appInfo = appService.getAppInfo(appInfo) ;
            
            WxPayUtil wxp = new WxPayUtil();
            String ret = wxp.getOpenid(appInfo.getApp_id(), appInfo.getSecret(), code) ;

           JSONObject retJ= JSONObject.fromObject(ret) ;
           if(retJ.containsKey("openid") && retJ.containsKey("session_key"))
           {
        	   return Response.success("获取登陆信息成功", retJ) ;
           }else{
        	   return Response.error("登陆失败，小程序配置信息错误:"+appInfo,retJ) ;
           }
           
         } catch (Exception e) {
            
              e.printStackTrace();
              return Response.error(e.getMessage()) ;
        }
	}


	/**
	 * 小程序获取用户信息
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserInfo.do") 
	private Response getUserInfo(@RequestParam  Map<String,Object> param){
        try {
        	  
			JSONObject userInfo = appService.getUserInfo(param) ;
			if(userInfo != null){
				return Response.success("获取用户信息成功",userInfo);
			}else{
				return Response.error("获取用户信息失败") ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
	
	@RequestMapping("getAllCourse.do")
	@ResponseBody
	private Response getAllCourse( Course course){
		
		try {
			List<Course> list = courseService.selectCourses(course) ;
			  
			return Response.success("查询课程成功", JSONArray.fromObject(list)) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
	
	
	@RequestMapping("getCourseChapters.do")
	@ResponseBody
	private Response getCourseChapters(Chapter chapter){
		
		try {
			List<Chapter> list = chapterService.selectChapters(chapter) ;
			  
			return Response.success("查询章节成功", JSONArray.fromObject(list)) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
	
	   /**
	    * 小程序用户注册
	    * @param client
	    * @return
	    */
		@RequestMapping("addClient.do")
		@ResponseBody
		private Response addClient(Client client){
			
			try {
				  clientService.addClient(client) ; 
				  return Response.success("注册成功", JSONObject.fromObject(client)) ;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.error(e.getMessage()) ;
			}
		}	
		

		   /**
		    * 小程序用户注册信息修改
		    * @param client
		    * @return
		    */
			@RequestMapping("updateClient.do")
			@ResponseBody
			private Response updateClient(Client client){
				
				try {
					  clientService.updateClient(client) ; 
					  return Response.success("修改注册信息成功", JSONObject.fromObject(client)) ;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Response.error(e.getMessage()) ;
				}
			}
			
			@RequestMapping("findChapterByCourse.do")
			@ResponseBody
			public Response findChapterByCourse(Course course){
				try {
					 Chapter chapter=this.chapterService.findChapterByCourse(course);
					 return Response.success("成功", chapter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Response.error(e.getMessage()) ;
				}
			}
			
			@RequestMapping("addPractice.do")
			@ResponseBody
			public Response addPractice(CourseOrder courseOrder){
				try {
					 courseOrder.setLast_date(new Date().toLocaleString());
					 Practice practice=new Practice();
					 practice.setOpenid(courseOrder.getOpenid());
					 practice.setCourse(courseOrder.getCourse_code());
					 practice.setApp_info_code(courseOrder.getApp_info_code());
					 practice.setChapter(courseOrder.getChapter_code());
					 practice.setMinute(courseOrder.getNum());
					 practice.setDatetime(new Date().toLocaleString());
					 this.practiceService.addPractice(practice, courseOrder);
					 return Response.success("成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Response.error(e.getMessage()) ;
				}
			}
			
			@RequestMapping("courseRestart.do")
			@ResponseBody
			public Response courseRestart(CourseOrder courseOrder){
				try {
					 this.courseService.courseRestart(courseOrder);
					 return Response.success("成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Response.error(e.getMessage()) ;
				}
			}
			
			@RequestMapping("alreayBuy.do")
			@ResponseBody
			public Response alreayBuy(CourseOrder courseOrder){
				try {
					 Integer count = this.courseService.getClientCourseCount(courseOrder);
					 JSONObject retJsonObject = new JSONObject() ;
					 retJsonObject.put("count", count) ;
					 return Response.success("成功",retJsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Response.error(e.getMessage()) ;
				}
			}
			
			
			
}
