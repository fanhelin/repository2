package com.wx.controller.applet;

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
import com.util.UUIDUntil;
import com.wx.entity.rcv.RcvInfo;
import com.wx.service.applet.RcvInfoService;

@Controller
@RequestMapping("/rcv")
public class RcvInfoController extends BaseController {
	
	@Autowired
	RcvInfoService rcvInfoService ;
	
	@ResponseBody
	@RequestMapping("/getRcvInfos.do")
	private Response getRcvInfos(RcvInfo rcvInfo){
	 try {
			List<RcvInfo> list = rcvInfoService.selectRcvInfos(rcvInfo) ;
				return Response.success("查询用户收货人信息成功",JSONArray.fromObject(list)) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 	Response.error("查询用户收货人信息失败:"+e.getMessage()) ;
			}	  
	}
	
	@ResponseBody
	@RequestMapping("/setDRcvInfo.do")
	private Response setDRcvInfo(RcvInfo rcvInfo){
	 try {
			boolean ret = rcvInfoService.setDefaultRcv(rcvInfo) ;
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
	private Response addRcvInfo(RcvInfo rcvInfo){
	

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
		
		rcvInfo.setRcv_code(UUIDUntil.genUUID(16, "SHR-"));
		
	 try {
			boolean ret = rcvInfoService.addRcvInfo(rcvInfo) ;
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
	private Response modifyRcvInfo(RcvInfo rcvInfo){
	
	
		
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
		
		

	 try {
			boolean ret = rcvInfoService.modifyRcvInfo(rcvInfo) ;
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
	private Response delRcvInfo(RcvInfo rcvInfo){

		if(rcvInfo.getRcv_code() == null || rcvInfo.getRcv_code().isEmpty()){
			  return Response.error("无效的收货人信息编号");
		}
		
		if(rcvInfo.getApp_info_code() == null || rcvInfo.getApp_info_code().isEmpty()){
			  return Response.error("无效的小程序编号");
		}
		

	 try {
			boolean ret = rcvInfoService.deleteRcvInfo(rcvInfo) ;
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
