package com.wx.service.commodity;

import java.util.List;
import java.util.Map;

import com.wx.entity.commodity.CommodityInfo;

public interface CommodityInfoService {
	public void addCommodityInfo(CommodityInfo commodityInfo) throws Exception;
	public List<CommodityInfo> findCommodityInfoList(Map<String,String> map)throws Exception;
	public void updateCommodityInfo(CommodityInfo commodityInfo)throws Exception;
	public void deletCommodityInfo(CommodityInfo commodityInfo)throws Exception;
}
