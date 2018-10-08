package com.wx.serviceImpl.applet;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.client.Client;
import com.wx.entity.course.Course;
import com.wx.entity.practice.Practice;
import com.wx.entity.sys.AppInfo;
import com.wx.mapper.applet.AppMapper;
import com.wx.mapper.client.ClientMapper;
import com.wx.mapper.course.CourseMapper;
import com.wx.mapper.practice.PracticeMapper;
import com.wx.service.applet.AppService;
@Service
public class AppServiceImpl extends BaseService implements AppService {


	@Autowired
    AppMapper appMapper ;

	
	@Autowired
	ClientMapper clientMapper ;
	
	@Autowired
	PracticeMapper practiceMapper ;
	
	
	@Autowired
	CourseMapper courseMapper ;
	

	
	@Override
	public JSONObject getAppJson(AppInfo appInfo) throws Exception {
		// TODO Auto-generated method stub
		JSONObject retObject = new JSONObject() ;
		AppInfo retInfo = appMapper.selectAppInfo(appInfo);
		retObject.put("appInfo", retInfo) ;
		

		return retObject ;
		
	}

	@Override
	public AppInfo getAppInfo(AppInfo appInfo) throws Exception {
		// TODO Auto-generated method stub
		AppInfo retInfo = appMapper.selectAppInfo(appInfo);
		return retInfo;
	}
	
	@Override
	public JSONObject getUserInfo(Map<?, ?> map) throws Exception {
		// TODO Auto-generated method stub
		Client client = clientMapper.getUserInfo(map);
		JSONObject retObject = new JSONObject() ;
		retObject.put("client", client) ;
		if(client != null){
			List<Practice> practices = practiceMapper.selectPractice(map) ;
			retObject.put("practices", practices) ;
			
			List<Course> courses=courseMapper.findClientCourse(client);
			retObject.put("courses", courses) ;
		}
		/*Practice practice = new Practice();
		practice.setOpenid(client.getOpenid()) ;
		practice = practiceMapper.getLastPractice(practice) ;
		retObject.put("lastPractice", practice) ;
	*/
		return retObject ;
		
	}
	
	


}
