package com.wx.mapper.out;

import java.util.List;


import com.wx.entity.commodity.CommodityImg;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.entity.commodity.CommodityType;
import com.wx.entity.out.OutPicInfo;
import com.wx.entity.out.OutPicSetDetail;
import com.wx.entity.out.OutTextInfo;
import com.wx.entity.pic.ImageInfo;
import com.wx.entity.pic.PicSet;

import com.wx.entity.sys.AppInfo;



public interface AppRequestMapper {
	
  List<OutPicInfo> selectPictures(ImageInfo imageInfo) throws Exception ;
  
  
  List<OutPicSetDetail> selectPicSetLists(PicSet picSet) throws Exception ;
  
  AppInfo selectAppInfo(AppInfo appInfo) throws Exception ;
  
  List<CommodityType> selectCommodityType(CommodityType commodityType) throws Exception ;
  
  List<CommodityInfo> selectCommodityInfo(CommodityInfo commodityInfo) throws Exception ;
  
  List<CommodityImg> selectCommodityImgs(CommodityImg commodityImg) throws Exception ;
  
  
 
  
}
