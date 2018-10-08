package com.wx.service.commodity;

import java.util.List;

import com.wx.entity.commodity.CommodityType;

public interface ComTypeService {
	 List<CommodityType> selectCommodityType(CommodityType commodityType) throws Exception ;
	 
	 int addCommodityType(CommodityType commodityType) throws Exception ;
	 
	 int modifyCommodityType(CommodityType commodityType) throws Exception ;
	 
	 int delCommodityType(CommodityType commodityType) throws Exception ;
	 
	 
}
