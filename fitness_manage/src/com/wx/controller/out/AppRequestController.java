package com.wx.controller.out;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.framework.common.StaticFinal;

import com.util.UUIDUntil;
import com.wx.entity.commodity.CommodityImg;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.entity.rcv.RcvInfo;
import com.wx.entity.sys.AppInfo;
import com.wx.service.out.AppRequestService;


@Controller
@RequestMapping("/appReq")
public class AppRequestController extends BaseController {
	
	@Autowired
	private AppRequestService appRequestService ;

	@RequestMapping("/loadPicTexts.do")
	@ResponseBody
	private Response loadPicTexts(HttpServletRequest request){
		
		   String app_info_code = request.getParameter("app_info_code") ;
		   if(app_info_code == null || "".equals(app_info_code)){
			  return Response.error("无效的小程序编号");
		   }
			   
	       try {
			 JSONObject ret = appRequestService.loadPicTexts(app_info_code) ;
		     
		    return Response.success("加载小程序数据成功", ret) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			return Response.error(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/loadAppInfo.do")
	@ResponseBody
	private Response loadAppInfo(HttpServletRequest request){  //在线商城方法

	   String app_info_code = request.getParameter("app_info_code") ;
	   if(app_info_code == null || "".equals(app_info_code)){
		  return Response.error("无效的小程序编号");
	   }
		   
	try{
			JSONObject ret = appRequestService.loadAppInfo(app_info_code) ;
		     
		    return Response.success("加载小程序数据成功", ret) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			return Response.error(e.getMessage());
		}	   
	}
	
	
	/**
	 * //驾校报名
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
			    JSONObject ret  = appRequestService.getAppInfo(appinfo) ;
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
	
	@ResponseBody
	@RequestMapping("/getCommodityList.do")
	private Response getCommodityList(HttpServletRequest request){
		   String app_info_code = request.getParameter("app_info_code") ;
		   if(app_info_code == null || "".equals(app_info_code)){
			  return Response.error("无效的小程序编号");
		   }
		   
		   CommodityInfo commodifyInfo = new CommodityInfo() ;
		   commodifyInfo.setApp_info_code(app_info_code) ;
		   try {
			List<CommodityInfo> list = appRequestService.getCommodityList(commodifyInfo) ;
			return Response.success("查询商品列表成功",JSONArray.fromObject(list)) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 	Response.error("查询商品列表失败:"+e.getMessage()) ;
		}	   
	}
	
	@ResponseBody
	@RequestMapping("/getCommodityImgs.do")
	private Response getCommodityImgs(CommodityImg commodityImg){
		
		if(commodityImg.getApp_info_code() == null || "".equals(commodityImg.getApp_info_code())){
			  return Response.error("无效的小程序编号");
		}

	   try {
		List<CommodityImg> list = appRequestService.selectCommodityImgs(commodityImg) ;
		return Response.success("查询商品图片列表成功",JSONArray.fromObject(list)) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 	Response.error("查询图片列表失败:"+e.getMessage()) ;
		}	   
	}
	
	@ResponseBody
	@RequestMapping("/getRcvInfos.do")
	private Response getRcvInfos(String aic, String sid,HttpServletRequest req){
	
		if(aic == null ||aic.isEmpty()){
			  return Response.error("无效的小程序编号");
		}
		
		if(sid == null ||sid.isEmpty()){
			  return Response.error("无效的sessionId");
		}
		
		
		HttpSession session = req.getSession() ;
		if(!session.getId().equals(sid)){
			return Response.error("session过期",null,"expire") ;
		}
		
		Object sData = session.getAttribute(StaticFinal.WX_SECRETY_KEY) ;

		if(sData == null){
			return Response.error("获取session 数据失败。",null,"expire") ;
		}
		/*
		 * loginInfo: { 
			  "session_key": "a9IKgGXh\/\/aGAcRgviy6eg==", 
			  "expires_in": 7200, 
			  "openid": "o7J_w0Iq-p3R-wVkKITPbQI1PrEw" 
		  }
		*/
		
		JSONObject jsonObj = JSONObject.fromObject(sData) ;

		RcvInfo rcvInfo = new RcvInfo() ;
		rcvInfo.setApp_info_code(aic);
		//rcvInfo.setOpenid(openid) ;
		rcvInfo.setOpenid(jsonObj.getString("openid")) ;
		
	 try {
			List<RcvInfo> list = appRequestService.selectRcvInfos(rcvInfo) ;
				return Response.success("查询用户收货人信息成功",JSONArray.fromObject(list)) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 	Response.error("查询用户收货人信息失败:"+e.getMessage()) ;
			}	  
	}
	
	@ResponseBody
	@RequestMapping("/setDRcvInfo.do")
	private Response setDRcvInfo(String aic, String sid,String code, HttpServletRequest req){
	
		if(aic == null ||aic.isEmpty()){
			  return Response.error("无效的小程序编号");
		}
		
		if(sid == null ||sid.isEmpty()){
			  return Response.error("无效的sessionId");
		}
		
		if(code == null ||code.isEmpty()){
			  return Response.error("无效的收货人信息编号");
		}
		
		HttpSession session = req.getSession() ;
		if(!session.getId().equals(sid)){
			return Response.error("session过期",null,"expire") ;
		}
		
		Object sData = session.getAttribute(StaticFinal.WX_SECRETY_KEY) ;

		if(sData == null){
			return Response.error("获取session 数据失败。",null,"expire") ;
		}
		/*
		 * loginInfo: { 
			  "session_key": "a9IKgGXh\/\/aGAcRgviy6eg==", 
			  "expires_in": 7200, 
			  "openid": "o7J_w0Iq-p3R-wVkKITPbQI1PrEw" 
		  }
		*/
		
		JSONObject jsonObj = JSONObject.fromObject(sData) ;

		RcvInfo rcvInfo = new RcvInfo() ;
		rcvInfo.setApp_info_code(aic);
		rcvInfo.setRcv_code(code) ;
		//rcvInfo.setOpenid(openid) ;
		rcvInfo.setOpenid(jsonObj.getString("openid")) ;
	 try {
			boolean ret = appRequestService.setDefaultRcv(rcvInfo) ;
			if(ret){
				return Response.success("设置默认收货人成功") ;
			}
			    return Response.error("设置默认收货人失败") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 	Response.error("设置默认收货人失败:"+e.getMessage()) ;
			}	  
	}
	
	

	@ResponseBody
	@RequestMapping("/addRcvInfo.do")
	private Response addRcvInfo(RcvInfo rcvInfo,String sid, HttpServletRequest req){
	
		if(sid == null ||sid.isEmpty()){
			  return Response.error("无效的sessionId");
		}
		
		HttpSession session = req.getSession() ;
		if(!session.getId().equals(sid)){
			return Response.error("session过期",null,"expire") ;
		}
		
		if(rcvInfo.getApp_info_code() == null || rcvInfo.getApp_info_code().isEmpty()){
			  return Response.error("无效的小程序编号");
		}
		
		if(rcvInfo.getName() == null || rcvInfo.getName().isEmpty()){
			  return Response.error("请填写收货人姓名");
		}
		
		if(rcvInfo.getAddress() == null || rcvInfo.getAddress().isEmpty() ||  rcvInfo.getAddress().length()<8){
			  return Response.error("请填写详细的收货人地址");
		}
		
		if(rcvInfo.getMobile() == null || rcvInfo.getMobile().isEmpty() ){
			  return Response.error("请填写收货人电话");
		}
		
		Object sData = session.getAttribute(StaticFinal.WX_SECRETY_KEY) ;
		JSONObject jsonObj = JSONObject.fromObject(sData) ;
		rcvInfo.setOpenid(jsonObj.getString("openid")) ;

		if(sData == null){
			return Response.error("获取session 数据失败。",null,"expire") ;
		}
		
		rcvInfo.setRcv_code(UUIDUntil.genUUID(16, "SHR-"));
		
		/*
		 * loginInfo: { 
			  "session_key": "a9IKgGXh\/\/aGAcRgviy6eg==", 
			  "expires_in": 7200, 
			  "openid": "o7J_w0Iq-p3R-wVkKITPbQI1PrEw" 
		  }
		*/
		

	 try {
			boolean ret = appRequestService.addRcvInfo(rcvInfo) ;
			if(ret){
				 return Response.success("增加收货人成功",JSONObject.fromObject(rcvInfo)) ;
			}
			    return Response.error("增加收货人失败") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 	Response.error("增加收货人失败:"+e.getMessage()) ;
			}	  
	}
	
	

	@ResponseBody
	@RequestMapping("/modifyRcvInfo.do")
	private Response modifyRcvInfo(RcvInfo rcvInfo,String sid, HttpServletRequest req){
	
		if(sid == null ||sid.isEmpty()){
			  return Response.error("无效的sessionId");
		}
		
		HttpSession session = req.getSession() ;
		if(!session.getId().equals(sid)){
			return Response.error("session过期",null,"expire") ;
		}
		
		if(rcvInfo.getRcv_code() == null || rcvInfo.getRcv_code().isEmpty()){
			  return Response.error("无效的收货人信息编号");
		}
		
		if(rcvInfo.getApp_info_code() == null || rcvInfo.getApp_info_code().isEmpty()){
			  return Response.error("无效的小程序编号");
		}
		
		if(rcvInfo.getName() == null || rcvInfo.getName().isEmpty()){
			  return Response.error("请填写收货人姓名");
		}
		
		if(rcvInfo.getAddress() == null || rcvInfo.getAddress().isEmpty() ||  rcvInfo.getAddress().length()<8){
			  return Response.error("请填写详细的收货人地址");
		}
		
		if(rcvInfo.getMobile() == null || rcvInfo.getMobile().isEmpty() ){
			  return Response.error("请填写收货人电话");
		}
		
		Object sData = session.getAttribute(StaticFinal.WX_SECRETY_KEY) ;
		JSONObject jsonObj = JSONObject.fromObject(sData) ;
		rcvInfo.setOpenid(jsonObj.getString("openid")) ;

		if(sData == null){
			return Response.error("获取session 数据失败。",null,"expire") ;
		}
		
		
		
		/*
		 * loginInfo: { 
			  "session_key": "a9IKgGXh\/\/aGAcRgviy6eg==", 
			  "expires_in": 7200, 
			  "openid": "o7J_w0Iq-p3R-wVkKITPbQI1PrEw" 
		  }
		*/
		

	 try {
			boolean ret = appRequestService.modifyRcvInfo(rcvInfo) ;
			if(ret){
				 return Response.success("修改收货人成功",JSONObject.fromObject(rcvInfo)) ;
			}
			    return Response.error("修改收货人失败") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 	Response.error("修改收货人失败:"+e.getMessage()) ;
			}	  
	}
	
	

	@ResponseBody
	@RequestMapping("/delRcvInfo.do")
	private Response delRcvInfo(RcvInfo rcvInfo,String sid, HttpServletRequest req){
	
		if(sid == null ||sid.isEmpty()){
			  return Response.error("无效的sessionId");
		}
		
		HttpSession session = req.getSession() ;
		if(!session.getId().equals(sid)){
			return Response.error("session过期",null,"expire") ;
		}
		
		if(rcvInfo.getRcv_code() == null || rcvInfo.getRcv_code().isEmpty()){
			  return Response.error("无效的收货人信息编号");
		}
		
		if(rcvInfo.getApp_info_code() == null || rcvInfo.getApp_info_code().isEmpty()){
			  return Response.error("无效的小程序编号");
		}
		
		Object sData = session.getAttribute(StaticFinal.WX_SECRETY_KEY) ;
		JSONObject jsonObj = JSONObject.fromObject(sData) ;
		rcvInfo.setOpenid(jsonObj.getString("openid")) ;

		if(sData == null){
			return Response.error("获取session 数据失败。",null,"expire") ;
		}
		
		
		
		/*
		 * loginInfo: { 
			  "session_key": "a9IKgGXh\/\/aGAcRgviy6eg==", 
			  "expires_in": 7200, 
			  "openid": "o7J_w0Iq-p3R-wVkKITPbQI1PrEw" 
		  }
		*/
		

	 try {
			boolean ret = appRequestService.deleteRcvInfo(rcvInfo) ;
			if(ret){
				 return Response.success("删除收货人成功",JSONObject.fromObject(rcvInfo)) ;
			}
			    return Response.error("删除收货人失败") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 	Response.error("删除收货人失败:"+e.getMessage()) ;
			}	  
	}
	
	
	
	
}
