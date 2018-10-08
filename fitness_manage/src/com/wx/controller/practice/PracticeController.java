package com.wx.controller.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.wx.entity.practice.Practice;
import com.wx.service.practice.PracticeService;


@Controller
@RequestMapping("/practice")
public class PracticeController extends BaseController{
	
	@Autowired
	private  PracticeService practiceService;
	
	@ResponseBody
	@RequestMapping("/findPracticeList.do")
	public Response findPracticeList(@RequestParam Map<?,?> practice){
		try {
			List<Practice> list = practiceService.selectPractice(practice);
			return Response.success("查询全部练习成功", JSONArray.fromObject(list)) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
/*	@RequestMapping("/getLastPractice.do")
	@ResponseBody
	public Response getLastPractice(Practice practice){
		try {
			Practice practice1 = practiceService.getLastPractice(practice) ;
			if(practice1 != null){
				return Response.success("success", practice1) ;
			}else{
				return Response.error("查询失败") ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error("查询失败",e.getMessage()) ;
		}
	}*/
}
