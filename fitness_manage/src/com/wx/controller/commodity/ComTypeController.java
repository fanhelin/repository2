package com.wx.controller.commodity;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.wx.entity.commodity.CommodityType;
import com.wx.service.commodity.ComTypeService;

@Controller

@RequestMapping("/comType")
public class ComTypeController extends BaseController {
	
	@Autowired
	private ComTypeService comTypeService ;
	
	
	@ResponseBody
	@RequestMapping("/selectComTypes.do")
    private JSONObject selectComTypes(){
    try {
    	    CommodityType commodityType = new CommodityType() ;
    	    commodityType.setApp_info_code(this.user.getApp_info_code()) ;
			List<CommodityType> list = comTypeService.selectCommodityType(commodityType) ;
			JSONArray rows = JSONArray.fromObject(list) ;
			JSONObject ret = new JSONObject() ;
			ret.put("total", list.size()) ;
			ret.put("rows", rows) ;
			
			return ret ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return retEmptyJsonObj() ;
		}
    	  	 
     }
	
	@ResponseBody
	@RequestMapping("/addComType.do")
	private Response AddComType(CommodityType commodityType)
	{
		String appInfoCode = user.getApp_info_code() ;
		if(appInfoCode == null || appInfoCode.isEmpty()){
			return Response.error("获取小程序编号失败") ;
		}
		commodityType.setApp_info_code(user.getApp_info_code()) ;
		try {
			int ret = comTypeService.addCommodityType(commodityType) ;
			if(ret>0){
				return Response.success("增加商品类型成功") ;
			}else{
				return Response.error("增加商品类型失败") ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
			
	}
	
	
	@ResponseBody
	@RequestMapping("/modifyComType.do")
	private Response modifyComType(CommodityType commodityType)
	{
		String appInfoCode = user.getApp_info_code() ;
		if(appInfoCode == null || appInfoCode.isEmpty()){
			return Response.error("获取小程序编号失败") ;
		}
		commodityType.setApp_info_code(user.getApp_info_code()) ;
		try {
			int ret = comTypeService.modifyCommodityType(commodityType) ;
			if(ret>0){
				return Response.success("修改商品类型成功") ;
			}else{
				return Response.error("修改商品类型失败") ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}	
	}
	

	@ResponseBody
	@RequestMapping("/delComType.do")
	private Response deleteComType(CommodityType commodityType)
	{
		String appInfoCode = user.getApp_info_code() ;
		if(appInfoCode == null || appInfoCode.isEmpty()){
			return Response.error("获取小程序编号失败") ;
		}
		commodityType.setApp_info_code(user.getApp_info_code()) ;
		try {
			int ret = comTypeService.delCommodityType(commodityType) ;
			if(ret>0){
				return Response.success("删除商品类型成功") ;
			}else{
				return Response.error("删除商品类型失败") ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}	
	}
	
}
