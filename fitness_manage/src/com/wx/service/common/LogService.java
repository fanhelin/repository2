package com.wx.service.common;

import java.util.List;

import com.wx.entity.common.LogBean;

public interface LogService {
	  List<LogBean> selectLogs(LogBean logBean) throws Exception ;
	   
	   int insertLogBatch(List<LogBean> list) throws Exception ;
}
