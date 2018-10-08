package com.wx.serviceImpl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.wx.entity.common.LogBean;
import com.wx.mapper.common.LogMapper;
import com.wx.service.common.LogService;

@Service("logService")
public class LogServiceImpl extends BaseService implements LogService {
	
    @Autowired
	private LogMapper logMapper ;
	@Override
	public List<LogBean> selectLogs(LogBean logBean) throws Exception {
		// TODO Auto-generated method stub
		
		return logMapper.selectLogs(logBean); 
	}

	@Override
	public int insertLogBatch(List<LogBean> list) throws Exception {
		// TODO Auto-generated method stub
		return logMapper.insertLogBatch(list) ;
	
	}

}
