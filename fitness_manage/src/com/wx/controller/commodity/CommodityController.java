package com.wx.controller.commodity;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Config;
import com.framework.common.Response;
import com.framework.common.StaticFinal;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.entity.sys.AppInfo;
import com.wx.service.commodity.CommodityInfoService;

@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {
	
	@Autowired
	private CommodityInfoService commodityInfoService;

	@ResponseBody
	@RequestMapping("/addCommodityInfo.do")
	private Response addCommodityInfo(CommodityInfo commodityInfo)
			throws Exception {
		String appInfoCode = user.getApp_info_code();
		if (appInfoCode == null || appInfoCode.isEmpty()) {
			return Response.error("获取小程序编号失败");
		}
		try {
			commodityInfo.setApp_info_code(appInfoCode);
			if (commodityInfo.getMain_img_file() != null) {
				String fileName=commodityInfo.getMain_img_file().getOriginalFilename();
				String pix = fileName.substring(fileName.lastIndexOf("."));
				commodityInfo.setMain_img("main_" + new Date().getTime() + pix);
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)
						+ File.separator + "commodity";
				commodityInfo.getMain_img_file().transferTo(new File(
						webPath + File.separator + commodityInfo.getMain_img()));
			}
			if (commodityInfo.getFu_img_file() != null) {
				String fileName=commodityInfo.getFu_img_file().getOriginalFilename();
				String pix = fileName.substring(fileName.lastIndexOf("."));
				commodityInfo.setFu_img("fu_" + new Date().getTime() + pix);
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)
						+ File.separator + "commodity";
				commodityInfo.getFu_img_file().transferTo(new File(
						webPath + File.separator + commodityInfo.getFu_img()));
			}
			this.commodityInfoService.addCommodityInfo(commodityInfo);
			return Response.success("添加商品成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}

	}
	
	
	@ResponseBody
	@RequestMapping("/updateCommodityInfo.do")
	private Response updateCommodityInfo(CommodityInfo commodityInfo)
			throws Exception {
		try {
			if (commodityInfo.getMain_img_file() != null) {
				String fileName=commodityInfo.getMain_img_file().getOriginalFilename();
				String pix = fileName.substring(fileName.lastIndexOf("."));
				commodityInfo.setMain_img("main_" + new Date().getTime() + pix);
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)
						+ File.separator + "commodity";
				commodityInfo.getMain_img_file().transferTo(new File(
						webPath + File.separator + commodityInfo.getMain_img()));
			}
			if (commodityInfo.getFu_img_file() != null) {
				String fileName=commodityInfo.getFu_img_file().getOriginalFilename();
				String pix = fileName.substring(fileName.lastIndexOf("."));
				commodityInfo.setFu_img("fu_" + new Date().getTime() + pix);
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)
						+ File.separator + "commodity";
				commodityInfo.getFu_img_file().transferTo(new File(
						webPath + File.separator + commodityInfo.getFu_img()));
			}
			this.commodityInfoService.updateCommodityInfo(commodityInfo);
			return Response.success("修改商品成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
	
	@ResponseBody
	@RequestMapping("/deleteCommodityInfo.do")
	private Response deleteCommodityInfo(CommodityInfo commodityInfo)
			throws Exception {
		try{
			this.commodityInfoService.deletCommodityInfo(commodityInfo);
			return Response.success("删除商品成功");
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
	
	@ResponseBody
	@RequestMapping("/findCommodityInfoList.do")
	private String findCommodityInfoList(){
		try {
			Map<String,String> map=new HashMap<String,String>();
			String appInfoCode = user.getApp_info_code();
			if (appInfoCode == null || appInfoCode.isEmpty()) {
				return null;
			}
			map.put("app_info_code", appInfoCode);
			List<CommodityInfo> list=this.commodityInfoService.findCommodityInfoList(map);
			JSONObject ret = new JSONObject() ;
			ret.put("total",list.size()) ;
			ret.put("rows", JSONArray.fromObject(list));
			return ret.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj().toString() ;
		}	
	}
	
	/**
	 * 小程序使用的商品查询
	 * **/
	@ResponseBody
	@RequestMapping("/getCommoditys.do")
	private Response getCommoditys(AppInfo appInfo){
		try {
			
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("app_info_code",appInfo.getApp_info_code()) ;
			List<CommodityInfo> list=this.commodityInfoService.findCommodityInfoList(param);
			
			return Response.success("成功",JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage());
		}	
	}
}