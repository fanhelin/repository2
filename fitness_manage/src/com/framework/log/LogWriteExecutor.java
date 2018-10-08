package com.framework.log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.LinkedBlockingDeque;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;


import com.framework.common.Config;
import com.framework.common.StaticFinal;
import com.wx.entity.common.LogBean;
import com.wx.service.common.LogService;
import com.util.FunHelper;
/**
 * 日志执行器，从日志队列中取出日志，写入数据库，支持多线程配置,批量写入配置。
 * @author fhr
 * @since 2016/11/3
 */
public class LogWriteExecutor {
	LinkedBlockingDeque<LogBean> queue = null ;
	private LogService logService ;
	
	private static boolean flag = true ;
	private static int poolSize = 0 ; //处理日志线程数
	private static int sleepTime = 10 ; //ms 每轮休眠时间
	private static int deepSleepTime = 10;//s 深度休眠时间
	private static int maxSize = 200 ; //最大批处理数
	private static ExecutorService pool = null;  
	public  Logger log = Logger.getLogger(this.getClass()); 
	
	public LogWriteExecutor(ApplicationContext cont,LinkedBlockingDeque<LogBean> queue){
		this.queue = queue ;
		logService  = (LogService) cont.getBean("logService") ;
		
		flag = Config.getConfig(StaticFinal.CONFIG_LOG_FLAG, "off").endsWith("on")? true :false ;
		poolSize = Integer.parseInt(Config.getConfig(StaticFinal.CONFIG_LOG_THREADS,"1"));
		sleepTime = Integer.parseInt(Config.getConfig(StaticFinal.CONFIG_LOG_SLEEP,"10"));
		deepSleepTime = Integer.parseInt(Config.getConfig(StaticFinal.CONFIG_LOG_DEEPSLEEP,"10"));
		maxSize = Integer.parseInt(Config.getConfig(StaticFinal.CONFIG_LOG_BATCHSIZE,"100"));
	}
	
	
	public void start(){
	 if(!flag){
		 return ;
	 }
		if(pool == null){
			pool = Executors.newFixedThreadPool(poolSize);  
	    }
		
	for(int i=0 ;i< poolSize ;i++){
			
		Runnable task = new Runnable() {  
		    @Override  
		    public void run() {  
		    	List<LogBean> list = new ArrayList<LogBean>() ;
		    	long threadid = Thread.currentThread().getId() ;
		    	while(flag){
		    		
		    	try {
		    		 System.out.println("thread:" +threadid +" Log queue size:"+queue.size());
		    		if(queue.size()>0){
			    		LogBean logBean = queue.take() ;
			    		list.add(logBean) ;
			    		
			    		if(list.size() >= maxSize){
						try {
								
							    logService.insertLogBatch(list) ;
							
							    list.clear() ;
							    Thread.sleep(sleepTime) ;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		}
		    		}else {
		    			try {
		    				
		    				if(list.size()>0){
							    logService.insertLogBatch(list) ;
							    list.clear() ;
		    				}
		    				 System.out.println(FunHelper.getCurrentTime("-"," ",":",true) +" thread:"+threadid+" deep sleep :"+deepSleepTime +" seconds");
						     Thread.sleep(deepSleepTime * 1000) ;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							 list.clear() ;
							e.printStackTrace();
						}
		    		}
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.error("logWriteExecutor:"+ e.getMessage()) ;
					}
		    	} //while end 
		       
		    }  //run end 
		};  
		
		pool.execute(task);  
	} //for end
	
	}
	
	
	public void stop(){
		this.flag = false ;
		try {
			Thread.sleep(100) ;
		
		if(pool !=null)
		{
			pool.shutdown() ;
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}catch(Exception e){
			
		}
	}

}
