package com.framework.log;

import java.util.concurrent.LinkedBlockingDeque;

import com.framework.common.Config;
import com.wx.entity.common.LogBean;


/**
 * 
 * @todo 日志收集器，负责收集系统日志和操作日志
 *
 * @author fhr
 * @dateTime 2016 2016-11-4 下午5:25:57
 */
public class LogGather {

	private static LogGather logGather = null;
	private static Object locker = new Object();
	
	private LinkedBlockingDeque<LogBean> logQueue = null;
	
	
	private LogGather() {
		logQueue = new LinkedBlockingDeque<LogBean>();
	}
	
	private static LogGather Instance(){
		if(logGather == null){
			  synchronized (locker){
				  logGather = new LogGather(); 
			  }
		}
		return logGather ;
	}
	
	public static LinkedBlockingDeque<LogBean> getLogQueue(){
		return Instance().logQueue ;
	}
	
	public static void putLog (LogBean logBean){
		try {
			if(Config.getConfig("log_Flag").equals("on")){
				Instance().logQueue.put(logBean) ;
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
}
