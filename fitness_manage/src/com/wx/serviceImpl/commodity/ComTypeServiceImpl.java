package com.wx.serviceImpl.commodity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.commodity.CommodityType;
import com.wx.mapper.commodity.ComTypeMapper;
import com.wx.service.commodity.ComTypeService;

@Service
public class ComTypeServiceImpl extends BaseService implements ComTypeService {

	@Autowired
	private ComTypeMapper comTypeMapper ;
	
	@Override
	public List<CommodityType> selectCommodityType(CommodityType commodityType)
			throws Exception {
		// TODO Auto-generated method stub
		
		return comTypeMapper.selectCommodityType(commodityType);
	}

	@Override
	public int addCommodityType(CommodityType commodityType) throws Exception {
		// TODO Auto-generated method stub
		
		return comTypeMapper.addCommodityType(commodityType);
	}

	@Override
	public int modifyCommodityType(CommodityType commodityType)
			throws Exception {
		// TODO Auto-generated method stub
		
		return comTypeMapper.modifyCommodityType(commodityType);
	}

	@Override
	public int delCommodityType(CommodityType commodityType) throws Exception {
		// TODO Auto-generated method stub
		return comTypeMapper.delCommodityType(commodityType);
	}

}
