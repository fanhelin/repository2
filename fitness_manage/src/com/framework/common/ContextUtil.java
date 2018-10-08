package com.framework.common;

import org.springframework.web.context.WebApplicationContext;
/**
 * 
 * @todo webapplication context uitl
 *
 * @author fhr
 * @dateTime 2016 2016-11-19 下午3:01:34
 */
public class ContextUtil {
	
	private static Object locker = new Object();
	private WebApplicationContext springContext ;
	
	private static ContextUtil instance ;
	private ContextUtil(){
		
	}
	
	public static ContextUtil Instance(){
		
		if(instance == null){
			  synchronized (locker){
				  instance = new ContextUtil() ;
			  }
		}
       return instance;
	}
	
   public  void setContext(WebApplicationContext context){
	   ContextUtil.Instance().springContext = context ;
   }
   
   public  Object getBean(String beanName){
	   if(springContext != null){
		   return springContext.getBean(beanName) ;
	   }
	   return null ;
   }
	
	
}
