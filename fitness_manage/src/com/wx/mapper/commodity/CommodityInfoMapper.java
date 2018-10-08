package com.wx.mapper.commodity;

import java.util.List;
import java.util.Map;

import com.wx.entity.commodity.CommodityInfo;

public interface CommodityInfoMapper {
	public List<CommodityInfo> findCommodityInfoList(Map<?,?> map)throws Exception;
	public int addCommodityInfo(CommodityInfo commodityInfo)throws Exception;
	public int updateCommodityInfo(CommodityInfo commodityInfo)throws Exception;
	public int deleteCommodityInfo(CommodityInfo commodityInfo)throws Exception;
}
