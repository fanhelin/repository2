package com.framework.base;

import org.apache.log4j.Logger;
/**
 * service 基类，日志功能和公共service方法写在该类，基类service继承该类。
 * @author fhr
 *
 */
public class BaseService {
	 public  Logger log = Logger.getLogger(this.getClass()); 
	 
	 protected String getMethodName() {  
		  String clazzName = new Object() {  
		        public String getClassName() {  
		            String clazzName = this.getClass().getName();  
		            return clazzName.substring(0, clazzName.lastIndexOf('$'));  
		        }  
		    }.getClassName(); 
		    
	      StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();  
	      StackTraceElement e = stacktrace[2];  
	      String methodName = e.getMethodName();  
	      return clazzName+"[" + methodName + "]";  
	  } 
}
