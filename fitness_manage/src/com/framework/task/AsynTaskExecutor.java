package com.framework.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;

import com.framework.common.Config;
import com.framework.common.StaticFinal;



/**
 * get a task from it's queue and executor in a thread. 
 * threads can config more than 1. 
 * @todo 
 *
 * @author fhr
 * @dateTime 2016 2016-11-19 下午3:11:47
 */
public class AsynTaskExecutor {
	LinkedBlockingDeque<IAsynTask> queue = null ;
	
	
	private static boolean flag = true ;
	private static int poolSize = 0 ; //处理日志线程数
	private static int sleepTime = 10 ; //ms 每轮休眠时间
	
	private static ExecutorService pool = null;  
	public  Logger log = Logger.getLogger(this.getClass()); 
	

	public AsynTaskExecutor(LinkedBlockingDeque<IAsynTask> queue){
		this.queue = queue ;
		
		poolSize = Integer.parseInt(Config.getConfig(StaticFinal.CONFIG_ASYNTASK_THREADS,"1"));
		sleepTime = Integer.parseInt(Config.getConfig(StaticFinal.CONFIG_ASYNTASK_SLEEP,"10"));
		
		
		pool = Executors.newFixedThreadPool(poolSize);  
		  
	}
	
	public void start(){
	 if(!flag){
		 return ;
	 }
	 
	for(int i=0 ;i< poolSize ;i++){
			
		Runnable task = new Runnable() {  
		    @Override  
		    public void run() {  
		    
		    	long threadid = Thread.currentThread().getId() ;
		    	while(flag){
		    		
		    	try {
		    		    System.out.println("AsynTaskExecutor:" +threadid +" task queue size:"+queue.size());
		    		
		    			IAsynTask iAsynTask = queue.take() ;
		    			iAsynTask.doTask(null) ;
			    	     
		    		
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
