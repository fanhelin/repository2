package com.framework.log;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.context.ApplicationContext;
import com.wx.entity.common.CarClass;
import com.wx.entity.common.LogBean;
import com.wx.service.common.CommonService;




public class LogWriteThread extends Thread {
	
	LinkedBlockingDeque<LogBean> queue = null ;
	
	
	private CommonService commonService ;

	
	public LogWriteThread(ApplicationContext cont,LinkedBlockingDeque<LogBean> queue){
		this.queue = queue ;
		commonService  = (CommonService) cont.getBean("commonService") ;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
       
		try {
			while (true) {
				LogBean logBean = this.queue.take() ;
				
				CarClass carClass = new CarClass();
				carClass.setId(1) ;
				//--carClass.setEntity_id(3) ;
				try {
				List<CarClass> list =	commonService.queryCarClass(carClass) ;
				list.clear() ;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 System.out.println("insert a log:"+logBean.toString());
				
				if(this.queue.size() ==0){
					Thread.sleep(2* 1000) ;
					 System.out.println("log thread sleeped:");
				}
				 System.out.println("this.queue.size():"+this.queue.size());
				 Thread.sleep(20) ;
			}
		
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
