package com.wx.serviceImpl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.common.Area;
import com.wx.mapper.common.AreaMapper;
import com.wx.service.common.AreaService;

@Service
public class AreaServiceImpl extends BaseService implements AreaService {

	@Autowired
	private AreaMapper areaMapper ;
	@Override
	public List<Area> selectAreas(Area area) throws Exception {
		// TODO Auto-generated method stub
		return areaMapper.selectAreas(area);
	}

}
