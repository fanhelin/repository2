package com.wx.serviceImpl.commodity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.wx.entity.commodity.CommodityInfo;
import com.wx.mapper.commodity.CommodityInfoMapper;
import com.wx.service.commodity.CommodityInfoService;

@Service
public class CommodityInfoServiceImpl implements CommodityInfoService {
	
	@Autowired
	private CommodityInfoMapper commodityInfoMapper;

	@Override
	public void addCommodityInfo(CommodityInfo commodityInfo) throws Exception {
		// TODO Auto-generated method stub
		commodityInfo.setCode(UUIDUntil.genUUID(30, "comInf_", CaseEnum.LOWER));
		this.commodityInfoMapper.addCommodityInfo(commodityInfo);
	}

	@Override
	public List<CommodityInfo> findCommodityInfoList(Map<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		List<CommodityInfo> list=this.commodityInfoMapper.findCommodityInfoList(map);
		return list;
	}

	@Override
	public void updateCommodityInfo(CommodityInfo commodityInfo)
			throws Exception {
		// TODO Auto-generated method stub
		this.commodityInfoMapper.updateCommodityInfo(commodityInfo);
	}

	@Override
	public void deletCommodityInfo(CommodityInfo commodityInfo)
			throws Exception {
		// TODO Auto-generated method stub
		this.commodityInfoMapper.deleteCommodityInfo(commodityInfo);
	}

}
