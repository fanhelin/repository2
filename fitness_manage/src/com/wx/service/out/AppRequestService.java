package com.wx.service.out;

import java.util.List;

import com.wx.entity.commodity.CommodityImg;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.entity.rcv.RcvInfo;
import com.wx.entity.sys.AppInfo;

import net.sf.json.JSONObject;

public interface AppRequestService {
   JSONObject loadPicTexts(String app_info_code) throws Exception ;
   
   JSONObject loadAppInfo(String app_info_code) throws Exception ;
   
   JSONObject getAppInfo(AppInfo appInfo) throws Exception ;
   
   
    List<CommodityInfo> getCommodityList(CommodityInfo commodifyInfo) throws Exception ;

    List<CommodityImg> selectCommodityImgs(CommodityImg commodityImg) throws Exception ;
    
    List<RcvInfo> selectRcvInfos(RcvInfo rcvInfo) throws Exception ;
    
    boolean setDefaultRcv(RcvInfo rcvInfo) throws Exception;
    
    boolean addRcvInfo(RcvInfo rcvInfo) throws Exception ;
    
    boolean deleteRcvInfo(RcvInfo rcvInfo) throws Exception ;
    
    boolean modifyRcvInfo(RcvInfo rcvInfo) throws Exception ;
    
}
